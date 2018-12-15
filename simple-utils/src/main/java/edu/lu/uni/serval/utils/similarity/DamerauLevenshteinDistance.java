package edu.lu.uni.serval.utils.similarity;

import java.util.List;

/**
 * Calculate the similarities between two strings or two lists of data with the Damerau-Levenshtein distance.
 * https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance
 * 
 * S_v1_v2(i, j) = 
 * 		If min(i, j) == 0 then
 * 			max(i, j);
 * 		Else
 * 			min = min(S_v1_v2(i - 1, j) + 1, // a deletion (from a to b)
 * 					  S_v1_v2(i, j - 1) + 1, // an insertion (from a to b).
 * 					  S_v1_v2(i - 1, j - 1) + (v1_i != v2_j) ? 1 : 0); // a match or mismatch, depending on whether the respective symbols are the same.
 * 			If i,j > 1 && v1_i == v2_j-1 && v1_i-1 == v2_j then
 * 				min = min(min, S_v1_v2(i - 2, j - 2) + 1);
 * 			EndIf
 * 			min;
 * 		EndIf
 * 
 * The similarity between v1 and v2 is inversely proportional to the value of S_v1_v2(i, j).
 * 		
 * @author kui.liu
 *
 */
public class DamerauLevenshteinDistance implements Similarity {
	
	/**
     * Calculates the string distance between source and target strings using the Damerau-Levenshtein algorithm. 
     * The distance is case-sensitive.
     *
     * @param source The source String.
     * @param target The target String.
     * @return The distance between source and target strings.
     * @throws IllegalArgumentException If either source or target is null.
     */
	@Override
    public Double similarity(final String source, final String target) {
        if (source == null || target == null) {
            return Double.NaN;
        }
        if(source.equals(target)) return 1d;
        
        int sourceLength = source.length();
        int targetLength = target.length();
        if (sourceLength == 0) return 0d;//Double.valueOf(targetLength);
        if (targetLength == 0) return 0d;//Double.valueOf(sourceLength);
        
        int[][] dist = new int[sourceLength + 1][targetLength + 1];
        for (int i = 0; i < sourceLength + 1; i++) {
            dist[i][0] = i;
        }
        for (int j = 0; j < targetLength + 1; j++) {
            dist[0][j] = j;
        }
        for (int i = 1; i < sourceLength + 1; i++) {
            for (int j = 1; j < targetLength + 1; j++) {
                int cost = source.charAt(i - 1) == target.charAt(j - 1) ? 0 : 1;
                dist[i][j] = Math.min(Math.min(
                		dist[i - 1][j] + 1, 
                		dist[i][j - 1] + 1), 
                		dist[i - 1][j - 1] + cost);
                if (i > 1 && j > 1 &&
                        source.charAt(i - 1) == target.charAt(j - 2) &&
                        source.charAt(i - 2) == target.charAt(j - 1)) {
                    dist[i][j] = Math.min(dist[i][j], dist[i - 2][j - 2] + cost);
                }
            }
        }
        return NormalizedSimilarity.normalize(dist[sourceLength][targetLength], sourceLength, targetLength);//Double.valueOf(dist[sourceLength][targetLength]);
    }
    
	@Override
    public <T> Double similarity(final List<T> source, final List<T> target) {
        if (source == null || target == null) {
        	return Double.NaN;
        }
        if (source.containsAll(target) && target.containsAll(source)) return 1d;
        
        int sourceLength = source.size();
        int targetLength = target.size();
        if (sourceLength == 0) return 0d;//Double.valueOf(targetLength);
        if (targetLength == 0) return 0d;//Double.valueOf(sourceLength);
        
        int[][] dist = new int[sourceLength + 1][targetLength + 1];
        for (int i = 0; i < sourceLength + 1; i++) {
            dist[i][0] = i;
        }
        for (int j = 0; j < targetLength + 1; j++) {
            dist[0][j] = j;
        }
        for (int i = 1; i < sourceLength + 1; i++) {
        	T iObjectOfS = source.get(i - 1);
            for (int j = 1; j < targetLength + 1; j++) {
            	T jObjectOfT = target.get(j - 1);
                int cost = iObjectOfS.equals(jObjectOfT) ? 0 : 1;
                dist[i][j] = Math.min(Math.min(
                		dist[i - 1][j] + 1, 
                		dist[i][j - 1] + 1), 
                		dist[i - 1][j - 1] + cost);
                if (i > 1 && j > 1 &&
                		iObjectOfS.equals(target.get(j - 2)) &&
                        source.get(i - 2).equals(jObjectOfT)) {
                    dist[i][j] = Math.min(dist[i][j], dist[i - 2][j - 2] + cost);
                }
            }
        }
        return NormalizedSimilarity.normalize(dist[sourceLength][targetLength], sourceLength, targetLength);//Double.valueOf(dist[sourceLength][targetLength]);
    }

}