package edu.lu.uni.serval.dlMethods;

import java.io.File;
import java.io.IOException;

import edu.lu.uni.Configuration;
import edu.lu.uni.serval.MethodName.detector.MethodNameFeatureLearner;

/**
 * Learn features of method names with ParagraphVectors.
 * 
 * @author kui.liu
 *
 */
public class MethodNameLearner {
	
	public static void main(String[] args) throws IOException {
		MethodNameFeatureLearner learner = new MethodNameFeatureLearner();
		String testingData = Configuration.SELECTED_RENAMED_DATA_PATH + "ParsedMethodNames.txt";
		String outputPath = Configuration.EVALUATION_DATA_PATH;
		
		// Selecting data for method name feature learning.
		String testingMethodNamesFile = outputPath + "TestingMethodNames.txt";
		learner.prepareData(testingData, testingMethodNamesFile, outputPath + "TestingLabels.txt");
		
		String trainingData = Configuration.SELECTED_DATA_PATH  + "SelectedMethodInfo.txt";
		String featureLearningData1 = outputPath + "FeatureLearningData1.txt"; // without return type.
		String featureLearningData2 = outputPath + "FeatureLearningData2.txt"; // with return type.
		String returnTypeOfTestingFile = Configuration.SELECTED_RENAMED_DATA_PATH + "MethodInfo.txt";
		
		learner.prepareFeatureLearningData(trainingData, testingMethodNamesFile, featureLearningData1, featureLearningData2, returnTypeOfTestingFile);
		
		learner.learnFeatures(new File(featureLearningData1), outputPath + "MethodNameFeatures_1_Size=" + learner.SIZE + ".txt");
		learner.learnFeatures(new File(featureLearningData2), outputPath + "MethodNameFeatures_2_Size=" + learner.SIZE + ".txt");
		learner.learnFeatures(new File(featureLearningData1 + ".bak"), outputPath + "MethodNameFeatures_1_Size=" + learner.SIZE + ".txt.bak");
		learner.learnFeatures(new File(featureLearningData2 + ".bak"), outputPath + "MethodNameFeatures_2_Size=" + learner.SIZE + ".txt.bak");
	}
	
}
