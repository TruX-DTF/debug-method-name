package edu.lu.uni.serval.dlMethods.DataPrepare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.lu.uni.serval.utils.FileHelper;
import edu.lu.uni.serval.utils.ReturnType;
import edu.lu.uni.serval.utils.ReturnType.ReturnTypeClassification;

public class CommonFirstTokens {
	
	public String inputPath;
	public String outputPath;
	public int QUANTITY = 1000;// or 500, the number of methods of which names start with the same token.
	
	/**
	 * <FirstToken, Number>: of all methods.
	 */
	Map<String, Integer> allFirstTokensDistribution = new HashMap<>();
	/**
	 * <ReturnType, <FirstToken, Number>>: of all methods.
	 */
	Map<String, Map<String, Integer>> returnTypes = new HashMap<>();
	// First token list of all methods.
	public List<String> allFirstTokensList = new ArrayList<>();
	// Tokens of all parsed method names.
	public List<String> tokenVectorOfAllParsedMethodNames = new ArrayList<>();
	// Common first tokens of all methods.
	public List<String> commonFirstTokens = new ArrayList<>();
	
	/**
	 * Read the distribution of first tokens.
	 * @throws IOException
	 */
	public void readTokens() throws IOException {
		String parsedMethodNamesFile = inputPath + "ParsedMethodNames.txt";
		String content = FileHelper.readFile(parsedMethodNamesFile);
		BufferedReader reader = new BufferedReader(new StringReader(content));
		String line = null;
		StringBuilder builder = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			//hibernate-metamodelgen:org.hibernate.jpamodelgen.util:StringUtil:determineFullyQualifiedClassName:String+defaultPackage+String+name:String#determine:VB,Fully:NNP,Qualified:NNP,Class:NNP,Name:VB
			int sharpSymbleIndex = line.indexOf("#");
			String methodInfo = line.substring(0, sharpSymbleIndex);
			String returnType = methodInfo.substring(methodInfo.lastIndexOf(":") + 1);
			returnType = new ReturnType().readReturnType(returnType, ReturnTypeClassification.ABSTRACT);
			String methodNameTokens = line.substring(sharpSymbleIndex + 1);
			int indexOfComma = methodNameTokens.indexOf(",");
			String firstToken;
			if (indexOfComma > 0) {
				firstToken = methodNameTokens.substring(0, indexOfComma);
			} else firstToken = methodNameTokens;
			
			Integer value = allFirstTokensDistribution.get(firstToken);
			if (value == null) {
				allFirstTokensDistribution.put(firstToken, 1);
			} else {
				allFirstTokensDistribution.put(firstToken, value + 1);
			}
			
			Map<String, Integer> returnTypeTokens = returnTypes.get(returnType);
			if (returnTypeTokens == null) {
				returnTypeTokens = new HashMap<>();
				returnTypeTokens.put(firstToken, 1);
				returnTypes.put(returnType, returnTypeTokens);
			} else {
				Integer subValue = returnTypeTokens.get(firstToken);
				if (subValue == null) {
					returnTypeTokens.put(firstToken, 1);
				} else {
					returnTypeTokens.put(firstToken, subValue + 1);
				}
			}
//			methodNameTokens = methodNameTokens.replace(":", ",");
			
			this.allFirstTokensList.add(firstToken);
			methodNameTokens = returnType + "@" + methodNameTokens;
			this.tokenVectorOfAllParsedMethodNames.add(methodNameTokens);
			builder.append(methodNameTokens).append("\n");
		}
		reader.close();
		
		FileHelper.outputToFile(this.outputPath + "ParsedMethodNames.txt", builder, false);
		builder.setLength(0);
	}

	/**
	 * Export the distribution of first tokens to a file, and select the common first tokens by the threshold of QUANTITY.
	 * @param outputPath
	 */
	public void outputTokens() {
		StringBuilder builder = new StringBuilder("Token,Number\n");
		for (Map.Entry<String, Integer> entry : this.allFirstTokensDistribution.entrySet()) {
			builder.append(entry.getKey()).append(",").append(entry.getValue()).append("\n");
			if (entry.getValue() >= QUANTITY) {
				this.commonFirstTokens.add(entry.getKey());
			}
		}
		FileHelper.outputToFile(outputPath + "FirstTokens.csv", builder, false);
		builder.setLength(0);
		
		for (Map.Entry<String, Map<String, Integer>> entry : this.returnTypes.entrySet()) {
			String returnType = entry.getKey();
			Map<String, Integer> firstTokens = entry.getValue();
			builder.append("Token,Number\n");
			for (Map.Entry<String, Integer> subEntry : firstTokens.entrySet()) {
				builder.append(subEntry.getKey()).append(",").append(subEntry.getValue()).append("\n");
			}
			FileHelper.outputToFile(outputPath + "/ReturnTypes/" + returnType + ".csv", builder, false);
			builder.setLength(0);
		}
	}
}
