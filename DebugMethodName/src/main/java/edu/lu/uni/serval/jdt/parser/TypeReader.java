package edu.lu.uni.serval.jdt.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.lu.uni.serval.utils.ListSorter;

public class TypeReader {
	
	public static String readArgumentTypes(String arguments) {
		List<String> argumentTypesList = new ArrayList<>();
		List<String> argumentsList = Arrays.asList(arguments.split("#"));
		int size = argumentsList.size();
		if (argumentsList.size() == 1) {
			return "null";
		}
		
		for (int i = 0; i < size; i = i + 2) {
			argumentTypesList.add(canonicalType(argumentsList.get(i)));
		}
		
		if (argumentTypesList.size() > 1) {
			ListSorter<String> sorter = new ListSorter<>(argumentTypesList);
			argumentTypesList = sorter.sortAscending();
		}
		
		String argumentTypesStr = "";
		size = argumentTypesList.size() - 1;
		for (int i = 0; i < size; i ++) {
			argumentTypesStr += argumentTypesList.get(i) + "#";
		}
		argumentTypesStr += argumentTypesList.get(size);
		return argumentTypesStr;
	}
	
	public static String canonicalType(String returnType) {
		String arrays = "";
		if (returnType.endsWith("[]")) {
			arrays = "[]";
			returnType = returnType.substring(0, returnType.length() - 2);
		}
		
		int index = returnType.indexOf("<");
		if (index != -1) {
			if (index == 0) {
				while (index == 0) {
					returnType = returnType.substring(returnType.indexOf(">") + 1).trim();
					index = returnType.indexOf(">");
				}
				index = returnType.indexOf("<");
				if (index == -1) index = returnType.length();
			}
			returnType = returnType.substring(0, index);
		}
		index = returnType.lastIndexOf(".");
		if (index != -1) { // && returnType.startsWith("java.")) {
			returnType = returnType.substring(index + 1);
		}

		return returnType + arrays;
	}
}
