package edu.lu.uni.serval.dlMethods;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.lu.uni.Configuration;

/**
 * Compute the number of renamed methods that are the same as their latest versions.
 * @author kui.liu
 *
 */
public class RenamedMethods {

	public static void main(String[] args) {
		try {
			String inputPath = Configuration.TOKENIZED_METHODS_PATH;
			String renamedMethodsPath = Configuration.RENAMED_METHODS_PATH;
			selectMethod(inputPath, renamedMethodsPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void selectMethod(String inputPath, String renamedMethodsPath) throws IOException {
		List<Integer> furtherSelecteRenamedMethodIndexes = new ArrayList<>();
		List<String> methodInfoOfRenamedMethods = new ArrayList<>();
		List<String> tokenVectorsOfRenamedMethods = new ArrayList<>();
		selectRenamedMethods(renamedMethodsPath, methodInfoOfRenamedMethods, tokenVectorsOfRenamedMethods);
		System.out.println(methodInfoOfRenamedMethods.size());
		System.out.println(tokenVectorsOfRenamedMethods.size());
		
		String tokensFile = inputPath + "tokens.list";
		
		FileInputStream fis = new FileInputStream(tokensFile);
		Scanner scanner = new Scanner(fis);
		int counter = 0;
		while (scanner.hasNextLine()) {
			//pig:org.apache.tools.bzip2r:CRC:initialiseCRC:null:void#[tokens]
			// projectName : packageName : ClassName : methodName : arguments: ReturnType#tokens.
			String lineStr = scanner.nextLine();
			counter ++;
			
			int sharpPosition = lineStr.indexOf("#");
			String methodInfo = lineStr.substring(0, sharpPosition);
			String tokens = lineStr.substring(sharpPosition + 2, lineStr.length() - 1).replace(", ", " ");

			int renamedIndex = methodInfoOfRenamedMethods.indexOf(methodInfo);
			if (renamedIndex >= 0) {
				// selected renamed methods.
				String renamedTokens = tokenVectorsOfRenamedMethods.get(renamedIndex);
				if (renamedTokens.equals(tokens)) {
					if (!furtherSelecteRenamedMethodIndexes.contains(renamedIndex)) {
						furtherSelecteRenamedMethodIndexes.add(renamedIndex);
					}
					continue;
				}
			}
		}
		scanner.close();
		fis.close();
		
		System.out.println(furtherSelecteRenamedMethodIndexes.size());
		System.out.println(counter);
	}
	
	public static void selectRenamedMethods(String renamedMethodsPath, List<String> methodInfoOfRenamedMethods, List<String> tokenVectorsOfRenamedMethods) throws IOException {
		// Read tokens of renamed method bodies.
		FileInputStream fis = new FileInputStream(renamedMethodsPath + "RenamedMethods.txt");
		Scanner scanner = new Scanner(fis);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
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
			methodInfoOfRenamedMethods.add(methodInfo);
			tokenVectorsOfRenamedMethods.add(tokens);
		}
		scanner.close();
		fis.close();
		
	}

}
