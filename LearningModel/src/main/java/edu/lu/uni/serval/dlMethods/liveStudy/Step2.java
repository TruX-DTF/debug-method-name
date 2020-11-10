package edu.lu.uni.serval.dlMethods.liveStudy;

import java.io.IOException;

import edu.lu.uni.serval.dlMethods.TokensEmbedder;
import edu.lu.uni.serval.utils.FileHelper;

/**
 * Embedding tokens and vectorize method bodies.
 * 
 * @author kui.liu
 *
 */
public class Step2 {

	public static void main(String[] args) throws IOException {
		String inputPath = args[0];//"../OUTPUT_4/DL_Data/";
		String outputPath = args[1];//"../OUTPUT_4/DL_Data/DLinput/";
		boolean mergeData = Boolean.valueOf(args[2]);
		FileHelper.deleteDirectory(outputPath);
		TokensEmbedder embedder = new TokensEmbedder();
		embedder.inputPath = inputPath;
		embedder.outputPath = outputPath;
		embedder.mergeData(mergeData);
		embedder.embedTokens();
		embedder.vectorizedData(mergeData);
	}

}
