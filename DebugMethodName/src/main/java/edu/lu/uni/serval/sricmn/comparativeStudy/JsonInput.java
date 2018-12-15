package edu.lu.uni.serval.sricmn.comparativeStudy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.lu.uni.serval.utils.FileHelper;

public class JsonInput {

	public static void main(String[] args) {
		JsonInput ji = new JsonInput();
//		String methodIndexesFile1 = "../OUTPUT_1/DL_Data/SelectedData/SelectedMethodInfo.txt";
//		String methodBodyFile = "../OUTPUT_1/tokenization/method_bodies.txt";
//		String outputPath1 = "../OUTPUT_1/DL_Data/json/TrainingData1/";
//		String methodIndexesFile2 = "../OUTPUT_1/DL_Data/TrainingData/MethodsInfo.txt";
//		String outputPath2 = "../OUTPUT_1/DL_Data/json/TrainingData2/";
//		String methodIndexesFile3 = "../OUTPUT_1/DL_Data/TestingData/MethodsInfo.txt";
//		String outputPath3 = "../OUTPUT_1/DL_Data/json/TestingData2/";
//		ji.mergerData("../OUTPUT_1/DL_Data/json/T/", 372, "trainingData1/trainingData.json");
//		ji.mergerData("../OUTPUT_1/DL_Data/json/T2/", 335, "trainingData2/trainingData.json");
//		ji.mergerData("../OUTPUT_1/DL_Data/json/Test2/", 37, "testingData2/testingData.json");
		
		ji.mergerData("../OUTPUT_4/DL_Data/json/T/", "../OUTPUT_4/DL_Data/json/trainingData/trainingData.json");
	}

	private void mergerData(String inputPath, String outputFileName) {
		List<File> files = FileHelper.getAllFiles(inputPath, ".json");
		String fileContent = FileHelper.readFile(files.get(0));
		FileHelper.outputToFile(outputFileName, fileContent.substring(0, fileContent.lastIndexOf("]")) + ",", false);
		
		for (int index = 1, size = files.size() - 1; index < size; index ++) {
			fileContent = FileHelper.readFile(files.get(index));
			FileHelper.outputToFile(outputFileName, fileContent.substring(1, fileContent.lastIndexOf("]")) + ",", true);
		}
		
		fileContent = FileHelper.readFile(files.get(files.size() - 1));
		FileHelper.outputToFile(outputFileName, fileContent.substring(1), true);
	}

	@SuppressWarnings("unused")
	private void mergerData(String path, int a, String fileName) {
		String fileContent = FileHelper.readFile(path + "trainingData_0.json");
		FileHelper.outputToFile(fileName, fileContent.substring(0, fileContent.lastIndexOf("]")) + ",", false);
		
		for (int i = 1; i < a; i ++) {
			fileContent = FileHelper.readFile(path + "trainingData_" + i + ".json");
			FileHelper.outputToFile(fileName, fileContent.substring(1, fileContent.lastIndexOf("]")) + ",", true);
		}
		
		fileContent = FileHelper.readFile(path + "trainingData_" + a + ".json");
		FileHelper.outputToFile(fileName, fileContent.substring(1), true);
	}

	@SuppressWarnings("unused")
	private void renameFiles(String outputPath) {
		File[] projects = new File(outputPath).listFiles();
		for (File pro :projects) {
			File javaFile = new File(pro.getPath() + "/Methods.java.");
			javaFile.renameTo(new File(pro.getPath() + "/Methods.java"));
		}
	}

	@SuppressWarnings("unused")
	private void prepareData(String methodIndexesFile, String methodBodyFile, String outputPath) {
		List<Integer> methodIndexes = readIndexes(methodIndexesFile);
		String projectName = outputPath + "T";
		int numOfPro = 0;
		try {
			StringBuilder singleMethod = new StringBuilder();
			StringBuilder methods = new StringBuilder();
			
			FileInputStream fis = new FileInputStream(methodBodyFile);
			Scanner scanner = new Scanner(fis);
			int index = -1;
			int counter = 0;
			boolean isMethodBody = false;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if ("#METHOD_BODY#========================".equals(line)) {
					if (isMethodBody) {
						if (methodIndexes.contains(index)) {
							methods.append(singleMethod).append("\n");
							counter ++;
							if (counter % 5000 == 0) {
								FileHelper.outputToFile(projectName + numOfPro + "/Methods.java", "public class Methods {\n", false);
								FileHelper.outputToFile(projectName + numOfPro + "/Methods.java", methods.append("}"), true);
								methods.setLength(0);
								numOfPro ++;
							}
						}
						singleMethod.setLength(0);
					}
					isMethodBody = false;
					index ++;
				} else {
					if (isMethodBody) {
						singleMethod.append(line).append("\n");
					} else isMethodBody = true;
				}
			}
			if (methodIndexes.contains(index)) {
				methods.append(singleMethod).append("\n");
				singleMethod.setLength(0);
			}
			
			FileHelper.outputToFile(projectName + numOfPro + "/Methods.java", "public class Methods {\n", false);
			FileHelper.outputToFile(projectName + numOfPro + "/Methods.java", methods.append("}"), true);
			methods.setLength(0);
			scanner.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<Integer> readIndexes(String methodIndexesFile) {
		List<Integer> methodIndexes = new ArrayList<>();
		try {
			FileInputStream fis = new FileInputStream(methodIndexesFile);
			Scanner scanner = new Scanner(fis);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				Integer index = Integer.valueOf(line.substring(0, line.indexOf("@")));
				methodIndexes.add(index);
			}
			scanner.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return methodIndexes;
	}

}
