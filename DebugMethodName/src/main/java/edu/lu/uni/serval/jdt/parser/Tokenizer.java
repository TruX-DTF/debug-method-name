package edu.lu.uni.serval.jdt.parser;

import java.util.List;
import java.util.Locale;

public class Tokenizer {

	public StringBuilder getAbstractTokensDeepFirst(SimpleTree simpleTree) {
		StringBuilder tokens = new StringBuilder();
		List<SimpleTree> children = simpleTree.getChildren();
		String astNodeType = simpleTree.getNodeType();
		
		if (children.isEmpty()) { // BreakStatement, ContinueStatement, ReturnStatement, TryStatement
			if (astNodeType.endsWith("Statement")) {
				String label = astNodeType;
				label = label.substring(0, label.lastIndexOf("S")).toLowerCase();
				tokens.append(astNodeType).append(" ").append(label).append(" ");
			} else if ("SuperConstructorInvocation".equals(astNodeType)) {
				tokens.append(astNodeType).append(" super ");
			} else if ("ConstructorInvocation".equals(astNodeType)) {
				tokens.append(astNodeType).append(" this ");
			} else if ("StringLiteral".equals(astNodeType)) {
				tokens.append(astNodeType).append(" stringLiteral ");
			} else if ("CharacterLiteral".equals(astNodeType)) {
				tokens.append(astNodeType).append(" charLiteral ");
			} else if ("ArrayInitializer".equals(astNodeType)) {
				tokens.append(astNodeType).append(" arrayInitializer ");
			} else if ("LambdaExpression".equals(astNodeType)) {
				tokens.append(astNodeType).append(" lambda ");
			} else if ("NumberLiteral".equals(astNodeType)) {
				tokens.append(astNodeType).append(" numberLiteral ");
			} else {
				tokens.append(astNodeType).append(" ").append(simpleTree.getLabel()).append(" ");
			}
		} else {
			if ("AssertStatement".equals(astNodeType) || "DoStatement".equals(astNodeType)
					|| "ForStatement".equals(astNodeType) || "IfStatement".equals(astNodeType)
					|| "ReturnStatement".equals(astNodeType) || "SwitchStatement".equals(astNodeType) 
					|| "SynchronizedStatement".equals(astNodeType) || "ThrowStatement".equals(astNodeType)
					|| "TryStatement".equals(astNodeType) || "WhileStatement".equals(astNodeType)) {
				String label = astNodeType;
				label = label.substring(0, label.lastIndexOf("S")).toLowerCase(Locale.ENGLISH);
				tokens.append(astNodeType).append(" ").append(label).append(" ");
			} else if ("EnhancedForStatement".equals(astNodeType)) {
				tokens.append(astNodeType).append(" for ");
			} else if ("CatchClause".equals(astNodeType)) {
				tokens.append(astNodeType).append(" catch ");
			} else if ("SwitchCase".equals(astNodeType)) {
				tokens.append(astNodeType).append(" case ");
			} else if ("SuperConstructorInvocation".equals(astNodeType)) {
				tokens.append(astNodeType).append(" super ");
			} else if ("ConstructorInvocation".equals(astNodeType)) {
				tokens.append(astNodeType).append(" this ");
			} else if ("FinallyBody".equals(astNodeType)) {
				tokens.append(astNodeType).append(" finally ");
			} else if ("LabeledStatement".equals(astNodeType)) {
				tokens.append("LabeledStatement ").append(simpleTree.getLabel());
			} else if ("SuperMethodInvocation".equals(astNodeType)) {
				tokens.append(astNodeType).append(" super ");
			} else if ("MethodName".equals(astNodeType)) {
				tokens.append("MethodName ").append(simpleTree.getLabel()).append(" ");
			}
			
			if ("ArrayInitializer".equals(astNodeType)) {
				tokens.append(astNodeType).append(" arrayInitializer ");
			} else {
				for (SimpleTree child : children) {
					tokens.append(getAbstractTokensDeepFirst(child));
				}
			}
		}
		return tokens;
	}
	
	public StringBuilder getTokensDeepFirst(SimpleTree simpleTree) {
		StringBuilder tokens = new StringBuilder();
		List<SimpleTree> children = simpleTree.getChildren();
		String astNodeType = simpleTree.getNodeType();
		
		if (children.isEmpty()) { // BreakStatement, ContinueStatement, ReturnStatement, TryStatement
			if (astNodeType.endsWith("Statement")) {
				String label = astNodeType;
				label = label.substring(0, label.lastIndexOf("S")).toLowerCase();
				tokens.append(astNodeType).append(" ").append(label).append(" ");
			} else if ("SuperConstructorInvocation".equals(astNodeType)) {
				tokens.append(astNodeType).append(" super ");
			} else if ("ConstructorInvocation".equals(astNodeType)) {
				tokens.append(astNodeType).append(" this ");
			} else if ("StringLiteral".equals(astNodeType)) {
				String label = simpleTree.getLabel();
				tokens.append(astNodeType).append(" ").append(label.replace(" ", "")).append(" ");
			} else if ("CharacterLiteral".equals(astNodeType)) {
//				char c = ' ';//FIXME 
				tokens.append(astNodeType).append(" ").append(simpleTree.getLabel()).append(" ");
			} else if ("ArrayInitializer".equals(astNodeType)) {
				tokens.append(astNodeType).append(" arrayInitializer ");
			} else if ("LambdaExpression".equals(astNodeType)) {
				tokens.append(astNodeType).append(" lambda ");
			} else if ("NumberLiteral".equals(astNodeType)) {
				tokens.append(astNodeType).append(" ").append(simpleTree.getLabel()).append(" ");
			} else {
				tokens.append(astNodeType).append(" ").append(simpleTree.getLabel()).append(" ");
			}
		} else {
			if ("AssertStatement".equals(astNodeType) || "DoStatement".equals(astNodeType)
					|| "ForStatement".equals(astNodeType) || "IfStatement".equals(astNodeType)
					|| "ReturnStatement".equals(astNodeType) || "SwitchStatement".equals(astNodeType) 
					|| "SynchronizedStatement".equals(astNodeType) || "ThrowStatement".equals(astNodeType)
					|| "TryStatement".equals(astNodeType) || "WhileStatement".equals(astNodeType)) {
				String label = astNodeType;
				label = label.substring(0, label.lastIndexOf("S")).toLowerCase(Locale.ENGLISH);
				tokens.append(astNodeType).append(" ").append(label).append(" ");
			} else if ("EnhancedForStatement".equals(astNodeType)) {
				tokens.append(astNodeType).append(" for ");
			} else if ("CatchClause".equals(astNodeType)) {
				tokens.append(astNodeType).append(" catch ");
			} else if ("SwitchCase".equals(astNodeType)) {
				tokens.append(astNodeType).append(" case ");
			} else if ("SuperConstructorInvocation".equals(astNodeType)) {
				tokens.append(astNodeType).append(" super ");
			} else if ("ConstructorInvocation".equals(astNodeType)) {
				tokens.append(astNodeType).append(" this ");
			} else if ("FinallyBody".equals(astNodeType)) {
				tokens.append(astNodeType).append(" finally ");
			} else if ("LabeledStatement".equals(astNodeType)) {
				tokens.append("LabeledStatement ").append(simpleTree.getLabel());
			} else if ("SuperMethodInvocation".equals(astNodeType)) {
				tokens.append(astNodeType).append(" super ");
			} else if ("MethodName".equals(astNodeType)) {
				tokens.append("MethodName ").append(simpleTree.getLabel()).append(" ");
			}
			
//			if ("ArrayInitializer".equals(astNodeType)) {
//				tokens.append(astNodeType).append(" arrayInitializer ");
//			} else {
//				for (SimpleTree child : children) {
//					tokens.append(getAbstractTokensDeepFirst(child));
//				}
//			}
			for (SimpleTree child : children) {
				tokens.append(getAbstractTokensDeepFirst(child));
			}
		}
		return tokens;
	}
	
	public StringBuilder getRawTokens(SimpleTree simpleTree) {
		StringBuilder tokens = new StringBuilder();
		List<SimpleTree> children = simpleTree.getChildren();
		String astNodeType = simpleTree.getNodeType();
		
		if (children.isEmpty()) { // BreakStatement, ContinueStatement, ReturnStatement, TryStatement
			if (astNodeType.endsWith("Statement")) {
				String label = astNodeType;
				label = label.substring(0, label.lastIndexOf("S")).toLowerCase();
				tokens.append(label).append(" ");
			} else if ("SuperConstructorInvocation".equals(astNodeType)) {
				tokens.append(" super ");
			} else if ("ConstructorInvocation".equals(astNodeType)) {
				tokens.append(" this ");
			} else if ("StringLiteral".equals(astNodeType)) {
				String label = simpleTree.getLabel();
				tokens.append(label.replace(" ", "")).append(" ");
			} else if ("CharacterLiteral".equals(astNodeType)) {
//				char c = ' ';//FIXME 
				tokens.append(simpleTree.getLabel()).append(" ");
			} else if ("ArrayInitializer".equals(astNodeType)) {
			} else if ("LambdaExpression".equals(astNodeType)) {
			} else if ("NumberLiteral".equals(astNodeType)) {
				tokens.append(simpleTree.getLabel()).append(" ");
			} else {
				tokens.append(simpleTree.getLabel()).append(" ");
			}
		} else {
			if ("AssertStatement".equals(astNodeType) || "DoStatement".equals(astNodeType)
					|| "ForStatement".equals(astNodeType) || "IfStatement".equals(astNodeType)
					|| "ReturnStatement".equals(astNodeType) || "SwitchStatement".equals(astNodeType) 
					|| "SynchronizedStatement".equals(astNodeType) || "ThrowStatement".equals(astNodeType)
					|| "TryStatement".equals(astNodeType) || "WhileStatement".equals(astNodeType)) {
				String label = astNodeType;
				label = label.substring(0, label.lastIndexOf("S")).toLowerCase(Locale.ENGLISH);
				tokens.append(label).append(" ");
			} else if ("EnhancedForStatement".equals(astNodeType)) {
				tokens.append(" for ");
			} else if ("CatchClause".equals(astNodeType)) {
				tokens.append(" catch ");
			} else if ("SwitchCase".equals(astNodeType)) {
				tokens.append(" case ");
			} else if ("SuperConstructorInvocation".equals(astNodeType)) {
				tokens.append(" super ");
			} else if ("ConstructorInvocation".equals(astNodeType)) {
				tokens.append(" this ");
			} else if ("FinallyBody".equals(astNodeType)) {
				tokens.append(" finally ");
			} else if ("LabeledStatement".equals(astNodeType)) {
				tokens.append(simpleTree.getLabel());
			} else if ("SuperMethodInvocation".equals(astNodeType)) {
				tokens.append(" super ");
			} else if ("MethodName".equals(astNodeType)) {
				tokens.append(simpleTree.getLabel()).append(" ");
			}
			
			for (SimpleTree child : children) {
				tokens.append(getRawTokens(child));
			}
		}
		return tokens;
	}
}
