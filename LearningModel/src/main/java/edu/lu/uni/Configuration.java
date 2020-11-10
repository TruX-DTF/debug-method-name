package edu.lu.uni;

public class Configuration {
	public static final String ROOT_PATH = "../Data/Output/";

	public static final String TOKENIZED_METHODS_PATH = ROOT_PATH + "tokenization/";
	public static final String RENAMED_METHODS_PATH = ROOT_PATH + "RenamedMethods/";
	public static final String JAVA_REPO_NAMES_FILE = "../Data/JavaRepos/repos.txt";
	
	public static final String DL_DATA_PATH = ROOT_PATH + "DL_Data/";
	public static final String SELECTED_DATA_PATH = DL_DATA_PATH + "SelectedData/";
	public static final String SELECTED_RENAMED_DATA_PATH = DL_DATA_PATH + "RenamedMethods/";

	public static final String DL_INPUT_DATA_PATH = DL_DATA_PATH + "DLinput/";
	
	public static final String ENCODED_METHOD_BODY_FILE_PATH = DL_DATA_PATH + "encoding/encoded_method_bodies.txt";  // output
	
	// token embedding with word2vec
	public static final int SIZE_OF_EMBEDDED_VECTOR = 300;
	public static final String EMBEDDED_DATA_FILE_PATH = DL_DATA_PATH + "data_for_CNN/vectorized_tokens.csv";
		
	
	/**
	 * Configuration of the third step: extract features of method bodies by deep learning with the CNN algorithm.
	 */
	public static final int BATCH_SIZE = 1024;
	public static final int SIZE_OF_FEATURE_VECTOR = 300; // size of extracted feature vectors
	public static final String DATA_APPZENDED_ZERO = DL_DATA_PATH + "data_for_CNN/append_zero/";        // file path of output
	public static final String DATA_STANDARDIZED = DL_DATA_PATH + "data_for_CNN/standardized_data/";    // file path of output
	public static final String DATA_EXTRACTED_FEATURE = DL_DATA_PATH + "CNN_extracted_feature/";        // file path of output
	public static final String DATA_EXTRACTED_FEATURE_BY_PROJECTS = DL_DATA_PATH + "extracted_features_by_project/";
	public static final int N_EPOCHS = 10;
	
	public static final String EVALUATION_DATA_PATH = ROOT_PATH + "Eva/";

}
