package edu.lu.uni.serval.sricmn.comparativeStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.lu.uni.serval.utils.FileHelper;

public class EvaluateJson {

	public static void main(String[] args) throws IOException {
//		List<Integer> labels = readLabels("../OUTPUT/TestingLabels.txt");
		List<String> oldNames = new ArrayList<>();
		List<String> newNames = new ArrayList<>();
		readNames(oldNames, newNames, "../OUTPUT/ParsedMethodNames.txt");
		
		String intputFile = "../OUTPUT/data.txt";
		
		int a = 2837;

		int top1FullName = 0;
		int top5FullName = 0;
		int top15FullName = 0;
		int top1FirstToken = 0;
		int top5FirstToken = 0;
		int top15FirstToken = 0;
		int top1FirstToken_ = 0;
		int top5FirstToken_ = 0;
		int top15FirstToken_ = 0;
//		validate,paths[get,path,create,client,zk,set,is,string,for,add,write,if,paths,exists,get_path]
		String content = FileHelper.readFile(intputFile);
		BufferedReader reader = new BufferedReader(new StringReader(content));
		String line = null;
		int listIndex = -1;
		while ((line = reader.readLine()) != null) {
			line = line.toLowerCase().replace(" ", "");
			if (line.startsWith(",")) line = line.substring(1);
			int index = line.indexOf("[");
			String originName = line.substring(0, index);
			String originName1 = originName.replace(",1", "").replace(",2", "").replace(",0", "");
			String newName = "";
			do {
				listIndex ++;
				if (listIndex >= newNames.size()) {
					break;
				}
				newName = newNames.get(listIndex);
			} while (!newName.equals(originName1));
			
			String oldName = listIndex == newNames.size() ? "" : oldNames.get(listIndex);
			listIndex = listIndex == newNames.size() ? 0 : listIndex;
			
			
			String predictedResults = line.substring(index + 1, line.length() - 1);
			String[] originTokens = originName.split(",");
			originName = originName.replace(",", "_");
			String[] predictedNames = predictedResults.split(",");
			String[] oldTokens = oldName.split(",");
			if (oldTokens[0].equals(predictedNames[0])) top1FirstToken_ ++;
			List<String> preNames = Arrays.asList(predictedNames);
			preNames = preNames.subList(0, 5);
			if (!preNames.contains(oldTokens[0]))top5FirstToken_ ++;
			for (int i = 0; i < 15; i ++) {
				String predictedName = predictedNames[i];
				if (predictedName.equals(originName)) {
					top15FullName ++;
					top15FirstToken ++;
					if (i < 5) {
						top5FullName ++;
						top5FirstToken ++;
						if (i == 0) {
							top1FullName ++;
							top1FirstToken ++;
						}
					}
					break;
				} else {
					String[] subTokens = predictedName.split("_");
					if (subTokens[0].equals(originTokens[0])) {
						top15FirstToken ++;
						if (i < 5) {
							top5FirstToken ++;
							if (i == 0) {
								top1FirstToken ++;
							}
						}
						break;
					}
				}
			}
		}
		System.out.println("top1FirstToken_: " + top1FirstToken_);
		System.out.println("top5FirstToken_: " + top5FirstToken_);
		System.out.println("top15FirstToken_: " + top15FirstToken_);
		System.out.println("top1FirstToken: " + top1FirstToken);
		System.out.println("top5FirstToken: " + top5FirstToken);
		System.out.println("top15FirstToken: " + top15FirstToken);
		System.out.println("top1FullName: " + top1FullName);
		System.out.println("top5FullName: " + top5FullName);
		System.out.println("top15FullName: " + top15FullName);

		System.out.println("top1FirstToken: " + (double)top1FirstToken / a);
		System.out.println("top5FirstToken: " + (double)top5FirstToken / a);
//		System.out.println("top15FirstToken: " + (double)top15FirstToken / a);
		System.out.println("top1FullName: " + (double)top1FullName / a);
		System.out.println("top5FullName: " + (double)top5FullName / a);
//		System.out.println("top15FullName: " + (double)top15FullName / a);
	}

	@SuppressWarnings("unused")
	private static List<Integer> readLabels(String fileName) throws IOException {
		List<Integer> labels = new ArrayList<>();
		String content = FileHelper.readFile(fileName);
		BufferedReader reader = new BufferedReader(new StringReader(content));
		String line = null;
		while ((line = reader.readLine()) != null) {
			labels.add(Integer.parseInt(line));
		}
		reader.close();
		return labels;
	}

	private static void readNames(List<String> oldNames, List<String> newNames, String fileName) throws IOException {
		String content = FileHelper.readFile(fileName);
		BufferedReader reader = new BufferedReader(new StringReader(content));
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] names = line.toLowerCase().replace(" ", "").split("@");
			oldNames.add(names[0]);
			newNames.add(names[1]);
		}
		reader.close();
	}

}
