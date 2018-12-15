package edu.lu.uni.serval.dlMethods.liveStudy;

import java.io.File;
import java.io.IOException;

import edu.lu.uni.serval.MethodName.detector.MethodNameFeatureLearner;

/**
 * Learn features of method names with ParagraphVectors.
 * 
 * @author kui.liu
 *
 */
public class Step4 {
	
	public static void main(String[] args) throws IOException {
		MethodNameFeatureLearner learner = new MethodNameFeatureLearner();
		String inputPath = args[0];  // "../OUTPUT_4/LiveStudy/";
		String outputPath = args[1]; // "../OUTPUT_4/LiveStudy/NameFeatures/";
		String trainingData = inputPath + "TrainingData/MethodsInfo.txt";
		String testingData = inputPath + "TestingData/MethodsInfo.txt";
		String featureLearningData1 = outputPath + "FeatureLearningData1.txt"; // without return type.
		String featureLearningData2 = outputPath + "FeatureLearningData2.txt"; // with return type.
		learner.prepareFeatureLearningData(trainingData, featureLearningData1, featureLearningData2, false);
		learner.prepareFeatureLearningData(testingData, featureLearningData1, featureLearningData2, true);
		learner.learnFeatures(new File(featureLearningData1), outputPath + "MethodNameFeatures_1_Size=" + learner.SIZE + ".txt");
		learner.learnFeatures(new File(featureLearningData2), outputPath + "MethodNameFeatures_2_Size=" + learner.SIZE + ".txt");
	}
	
}
