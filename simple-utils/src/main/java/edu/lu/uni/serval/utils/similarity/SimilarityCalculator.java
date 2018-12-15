package edu.lu.uni.serval.utils.similarity;

import java.util.List;

public class SimilarityCalculator {

	public Double calculateSimilarity(String str1, String str2, StringMetrics sm) {
		Similarity sim = createSimilarityCalculatorInstance(sm);
		
		if (sim == null) return null;
		
		return sim.similarity(str1, str2);
	}
	
	public <T> Double calculateSimilarity(List<T> l1, List<T> l2, StringMetrics sm) {
		Similarity sim = createSimilarityCalculatorInstance(sm);
		
		if (sim == null) return null;
		
		return sim.similarity(l1, l2);
	}

	private Similarity createSimilarityCalculatorInstance(StringMetrics sm) {
		if (sm == StringMetrics.DamerauLevenshtein) {
			return new DamerauLevenshteinDistance();
		} else if (sm == StringMetrics.Jaccard) {
			return new Jaccard();
		} else if (sm == StringMetrics.JaroWinkler) {
			return new JaroWinkler();
		} else if (sm == StringMetrics.LevenshteinDistance) {
			return new LevenshteinDistance();
		} else if (sm == StringMetrics.LongestCommonSubsequence) {
			return new LongestCommonSubsequence();
		} else if (sm == StringMetrics.MetricLCS) {
			return new MetricLCS();
		} else if (sm == StringMetrics.MostFreqKDistance) {
			return new MostFreqKDistance();
		} else if (sm == StringMetrics.NGram) {
			return new NGram();
		} else if (sm == StringMetrics.NormalizedLevenshteinDistance) {
			return new NormalizedLevenshteinDistance();
		} else if (sm == StringMetrics.OptimalStringAlignment) {
			return new OptimalStringAlignment();
		} else if (sm == StringMetrics.Overlap) {
			return new Overlap();
		} else if (sm == StringMetrics.QGram) {
			return new QGram();
		} else if (sm == StringMetrics.Sift4) {
			return new Sift4();
		} else if (sm == StringMetrics.SorensenDice) {
			return new SorensenDice();
		} else if (sm == StringMetrics.Tversky) {
			return new Tversky();
		} 
		return null;
	}
}
