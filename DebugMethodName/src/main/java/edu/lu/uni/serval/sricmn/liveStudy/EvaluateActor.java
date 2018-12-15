package edu.lu.uni.serval.sricmn.liveStudy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import akka.routing.RoundRobinPool;
import edu.lu.uni.serval.sricmn.akka.WorkerMessage;
import edu.lu.uni.serval.sricmn.akka.WorkerReturnMessage;
import edu.lu.uni.serval.sricmn.info.MethodInfo;
import edu.lu.uni.serval.utils.DataReader;

public class EvaluateActor extends UntypedActor {

	private static Logger logger = LoggerFactory.getLogger(EvaluateActor.class);

	private ActorRef travelRouter;
	private final int numberOfBodyFeatureWorkers;
	private final int numberOfNameFeatureWorkers;
	private int counter = 0;
	private String inputPath;
	private int nEpochs;
	private int topNum;
	private int fileId;
	private int startIndex;
	private int endIndex;
	
	public EvaluateActor(int numberOfBodyFeatureWorkers, int numberOfNameFeatureWorkers, String inputPath, int nEpochs, int topNum, boolean considerReturnType, int fileId, int startIndex, int endIndex) {
		this.numberOfBodyFeatureWorkers = numberOfBodyFeatureWorkers;
		this.numberOfNameFeatureWorkers = numberOfNameFeatureWorkers;
		this.inputPath = inputPath;
		this.nEpochs = nEpochs;
		this.topNum = topNum;
		this.fileId = fileId;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.travelRouter = this.getContext().actorOf(new RoundRobinPool(numberOfBodyFeatureWorkers + numberOfNameFeatureWorkers)
				.props(EvaluateWorker.props(topNum, considerReturnType)), "evaluate-router");
	}

	public static Props props(final int numberOfWorkers, final int numberOfNameFeatureWorkers, final String inputPath, final int nEpochs, final int topNum, final boolean considerReturnType, final int fileId, final int startIndex, final int endIndex) {
		return Props.create(new Creator<EvaluateActor>() {

			private static final long serialVersionUID = -4609365408992093978L;

			@Override
			public EvaluateActor create() throws Exception {
				return new EvaluateActor(numberOfWorkers, numberOfNameFeatureWorkers, inputPath, nEpochs, topNum, considerReturnType, fileId, startIndex, endIndex);
			}
			
		});
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Object msg) throws Throwable {
		if (msg instanceof String && msg.toString().startsWith("BEGIN")) {
			String bodyFeaturesFilePath = inputPath + "DLoutput_" + nEpochs + "/";
			System.out.println("bodyFeaturesFilePath" + bodyFeaturesFilePath);
			File[] files = new File(bodyFeaturesFilePath).listFiles();
			File trainingBodyFeaturesFile = null;
			File testingBodyFeaturesFile = null;//TestingFeatures_
			
			for (File file : files) {
				String fileName = file.getName();
				if (fileName.equals(nEpochs + "_CNNoutput.csv")) {
					trainingBodyFeaturesFile = file;
				} else if (fileName.startsWith("TestingFeatures_")) {
					testingBodyFeaturesFile = file;
				}
			}

			DataReader reader = new DataReader();
			trainingMethodInfo = reader.readMethodInfo(inputPath + "TrainingData/MethodsInfo.txt");
			testingMethodInfo = reader.readMethodInfo(inputPath + "TestingData/MethodsInfo.txt").subList(this.startIndex, this.endIndex);
			
			// Method body features.
			Map<String, List<MethodInfo>> trainingFeatures = new DataReader().readTrainingFeatures(trainingBodyFeaturesFile, trainingMethodInfo);
			List<Double[]> testingBodyFeatures = reader.readMethodNameFeatures(testingBodyFeaturesFile).subList(this.startIndex, this.endIndex);
			
			int testingSize = testingMethodInfo.size();
			int testingAverage = testingSize / numberOfBodyFeatureWorkers;
			int testingReminder = testingSize % numberOfBodyFeatureWorkers;
			int testing = 0;

			System.out.println(this.startIndex + " ------ " + this.endIndex);
			System.out.println(testingSize + "-------" + testingBodyFeatures.size());
			for (int i = 0; i < numberOfBodyFeatureWorkers; i ++) {
				int fromIndex = i * testingAverage + testing;
				if (testing < testingReminder) testing ++;
				int toIndex = (i + 1) * testingAverage + testing;
				List<Double[]> subTestingFeatures = testingBodyFeatures.subList(fromIndex, toIndex);
				List<MethodInfo> subTestingMethodInfo = testingMethodInfo.subList(fromIndex, toIndex);

				final WorkerMessage workerMsg = new WorkerMessage(i + 1, subTestingFeatures, null, trainingFeatures, fromIndex, toIndex, "");
				workerMsg.setSubTestingMethodInfo(subTestingMethodInfo);
				this.travelRouter.tell(workerMsg, getSelf());
				logger.info("Assign a task to worker #" + (i + 1) + "...");
				if (i == numberOfBodyFeatureWorkers - 1) {
					System.out.println(toIndex + "-------");
				}
			}
			
			// Method name features.
			File methodNameFeatureFile = null;
			String nameFeatureFath = inputPath + "NameFeatures/";
			File[] subFiles = new File(nameFeatureFath).listFiles();
			for (File file : subFiles) {
				String fileName = file.getName();
				if (fileName.startsWith("MethodNameFeatures_" + fileId + "_Size=") && fileName.endsWith(".txt")) {
					methodNameFeatureFile = file;
					System.out.println("MethodNameFeaturesFile: " + methodNameFeatureFile);
				}
			}
			List<Double[]> allMethodNameFeatures = new DataReader().readMethodNameFeatures(methodNameFeatureFile);
			int index1 = trainingMethodInfo.size();
			List<Double[]> traingingNameFeaturesList = allMethodNameFeatures.subList(0, index1);
			List<Double[]> testingNameFeatures = allMethodNameFeatures.subList(index1, allMethodNameFeatures.size()).subList(this.startIndex, this.endIndex);
			int renamedSize = testingNameFeatures.size();
			int renamedAverage = renamedSize / numberOfNameFeatureWorkers;
			int renamedReminder = renamedSize % numberOfNameFeatureWorkers;
			int renamed = 0;
			System.out.println(renamedSize + "-------");
			for (int i = 0; i < numberOfNameFeatureWorkers; i ++) {
				int fromIndex = i * renamedAverage + renamed;
				if (renamed < renamedReminder) renamed ++;
				int toIndex = (i + 1) * renamedAverage + renamed;
				List<Double[]> subTestingNameFeatures = testingNameFeatures.subList(fromIndex, toIndex);
				
				final WorkerMessage workerMsg = new WorkerMessage(i, null, subTestingNameFeatures, null, fromIndex, toIndex, "");
				workerMsg.setTrainingMethodNameFeatures(traingingNameFeaturesList);
				this.travelRouter.tell(workerMsg, getSelf());
				logger.info("Assign a task to worker #" + (i + numberOfBodyFeatureWorkers + 1) + "...");
				if (i == numberOfNameFeatureWorkers - 1) {
					System.out.println(toIndex + "-------");
				}
			}
		} else if (msg instanceof WorkerReturnMessage) {
			counter ++;
			
			WorkerReturnMessage wrMsg = (WorkerReturnMessage) msg;
			if ("BodyFeatures".equals(wrMsg.getType())) {
				topSimilarBodyMap.putAll(wrMsg.getTopPredictedTokens());
			} else {
				topSimilarNameMap.putAll(wrMsg.getTopPredictedTokens());
			}
			
			if (counter >= numberOfBodyFeatureWorkers + numberOfNameFeatureWorkers) {
				logger.info("All workers finished their work...");
				System.out.println(topSimilarBodyMap.size() + "=======" + topSimilarNameMap.size());
				//Identification: 1, Suggestion: 10.

				readTestingMethodBody();
				
				int[] topNums = {1, 5, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
				for (int topNum : topNums) {
					if (topNum > this.topNum) topNum = this.topNum;
					System.out.println("========Number of most similar methods: " + topNum + " =========");
					int numOfPredictTokens = 1;
					boolean identifyWithTopOne = false;
					new LiveStudyEvl().evaluate(trainingMethodInfo, testingMethodInfo, topSimilarBodyMap, topSimilarNameMap, topNum, inputPath + "Results_" + topNum + "_" + identifyWithTopOne + "_" +  numOfPredictTokens + "-" + this.startIndex + "-" + this.endIndex + "/", numOfPredictTokens, identifyWithTopOne);
					numOfPredictTokens = 1;
					identifyWithTopOne = true;
					new LiveStudyEvl().evaluate(trainingMethodInfo, testingMethodInfo, topSimilarBodyMap, topSimilarNameMap, topNum, inputPath + "Results_" + topNum + "_" + identifyWithTopOne + "_" +  numOfPredictTokens + "-" + this.startIndex + "-" + this.endIndex + "/", numOfPredictTokens, identifyWithTopOne);
					numOfPredictTokens = 3;
					identifyWithTopOne = false;
					new LiveStudyEvl().evaluate(trainingMethodInfo, testingMethodInfo, topSimilarBodyMap, topSimilarNameMap, topNum, inputPath + "Results_" + topNum + "_" + identifyWithTopOne + "_" +  numOfPredictTokens + "-" + this.startIndex + "-" + this.endIndex + "/", numOfPredictTokens, identifyWithTopOne);
					numOfPredictTokens = 3;
					identifyWithTopOne = false;
					new LiveStudyEvl().evaluate(trainingMethodInfo, testingMethodInfo, topSimilarBodyMap, topSimilarNameMap, topNum, inputPath + "Results_" + topNum + "_" + identifyWithTopOne + "_" +  numOfPredictTokens + "-" + this.startIndex + "-" + this.endIndex + "/", numOfPredictTokens, identifyWithTopOne);
					if (topNum == this.topNum) break;
				}
				
				this.getContext().stop(travelRouter);
				this.getContext().stop(getSelf());
				this.getContext().system().shutdown();
			}
		} else {
			unhandled(msg);
		}
	}
	
	private void readTestingMethodBody() throws IOException {
		FileInputStream fis = new FileInputStream(inputPath + "TestingData/method_bodies.txt");
		Scanner scanner = new Scanner(fis);
		StringBuilder methodBody = new StringBuilder();
		int index = -1;
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if ("#METHOD_BODY#========================".equals(line)) {
				if (methodBody.length() > 0) {
					if (this.startIndex <= index && index < this.endIndex) {
						MethodInfo methodInfo = testingMethodInfo.get(index - this.startIndex);
						methodInfo.setMethodBody(methodBody.toString());
					}
				}
				methodBody.setLength(0);
				index ++;
				if (index >= this.endIndex) break;
			}
			methodBody.append(line).append("\n");
		}
		scanner.close();
		fis.close();
		
		if (this.startIndex <= index && index < this.endIndex) {
			MethodInfo methodInfo = testingMethodInfo.get(index - this.startIndex);
			methodInfo.setMethodBody(methodBody.toString());
		}
	}

	List<MethodInfo> trainingMethodInfo;
	List<MethodInfo> testingMethodInfo;
	Map<Integer, Map<Integer, Double>> topSimilarBodyMap = new HashMap<>();
	Map<Integer, Map<Integer, Double>> topSimilarNameMap = new HashMap<>();

}
