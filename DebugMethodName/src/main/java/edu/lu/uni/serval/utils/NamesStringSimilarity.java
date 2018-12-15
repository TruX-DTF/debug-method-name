package edu.lu.uni.serval.utils;

import java.util.List;

import edu.lu.uni.serval.utils.similarity.SimilarityCalculator;
import edu.lu.uni.serval.utils.similarity.StringMetrics;

public class NamesStringSimilarity {
	
	// Sorting the similarities by descending (true) or ascending (false).
//	public boolean isDescending = false;
	
	public Double calculateSimilarity(String str1, String str2, String stringMetric) {
		StringMetrics  sm = readStringMetric(stringMetric);
		if (sm == null) return null;
		return new SimilarityCalculator().calculateSimilarity(str1, str2, sm);
	}
	
	public Double calculateSimilarity(String str1, String str2, StringMetrics stringMetric) {
		return new SimilarityCalculator().calculateSimilarity(str1, str2, stringMetric);
	}
	
	public <T> Double calculateSimilarity(List<T> l1, List<T> l2, String stringMetric) {
		StringMetrics  sm = readStringMetric(stringMetric);
		if (sm == null) return null;
		return new SimilarityCalculator().calculateSimilarity(l1, l2, sm);
	}
	
	public <T> Double calculateSimilarity(List<T> l1, List<T> l2, StringMetrics stringMetric) {
		return new SimilarityCalculator().calculateSimilarity(l1, l2, stringMetric);
	}

	public StringMetrics readStringMetric(String stringMetric) {
		StringMetrics sm = null;
		if ("dl".equalsIgnoreCase(stringMetric) || "DamerauLevenshtein".equalsIgnoreCase(stringMetric)) {
			sm = StringMetrics.DamerauLevenshtein;
		} else if ("ja".equalsIgnoreCase(stringMetric) || "Jaccard".equalsIgnoreCase(stringMetric)) {
			sm = StringMetrics.Jaccard;
		} else if ("jw".equalsIgnoreCase(stringMetric) || "JaroWinkler".equalsIgnoreCase(stringMetric)) {
			sm = StringMetrics.JaroWinkler;
		} else if ("ld".equalsIgnoreCase(stringMetric) || "LevenshteinDistance".equalsIgnoreCase(stringMetric)) {
			sm = StringMetrics.LevenshteinDistance;
		} else if ("lcs".equalsIgnoreCase(stringMetric) || "LongestCommonSubsequence".equalsIgnoreCase(stringMetric)) {
			sm = StringMetrics.LongestCommonSubsequence;
		} else if ("mlcs".equalsIgnoreCase(stringMetric) || "MetricLCS".equalsIgnoreCase(stringMetric)) {
			sm = StringMetrics.MetricLCS;
		} else if ("mfkd".equalsIgnoreCase(stringMetric) || "MostFreqKDistance".equalsIgnoreCase(stringMetric)) {
			sm = StringMetrics.MostFreqKDistance;
		} else if ("ng".equalsIgnoreCase(stringMetric) || "NGram".equalsIgnoreCase(stringMetric)) {
			sm = StringMetrics.NGram;
		} else if ("nld".equalsIgnoreCase(stringMetric) || "NormalizedLevenshteinDistance".equalsIgnoreCase(stringMetric)) {
			sm = StringMetrics.NormalizedLevenshteinDistance;
		} else if ("osa".equalsIgnoreCase(stringMetric) || "OptimalStringAlignment".equalsIgnoreCase(stringMetric)) {
			sm = StringMetrics.OptimalStringAlignment;
		} else if ("ol".equalsIgnoreCase(stringMetric) || "Overlap".equalsIgnoreCase(stringMetric)) {
			sm = StringMetrics.Overlap;
		} else if ("qg".equalsIgnoreCase(stringMetric) || "QGram".equalsIgnoreCase(stringMetric)) {
			sm = StringMetrics.QGram;
		} else if ("s4".equalsIgnoreCase(stringMetric) || "Sift4".equalsIgnoreCase(stringMetric)) {
			sm = StringMetrics.Sift4;
		} else if ("sd".equalsIgnoreCase(stringMetric) || "SorensenDice".equalsIgnoreCase(stringMetric)) {
			sm = StringMetrics.SorensenDice;
		} else if ("ts".equalsIgnoreCase(stringMetric) || "Tversky".equalsIgnoreCase(stringMetric)) {
			sm = null;//StringMetrics.Tversky;
		}
		
//		if (sm == StringMetrics.DamerauLevenshtein || sm == StringMetrics.LevenshteinDistance
//				|| sm == StringMetrics.LongestCommonSubsequence || sm == StringMetrics.MetricLCS
//				|| sm == StringMetrics.MostFreqKDistance || sm == StringMetrics.NGram
//				|| sm == StringMetrics.NormalizedLevenshteinDistance || sm == StringMetrics.OptimalStringAlignment
//				|| sm == StringMetrics.QGram || sm == StringMetrics.Sift4) {
//			isDescending = false;
//		} else if (sm == StringMetrics.Jaccard || sm == StringMetrics.JaroWinkler
//				|| sm == StringMetrics.Overlap || sm == StringMetrics.SorensenDice) {
//			isDescending = true;
//		}
		return sm;
	}

}
