package edu.lu.uni.serval.dlMethods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import edu.lu.uni.serval.utils.FileHelper;

/**
 * Prepare data for deep learning of methods.
 * @author kui.liu
 *
 */
public class App1 {
	private static final int QUANTITY = 1; // the number of methods of which names start with the same token.
	private static final int MIN_SIZE = 0; // The minimum size of vectors.
	
	private static final String OUTPUT_ROOT_PATH = "/work/users/kliu/DataPath/Method_Name/";
	private static final String OUTPUT_PATH1 = OUTPUT_ROOT_PATH + "tokenization/";
	private static final String OUTPUT_PATH2 = OUTPUT_ROOT_PATH + "RenamedMethods/";

	public static void main(String[] args) throws IOException {
		List<String> projects = readProjects();
		int index = Integer.valueOf(args[0]);
		if (index < 0 || index >= projects.size()) {
			System.out.println("Wrong index value.");
			return;
		}
		System.out.println(index);
		String project = projects.get(index);
		System.out.println(project);
		
		
		String inputPath = OUTPUT_PATH1 + project;
		String outputPath = OUTPUT_ROOT_PATH + "DL_Data1/" + project;
		String renamedMethodsPath = OUTPUT_PATH2 + project + "/ActualRenamed/";
		FileHelper.deleteDirectory(outputPath);
		
		// method_bodies.txt sizes.csv tokens.txt ParsedMethodNames.txt
		/**
		 * Prepare data:
		 * 
		 * Figure out the common first tokens of all method names.
		 * Select the methods of which method names start with these common first tokens.
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
//		dataInit.selectTrainingAndTestingData();
//		dataInit.exportTrainingAndTestingMethodBodies();
	}

	public static List<String> readProjects() throws IOException {
		List<String> projects = new ArrayList<>();
		String content = FileHelper.readFile("repos.txt");
		BufferedReader reader = new BufferedReader(new StringReader(content));
		String line = null;
		while ((line = reader.readLine()) != null) {
			projects.add(line);
		}
		reader.close();
		return projects;
	}

}
