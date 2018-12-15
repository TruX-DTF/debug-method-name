package edu.lu.uni.serval.utils.similarity;

import java.util.List;

/**
 * Interface of calculating similarities between two strings or two lists of data with the string metric algorithms.
 * https://en.wikipedia.org/wiki/String_metric
 * 
 * @author kui.liu
 *
 */
public interface Similarity {
	
	/**
	 * Calculate the similarity between two strings.
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	Double similarity(final String str1, final String str2);
	
	/**
	 * Calculate the similarity between two lists of data.
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	<T> Double similarity(final List<T> l1, final List<T> l2);
	
}
