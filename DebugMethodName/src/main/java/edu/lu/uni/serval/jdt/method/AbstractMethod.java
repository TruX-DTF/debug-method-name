package edu.lu.uni.serval.jdt.method;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeParameter;

public abstract class AbstractMethod implements IMethod {
	
	protected String projectName;
	protected String packageName;
	protected String className;
	protected List<Modifier> modifiers;
	protected Type returnType = null;  // Return type of this method.
	protected String returnTypeStr;
	protected List<TypeParameter> typeParameters; //generic types
	protected String name;      // the method name.
	protected Map<String, Type> parameters;      // the parameters/arguments of this method
	protected Map<String, String> parametersStr;      // the parameters/arguments of this method
	protected String argumentsStr; // Type1+var1+Type2+var2 ...
	protected String bodyCodeTokens;
	protected String bodyCodeRawTokens;
	
	protected String key;   // projectName + ":" + packageName + ":" + className + ":" + methodName + ":" + parameters;
	protected String body;
	protected Javadoc javadoc;
	protected boolean constructor;

	@Override
	public String getKey() {
		return key + ":" + (returnType == null ? returnTypeStr : returnType);
	}

	@Override
	public Type getReturnType() {
		return returnType;
	}
	
	@Override
	public String getReturnTypeString() {
		return returnTypeStr;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Map<String, Type> getParameters()	{
		return parameters;
	}
	
	public Map<String, String> getParameterStrings() {
		return parametersStr;
	}
	
	public String getArgumentsStr() {
		return this.argumentsStr;
	}

	@Override
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}

	public void setBodyCodeTokens(String bodyCodeTokens) {
		this.bodyCodeTokens = bodyCodeTokens;
	}
	
	public String getBodyCodeTokens() {
		return bodyCodeTokens;
	}

	public String getBodyCodeRawTokens() {
		return bodyCodeRawTokens;
	}

	public void setBodyCodeRawTokens(String bodyCodeRawTokens) {
		this.bodyCodeRawTokens = bodyCodeRawTokens;
	}

	@Override
	public boolean isConstructor() {
		return constructor;
	}

	@Override
	public String toString() {
		if (returnType == null) {
			return "ID: " + key + "\n" + 
					"Return Type: void\n" + 
					"MethodName: " + name + "\n" +
					"Constructor: " + constructor  + "\n" +
					"Parameters: " + parametersStr + "\n" +
					"MethodBody: " + body + "\n";
		} else {
			return "ID: " + key + "\n" + 
					"Return Type: " + returnType + "\n" + 
					"MethodName: " + name + "\n" +
					"Constructor: " + constructor  + "\n" +
					"Parameters: " + parameters + "\n" +
					"MethodBody: " + body + "\n";
		}
	}

}
