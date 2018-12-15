package edu.lu.uni.serval.sricmn.liveStudy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import edu.lu.uni.serval.jdt.AST.ASTGenerator;
import edu.lu.uni.serval.jdt.AST.ASTGenerator.TokenType;
import edu.lu.uni.serval.jdt.tree.ITree;
import edu.lu.uni.serval.utils.FileHelper;

public class Main {

	public static void main(String[] args) throws IOException {
		int a = 0;
		
		String file = "../OUTPUT/LiveStudy/";
		File[] files = new File(file).listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				file = f.getPath() + "/results.txt";
				FileReader fileReader = new FileReader(file);
				BufferedReader reader = new BufferedReader(fileReader);
				String line = null;
				StringBuilder singleMethod = new StringBuilder();
				StringBuilder methodCode = new StringBuilder();
				boolean methodSymble = false;
				boolean methodBody = false;
				StringBuilder allMethods = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					if (line.startsWith("T1: [")) {
						if (singleMethod.length() > 0) {
							String method = singleMethod.toString();
//							if (!method.contains("@Override") && !method.contains("@Deprecated")) {
								if (isMethod(methodCode)) {
									allMethods.append(method);
									a ++;
								}
//							}
						}
						methodSymble = false;
						methodBody = false;
						singleMethod.setLength(0);
						methodCode.setLength(0);
					} else if ("#METHOD_BODY#========================".equals(line)) {
						methodSymble = true;
					} else if (methodSymble) {
						if (methodBody) {
							methodCode.append(line).append("\n");
						} else {
							methodBody = true;
						}
					}
					singleMethod.append(line).append("\n");
				}
				reader.close();
				fileReader.close();
				String method = singleMethod.toString();
//				if (!method.contains("@Override") && !method.contains("@Deprecated")) {
					if (isMethod(methodCode)) {
						allMethods.append(method);
						a ++;
					}
//				}
				FileHelper.outputToFile("../OUTPUT/LiveStudy/results2.txt", allMethods, true);
			}
		}
		
		System.out.println(a);// 7361,4430
		
	}

	private static int pos = 0;
	
	private static boolean isMethod(StringBuilder methodCode) {
		String method = "public class T {\n" + methodCode + "}\n";
		ITree newMethodTree = new ASTGenerator().generateTreeForJavaFileContent(method, TokenType.EXP_JDT);
		String methodName = readEndPositionOfMethodName(newMethodTree);
		if (pos == 0) return true;
		String code = method.substring(pos);
		pos = 0;
		return (code.contains("." + methodName + "(") || code.contains(" " + methodName + "(")) ? false : true;
	}
	
	private static String readEndPositionOfMethodName(ITree methodTree) {
		List<ITree> children = methodTree.getChildren();
		for (ITree child : children) {
			if (child.getType() == 42 && child.getLabel().contains("MethodName:")) {// SimpleName
				pos = child.getPos() + child.getLength();
				return child.getLabel().substring(11);
			} else {
				String name = readEndPositionOfMethodName(child);
				if (name.equals("")) continue;
				else return name;
			}
		}
		return "";
	}

}
