package edu.lu.uni.serval.dlMethods.liveStudy;

import java.io.IOException;

import edu.lu.uni.Configuration;
import edu.lu.uni.serval.utils.FileHelper;

/**
 * Prepare data for deep learning of methods.
 * @author kui.liu
 *
 */
public class Step1 {

	private static final int QUANTITY = 1;// or 500, the number of methods of which names start with the same token.
	private static final int MIN_SIZE = 0;// The minimum size of vectors.

	public static void main(String[] args) throws IOException {
		String inputPath = Configuration.TOKENIZED_METHODS_PATH;
		String outputPath = Configuration.ROOT_PATH + "LiveStudy/";
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
		dataInit.selectTrainingAndTestingData();
		dataInit.exportTrainingAndTestingMethodBodies();
	}

}
