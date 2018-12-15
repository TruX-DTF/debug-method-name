package edu.lu.uni.serval.sricmn.akka;

import java.util.List;
import java.util.Map;

import edu.lu.uni.serval.sricmn.info.MethodInfo;

public class WorkerMessage {
	
	private int workerId;
	private List<Double[]> subTestingFeatures;
	private List<Double[]> subRenamedFeatures;
	private Map<String, List<MethodInfo>> trainingFeatures;
	private List<MethodInfo> subTestingMethodInfo;
	private List<MethodInfo> subRenamedMethodInfo;
	private int fromIndex;
	private int toIndex;
	private List<Double[]> trainingMethodNameFeatures;
	
	private String similarityType;
	private boolean isSubToken;

	public WorkerMessage(int workerId, List<Double[]> subTestingFeatures, List<Double[]> subRenamedFeatures,
			Map<String, List<MethodInfo>> trainingFeatures, int fromIndex, int toIndex, String similarityType) {
		super();
		this.workerId = workerId;
		this.subTestingFeatures = subTestingFeatures;
		this.subRenamedFeatures = subRenamedFeatures;
		this.trainingFeatures = trainingFeatures;
		this.fromIndex = fromIndex;
		this.toIndex = toIndex;
		this.similarityType = similarityType;
	}
	
	public List<Double[]> getTrainingMethodNameFeatures() {
		return trainingMethodNameFeatures;
	}

	public void setTrainingMethodNameFeatures(List<Double[]> trainingMethodNameFeatures) {
		this.trainingMethodNameFeatures = trainingMethodNameFeatures;
	}

	public List<MethodInfo> getSubTestingMethodInfo() {
		return subTestingMethodInfo;
	}

	public void setSubTestingMethodInfo(List<MethodInfo> subTestingMethodInfo) {
		this.subTestingMethodInfo = subTestingMethodInfo;
	}

	public List<MethodInfo> getSubRenamedMethodInfo() {
		return subRenamedMethodInfo;
	}

	public void setSubRenamedMethodInfo(List<MethodInfo> subRenamedMethodInfo) {
		this.subRenamedMethodInfo = subRenamedMethodInfo;
	}

	public int getFromIndex() {
		return fromIndex;
	}

	public int getToIndex() {
		return toIndex;
	}

	public int getWorkerId() {
		return workerId;
	}

	public List<Double[]> getSubTestingFeatures() {
		return subTestingFeatures;
	}

	public List<Double[]> getSubRenamedFeatures() {
		return subRenamedFeatures;
	}

	public Map<String, List<MethodInfo>> getTrainingFeatures() {
		return trainingFeatures;
	}
	
	public String getSimilarityType() {
		return similarityType;
	}
	
	public void setIsSubToken(boolean isSubToken) {
		this.isSubToken = isSubToken;
	}

	public boolean isSubToken() {
		return isSubToken;
	}

}
