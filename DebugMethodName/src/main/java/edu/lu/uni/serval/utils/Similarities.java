package edu.lu.uni.serval.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.lu.uni.serval.sricmn.info.MethodInfo;
import edu.lu.uni.serval.utils.DistanceCalculator;
import edu.lu.uni.serval.utils.DistanceCalculator.DistanceFunction;
import edu.lu.uni.serval.utils.similarity.StringMetrics;

public class Similarities {

	private int TOP_NUM = 100;
	
	public void setTopNum(int topNum) {
		this.TOP_NUM = topNum;
	}
	
	/**
	 * Calculate similarities with features learned by deep learning models.
	 * 
	 * @param testingFeatures
	 * @param trainingMethodInfoMap
	 * @param testingMethodInfo
	 * @param fromIndex
	 * @param considerReturnType
	 * @return
	 */
	public Map<Integer, Map<Integer, Double>> calculateSimilarities(List<Double[]> testingFeatures, Map<String, List<MethodInfo>> trainingMethodInfoMap,
			List<MethodInfo> testingMethodInfo, int fromIndex, boolean considerReturnType) {
		Map<Integer, Map<Integer, Double>> topSimilarInstancesMap = new HashMap<>();
		
		if (considerReturnType) { 
			for (int index = 0, size = testingFeatures.size(); index < size; index ++) {
				String returnType = testingMethodInfo.get(index).getReturnType();
				Double[] testingFeature = testingFeatures.get(index);
				List<MethodInfo> trainingMethodInfoList = trainingMethodInfoMap.get(returnType);
				Map<Integer, Double> similarities = calculateSimilarties(trainingMethodInfoList, testingFeature);
				topSimilarInstancesMap.put(index + fromIndex, similarities);
			}
		} else {
			for (int index = 0, size = testingFeatures.size(); index < size; index ++) {
				Double[] testingFeature = testingFeatures.get(index);
				Map<Integer, Double> similarities = new HashMap<>();
				for (Map.Entry<String, List<MethodInfo>> entry : trainingMethodInfoMap.entrySet()) {
					List<MethodInfo> trainingMethodInfoList = entry.getValue();
					Map<Integer, Double> subSimilarities = calculateSimilarties(trainingMethodInfoList, testingFeature);
					similarities.putAll(subSimilarities);
				}
				topSimilarInstancesMap.put(index + fromIndex, selectedTopNumIndexes(similarities));
			}
		}
		
		return topSimilarInstancesMap;
	}
	
	private Map<Integer, Double> calculateSimilarties(List<MethodInfo> trainingMethodInfoList, Double[] testingFeature) {
		Map<Integer, Double> similarities = new HashMap<>();
		for (int index2 = 0, size2 = trainingMethodInfoList.size(); index2 < size2; index2 ++) {
			MethodInfo methodInfo = trainingMethodInfoList.get(index2);
			Double[] trainingFeature = methodInfo.getBodyFeatures();
			Double similarity = new DistanceCalculator().calculateDistance(DistanceFunction.COSINESIMILARITY, testingFeature, trainingFeature);
			if (similarity.equals(Double.NaN)) {
				continue;
			}
			similarities.put(methodInfo.getListIndex(), similarity);
		}
		
		return selectedTopNumIndexes(similarities);
	}

	private Map<Integer, Double> selectedTopNumIndexes(Map<Integer, Double> similarities) {
		MapSorter<Integer, Double> sorter = new MapSorter<>();
		similarities = sorter.sortByValueDescending(similarities);//isDescending ? sorter.sortByValueDescending(similarities) : sorter.sortByValueAscending(similarities);
		
		int counter = 0;
		Map<Integer, Double> similaritiesMap = new HashMap<>();
		for (Map.Entry<Integer, Double> subEntry : similarities.entrySet()) {
			similaritiesMap.put(subEntry.getKey(), subEntry.getValue());
			counter ++;
			if (counter == TOP_NUM) break;
		}
		return similaritiesMap;
	}

	/**
	 * Calculate similarities with features learned by deep learning models.
	 * 
	 * @param testingMethodNameFeatures
	 * @param trainingMethodNameFeatures
	 * @param fromIndex
	 * @return
	 */
	public Map<Integer, Map<Integer, Double>> calculateSimilarities(List<Double[]> testingMethodNameFeatures, List<Double[]> trainingMethodNameFeatures, 
			int fromIndex, List<MethodInfo> testingMethodInfoList, Map<String, List<MethodInfo>> trainingMethodInfoMap) {
		Map<Integer, Map<Integer, Double>> topInstancesMap = new HashMap<>();
		for (int i = 0, size = testingMethodNameFeatures.size(); i < size; i ++) {
			Double[] testingFeature = testingMethodNameFeatures.get(i);
			MethodInfo testMethodInfo = testingMethodInfoList.get(i);
			String testMethodName = testMethodInfo.getMethodNameTokens().toString();
			Map<Integer, Double> similarities = new HashMap<>();
			
			for (Map.Entry<String, List<MethodInfo>> entry : trainingMethodInfoMap.entrySet()) {
				List<MethodInfo> trainingMethodInfoList = entry.getValue();
				for (int index2 = 0, size2 = trainingMethodInfoList.size(); index2 < size2; index2 ++) {
					MethodInfo methodInfo = trainingMethodInfoList.get(index2);
					
					// Filter out the methods with the same method names.
					if (methodInfo.getMethodNameTokens().toString().equalsIgnoreCase(testMethodName)) continue;
					
					Integer methodIndex = methodInfo.getListIndex();
					Double[] trainingFeature = trainingMethodNameFeatures.get(methodIndex);
					Double similarity = new DistanceCalculator().calculateDistance(DistanceFunction.COSINESIMILARITY, testingFeature, trainingFeature);
					if (similarity.equals(Double.NaN)) {
						continue;
					}
					similarities.put(methodIndex, similarity);
				}
			}
			
			topInstancesMap.put(i + fromIndex, selectedTopNumIndexes(similarities));
		}
		return topInstancesMap;
	}

	/**
	 * Calculate similarities with string metric algorithms.
	 * 
	 * @param testingMethodInfoList
	 * @param trainingMethodInfoMap
	 * @param fromIndex
	 * @param considerReturnType
	 * @return
	 */
	public Map<Integer, Map<Integer, Double>> calculateSimilarities1(List<MethodInfo> testingMethodInfoList, Map<String, List<MethodInfo>> trainingMethodInfoMap,
			int fromIndex, boolean considerReturnType, StringMetrics stringMetric, boolean isSubToken) {
		
		NamesStringSimilarity nStrSm = new NamesStringSimilarity();
		nStrSm.readStringMetric(stringMetric.toString());
//		boolean isDescending = nStrSm.isDescending;
		
		Map<Integer, Map<Integer, Double>> topInstancesMap = new HashMap<>();
		
		if (considerReturnType) { 
			for (int index = 0, size = testingMethodInfoList.size(); index < size; index ++) {
				MethodInfo testingMethodInfo = testingMethodInfoList.get(index);
				String returnType = testingMethodInfo.getReturnType();
				List<MethodInfo> trainingMethodInfoList = trainingMethodInfoMap.get(returnType);
				Map<Integer, Double> indexes = calculateSimilarties(trainingMethodInfoList, testingMethodInfo, stringMetric, isSubToken);
				topInstancesMap.put(index + fromIndex, indexes);
			}
		} else {
			for (int index = 0, size = testingMethodInfoList.size(); index < size; index ++) {
				MethodInfo testingMethodInfo = testingMethodInfoList.get(index);
				Map<Integer, Double> indexes = new HashMap<>();
				for (Map.Entry<String, List<MethodInfo>> entry : trainingMethodInfoMap.entrySet()) {
					List<MethodInfo> trainingMethodInfoList = entry.getValue();
					Map<Integer, Double> subIndexes = calculateSimilarties(trainingMethodInfoList, testingMethodInfo, stringMetric, isSubToken);
					indexes.putAll(subIndexes);
				}
				topInstancesMap.put(index + fromIndex, selectedTopNumIndexes(indexes));
			}
		}
		return topInstancesMap;
	}
	
	/**
	 * Calculate similarities with string metric algorithms.
	 * 
	 * @param testingMethodInfoList
	 * @param trainingMethodInfoMap
	 * @param fromIndex
	 * @param considerReturnType
	 * @return
	 */
	public Map<Integer, Map<Integer, Double>> calculateSimilarities(List<MethodInfo> testingMethodInfoList, Map<String, List<MethodInfo>> trainingMethodInfoMap,
			int fromIndex, boolean considerReturnType, StringMetrics stringMetric, boolean isSubToken) {
		
		Map<Integer, Map<Integer, Double>> topInstancesMap = new HashMap<>();
		
		if (considerReturnType) { 
			for (int index = 0, size = testingMethodInfoList.size(); index < size; index ++) {
				MethodInfo testingMethodInfo = testingMethodInfoList.get(index);
				String returnType = testingMethodInfo.getReturnType();
				List<MethodInfo> trainingMethodInfoList = trainingMethodInfoMap.get(returnType);
				Map<Integer, Double> indexes = calculateSimilarties(trainingMethodInfoList, testingMethodInfo, stringMetric, isSubToken);
				topInstancesMap.put(index + fromIndex, indexes);
			}
		} else {
			for (int index = 0, size = testingMethodInfoList.size(); index < size; index ++) {
				MethodInfo testingMethodInfo = testingMethodInfoList.get(index);
				Map<Integer, Double> indexes = new HashMap<>();
				for (Map.Entry<String, List<MethodInfo>> entry : trainingMethodInfoMap.entrySet()) {
					List<MethodInfo> trainingMethodInfoList = entry.getValue();
					Map<Integer, Double> subIndexes = calculateSimilarties(trainingMethodInfoList, testingMethodInfo, stringMetric, isSubToken);
					indexes.putAll(subIndexes);
				}
				topInstancesMap.put(index + fromIndex, selectedTopNumIndexes(indexes));
			}
		}
		return topInstancesMap;
	}

	private Map<Integer, Double> calculateSimilarties(List<MethodInfo> trainingMethodInfoList,
			MethodInfo testingMethodInfo, StringMetrics stringMetric, boolean isSubToken) {
		String testMethodName = testingMethodInfo.getMethodNameTokens().toString();
		
		Map<Integer, Double> similarities = new HashMap<>();
		for (int j = 0, size2 = trainingMethodInfoList.size(); j < size2; j ++) {
			MethodInfo trainingMethodInfo = trainingMethodInfoList.get(j);
			
			// Filter out the methods with the same method names.
			if (trainingMethodInfo.getMethodNameTokens().toString().equalsIgnoreCase(testMethodName)) continue;
			
			Double similarity = calculateSimilarity(stringMetric, testingMethodInfo, trainingMethodInfo, isSubToken);
			if (similarity == null || similarity.equals(Double.NaN)) {
				continue;
			}
			similarities.put(j, similarity);
		}
		
		return selectedTopNumIndexes(similarities);
	}

	private Double calculateSimilarity(StringMetrics stringMetric, MethodInfo testingMethodInfo, MethodInfo trainingMethodInfo, boolean isSubToken) {
		if (isSubToken) {
			List<String> l1 = testingMethodInfo.getMethodNameTokens();
			List<String> l2 = trainingMethodInfo.getMethodNameTokens();
			return new NamesStringSimilarity().calculateSimilarity(l1, l2, stringMetric);
		} else {
			String str1 = testingMethodInfo.getMethodName();
			String str2 = trainingMethodInfo.getMethodName();
			return new NamesStringSimilarity().calculateSimilarity(str1, str2, stringMetric);
		}
	}
	
}
