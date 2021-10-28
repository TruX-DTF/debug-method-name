package edu.lu.uni.serval.MethodName.detector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

import edu.lu.uni.Configuration;
import edu.lu.uni.serval.deeplearner.SentenceEncoder;
import edu.lu.uni.serval.utils.FileHelper;
import edu.lu.uni.serval.utils.ReturnType;
import edu.lu.uni.serval.utils.ReturnType.ReturnTypeClassification;

/**
 * Learn features of method names with ParagraphVectors.
 * 
 * @author kui.liu
 *
 */
public class MethodNameFeatureLearner {
	
	public int SIZE = 0;

	public static void main(String[] args) throws IOException {
		MethodNameFeatureLearner learner = new MethodNameFeatureLearner();
		String inputPath = Configuration.DL_DATA_PATH;
		String methodNameTokensFile = Configuration.RENAMED_METHODS_PATH + "ParsedMethodNames.txt";
		String outputPath = Configuration.EVALUATION_DATA_PATH;
		// Selecting data for method name feature learning.
		String testingMethodNamesFile = outputPath + "TestingMethodNames.txt";
		learner.prepareData(methodNameTokensFile, testingMethodNamesFile, outputPath + "TestingLabels.txt");
		
		String trainingData = inputPath + "SelectedData/SelectedMethodInfo.txt";
		String featureLearningData1 = outputPath + "FeatureLearningData1.txt"; // without return type.
		String featureLearningData2 = outputPath + "FeatureLearningData2.txt"; // with return type.
		String returnTypeOfTestingFile = inputPath + "RenamedMethods/MethodInfo.txt";
		learner.prepareFeatureLearningData(trainingData, testingMethodNamesFile, featureLearningData1, featureLearningData2, returnTypeOfTestingFile);
		
		learner.learnFeatures(new File(featureLearningData1), outputPath + "MethodNameFeatures_1_Size=" + learner.SIZE + ".txt");
		learner.learnFeatures(new File(featureLearningData2), outputPath + "MethodNameFeatures_2_Size=" + learner.SIZE + ".txt");
		learner.learnFeatures(new File(featureLearningData1 + ".bak"), outputPath + "MethodNameFeatures_1_Size=" + learner.SIZE + ".txt.bak");
		learner.learnFeatures(new File(featureLearningData2 + ".bak"), outputPath + "MethodNameFeatures_2_Size=" + learner.SIZE + ".txt.bak");
	} 

	public void prepareData(String methodNameTokensFile, String outputFile, String outputLabelFile) throws IOException {
		// tokens of old method names @ tokens of new method names.
		List<String> methodNames = readFile(methodNameTokensFile);
		int numConsistent = methodNames.size() / 2;
		int numInconsistent = methodNames.size() - numConsistent;
		StringBuilder builder = new StringBuilder();
		StringBuilder labelBuilder = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		StringBuilder labelBuilder2 = new StringBuilder();
		Random random = new Random();
		for (String methodNameStr : methodNames) {
			int index = methodNameStr.indexOf("@");
			int nextNumber = random.nextInt(100);
			String methodName;
			String methodName2;
			if (nextNumber < 50) {// inconsistent data: old name.
				if (numInconsistent == 0) {
					labelBuilder.append("1\n");
					methodName = methodNameStr.substring(index + 1);
					labelBuilder2.append("0\n");
					methodName2 = methodNameStr.substring(0, index);
				} else {
					labelBuilder.append("0\n");
					methodName = methodNameStr.substring(0, index);
					labelBuilder2.append("1\n");
					methodName2 = methodNameStr.substring(index + 1);
					numInconsistent --;
				}
			} else { // consistent data: new name.
				if (numConsistent == 0) {
					labelBuilder.append("0\n");
					methodName = methodNameStr.substring(0, index);
					labelBuilder2.append("1\n");
					methodName2 = methodNameStr.substring(index + 1);
				} else {
					numConsistent --;
					labelBuilder.append("1\n");
					methodName = methodNameStr.substring(index + 1);
					labelBuilder2.append("0\n");
					methodName2 = methodNameStr.substring(0, index);
				}
			}
			builder.append(methodName.replace(",", " ").toLowerCase(Locale.ROOT));
			builder.append("\n");
			builder2.append(methodName2.replace(",", " ").toLowerCase(Locale.ROOT));
			builder2.append("\n");
			SIZE ++;
		}

		FileHelper.outputToFile(outputFile, builder, false);
		FileHelper.outputToFile(outputLabelFile, labelBuilder, false);
		FileHelper.outputToFile(outputFile + ".bak", builder2, false);
		FileHelper.outputToFile(outputLabelFile + ".bak", labelBuilder2, false);
	}

	private List<String> readFile(String methodNameTokensFile) throws IOException {
		List<String> methodNames = new ArrayList<>();
		String content = FileHelper.readFile(methodNameTokensFile);
		BufferedReader reader = new BufferedReader(new StringReader(content));
		String line = null;
		while ((line = reader.readLine()) != null) {
			methodNames.add(line);
		}
		reader.close();
		return methodNames;
	}

	public void prepareFeatureLearningData(String trainingData, String testingMethodNamesFile,
			String featureLearningData1, String featureLearningData2, String returnTypeOfTestingFile) throws IOException {
		FileInputStream fis = new FileInputStream(trainingData);
		Scanner scanner = new Scanner(fis);

		StringBuilder builder = new StringBuilder();
		StringBuilder returnTypeBuilder = new StringBuilder();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			int index = line.lastIndexOf("@");
			String tokens = line.substring(index + 1).replace(",", " ").toLowerCase(Locale.ROOT);
			builder.append(tokens).append("\n");
			
			line = line.substring(0, index);
			index = line.lastIndexOf("@");
			String returnType = line.substring(index + 1);
			returnTypeBuilder.append(returnType).append(" ").append(tokens).append("\n");
		}
		scanner.close();
		fis.close();
		
		FileHelper.outputToFile(featureLearningData1, builder, false);
		FileHelper.outputToFile(featureLearningData1 + ".bak", builder, false);
		String content = FileHelper.readFile(testingMethodNamesFile);
		String contentBak = FileHelper.readFile(testingMethodNamesFile + ".bak");
		FileHelper.outputToFile(featureLearningData1, content, true);
		FileHelper.outputToFile(featureLearningData1 + ".bak", contentBak, true);
		
		if (featureLearningData2 != null) {
			FileHelper.outputToFile(featureLearningData2, returnTypeBuilder, false);
			FileHelper.outputToFile(featureLearningData2 + ".bak", returnTypeBuilder, false);
		}
		returnTypeBuilder.setLength(0);
		StringBuilder returnTypeBuilderBak = new StringBuilder();

		List<String> tokensList = readTokensList(content);
		List<String> tokensListBak = readTokensList(contentBak);
		
		BufferedReader reader = new BufferedReader(new StringReader(FileHelper.readFile(returnTypeOfTestingFile)));
		int index = 0;
		String line = null;
		while ((line = reader.readLine()) != null) {
			String returnType = line.substring(line.lastIndexOf(":") + 1);
			returnType = new ReturnType().readReturnType(returnType, ReturnTypeClassification.ABSTRACT);
			returnTypeBuilder.append(returnType).append(" ").append(tokensList.get(index)).append("\n");
			returnTypeBuilderBak.append(returnType).append(" ").append(tokensListBak.get(index)).append("\n");
			index ++;
		}
		reader.close();
		
		if (featureLearningData2 != null) {
			FileHelper.outputToFile(featureLearningData2, returnTypeBuilder, true);
			FileHelper.outputToFile(featureLearningData2 + ".bak", returnTypeBuilderBak, true);
		}
	}

	private List<String> readTokensList(String content) throws IOException {
		List<String> tokensList = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new StringReader(content));
		String line = null;
		while ((line = reader.readLine()) != null) {
			tokensList.add(line);
		}
		reader.close();
		return tokensList;
	}

	public void prepareFeatureLearningData(String dataFile, String featureLearningData1, String featureLearningData2,
			boolean appended) throws IOException {
		FileInputStream fis = new FileInputStream(dataFile);
		Scanner scanner = new Scanner(fis);

		StringBuilder builder = new StringBuilder();
		StringBuilder returnTypeBuilder = new StringBuilder();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			int index = line.lastIndexOf("@");
			String tokens = line.substring(index + 1).replace(",", " ");
			builder.append(tokens).append("\n");

			line = line.substring(0, index);
			index = line.lastIndexOf("@");
			String returnType = line.substring(index + 1);
			returnTypeBuilder.append(returnType).append(" ").append(tokens).append("\n");
			if (appended) this.SIZE ++;
		}
		scanner.close();
		fis.close();

		FileHelper.outputToFile(featureLearningData1, builder, appended);
		if (featureLearningData2 != null) FileHelper.outputToFile(featureLearningData2, returnTypeBuilder, appended);
	}
	
	public void learnFeatures(File inputFile, String outputFileName) throws FileNotFoundException {
		FileHelper.deleteFile(outputFileName);
		SentenceEncoder encoder = new SentenceEncoder();
		int minWordFrequency = 1;
		int layerSize = 300;
		int windowSize = 2;
		encoder.encodeSentences(inputFile, minWordFrequency, layerSize, windowSize, outputFileName);
//		ParagraphVectors vec = encoder.vec;
//		for (int i = 0; i < SIZE; i ++) {
//			Map<Integer, Double> similarities = new HashMap<>();
//			for (int j = 0; j < numOfTrainingData; j ++) {
//				Double similarity = Double.valueOf(vec.similarity("SEN_" + (i + numOfTrainingData), "SEN_" + j));
//				similarities.put(j, similarity);
//			}
//			MapSorter<Integer, Double> sorter = new MapSorter<>();
//			similarities = sorter.sortByValueDescending(similarities);
//		}
	}
	
}
