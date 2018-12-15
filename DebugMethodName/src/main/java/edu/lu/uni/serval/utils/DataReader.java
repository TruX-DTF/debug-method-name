package edu.lu.uni.serval.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import edu.lu.uni.serval.sricmn.info.MethodInfo;
import edu.lu.uni.serval.utils.FeatureReader;
import edu.lu.uni.serval.utils.ReturnType;
import edu.lu.uni.serval.utils.ReturnType.ReturnTypeClassification;

public class DataReader {
	public List<MethodInfo> testingMethodInfoList = new ArrayList<>();
	
	public List<Integer> readLabels(String testingLabelFile, List<MethodInfo> renamedOldMethodInfo, List<MethodInfo> renamedNewMethodInfo) throws IOException {
		List<Integer> labels = new ArrayList<>();
		
		String content = FileHelper.readFile(testingLabelFile);
		BufferedReader reader = new BufferedReader(new StringReader(content));
		String line = null;
		int index = -1;
		while ((line = reader.readLine()) != null) {
			index ++;
			int label = Integer.parseInt(line);
			labels.add(label);
			if (label == 0) {
				testingMethodInfoList.add(renamedOldMethodInfo.get(index));
			} else {
				testingMethodInfoList.add(renamedNewMethodInfo.get(index));
			}
		}
		reader.close();
		return labels;
	}
	
	public List<Double[]> readMethodNameFeatures(File methodNameFeaturesFile) throws IOException {
		Map<Integer, Double[]> featuresMap = new HashMap<>();
//		List<Integer> indexesList = new ArrayList<>();
		FileInputStream fis = new FileInputStream(methodNameFeaturesFile);
		Scanner scanner = new Scanner(fis);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.startsWith("L SEN_")) {
				String[] featureArray = line.split(" ");
				Integer index = Integer.parseInt(featureArray[1].substring(4));
//				indexesList.add(index);
				int length = featureArray.length;
				Double[] features = new Double[length - 2];
				for (int i = 2; i < length; i ++) {
					features[i - 2] = Double.valueOf(featureArray[i]);
				}
				featuresMap.put(index, features);
			}
		}
		scanner.close();
		fis.close();
		
		List<Double[]> featuresList = new ArrayList<>();

//		ListSorter<Integer> sorter = new ListSorter<>(indexesList);
//		indexesList = sorter.sortAscending();
		// 1. Convert Map to List of Map
        List<Map.Entry<Integer, Double[]>> list =
                new LinkedList<Map.Entry<Integer, Double[]>>(featuresMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<Integer, Double[]>>() {
            public int compare(Map.Entry<Integer, Double[]> o1,
                               Map.Entry<Integer, Double[]> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

//        Map<Integer, Double[]> sortedMap = new LinkedHashMap<Integer, Double[]>();
        for (Map.Entry<Integer, Double[]> entry : list) {
//            sortedMap.put(entry.getKey(), entry.getValue());
        	featuresList.add(entry.getValue());
        }
//		for (int index : indexesList) {
//			featuresList.add(featuresMap.get(index));
//		}
		return featuresList;
	}
	
	public Map<String, List<MethodInfo>> readTrainingFeatures(File trainingFeatureFile, List<MethodInfo> trainingMethodInfo) {
		Map<String, List<MethodInfo>> methodInfoMap = new HashMap<>();
		
		FileInputStream fis = null;
		Scanner scanner = null;
		try {
			fis = new FileInputStream(trainingFeatureFile);
			scanner = new Scanner(fis);
			int index = -1;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				Double[] feature = doubleParseFeature(line);
				
				index ++;
				MethodInfo methodInfo = trainingMethodInfo.get(index);
				String returnType = methodInfo.getReturnType();
				methodInfo.setBodyFeatures(feature);
				methodInfo.setListIndex(index);
				
				List<MethodInfo> methodInfoList = methodInfoMap.get(returnType);
				if (methodInfoList == null) {
					methodInfoList = new ArrayList<>();
					methodInfoMap.put(returnType, methodInfoList);
				}
				methodInfoList.add(methodInfo);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				scanner.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return methodInfoMap;
	}

	private Double[] doubleParseFeature(String feature) {
		String[] features = feature.split(",");
		int length = features.length;
		Double[] doubleFeatures = new Double[length];
		for (int i = 0; i < length; i ++) {
			doubleFeatures[i] = Double.parseDouble(features[i]);
		}
		return doubleFeatures;
	}
	
	public List<MethodInfo> readMethodInfo(String methodInfoFile) throws IOException {
		List<MethodInfo> allMethods = new ArrayList<>();
		FileInputStream fis = new FileInputStream(methodInfoFile);
		Scanner scanner = new Scanner(fis);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] elements = line.split("@");
			int index = Integer.parseInt(elements[0]);
			String methodInfo = elements[1];
//			String returnType = new ReturnType().readReturnType(methodInfo.substring(methodInfo.lastIndexOf(":") + 1), ReturnTypeClassification.ABSTRACT);
			String returnType = elements[2];
			String[] tokens = elements[3].toLowerCase(Locale.ROOT).split(",");
			List<String> rawTokens = new ArrayList<>();
			for (int i = 0, length = tokens.length; i < length; i ++) {
				rawTokens.add(tokens[i]);
			}
			
			MethodInfo method= new MethodInfo(index, returnType, rawTokens);
			method.setInfo(methodInfo);
			allMethods.add(method);
		}
		scanner.close();
		fis.close();
		
		return allMethods;
	}
	
	public void readRenamedMethodInfo(String renamedMethodInfoFile, String parsedMethodTokensFile, List<MethodInfo> renamedOldMethodInfo,
			List<MethodInfo> renamedNewMethodInfo) throws IOException {
		List<String> methodInfoList = readInfo(renamedMethodInfoFile);
		List<String> parsedTokensList = readInfo(parsedMethodTokensFile);
		
		for (int i = 0, size = methodInfoList.size(); i < size; i ++) {
			String methodInfo = methodInfoList.get(i);
			String returnType = methodInfo.substring(methodInfo.lastIndexOf(":") + 1);
			returnType = new ReturnType().readReturnType(returnType, ReturnTypeClassification.ABSTRACT);

			String parsedTokens = parsedTokensList.get(i).toLowerCase(Locale.ROOT);
			String[] elements = parsedTokens.split("@");
			String oldParsedTokens = elements[0];
			String newParsedTokens = elements[1];
			List<String> oldRawTokens = readRawTokens(oldParsedTokens);
			List<String> newRawTokens = readRawTokens(newParsedTokens);
			MethodInfo oldMethodInfo = new MethodInfo(i, returnType, oldRawTokens);
			MethodInfo newMethodInfo = new MethodInfo(i, returnType, newRawTokens);
			renamedOldMethodInfo.add(oldMethodInfo);
			renamedNewMethodInfo.add(newMethodInfo);
		}
	}
	
	private List<String> readInfo(String fileName) throws IOException {
		List<String> infoList = new ArrayList<>();
		String content = FileHelper.readFile(fileName);
		BufferedReader reader = new BufferedReader(new StringReader(content));
		String line = null;
		while ((line = reader.readLine()) != null) {
			infoList.add(line);
		}
		reader.close();
		return infoList;
	}

	private List<String> readRawTokens(String parsedTokens) {
		String[] tokens = parsedTokens.split(",");
		List<String> rawTokens = new ArrayList<>();
		for (int i = 0, length = tokens.length; i < length; i = i + 2) {
			rawTokens.add(tokens[i]);
		}
		return rawTokens;
	}
	
	public List<Double[]> readFeatures(File featureFile) {
		FeatureReader reader = new FeatureReader();
		reader.setFeatureFile(featureFile);
		reader.readFeatures();
		List<Double[]> features = reader.getFeatures();
		return features;
	}
}
