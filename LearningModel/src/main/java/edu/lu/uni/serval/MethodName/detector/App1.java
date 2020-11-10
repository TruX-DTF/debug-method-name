package edu.lu.uni.serval.MethodName.detector;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import edu.lu.uni.Configuration;
import edu.lu.uni.deeplearning.extractor.CNNFeatureExtractor;
import edu.lu.uni.serval.dlMethods.TokensEmbedder;
import edu.lu.uni.serval.utils.FileHelper;

/**
 * Learning features for detecting inconsistent method names.
 * 
 * @author kui.liu
 *
 */
public class App1 {
	private static int maxSize = 0;
		
	public static void main(String[] args) throws IOException, InterruptedException {
		String rootPath = Configuration.ROOT_PATH;
		String inputPath = Configuration.DL_DATA_PATH;
		String trainingData = inputPath + "SelectedData/SelectedMethodTokens.txt";  // SelectedSizes.csv, SelectedMethodTokens.txt, SelectedMethodInfo.txt
		String testingData = inputPath + "RenamedMethods/MethodTokens.txt"; // MethodTokens.txt, MethodInfo.txt, ParsedMethodNames.txt
		String outputPath = rootPath + "Detect_Data/";
		FileHelper.deleteDirectory(outputPath);

		vectorizedData(inputPath, outputPath, new File(trainingData), new File(testingData), inputPath + "SelectedData/SelectedSizes.csv");
		
		File[] files = new File(outputPath).listFiles();
		File trainingDataFile = null;
		File testingDataFile = null;
		
		for (File file : files) {
			String fileName = file.getName();
			if (fileName.startsWith("TrainingData_")) {
				trainingDataFile = file;
			} else if (fileName.startsWith("TestingData_")) {
				testingDataFile = file;
			}
		}
		
		int sizeOfTokensVector = maxSize;
		int sizeOfEmbeddedVector = 300;
		int batchSize = Configuration.BATCH_SIZE;
		int sizeOfFeatureVector = Configuration.SIZE_OF_FEATURE_VECTOR;
		
		int nEpochs = Integer.valueOf(args[0]);//1, 10, 20
		String featureOutputPath = outputPath + "Features_" + nEpochs + "/";
		CNNFeatureExtractor learner = new CNNFeatureExtractor(trainingDataFile, sizeOfTokensVector, sizeOfEmbeddedVector, batchSize, sizeOfFeatureVector);
		learner.setNumberOfEpochs(nEpochs);
		learner.setSeed(123);
		learner.setNumOfOutOfLayer1(20);
		learner.setNumOfOutOfLayer2(50);
		learner.setOutputPath(featureOutputPath);
		
		learner.extracteFeaturesWithCNN();
		
		File modelFile = new File(featureOutputPath + "CNNoutput.zip");
		learner.setModelFile(modelFile);
		learner.setTestingData(testingDataFile);
		learner.extracteFeaturesWithCNNByLoadingModel();
		
		// TrainingFeatures: outputPath + "1_CNNoutput.csv"
		// TestingFeatures: outputPath + "TestingFeatures_"...
	}
	
	public static void vectorizedData(String inputPath, String outputPath, File trainingDataFile, File testingDataFile, String sizeFileName) throws IOException {
		String embeddedTokensFile = inputPath + "embedding/embeddedTokens.txt";
		Map<String, String> embeddedTokens = new TokensEmbedder().readEmbeddedTokens(embeddedTokensFile);
		
		File trainDataFile = new File(inputPath + "TrainingData/");
		if (trainDataFile.exists()) {
			File[] files = trainDataFile.listFiles();
			for (File file : files) {
				String fileName = file.getName();
				if (fileName.startsWith("Tokens_MaxSize=")) {
					maxSize = Integer.parseInt(fileName.substring("Tokens_MaxSize=".length(), fileName.lastIndexOf(".txt")));
					break;
				}
			}
		}
		
		if (maxSize == 0) {
			maxSize = readMaxSize(sizeFileName);
		}
		
		StringBuilder zeroVector = new StringBuilder();
		int size = Configuration.SIZE_OF_EMBEDDED_VECTOR - 1;
		for (int i = 0; i < size; i ++) {
			zeroVector.append("0,");
		}
		zeroVector.append("0");
		System.out.println(maxSize);
		
		new TokensEmbedder().vectorizeTokenVector(trainingDataFile, embeddedTokens, maxSize, zeroVector, outputPath + "TrainingData_");
		new TokensEmbedder().vectorizeTokenVector(testingDataFile, embeddedTokens, maxSize, zeroVector, outputPath + "TestingData_");
	}
	
	private static int readMaxSize(String sizesFile) throws IOException {
		int maxSize = 0;
		String sizesContent = FileHelper.readFile(sizesFile);
		BufferedReader reader = new BufferedReader(new StringReader(sizesContent));
		String line = null;
		while ((line = reader.readLine()) != null) {
			int a = Integer.parseInt(line);
			if (maxSize < a) {
				maxSize = a;
			}
		}
		reader.close();
		
		return maxSize;
	}

}
