package edu.lu.uni.serval.dlMethods.liveStudy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import edu.lu.uni.Configuration;
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
	
	public int QUANTITY = 5000;// or 500, the number of methods of which names start with the same token.
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

	// selected token vectors of all methods.
	List<String> selectedTokenVecotrsOfAllMethods = new ArrayList<>();
	// Selected method info of all methods.
	List<String>  selectedMethodInfoOfAllMethods = new ArrayList<>();
	
	List<Integer> selectMethodIndexes = new ArrayList<>();
	List<Integer> trainingMethodIndexes = new ArrayList<>();
	List<Integer> testingMethodIndexes = new ArrayList<>();
	
	public void initializeData1() throws IOException {
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
		this.sizesListOfAllMethodBodyTokenVectors = readSizes(inputPath + "sizes.csv");
		maxSize = Distribution.computeMaxSize(MaxSizeType.UpperWhisker, this.sizesListOfAllMethodBodyTokenVectors);
		if (maxSize % 2 != 0) {
			maxSize += 1;
		}
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
//		boolean isMethodBody = false;
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
//				isMethodBody = false;
				index ++;
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
		scanner.close();
		fis.close();

		FileHelper.outputToFile(trainingDataPath, trainingMethods, true);
		FileHelper.outputToFile(testingDataPath, testingMethods, true);
		FileHelper.outputToFile(selectedMethodsPath, selectedMethods, true);
		trainingMethods.setLength(0);
		testingMethods.setLength(0);
		selectedMethods.setLength(0);
	}
	
}
