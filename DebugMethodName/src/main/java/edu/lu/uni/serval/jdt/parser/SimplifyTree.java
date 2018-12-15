package edu.lu.uni.serval.jdt.parser;

import java.util.ArrayList;
import java.util.List;

import edu.lu.uni.serval.jdt.tree.ITree;
import edu.lu.uni.serval.utils.ASTNodeMap;
import edu.lu.uni.serval.utils.Checker;

/**
 * Simplify the ITree of source code into a simple tree.
 * 
 * @author kui.liu
 *
 */
public class SimplifyTree {
	
	public SimpleTree simplifyTree(ITree tree, SimpleTree parent) {
		SimpleTree simpleTree = new SimpleTree();

		String label = tree.getLabel();
		String astNode = ASTNodeMap.map.get(tree.getType());

		List<ITree> children = tree.getChildren();
		if (children.size() > 0) {
			simpleTree.setNodeType(astNode);
			if (astNode.endsWith("Type")) {
				simpleTree.setLabel(label.replace(" ", ""));
			} else {
				if ((astNode.equals("SimpleName") || astNode.equals("MethodInvocation"))) {
					if (label.startsWith("MethodName:")) {
						simpleTree.setNodeType("MethodName");
						label = label.substring(11);
						int argusIndex = label.indexOf(":[");
						if (argusIndex > 0) {
							label = label.substring(0, argusIndex);
						}
					} else if (label.startsWith("ClassName:")) {
						simpleTree.setNodeType("ClassName");
						label = label.substring(10);
					}
					simpleTree.setLabel(label);
				} else {
					simpleTree.setLabel(astNode);
				}
				List<SimpleTree> subTrees = new ArrayList<>();
				for (ITree child : children) {
					subTrees.add(simplifyTree(child, simpleTree));
				}
				simpleTree.setChildren(subTrees);
			}
		} else {
			if (astNode.endsWith("Name")) {
				// variableName, methodName, QualifiedName
				if (label.startsWith("MethodName:")) { // <MethodName, name>
					simpleTree.setNodeType("MethodName");
					label = label.substring(11);
					int argusIndex = label.indexOf(":[");
					if (argusIndex > 0) {
						label = label.substring(0, argusIndex);
					}
					simpleTree.setLabel(label);
				} else if (label.startsWith("ClassName:")) {
					simpleTree.setNodeType("ClassName");
					label = label.substring(10);
					simpleTree.setLabel(label);
				} else if (label.startsWith("Name:")) {
					label = label.substring(5);
					char firstChar = label.charAt(0);
					if (Character.isUpperCase(firstChar)) {
						simpleTree.setNodeType("Name");
						simpleTree.setLabel(label);
					} else {// variableName: <VariableName, canonicalName>
						simpleTree.setNodeType("VariableName");
						simpleTree.setLabel(label);
					}
				} else {// variableName: <VariableName, canonicalName>
					simpleTree.setNodeType("VariableName");
					simpleTree.setLabel(label);
				}
			} else {
				simpleTree.setNodeType(astNode);
				if (astNode.endsWith("Type")) {
					simpleTree.setLabel(label.replace(" ", ""));
				} else if (astNode.startsWith("Type")) {
					simpleTree.setLabel(label.replace(" ", ""));
				} else if ((astNode.equals("SimpleName") || astNode.equals("MethodInvocation")) && label.startsWith("MethodName:")) {
					simpleTree.setNodeType("MethodName");
					label = label.substring(11);
					int argusIndex = label.indexOf(":[");
					if (argusIndex > 0) {
						label = label.substring(0, argusIndex);
					}
					simpleTree.setLabel(label);
				} else {
					simpleTree.setLabel(label.replaceAll(" ", ""));
				}
			}
		}
		
		simpleTree.setParent(parent);
		return simpleTree;
	}
	
	public SimpleTree canonicalizeSourceCodeTree(ITree tree, SimpleTree parent) {
		SimpleTree simpleTree = new SimpleTree();

		String label = tree.getLabel();
		String astNode = ASTNodeMap.map.get(tree.getType());

		List<ITree> children = tree.getChildren();
		if (children.size() > 0) {
			simpleTree.setNodeType(astNode);
			if (astNode.endsWith("Type")) {
				simpleTree.setLabel(canonicalizeTypeStr(label).replaceAll(" ", ""));
			} else {
				if ((astNode.equals("SimpleName") || astNode.equals("MethodInvocation"))) {
					if (label.startsWith("MethodName:")) {
						simpleTree.setNodeType("MethodName");
						label = label.substring(11);
						int argusIndex = label.indexOf(":[");
						if (argusIndex > 0) {
							label = label.substring(0, argusIndex);
						}
					} else if (label.startsWith("ClassName:")) {
						simpleTree.setNodeType("ClassName");
						label = label.substring(10);
					}
					simpleTree.setLabel(label);
				} else {
					simpleTree.setLabel(astNode);
				}
				List<SimpleTree> subTrees = new ArrayList<>();
				for (ITree child : children) {
					subTrees.add(canonicalizeSourceCodeTree(child, simpleTree));
				}
				simpleTree.setChildren(subTrees);
			}
		} else {
			if (astNode.endsWith("Name")) {
				// variableName, methodName, QualifiedName
				if (label.startsWith("MethodName:")) { // <MethodName, name>
					simpleTree.setNodeType("MethodName");
					label = label.substring(11);
					int argusIndex = label.indexOf(":[");
					if (argusIndex > 0) {
						label = label.substring(0, argusIndex);
					}
					simpleTree.setLabel(label);
				} else if (label.startsWith("ClassName:")) {
					simpleTree.setNodeType("ClassName");
					label = label.substring(10);
					simpleTree.setLabel(label);
				} else if (label.startsWith("Name:")) {
					label = label.substring(5);
					char firstChar = label.charAt(0);
					if (Character.isUpperCase(firstChar)) {
						simpleTree.setNodeType("Name");
						simpleTree.setLabel(label);
					} else {// variableName: <VariableName, canonicalName>
						simpleTree.setNodeType("VariableName");
						simpleTree.setLabel(canonicalVariableName(label, tree));
					}
				} else {// variableName: <VariableName, canonicalName>
					simpleTree.setNodeType("VariableName");
					simpleTree.setLabel(canonicalVariableName(label, tree));
				}
			} else {
				simpleTree.setNodeType(astNode);
				if (astNode.endsWith("Type")) {
					simpleTree.setLabel(canonicalizeTypeStr(label).replaceAll(" ", ""));
				} else if (astNode.startsWith("Type")) {
					simpleTree.setLabel(canonicalizeTypeStr(label).replaceAll(" ", ""));
				} else if ((astNode.equals("SimpleName") || astNode.equals("MethodInvocation")) && label.startsWith("MethodName:")) {
					simpleTree.setNodeType("MethodName");
					label = label.substring(11);
					int argusIndex = label.indexOf(":[");
					if (argusIndex > 0) {
						label = label.substring(0, argusIndex);
					}
					simpleTree.setLabel(label);
				} else {
					simpleTree.setLabel(label.replaceAll(" ", ""));
				}
			}
		}
		
		simpleTree.setParent(parent);
		return simpleTree;
	}
	
	private String canonicalVariableName(String label, ITree tree) {
		ITree parent = tree.getParent();
		if (parent == null) {
			return label;
		} else {
			String matchStr = null;
			int parentType = parent.getType();
			if (parentType == 44) { // SingleVariableDeclaration
				matchStr = matchSingleVariableDeclaration(parent, label);
			} else if (parentType == 23 || parentType == 58 || parentType == 60) {
				//FieldDeclaration, VariableDeclarationExpression, VariableDeclarationStatement
				matchStr = matchVariableDeclarationExpression(parent, label);
			} else if (parentType == 31) { // MethodDeclaration
				List<ITree> children = parent.getChildren();
				int index = children.indexOf(tree);
				for (int i = index - 1; i >=0; i --) {
					ITree child = children.get(i);
					int childType = child.getType();
					if (childType == 60) { // VariableDeclarationStatement
						matchStr = matchVariableDeclarationExpression(child, label);
					} else if (childType == 44) { // SingleVariableDeclaration
						matchStr = matchSingleVariableDeclaration(child, label);
					}
					if (matchStr != null) break;
				}
			} else if (parentType ==70 || parentType == 24 ||parentType == 12 || parentType == 54) {
				// EnhancedForStatement, ForStatement, CatchClause, TryStatement
				matchStr = matchStatements(parentType, parent, label);
			} else if (parentType == 55) { // TypeDeclaration: Class Declaration
				List<ITree> children = parent.getChildren();
				int index = children.indexOf(tree);
				for (int i = index - 1; i >=0; i --) {
					ITree child = children.get(i);
					if (child.getType() == 23) { // FieldDeclaration
						matchStr = matchVariableDeclarationExpression(child, label);
						if (matchStr != null) break;
					}
				}
			} else if (parentType == 8) { // Block body
				List<ITree> children = parent.getChildren();
				int index = children.indexOf(tree);
				for (int i = index - 1; i >=0; i --) {
					ITree child = children.get(i);
					if (child.getType() == 60) { // VariableDeclarationStatement
						matchStr = matchVariableDeclarationExpression(child, label);
						if (matchStr != null) break;
					}
				}
			}
			
			if (matchStr != null) {
				return matchStr;
			} else {
				return canonicalVariableName(label, parent);
			}
		}
	}
	
	private String matchStatements(int typeInt, ITree tree, String label) {
		String matchStr = null;
		if (typeInt == 70) { // EnhancedForStatement
			matchStr = matchSingleVariableDeclaration(tree.getChild(0), label);
		} else if (typeInt == 24) { // ForStatement
			List<ITree> children = tree.getChildren();
			for (ITree child : children) {
				if (child.getType() == 58) {
					matchStr = matchVariableDeclarationExpression(child, label);
					if (matchStr != null) break;
				} else {
					break;
				}
			}
		} else if (typeInt == 12) { // CatchClause
			matchStr = matchSingleVariableDeclaration(tree.getChild(0), label);
		} else if (typeInt == 54) { // TryStatement
			List<ITree> children = tree.getChildren();
			for (ITree child : children) {
				if (child.getType() == 58) { //VariableDeclarationExpression
					matchStr = matchVariableDeclarationExpression(tree, label);
					if (matchStr != null) break;
				} else {
					break;
				}
			}
		}
		return null;
	}

	private String matchVariableDeclarationExpression(ITree variable, String label) {
		List<ITree> children = variable.getChildren();
		ITree type = null;
		for (int i = 0, size = children.size(); i < size; i ++) {
			ITree child = children.get(i);
			if (child.getType() == 59) {// VariableDeclarationFragment
				if (type == null) {
					type = children.get(i - 1);
				}
				ITree simpleName = child.getChild(0);
				if (simpleName.getLabel().equals(label)) {
					String typeStr = canonicalizeTypeStr(type.getLabel());
					label = typeStr.toLowerCase() + "Var";
					return label;
				}
			}
		}
		return null;
	}

	private String matchSingleVariableDeclaration(ITree singleVariable, String label) {
		List<ITree> children = singleVariable.getChildren();
		for (int i = 0, size = children.size(); i < size; i ++) {
			ITree child = children.get(i);
			if (child.getType() == 42) { // SimpleName
				if (child.getLabel().equals(label)) {
					ITree type = children.get(i - 1);
					String typeStr = canonicalizeTypeStr(type.getLabel());
					label = typeStr.toLowerCase() + "Var";
					return label;
				}
				break;
			}
		}
		return null;
	}

	private String canonicalizeTypeStr(String label) {
		String typeStr = label;
		int index1 = typeStr.indexOf("<");
		if (index1 != -1) {
			typeStr = typeStr.substring(0, index1);
		}
		index1 = typeStr.lastIndexOf(".");
		if (index1 != -1) {
			typeStr = typeStr.substring(index1 + 1);
		}
		return typeStr;
	}

	public SimpleTree simplifyTreeWithoutBodyCode(ITree tree, SimpleTree parent) {
		SimpleTree simpleTree = new SimpleTree();

		String label = tree.getLabel();
		String astNode = ASTNodeMap.map.get(tree.getType());

		List<ITree> children = tree.getChildren();
		if (children.size() > 0) {
			simpleTree.setNodeType(astNode);
			if (astNode.endsWith("Type")) {
				simpleTree.setLabel(label.replace(" ", ""));
			} else {
				if ((astNode.equals("SimpleName") || astNode.equals("MethodInvocation"))) {
					if (label.startsWith("MethodName:")) {
						simpleTree.setNodeType("MethodName");
						label = label.substring(11);
						int argusIndex = label.indexOf(":[");
						if (argusIndex > 0) {
							label = label.substring(0, argusIndex);
						}
					} else if (label.startsWith("ClassName:")) {
						simpleTree.setNodeType("ClassName");
						label = label.substring(10);
					}
					simpleTree.setLabel(label);
				} else {
					simpleTree.setLabel(astNode);
				}
				List<SimpleTree> subTrees = new ArrayList<>();
				if ("DoStatement".equals(astNode)) {
					for (ITree child : children) {
						if (Checker.isStatement(child.getType())) continue;
						subTrees.add(simplifyTree(child, simpleTree));
					}
				} else {
					for (ITree child : children) {
						if (Checker.isStatement(child.getType())) break;
						subTrees.add(simplifyTree(child, simpleTree));
					}
				}
				simpleTree.setChildren(subTrees);
			}
		} else {
			if (astNode.endsWith("Name")) {
				// variableName, methodName, QualifiedName
				if (label.startsWith("MethodName:")) { // <MethodName, name>
					simpleTree.setNodeType("MethodName");
					label = label.substring(11);
					int argusIndex = label.indexOf(":[");
					if (argusIndex > 0) {
						label = label.substring(0, argusIndex);
					}
					simpleTree.setLabel(label);
				} else if (label.startsWith("ClassName:")) {
					simpleTree.setNodeType("ClassName");
					label = label.substring(10);
					simpleTree.setLabel(label);
				} else if (label.startsWith("Name:")) {
					label = label.substring(5);
					char firstChar = label.charAt(0);
					if (Character.isUpperCase(firstChar)) {
						simpleTree.setNodeType("Name");
						simpleTree.setLabel(label);
					} else {// variableName: <VariableName, canonicalName>
						simpleTree.setNodeType("VariableName");
						simpleTree.setLabel(label);
					}
				} else {// variableName: <VariableName, canonicalName>
					simpleTree.setNodeType("VariableName");
					simpleTree.setLabel(label);
				}
			} else {
				simpleTree.setNodeType(astNode);
				if (astNode.endsWith("Type")) {
					simpleTree.setLabel(label.replace(" ", ""));
				} else if (astNode.startsWith("Type")) {
					simpleTree.setLabel(label.replace(" ", ""));
				} else if ((astNode.equals("SimpleName") || astNode.equals("MethodInvocation")) && label.startsWith("MethodName:")) {
					simpleTree.setNodeType("MethodName");
					label = label.substring(11);
					int argusIndex = label.indexOf(":[");
					if (argusIndex > 0) {
						label = label.substring(0, argusIndex);
					}
					simpleTree.setLabel(label);
				} else {
					simpleTree.setLabel(label.replaceAll(" ", ""));
				}
			}
		}
		
		simpleTree.setParent(parent);
		return simpleTree;
	}

}
