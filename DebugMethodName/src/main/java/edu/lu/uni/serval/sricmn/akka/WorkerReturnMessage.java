package edu.lu.uni.serval.sricmn.akka;

import java.util.Map;

public class WorkerReturnMessage {
	
	private String type;
	private Map<Integer, Map<Integer, Double>> topPredictedTokens;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Map<Integer, Map<Integer, Double>> getTopPredictedTokens() {
		return topPredictedTokens;
	}
	
	public void setTopPredictedTokens(Map<Integer, Map<Integer, Double>> topPredictedTokens) {
		this.topPredictedTokens = topPredictedTokens;
	}
	
}
