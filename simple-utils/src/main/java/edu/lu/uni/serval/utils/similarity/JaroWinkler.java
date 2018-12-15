package edu.lu.uni.serval.utils.similarity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Calculate the similarities between two strings or two lists of data with the Jaro-Winkler distance.
 * https://en.wikipedia.org/wiki/Jaro%E2%80%93Winkler_distance
 * 
 * The similarity between two strings is proportional to the similarity value.
 * 
 * @author kui.liu
 *
 */
public class JaroWinkler implements Similarity {

    private static final int THREE = 3;
    private static final double JW_COEF = 0.1;
    private double threshold = 0.7d;
    
    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }
    
	@Override
	public Double similarity(String str1, String str2) {
		if (str1 == null || str2 == null) {
			return Double.NaN;
        }

        if (str1.equals(str2)) {
            return Double.valueOf(1d);
        }

        int[] mtp = matches(str1, str2);
        float m = mtp[0];
        if (m == 0) {
            return Double.valueOf(0d);
        }
        double j = ((m / str1.length() + m / str2.length() + (m - mtp[1]) / m))
                / THREE;
        double jw = j;

        if (j > this.threshold) {
            jw = j + Math.min(JW_COEF, 1.0 / mtp[THREE]) * mtp[2] * (1 - j);
        }
        return jw;
	}

	@Override
	public <T> Double similarity(List<T> str1, List<T> str2) {
		if (str1 == null || str2 == null) {
			return Double.NaN;
        }

        if (str1.containsAll(str2) && str2.containsAll(str1)) {
            return Double.valueOf(1d);
        }

        int[] mtp = matches(str1, str2);
        float m = mtp[0];
        if (m == 0) {
            return Double.valueOf(0d);
        }
        double j = ((m / str1.size() + m / str2.size() + (m - mtp[1]) / m))
                / THREE;
        double jw = j;

        if (j > this.threshold) {
            jw = j + Math.min(JW_COEF, 1.0 / mtp[THREE]) * mtp[2] * (1 - j);
        }
        return jw;
	}
	
	private int[] matches(final String s1, final String s2) {
        String max, min;
        if (s1.length() > s2.length()) {
            max = s1;
            min = s2;
        } else {
            max = s2;
            min = s1;
        }
        int range = Math.max(max.length() / 2 - 1, 0);
        int[] match_indexes = new int[min.length()];
        Arrays.fill(match_indexes, -1);
        boolean[] match_flags = new boolean[max.length()];
        int matches = 0;
        for (int mi = 0; mi < min.length(); mi++) {
            char c1 = min.charAt(mi);
            for (int xi = Math.max(mi - range, 0),
                    xn = Math.min(mi + range + 1, max.length());
                    xi < xn;
                    xi++) {
                if (!match_flags[xi] && c1 == max.charAt(xi)) {
                    match_indexes[mi] = xi;
                    match_flags[xi] = true;
                    matches++;
                    break;
                }
            }
        }
        char[] ms1 = new char[matches];
        char[] ms2 = new char[matches];
        for (int i = 0, si = 0; i < min.length(); i++) {
            if (match_indexes[i] != -1) {
                ms1[si] = min.charAt(i);
                si++;
            }
        }
        for (int i = 0, si = 0; i < max.length(); i++) {
            if (match_flags[i]) {
                ms2[si] = max.charAt(i);
                si++;
            }
        }
        int transpositions = 0;
        for (int mi = 0; mi < ms1.length; mi++) {
            if (ms1[mi] != ms2[mi]) {
                transpositions++;
            }
        }
        int prefix = 0;
        for (int mi = 0; mi < min.length(); mi++) {
            if (s1.charAt(mi) == s2.charAt(mi)) {
                prefix++;
            } else {
                break;
            }
        }
        return new int[]{matches, transpositions / 2, prefix, max.length()};
    }
	
	private <T> int[] matches(final List<T> s1, final List<T> s2) {
        List<T> max, min;
        if (s1.size() > s2.size()) {
            max = s1;
            min = s2;
        } else {
            max = s2;
            min = s1;
        }
        int range = Math.max(max.size() / 2 - 1, 0);
        int[] match_indexes = new int[min.size()];
        Arrays.fill(match_indexes, -1);
        boolean[] match_flags = new boolean[max.size()];
        int matches = 0;
        for (int mi = 0; mi < min.size(); mi++) {
            T c1 = min.get(mi);
            for (int xi = Math.max(mi - range, 0),
                    xn = Math.min(mi + range + 1, max.size());
                    xi < xn;
                    xi++) {
                if (!match_flags[xi] && c1.equals(max.get(xi))) {
                    match_indexes[mi] = xi;
                    match_flags[xi] = true;
                    matches++;
                    break;
                }
            }
        }
        List<T> ms1 = new ArrayList<T>(matches);
        List<T> ms2 = new ArrayList<T>(matches);
        for (int i = 0; i < min.size(); i++) {
            if (match_indexes[i] != -1) {
                ms1.add(min.get(i));
            }
        }
        for (int i = 0; i < max.size(); i++) {
            if (match_flags[i]) {
                ms2.add(max.get(i));
            }
        }
        int transpositions = 0;
        for (int mi = 0; mi < ms1.size(); mi++) {
            if (ms1.get(mi).equals(ms2.get(mi))) {
                transpositions++;
            }
        }
        int prefix = 0;
        for (int mi = 0; mi < min.size(); mi++) {
            if (s1.get(mi).equals(s2.get(mi))) {
                prefix++;
            } else {
                break;
            }
        }
        return new int[]{matches, transpositions / 2, prefix, max.size()};
    }

}
