package edu.lu.uni.serval.sricmn.info;

import java.io.Serializable;
import java.util.List;

public class MethodInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int index;
//	private String projectName;
//	private String packageName;
//	private String className;
	private String methodName;
	private String info;
	private String returnType;
	private List<String> methodNameTokens;
	private String methodBody;
	private Double[] bodyFeatures;
	private int listIndex;
	
	public MethodInfo(int index, String returnType, List<String> methodNameTokens) {
		super();
		this.index = index;
		this.returnType = returnType;
		this.methodNameTokens = methodNameTokens;
		
		for (String token : methodNameTokens) {
			methodName += token;
		}
	}
	
	public int getIndex() {
		return index;
	}
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getReturnType() {
		return returnType;
	}

	public List<String> getMethodNameTokens() {
		return methodNameTokens;
	}

	public String getMethodBody() {
		return methodBody;
	}

	public void setMethodBody(String methodBody) {
		this.methodBody = methodBody;
	}

	public Double[] getBodyFeatures() {
		return bodyFeatures;
	}

	public void setBodyFeatures(Double[] bodyFeatures) {
		this.bodyFeatures = bodyFeatures;
	}

	public void setListIndex(int listIndex) {
		this.listIndex = listIndex;
	}
	
	public int getListIndex() {
		return listIndex;
	}
}
