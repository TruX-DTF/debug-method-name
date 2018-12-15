package edu.lu.uni;

public class Configuration {
	private static final String OUTPUT_PATH = "../Output/";
	
	public static final String ENCODED_METHOD_BODY_FILE_PATH = OUTPUT_PATH + "encoding/encoded_method_bodies.txt";  // output
	
	// token embedding with word2vec
	public static final int SIZE_OF_EMBEDDED_VECTOR = 300;
	public static final String EMBEDDED_DATA_FILE_PATH = OUTPUT_PATH + "data_for_CNN/vectorized_tokens.csv";
		
	
	/**
	 * Configuration of the third step: extract features of method bodies by deep learning with the CNN algorithm.
	 */
	public static final int BATCH_SIZE = 1024;
	public static final int SIZE_OF_FEATURE_VECTOR = 300; // size of extracted feature vectors
	public static final String DATA_APPZENDED_ZERO = OUTPUT_PATH + "data_for_CNN/append_zero/";        // file path of output
	public static final String DATA_STANDARDIZED = OUTPUT_PATH + "data_for_CNN/standardized_data/";    // file path of output
	public static final String DATA_EXTRACTED_FEATURE = OUTPUT_PATH + "CNN_extracted_feature/";        // file path of output
	public static final String DATA_EXTRACTED_FEATURE_BY_PROJECTS = OUTPUT_PATH + "extracted_features_by_project/";
	public static final int N_EPOCHS = 10;
	
}
