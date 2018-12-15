package edu.lu.uni.serval.utils.similarity;

import java.util.List;

/**
 * Distance metric based on Longest Common Subsequence, 
 * from the notes "An LCS-based string metric" by Daniel Bakkelund.
 * 
 * The similarity between two strings is inversely proportional to the similarity value.
 * 
 * @author kui.liu
 *
 */
public class MetricLCS implements Similarity {

    private final LongestCommonSubsequence lcs = new LongestCommonSubsequence();

    /**
     * Distance metric based on Longest Common Subsequence, computed as
     * 1 - |LCS(str1, str2)| / max(|str1|, |str2|).
     *
     * @param s1 The first string to compare.
     * @param s2 The second string to compare.
     * @return The computed distance metric value.
     * @throws NullPointerException if s1 or s2 is null.
     */
	@Override
	public Double similarity(String str1, String str2) {
		if (str1 == null || str2 == null) {
            return Double.NaN;
        }

        if (str1.equals(str2)) {
            return 1d;
        }

        int maxLength = Math.max(str1.length(), str2.length());
        if (maxLength == 0) {
            return 0d;
        }
        return (double) lcs.lcs(str1, str2) / maxLength;//1.0 - (1.0 * lcs.similarity(str1, str2)) / maxLength;
	}

	@Override
	public <T> Double similarity(List<T> l1, List<T> l2) {
		if (l1 == null || l2 == null) {
			return Double.NaN;
        }

        if (l1.containsAll(l2) && l2.containsAll(l1)) {
            return 1d;
        }

        int maxSize = Math.max(l1.size(), l2.size());
        if (maxSize == 0) {
            return 0d;
        }
        return (double) lcs.lcs(l1, l2) / maxSize;//1.0 - (1.0 * lcs.similarity(l1, l2)) / maxSize;
	}
}
