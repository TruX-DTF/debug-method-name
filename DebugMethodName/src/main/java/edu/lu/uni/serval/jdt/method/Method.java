package edu.lu.uni.serval.jdt.method;

import java.util.Map;

import org.eclipse.jdt.core.dom.Type;

public class Method extends AbstractMethod {
	
	private int startLine;
	private int endLine;
	private int startPosition;
	private int endPosition;
	private String signature;
	private String exceptionStr;

	public Method(String projectName, String packageName, String className, Type returnType, String methodName, Map<String, Type> parameters, String body, boolean constructor) {
		this.key = projectName + ":" + packageName + ":" + className + ":" + methodName + ":" + getParameters(parameters);
		this.returnType = returnType;
		this.name = methodName;
		this.parameters = parameters;
		this.body = body;
		this.constructor = constructor;
	}
	
	public Method(String projectName, String packageName, String className, String returnType, String methodName, Map<String, String> parameters, String body, boolean constructor) {
		this.key = projectName + ":" + packageName + ":" + className + ":" + methodName + ":" + getParameters(parameters);
		this.returnTypeStr = returnType;
		this.name = methodName;
		this.parametersStr = parameters;
		this.body = body;
		this.constructor = constructor;
	}
	
	public Method(String projectName, String packageName, String className, String returnType, String methodName, String body, boolean constructor, String parameters) {
		this.key = projectName + ":" + packageName + ":" + className + ":" + methodName + ":" + parameters;
		this.returnTypeStr = returnType;
		this.name = methodName;
		this.parametersStr = null;
		this.body = body;
		this.constructor = constructor;
		this.argumentsStr = parameters;
	}
	
	private String getParameters(Map<String, ?> parameters) {
		StringBuilder parameterStr = new StringBuilder();
		if (parameters == null || parameters.size() == 0) {
			parameterStr.append("null");
		} else {
			for (Map.Entry<String, ?> entry : parameters.entrySet()) {
				if (parameterStr.length() > 0) {
					parameterStr.append("+").append(entry.getValue().toString()).append("+").append(entry.getKey());
				} else {
					parameterStr.append(entry.getValue().toString()).append("+").append(entry.getKey());
				}
			}
		}
		return parameterStr.toString();
	}

	public int getStartLine() {
		return startLine;
	}

	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	public int getEndLine() {
		return endLine;
	}

	public void setEndLine(int endLine) {
		this.endLine = endLine;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSignature() {
		return signature;
	}

	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}

	public void setEndPosition(int endPosition) {
		this.endPosition = endPosition;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public int getEndPosition() {
		return endPosition;
	}

	public void setExceptionStr(String exceptionStr) {
		this.exceptionStr = exceptionStr;
	}
	
	public String getExceptionStr() {
		return this.exceptionStr;
	}
	
}
