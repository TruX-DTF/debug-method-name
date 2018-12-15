package edu.lu.uni.serval.sricmn.info;

public class PredictToken implements Comparable<PredictToken> {

	private String token;
	private Integer times = 0;
	private Double similarity = 0.0;
	
	public PredictToken(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public int getTimes() {
		return times;
	}
	
	public void setTimes(int times) {
		this.times = times;
	}
	
	public Double getSimilarity() {
		return similarity;
	}
	
	public void setSimilarity(Double similarity) {
		this.similarity = similarity;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PredictToken) {
			PredictToken pt = (PredictToken) obj;
			return this.token.equals(pt.token);
		}
		return false;
	}

	@Override
	public int compareTo(PredictToken pt) {
		int compareResult = this.similarity.compareTo(pt.similarity);
		if (compareResult == 0) {
			compareResult = this.times.compareTo(pt.times);
		}
		return compareResult;
	}
	
	
}
