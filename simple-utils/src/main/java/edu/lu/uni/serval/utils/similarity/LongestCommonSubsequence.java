package edu.lu.uni.serval.utils.similarity;

import java.util.List;

/**
 * https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
 * 
 * The similarity between two strings is inversely proportional to the similarity value.
 * 
 * @author kui.liu
 *
 */
public class LongestCommonSubsequence implements Similarity {

	@Override
	public Double similarity(String str1, String str2) {
		if (str1 == null || str2 == null) {
			return Double.NaN;
        }

        if (str1.equals(str2)) {
            return 1d; // 0d
        }

        // str1.length() + str2.length() - 2 * lcs(str1, str2)
        return NormalizedSimilarity.normalize(lcs(str1, str2), str1.length(), str2.length());
	}

	int lcs(String s1, String s2) {
        int lengthOfS1 = s1.length();
        int lengthOfS2 = s2.length();
        char[] x = s1.toCharArray();
        char[] y = s2.toCharArray();

        int[][] c = new int[lengthOfS1 + 1][lengthOfS2 + 1];

        for (int i = 1; i <= lengthOfS1; i++) {
            for (int j = 1; j <= lengthOfS2; j++) {
                if (x[i - 1] == y[j - 1]) {
                    c[i][j] = c[i - 1][j - 1] + 1;

                } else {
                    c[i][j] = Math.max(c[i][j - 1], c[i - 1][j]);
                }
            }
        }

        return c[lengthOfS1][lengthOfS2];
	}

	@Override
	public <T> Double similarity(List<T> l1, List<T> l2) {
		if (l1 == null || l2 == null) {
			return Double.NaN;
        }

        if (l1.containsAll(l2) && l2.containsAll(l1)) {
            return 1d;
        }

        return NormalizedSimilarity.normalize(lcs(l1, l2), l1.size(), l2.size());//Double.valueOf(2d * lcs(l1, l2) / (l1.size() + l2.size()));
	}

	<T> int lcs(List<T> l1, List<T> l2) {
        int sizeOfL1 = l1.size();
        int sizeOfL2 = l2.size();

        int[][] c = new int[sizeOfL1 + 1][sizeOfL2 + 1];

        for (int i = 1; i <= sizeOfL1; i++) {
            for (int j = 1; j <= sizeOfL2; j++) {
                if (l1.get(i - 1).equals(l2.get(j - 1))) {
                    c[i][j] = c[i - 1][j - 1] + 1;

                } else {
                    c[i][j] = Math.max(c[i][j - 1], c[i - 1][j]);
                }
            }
        }

        return c[sizeOfL1][sizeOfL2];
	}

}
