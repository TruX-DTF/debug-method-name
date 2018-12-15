package edu.lu.uni.serval.method.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.lu.uni.serval.jdt.method.Method;
import edu.lu.uni.serval.jdt.parser.JavaFileParser;
import edu.lu.uni.serval.method.parser.util.MethodExporter;
import edu.lu.uni.serval.utils.FileHelper;

/**
 * Parse methods of Java source code.
 * 
 * @author kui.liu
 *
 */
public class MethodParser {
	
	private static Logger logger = LoggerFactory.getLogger(MethodParser.class);
	private String projectName = "";
	
	public void parseProjects(List<String> projects, String outputFilePath) throws IOException {

		logger.debug("****************Start to parse methods****************");
		int counter1 = 0; // totality of all methods.
		int counter2 = 0; // totality of all non-empty methods.
		
		MethodExporter exporter = new MethodExporter(outputFilePath);
		
		for (String project : projects) {
			List<Method> methods = parseMethods(project);
			counter1 += methods.size();
			// output methods, and clear methods list
			counter2 += exporter.outputMethods(methods);
			methods.clear();
		}
		
		logger.debug("****************Totality of all methods: " + counter1);
		logger.debug("****************Totality of all non-empty methods: " + counter2);
		logger.debug("****************Finish off parsing methods****************\n");
	}
	
	public List<Method> parseMethods(String project) throws IOException {
		List<File> javaFiles = getAllJavaFiles(project);
		
		List<Method> methods = new ArrayList<Method>();
		if (javaFiles.size() == 0) {
			logger.error("*******************There is no .java file in the project:" + project);
		} else {
			for (File file : javaFiles) {
				methods.addAll(parseMethods(file, project));
			}
		}
		
		return methods;
	}

	public void parseMethods(String projectPath, String outputPath) throws IOException {
		List<File> files = getAllJavaFiles(outputPath);
		
		if (files.size() == 0) {
			logger.error("*******************There is no .java file in the project:" + projectName);
		} else {
			List<Method> methods = new ArrayList<Method>();
			int counter1 = 0; // totality of all methods.
			int counter2 = 0; // totality of all non-empty methods.
			
			MethodExporter exporter = new MethodExporter(outputPath);
			
			for (File file : files) {
				methods.addAll(parseMethods(file, projectPath));
				if (methods.size() > 10000) {
					counter1 += methods.size();
					// output methods, and clear methods list.
					counter2 += exporter.outputMethods(methods);
					methods.clear();
				}
			}
			counter1 += methods.size();
			// output methods, and clear methods list.
			counter2 += exporter.outputMethods(methods);
			methods.clear();
			
			logger.debug("Totality of all methods: " + counter1 + " in project " + projectName);
			logger.debug("Totality of all non-empty methods: " + counter2 + " in project " + projectName);
		}
	}

	private List<File> getAllJavaFiles(String project) {
		List<File> javaFiles = new ArrayList<>();
		
		if (project.endsWith(".git")) {
			javaFiles.addAll(FileHelper.getAllFiles(project.substring(0, project.lastIndexOf(".git")), ".java"));
			projectName = new File(project).getParentFile().getName();
		} else {
			javaFiles.addAll(FileHelper.getAllFiles(project, ".java"));
			projectName = new File(project).getName();
		}
		return javaFiles;
	}

	private List<Method> parseMethods(File file, String projectName) {
		if (isTestOrSampleJavaFile(file.getName().toLowerCase(Locale.ROOT))) return new ArrayList<>();
		
		String filePath = file.getPath();
		int index = filePath.indexOf(projectName);
		if (index < 0) index = projectName.length();
		else index += projectName.length();
		filePath = filePath.substring(index).toLowerCase(Locale.ROOT);
		if (isTestOrSampleJavaFile(filePath)) return new ArrayList<>();
		
		JavaFileParser jfp = new JavaFileParser();
		jfp.parseJavaFile(projectName, file);
		List<Method> methods= jfp.getMethods();
		if (methods != null && methods.size() > 0) {
			return methods;
		}
		return new ArrayList<>();
	}
	
	private boolean isTestOrSampleJavaFile(String filePath) {
		if (filePath.contains("test") || filePath.contains("sample") 
				|| filePath.contains("example") || filePath.contains("template"))
			return true;
		return false;
	}

}
