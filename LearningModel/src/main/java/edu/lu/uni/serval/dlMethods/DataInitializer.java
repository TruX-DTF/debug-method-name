package edu.lu.uni.serval.dlMethods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import edu.lu.uni.Configuration;
import edu.lu.uni.serval.dlMethods.DataPrepare.CommonFirstTokens;
import edu.lu.uni.serval.dlMethods.DataPrepare.RenamedMethodSelector;
import edu.lu.uni.serval.utils.Distribution;
import edu.lu.uni.serval.utils.Distribution.MaxSizeType;
import edu.lu.uni.serval.utils.FileHelper;

/**
 * Figure out the common first tokens of all method names.
 * Select the methods of which method names start with these common first tokens.
 * 
 * @author kui.liu
 *
 */
public class DataInitializer {
	
	public int QUANTITY = 1;// or 500, the number of methods of which names start with the same token.
	public int MIN_SIZE = 0;    // The minimum size of vectors.
	
	public String inputPath;
	public String outputPath1;
	public String outputPath2;
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
		// Common first tokens of all methods.
		CommonFirstTokens cft = new CommonFirstTokens();
		cft.inputPath = this.inputPath;
		cft.outputPath = this.outputPath1;
		cft.QUANTITY = this.QUANTITY;
		// Read the distribution of first tokens to get the common first tokens.
		cft.readTokens();
		// Select common first tokens.
		cft.outputTokens();
		this.allFirstTokensList = cft.allFirstTokensList;
		this.tokenVectorOfAllParsedMethodNames = cft.tokenVectorOfAllParsedMethodNames;
		this.commonFirstTokens = cft.commonFirstTokens;
		
		
		File sizesFile = new File(Configuration.TOKENIZED_METHODS_PATH + "sizes.csv");
		if (!sizesFile.exists()) {
			// Merge sizes files.
			List<String> projects = DataPreparer.readProjects();
			for (String project : projects) {
				String sizeFile = Configuration.TOKENIZED_METHODS_PATH + project + "/sizes.csv";
				if (! new File(sizeFile).exists()) continue;
				FileHelper.outputToFile(sizesFile, FileHelper.readFile(sizeFile), true);
			}
		}
		
		// Get the threshold of sizes of method token vectors
		List<Integer> sizesList = readSizes(sizesFile);
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
		this.parsedRenamedMethodNames = rms.parsedRenamedMethodNames; // oldName@newName
		this.selecteRenamedMethodIndexes = rms.indexesOfSelectedMethods;	
	}

	/**
	 * Select methods to get the indexes for selecting methods.
	 * @param outputPath
	 * @throws IOException 
	 */
	public void selectMethod() throws IOException {
		String tokensFile = inputPath + "tokens.txt";
		this.sizesListOfAllMethodBodyTokenVectors = readSizes(new File(inputPath + "sizes.csv"));
		
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
				String tokens = lineStr.substring(sharpPosition + 1, lineStr.length() - 1).replace(", ", " ");
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
						FileHelper.outputToFile(outputPath1 + "SelectedMethodTokens.txt", tokensBulder, true);
						tokensBulder.setLength(0);
						FileHelper.outputToFile(outputPath1 + "SelectedMethodInfo.txt", methodInfoBuilder, true);
						methodInfoBuilder.setLength(0);
					}
				}
//			}
		}
		scanner.close();
		fis.close();
		
		FileHelper.outputToFile(outputPath1 + "SelectedSizes.csv", sizesBuilder, false);
		FileHelper.outputToFile(outputPath1 + "SelectedMethodTokens.txt", tokensBulder, true);
		tokensBulder.setLength(0);
		FileHelper.outputToFile(outputPath1 + "SelectedMethodInfo.txt", methodInfoBuilder, true);
		methodInfoBuilder.setLength(0);
		
		File tokensFile_ = new File(Configuration.SELECTED_DATA_PATH + "TrainingData/Tokens_MaxSize=" + this.maxSize + ".txt");
		if (!tokensFile_.exists()) FileHelper.outputToFile(tokensFile_, "", false);

		FileHelper.outputToFile(outputPath2 + "MethodTokens.txt", tokensBuilderOfSelectedRenamedMethods, false);
		FileHelper.outputToFile(outputPath2 + "MethodInfo.txt", methodInfoBuilderOfSelectedRenamedMethods, false);
		FileHelper.outputToFile(outputPath2 + "ParsedMethodNames.txt", selectedParseRenamedMethodNames, false);
		System.out.println("Number of further selected renamed methods:" + furtherSelecteRenamedMethodIndexes.size());
		System.out.println("Number of selected training methods:" + this.selectedMethodInfoOfAllMethods.size());
		System.out.println("Renamed methods: " + a);
		System.out.println("Test methods: " + test);
		
		exportMethodBodies();
	}
	
	private void exportMethodBodies() throws IOException {
		String selectedMethodsPath = outputPath1 + "method_bodies.txt";
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
		FileHelper.outputToFile(outputPath2 + "method_bodies/TestingMethods.java", "public class TestingMethods {\n" + testMethods.toString() + "}", false);
		System.out.println("Testing methods: " + counter);
	}

	private List<Integer> readSizes(File sizesFile) throws IOException {
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

}
