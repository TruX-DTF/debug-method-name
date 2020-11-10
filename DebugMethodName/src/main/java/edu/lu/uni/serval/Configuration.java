package edu.lu.uni.serval;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Configuration {
	
	private static final String ROOT_PATH = "../Data/";
	// Data paths of Java method parsing.
	public static final String JAVA_REPOS_PATH = ROOT_PATH + "JavaRepos/";
	public static final String JAVA_REPO_NAMES_FILE = JAVA_REPOS_PATH + "repos.txt";
	public static final List<String> PROJECTS = new ArrayList<>();  // input
	static {
		File trainingDataFile = new File(JAVA_REPOS_PATH);
		File[] files = trainingDataFile.listFiles();
		for (File file : files) {
			if (file.isDirectory() && !file.getName().startsWith(".")) {
				PROJECTS.add(JAVA_REPOS_PATH + file.getName() + "/.git");
			}
		}
	}
	public static final String OUTPUT_PATH = ROOT_PATH + "Output/";
	public static final String TOKENIZED_METHODS_PATH = OUTPUT_PATH + "tokenization/";
	public static final String JAVA_FILES_PATH = OUTPUT_PATH + "JavaFiles/";
	public static final String JAVA_FILES_FILE = OUTPUT_PATH + "JavaFiles.txt";
	
	public static final String WORDNET_PATH = "/usr/local/Cellar/wordnet/3.1/dict";
	public static final String DL_DATA_PATH = OUTPUT_PATH + "DL_Data/";
	
}
