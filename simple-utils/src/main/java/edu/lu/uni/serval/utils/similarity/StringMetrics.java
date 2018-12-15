package edu.lu.uni.serval.utils.similarity;

/**
 * A string metric (also known as a string similarity metric or string distance function) 
 * is a metric that measures distance ("inverse similarity") between two text strings 
 * for approximate string matching or comparison and in fuzzy string searching.
 * 
 * https://en.wikipedia.org/wiki/String_metric
 * 
 * @author kui.liu
 *
 */
public enum StringMetrics {
	DamerauLevenshtein,   // https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance
	Jaccard,		  	  // https://en.wikipedia.org/wiki/Jaccard_index
	JaroWinkler, 		  // https://en.wikipedia.org/wiki/Jaro%E2%80%93Winkler_distance
	LevenshteinDistance,  // https://en.wikipedia.org/wiki/Levenshtein_distance
	LongestCommonSubsequence, //
	MetricLCS,			  // https://pdfs.semanticscholar.org/03eb/b47423f23c70608ce4d6995f6d773ffa3348.pdf
	MostFreqKDistance,	  // https://en.wikipedia.org/wiki/Most_frequent_k_characters
	NGram,				  // http://webdocs.cs.ualberta.ca/~kondrak/papers/spire05.pdf
	NormalizedLevenshteinDistance, // 
	OptimalStringAlignment, 	   //
	Overlap,              // https://en.wikipedia.org/wiki/Overlap_coefficient
	QGram, 				  // https://www-sciencedirect-com.proxy.bnl.lu/science/article/pii/0304397592901434?pds=107201810203393151758490735537587
	Sift4,				  // https://siderite.blogspot.com/2014/11/super-fast-and-accurate-string-distance.html
	SorensenDice,		  // https://en.wikipedia.org/wiki/S%C3%B8rensen%E2%80%93Dice_coefficient
	Tversky,	   		  // https://en.wikipedia.org/wiki/Tversky_index
}
