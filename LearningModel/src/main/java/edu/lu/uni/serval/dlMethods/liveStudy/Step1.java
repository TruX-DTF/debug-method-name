package edu.lu.uni.serval.dlMethods.liveStudy;

import java.io.IOException;

import edu.lu.uni.serval.utils.FileHelper;

/**
 * Prepare data for deep learning of methods.
 * @author kui.liu
 *
 */
public class Step1 {
	/*
	 * Data:
	 * 2743020 methods: non-test methods, non-main methods, non-constructor, and non-empty methods, _ methods.
	 * Number of selected renamed methods:8368.
	 * Number of further selected renamed methods:1544.
	 * Number of selected methods:1864264.
	 * Number of selected methods:1864264.
	 * Number of selected methods:1677800==186464.
	 * 
	 * Number of further selected renamed methods:1544
	 * Number of selected methods:1641453
	 * Number of selected methods:1477300==164153
	 * 
	 * Number of further selected renamed methods:2805
	 * Number of selected training methods:2116413
	 * Renamed methods: 4445
	 */
	
	// TODO: to be finally confirmed.
	private static final int QUANTITY = 1;// or 500, the number of methods of which names start with the same token.
	private static final int MIN_SIZE = 0;    // The minimum size of vectors.

	public static void main(String[] args) throws IOException {
		String rootPath = args[0];//"../OUTPUT/";
		String inputPath = rootPath + "tokenization/";
		String outputPath = rootPath + args[1];//"LiveStudy/" or  "DL_Data/";
		String renamedMethodsPath = rootPath + "RenamedMethods/ActualRenamed/";
		FileHelper.deleteDirectory(outputPath);
		
		/**
		 * Prepare data:
		 * 
		 * Figure out the common first tokens of all method names.
		 * Select the methods of which method names start with these common first tokens.
		 * Training data: 90%.
		 * Testing data: 10%.
		 */
		DataInitializer dataInit = new DataInitializer();
		dataInit.QUANTITY = QUANTITY;
		dataInit.MIN_SIZE = MIN_SIZE;
		dataInit.inputPath = inputPath;
		dataInit.outputPath = outputPath;
		dataInit.renamedMethodsPath = renamedMethodsPath;
		dataInit.initializeData();
		
		// Further select renamed methods, and select data for deep learning.
		dataInit.selectMethod();
		dataInit.selectTrainingAndTestingData();
		dataInit.exportTrainingAndTestingMethodBodies();
	}

}
