package edu.lu.uni.serval.method.parser.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.lu.uni.serval.Configuration;
import edu.lu.uni.serval.utils.FileHelper;

public class MethodBodiesExporter {

	public static void main(String[] args) throws IOException {
		MethodBodiesExporter m = new MethodBodiesExporter();
		m.exportTrainingAndTestingMethodBodies(Configuration.OUTPUT_PATH, Configuration.DL_DATA_PATH);
	}

	public void exportTrainingAndTestingMethodBodies(String inputPath, String outputPath) throws IOException {
		String trainingDataPath = outputPath + "TrainingData/method_bodies/";
		String testingDataPath = outputPath + "TestingData/method_bodies/";
		String selectedMethodsPath = outputPath + "SelectedData/method_bodies/";
		
		List<Integer> trainingMethodIndexes = readIndexes(inputPath + "DL_Data/TrainingData/MethodsInfo.txt");
		List<Integer> testingMethodIndexes = readIndexes(inputPath + "DL_Data/TestingData/MethodsInfo.txt");
		
		String methodBodyFile = inputPath + "tokenization/method_bodies.txt";
		FileInputStream fis = new FileInputStream(methodBodyFile);
		Scanner scanner = new Scanner(fis);
		StringBuilder singleMethod = new StringBuilder();
		StringBuilder trainingMethods = new StringBuilder();
		StringBuilder testingMethods = new StringBuilder();
		StringBuilder selectedMethods = new StringBuilder();
		int index = -1;
		boolean isMethodBody = false;
		String methodFile1 = "";
		String methodFile2 = "";
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if ("#METHOD_BODY#========================".equals(line)) {
				if (isMethodBody) {
					if (trainingMethodIndexes.contains(index)) {
						trainingMethods.append(singleMethod).append("\n");
						selectedMethods.append(singleMethod).append("\n");
					} else if (testingMethodIndexes.contains(index)) {
						testingMethods.append(singleMethod).append("\n");
						selectedMethods.append(singleMethod).append("\n");
					}
					singleMethod.setLength(0);
				}
				isMethodBody = false;
				index ++;
			} else {
				if (isMethodBody) {
					singleMethod.append(line).append("\n");
				} else {
//					hibernate-metamodelgen:org.hibernate.jpamodelgen.util:StringUtil:
					String[] elements = line.split(":");
					methodFile2 = elements[0] + "/" + elements[1].replace(".", "") + elements[2];
					if (methodFile1.equals("")) methodFile1 = methodFile2;
					else if (!methodFile1.equals(methodFile2)) { // output to file.
						if (trainingMethods.length() > 0) FileHelper.outputToFile(trainingDataPath + methodFile1 + ".java", "public class MethodBody {\n" + trainingMethods.toString() + "}", true);
						if (testingMethods.length() > 0) FileHelper.outputToFile(testingDataPath + methodFile1 + ".java", "public class MethodBody {\n" + testingDataPath.toString() + "}", true);
						FileHelper.outputToFile(selectedMethodsPath + methodFile1 + ".java", "public class MethodBody {\n" + selectedMethods.toString() + "}", true);
						trainingMethods.setLength(0);
						testingMethods.setLength(0);
						selectedMethods.setLength(0);
						methodFile1 = methodFile2;
					}
					isMethodBody = true;
				}
			}
		}
		if (trainingMethodIndexes.contains(index)) {
			trainingMethods.append(singleMethod).append("\n");
			selectedMethods.append(singleMethod).append("\n");
		} else if (testingMethodIndexes.contains(index)) {
			testingMethods.append(singleMethod).append("\n");
			selectedMethods.append(singleMethod).append("\n");
		}
		scanner.close();
		fis.close();
		
		FileHelper.outputToFile(trainingDataPath + methodFile2 + ".java", "public class MethodBody {\n" + trainingMethods.toString() + "}", true);
		FileHelper.outputToFile(testingDataPath + methodFile2 + ".java", "public class MethodBody {\n" + testingDataPath.toString() + "}", true);
		FileHelper.outputToFile(selectedMethodsPath + methodFile2 + ".java", "public class MethodBody {\n" + selectedMethods.toString() + "}", true);
		trainingMethods.setLength(0);
		testingMethods.setLength(0);
		selectedMethods.setLength(0);
		
	}

	private List<Integer> readIndexes(String fileName) {
		List<Integer> indexes = new ArrayList<>();
		String content = FileHelper.readFile(fileName);
		try {
			BufferedReader reader = new BufferedReader(new StringReader(content));
			String line = null;
			while ((line = reader.readLine()) != null) {
				indexes.add(Integer.parseInt(line.substring(0, line.indexOf("@"))));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return indexes;
	}
}
