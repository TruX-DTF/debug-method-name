package edu.lu.uni.serval.dlMethods;

import java.io.File;
import java.io.IOException;
import java.util.List;

import edu.lu.uni.serval.utils.FileHelper;

public class App {
	
	private static final String OUTPUT_ROOT_PATH = "/work/users/kliu/DataPath/Method_Name/";
	private static final String OUTPUT_PATH1 = OUTPUT_ROOT_PATH + "DL_Data1/";
	private static final String OUTPUT_PATH2 = OUTPUT_ROOT_PATH + "DL_Data/";

	/**
	 * Merge data.
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		List<String> projects = App1.readProjects();
		String methodTokensPath = OUTPUT_PATH2 + "SelectedData/SelectedMethodTokens.txt";
		String methodInfoPath = OUTPUT_PATH2 + "SelectedData/SelectedMethodInfo.txt";
		String renamedMethodTokensPath = OUTPUT_PATH2 + "RenamedMethods/MethodTokens.txt";
		String renamedMethodInfoPath = OUTPUT_PATH2 + "RenamedMethods/MethodInfo.txt";
		String renamedMethodNamesPath = OUTPUT_PATH2 + "RenamedMethods/ParsedMethodNames.txt";
		new File(methodTokensPath).delete();
		new File(methodInfoPath).delete();
		new File(renamedMethodTokensPath).delete();
		new File(renamedMethodInfoPath).delete();
		new File(renamedMethodNamesPath).delete();
		
		int i = 0;
		for (String project : projects) {
			i ++;
			/*
			 *  /SelectedData/
			 *  	SelectedMethodTokens.txt
			 *  	SelectedMethodInfo.txt
			 *  			method_bodies.txt
			 *  /RenamedMethods/
			 *  	MethodTokens.txt"
			 *  	MethodInfo.txt"
			 *  	ParsedMethodNames.txt
			 *  			method_bodies/TestingMethods.java
			 */
			String dataPath = OUTPUT_PATH1 + project;
			FileHelper.outputToFile(methodTokensPath, FileHelper.readFile(dataPath + "SelectedData/SelectedMethodTokens.txt"), true);
			FileHelper.outputToFile(methodInfoPath, FileHelper.readFile(dataPath + "SelectedData/SelectedMethodInfo.txt"), true);
			FileHelper.outputToFile(renamedMethodTokensPath, FileHelper.readFile(dataPath + "RenamedMethods/MethodTokens.txt"), true);
			FileHelper.outputToFile(renamedMethodInfoPath, FileHelper.readFile(dataPath + "RenamedMethods/MethodInfo.txt"), true);
			FileHelper.outputToFile(renamedMethodNamesPath, FileHelper.readFile(dataPath + "RenamedMethods/ParsedMethodNames.txt"), true);
			
			FileHelper.outputToFile(OUTPUT_ROOT_PATH + "CompStudyData/TestSource/TestingMethods" + i + ".java", FileHelper.readFile(dataPath + "RenamedMethods/method_bodies/TestingMethods.java"), false);
			FileHelper.outputToFile(OUTPUT_ROOT_PATH + "CompStudyData/TrainingSource/TrainingMethods" + i + ".java", "public class TrainingMethods" + i + " {\n" + FileHelper.readFile(dataPath + "SelectedData/method_bodies.txt") + "}", false);
		
//			FileHelper.deleteDirectory(dataPath);
		}
	}

}
