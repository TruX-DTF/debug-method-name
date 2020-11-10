package edu.lu.uni.serval.dlMethods;

import java.io.IOException;

import edu.lu.uni.Configuration;
import edu.lu.uni.serval.utils.FileHelper;

/**
 * Embedding tokens and vectorize method bodies.
 * 
 * @author kui.liu
 *
 */
public class EmbedCodeTokens {

	public static void main(String[] args) throws IOException {
		String inputPath = Configuration.DL_DATA_PATH;
		String outputPath = Configuration.DL_INPUT_DATA_PATH;
		FileHelper.deleteDirectory(outputPath);
		
		TokensEmbedder embedder = new TokensEmbedder();
		embedder.inputPath = inputPath;
		embedder.outputPath = outputPath;
		embedder.mergeData(false);
		embedder.embedTokens();
		embedder.vectorizedData(true);
	}

}
