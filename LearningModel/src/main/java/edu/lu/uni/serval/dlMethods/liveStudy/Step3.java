package edu.lu.uni.serval.dlMethods.liveStudy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.lu.uni.Configuration;
import edu.lu.uni.deeplearning.extractor.CNNFeatureExtractor;
import edu.lu.uni.serval.utils.FileHelper;

/**
 * Feature learning.
 * 
 * @author kui.liu
 *
 */
public class Step3 {

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		String rootPath = args[0];//"../OUTPUT_3/DL_Data/";
		String inputPath = rootPath + "DLinput/";
		File[] files = new File(inputPath).listFiles();
		File trainingDataFile = null;
		File testingDataFile = null;
//		File renamedDataFile = null;
		int maxSize = 0;
		
		for (File file : files) {
			String fileName = file.getName();
			if (fileName.startsWith("TrainingData_")) {
				trainingDataFile = file;
				maxSize = Integer.parseInt(fileName.substring("TrainingData_Tokens_MaxSize=".length(), fileName.lastIndexOf(".csv")));
			} else if (fileName.startsWith("TestingData_")) {
				testingDataFile = file;
			}
		}

		int nEpochs = Integer.valueOf(args[1]);//1, 10, 20
		String outputPath = rootPath + "DLoutput_" + nEpochs + "/";
		FileHelper.deleteDirectory(outputPath);
		
		int sizeOfTokensVector = maxSize;
		int sizeOfEmbeddedVector = 300;
		int batchSize = Configuration.BATCH_SIZE;
		int sizeOfFeatureVector = Configuration.SIZE_OF_FEATURE_VECTOR;
		
		
		CNNFeatureExtractor learner = new CNNFeatureExtractor(trainingDataFile, sizeOfTokensVector, sizeOfEmbeddedVector, batchSize, sizeOfFeatureVector);
		learner.setNumberOfEpochs(nEpochs);
		learner.setSeed(123);
		learner.setNumOfOutOfLayer1(20);
		learner.setNumOfOutOfLayer2(50);
		learner.setOutputPath(outputPath);
		
		learner.extracteFeaturesWithCNN();
		
		File modelFile = new File(outputPath + "CNNoutput.zip");
		learner.setModelFile(modelFile);
		learner.setTestingData(testingDataFile);
		learner.extracteFeaturesWithCNNByLoadingModel();
	}

}
