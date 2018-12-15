package edu.lu.uni.feature.exporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.lu.uni.serval.utils.FileHelper;

public class FeatureExporter {
	
	private File featureFile;
	private File methodInfoFile;
	private String outputPath;
	private String fileExtension;
	private List<String> methodsInfo;
	
	public FeatureExporter(File featureFile, String methodInfoFiles, String outputPath, String fileExtension) {
		super();
		this.featureFile = featureFile;
		this.methodInfoFile = new File(methodInfoFiles);//matchMethodInfoFile(featureFile, methodInfoFiles);
		this.outputPath = outputPath;
		this.fileExtension = fileExtension;
		methodsInfo = new ArrayList<>();
	}

	@SuppressWarnings("unused")
	private File matchMethodInfoFile(File featureFile, List<File> methodInfoFiles) {
		String fileName = featureFile.getName();
		fileName = fileName.substring(0, fileName.indexOf("."));
		
		for (File methodInfoFile : methodInfoFiles) {
			String name = methodInfoFile.getName();
			name = name.substring(0, name.indexOf("."));
			if (fileName.endsWith(name)) {
				return methodInfoFile;
			}
		}
		return null;
	}

	public void exportFeatureByProjects() {
		readProjects();
		
		String subPath = featureFile.getName();
		subPath = subPath.substring(0, subPath.indexOf(".")) + "/";
		outputPath += subPath;
		
		FileInputStream fis = null;
		Scanner scanner = null;
		
		try {
			fis = new FileInputStream(featureFile);
			scanner = new Scanner(fis);
			
			int index = 0;
			StringBuilder features = new StringBuilder();
			String previousProject = "";
			String currentProject = "";
			
			while (scanner.hasNextLine()) {
				String methodInfo = methodsInfo.get(index);
				currentProject = methodInfo.substring(0, methodInfo.indexOf(":"));
				if (index == 0) {
					previousProject = currentProject;
				} else if (!currentProject.equals(previousProject)) {
					FileHelper.outputToFile(outputPath + previousProject + fileExtension, features, false);
					features.setLength(0);
					previousProject = currentProject;
				}

				String feature = scanner.nextLine();
				features.append(methodInfo + feature + "\n");
				index ++;
			}
			
			FileHelper.outputToFile(outputPath + previousProject + fileExtension, features, false);
			features.setLength(0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (scanner != null) {
					scanner.close();
					scanner = null;
				}
				
				if (fis != null) {
					fis.close();
					fis = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void readProjects() {
		FileInputStream fis = null;
		Scanner scanner = null;
		
		try {
			fis = new FileInputStream(methodInfoFile);
			scanner = new Scanner(fis);
			
			while (scanner.hasNextLine()) {
				String methodInfo = scanner.nextLine();
				methodInfo = methodInfo.substring(0, methodInfo.indexOf("#") + 1);
				methodsInfo.add(methodInfo);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (scanner != null) {
					scanner.close();
					scanner = null;
				}
				
				if (fis != null) {
					fis.close();
					fis = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
