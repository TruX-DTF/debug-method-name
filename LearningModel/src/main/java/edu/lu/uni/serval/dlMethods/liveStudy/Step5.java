package edu.lu.uni.serval.dlMethods.liveStudy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.lu.uni.serval.utils.FileHelper;

public class Step5 {

	public static void main(String[] args) throws IOException {
		String inputFile = args[0];//../OUTPUT_4/LiveStudy/TestingData/MethodsInfo.txt
		String outputFile = args[1];//../OUTPUT_4/LiveStudy/TestingData/method_bodies.txt
		String methodBodiesFile = "../OUTPUT/tokenization/method_bodies.txt";
		List<Integer> selectedIndexes = readIndexes(inputFile);
		FileInputStream fis = new FileInputStream(methodBodiesFile);
		Scanner scanner = new Scanner(fis);
		StringBuilder methods = new StringBuilder();
		int index = -1;
		StringBuilder singleMethod = new StringBuilder();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if ("#METHOD_BODY#========================".equals(line)) {
				if (singleMethod.length() > 0) {
					if (selectedIndexes.contains(index)) {
						methods.append(singleMethod).append("\n");
					}
				}
				singleMethod.setLength(0);
				index ++;
			}
			singleMethod.append(line).append("\n");
		}
		scanner.close();
		fis.close();
		
		if (selectedIndexes.contains(index)) {
			methods.append(singleMethod).append("\n");
		}
		
		FileHelper.outputToFile(outputFile, methods, false);
	}

	private static List<Integer> readIndexes(String inputFile) throws IOException {
		List<Integer> indexes = new ArrayList<>();
		FileReader fileReader = new FileReader(inputFile);
		BufferedReader reader = new BufferedReader(fileReader);
		String line = null;
		while ((line = reader.readLine()) != null) {
			int index = Integer.valueOf(line.substring(0, line.indexOf("@")));
			indexes.add(index);
		}
		reader.close();
		fileReader.close();
		return indexes;
	}

}
