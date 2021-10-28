package edu.lu.uni.serval;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import edu.lu.uni.serval.akka.method.parser.MultipleShreadParser;
import edu.lu.uni.serval.method.parser.MethodParser;
import edu.lu.uni.serval.utils.FileHelper;

/**
 * Examples of the tool: parse methods.
 * 
 * @author kui.liu
 *
 */
public class MainParser {
	
	public static void main(String[] args) {
		MainParser parser = new MainParser();
		
		try {
			/*
			 * Parse Java code methods with multiple threads.
			 * The input is all obtained Java code files of one Java project.
			 * It needs to merge all output data if using this choice.
			 * 
			 */
			List<String> projectNames = readProjects();
			int i = Integer.valueOf(args[0]); // 0 - 429: 430 Java projects.
			if (i >= projectNames.size()) {
				System.out.println("Wrong parameter: " + args[0]);
				return;
			}
			String projectName = projectNames.get(i);
			String project = Configuration.JAVA_FILES_PATH + projectName + ".txt";
			if (! new File(project).exists()) {
				project = Configuration.JAVA_REPOS_PATH + projectName;
			}
			parser.parseMethodsWithMultipleThreads(project, projectName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Parse method bodies: tokenize and vectorize method bodies.
	 * 
	 * @throws IOException
	 */
	public void parseMethodsWithSingleThread() throws IOException {
		List<String> projects = Configuration.PROJECTS;

		String outputPath = Configuration.TOKENIZED_METHODS_PATH;
		// Clear existing output data generated at the last time.
		FileHelper.deleteDirectory(outputPath);

		MethodParser mp = new MethodParser();
		mp.parseProjects(projects, outputPath);
	}
	
	/**
	 * Parse method bodies with multiple threads.
	 * 
	 * One thread is used to parse one project.
	 * 
	 * @throws IOException
	 */
	public void parseMethodsWithMultipleThreads() throws IOException {
		List<String> projects = Configuration.PROJECTS;

		String outputPath = Configuration.TOKENIZED_METHODS_PATH;
		// Clear existing output data generated at the last time.
		FileHelper.deleteDirectory(outputPath);

		int numberOfWorkers = 430;
		MultipleShreadParser parser = new MultipleShreadParser(projects, outputPath, numberOfWorkers);
		parser.parseMethods();
	}
	
	/**
	 * Parse method bodies with multiple threads.
	 * @throws IOException
	 */
	public void parseMethodsWithMultipleThreads(String allJavaFilesFile) throws IOException {
		String outputPath = Configuration.TOKENIZED_METHODS_PATH;
		int numberOfWorkers = 1000;
		MultipleShreadParser parser = new MultipleShreadParser(allJavaFilesFile, outputPath, numberOfWorkers);
		parser.parseMethods();
	}
	
	/**
	 * Parse method bodies with multiple threads.
	 * 
	 * @throws IOException
	 */
	public void parseMethodsWithMultipleThreads(String project, String projectName) throws IOException {
		String outputPath = Configuration.TOKENIZED_METHODS_PATH + projectName + "/";
		int numberOfWorkers = 1000;
		MultipleShreadParser parser = new MultipleShreadParser(project, outputPath, numberOfWorkers);
		parser.parseMethods();
	}
	
	private static List<String> readProjects() throws IOException {
		String content = FileHelper.readFile(Configuration.JAVA_REPO_NAMES_FILE);
		List<String> list = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new StringReader(content));
		try {
			String line = null;
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
