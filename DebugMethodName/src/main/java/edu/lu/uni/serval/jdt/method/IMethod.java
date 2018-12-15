package edu.lu.uni.serval.jdt.method;

import java.util.Map;

import org.eclipse.jdt.core.dom.Type;

public interface IMethod {
	
	public String getKey();
	
	public Type getReturnType();
	
	public String getReturnTypeString();
	
	public String getName();

	public Map<String, Type> getParameters();

	public String getBody();

	public boolean isConstructor();
}
