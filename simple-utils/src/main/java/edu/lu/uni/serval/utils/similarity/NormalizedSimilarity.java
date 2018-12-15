package edu.lu.uni.serval.utils.similarity;

public class NormalizedSimilarity {

	public static Double normalize(int distance, int length1, int length2) {
		return 2d * distance / (length1 + length2);
	}
	
	public static Double normalize(int distance, int length) {
		return (double) distance / length;
	}
}
