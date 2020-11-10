package edu.lu.uni.serval.dlMethods;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import edu.lu.uni.Configuration;
import edu.lu.uni.serval.utils.FileHelper;

public class DataMerger {
	/**
	 * Merge data.
	 */
	public static void merge() {
		List<String> projects = DataPreparer.readProjects();
		String methodTokensPath = Configuration.SELECTED_DATA_PATH + "SelectedMethodTokens.txt";
		String methodInfoPath = Configuration.SELECTED_DATA_PATH  + "SelectedMethodInfo.txt";
		String renamedMethodTokensPath = Configuration.SELECTED_RENAMED_DATA_PATH + "MethodTokens.txt";
		String renamedMethodInfoPath = Configuration.SELECTED_RENAMED_DATA_PATH + "MethodInfo.txt";
		String renamedMethodNamesPath = Configuration.SELECTED_RENAMED_DATA_PATH + "ParsedMethodNames.txt";
		new File(methodTokensPath).delete();
		new File(methodInfoPath).delete();
		new File(renamedMethodTokensPath).delete();
		new File(renamedMethodInfoPath).delete();
		new File(renamedMethodNamesPath).delete();
		
//		int i = 0;
		for (String project : projects) {
//			i ++;
			/*
			 *  /SelectedData/
			 *  	SelectedMethodTokens.txt
			 *  	SelectedMethodInfo.txt
			 *  	method_bodies.txt
			 *  /RenamedMethods/
			 *  	MethodTokens.txt"
			 *  	MethodInfo.txt"
			 *  	ParsedMethodNames.txt
			 *  	method_bodies/TestingMethods.java
			 */
			String dataPath1 = Configuration.SELECTED_DATA_PATH + project;
			String dataPath2 = Configuration.SELECTED_RENAMED_DATA_PATH + project;
			FileHelper.outputToFile(methodTokensPath, FileHelper.readFile(dataPath1 + "/SelectedMethodTokens.txt"), true);
			FileHelper.outputToFile(methodInfoPath, FileHelper.readFile(dataPath1 + "/SelectedMethodInfo.txt"), true);
			FileHelper.outputToFile(renamedMethodTokensPath, FileHelper.readFile(dataPath2 + "/MethodTokens.txt"), true);
			FileHelper.outputToFile(renamedMethodInfoPath, FileHelper.readFile(dataPath2 + "/MethodInfo.txt"), true);
			FileHelper.outputToFile(renamedMethodNamesPath, FileHelper.readFile(dataPath2 + "/ParsedMethodNames.txt"), true);
			
//			FileHelper.outputToFile(Configuration.ROOT_PATH + "CompStudyData/TestSource/TestingMethods" + i + ".java", FileHelper.readFile(dataPath2 + "/method_bodies/TestingMethods.java"), false);
//			FileHelper.outputToFile(Configuration.ROOT_PATH + "CompStudyData/TrainingSource/TrainingMethods" + i + ".java", "public class TrainingMethods" + i + " {\n" + FileHelper.readFile(dataPath1 + "/method_bodies.txt") + "}", false);

//			FileHelper.deleteDirectory(dataPath1);
//			FileHelper.deleteDirectory(dataPath2);
		}
		
		File[] files = new File(Configuration.SELECTED_DATA_PATH + "TrainingData/").listFiles();
		File trainingDataFile = null;
		for (File file : files) {
			String fileName = file.getName();
			if (fileName.startsWith("Tokens_MaxSize=")) {
				trainingDataFile = file;
			}
		}
		try {
			FileUtils.copyFile(new File(methodTokensPath), trainingDataFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
