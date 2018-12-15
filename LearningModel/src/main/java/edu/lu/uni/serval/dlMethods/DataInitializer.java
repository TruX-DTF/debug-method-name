package edu.lu.uni.serval.dlMethods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

import edu.lu.uni.Configuration;
import edu.lu.uni.serval.dlMethods.DataPreparer.RenamedMethodSelector;
import edu.lu.uni.serval.utils.Distribution;
import edu.lu.uni.serval.utils.Distribution.MaxSizeType;
import edu.lu.uni.serval.utils.FileHelper;

/**
 * Figure out the common first tokens of all method names.
 * Select the methods of which method names start with these common first tokens.
 * 
 * Training data: 90%.
 * Testing data: 10%.
 * @author kui.liu
 *
 */
public class DataInitializer {
	
	public int QUANTITY = 1;// or 500, the number of methods of which names start with the same token.
	public int MIN_SIZE = 0;    // The minimum size of vectors.
	
	public String inputPath;
	public String outputPath;
	public String renamedMethodsPath;
	
	// First token list of all methods.
	List<String> allFirstTokensList = new ArrayList<>();
	// Tokens of all parsed method names.
	List<String> tokenVectorOfAllParsedMethodNames = new ArrayList<>();
	// Common first tokens of all methods.
	List<String> commonFirstTokens = new ArrayList<>();
	// Sizes of all method token vectors.
	List<Integer> sizesListOfAllMethodBodyTokenVectors = new ArrayList<>();
	// Threshold of method token vectors sizes for selecting methods.
	int maxSize = 0;

	// Method info of renamed methods.
	List<String> methodInfoOfRenamedMethods = new ArrayList<>();
	// Method body token vectors of renamed methods.
	List<String> tokenVectorsOfRenamedMethods = new ArrayList<>();
	// parsed old method names @ parsed new method names: of selected renamed methods
	List<String> parsedRenamedMethodNames = new ArrayList<>();
	
	// Sizes of selected method token vectors.
//	List<Integer> selectedSizesList = new ArrayList<>();
	// Selected parsed method names of all methods.
//	List<String> selecteParsedMethodNames = new ArrayList<>();
	// selected token vectors of all methods.
	List<String> selectedTokenVecotrsOfAllMethods = new ArrayList<>();
	// Selected method info of all methods.
	List<String>  selectedMethodInfoOfAllMethods = new ArrayList<>();
	
	List<Integer> selectMethodIndexes = new ArrayList<>();
	List<Integer> trainingMethodIndexes = new ArrayList<>();
	List<Integer> testingMethodIndexes = new ArrayList<>();
	List<Integer> selecteRenamedMethodIndexes = new ArrayList<>();
	List<Integer> furtherSelecteRenamedMethodIndexes = new ArrayList<>();
	
	public void initializeData() throws IOException {
//		// Common first tokens of all methods.
//		CommonFirstTokens cft = new CommonFirstTokens();
//		cft.inputPath = this.inputPath;
//		cft.outputPath = this.outputPath;
//		cft.QUANTITY = this.QUANTITY;
//		// Read the distribution of first tokens to get the common first tokens.
//		cft.readTokens();
//		// Select common first tokens.
//		cft.outputTokens();
//		this.allFirstTokensList = cft.allFirstTokensList;
//		this.tokenVectorOfAllParsedMethodNames = cft.tokenVectorOfAllParsedMethodNames;
//		this.commonFirstTokens = cft.commonFirstTokens;
		
		
		// Get the threshold of sizes of method token vectors
		List<Integer> sizesList = readSizes("sizes.csv");//TODO
		maxSize = Distribution.computeMaxSize(MaxSizeType.UpperWhisker, sizesList);
		if (maxSize % 2 != 0) {
			maxSize += 1;
		}
		

		// Select the renamed methods of which names start with one of the common first token.
		RenamedMethodSelector rms = new RenamedMethodSelector();
		rms.renamedMethodsPath = renamedMethodsPath;
		rms.selectRenamedMethods(null, MIN_SIZE, maxSize);
		this.methodInfoOfRenamedMethods = rms.methodInfoOfRenamedMethods;
		this.tokenVectorsOfRenamedMethods = rms.tokenVectorsOfRenamedMethods;
		this.parsedRenamedMethodNames = rms.parsedRenamedMethodNames;
		this.selecteRenamedMethodIndexes = rms.indexesOfSelectedMethods;	
	}

	/**
	 * Select methods to get the indexes for selecting methods.
	 * @param outputPath
	 * @throws IOException 
	 */
	public void selectMethod() throws IOException {
		String tokensFile = inputPath + "tokens.list";
		this.sizesListOfAllMethodBodyTokenVectors = readSizes(inputPath + "sizes.csv");
		
		StringBuilder tokensBulder = new StringBuilder();
		StringBuilder methodInfoBuilder = new StringBuilder();
		StringBuilder sizesBuilder = new StringBuilder();
		StringBuilder tokensBuilderOfSelectedRenamedMethods = new StringBuilder();
		StringBuilder methodInfoBuilderOfSelectedRenamedMethods = new StringBuilder();
		StringBuilder selectedParseRenamedMethodNames = new StringBuilder();
		
		FileInputStream fis = new FileInputStream(tokensFile);
		Scanner scanner = new Scanner(fis);
		int index = -1;
		int counter = 0;
		int a = 0;
		int test = 0;
		
		while (scanner.hasNextLine()) {
			//pig:org.apache.tools.bzip2r:CRC:initialiseCRC:null:void#[tokens]
			// projectName : packageName : ClassName : methodName : arguments: ReturnType#tokens.
			String lineStr = scanner.nextLine();
			index ++;
			
//			String firstToken = this.allFirstTokensList.get(index);
//			if (this.commonFirstTokens.contains(firstToken)) {
				int sizeOfTokenVector = this.sizesListOfAllMethodBodyTokenVectors.get(index);
				
				int sharpPosition = lineStr.indexOf("#");
				String methodInfo = lineStr.substring(0, sharpPosition);
				String packageName = methodInfo.split(":")[1].toLowerCase(Locale.ROOT);
				if (packageName.contains("test") || packageName.contains("sample") || packageName.contains("example") || packageName.contains("template")) {
					test ++;
					continue;
				}
				String tokens = lineStr.substring(sharpPosition + 2, lineStr.length() - 1).replace(", ", " ");
				int renamedIndex = this.methodInfoOfRenamedMethods.indexOf(methodInfo);
				if (renamedIndex >= 0) {
					a ++;
				}
				
				if (MIN_SIZE < sizeOfTokenVector && sizeOfTokenVector <= maxSize) {
					if (renamedIndex >= 0) {
						// selected renamed methods.
						String renamedTokens = this.tokenVectorsOfRenamedMethods.get(renamedIndex);
						if (renamedTokens.equals(tokens)) {
							int index2 = this.selecteRenamedMethodIndexes.get(renamedIndex);
							if (!this.furtherSelecteRenamedMethodIndexes.contains(index2)) {
								this.furtherSelecteRenamedMethodIndexes.add(index2);
								selectedParseRenamedMethodNames.append(this.parsedRenamedMethodNames.get(renamedIndex)).append("\n");
								methodInfoBuilderOfSelectedRenamedMethods.append(methodInfo).append("\n");
								tokensBuilderOfSelectedRenamedMethods.append(renamedTokens).append("\n");
							}
							continue;
						}
					}
					
					if ("Block Block".equals(tokens)) continue;
					
					String parsedMethodName = this.tokenVectorOfAllParsedMethodNames.get(index);
					tokensBulder.append(tokens).append("\n");
					String methodInfo1 = index + "@" + methodInfo + "@"+ parsedMethodName;
					methodInfoBuilder.append(methodInfo1).append("\n");
					selectMethodIndexes.add(index);
					
					this.selectedTokenVecotrsOfAllMethods.add(tokens);
					this.selectedMethodInfoOfAllMethods.add(methodInfo1);
					sizesBuilder.append(sizeOfTokenVector).append("\n");
					counter ++;
					
					if (counter % 10000 == 0) {
						FileHelper.outputToFile(outputPath + "SelectedData/SelectedMethodTokens.txt", tokensBulder, true);
						tokensBulder.setLength(0);
						FileHelper.outputToFile(outputPath + "SelectedData/SelectedMethodInfo.txt", methodInfoBuilder, true);
						methodInfoBuilder.setLength(0);
					}
				}
//			}
		}
		scanner.close();
		fis.close();
		
		FileHelper.outputToFile(outputPath + "SelectedData/SelectedSizes.csv", sizesBuilder, false);
		FileHelper.outputToFile(outputPath + "SelectedData/SelectedMethodTokens.txt", tokensBulder, true);
		tokensBulder.setLength(0);
		FileHelper.outputToFile(outputPath + "SelectedData/SelectedMethodInfo.txt", methodInfoBuilder, true);
		methodInfoBuilder.setLength(0);

		FileHelper.outputToFile(outputPath + "RenamedMethods/MethodTokens.txt", tokensBuilderOfSelectedRenamedMethods, false);
		FileHelper.outputToFile(outputPath + "RenamedMethods/MethodInfo.txt", methodInfoBuilderOfSelectedRenamedMethods, false);
		FileHelper.outputToFile(outputPath + "RenamedMethods/ParsedMethodNames.txt", selectedParseRenamedMethodNames, false);
		System.out.println("Number of further selected renamed methods:" + furtherSelecteRenamedMethodIndexes.size());
		System.out.println("Number of selected training methods:" + this.selectedMethodInfoOfAllMethods.size());
		System.out.println("Renamed methods: " + a);
		System.out.println("Test methods: " + test);
		
		exportMethodBodies();
	}
	
	private void exportMethodBodies() throws IOException {
		String selectedMethodsPath = outputPath + "SelectedData/method_bodies.txt";
		new File(selectedMethodsPath).delete();
		
		String methodBodyFile = inputPath + "method_bodies.txt";
		FileInputStream fis = new FileInputStream(methodBodyFile);
		Scanner scanner = new Scanner(fis);
		StringBuilder singleMethod = new StringBuilder();
		StringBuilder selectedMethods = new StringBuilder();
		int index = -1;
		int counter = 0;
		boolean isMethodBody = false;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if ("#METHOD_BODY#========================".equals(line)) {
				if (isMethodBody) {
					if (this.selectMethodIndexes.contains(index)) {
						selectedMethods.append(singleMethod.toString().replace("@Override", "")).append("\n");
						if (counter % 2000 == 0) {
							FileHelper.outputToFile(selectedMethodsPath, selectedMethods, true);
							selectedMethods.setLength(0);
						}
						counter ++;
					}
				}
				singleMethod.setLength(0);
				isMethodBody = false;
				index ++;
			} else {
				if (isMethodBody) {
					singleMethod.append(line).append("\n");
				} else isMethodBody = true;
			}
			singleMethod.append(line).append("\n");
		}
		if (this.selectMethodIndexes.contains(index)) {
			selectedMethods.append(singleMethod.toString().replace("@Override", "")).append("\n");
		}
		scanner.close();
		fis.close();

		FileHelper.outputToFile(selectedMethodsPath, selectedMethods, true);
		selectedMethods.setLength(0);
		
		isMethodBody = false;
		
		// Export the method bodies of further selected renamed methods.
		String renamedMethodBodyFile = this.renamedMethodsPath + "MethodBodies.txt";
		fis = new FileInputStream(renamedMethodBodyFile);
		scanner = new Scanner(fis);
		index = -1;
		counter = 0;
		isMethodBody = false;
		singleMethod = new StringBuilder();
		StringBuilder testMethods = new StringBuilder();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if ("#METHOD_BODY#========================".equals(line)) {
				if (isMethodBody) {
					if (this.furtherSelecteRenamedMethodIndexes.contains(index)) {
						testMethods.append(singleMethod.toString().replace("@Override", "")).append("\n");
						counter ++;
					}
				}
				singleMethod.setLength(0);
				isMethodBody = false;
				index ++;
			} else {
				if (isMethodBody) {
					singleMethod.append(line).append("\n");
				} else isMethodBody = true;
			}
		}
		scanner.close();
		fis.close();
		if (this.furtherSelecteRenamedMethodIndexes.contains(index)) {
			counter ++;
			testMethods.append(singleMethod.toString().replace("@Override", "")).append("\n");
		}
		FileHelper.outputToFile(outputPath + "RenamedMethods/method_bodies/TestingMethods.java", "public class TestingMethods {\n" + testMethods.toString() + "}", false);
		System.out.println("Testing methods: " + counter);
	}

	private List<Integer> readSizes(String sizesFile) throws IOException {
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

	/**
	 * Randomly select training data and testing data.
	 * Training data: 90%.
	 * Testing data: 10%.
	 * 
	 * @param inputPath
	 * @param outputPath
	 * @throws IOException
	 */
	public void selectTrainingAndTestingData() throws IOException {
		String trainingDataPath = outputPath + "TrainingData/";
		String testingDataPath = outputPath + "TestingData/";
		String tokensFileName = "Tokens_MaxSize=" + this.maxSize + ".txt";
		String methodInfoFileName = "MethodsInfo.txt";
		
		StringBuilder trainingTokensBuilder = new StringBuilder();
		StringBuilder testingTokensBuilder = new StringBuilder();
		StringBuilder trainingInfoBuilder = new StringBuilder();
		StringBuilder testingInfoBuilder = new StringBuilder();
		
		int numberOfMethods = this.selectedMethodInfoOfAllMethods.size();
		int numberOfTrainingMethods = numberOfMethods * 90 / 100; // Training data: 90%
		numberOfTrainingMethods /= Configuration.BATCH_SIZE;
		numberOfTrainingMethods *= Configuration.BATCH_SIZE;
		int numberOfTestingMethods = numberOfMethods - numberOfTrainingMethods;
		
		Random random = new Random();
		for (int i = 0; i < numberOfMethods; i ++) {
			String tokens = this.selectedTokenVecotrsOfAllMethods.get(i);
			String methodInfo = this.selectedMethodInfoOfAllMethods.get(i);
			// Randomly select training data and testing data.
			int nextNumber = random.nextInt(10000);
			if (nextNumber < 9000) {
				if (numberOfTrainingMethods > 0) {// Training Data.
					trainingTokensBuilder.append(tokens).append("\n");
					trainingInfoBuilder.append(methodInfo).append("\n");
					trainingMethodIndexes.add(selectMethodIndexes.get(i));
				} else {// Testing data.
					testingTokensBuilder.append(tokens).append("\n");
					testingInfoBuilder.append(methodInfo).append("\n");
					testingMethodIndexes.add(selectMethodIndexes.get(i));
				}
				numberOfTrainingMethods --;
			} else {
				if (numberOfTestingMethods > 0) {// Testing data.
					testingTokensBuilder.append(tokens).append("\n");
					testingInfoBuilder.append(methodInfo).append("\n");
					testingMethodIndexes.add(selectMethodIndexes.get(i));
				} else {// Training Data.
					trainingTokensBuilder.append(tokens).append("\n");
					trainingInfoBuilder.append(methodInfo).append("\n");
					trainingMethodIndexes.add(selectMethodIndexes.get(i));
				}
				numberOfTestingMethods --;
			}
		}
		
		FileHelper.outputToFile(trainingDataPath + tokensFileName, trainingTokensBuilder, true);
		trainingTokensBuilder.setLength(0);
		FileHelper.outputToFile(testingDataPath + tokensFileName, testingTokensBuilder, true);
		testingTokensBuilder.setLength(0);
		FileHelper.outputToFile(trainingDataPath + methodInfoFileName, trainingInfoBuilder, true);
		trainingInfoBuilder.setLength(0);
		FileHelper.outputToFile(testingDataPath + methodInfoFileName, testingInfoBuilder, true);
		testingInfoBuilder.setLength(0);
		
		System.out.println("Number of selected methods:" + numberOfMethods);
		System.out.println("Number of selected methods:" + selectMethodIndexes.size());
		System.out.println("Number of selected methods:" + trainingMethodIndexes.size() + "==" + testingMethodIndexes.size());
	}

	public void exportTrainingAndTestingMethodBodies() throws IOException {
		String trainingDataPath = outputPath + "TrainingData/method_bodies.txt";
		String testingDataPath = outputPath + "TestingData/method_bodies.txt";
		String selectedMethodsPath = outputPath + "SelectedData/method_bodies.txt";
		
		String methodBodyFile = inputPath + "method_bodies.txt";
		FileInputStream fis = new FileInputStream(methodBodyFile);
		Scanner scanner = new Scanner(fis);
		StringBuilder singleMethod = new StringBuilder();
		StringBuilder trainingMethods = new StringBuilder();
		StringBuilder testingMethods = new StringBuilder();
		StringBuilder selectedMethods = new StringBuilder();
		int index = -1;
		boolean isMethodBody = false;
//		String methodFile1 = "";
//		String methodFile2 = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if ("#METHOD_BODY#========================".equals(line)) {
//				if (isMethodBody) {
				if (singleMethod.length() > 0) {
					if (trainingMethodIndexes.contains(index)) {
						trainingMethods.append(singleMethod).append("\n");
						selectedMethods.append(singleMethod).append("\n");//.toString().replace("@Override", "")
					} else if (testingMethodIndexes.contains(index)) {
						testingMethods.append(singleMethod).append("\n");
						selectedMethods.append(singleMethod).append("\n");//.toString().replace("@Override", "")
					}
//					if (selectMethodIndexes.contains(index)) {
//						selectedMethods.append(singleMethod).append("\n");//.toString().replace("@Override", "")
//					}
					if (index % 10000 == 0) {
						FileHelper.outputToFile(trainingDataPath, trainingMethods, true);
						FileHelper.outputToFile(testingDataPath, testingMethods, true);
						FileHelper.outputToFile(selectedMethodsPath, selectedMethods, true);
						trainingMethods.setLength(0);
						testingMethods.setLength(0);
						selectedMethods.setLength(0);
						Date date = new Date();
						System.out.println(dateFormat.format(date) + "===" + index); //2016/11/16 12:08:43
					}
				}
				singleMethod.setLength(0);
				isMethodBody = false;
				index ++;
//			} else {
//				if (isMethodBody) {
////					singleMethod.append(line).append("\n");
//				} else {
//					//hibernate-metamodelgen:org.hibernate.jpamodelgen.util:StringUtil:
////					String[] elements = line.split(":");
////					methodFile2 = elements[0];// + "/" + elements[1].replace(".", "") + elements[2];
////					if (methodFile1.equals("")) methodFile1 = methodFile2;
////					else if (!methodFile1.equals(methodFile2)) { // output to file.
////						if (trainingMethods.length() > 0) FileHelper.outputToFile(trainingDataPath + methodFile1 + ".java", "public class MethodBody {\n" + trainingMethods.toString() + "}", true);
////						if (testingMethods.length() > 0) FileHelper.outputToFile(testingDataPath + methodFile1 + ".java", "public class MethodBody {\n" + testingDataPath.toString() + "}", true);
////						FileHelper.outputToFile(selectedMethodsPath + methodFile1 + "/TrainingMethods.java", "public class TrainingMethods {\n" + selectedMethods.toString() + "}", true);
////						trainingMethods.setLength(0);
////						testingMethods.setLength(0);
////						selectedMethods.setLength(0);
////						methodFile1 = methodFile2;
////					}
//					isMethodBody = true;
//				}
			}
			singleMethod.append(line).append("\n");
		}
		if (trainingMethodIndexes.contains(index)) {
			trainingMethods.append(singleMethod).append("\n");
			selectedMethods.append(singleMethod).append("\n");
		} else if (testingMethodIndexes.contains(index)) {
			testingMethods.append(singleMethod).append("\n");
			selectedMethods.append(singleMethod).append("\n");
		}
//		if (selectMethodIndexes.contains(index)) {
//			selectedMethods.append(singleMethod).append("\n");//.toString().replace("@Override", "")
//		}
		scanner.close();
		fis.close();

//		FileHelper.outputToFile(trainingDataPath + methodFile2 + ".java", "public class MethodBody {\n" + trainingMethods.toString() + "}", true);
//		FileHelper.outputToFile(testingDataPath + methodFile2 + ".java", "public class MethodBody {\n" + testingDataPath.toString() + "}", true);
//		FileHelper.outputToFile(selectedMethodsPath +  methodFile2 + "/TrainingMethods.java", "public class TrainingMethods {\n" + selectedMethods.toString() + "}", true);
		FileHelper.outputToFile(trainingDataPath, trainingMethods, true);
		FileHelper.outputToFile(testingDataPath, testingMethods, true);
		FileHelper.outputToFile(selectedMethodsPath, selectedMethods, true);
		trainingMethods.setLength(0);
		testingMethods.setLength(0);
		selectedMethods.setLength(0);
		
		
		// Export the method bodies of further selected renamed methods.
		String renamedMethodBodyFile = this.renamedMethodsPath + "MethodBodies.txt";
		fis = new FileInputStream(renamedMethodBodyFile);
		scanner = new Scanner(fis);
		index = -1;
		int counter = 0;
		isMethodBody = false;
		singleMethod = new StringBuilder();
		StringBuilder testMethods = new StringBuilder();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if ("#METHOD_BODY#========================".equals(line)) {
				if (isMethodBody) {
					if (this.furtherSelecteRenamedMethodIndexes.contains(index)) {
						testMethods.append(singleMethod.toString().replace("@Override", "")).append("\n");
						counter ++;
					}
				}
				singleMethod.setLength(0);
				isMethodBody = false;
				index ++;
			} else {
				if (isMethodBody) {
					singleMethod.append(line).append("\n");
				} else isMethodBody = true;
			}
		}
		scanner.close();
		fis.close();
		if (this.furtherSelecteRenamedMethodIndexes.contains(index)) {
			counter ++;
			testMethods.append(singleMethod.toString().replace("@Override", "")).append("\n");
		}
		FileHelper.outputToFile(outputPath + "RenamedMethods/method_bodies/TestingMethods.java", "public class TestingMethods {\n" + testMethods.toString() + "}", false);
		System.out.println("Testing methods: " + counter);
	}
	
}
