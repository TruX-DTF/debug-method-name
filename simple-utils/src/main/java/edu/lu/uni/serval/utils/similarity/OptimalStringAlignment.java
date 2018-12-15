package edu.lu.uni.serval.utils.similarity;

import java.util.List;

/**
 * Implementation of the the Optimal String Alignment (sometimes called the
 * restricted edit distance) variant of the Damerau-Levenshtein distance.
 *
 * The difference between the two algorithms consists in that the Optimal String
 * Alignment algorithm computes the number of edit operations needed to make the
 * strings equal under the condition that no substring is edited more than once,
 * whereas Damerau-Levenshtein presents no such restriction.
 * 
 * The similarity between two strings is inversely proportional to the similarity value.
 *
 */
public class OptimalStringAlignment implements Similarity {

    /**
     * Compute the distance between strings: the minimum number of operations
     * needed to transform one string into the other (insertion, deletion,
     * substitution of a single character, or a transposition of two adjacent
     * characters) while no substring is edited more than once.
     *
     * @param str1 The first string to compare.
     * @param str2 The second string to compare.
     * @return the OSA distance
     * @throws NullPointerException if str1 or str2 is null.
     */
	@Override
    public Double similarity(final String str1, final String str2) {
        if (str1 == null || str2 == null) {
        	return Double.NaN;
        }

        if (str1.equals(str2)) {
            return 1d;
        }

        int n = str1.length(), m = str2.length();

        if (n == 0 || m == 0) {
            return 0d;
        }

        // Create the distance matrix H[0 .. s1.length+1][0 .. s2.length+1]
        int[][] d = new int[n + 2][m + 2];

        //initialize top row and leftmost column
        for (int i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            d[0][j] = j;
        }

        //fill the distance matrix
        int cost;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                //if s1[i - 1] = s2[j - 1] then cost = 0, else cost = 1
                cost = 1;
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    cost = 0;
                }

                d[i][j] = min(
                        d[i - 1][j - 1] + cost, // substitution
                        d[i][j - 1] + 1, // insertion
                        d[i - 1][j] + 1 // deletion
                );

                //transposition check
                if (i > 1 && j > 1
                        && str1.charAt(i - 1) == str2.charAt(j - 2)
                        && str1.charAt(i - 2) == str2.charAt(j - 1)) {
                    d[i][j] = Math.min(d[i][j], d[i - 2][j - 2] + cost);
                }
            }
        }

        return NormalizedSimilarity.normalize(Math.max(n, m) - d[n][m], n, m);//Double.valueOf(d[n][m]);
    }

    private int min(final int a, final int b, final int c) {
        return Math.min(a, Math.min(b, c));
    }

	@Override
	public <T> Double similarity(List<T> l1, List<T> l2) {
		if (l1 == null || l2 == null) {
			return Double.NaN;
        }

        if (l1.containsAll(l2) && l2.containsAll(l1)) {
            return 1d;
        }

        int n = l1.size();
        int m = l2.size();

        if (n == 0 || m == 0) {
            return 0d;
        }

        // Create the distance matrix H[0 .. s1.length+1][0 .. s2.length+1]
        int[][] d = new int[n + 2][m + 2];

        //initialize top row and leftmost column
        for (int i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            d[0][j] = j;
        }

        //fill the distance matrix
        int cost;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                //if s1[i - 1] = s2[j - 1] then cost = 0, else cost = 1
                cost = 1;
                if (l1.get(i - 1).equals(l2.get(j - 1))) {
                    cost = 0;
                }

                d[i][j] = min(
                        d[i - 1][j - 1] + cost, // substitution
                        d[i][j - 1] + 1, // insertion
                        d[i - 1][j] + 1 // deletion
                );

                //transposition check
                if (i > 1 && j > 1
                        && l1.get(i - 1).equals(l2.get(j - 2))
                        && l1.get(i - 2).equals(l2.get(j - 1))) {
                    d[i][j] = Math.min(d[i][j], d[i - 2][j - 2] + cost);
                }
            }
        }

        return NormalizedSimilarity.normalize(Math.max(n, m) - d[n][m], n, m);//Double.valueOf(d[n][m]);
	}
}
