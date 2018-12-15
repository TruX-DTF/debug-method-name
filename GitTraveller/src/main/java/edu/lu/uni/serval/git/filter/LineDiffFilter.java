package edu.lu.uni.serval.git.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineDiffFilter {
	private static final String REGULAR_EXPRESSION = "^@@\\s\\-\\d+,*\\d*\\s\\+\\d+,*\\d*\\s@@$"; //@@ -21,0 +22,2 @@
	private static Pattern pattern = Pattern.compile(REGULAR_EXPRESSION);
	
	public static boolean filterSignal(String string) {
		boolean flag = false;
		
		Matcher res = pattern.matcher(string);
		if (res.matches()) {
			flag = true;
		}
		
		return flag;
	}
	
	public static boolean filterString(String string) {
		boolean flag = false; 
	
		if (string.startsWith("@@") && string.endsWith("@@") && 
			   string.contains("-") && string.contains("+")) {
			flag = true;
		}
		
		return flag;
	}
	
	public static boolean filterImport(String string) {
		boolean flag = false;
		
		if (string.substring(1).trim().startsWith("import")) {
			flag = true;
		}
		
		return flag;
	}
	
	public static boolean filterComment(String string) {
		boolean flag = false;
		String s = string.substring(1).trim();
		/*
		 *  startsWith("*") is not very accurate, if the previous statement not endsWith(";") and is a statement, this predicate is OK.
		 */
		// startsWith("*/").
		if (s.startsWith("/*") || s.startsWith("*") || s.startsWith("//")) {
			flag = true;
		}
		return flag;
	}
	
	public static boolean filterAnnotation(String string) {
		if (string.substring(1).trim().startsWith("@")){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(filterSignal("@@ -895,0 +896 @@"));
		//@@ -209 +209 @@
		//@@ -3513 +3513,19 @@
		//@@ -895,0 +896,6 @@
	}
}
