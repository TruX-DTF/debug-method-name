package edu.lu.uni.serval.git.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp {
	public static void main(String[] args) {
		if (isModifier("INS Modifier: public")) {
			System.out.println(true);
		} else System.out.println(false);
	}
	
	public static boolean endsWithPos(String string) {
		
		String regularExp = ".*Pos\\:\\d+$";
		Pattern pattern = Pattern.compile(regularExp);

		Matcher res = pattern.matcher(string.trim());
		if (res.matches()) {
			return true;
		}
		
		return false;
	}
	//QualifiedName
	public static boolean isQualifiedName(String string) {
		
		String regularExp = "^(INS|UPD|DEL|MOV)\\sQualifiedName:\\s.*";
		Pattern pattern = Pattern.compile(regularExp);

		Matcher res = pattern.matcher(string.trim());
		if (res.matches()) {
			return true;
		}
		
		return false;
	}

	public static boolean isUselessNode(String string) {
		
		String regularExp = "^(INS|UPD|DEL|MOV)\\s(ImportDeclaration|PackageDeclaration|Javadoc|MarkerAnnotation|NormalAnnotation|SingleMemberAnnotation).*";
		Pattern pattern = Pattern.compile(regularExp);

		Matcher res = pattern.matcher(string.trim());
		if (res.matches()) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isModifier(String string) {
		
		String regularExp = "^(INS|UPD|DEL|MOV)\\sModifier:\\s(public|protected|private).*";
		Pattern pattern = Pattern.compile(regularExp);

		Matcher res = pattern.matcher(string.trim());
		if (res.matches()) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isFragmentNode(String string) {
		
		String regularExp = ".*\\_\\d+\\=$";
		Pattern pattern = Pattern.compile(regularExp);

		Matcher res = pattern.matcher(string);
		if (res.matches()) {
			return true;
		}
		return false;
	}
	
	public static boolean keySetContainsStmt(String string) {

		String regularExp = ".*Statement\\_\\d+$";
		Pattern pattern = Pattern.compile(regularExp);

		Matcher res = pattern.matcher(string);
		if (res.matches()) {
			return true;
		}
		return false;
	}
	
	public static boolean endsWithTwoStrings(String reg1, String reg2, String string) {
		String regularExp = ".*" + reg1 + "\\s*"+ reg2 + "$";
		Pattern pattern = Pattern.compile(regularExp);

		Matcher res = pattern.matcher(string);
		if (res.matches()) {
			return true;
		}
		
		return false;
	}
	
	public static boolean startsWithTwoStrings(String reg1, String reg2, String string) {
		String regularExp = "^" + reg1 + "\\s*" + reg2 + ".*";
		Pattern pattern = Pattern.compile(regularExp);
		
		Matcher res = pattern.matcher(string);
		if (res.matches()) {
			return true;
		}
		
		return false;
	}
	
	public static boolean startsWithThreeStrings(String reg1, String reg2, String reg3, String string) {
		String regularExp = "^" + reg1 + "\\s*" + reg2 + "\\s*" + reg3 + ".*";
		Pattern pattern = Pattern.compile(regularExp);
		
		Matcher res = pattern.matcher(string);
		if (res.matches()) {
			return true;
		}
		
		return false;
	}
	
	public static boolean startsWithFourStrings(String reg1, String reg2, String reg3, String reg4, String string) {
		String regularExp = "^" + reg1 + "\\s*" + reg2 + "\\s*" + reg3 + "\\s*" + reg4 + ".*";
		Pattern pattern = Pattern.compile(regularExp);
		
		Matcher res = pattern.matcher(string);
		if (res.matches()) {
			return true;
		}
		
		return false;
	}

	public static boolean isASTNodeInfo(String string) {
		//StringLiteral_45="Bar"
		
		String regularExp = ".*\\_\\d+.*";
		Pattern pattern = Pattern.compile(regularExp);

		Matcher res = pattern.matcher(string);
		if (res.matches()) {
			return true;
		}
		return false;
	}

	public static boolean failedMappingTokens(String string) {

		String regularExp = "^(INS|ADD|DEL|MOV)\\s\\d+:\\s*.*";
		Pattern pattern = Pattern.compile(regularExp);
		String regularExp2 = "^(INS|ADD|DEL|MOV)\\s\\d+\\s*$";
		Pattern pattern2 = Pattern.compile(regularExp2);

		Matcher res = pattern.matcher(string.trim());
		Matcher res2 = pattern2.matcher(string.trim());
		if (res.matches() || res2.matches()) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isNumber(String string) {
		
		String regularExp = "^[0-9]+$";
		Pattern pattern = Pattern.compile(regularExp);

		Matcher res = pattern.matcher(string.trim());
		if (res.matches()) {
			return true;
		}
		return false;
	}
	
}
