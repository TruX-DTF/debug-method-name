package edu.lu.uni.serval.renamed.methods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import edu.lu.uni.serval.utils.FileHelper;
import edu.lu.uni.serval.utils.MapSorter;
import edu.lu.uni.serval.utils.ReturnType;
import edu.lu.uni.serval.utils.ReturnType.ReturnTypeClassification;
import edu.lu.uni.serval.utils.LevenshteinDistance;
import jxl.CellView;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * Parse method names: valid-method-ids-cluster.
 * 
 * Filter out correcting method naming typos.
 * 
 * @author kui.liu
 *
 */
public class RenamedMethodsFilter {

	private static String rootPath = Configuration.OUTPUT_PATH;
	
	public static void main(String[] args) {
		
		// Fileter out typos by leveraging levenshtein distance.
//		filteroutTyposByLevenshteinDistance();
		
		/*
		 * 1. the length of the old method name is equal to the length of the new method name. 
		 * 2. different length.
		 * 
		 * Starts with the same token: typos.
		 */
		filteroutTyposByParsedMethodNames(Configuration.RENAMED_METHODS_PATH);
		
		// return types of renamed methods
//		returnTypes();
	}

	public static void returnTypes() {
		List<String> returnTypesList = new ArrayList<>();
		try {
			FileInputStream fis = new FileInputStream(rootPath + "RenamedMethods.txt");
			Scanner scanner = new Scanner(fis);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] elements = line.split(":");
				String returnType = elements[8];
				if ("Arrays".equals(returnType)) {
					returnType = "Arrays[]";
				}
				returnTypesList.add(new ReturnType().readReturnType(returnType, ReturnTypeClassification.ABSTRACT) + 
						"@" + elements[4] + "@" + elements[5]);
			}
			scanner.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String content = FileHelper.readFile(rootPath + "RenamedMethodNames.txt");
		BufferedReader reader = new BufferedReader(new StringReader(content));
		String line = null;
		Map<String, Map<String, Integer>> oldTokens = new HashMap<>();
		Map<String, Map<String, Integer>> newTokens = new HashMap<>();
		try {
			while ((line = reader.readLine()) != null) {
				String[] elements = line.split("@");
				int index = Integer.parseInt(elements[0]);
				String oldToken = elements[1];
				String newToken = elements[2];
				String names = returnTypesList.get(index);
				String returnType = names.substring(0, names.indexOf("@"));
				
				if ("boolean".equals(returnType)) {
					if (newToken.equals("is") || newToken.equals("has") || newToken.equals("equals")
							 || newToken.equals("should") || newToken.equals("needs") || newToken.equals("contains")
							 || newToken.equals("can") || newToken.equals("are") || newToken.equals("starts")
							 || newToken.equals("start") || oldToken.equals("get") || oldToken.equals("set")) {
						System.err.println(index + "@" + names);
					}
				} else if ("OTHERS".equals(returnType)) {
					if (oldToken.equals("is") || oldToken.equals("has") || oldToken.equals("exit") 
							|| oldToken.equals("before") || oldToken.equals("id")
							|| newToken.equals("get") || newToken.equals("create") || newToken.equals("new") 
							|| newToken.equals("to") || newToken.equals("find") || newToken.equals("next")) {
						
					}
				} else if ("void".equals(returnType)) {
					if (oldToken.equals("are") || oldToken.equals("get") || oldToken.equals("after") || oldToken.equals("is") 
							|| oldToken.equals("before") || oldToken.equals("end") || oldToken.equals("find") || oldToken.equals("index")
							|| oldToken.equals("to") || oldToken.equals("copy") || oldToken.equals("return") ) {
						
					}
				} else if ("double".equals(returnType)) {
					if (oldToken.equals("string") || newToken.equals("double")) {
						
					}
				}
				
				if (oldTokens.containsKey(returnType)) {
					Map<String, Integer> oldTokensMap = oldTokens.get(returnType);
					if (oldTokensMap.containsKey(oldToken)) {
						oldTokensMap.put(oldToken, oldTokensMap.get(oldToken) + 1);
					} else {
						oldTokensMap.put(oldToken, 1);
					}
				} else {
					Map<String, Integer> oldTokensMap = new HashMap<>();
					oldTokensMap.put(oldToken, 1);
					oldTokens.put(returnType, oldTokensMap);
				}
				
				if (newTokens.containsKey(returnType)) {
					Map<String, Integer> newTokensMap = newTokens.get(returnType);
					if (newTokensMap.containsKey(newToken)) {
						newTokensMap.put(newToken, newTokensMap.get(newToken) + 1);
					} else {
						newTokensMap.put(newToken, 1);
					}
				} else {
					Map<String, Integer> newTokensMap = new HashMap<>();
					newTokensMap.put(newToken, 1);
					newTokens.put(returnType, newTokensMap);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (Map.Entry<String, Map<String, Integer>> entry : oldTokens.entrySet()) {
			MapSorter<String, Integer> sorter = new MapSorter<>();
			oldTokens.put(entry.getKey(), sorter.sortByValueDescending(entry.getValue()));
		}
		for (Map.Entry<String, Map<String, Integer>> entry : newTokens.entrySet()) {
			MapSorter<String, Integer> sorter = new MapSorter<>();
			newTokens.put(entry.getKey(), sorter.sortByValueDescending(entry.getValue()));
		}
		
		exportData(oldTokens, rootPath + "oldTokens.xls");
		exportData(newTokens, rootPath + "newTokens.xls");
	}

	private static void exportData(Map<String, Map<String, Integer>> oldTokens, String fileName) {
		File file = new File(fileName);
		
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			
			// new excel file.
			WritableWorkbook book = Workbook.createWorkbook(file);
			WritableSheet sheet = book.createSheet("sheet ", 0);

			// Setting cell width according to the length of content automatically.
			CellView cellView = new CellView();
			cellView.setAutosize(true);
			for (int i = 0, length = oldTokens.size() * 3; i < length; i++) {
				sheet.setColumnView(i, cellView);
			}
			
			int column = 0;
			for (Map.Entry<String, Map<String, Integer>> entry : oldTokens.entrySet()) {
				String returnType = entry.getKey();
				Map<String, Integer> tokens = entry.getValue();
				int rowIndex = 0;
				sheet.addCell(new Label(column, rowIndex, returnType));
				sheet.addCell(new Label(column + 1, rowIndex, ""));
				sheet.addCell(new Label(column + 2, rowIndex, ""));
				rowIndex ++;
				sheet.addCell(new Label(column, rowIndex, "Token"));
				sheet.addCell(new Label(column + 1, rowIndex, "Quantity"));
				sheet.addCell(new Label(column + 2, rowIndex, ""));
				rowIndex ++;
				
				for (Map.Entry<String, Integer> subEntry : tokens.entrySet()) {
					sheet.addCell(new Label(column, rowIndex, subEntry.getKey()));
					sheet.addCell(new Label(column + 1, rowIndex, subEntry.getValue() + ""));
					sheet.addCell(new Label(column + 2, rowIndex, ""));
					rowIndex++;
				}
				sheet.mergeCells(column, 0, column + 1, 0);
				column += 3;
			}
			
			book.write();
			book.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	public static void filteroutTyposByLevenshteinDistance() {
		List<String> oldMethodNames = readParsedMethodNames(rootPath + "OldMethodNames.list");
		List<String> newMethodNames = readParsedMethodNames(rootPath + "NewMethodNames.list");
		List<Integer> distances = new ArrayList<>();
		StringBuilder distanceBuilder = new StringBuilder();
		for (int i = 0, size = oldMethodNames.size(); i < size; i ++) {
			String oldMethodName = oldMethodNames.get(i);
			String newMethodName = newMethodNames.get(i);
			int levenshteinDistance = new LevenshteinDistance().computeLevenshteinDistance(oldMethodName.toLowerCase(), newMethodName.toLowerCase());
			distances.add(levenshteinDistance);
			distanceBuilder.append(levenshteinDistance).append("\n");
		}
		FileHelper.outputToFile(rootPath + "LevenshteinDistances.csv", distanceBuilder, false);
		
		
		
//		oldMethodNames = readParsedMethodNames("../OUTPUT/RenamedMethods/renameOldNames.list");
//		newMethodNames = readParsedMethodNames("../OUTPUT/RenamedMethods/renameNewNames.list");
//		distances = new ArrayList<>();
//		distanceBuilder = new StringBuilder();
//		for (int i = 0, size = oldMethodNames.size(); i < size; i ++) {
//			String oldMethodName = oldMethodNames.get(i);
//			String newMethodName = newMethodNames.get(i);
//			int levenshteinDistance = new LevenshteinDistance().computeLevenshteinDistance(oldMethodName.toLowerCase(), newMethodName.toLowerCase());
//			distances.add(levenshteinDistance);
//			distanceBuilder.append(levenshteinDistance).append("\n");
//		}
//		FileHelper.outputToFile("../OUTPUT/RenamedMethods/LevenshteinDistances.csv", distanceBuilder, false);
//		
//		
//		
//		oldMethodNames = readParsedMethodNames("../OUTPUT/TypoMethods/typoOldNames.list");
//		newMethodNames = readParsedMethodNames("../OUTPUT/TypoMethods/typoNewNames.list");
//		distances = new ArrayList<>();
//		distanceBuilder = new StringBuilder();
//		for (int i = 0, size = oldMethodNames.size(); i < size; i ++) {
//			String oldMethodName = oldMethodNames.get(i);
//			String newMethodName = newMethodNames.get(i);
//			int levenshteinDistance = new LevenshteinDistance().computeLevenshteinDistance(oldMethodName.toLowerCase(), newMethodName.toLowerCase());
//			distances.add(levenshteinDistance);
//			distanceBuilder.append(levenshteinDistance).append("\n");
//		}
//		FileHelper.outputToFile("../OUTPUT/TypoMethods/LevenshteinDistances.csv", distanceBuilder, false);
	}

	public static void filteroutTyposByParsedMethodNames(String dataPath) {
		String parsedOldMethodNamesFile = dataPath + "OldMethodNames.txt";
		List<String> parsedOldMethodNames = readParsedMethodNames(parsedOldMethodNamesFile);
		String parsedNewMethodNamesFile = dataPath + "NewMethodNames.txt";
		List<String> parsedNewMethodNames = readParsedMethodNames(parsedNewMethodNamesFile);
		StringBuilder typos = new StringBuilder("index@ParsedOldName@ParsedNewName\n");
		StringBuilder renames = new StringBuilder("index@oldToken@newToken\n");
		StringBuilder oldNames = new StringBuilder();
		StringBuilder newNames = new StringBuilder();
		List<Integer> renameIndexes = new ArrayList<>();
		for (int i = 0, size = parsedOldMethodNames.size(); i < size; i ++) {
			String oldMethodName = parsedOldMethodNames.get(i);
			oldMethodName = oldMethodName.substring(oldMethodName.indexOf("@") + 1);
			List<String> oldMethodNameTokens = Arrays.asList(oldMethodName.split(","));
			String newMethodName = parsedNewMethodNames.get(i);
			newMethodName = newMethodName.substring(newMethodName.indexOf("@") + 1);
			List<String> newMethodNameTokens = Arrays.asList(newMethodName.split(","));
			
			if (oldMethodNameTokens.get(0).equals(newMethodNameTokens.get(0))) {// typos: starts with the same token.
				typos.append(i).append("@").append(oldMethodName).append("@").append(newMethodName).append("\n");
			} else {
				if (oldMethodName.equals("main") || newMethodName.equals("main")) { 
//						|| oldMethodName.startsWith("main,") || newMethodName.startsWith("main,")) {
					typos.append(i).append("@").append(oldMethodName).append("@").append(newMethodName).append("\n");
					continue;
				}
				String firstOldChar = oldMethodName.substring(0, 1);
				String firstNewChar = newMethodName.substring(0, 1);
				if (!firstOldChar.toLowerCase().equals(firstOldChar) || !firstNewChar.toLowerCase().equals(firstNewChar)) {
					// Constructors. Maybe.
					typos.append(i).append("@").append(oldMethodName).append("@").append(newMethodName).append("\n");
					continue;
				}
				String oldToken = oldMethodNameTokens.get(0);
				String newToken = newMethodNameTokens.get(0);
				if (!oldToken.equalsIgnoreCase(newToken) && !newToken.startsWith(oldToken) && !oldToken.startsWith(newToken)) {
					renameIndexes.add(i);
					renames.append(i).append("@").append(oldToken).append("@").append(newToken).append("\n");
					oldNames.append(oldMethodName).append("\n");
					newNames.append(newMethodName).append("\n");
				} else {
					typos.append(i).append("@").append(oldMethodName).append("@").append(newMethodName).append("\n");
				}
			}
		}
		FileHelper.outputToFile(dataPath + "Typo/MethodNamePairs.txt", typos, false);
		FileHelper.outputToFile(dataPath + "ActualRenamed/MethodNamesInfo.txt", renames, false);
		FileHelper.outputToFile(dataPath + "ActualRenamed/ParsedOldNames.txt", oldNames, false);
		FileHelper.outputToFile(dataPath + "ActualRenamed/ParsedNewNames.txt", newNames, false);
		
		
//		List<String> oldMethodNames = readParsedMethodNames(rootPath + "RenamedMethods/OldMethodNames.txt");
//		List<String> newMethodNames = readParsedMethodNames(rootPath + "RenamedMethods/NewMethodNames.txt");
//		StringBuilder typoOldNames = new StringBuilder();
//		StringBuilder typoNewNames = new StringBuilder();
//		StringBuilder renameOldNames = new StringBuilder();
//		StringBuilder renameNewNames = new StringBuilder();
//		for (int i = 0, size = oldMethodNames.size(); i < size; i ++) {
//			if (renameIndexes.contains(i)) {
//				renameOldNames.append(oldMethodNames.get(i)).append("\n");
//				renameNewNames.append(newMethodNames.get(i)).append("\n");
//			} else {
//				typoOldNames.append(oldMethodNames.get(i)).append("\n");
//				typoNewNames.append(newMethodNames.get(i)).append("\n");
//			}
//		}
//		FileHelper.outputToFile(rootPath + "ActualRenamed/OldNames.txt", renameOldNames, false);
//		FileHelper.outputToFile(rootPath + "ActualRenamed/NewNames.txt", renameNewNames, false);
//		FileHelper.outputToFile(rootPath + "Typo/OldNames.txt", typoOldNames, false);
//		FileHelper.outputToFile(rootPath + "Typo/NewNames.txt", typoNewNames, false);
		

		// Output tokens of method bodies.
		StringBuilder renamedMethodsBuilder = new StringBuilder();
		StringBuilder renameMethodTokensSizeBuilder = new StringBuilder();
		StringBuilder typoMethodsBuilder = new StringBuilder();
		StringBuilder typoMethodTokensSizeBuilder = new StringBuilder();
		String methodsFile = dataPath + "RenamedMethods.txt";
		FileInputStream fis = null;
		Scanner scanner = null;
		try {
			fis = new FileInputStream(methodsFile);
			scanner = new Scanner(fis);
			int index = -1;
			
			while (scanner.hasNextLine()) {
				index ++;
				String line = scanner.nextLine();
				String[] elements = line.split(":");
				String[] tokens = elements[10].split(" ");
				if (elements.length != 11) {
					System.err.println(line);
				}
				
				if (renameIndexes.contains(index)) {
					renamedMethodsBuilder.append(line).append("\n");
					renameMethodTokensSizeBuilder.append(tokens.length).append("\n");
				} else {
					typoMethodsBuilder.append(line).append("\n");
					typoMethodTokensSizeBuilder.append(tokens.length).append("\n");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
				scanner = null;
			}
			if (fis != null) {
				try {
					fis.close();
					fis = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		FileHelper.outputToFile(dataPath + "ActualRenamed/MethodTokens.txt", renamedMethodsBuilder, false);
		renamedMethodsBuilder.setLength(0);
		FileHelper.outputToFile(dataPath + "ActualRenamed/MethodTokensSizes.csv", renameMethodTokensSizeBuilder, false);
		FileHelper.outputToFile(dataPath + "Typo/MethodTokens.txt", typoMethodsBuilder, false);
		renamedMethodsBuilder.setLength(0);
		FileHelper.outputToFile(dataPath + "Typo/MethodTokensSizes.csv", typoMethodTokensSizeBuilder, false);
		
		
		// Output method bodies.
		StringBuilder renamedMethodBodies = new StringBuilder();
		StringBuilder typoMethodBodies = new StringBuilder();
		try {
			fis = new FileInputStream(dataPath + "MethodBodies.txt");
			scanner = new Scanner(fis);
			int index = -1;
			StringBuilder singleMethod = new StringBuilder();
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if ("#METHOD_BODY#========================".equals(line)) {
					if (singleMethod.length() > 0) {
						if (renameIndexes.contains(index)) {
							renamedMethodBodies.append(singleMethod);
						} else {
							typoMethodBodies.append(singleMethod);
						}
						singleMethod.setLength(0);
					}
					index ++;
				}
				singleMethod.append(line).append("\n");
			}
			scanner.close();
			fis.close();
			
			if (singleMethod.length() > 0) {
				if (renameIndexes.contains(index)) {
					renamedMethodBodies.append(singleMethod);
				} else {
					typoMethodBodies.append(singleMethod);
				}
				singleMethod.setLength(0);
			}

			FileHelper.outputToFile(dataPath + "ActualRenamed/MethodBodies.txt", renamedMethodBodies, false);
			FileHelper.outputToFile(dataPath + "Typo/MethodBodies.txt", typoMethodBodies, false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<String> readParsedMethodNames(String parsedMethodNamesFile) {
		List<String> parsedMethodNames = new ArrayList<>();
		String content = FileHelper.readFile(parsedMethodNamesFile);
		BufferedReader reader = new BufferedReader(new StringReader(content));
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				parsedMethodNames.add(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return parsedMethodNames;
	}

}
