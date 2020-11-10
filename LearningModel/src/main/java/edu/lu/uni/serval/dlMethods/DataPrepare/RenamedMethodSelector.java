package edu.lu.uni.serval.dlMethods.DataPrepare;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.lu.uni.serval.utils.FileHelper;

public class RenamedMethodSelector {
	public String renamedMethodsPath;
	
	// Method info of renamed methods.
	public List<String> methodInfoOfRenamedMethods = new ArrayList<>();
	// Method body token vectors of renamed methods.
	public List<String> tokenVectorsOfRenamedMethods = new ArrayList<>();
	// parsed old method names @ parsed new method names: of selected renamed methods
	public List<String> parsedRenamedMethodNames = new ArrayList<>();
	public List<Integer> indexesOfSelectedMethods = new ArrayList<>();
	
	/**
	 * Select renamed methods by their first tokens and sizes.
	 * @param renamedMethodsPath
	 * @throws IOException 
	 */
	public void selectRenamedMethods(List<String> commonFirstTokens,
			int minSize, int maxSize) throws IOException {
		// Read the sizes of renamed method token vectors.
		List<Integer> sizesOfRenamesMethodTokenVectors = readSizes(renamedMethodsPath + "MethodTokensSizes.csv");
		
		// Select renamed methods by their first tokens and their vector sizes.
		List<String> parsedOldMethodNames = new ArrayList<>();
		List<String> parsedNewMethodNames = new ArrayList<>();
		String parsedOldNamesFile = renamedMethodsPath + "ParsedOldNames.txt";
		List<String> oldFirstTokens = readFirstTokens(parsedOldNamesFile, parsedOldMethodNames);
		String parsedNewNamesFile = renamedMethodsPath + "ParsedNewNames.txt";
		List<String> newFirstTokens = readFirstTokens(parsedNewNamesFile, parsedNewMethodNames);
		
		List<Integer> selectedRenamedMethodIndexes = new ArrayList<>();
		
		// Read tokens of renamed method bodies.
		FileInputStream fis = new FileInputStream(renamedMethodsPath + "MethodTokens.txt");
		Scanner scanner = new Scanner(fis);
		int index = -1;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			index ++;

//			String oldFirstToken = oldFirstTokens.get(index);
//			String newFirstToken = newFirstTokens.get(index);
//			if (commonFirstTokens.contains(newFirstToken)) {//commonFirstTokens.contains(oldFirstToken) || 
				int sizeOfTokenVector = sizesOfRenamesMethodTokenVectors.get(index);
				if (minSize < sizeOfTokenVector && sizeOfTokenVector <= maxSize) {//(true) {//
					// selected renamed method.
					selectedRenamedMethodIndexes.add(index);
					
					// commons-io:626bad:3e9473:org.apache.commons.io.IOUtils.java:asBufferedOutputStream:buffer:491:491:BufferedOutputStream:arguments:tokensVector.
					// project_name, old_commit_id, new_commit_id, file_path, old_method_name, new_method_name, old_line, new_line, return_type, arguments, tokens.
					String[] elements = line.split(":");
					// org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor.java
					String filePath = elements[3];
					filePath = filePath.substring(0, filePath.lastIndexOf(".java"));
					int indexOfLastDot = filePath.lastIndexOf(".");
					String packageName = filePath.substring(0, indexOfLastDot);
					String className = filePath.substring(indexOfLastDot + 1);
					// ProjectName : packageName : className : methodName : arguments : returnType.
					String methodName = elements[5];
					methodName = methodName.substring(0, methodName.indexOf("@"));
					String methodInfo = elements[0] + ":" + packageName + ":" + className + ":" + methodName + ":" + elements[9] + ":" + elements[8];
					String tokens = elements[10];
					
					if ("Block Block".equals(tokens)) continue;
					
					indexesOfSelectedMethods.add(index);
					this.methodInfoOfRenamedMethods.add(methodInfo);
					this.tokenVectorsOfRenamedMethods.add(tokens);
					this.parsedRenamedMethodNames.add(parsedOldMethodNames.get(index)+ "@" + parsedNewMethodNames.get(index));
				}
//			}
		}
		scanner.close();
		fis.close();
		
		System.out.println("Number of selected renamed methods:" + selectedRenamedMethodIndexes.size());
	}
	
	public List<Integer> readSizes(String sizesFile) throws IOException {
		List<Integer> sizesList = new ArrayList<>();
		String sizesContent = FileHelper.readFile(sizesFile);
		BufferedReader reader = new BufferedReader(new StringReader(sizesContent));
		String line = null;
		while ((line = reader.readLine()) != null) {
			sizesList.add(Integer.parseInt(line));
		}
		reader.close();
		
		return sizesList;
	}

	private List<String> readFirstTokens(String parsedMethodNamesFile, List<String> parsedMethodNames) throws IOException {
		List<String> firstTokens = new ArrayList<>();
		String content = FileHelper.readFile(parsedMethodNamesFile);
		BufferedReader reader = new BufferedReader(new StringReader(content));
		String line = null;
		while ((line = reader.readLine()) != null) {
			parsedMethodNames.add(line);
			int index = line.indexOf(",");
			if (index > 0) {
				firstTokens.add(line.substring(0, index));
			} else firstTokens.add(line);
		}
		reader.close();
		return firstTokens;
	}
	
}
