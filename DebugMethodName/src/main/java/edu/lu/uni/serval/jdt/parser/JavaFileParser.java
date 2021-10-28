package edu.lu.uni.serval.jdt.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import edu.lu.uni.serval.jdt.AST.ASTGenerator;
import edu.lu.uni.serval.jdt.AST.ASTGenerator.TokenType;
import edu.lu.uni.serval.jdt.method.Method;
import edu.lu.uni.serval.jdt.tree.ITree;
import edu.lu.uni.serval.utils.Checker;
import edu.lu.uni.serval.utils.FileHelper;

public class JavaFileParser {
	
	private String projectName;
	private String packageName;
	private CompilationUnit unit = null;
	private File javaFile;
	private List<Method> methods = new ArrayList<>();
	private boolean ignoreGetterAndSetterMethods = false;

	public List<Method> getMethods() {
		return methods;
	}

	public void setIgnoreGetterAndSetterMethods(boolean ignoreGetterAndSetterMethods) {
		this.ignoreGetterAndSetterMethods = ignoreGetterAndSetterMethods;
	}

	public void parseJavaFile(String projectName, File file) {
		this.javaFile = file;
		this.projectName = projectName;
		unit = new MyUnit().createCompilationUnit(file);
		try {
			packageName = unit.getPackage().getName().toString();
		} catch (Exception e) {
			packageName = file.getPath();
			System.err.println("Failed to get package name: " + packageName);
			int fromIndex = packageName.indexOf(projectName);
			if (fromIndex < 0) {
				fromIndex = packageName.indexOf("dataset") + 8;
			}
			packageName = packageName.substring(fromIndex, packageName.lastIndexOf("/")).replaceAll("/", ".");
		}
		
		ITree rootTree = new ASTGenerator().generateTreeForJavaFile(file, TokenType.EXP_JDT);
		identifyMethod(rootTree, "");
	}

	private void identifyMethod(ITree tree, String className) {
		List<ITree> children = tree.getChildren();
		
		for (ITree child : children) {
			int astNodeType = child.getType();
			
			if (Checker.isMethodDeclaration(astNodeType)) { // MethodDeclaration.
				identifyMethod(child, className);
				readMethodInfo(child, className);
			} else {
				String currentClassName = "";
				if (Checker.isTypeDeclaration(astNodeType)) { // TypdeDeclaration.
					String classNameLabel = readClassName(child);
					currentClassName = classNameLabel.substring(10);
				}
				if ("".equals(className)) {
					identifyMethod(child, currentClassName);
				} else {
					if ("".equals(currentClassName)) {
						identifyMethod(child, className);
					} else {
						identifyMethod(child, className + "$" + currentClassName);
					}
				}
			}
 		}
	}

	private String readClassName(ITree classNameTree) {
		String classNameLabel = "";
		List<ITree> children = classNameTree.getChildren();
		for (ITree child : children) {
			if (Checker.isSimpleName(child.getType())) { // SimpleName
				classNameLabel = child.getLabel();
				break;
			}
		}
		return classNameLabel;
	}

	private void readMethodInfo(ITree methodBodyTree, String className) {
		String methodNameInfo = methodBodyTree.getLabel();
		int indexOfMethodName = methodNameInfo.indexOf("MethodName:");
		String methodName = methodNameInfo.substring(indexOfMethodName);
		methodName = methodName.substring(11, methodName.indexOf(", "));
		
		if ("main".equals(methodName)) return;
		
		boolean isConstructor = false;
		String returnType = methodNameInfo.substring(methodNameInfo.indexOf("@@") + 2, indexOfMethodName - 2);
		int index = returnType.indexOf("@@tp:");
		if (index > 0) {
			returnType = returnType.substring(0, index - 2);
		}
		if ("=CONSTRUCTOR=".equals(returnType)) {// Constructor.
			isConstructor = true;
		}
		
		int startPosition = methodBodyTree.getPos();
		int endPosition = startPosition + methodBodyTree.getLength();
		
		String methodBodySourceCode = getMethodSourceCode(startPosition, endPosition);//getMethodSourceCode(methodBodyTree, startLine, endLine);
		
		MethodBodyTreeReader reader = new MethodBodyTreeReader();
		reader.readToken(methodBodyTree);
		String arguments = reader.argus;
		SimpleTree methodBodySimpleTree = reader.methodBodyStatementTrees;
		if (methodBodySimpleTree.getChildren().size() == 0) return;// empty method.
		String tokens = new Tokenizer().getAbstractTokensDeepFirst(methodBodySimpleTree).toString();
		List<ITree> children = methodBodyTree.getChildren();
		List<ITree> childStmts = new ArrayList<>();
		boolean isStatement = false;
		for (ITree child : children) {
			if (isStatement) {
				childStmts.add(child);
			} else {
				if (Checker.isStatement(child.getType())) {
					childStmts.add(child);
					isStatement = true;
				}
			}
		}
		//Remove getter and setter methods.
		if (ignoreGetterAndSetterMethods) {
			if (methodName.startsWith("get") || methodName.startsWith("set") || methodName.startsWith("is")) {
				if (childStmts.size() == 1) return;
			}
		}
		methodBodyTree.setChildren(childStmts);
		SimpleTree simpleTree = new SimplifyTree().simplifyTree(methodBodyTree, null);
		String rawTokens = new Tokenizer().getRawTokens(simpleTree).toString();
//		if (tokens.equals("Block Block")) return;
		
		Method method = new Method(projectName, packageName, className, returnType, methodName, methodBodySourceCode, isConstructor, arguments);
		method.setBodyCodeTokens(tokens);
		method.setBodyCodeRawTokens(rawTokens);
		if (!isConstructor) {// Constructor.
			methods.add(method);
		}
	}

	/**
	 * Read method body code.
	 * @param methodBodyTree
	 * @return
	 */
	private String getMethodSourceCode(int startPos, int endPos) {
		String javaCode = FileHelper.readFile(this.javaFile);
		return javaCode.substring(startPos, endPos);
	}

	public class MyUnit {
		
		public CompilationUnit createCompilationUnit(File javaFile) {
			char[] javaCode = readFileToCharArray(javaFile);
			ASTParser parser = createASTParser(javaCode);
			parser.setKind(ASTParser.K_COMPILATION_UNIT);
			CompilationUnit unit = (CompilationUnit) parser.createAST(null);
			
			return unit;
		}

		private ASTParser createASTParser(char[] javaCode) {
			ASTParser parser = ASTParser.newParser(AST.JLS8);
			parser.setSource(javaCode);

			return parser;
		}
		
		private char[] readFileToCharArray(File javaFile) {
			StringBuilder fileData = new StringBuilder();
			BufferedReader br = null;
			
			char[] buf = new char[10];
			int numRead = 0;
			try {
				FileReader fileReader = new FileReader(javaFile);
				br = new BufferedReader(fileReader);
				while ((numRead = br.read(buf)) != -1) {
					String readData = String.valueOf(buf, 0, numRead);
					fileData.append(readData);
					buf = new char[1024];
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null) {
						br.close();
						br = null;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fileData.length() > 0)
				return fileData.toString().toCharArray();
			else return new char[0];
		}
	}

	/**
	 * Read Parameters of each method, and simple tree of method body.
	 * 
	 * @author kui.liu
	 *
	 */
	public class MethodBodyTreeReader {
		public SimpleTree methodBodyStatementTrees = new SimpleTree();
		public String argus = "";
		
		public void readToken(ITree methodBodyTree) {
			String methodDeclarationLabel = methodBodyTree.getLabel();
			if (methodDeclarationLabel.endsWith("@@Argus:null")) {
				argus = "null";
			} else {
				argus = methodDeclarationLabel.substring(methodDeclarationLabel.indexOf("@@Argus:") + 8, methodDeclarationLabel.length() - 1).replace(" ", "");
				int expIndex = argus.indexOf("@@Exp:");
				if (expIndex > 0) {
					argus = argus.substring(0, expIndex - 1);
				}
			}
			
			simplifyTree(methodBodyTree);
		}

		public void simplifyTree(ITree methodBodyTree) {
			methodBodyStatementTrees.setNodeType("Block");
			methodBodyStatementTrees.setLabel("Block");
			methodBodyStatementTrees.setParent(null);
			SimpleTree methodBodySimpleTree = new SimplifyTree().canonicalizeSourceCodeTree(methodBodyTree, null);
			
			List<SimpleTree> stmts = new ArrayList<>();
			List<SimpleTree> children = methodBodySimpleTree.getChildren();
			boolean isStatement = false;
			for (SimpleTree child : children) {
				if (isStatement) {
					stmts.add(child);
				} else {
					String astNodeType = child.getNodeType();
					if (astNodeType.endsWith("Statement") || astNodeType.endsWith("ConstructorInvocation")) {
						stmts.add(child);
						isStatement = true;
					}
				}
			}
			
			methodBodyStatementTrees.setChildren(stmts);
		}
	}
	
}
