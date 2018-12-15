package edu.lu.uni.serval.utils.similarity;

import java.util.List;

/**
 * The similarity between two strings is inversely proportional to the similarity value.
 * @author kui.liu
 *
 */
public class NormalizedLevenshteinDistance implements Similarity {
	
	private LevenshteinDistance ld = new LevenshteinDistance();

	@Override
	public Double similarity(String str1, String str2) {
		if (str1 == null || str2 == null) {
			return Double.NaN;
        }

        if (str1.equals(str2)) {
            return 1d;
        }

        int m_len = Math.max(str1.length(), str2.length());

        if (m_len == 0) {
            return 0d;
        }
		return 1 - (double) ld.ld(str1, str2) / m_len;
	}

	@Override
	public <T> Double similarity(List<T> l1, List<T> l2) {
		if (l1 == null || l2 == null) {
			return Double.NaN;
        }

        if (l1.containsAll(l2) && l2.containsAll(l1)) {
            return 1d;
        }

        int m_len = Math.max(l1.size(), l2.size());

        if (m_len == 0) {
            return 0d;
        }
		return 1 - (double) ld.ld(l1, l2) / m_len;
	}

}
