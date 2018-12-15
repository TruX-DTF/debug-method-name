package edu.lu.uni.serval.utils.similarity;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;


/**
 * Q-gram distance, as defined by Ukkonen in "Approximate string-matching with
 * q-grams and maximal matches". The distance between two strings is defined as
 * the L1 norm of the difference of their profiles (the number of occurences of
 * each n-gram): 
 * 		SUM( |V1_i - V2_i| ). 
 * Q-gram distance is a lower bound on Levenshtein distance, but can be computed in O(m + n), 
 * where Levenshtein requires O(m.n).
 * 
 * The similarity between two strings is inversely proportional to the distance.
 *
 */
public class QGram implements Similarity {
	
	private int k = 2;
	
	public void setK(int k) {
		this.k = k;
	}

    /**
     * Pattern for finding multiple following spaces.
     */
    private static final Pattern SPACE_REG = Pattern.compile("\\s+");

    /**
     * The distance between two strings is defined as the L1 norm of the
     * difference of their profiles (the number of occurence of each k-shingle).
     *
     * @param str1 The first string to compare.
     * @param str2 The second string to compare.
     * @return The computed Q-gram distance.
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

        Map<String, Integer> profile1 = getProfile(str1);
        Map<String, Integer> profile2 = getProfile(str2);

        return NormalizedSimilarity.normalize(distance(profile1, profile2), profile1.size(), profile2.size());
    }

	@Override
	public <T> Double similarity(List<T> s1, List<T> s2) {
		if (s1 == null || s2 == null) return Double.NaN;
		if (s1.isEmpty() || s2.isEmpty()) return 0d;
        if (s1.containsAll(s2) && s2.containsAll(s1)) return 1d;

        Map<String, Integer> profile1 = getProfile(s1);
        Map<String, Integer> profile2 = getProfile(s2);

        return NormalizedSimilarity.normalize(distance(profile1, profile2), profile1.size(), profile2.size());//distance(profile1, profile2);
	}

    /**
     * Compute QGram distance using precomputed profiles.
     *
     * @param profile1
     * @param profile2
     * @return
     */
    private final int distance(
            final Map<String, Integer> profile1,
            final Map<String, Integer> profile2) {

        Set<String> union = new HashSet<String>();
        union.addAll(profile1.keySet());
        union.addAll(profile2.keySet());

        int agg = 0;
        for (String key : union) {
            int v1 = 0;
            int v2 = 0;
            Integer iv1 = profile1.get(key);
            if (iv1 != null) {
                v1 = iv1;
            }

            Integer iv2 = profile2.get(key);
            if (iv2 != null) {
                v2 = iv2;
            }
            agg += Math.abs(v1 - v2);
        }
        return union.size() - agg;
    }
    
    /**
     * Compute and return the profile of s, as defined by Ukkonen "Approximate
     * string-matching with q-grams and maximal matches".
     * https://www.cs.helsinki.fi/u/ukkonen/TCS92.pdf The profile is the number
     * of occurrences of k-shingles, and is used to compute q-gram similarity,
     * Jaccard index, etc. Pay attention: the memory requirement of the profile
     * can be up to k * size of the string
     *
     * @param string
     * @return the profile of this string, as an unmodifiable Map
     */
    private final Map<String, Integer> getProfile(final String string) {
        HashMap<String, Integer> shingles = new HashMap<String, Integer>();

        String string_no_space = SPACE_REG.matcher(string).replaceAll(" ");
        for (int i = 0; i < (string_no_space.length() - k + 1); i++) {
            String shingle = string_no_space.substring(i, i + k);
            Integer old = shingles.get(shingle);
            if (old != null) {
                shingles.put(shingle, old + 1);
            } else {
                shingles.put(shingle, 1);
            }
        }

        return Collections.unmodifiableMap(shingles);
    }
    
    private <T> Map<String, Integer> getProfile(final List<T> l) {
        HashMap<String, Integer> shingles = new HashMap<String, Integer>();

        for (int i = 0; i < (l.size() - k + 1); i++) {
            List<T> shingleL = l.subList(i, i + k);
            String shingle = "";
            for (T e : shingleL) {
            	shingle += e.toString();
            }
            Integer old = shingles.get(shingle);
            if (old != null) {
                shingles.put(shingle, old + 1);
            } else {
                shingles.put(shingle, 1);
            }
        }

        return Collections.unmodifiableMap(shingles);
    }
}
