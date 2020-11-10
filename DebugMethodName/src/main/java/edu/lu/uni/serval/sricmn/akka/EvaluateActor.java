package edu.lu.uni.serval.sricmn.akka;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import akka.routing.RoundRobinPool;
import edu.lu.uni.serval.sricmn.DetectMethodNames;
import edu.lu.uni.serval.sricmn.info.MethodInfo;
import edu.lu.uni.serval.utils.DataReader;
import edu.lu.uni.serval.utils.FileHelper;
import edu.lu.uni.serval.utils.MapSorter;

public class EvaluateActor extends UntypedActor {

	private static Logger logger = LoggerFactory.getLogger(EvaluateActor.class);
	
	private static final String BODY_SIMILARITIES_FILE_PATH = "OUTPUT/SimilarNamesByBody/";
	private static final String NAME_SIMILARITIES_FILE_PATH = "OUTPUT/SimilarNamesByName_";
//	private static final String DATA_PATH = "Data/";

	private ActorRef travelRouter;
	private final int numberOfTestingWorkers;
	private int numberOfRenamedWorkers;
	private int counter = 0;
	private String[] inputPaths;
	private int nEpochs;
	private int Num_Of_Predict_Tokens = 0;
	private int topNum;
	private int fileId;
	private String similarityType = "NameFeature";
	private boolean isSubToken = false;
	private boolean considerSynonyms = false;
	private boolean needAllSynonyms = false;

	private int numberOfWorkers;
//	private boolean isDescending = true;
	private List<Integer> labels; // 0: inconsistent, 1: consistent.
	private List<MethodInfo> trainingMethodInfo = null;
	private List<MethodInfo> renamedOldMethodInfo = new ArrayList<>();
	private List<MethodInfo> renamedNewMethodInfo = new ArrayList<>();
	private Map<Integer, Map<Integer, Double>> topSimilarBodyMap = new HashMap<>();
	private Map<Integer, Map<Integer, Double>> topSimilarNameMap = new HashMap<>();
	
	public EvaluateActor(int numberOfTestingWorkers, int numberOfRenamedWorkers, String[] inputPaths, int nEpochs, int topNum, 
			boolean considerReturnType, int fileId, int similarityType, boolean isSubToken, boolean considerSynonyms, boolean needAllSynonyms) {
		this.inputPaths = inputPaths;
		this.numberOfTestingWorkers = numberOfTestingWorkers;
		this.numberOfRenamedWorkers = numberOfRenamedWorkers;
		this.numberOfWorkers = numberOfTestingWorkers + numberOfRenamedWorkers;
		this.nEpochs = nEpochs;
		this.topNum = topNum;
		this.fileId = fileId;
		this.considerSynonyms = considerSynonyms;
		this.needAllSynonyms = needAllSynonyms;
		switch (similarityType) {
		case 0:
			this.similarityType = "NameFeature";
			break;
		case 1:
			this.similarityType = "DamerauLevenshtein";
			break;
		case 2:
			this.similarityType = "Jaccard";
			break;
		case 3:
			this.similarityType = "JaroWinkler";
			break;
		case 4:
			this.similarityType = "LevenshteinDistance";
			break;
		case 5:
			this.similarityType = "LongestCommonSubsequence";
			break;
		case 6:
			this.similarityType = "MetricLCS";
			break;
		case 7:
			this.similarityType = "MostFreqKDistance";
			break;
		case 8:
			this.similarityType = "NGram";
			break;
		case 9:
			this.similarityType = "NormalizedLevenshteinDistance";
			break;
		case 10:
			this.similarityType = "OptimalStringAlignment";
			break;
		case 11:
			this.similarityType = "Overlap";
			break;
		case 12:
			this.similarityType = "QGram";
			break;
		case 13:
			this.similarityType = "Sift4";
			break;
		case 14:
			this.similarityType = "SorensenDice";
			break;
		case 15:
			this.similarityType = "Tversky";
			break;
		default:
			break;
		}
		this.isSubToken = isSubToken;
		this.travelRouter = this.getContext().actorOf(new RoundRobinPool(numberOfTestingWorkers + numberOfRenamedWorkers)
				.props(EvaluateWorker.props(topNum, considerReturnType)), "evaluate-router");
	}

	public static Props props(final int numberOfWorkers, final int numberOfRenamedWorkers, final String[] inputPaths, final int nEpochs, final int topNum, 
			final boolean considerReturnType, final int fileId, final int similarityType, final boolean isSubToken, final boolean considerSynonyms, final boolean needAllSynonyms) {
		
		return Props.create(new Creator<EvaluateActor>() {

			private static final long serialVersionUID = -4609365408992093978L;

			@Override
			public EvaluateActor create() throws Exception {
				return new EvaluateActor(numberOfWorkers, numberOfRenamedWorkers, inputPaths, nEpochs, topNum, considerReturnType, fileId, 
						similarityType, isSubToken, considerSynonyms, needAllSynonyms);
			}
			
		});
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Object msg) throws Throwable {
		if (msg instanceof String && msg.toString().equals("BEGIN")) {
			// Read data from existing results.
			readExistingData();

			// If failed to read existing data, measure similarities.
			String rootPath1 = inputPaths[0];
			String rootPath2 = inputPaths[1];
			
			System.out.println("Reading data.");
			readDataFromFile(rootPath1);
			
			File[] files = new File(rootPath2 + "Features_" + nEpochs + "/").listFiles();
			File trainingDataFile = null;
			File testingDataFile = null;
			
			for (File file : files) {
				String fileName = file.getName();
				if (fileName.equals(nEpochs + "_CNNoutput.csv")) {
					trainingDataFile = file;
				} else if (fileName.contains("TestingFeatures_")) {
					testingDataFile = file;
				}
			}
			
			String testingLabelFile = rootPath2 + "TestingLabels.txt";
			DataReader reader = new DataReader();
			labels = reader.readLabels(testingLabelFile, this.renamedOldMethodInfo, this.renamedNewMethodInfo);
			List<MethodInfo> testingMethodInfoList = reader.testingMethodInfoList;
			
			Map<String, List<MethodInfo>> trainingFeatures = reader.readTrainingFeatures(trainingDataFile, trainingMethodInfo);
			
			if (!this.topSimilarBodyMap.isEmpty() && !this.topSimilarNameMap.isEmpty()) {
				finalEvaluation();
				this.getContext().stop(travelRouter);
				this.getContext().stop(getSelf());
				this.getContext().system().shutdown();
			} else {
				if (topSimilarBodyMap.isEmpty()) {
					System.out.println("Measure body similarity.");
					// Features of method bodies.
					List<Double[]> testingFeatures = reader.readFeatures(testingDataFile);
					int testingSize = renamedOldMethodInfo.size();
					int testingAverage = testingSize / numberOfTestingWorkers;
					int testingReminder = testingSize % numberOfTestingWorkers;
					int testing = 0;

					for (int i = 0; i < numberOfTestingWorkers; i ++) {
						int fromIndex = i * testingAverage + testing;
						if (testing < testingReminder) testing ++;
						int toIndex = (i + 1) * testingAverage + testing;
						List<Double[]> subTestingFeatures = testingFeatures.subList(fromIndex, toIndex);
						List<MethodInfo> subTestingMethodInfo = renamedNewMethodInfo.subList(fromIndex, toIndex);

						WorkerMessage workerMsg = new WorkerMessage(i + 1, subTestingFeatures, null, trainingFeatures, fromIndex, toIndex, "BodyFeature");
						workerMsg.setSubTestingMethodInfo(subTestingMethodInfo);
						this.travelRouter.tell(workerMsg, getSelf());
						logger.info("Assign a task to worker #" + (i + 1) + "...");
					}
				}
				
				if (this.topSimilarNameMap.isEmpty()) {
					System.out.println("Measure name similarity.");
					if ("NameFeature" == similarityType) {// 0: NameFeature
						// Features of method names.
						List<Double[]> trainingMethodNameFeatures, testingMethodNameFeatures;
						if (isSubToken) {
							File methodNameFeaturesFile = null;
							files = new File(rootPath2).listFiles();
							for (File file : files) {
								String fileName = file.getName();
								if (fileName.startsWith("MethodNameFeatures_" + fileId + "_") && fileName.endsWith(".txt")) {
									methodNameFeaturesFile = file;
								}
							}
							List<Double[]> methodNameFeatures = reader.readMethodNameFeatures(methodNameFeaturesFile);
							int fromIndex1 = trainingMethodInfo.size();
							trainingMethodNameFeatures = methodNameFeatures.subList(0, fromIndex1);
							testingMethodNameFeatures = methodNameFeatures.subList(fromIndex1, methodNameFeatures.size());
						} else {
							files = new File(rootPath2 + "MethodNamesCNN/DLoutput_" + fileId + "_" + nEpochs + "/").listFiles();
							for (File file : files) {
								String fileName = file.getName();
								if (fileName.equals(nEpochs + "_CNNoutput.csv")) {
									trainingDataFile = file;
								} else if (fileName.contains("TestingFeatures_")) {
									testingDataFile = file;
								}
							}
							trainingMethodNameFeatures = reader.readFeatures(trainingDataFile);
							testingMethodNameFeatures = reader.readFeatures(testingDataFile);
						}
						
						int testingSize2 = testingMethodNameFeatures.size();
						int testingAverage2 = testingSize2 / numberOfRenamedWorkers;
						int testingReminder2 = testingSize2 % numberOfRenamedWorkers;
						int testing2 = 0;
						System.out.println(testingSize2 + "-------" + trainingMethodNameFeatures.size());
						for (int i = 0; i < this.numberOfRenamedWorkers; i ++) {
							int fromIndex = i * testingAverage2 + testing2;
							if (testing2 < testingReminder2) testing2 ++;
							int toIndex = (i + 1) * testingAverage2 + testing2;
							List<Double[]> subTestingFeatures = testingMethodNameFeatures.subList(fromIndex, toIndex);
							List<MethodInfo> subTestingMethodInfoList = testingMethodInfoList.subList(fromIndex, toIndex);

							WorkerMessage workerMsg = new WorkerMessage(i + 1, null, subTestingFeatures, trainingFeatures, fromIndex, toIndex, similarityType);
							workerMsg.setTrainingMethodNameFeatures(trainingMethodNameFeatures);
							workerMsg.setSubTestingMethodInfo(subTestingMethodInfoList);
							this.travelRouter.tell(workerMsg, getSelf());
							logger.info("Assign a task to worker #" + (i + 1 + numberOfTestingWorkers) + "...");
						}
					} else {
						int testingSize2 = testingMethodInfoList.size();
						int testingAverage2 = testingSize2 / numberOfRenamedWorkers;
						int testingReminder2 = testingSize2 % numberOfRenamedWorkers;
						int testing2 = 0;
						System.out.println(testingSize2 + "-------" + trainingMethodInfo.size());
						for (int i = 0; i < this.numberOfRenamedWorkers; i ++) {
							int fromIndex = i * testingAverage2 + testing2;
							if (testing2 < testingReminder2) testing2 ++;
							int toIndex = (i + 1) * testingAverage2 + testing2;
							List<MethodInfo> subTestingMethodInfoList = testingMethodInfoList.subList(fromIndex, toIndex);

							WorkerMessage workerMsg = new WorkerMessage(i + 1, null, null, trainingFeatures, fromIndex, toIndex, similarityType);
							workerMsg.setSubTestingMethodInfo(subTestingMethodInfoList);
							workerMsg.setIsSubToken(isSubToken);
							this.travelRouter.tell(workerMsg, getSelf());
							logger.info("Assign a task to worker #" + (i + 1 + numberOfTestingWorkers) + "...");
						}
					}
				}
			}
		} else if (msg instanceof WorkerReturnMessage) {
			counter ++;
			
			WorkerReturnMessage wrMsg = (WorkerReturnMessage) msg;
			if ("SimilarBodies".equals(wrMsg.getType())) {
				topSimilarBodyMap.putAll(wrMsg.getTopPredictedTokens());
			} else {
				topSimilarNameMap.putAll(wrMsg.getTopPredictedTokens());
			}
			
			if (counter >= numberOfWorkers) {
				logger.info("All workers finished their work...");
				
//				NamesStringSimilarity nStrSm = new NamesStringSimilarity();
//				StringMetrics stringMetric = nStrSm.readStringMetric(this.similarityType);
//				if (stringMetric != null) {
//					isDescending = nStrSm.isDescending;
//				}
				
				//output the data of top num.
				outputTopNumData();
				
				finalEvaluation();
				
				this.getContext().stop(travelRouter);
				this.getContext().stop(getSelf());
				this.getContext().system().shutdown();
			}
		} else {
			unhandled(msg);
		}
	}
	
//	@SuppressWarnings("unchecked")
	private void readDataFromFile(String rootPath) throws IOException, ClassNotFoundException {
//		File trainingMethodFile = new File(DATA_PATH + "TrainingMethodInfo.obj");
//		File oldMethodInfoFile = new File(DATA_PATH + "OldMethodInfo.obj");
//        File newMethodInfoFile = new File(DATA_PATH + "NewMethodInfo.obj");
        
//        if (trainingMethodFile.exists()) {
//			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(trainingMethodFile));
//            Object object = objectInputStream.readObject();
//            if (object instanceof List<?>) {
//            	trainingMethodInfo = (List<MethodInfo>) object;
//            }
//            objectInputStream.close();
//            
//            objectInputStream = new ObjectInputStream(new FileInputStream(oldMethodInfoFile));
//            object = objectInputStream.readObject();
//            if (object instanceof List<?>) {
//            	renamedOldMethodInfo = (List<MethodInfo>) object;
//            }
//            objectInputStream.close();
//            
//            objectInputStream = new ObjectInputStream(new FileInputStream(newMethodInfoFile));
//            object = objectInputStream.readObject();
//            if (object instanceof List<?>) {
//            	renamedOldMethodInfo = (List<MethodInfo>) object;
//            }
//            objectInputStream.close();
//		}
		
		DataReader reader = new DataReader();
//		if (trainingMethodInfo == null) {
			trainingMethodInfo = reader.readMethodInfo(rootPath + "SelectedData/SelectedMethodInfo.txt");
			
//			if (!new File(DATA_PATH).exists()) new File(DATA_PATH).mkdirs();
//			trainingMethodFile.createNewFile();
//			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(trainingMethodFile));
//            objectOutputStream.writeObject(trainingMethodInfo);
//            objectOutputStream.close();
            
            reader.readRenamedMethodInfo(rootPath + "RenamedMethods/MethodInfo.txt", rootPath + "RenamedMethods/ParsedMethodNames.txt",
    				renamedOldMethodInfo, renamedNewMethodInfo);
            
//            objectOutputStream = new ObjectOutputStream(new FileOutputStream(oldMethodInfoFile));
//            objectOutputStream.writeObject(renamedOldMethodInfo);
//            objectOutputStream.close();
//
//            objectOutputStream = new ObjectOutputStream(new FileOutputStream(newMethodInfoFile));
//            objectOutputStream.writeObject(renamedNewMethodInfo);
//            objectOutputStream.close();
//		}
		
		readRenamedMethodTokens(rootPath + "RenamedMethods/ParsedMethodNames.txt");
	}

	private void finalEvaluation() {
//		NamesStringSimilarity nStrSm = new NamesStringSimilarity();
//		StringMetrics stringMetric = nStrSm.readStringMetric(this.similarityType);
//		if (stringMetric != null) {
//			isDescending = nStrSm.isDescending;
//		}
		
		System.out.println("======SimilarNamesByName_" + this.similarityType + "_" + (isSubToken ? "SubToken" : "FullName"));
		
		// Evaluate the performance of identifying inconsistent method names.
//		System.out.println(topSimilarBodyMap.size() + "=======" + topSimilarNameMap.size());
		int[] topNums = {1, 5, 10, 20, 30, 40};//, 50, 60, 70, 80, 90, 100};
		for (int topNum : topNums) {
			if (topNum > this.topNum) topNum = this.topNum;
			System.out.println("========Number of most similar methods: " + topNum + " =========");
			new DetectMethodNames().evaluate(renamedOldMethodInfo, renamedNewMethodInfo, trainingMethodInfo, 
					topSimilarBodyMap, topSimilarNameMap, labels, topNum, considerSynonyms, needAllSynonyms);
			if (topNum == this.topNum) break;
		}
		
//		// Evaluate the performance of suggesting new names for identified inconsistent method names.
//		for (int topNum : topNums) {
//			int numOfPredictTokens;
//			if (topNum < 10) continue;
//			if (topNum > this.topNum) topNum = this.topNum;
//			System.out.println("========Number of most similar methods: " + topNum + " =========");
//			for (int i = 1; i <= Num_Of_Predict_Tokens; i ++) {
//				if (i != 1 && i != 3) continue;
//				numOfPredictTokens = i;
//				System.out.println("========Number of Tokens: " + i + " =========");
//				new DetectMethodNames().evaluate1(renamedOldMethodInfo, renamedNewMethodInfo, trainingMethodInfo, 
//						topSimilarBodyMap, topSimilarNameMap, numOfPredictTokens, labels, topNum, considerSynonyms, needAllSynonyms);
//			}
//			if (topNum == this.topNum) break;
//		}
		
		// Evaluate the performance of suggesting new names for inconsistent method names, when only considering the top-10 similar methods with normal ranking.
		System.out.println("========Number of most similar methods=========ONLY TOP-5");
		for (int i = 1; i <= Num_Of_Predict_Tokens; i ++) {
			if (i != 1 && i != 3) continue;
			System.out.println("========Number of Tokens: " + i + " =========");
			new DetectMethodNames().evaluate2(renamedOldMethodInfo, renamedNewMethodInfo, trainingMethodInfo, 
					topSimilarBodyMap, topSimilarNameMap, i, labels, 10, considerSynonyms, needAllSynonyms);
		}
		
		// Evaluate the performance of suggesting new names for inconsistent method names with the method proposed in 2016 ICML paper.
		int topNum = 10;
		new DetectMethodNames().evaluate3(renamedNewMethodInfo, trainingMethodInfo, topSimilarBodyMap, topNum, considerSynonyms, needAllSynonyms);
	}

	private void readExistingData() throws IOException {
		System.out.println(this.numberOfWorkers);
		readSimilarities(BODY_SIMILARITIES_FILE_PATH, this.topSimilarBodyMap);
		if (!this.topSimilarBodyMap.isEmpty()) {
			this.numberOfWorkers -= this.numberOfTestingWorkers;
			System.out.println("read data1.");
		}
		System.out.println(this.numberOfWorkers);
		readSimilarities(NAME_SIMILARITIES_FILE_PATH  + this.similarityType + "_" + (isSubToken ? "SubToken" : "FullName") + "_" + this.fileId + "/", this.topSimilarNameMap);
		if (!this.topSimilarNameMap.isEmpty()) {
			this.numberOfWorkers -= this.numberOfRenamedWorkers;
			System.out.println("read data2.");
		}
		System.out.println(this.numberOfWorkers);
	}

	private void readSimilarities(String filePath, Map<Integer, Map<Integer, Double>> similaritiesMap) throws IOException {
		File parentFile = new File(filePath);
		if (parentFile.exists()) {
			File[] files = parentFile.listFiles();
			for (File file : files) {
				String fileName = file.getName();
				if (fileName.endsWith(".txt")) {
					String indexStr = fileName.substring(0, fileName.length() - 4);
					if (!StringUtils.isNumeric(indexStr)) continue;
					Integer index = Integer.valueOf(indexStr);
					Map<Integer, Double> similarities = readSimilarities(file);
					similaritiesMap.put(index, similarities);
				}
			}
		}
	}

	private Map<Integer, Double> readSimilarities(File file) throws IOException {
		Map<Integer, Double> similarities = new HashMap<>();
		String content = FileHelper.readFile(file);
		StringReader strReader = new StringReader(content);
		BufferedReader reader = new BufferedReader(strReader);
		String line = null;
		while((line = reader.readLine()) != null) {
			String[] elements = line.split("@");
			Integer index = Integer.valueOf(elements[0]);
			Double similarity = Double.valueOf(elements[2]);
			similarities.put(index, similarity);
		}
		reader.close();
		strReader.close();
		return similarities;
	}

	private void outputTopNumData() {
		for (Map.Entry<Integer, Map<Integer, Double>> entry : this.topSimilarBodyMap.entrySet()) {
			int key = entry.getKey();
			Map<Integer, Double> indexes = entry.getValue();
			MapSorter<Integer, Double> sorter = new MapSorter<>();
			indexes = sorter.sortByValueDescending(indexes);// Body similarities are cosine similarities of learned body feature vectors.
			
			StringBuilder builder = new StringBuilder();
			int count = 0;
			for (Map.Entry<Integer, Double> subEntry : indexes.entrySet()) {
				int index = subEntry.getKey();
				builder.append(index).append("@");
				MethodInfo methodInfo = this.trainingMethodInfo.get(index);
				List<String> methodNameTokens = methodInfo.getMethodNameTokens();
				for (String token : methodNameTokens) {
					builder.append(token).append(",");
				}
				builder.append("@").append(subEntry.getValue()).append("\n");
				count ++;
				if (count == this.topNum) break;
			}
			FileHelper.outputToFile(BODY_SIMILARITIES_FILE_PATH + key + ".txt", builder, false);
		}
		
		for (Map.Entry<Integer, Map<Integer, Double>> entry : this.topSimilarNameMap.entrySet()) {
			int key = entry.getKey();
			Map<Integer, Double> similarities = entry.getValue();
			MapSorter<Integer, Double> sorter = new MapSorter<>();
			similarities = sorter.sortByValueDescending(similarities);//this.isDescending ? sorter.sortByValueDescending(similarities) : sorter.sortByValueAscending(similarities);
			
			StringBuilder builder = new StringBuilder();
			int count = 0;
			for (Map.Entry<Integer, Double> subEntry : similarities.entrySet()) {
				int index = subEntry.getKey();
				builder.append(index).append("@");
				MethodInfo methodInfo = this.trainingMethodInfo.get(index);
				List<String> methodNameTokens = methodInfo.getMethodNameTokens();
//				for (String token : methodNameTokens) {
//					builder.append(token).append(",");
//				}
				builder.append(methodNameTokens.toString());
				builder.append("@").append(subEntry.getValue()).append("\n");
				count ++;
				if (count == this.topNum) break;
			}
			FileHelper.outputToFile(NAME_SIMILARITIES_FILE_PATH  + this.similarityType + "_" + (isSubToken ? "SubToken" : "FullName") + "_" + this.fileId + "/" + key + ".txt", builder, false);
		}
	}

	private void readRenamedMethodTokens(String fileName) throws IOException {
		String content = FileHelper.readFile(fileName);
		BufferedReader reader = new BufferedReader(new StringReader(content));
		String line = null;
		while ((line = reader.readLine()) != null) {
			String tokensStr = line.substring(line.indexOf("@") + 1);
			String[] tokens = tokensStr.split(",");
			int length = tokens.length;
			if (this.Num_Of_Predict_Tokens < length) {
				this.Num_Of_Predict_Tokens = length;
			}
		}
		reader.close();
	}

}
