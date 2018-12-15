package edu.lu.uni.serval.sricmn;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import edu.lu.uni.serval.Configuration;
import edu.lu.uni.serval.sricmn.info.MethodInfo;
import edu.lu.uni.serval.sricmn.info.PredictToken;
import edu.lu.uni.serval.utils.Evaluation;
import edu.lu.uni.serval.utils.MapSorter;
import edu.lu.uni.serval.utils.SynonymsFinder;

public class DetectMethodNames {
	
	/**
	 * Evaluate the performance of identifying inconsistent method names.
	 * 
	 * @param renamedOldMethodInfo
	 * @param renamedNewMethodInfo
	 * @param trainingMethodInfo
	 * @param topSimilarBodyMap
	 * @param topSimilarMethodNameMap
	 * @param labels
	 * @param topNum
	 */
	public void evaluate(List<MethodInfo> renamedOldMethodInfo, List<MethodInfo> renamedNewMethodInfo, List<MethodInfo> trainingMethodInfo, 
			Map<Integer, Map<Integer, Double>> topSimilarBodyMap, Map<Integer, Map<Integer, Double>> topSimilarMethodNameMap, List<Integer> labels,
			int topNum, boolean considerSynonyms, boolean needAllSynonyms) {
		int size = renamedOldMethodInfo.size();
		int falseNegatives = 0;
		int trueNegatives = 0;
		int falsePositives = 0;
		int truePositives = 0;
		
		for (int index = 0; index < size; index ++) {
			Map<Integer, Double> similarities = topSimilarBodyMap.get(index);
			if (similarities.size() == 0) {
				continue;
			}
			MapSorter<Integer, Double> sorter = new MapSorter<Integer, Double>();
			similarities = sorter.sortByValueDescending(similarities);
			int label = labels.get(index);
			
			List<String> tokensPredictedByBody = new ArrayList<>();// used to identify inconsistent method names
			List<String> tokensPredictedByName = new ArrayList<>();// used to identify inconsistent method names
			int counter = 0;
			for (Map.Entry<Integer, Double> entry : similarities.entrySet()) {
				int methodIndex = entry.getKey();
				MethodInfo methodInfo = trainingMethodInfo.get(methodIndex);
				List<String> similarRawTokens = methodInfo.getMethodNameTokens();
				String similarToken = similarRawTokens.get(0);
				if (!tokensPredictedByBody.contains(similarToken)) {
					tokensPredictedByBody.add(similarToken);
				}
				
				counter ++;
				if (counter == topNum) break;
			}
			
			Map<Integer, Double> methodNameMap = topSimilarMethodNameMap.get(index);
			methodNameMap = sorter.sortByValueDescending(methodNameMap);//isDescending ? sorter.sortByValueDescending(methodNameMap) : sorter.sortByValueAscending(methodNameMap);
			for (Map.Entry<Integer, Double> entry : methodNameMap.entrySet()) {
				int methodIndex = entry.getKey();
				MethodInfo methodInfo = trainingMethodInfo.get(methodIndex);
				String token = methodInfo.getMethodNameTokens().get(0);
				if (!tokensPredictedByName.contains(token)) {
					tokensPredictedByName.add(token);
				}
				counter --;
				if (counter == 0) break;
			}
			
			if (considerSynonyms) {
				tokensPredictedByBody = findSynonyms(tokensPredictedByBody, needAllSynonyms);
				tokensPredictedByName = findSynonyms(tokensPredictedByName, needAllSynonyms);
			}
			
			tokensPredictedByName.retainAll(tokensPredictedByBody);
			if (tokensPredictedByName.isEmpty()) {// spot as inconsistent method names, positive.
				if (label == 0) {// actual result: inconsistent
					truePositives ++;
				} else {// actual result: consistent
					falsePositives ++;
				}
			} else {// spot as consistent method names, negative.
				if (label == 0) {// actual result: inconsistent
					falseNegatives ++;
				} else { // actual result: consistent
					trueNegatives ++;
				}
			}
		}
		
		System.out.println("=======Identify inconsistent methods====== With Synonyms: " + (considerSynonyms ? "Yes." : "No."));
		System.out.println(" TP TN FN FP ");
		System.out.println(truePositives + " " + trueNegatives + " " + falseNegatives + " " + falsePositives);
		Evaluation eval = new Evaluation(truePositives, trueNegatives, falsePositives, falseNegatives);
		eval.evaluate();
		System.out.println("------------------------------------------");
	}
	
	private List<String> findSynonyms(List<String> tokens, boolean needAllSynonyms) {
		List<String> tokensCopy = new ArrayList<>();
		tokensCopy.addAll(tokens);
		for (String token : tokensCopy) {
			tokens.addAll(findSynonyms(token, needAllSynonyms));
		}
		tokens = tokens.stream().distinct().collect(Collectors.toList());
		return tokens;
	}

	private List<String> findSynonyms(String token, boolean needAllSynonyms) {
		SynonymsFinder finder = new SynonymsFinder();
		finder.setNeedAllSynonyms(needAllSynonyms);
		finder.setPropertyOfWordNet(Configuration.WORDNET_PATH);
		finder.retrieveSynonyms(token);
		List<String> sysnonyms = finder.getSynonyms();
		sysnonyms.add(token);
		if ("init".equals(token)) {
			sysnonyms.add("initilize");
			sysnonyms.add("initilise");
			sysnonyms.add("initilization");
			sysnonyms = sysnonyms.stream().distinct().collect(Collectors.toList());
		} else if ("is".equals(token) || "am".equals(token) || "was".equals(token)
				|| "are".equals(token) || "were".equals(token) || "be".equals(token) || "been".equals(token)) {
			sysnonyms.add("is");
			sysnonyms.add("was");
			sysnonyms.add("am");
			sysnonyms.add("are");
			sysnonyms.add("were");
			sysnonyms.add("be");
			sysnonyms.add("been");
			sysnonyms = sysnonyms.stream().distinct().collect(Collectors.toList());
		}
		return sysnonyms;
	}

	/**
	 * Evaluate the performance of suggesting new names for inconsistent method names.
	 * 
	 * @param renamedOldMethodInfo
	 * @param renamedNewMethodInfo
	 * @param trainingMethodInfo
	 * @param topSimilarBodyMap
	 * @param topSimilarMethodNameMap
	 * @param numOfPredictTokens
	 * @param labels
	 * @param topNum
	 */
	public void evaluate1(List<MethodInfo> renamedOldMethodInfo, List<MethodInfo> renamedNewMethodInfo, List<MethodInfo> trainingMethodInfo, 
			Map<Integer, Map<Integer, Double>> topSimilarBodyMap, Map<Integer, Map<Integer, Double>> topSimilarMethodNameMap, int numOfPredictTokens, 
			List<Integer> labels, int topNum, boolean considerSynonyms, boolean needAllSynonyms) {
		int oldPredictedNum1 = 0;
		int newPredictedNum1 = 0;
		int oldPredictedNum1_1 = 0;
		int newPredictedNum1_1 = 0;
		int oldPredictedNum2 = 0;
		int newPredictedNum2 = 0;
		int oldPredictedNum2_1 = 0;
		int newPredictedNum2_1 = 0;
		int oldPredictedNum3 = 0;
		int newPredictedNum3 = 0;
		int oldPredictedNum3_1 = 0;
		int newPredictedNum3_1 = 0;
		int oldPredictedNum1_5 = 0;
		int newPredictedNum1_5 = 0;
		int oldPredictedNum1_1_5 = 0;
		int newPredictedNum1_1_5 = 0;
		int oldPredictedNum2_5 = 0;
		int newPredictedNum2_5 = 0;
		int oldPredictedNum2_1_5 = 0;
		int newPredictedNum2_1_5 = 0;
		int oldPredictedNum3_5 = 0;
		int newPredictedNum3_5 = 0;
		int oldPredictedNum3_1_5 = 0;
		int newPredictedNum3_1_5 = 0;
		int size = renamedOldMethodInfo.size();
		int falseNegatives = 0;
		int trueNegatives = 0;
		int falsePositives = 0;
		int truePositives = 0;
		int numOfFullNames1 = 0;
		int numOfFullNames1_5 = 0;
		int numOfFullNames2 = 0;
		int numOfFullNames2_5 = 0;
		int numOfFullNames3 = 0;
		int numOfFullNames3_5 = 0;
		int numOfFullNames1_1 = 0;
		int numOfFullNames1_1_5 = 0;
		int numOfFullNames2_1 = 0;
		int numOfFullNames2_1_5 = 0;
		int numOfFullNames3_1 = 0;
		int numOfFullNames3_1_5 = 0;
		
		for (int index = 0; index < size; index ++) {
			Map<Integer, Double> similarities = topSimilarBodyMap.get(index);
			if (similarities.size() == 0) {
				continue;
			}
			MapSorter<Integer, Double> sorter = new MapSorter<Integer, Double>();
			similarities = sorter.sortByValueDescending(similarities);
			int label = labels.get(index);
			
			List<PredictToken> ptList = new ArrayList<>(); // used to recommend method names.
			List<String> tokensPredictedByBody = new ArrayList<>();// used to identify inconsistent method names
			List<String> tokensPredictedByName = new ArrayList<>();// used to identify inconsistent method names
			int counter = 0;
			for (Map.Entry<Integer, Double> entry : similarities.entrySet()) {
				int methodIndex = entry.getKey();
				Double similarity = entry.getValue();
				MethodInfo methodInfo = trainingMethodInfo.get(methodIndex);
				List<String> similarRawTokens = methodInfo.getMethodNameTokens();
				// 1 to numOfPredictTokens raw tokens
				StringBuilder token = new StringBuilder();
				for (int i = 0; i < numOfPredictTokens; i ++) {
					token.append(similarRawTokens.get(i)).append("_");
					if (similarRawTokens.size() <= i + 1) break;
				}
				
				PredictToken pt = new PredictToken(token.toString().toLowerCase());
				Integer index1 = ptList.indexOf(pt);
				if (index1 > -1) {
					pt = ptList.get(index1);
					pt.setSimilarity(pt.getSimilarity() + similarity);
					pt.setTimes(pt.getTimes() + 1);
				} else {
					pt.setSimilarity(similarity);
					pt.setTimes(1);
					ptList.add(pt);
				}
				
				if (tokensPredictedByBody.isEmpty()) tokensPredictedByBody.add(similarRawTokens.get(0));
				
				counter ++;
				if (counter >= topNum) break;
			}
			
			List<String> oldRawTokens = renamedOldMethodInfo.get(index).getMethodNameTokens();
			List<String> newRawTokens = renamedNewMethodInfo.get(index).getMethodNameTokens();
			StringBuilder oldOriginalToken = new StringBuilder();
			StringBuilder newOriginalToken = new StringBuilder();
			String newMethodName = readMethodName(newRawTokens);
			for (int i = 0; i < numOfPredictTokens; i ++) {
				oldOriginalToken.append(oldRawTokens.get(i)).append("_");
				if (oldRawTokens.size() <= i + 1) break;
			}
			for (int i = 0; i < numOfPredictTokens; i ++) {
				newOriginalToken.append(newRawTokens.get(i)).append("_");
				if (newRawTokens.size() <= i + 1) break;
			}
			
			boolean isTrue = false;
			Map<Integer, Double> methodNameMap = topSimilarMethodNameMap.get(index);
			methodNameMap = sorter.sortByValueDescending(methodNameMap);//isDescending ? sorter.sortByValueDescending(methodNameMap) : sorter.sortByValueAscending(methodNameMap);
			for (Map.Entry<Integer, Double> entry : methodNameMap.entrySet()) {
				int methodIndex = entry.getKey();
				MethodInfo methodInfo = trainingMethodInfo.get(methodIndex);
				String token = methodInfo.getMethodNameTokens().get(0);
				if (tokensPredictedByName.isEmpty()) tokensPredictedByName.add(token);
				counter --;
				if (counter <= 0) break;
			}
			
			for (PredictToken pt : ptList) {
				pt.setSimilarity(pt.getSimilarity() / pt.getTimes());
			}
			
			TokenSuggestor ts = new TokenSuggestor();
			List<String> predictTokens1 = ts.selectTop5PredictTokens1(ptList); 
			List<String> predictTokens2 = ts.selectTop5PredictTokens2(ptList);
			List<String> predictTokens3 = ts.selectTop5PredictTokens3(ptList);
			String predictToken1 = predictTokens1.get(0);
			String predictToken2 = predictTokens2.get(0);
			String predictToken3 = predictTokens3.get(0);
			String oldToken = oldOriginalToken.toString().toLowerCase(Locale.ROOT);
			String newToken = newOriginalToken.toString().toLowerCase(Locale.ROOT);
			
			if (considerSynonyms) {
				tokensPredictedByBody = findSynonyms(tokensPredictedByBody.get(0), needAllSynonyms);
				tokensPredictedByName = findSynonyms(tokensPredictedByName.get(0), needAllSynonyms);
			}
			
			tokensPredictedByName.retainAll(tokensPredictedByBody);
			if (tokensPredictedByName.isEmpty()) {// spot as inconsistent method names, positive.
				if (label == 0) {// actual result: inconsistent
					truePositives ++;
				} else {// actual result: consistent
					falsePositives ++;
				}
				isTrue = true;
			} else {// spot as consistent method names, negative.
				if (label == 0) {// actual result: inconsistent
					falseNegatives ++;
				} else { // actual result: consistent
					trueNegatives ++;
				}
			}

			if (considerSynonyms) {
				String[] newTokensArray = newToken.split("_");
				String[] oldTokensArray = oldToken.split("_");
				int predictResult = identifyPredictedResult(newTokensArray, oldTokensArray, predictToken1, needAllSynonyms);
				if (predictResult == 1) {
					newPredictedNum1 ++;
					if (isTrue) newPredictedNum1_1 ++;
				} else if (predictResult == 2) {
					oldPredictedNum1 ++;
					if (isTrue) oldPredictedNum1_1 ++;
				}
				predictResult = identifyPredictedResult(newTokensArray, oldTokensArray, predictToken2, needAllSynonyms);
				if (predictResult == 1) {
					newPredictedNum2 ++;
					if (isTrue) newPredictedNum2_1 ++;
				} else if (predictResult == 2) {
					oldPredictedNum2 ++;
					if (isTrue) oldPredictedNum2_1 ++;
				}
				predictResult = identifyPredictedResult(newTokensArray, oldTokensArray, predictToken3, needAllSynonyms);
				if (predictResult == 1) {
					newPredictedNum3 ++;
					if (isTrue) newPredictedNum3_1 ++;
				} else if (predictResult == 2) {
					oldPredictedNum3 ++;
					if (isTrue) oldPredictedNum3_1 ++;
				}

				predictResult = identifyPredictedResults(newTokensArray, oldTokensArray, predictTokens1, needAllSynonyms);
				if (predictResult == 1) {
					newPredictedNum1_5 ++;
					if (isTrue) newPredictedNum1_1_5 ++;
				} else if (predictResult == 2) {
					oldPredictedNum1_5 ++;
					if (isTrue) oldPredictedNum1_1_5 ++;
				}
				predictResult = identifyPredictedResults(newTokensArray, oldTokensArray, predictTokens2, needAllSynonyms);
				if (predictResult == 1) {
					newPredictedNum2_5 ++;
					if (isTrue) newPredictedNum2_1_5 ++;
				} else if (predictResult == 2) {
					oldPredictedNum2_5 ++;
					if (isTrue) oldPredictedNum2_1_5 ++;
				}
				predictResult = identifyPredictedResults(newTokensArray, oldTokensArray, predictTokens3, needAllSynonyms);
				if (predictResult == 1) {
					newPredictedNum3_5 ++;
					if (isTrue) newPredictedNum3_1_5 ++;
				} else if (predictResult == 2) {
					oldPredictedNum3_5 ++;
					if (isTrue) oldPredictedNum3_1_5 ++;
				}
				
				String[] fullNameTokens = newMethodName.split("_");
				predictResult = identifyPredictedResult(fullNameTokens, oldTokensArray, predictToken1, needAllSynonyms);
				if (predictResult == 1) {
					numOfFullNames1 ++;
					if (isTrue) numOfFullNames1_1 ++;
				}
				predictResult = identifyPredictedResult(fullNameTokens, oldTokensArray, predictToken2, needAllSynonyms);
				if (predictResult == 1) {
					numOfFullNames2 ++;
					if (isTrue) numOfFullNames2_1 ++;
				}
				predictResult = identifyPredictedResult(fullNameTokens, oldTokensArray, predictToken3, needAllSynonyms);
				if (predictResult == 1) {
					numOfFullNames3 ++;
					if (isTrue) numOfFullNames3_1 ++;
				}
				predictResult = identifyPredictedResults(fullNameTokens, oldTokensArray, predictTokens1, needAllSynonyms);
				if (predictResult == 1) {
					numOfFullNames1_5 ++;
					if (isTrue) numOfFullNames1_1_5 ++;
				}
				predictResult = identifyPredictedResults(fullNameTokens, oldTokensArray, predictTokens2, needAllSynonyms);
				if (predictResult == 1) {
					numOfFullNames2_5 ++;
					if (isTrue) numOfFullNames2_1_5 ++;
				}
				predictResult = identifyPredictedResults(fullNameTokens, oldTokensArray, predictTokens3, needAllSynonyms);
				if (predictResult == 1) {
					numOfFullNames3_5 ++;
					if (isTrue) numOfFullNames3_1_5 ++;
				}
			} else {
				if (!predictToken1.equalsIgnoreCase(oldToken)) {
					oldPredictedNum1 ++;
					if (isTrue) oldPredictedNum1_1 ++;
				}
				if (!predictToken2.equalsIgnoreCase(oldToken)) {
					oldPredictedNum2 ++;
					if (isTrue) oldPredictedNum2_1 ++;
				}
				if (!predictToken3.equalsIgnoreCase(oldToken)) {
					oldPredictedNum3 ++;
					if (isTrue) oldPredictedNum3_1 ++;
				}
				if (!predictTokens1.contains(oldToken)) {
					oldPredictedNum1_5 ++;
					if (isTrue) oldPredictedNum1_1_5 ++;
				}
				if (!predictTokens2.contains(oldToken)) {
					oldPredictedNum2_5 ++;
					if (isTrue) oldPredictedNum2_1_5 ++;
				}
				if (!predictTokens3.contains(oldToken)) {
					oldPredictedNum3_5 ++;
					if (isTrue) oldPredictedNum3_1_5 ++;
				}
				if (predictToken1.contains(newToken)) {
					newPredictedNum1 ++;
					if (isTrue) newPredictedNum1_1 ++;
				}
				if (predictToken2.contains(newToken)) {
					newPredictedNum2 ++;
					if (isTrue) newPredictedNum2_1 ++;
				}
				if (predictToken3.contains(newToken)) {
					newPredictedNum3 ++;
					if (isTrue) newPredictedNum3_1 ++;
				}
				if (predictTokens1.contains(newToken)) {
					newPredictedNum1_5 ++;
					if (isTrue) newPredictedNum1_1_5 ++;
				}
				if (predictTokens2.contains(newToken)) {
					newPredictedNum2_5 ++;
					if (isTrue) newPredictedNum2_1_5 ++;
				}
				if (predictTokens3.contains(newToken)) {
					newPredictedNum3_5 ++;
					if (isTrue) newPredictedNum3_1_5 ++;
				}
				if (predictToken1.contains(newMethodName)) {
					numOfFullNames1 ++;
					if (isTrue) numOfFullNames1_1 ++;
				}
				if (predictToken2.contains(newMethodName)) {
					numOfFullNames2 ++;
					if (isTrue) numOfFullNames2_1 ++;
				}
				if (predictToken3.contains(newMethodName)) {
					numOfFullNames3 ++;
					if (isTrue) numOfFullNames3_1 ++;
				}
				if (predictTokens1.contains(newMethodName)) {
					numOfFullNames1_5 ++;
					if (isTrue) numOfFullNames1_1_5 ++;
				}
				if (predictTokens2.contains(newMethodName)) {
					numOfFullNames2_5 ++;
					if (isTrue) numOfFullNames2_1_5 ++;
				}
				if (predictTokens3.contains(newMethodName)) {
					numOfFullNames3_5 ++;
					if (isTrue) numOfFullNames3_1_5 ++;
				}
			}
		}
		
		System.out.println("=======Identify inconsistent methods====== With Synonyms: " + (considerSynonyms ? "Yes." : "No."));
		System.out.println(" TP TN FN FP ");
		System.out.println(truePositives + " " + trueNegatives + " " + falseNegatives + " " + falsePositives);
		edu.lu.uni.serval.utils.Evaluation eval = new edu.lu.uni.serval.utils.Evaluation(truePositives, trueNegatives, falsePositives, falseNegatives);
		eval.evaluate();
		System.out.println("------------------------------------------");
		
		int size2 = truePositives + trueNegatives;// Number of Identified inconsistent method names.
		int size3 = falsePositives + falseNegatives;
		int size4 = truePositives + falsePositives;
		final DecimalFormat FORMAT = new DecimalFormat("0.000");
		System.out.println("------------Top_1_oldName R2---------------");
		System.out.println("Accuracy: " + FORMAT.format(((double)oldPredictedNum1_1 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(oldPredictedNum1_1, oldPredictedNum1 - oldPredictedNum1_1, size2 - oldPredictedNum1_1, size3 - (oldPredictedNum1 - oldPredictedNum1_1));
		eval.evaluate();
		System.out.println("------------Top_1_oldName R3---------------");
		System.out.println("Accuracy: " + FORMAT.format(((double)oldPredictedNum2_1 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(oldPredictedNum2_1, oldPredictedNum2 - oldPredictedNum2_1, size2 - oldPredictedNum2_1, size3 - (oldPredictedNum2 - oldPredictedNum2_1));
		eval.evaluate();
		System.out.println("------------Top_1_oldName R4---------------");
		System.out.println("Accuracy: " + FORMAT.format(((double)oldPredictedNum3_1 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(oldPredictedNum3_1, oldPredictedNum3 - oldPredictedNum3_1, size2 - oldPredictedNum3_1, size3 - (oldPredictedNum3 - oldPredictedNum3_1));
		eval.evaluate();
		System.out.println("------------Top_5_oldName R2---------------");
		System.out.println("Accuracy: " + FORMAT.format(((double)oldPredictedNum1_1_5 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(oldPredictedNum1_1_5, oldPredictedNum1_5 - oldPredictedNum1_1_5, size2 - oldPredictedNum1_1_5, size3 - (oldPredictedNum1_5 - oldPredictedNum1_1_5));
		eval.evaluate();
		System.out.println("------------Top_5_oldName R3---------------");
		System.out.println("Accuracy: " + FORMAT.format(((double)oldPredictedNum2_1_5 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(oldPredictedNum2_1_5, oldPredictedNum2_5 - oldPredictedNum2_1_5, size2 - oldPredictedNum2_1_5, size3 - (oldPredictedNum2_5 - oldPredictedNum2_1_5));
		eval.evaluate();
		System.out.println("------------Top_5_oldName R4---------------");
		System.out.println("Accuracy: " + FORMAT.format(((double)oldPredictedNum3_1_5 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(oldPredictedNum3_1_5, oldPredictedNum3_5 - oldPredictedNum3_1_5, size2 - oldPredictedNum3_1_5, size3 - (oldPredictedNum3_5 - oldPredictedNum3_1_5));
		eval.evaluate();
		
		System.out.println("------------Top_1_newName R2---------------");
		System.out.println("Accuracy: " + FORMAT.format(((double)newPredictedNum1_1 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(newPredictedNum1_1, newPredictedNum1 - newPredictedNum1_1, size2 - newPredictedNum1_1, size3 - (newPredictedNum1 - newPredictedNum1_1));
		eval.evaluate();
		System.out.println("------------Top_1_newName R3---------------");
		System.out.println("Accuracy: " + FORMAT.format(((double)newPredictedNum2_1 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(newPredictedNum2_1, newPredictedNum2 - newPredictedNum2_1, size2 - newPredictedNum2_1, size3 - (newPredictedNum2 - newPredictedNum2_1));
		eval.evaluate();
		System.out.println("------------Top_1_newName R4---------------");
		System.out.println("Accuracy: " + FORMAT.format(((double)newPredictedNum3_1 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(newPredictedNum3_1, newPredictedNum3 - newPredictedNum3_1, size2 - newPredictedNum3_1, size3 - (newPredictedNum3 - newPredictedNum3_1));
		eval.evaluate();
		System.out.println("------------Top_5_newName R2---------------");
		System.out.println("Accuracy: " + FORMAT.format(((double)newPredictedNum1_1_5 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(newPredictedNum1_1_5, newPredictedNum1_5 - newPredictedNum1_1_5, size2 - newPredictedNum1_1_5, size3 - (newPredictedNum1_5 - newPredictedNum1_1_5));
		eval.evaluate();
		System.out.println("------------Top_5_newName R3---------------");
		System.out.println("Accuracy: " + FORMAT.format(((double)newPredictedNum2_1_5 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(newPredictedNum2_1_5, newPredictedNum2_5 - newPredictedNum2_1_5, size2 - newPredictedNum2_1_5, size3 - (newPredictedNum2_5 - newPredictedNum2_1_5));
		eval.evaluate();
		System.out.println("------------Top_5_newName R4---------------");
		System.out.println("Accuracy: " + FORMAT.format(((double)newPredictedNum3_1_5 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(newPredictedNum3_1_5, newPredictedNum3_5 - newPredictedNum3_1_5, size2 - newPredictedNum3_1_5, size3 - (newPredictedNum3_5 - newPredictedNum3_1_5));
		eval.evaluate();
		
		System.out.println("------------Top_1_full Name R2---------------");
		System.out.println("Accuracy: " + FORMAT.format(((double)numOfFullNames1_1 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(numOfFullNames1_1, numOfFullNames1 - numOfFullNames1_1, size2 - numOfFullNames1_1, size3 - (numOfFullNames1 - numOfFullNames1_1));
		eval.evaluate();
		System.out.println("------------Top_1_full Name R3---------------");
		System.out.println("Accuracy: " + FORMAT.format(((double)numOfFullNames2_1 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(numOfFullNames2_1, numOfFullNames2 - numOfFullNames2_1, size2 - numOfFullNames2_1, size3 - (numOfFullNames2 - numOfFullNames2_1));
		eval.evaluate();
		System.out.println("------------Top_1_full Name R4---------------");
		System.out.println("Accuracy: " + FORMAT.format(((double)numOfFullNames3_1 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(numOfFullNames3_1, numOfFullNames3 - numOfFullNames3_1, size2 - numOfFullNames3_1, size3 - (numOfFullNames3 - numOfFullNames3_1));
		eval.evaluate();
		System.out.println("------------Top_5_full Name R2---------------");
		System.out.println("Accuracy: " + FORMAT.format(((double)numOfFullNames1_1_5 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(numOfFullNames1_1_5, numOfFullNames1_5 - numOfFullNames1_1_5, size2 - numOfFullNames1_1_5, size3 - (numOfFullNames1_5 - numOfFullNames1_1_5));
		eval.evaluate();
		System.out.println("------------Top_5_full Name R3---------------");
		System.out.println("Accuracy: " + FORMAT.format(((double)numOfFullNames2_1_5 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(numOfFullNames2_1_5, numOfFullNames2_5 - numOfFullNames2_1_5, size2 - numOfFullNames2_1_5, size3 - (numOfFullNames2_5 - numOfFullNames2_1_5));
		eval.evaluate();
		System.out.println("------------Top_5_full Name R4---------------");
		System.out.println("Accuracy: " + FORMAT.format(((double)numOfFullNames3_1_5 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(numOfFullNames3_1_5, numOfFullNames3_5 - numOfFullNames3_1_5, size2 - numOfFullNames3_1_5, size3 - (numOfFullNames3_5 - numOfFullNames3_1_5));
		eval.evaluate();
		System.out.println("------------------------------------------");
	}
	
	private int identifyPredictedResults(String[] newTokensArray, String[] oldTokensArray, List<String> predictTokens, boolean needAllSynonyms) {
		for (String predictToken : predictTokens) {
			int predictResult = identifyPredictedResult(newTokensArray, oldTokensArray, predictToken, needAllSynonyms);
			if (predictResult != 0) return predictResult;
		}
		return 0;
	}

	private int identifyPredictedResult(String[] newTokensArray, String[] oldTokensArray, String predictToken, boolean needAllSynonyms) {
		String[] tokensArray1 = predictToken.split("_");
		int length1 = tokensArray1.length;
		if (newTokensArray.length == length1 && isPredicted(newTokensArray, tokensArray1, needAllSynonyms)) {
			return 1;// the predicted result is the same as the new one.
		} else {
			if (oldTokensArray.length != length1) {
				return 0;
			} else if (!isPredicted(oldTokensArray, tokensArray1, needAllSynonyms)) {
				return 2;// the predicted result is the same as the old one.
			}
		}
		return 0;
	}

	private boolean isPredicted(String[] newTokensArray, String[] predictTokens, boolean needAllSynonyms) {
		for (int i = 0, length = newTokensArray.length; i < length; i ++) {
			String newToken = newTokensArray[i];
			String predictToken = predictTokens[i];
			if (newToken.equalsIgnoreCase(predictToken)) return true;
			
			List<String> newTokenSynonyms = findSynonyms(newToken, needAllSynonyms);
			List<String> predictTokenSynonyms = findSynonyms(predictToken, needAllSynonyms);
			newTokenSynonyms.retainAll(predictTokenSynonyms);
			if (!newTokenSynonyms.isEmpty()) return true;
		}
		return false;
	}

	/**
	 * Evaluate the performance of suggesting new names for inconsistent method names, when only considering the top-5 similar methods with normal ranking.
	 * 
	 * @param renamedOldMethodInfo
	 * @param renamedNewMethodInfo
	 * @param trainingMethodInfo
	 * @param topSimilarBodyMap
	 * @param topSimilarMethodNameMap
	 * @param numOfPredictTokens
	 * @param labels
	 * @param topNum
	 */
	public void evaluate2(List<MethodInfo> renamedOldMethodInfo, List<MethodInfo> renamedNewMethodInfo, List<MethodInfo> trainingMethodInfo, 
			Map<Integer, Map<Integer, Double>> topSimilarBodyMap, Map<Integer, Map<Integer, Double>> topSimilarMethodNameMap, 
			int numOfPredictTokens, List<Integer> labels, int topNum, boolean considerSynonyms, boolean needAllSynonyms) {
		int oldPredictedNum1 = 0;
		int newPredictedNum1 = 0;
		int oldPredictedNum1_1 = 0;
		int newPredictedNum1_1 = 0;
		int oldPredictedNum1_5 = 0;
		int newPredictedNum1_5 = 0;
		int oldPredictedNum1_1_5 = 0;
		int newPredictedNum1_1_5 = 0;
		int size = renamedOldMethodInfo.size();
		int falseNegatives = 0;
		int trueNegatives = 0;
		int falsePositives = 0;
		int truePositives = 0;
		int numOfFullNames1 = 0;
		int numOfFullNames1_5 = 0;
		int numOfFullNames1_1 = 0;
		int numOfFullNames1_1_5 = 0;
		
		for (int index = 0; index < size; index ++) {
			Map<Integer, Double> similarities = topSimilarBodyMap.get(index);
			if (similarities.size() == 0) {
				continue;
			}
			MapSorter<Integer, Double> sorter = new MapSorter<Integer, Double>();
			similarities = sorter.sortByValueDescending(similarities);
			int label = labels.get(index);
			
			List<String> ptList = new ArrayList<>(); // used to recommend method names.
			List<String> tokensPredictedByBody = new ArrayList<>();// used to identify inconsistent method names
			List<String> tokensPredictedByName = new ArrayList<>();// used to identify inconsistent method names
			int counter = 0;
			for (Map.Entry<Integer, Double> entry : similarities.entrySet()) {
				int methodIndex = entry.getKey();
				MethodInfo methodInfo = trainingMethodInfo.get(methodIndex);
				List<String> similarRawTokens = methodInfo.getMethodNameTokens();
				// 1 to numOfPredictTokens raw tokens
				StringBuilder token = new StringBuilder();
				for (int i = 0; i < numOfPredictTokens; i ++) {
					token.append(similarRawTokens.get(i)).append("_");
					if (similarRawTokens.size() <= i + 1) break;
				}
				
				String t = token.toString().toLowerCase();
				if (!ptList.contains(t)) {
					ptList.add(t);
				}
				if (tokensPredictedByBody.isEmpty()) {
					tokensPredictedByBody.add(similarRawTokens.get(0));
				}
				
				counter ++;
				if (counter >= topNum) break;
			}
			
			List<String> oldRawTokens = renamedOldMethodInfo.get(index).getMethodNameTokens();
			List<String> newRawTokens = renamedNewMethodInfo.get(index).getMethodNameTokens();
			StringBuilder oldOriginalToken = new StringBuilder();
			StringBuilder newOriginalToken = new StringBuilder();
			String newMethodName = readMethodName(newRawTokens);
			for (int i = 0; i < numOfPredictTokens; i ++) {
				oldOriginalToken.append(oldRawTokens.get(i)).append("_");
				if (oldRawTokens.size() <= i + 1) break;
			}
			for (int i = 0; i < numOfPredictTokens; i ++) {
				newOriginalToken.append(newRawTokens.get(i)).append("_");
				if (newRawTokens.size() <= i + 1) break;
			}
			
			boolean isTrue = false;
			Map<Integer, Double> methodNameMap = topSimilarMethodNameMap.get(index);
			methodNameMap = sorter.sortByValueDescending(methodNameMap);//isDescending ? sorter.sortByValueDescending(methodNameMap) : sorter.sortByValueAscending(methodNameMap);
			for (Map.Entry<Integer, Double> entry : methodNameMap.entrySet()) {
				int methodIndex = entry.getKey();
				MethodInfo methodInfo = trainingMethodInfo.get(methodIndex);
				String token = methodInfo.getMethodNameTokens().get(0);
				if (tokensPredictedByName.isEmpty()) {
					tokensPredictedByName.add(token);
				}
				counter --;
				if (counter <= 0) break;
			}
			
			if (considerSynonyms) {
				tokensPredictedByBody = findSynonyms(tokensPredictedByBody.get(0), needAllSynonyms);
				tokensPredictedByName = findSynonyms(tokensPredictedByName.get(0), needAllSynonyms);
			}
			
			tokensPredictedByName.retainAll(tokensPredictedByBody);
			if (tokensPredictedByName.isEmpty()) {// spot as inconsistent method names, positive.
				if (label == 0) {// actual result: inconsistent
					truePositives ++;
				} else {// actual result: consistent
					falsePositives ++;
				}
				isTrue = true;
			} else {// spot as consistent method names, negative.
				if (label == 0) {// actual result: inconsistent
					falseNegatives ++;
				} else { // actual result: consistent
					trueNegatives ++;
				}
			}

			String predictToken1 = ptList.get(0);
			String oldToken = oldOriginalToken.toString().toLowerCase();
			String newToken = newOriginalToken.toString().toLowerCase();
			if (considerSynonyms) {
				String[] newTokensArray = newToken.split("_");
				String[] oldTokensArray = oldToken.split("_");
				int predictResult = identifyPredictedResult(newTokensArray, oldTokensArray, predictToken1, needAllSynonyms);
				if (predictResult == 1) {
					newPredictedNum1 ++;
					if (isTrue) newPredictedNum1_1 ++;
				} else if (predictResult == 2) {
					oldPredictedNum1 ++;
					if (isTrue) oldPredictedNum1_1 ++;
				}

				predictResult = identifyPredictedResults(newTokensArray, oldTokensArray, ptList, needAllSynonyms);
				if (predictResult == 1) {
					newPredictedNum1_5 ++;
					if (isTrue) newPredictedNum1_1_5 ++;
				} else if (predictResult == 2) {
					oldPredictedNum1_5 ++;
					if (isTrue) oldPredictedNum1_1_5 ++;
				}
				
				String[] fullNameTokens = newMethodName.split("_");
				predictResult = identifyPredictedResult(fullNameTokens, oldTokensArray, predictToken1, needAllSynonyms);
				if (predictResult == 1) {
					numOfFullNames1 ++;
					if (isTrue) numOfFullNames1 ++;
				}
				predictResult = identifyPredictedResults(fullNameTokens, oldTokensArray, ptList, needAllSynonyms);
				if (predictResult == 1) {
					numOfFullNames1_5 ++;
					if (isTrue) numOfFullNames1_1_5 ++;
				}
			} else {
				if (!oldToken.equals(predictToken1)) oldPredictedNum1 ++;
				if (!ptList.contains(oldToken)) oldPredictedNum1_5 ++;
				if (newToken.equals(predictToken1)) newPredictedNum1 ++;
				if (ptList.contains(newToken)) newPredictedNum1_5 ++;
				if (predictToken1.equals(newMethodName)) numOfFullNames1 ++;
				if (ptList.contains(newMethodName)) numOfFullNames1_5 ++;
				
				if (isTrue) {
					if (!oldToken.equals(predictToken1)) oldPredictedNum1_1 ++;
					if (!ptList.contains(oldToken)) oldPredictedNum1_1_5 ++;
					if (newToken.equals(predictToken1)) newPredictedNum1_1 ++;
					if (ptList.contains(newToken)) newPredictedNum1_1_5 ++;
					if (predictToken1.equals(newMethodName)) numOfFullNames1_1 ++;
					if (ptList.contains(newMethodName)) numOfFullNames1_1_5 ++;
				}
			}
		}
		
		System.out.println("=======Identify inconsistent methods====== With Synonyms: " + (considerSynonyms ? "Yes." : "No."));
		System.out.println(" Positives Negative ");
		System.out.println("True " + truePositives + " " + trueNegatives + " " + (truePositives + trueNegatives));
		System.out.println("False " + falsePositives + " " + falseNegatives);
		edu.lu.uni.serval.utils.Evaluation eval = new edu.lu.uni.serval.utils.Evaluation(truePositives, trueNegatives, falsePositives, falseNegatives);
		eval.evaluate();
		System.out.println("------------------------------------------");
		
		int size2 = truePositives + trueNegatives;// Number of Identified inconsistent method names
		int size3 = falsePositives + falseNegatives;
		int size4 = truePositives + falsePositives;
		System.out.println("==All inconsistent method names==: " + size + " ==Accurately identified method names==: ");
		System.out.println("========================================================================================");
		final DecimalFormat FORMAT = new DecimalFormat("0.000");
		System.out.println("R1: Top_1_oldName ");// + oldPredictedNum1 + " " + oldPredictedNum1_1);
		System.out.println("Accuracy: " + FORMAT.format(((double)oldPredictedNum1_1 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(oldPredictedNum1_1, oldPredictedNum1 - oldPredictedNum1_1, size2 - oldPredictedNum1_1, size3 - (oldPredictedNum1 - oldPredictedNum1_1));
		eval.evaluate();
		System.out.println("R1: Top_5_oldName ");// + oldPredictedNum1_1_5 + " " + oldPredictedNum1_1_5);
		System.out.println("Accuracy: " + FORMAT.format(((double)oldPredictedNum1_1_5 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(oldPredictedNum1_1_5, oldPredictedNum1_5 - oldPredictedNum1_1_5, size2 - oldPredictedNum1_1_5, size3 - (oldPredictedNum1_5 - oldPredictedNum1_1_5));
		eval.evaluate();
		System.out.println("R1: Top_1_newName ");// + newPredictedNum1 + " " + newPredictedNum1_1);
		System.out.println("Accuracy: " + FORMAT.format(((double)newPredictedNum1_1 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(newPredictedNum1_1, newPredictedNum1 - newPredictedNum1_1, size2 - newPredictedNum1_1, size3 - (newPredictedNum1 - newPredictedNum1_1));
		eval.evaluate();
		System.out.println("R1: Top_5_newName ");// + newPredictedNum1_5 + " " + newPredictedNum1_1_5);
		System.out.println("Accuracy: " + FORMAT.format(((double)newPredictedNum1_1_5 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(newPredictedNum1_1_5, newPredictedNum1_5 - newPredictedNum1_1_5, size2 - newPredictedNum1_1_5, size3 - (newPredictedNum1_5 - newPredictedNum1_1_5));
		eval.evaluate();
		System.out.println("R1: Top_1_fullName ");// + numOfFullNames1 + " " + numOfFullNames1_1);
		System.out.println("Accuracy: " + FORMAT.format(((double)numOfFullNames1_1 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(numOfFullNames1_1, numOfFullNames1 - numOfFullNames1_1, size2 - numOfFullNames1_1, size3 - (numOfFullNames1 - numOfFullNames1_1));
		eval.evaluate();
		System.out.println("R1: Top_5_fullName ");// + numOfFullNames1_5 + " " + numOfFullNames1_1_5);
		System.out.println("Accuracy: " + FORMAT.format(((double)numOfFullNames1_1_5 / size4) * 100) + "%");
		eval = new edu.lu.uni.serval.utils.Evaluation(numOfFullNames1_1_5, numOfFullNames1_5 - numOfFullNames1_1_5, size2 - numOfFullNames1_1_5, size3 - (numOfFullNames1_5 - numOfFullNames1_1_5));
		eval.evaluate();
		System.out.println("========================================================================================");
	}

	private String readMethodName(List<String> methodNameTokens) {
		StringBuilder builder = new StringBuilder();
		for (String token : methodNameTokens) {
			builder.append(token).append("_");
		}
		return builder.toString().toLowerCase();
	}

	/**
	 * 
	 * @param renamedOldMethodInfo
	 * @param renamedNewMethodInfo
	 * @param trainingMethodInfo
	 * @param topSimilarBodyMap
	 * @param topSimilarNameMap
	 * @param labels
	 * @param topNum
	 */
	public void evaluate3(List<MethodInfo> renamedNewMethodInfo, List<MethodInfo> trainingMethodInfo, Map<Integer, Map<Integer, Double>> topSimilarBodyMap, int topNum,
			boolean considerSynonyms, boolean needAllSynonyms) {
		int newPredictedNum0 = 0;
		int newPredictedNum1 = 0;
		int newPredictedNum2 = 0;
		int newPredictedNum3 = 0;
		int newPredictedNum0_5 = 0;
		int newPredictedNum1_5 = 0;
		int newPredictedNum2_5 = 0;
		int newPredictedNum3_5 = 0;
		int numPredictedTokens0 = 0;
		int numPredictedTokens1 = 0;
		int numPredictedTokens2 = 0;
		int numPredictedTokens3 = 0;
		int numPredictedTokens0_5 = 0;
		int numPredictedTokens1_5 = 0;
		int numPredictedTokens2_5 = 0;
		int numPredictedTokens3_5 = 0;
		int size = renamedNewMethodInfo.size();
		int numberMethodNameTokens = 0;
		
		for (int index = 0; index < size; index ++) {
			Map<Integer, Double> similarities = topSimilarBodyMap.get(index);
			if (similarities.size() == 0) {
				continue;
			}
			MapSorter<Integer, Double> sorter = new MapSorter<Integer, Double>();
			similarities = sorter.sortByValueDescending(similarities);
			
			List<PredictToken> ptList = new ArrayList<>(); // used to recommend method names.
			int counter = 0;
			List<String> predictTokens = new ArrayList<>();
			for (Map.Entry<Integer, Double> entry : similarities.entrySet()) {
				int methodIndex = entry.getKey();
				Double similarity = entry.getValue();
				MethodInfo methodInfo = trainingMethodInfo.get(methodIndex);
				List<String> similarRawTokens = methodInfo.getMethodNameTokens();
				// 1 to numOfPredictTokens raw tokens
				StringBuilder token = new StringBuilder();
				for (int i = 0, size1 = similarRawTokens.size(); i < size1; i ++) {
					if (i >= 3) break;
					token.append(similarRawTokens.get(i)).append("_");
				}
				
				PredictToken pt = new PredictToken(token.toString().toLowerCase());
				Integer index1 = ptList.indexOf(pt);
				if (index1 > -1) {
					pt = ptList.get(index1);
					pt.setSimilarity(pt.getSimilarity() + similarity);
					pt.setTimes(pt.getTimes() + 1);
				} else {
					pt.setSimilarity(similarity);
					pt.setTimes(1);
					ptList.add(pt);
				}
				predictTokens.add(token.toString().toLowerCase());
				counter ++;
				if (counter >= topNum) break;
			}
			
			List<String> newRawTokens = renamedNewMethodInfo.get(index).getMethodNameTokens();
			StringBuilder newOriginalToken = new StringBuilder();
			int size1 = newRawTokens.size();
			numberMethodNameTokens += size1;
			for (int i = 0; i < size1; i ++) {
				newOriginalToken.append(newRawTokens.get(i)).append("_");
			}
			
			for (PredictToken pt : ptList) {
				pt.setSimilarity(pt.getSimilarity() / pt.getTimes());
			}
			
			List<String> predictTokens0 = predictTokens.subList(0, 5);
			TokenSuggestor ts = new TokenSuggestor(); 
			List<String> predictTokens1 = ts.selectTop5PredictTokens1(ptList);
			List<String> predictTokens2 = ts.selectTop5PredictTokens2(ptList);
			List<String> predictTokens3 = ts.selectTop5PredictTokens3(ptList);
			String predictToken0 = predictTokens0.get(0);
			String predictToken1 = predictTokens1.get(0);
			String predictToken2 = predictTokens2.get(0);
			String predictToken3 = predictTokens3.get(0);
			String newToken = newOriginalToken.toString().toLowerCase();
			
			numPredictedTokens0 += predictToken0.split("_").length;
			numPredictedTokens1 += predictToken1.split("_").length;
			numPredictedTokens2 += predictToken2.split("_").length;
			numPredictedTokens3 += predictToken3.split("_").length;
			
			if (considerSynonyms) {
				// Top1 
				String[] newTokens = newToken.split("_");
				newTokenSynonymsList = new ArrayList<>();
				predictTokensSynonyms = new HashMap<>();
				newPredictedNum0 += identifySynonyms(newTokens, predictToken0, needAllSynonyms);
				newPredictedNum1 += identifySynonyms(newTokens, predictToken1, needAllSynonyms);
				newPredictedNum2 += identifySynonyms(newTokens, predictToken2, needAllSynonyms);
				newPredictedNum3 += identifySynonyms(newTokens, predictToken3, needAllSynonyms);
				
				//Top5
				String predictedResults0 = computeResults(newTokens, predictTokens0, needAllSynonyms);
				String[] results0 = predictedResults0.split("_");
				newPredictedNum0_5 += Integer.parseInt(results0[0]);
				numPredictedTokens0_5 += Integer.parseInt(results0[1]);
				String predictedResults1 = computeResults(newTokens, predictTokens1, needAllSynonyms);
				String[] results1 = predictedResults1.split("_");
				newPredictedNum1_5 += Integer.parseInt(results1[0]);
				numPredictedTokens1_5 += Integer.parseInt(results1[1]);
				String predictedResults2 = computeResults(newTokens, predictTokens2, needAllSynonyms);
				String[] results2 = predictedResults2.split("_");
				newPredictedNum2_5 += Integer.parseInt(results2[0]);
				numPredictedTokens2_5 += Integer.parseInt(results2[1]);
				String predictedResults3 = computeResults(newTokens, predictTokens3, needAllSynonyms);
				String[] results3 = predictedResults3.split("_");
				newPredictedNum3_5 += Integer.parseInt(results3[0]);
				numPredictedTokens3_5 += Integer.parseInt(results3[1]);
			} else {
				// Top1
				newPredictedNum0 += intersection(newToken, predictToken0);
				newPredictedNum1 += intersection(newToken, predictToken1);
				newPredictedNum2 += intersection(newToken, predictToken2);
				newPredictedNum3 += intersection(newToken, predictToken3);
				
				//Top5
				String predictedResults0 = computeResults(newToken, predictTokens0);
				String[] results0 = predictedResults0.split("_");
				newPredictedNum0_5 += Integer.parseInt(results0[0]);
				numPredictedTokens0_5 += Integer.parseInt(results0[1]);
				String predictedResults1 = computeResults(newToken, predictTokens1);
				String[] results1 = predictedResults1.split("_");
				newPredictedNum1_5 += Integer.parseInt(results1[0]);
				numPredictedTokens1_5 += Integer.parseInt(results1[1]);
				String predictedResults2 = computeResults(newToken, predictTokens2);
				String[] results2 = predictedResults2.split("_");
				newPredictedNum2_5 += Integer.parseInt(results2[0]);
				numPredictedTokens2_5 += Integer.parseInt(results2[1]);
				String predictedResults3 = computeResults(newToken, predictTokens3);
				String[] results3 = predictedResults3.split("_");
				newPredictedNum3_5 += Integer.parseInt(results3[0]);
				numPredictedTokens3_5 += Integer.parseInt(results3[1]);
			}
		}

		edu.lu.uni.serval.utils.Evaluation eval = null;
		System.out.println("------------Top_1_newName R1---------------");
		eval = new edu.lu.uni.serval.utils.Evaluation(newPredictedNum0, 0, numPredictedTokens0 - newPredictedNum0, numberMethodNameTokens - newPredictedNum0);
		eval.evaluate();
		System.out.println("------------Top_1_newName R2---------------");
		eval = new edu.lu.uni.serval.utils.Evaluation(newPredictedNum1, 0, numPredictedTokens1 - newPredictedNum1, numberMethodNameTokens - newPredictedNum1);
		eval.evaluate();
		System.out.println("------------Top_1_newName R3---------------");
		eval = new edu.lu.uni.serval.utils.Evaluation(newPredictedNum2, 0, numPredictedTokens2 - newPredictedNum2, numberMethodNameTokens - newPredictedNum2);
		eval.evaluate();
		System.out.println("------------Top_1_newName R4---------------");
		eval = new edu.lu.uni.serval.utils.Evaluation(newPredictedNum3, 0, numPredictedTokens3 - newPredictedNum3, numberMethodNameTokens - newPredictedNum3);
		eval.evaluate();
		System.out.println("------------Top_5_newName R1---------------");
		eval = new edu.lu.uni.serval.utils.Evaluation(newPredictedNum1_5, 0, numPredictedTokens0_5 - newPredictedNum0_5, numberMethodNameTokens - newPredictedNum0_5);
		eval.evaluate();
		System.out.println("------------Top_5_newName R2---------------");
		eval = new edu.lu.uni.serval.utils.Evaluation(newPredictedNum1_5, 0, numPredictedTokens1_5 - newPredictedNum1_5, numberMethodNameTokens - newPredictedNum1_5);
		eval.evaluate();
		System.out.println("------------Top_5_newName R3---------------");
		eval = new edu.lu.uni.serval.utils.Evaluation(newPredictedNum2_5, 0, numPredictedTokens2_5 - newPredictedNum2_5, numberMethodNameTokens - newPredictedNum2_5);
		eval.evaluate();
		System.out.println("------------Top_5_newName R4---------------");
		eval = new edu.lu.uni.serval.utils.Evaluation(newPredictedNum3_5, 0, numPredictedTokens3_5 - newPredictedNum3_5, numberMethodNameTokens - newPredictedNum3_5);
		eval.evaluate();
		System.out.println("-------------------------------------------");
	}
	
	private List<List<String>> newTokenSynonymsList;
	private Map<String, List<String>> predictTokensSynonyms;

	private int identifySynonyms(String[] newTokens, String predictToken, boolean needAllSynonyms) {
		int predictedNum = 0;
		List<String> newTokensList = Arrays.asList(newTokens);
		String[] predictTokens = predictToken.split("_");
		
		for (int i = 0, size = newTokensList.size(); i < size; i ++) {
			List<String> newTokenSynonyms;
			if (newTokenSynonymsList.size() < (i + 1)) {
				List<String> newTokenSynonyms1 = findSynonyms(newTokensList.get(i), needAllSynonyms);
				newTokenSynonymsList.add(newTokenSynonyms1);
			}
			newTokenSynonyms = newTokenSynonymsList.get(i);
			
			for (String pToken : predictTokens) {
				List<String> synonyms = predictTokensSynonyms.get(pToken);
				if (synonyms == null) {
					synonyms = findSynonyms(pToken, needAllSynonyms);
					predictTokensSynonyms.put(pToken, synonyms);
				}
				newTokenSynonyms.retainAll(synonyms);
				if (!newTokenSynonyms.isEmpty()) {
					predictedNum ++;
					break;
				}
			}
		}
		return predictedNum;
	}
	
	private String computeResults(String[] newTokens, List<String> predictTokens, boolean needAllSynonyms) {
		int a = -1;
		int indexT = -1;
		for (int i = 0; i < predictTokens.size(); i ++) {
			String predictTs = predictTokens.get(i);
			int b = identifySynonyms(newTokens, predictTs, needAllSynonyms);
			if (a < b) {
				a = b;
				indexT = i;
			}
		}
		int b = Arrays.asList(predictTokens.get(indexT).split("_")).size();
		return a + "_" + b;
	}

	private String computeResults(String newToken, List<String> predictTokens) {
		int a = -1;
		int indexT = -1;
		for (int i = 0; i < predictTokens.size(); i ++) {
			String predictTs = predictTokens.get(i);
			int b = intersection(newToken, predictTs);
			if (a < b) {
				a = b;
				indexT = i;
			}
		}
		int b = Arrays.asList(predictTokens.get(indexT).split("_")).size();
		return a + "_" + b;
	}

	private int intersection(String newToken, String predictToken) {
		List<String> a = new ArrayList<>();
		a.addAll(Arrays.asList(newToken.split("_")));
		List<String> b = new ArrayList<>();
		b.addAll(Arrays.asList(predictToken.split("_")));
		a.retainAll(b);
		
		return a.size();
	}
	
}
