package edu.lu.uni.serval.utils.similarity;

import java.util.List;

/**
 * Calculate the similarities between two strings or two lists of data with the Levenshtein distance.
 * https://en.wikipedia.org/wiki/Levenshtein_distance
 * The distance is the number of deletions, insertions, or substitutions required to transform s into t.
 * 
 * S_v1_v2(i, j) = 
 * 		If min(i, j) == 0 then
 * 			max(i, j);
 * 		Else
 * 			min(S_v1_v2(i - 1, j) + 1,
 * 				S_v1_v2(i, j - 1) + 1,
 * 				S_v1_v2(i - 1, j - 1) + (v1_i != v2_j) ? 1 : 0);
 * 		EndIf
 * 
 * The similarity between v1 and v2 is inversely proportional to the value of S_v1_v2(i, j).
 * 
 * @author kui.liu
 *
 */
public class LevenshteinDistance implements Similarity {

	/**
	 * Compute the levenshtein distance of two strings.
	 * @param s, the source string.
	 * @param t, the target string.
	 * @return distance, The distance is the number of deletions, insertions, or substitutions required to transform s into t
	 */
	@Override
	public Double similarity(final String s, final String t) {
		if (s == null || t == null) return Double.NaN;
		
		if (s.equals(t)) return 1d;
		
		int lengthS = s.length();
		int lengthT = t.length();

		// Step 1
		if (lengthS == 0) return 0d;//Double.valueOf(lengthT);
		if (lengthT == 0) return 0d;//Double.valueOf(lengthT);

		// Step 7
		return NormalizedSimilarity.normalize(ld(s, t), lengthS, lengthT);//Double.valueOf(d[lengthS][lengthT]);
	}
	
	int ld(String s, String t) {
		
		int lengthS = s.length();
		int lengthT = t.length();

		// Step 2
		int d[][] = new int[lengthS + 1][lengthT + 1];// matrix
		int indexS; // iterates through s
		int indexT; // iterates through t
		for (indexS = 0; indexS <= lengthS; indexS++) d[indexS][0] = indexS;
		for (indexT = 0; indexT <= lengthT; indexT++) d[0][indexT] = indexT;

		// Step 3
		char iCharOfS; // i_th character of s
		char jCharOfT; // j_th character of t
		int cost; // cost
		for (indexS = 1; indexS <= lengthS; indexS++) {
			iCharOfS = s.charAt(indexS - 1);

			// Step 4
			for (indexT = 1; indexT <= lengthT; indexT++) {
				jCharOfT = t.charAt(indexT - 1);
				
				// Step 5
				if (iCharOfS == jCharOfT) {
					cost = 0;
				} else {
					cost = 1;
				}

				// Step 6
				d[indexS][indexT] = getMinimumValue(d[indexS - 1][indexT] + 1, 
													d[indexS][indexT - 1] + 1, 
													d[indexS - 1][indexT - 1] + cost);
			}
		}
		return d[lengthS][lengthT];
	}
	/**
	 * Get the minimum value of three values.
	 * @param a
	 * @param b
	 * @param c
	 * @return minimumValue
	 */
	private int getMinimumValue(int a, int b, int c) {
		int minimumValue = a;
		if (b < minimumValue)
			minimumValue = b;
		if (c < minimumValue)
			minimumValue = c;
		return minimumValue;
	}

	@Override
	public <T> Double similarity(final List<T> s, final List<T> t) {
		if (s == null || t == null) return Double.NaN;
		if (s.containsAll(t) && t.containsAll(s)) return 1d;
		
		int sizeS = s.size();
		int sizeT = t.size();
		// Step 1
		if (sizeS == 0) return 0d;// Double.valueOf(sizeT);
		if (sizeT == 0) return 0d;// Double.valueOf(sizeS);
		
		// Step 7
		return NormalizedSimilarity.normalize(ld(s, t), sizeS, sizeT);//Double.valueOf(d[sizeS][sizeT]);
	}
	
	<T> int ld(List<T> s, List<T> t) {
		int sizeS = s.size();
		int sizeT = t.size();

		// Step 2
		int d[][] = new int[sizeS + 1][sizeT + 1];// matrix
		int indexS; // iterates through s
		int indexT; // iterates through t
		for (indexS = 0; indexS <= sizeS; indexS++) d[indexS][0] = indexS;
		for (indexT = 0; indexT <= sizeT; indexT++) d[0][indexT] = indexT;

		// Step 3
		T iObjectOfS; // i_th object of s
		T jObjectOfT; // j_th object of t
		int cost; // cost
		for (indexS = 1; indexS <= sizeS; indexS++) {
			iObjectOfS = s.get(indexS - 1);

			// Step 4
			for (indexT = 1; indexT <= sizeT; indexT++) {
				jObjectOfT = t.get(indexT - 1);
				
				// Step 5
				if (iObjectOfS.equals(jObjectOfT)) {
					cost = 0;
				} else {
					cost = 1;
				}

				// Step 6
				d[indexS][indexT] = getMinimumValue(d[indexS - 1][indexT] + 1, 
													d[indexS][indexT - 1] + 1, 
													d[indexS - 1][indexT - 1] + cost);
			}
		}
		
		return d[sizeS][sizeT];
	}
	
}
