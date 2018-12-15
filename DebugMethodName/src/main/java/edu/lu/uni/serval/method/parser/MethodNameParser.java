package edu.lu.uni.serval.method.parser;

import java.io.IOException;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class MethodNameParser {

	/**
	 * Parse method name into sub-tokens.
	 * 
	 * @param methodName
	 * @return
	 */
	public String parseMethodName(String methodName) {
		try {
			StringBuilder subTokens = parseWithGenTest(methodName);
			if (subTokens.length() == 0) {
				return parseWithCamelCase(methodName);
			} else {
				return subTokens.toString().substring(0, subTokens.length() - 1);
			}
		} catch (IOException e) {
			return parseWithCamelCase(methodName);
		}
	}
	
	/**
	 * Parse method name into sub-tokens with GenTest.
	 * 
	 * @param methodName
	 * @return
	 * @throws IOException
	 */
	public StringBuilder parseWithGenTest(String methodName) throws IOException{
		Splitter s = new Splitter();
		StringBuilder subTokens = new StringBuilder();
		String parsedName = null;
		try {
			parsedName = s.split(methodName, "java", 1);
		} catch (IOException e) {
			throw new IOException("Failed to parse the method name with GenTest.");
		}
		
		if (parsedName == null) return subTokens;
		
		String[] subTokensArray = parsedName.split("\\n");
		for (String subToken : subTokensArray) {
			subToken = subToken.substring(1).trim();
			if (subToken.contains("_")) {
				String[] subSubTokens = subToken.split("_");
				for (String subSubToken : subSubTokens) {
					subTokens.append(subSubToken.toLowerCase(Locale.ROOT)).append(",");
				}
			} else if (NumberUtils.isDigits(subToken)) {// remove numeric letter.
				continue;
			} else {
				subTokens.append(subToken.toLowerCase(Locale.ROOT)).append(",");
			}
		}
		return subTokens;
	}

	/**
	 * Parse method name into sub-tokens with camel case.
	 * 
	 * @param methodName
	 * @return
	 */
	public String parseWithCamelCase(String methodName) {
		String[] subTokensArray = StringUtils.splitByCharacterTypeCamelCase(methodName);
		StringBuilder subTokens = new StringBuilder();
		int length = subTokensArray.length;
		for (int i = 0; i < length; i ++) {
			String subToken = subTokensArray[i];
			if ("_".equals(subToken)) {// remove underscore.
				continue;
			} else if (NumberUtils.isDigits(subToken)) {// remove numeric letter.
				continue;
			}
			subTokens.append(subTokensArray[i].toLowerCase(Locale.ROOT)).append(",");
		}
		if (subTokens.length() == 0) {
			System.err.println(methodName);
			return null;
		}
		return subTokens.toString().substring(0, subTokens.length() - 1);
	}
}
