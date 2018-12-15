package edu.lu.uni.serval.utils;

public class DistanceCalculator {

	public enum DistanceFunction {
		EUCLIDEAN, COSINESIMILARITY, MANHATTAN
	}
	
	public Double calculateDistance(DistanceFunction distanceType, Double[] targetPoint, Double[] selfPoint) {
		double distance = 0;
		switch (distanceType) {
		case EUCLIDEAN:
			distance = euclideanDistance(targetPoint, selfPoint);
			break;
		case COSINESIMILARITY:
			distance = cosineSimilarityDistance(targetPoint, selfPoint);
			break;
		case MANHATTAN:
			distance = manhattanDistance(targetPoint, selfPoint);
			break;
		default:
			distance = euclideanDistance(targetPoint, selfPoint);
			break;
		}
		return distance;
	}

	public Double euclideanDistance(Double[] targetPoint, Double[] selfPoint) {
		double sum = 0.0;

		for (int i = 0, length = targetPoint.length; i < length; i++) {
			double diff = targetPoint[i].doubleValue() - selfPoint[i].doubleValue();
			sum += diff * diff;
		}
		return Double.valueOf(Math.sqrt(sum));
	}

	public Double cosineSimilarityDistance(Double[] targetPoint, Double[] selfPoint) {
		double sim = 0.0d;
		int length = targetPoint.length;
		double dot = 0.0d;
		double mag1 = 0.0d;
		double mag2 = 0.0d;

		for (int i = 0; i < length; i ++) {
			dot += targetPoint[i].doubleValue() * selfPoint[i].doubleValue();
			mag1 += Math.pow(targetPoint[i], 2);
			mag2 += Math.pow(selfPoint[i], 2);
		}
		Double divisor = Double.valueOf((Math.sqrt(mag1) * Math.sqrt(mag2)));
		if (Math.abs(divisor) < 2 * Double.MIN_VALUE) {
			return Double.NaN;
		}
		sim = dot / divisor;
		return Double.valueOf(sim);
	}

	public Double manhattanDistance(Double[] targetPoint, Double[] selfPoint) {
		double result = 0.0;
		for (int i = 0; i < targetPoint.length; i++) {
			result += Math.abs(selfPoint[i].doubleValue() - targetPoint[i].doubleValue());
		}
		return Double.valueOf(result);
	}

	public Double minkowskiDistance(Double[] targetPoint, Double[] selfPoint) {
		return null;
	}
	
	public Double jaccardSimilarity(Double[] targetPoint, Double[] selfPoint) {
		return null;
	}

}

