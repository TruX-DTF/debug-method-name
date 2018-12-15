package edu.lu.uni.serval.utils.similarity;

import java.util.List;

/**
 * N-Gram Similarity as defined by Kondrak, "N-Gram Similarity and Distance",
 * String Processing and Information Retrieval, Lecture Notes in Computer
 * Science Volume 3772, 2005, pp 115-126.
 *
 * The algorithm uses affixing with special character '\n' to increase the
 * weight of first characters. The normalization is achieved by dividing the
 * total similarity score the original length of the longest word.
 *
 * http://webdocs.cs.ualberta.ca/~kondrak/papers/spire05.pdf
 * 
 * The similarity between two strings is inversely proportional to the similarity value.
 */
public class NGram implements Similarity {

    private int n = 2;

    public void setN(int n ) {
    	this.n = n;
    }

    /**
     * Compute the similarity with n-gram distance.
     * 
     * @param str1 The first string to compare.
     * @param str2 The second string to compare.
     * @return The computed n-gram distance in the range [0, 1]
     * @throws NullPointerException if s0 or s1 is null.
     */
    @Override
    public Double similarity(final String str1, final String str2) {
        if (str1 == null || str2 == null) {
        	return Double.NaN;
        }

        if (str1.equals(str2)) {
            return 1d;
        }

        final char special = '\n';
        final int sl = str1.length();
        final int tl = str2.length();

        if (sl == 0 || tl == 0) {
            return 0d;
        }

        int cost = 0;
        if (sl < n || tl < n) {
            for (int i = 0, ni = Math.min(sl, tl); i < ni; i++) {
                if (str1.charAt(i) == str2.charAt(i)) {
                    cost++;
                }
            }
            return Double.valueOf((double) cost / Math.max(sl, tl));
        }

        char[] sa = new char[sl + n - 1];
        float[] p; //'previous' cost array, horizontally
        float[] d; // cost array, horizontally
        float[] d2; //placeholder to assist in swapping p and d

        //construct sa with prefix
        for (int i = 0; i < sa.length; i++) {
            if (i < n - 1) {
                sa[i] = special; //add prefix
            } else {
                sa[i] = str1.charAt(i - n + 1);
            }
        }
        p = new float[sl + 1];
        d = new float[sl + 1];

        // indexes into strings s and t
        int i; // iterates through source
        int j; // iterates through target

        char[] t_j = new char[n]; // jth n-gram of t

        for (i = 0; i <= sl; i++) {
            p[i] = i;
        }

        for (j = 1; j <= tl; j++) {
            //construct t_j n-gram
            if (j < n) {
                for (int ti = 0; ti < n - j; ti++) {
                    t_j[ti] = special; //add prefix
                }
                for (int ti = n - j; ti < n; ti++) {
                    t_j[ti] = str2.charAt(ti - (n - j));
                }
            } else {
                t_j = str2.substring(j - n, j).toCharArray();
            }
            d[0] = j;
            for (i = 1; i <= sl; i++) {
                cost = 0;
                int tn = n;
                //compare sa to t_j
                for (int ni = 0; ni < n; ni++) {
                    if (sa[i - 1 + ni] != t_j[ni]) {
                        cost++;
                    } else if (sa[i - 1 + ni] == special) {
                        //discount matches on prefix
                        tn--;
                    }
                }
                float ec = (float) cost / tn;
                // minimum of cell to the left+1, to the top+1,
                // diagonally left and up +cost
                d[i] = Math.min(
                        Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + ec);
            }
            // copy current distance counts to 'previous row' distance counts
            d2 = p;
            p = d;
            d = d2;
        }

        // our last action in the above loop was to switch d and p, so p now
        // actually has the most recent cost counts
        return 1 - Double.valueOf(p[sl] / Math.max(tl, sl));
    }

	@Override
	public <T> Double similarity(List<T> l1, List<T> l2) {
		if (l1 == null || l2 == null) {
			return Double.NaN;
        }

        if (l1.containsAll(l2) && l2.containsAll(l1)) {
            return 1d;
        }

        final int sl = l1.size();
        final int tl = l2.size();

        if (sl == 0 || tl == 0) {
            return 0d;
        }

        int cost = 0;
        if (sl < n || tl < n) {
            for (int i = 0, ni = Math.min(sl, tl); i < ni; i++) {
                if (l1.get(i).equals(l2.get(i))) {
                    cost++;
                }
            }
            return Double.valueOf((double) cost / Math.max(sl, tl));
        }

        String[] sa = new String[sl + n - 1];
        float[] p; //'previous' cost array, horizontally
        float[] d; // cost array, horizontally
        float[] d2; //placeholder to assist in swapping p and d

        //construct sa with prefix
        for (int i = 0; i < sa.length; i++) {
            if (i < n - 1) {
                sa[i] = ""; //add prefix
            } else {
                sa[i] = l1.get(i - n + 1).toString();
            }
        }
        p = new float[sl + 1];
        d = new float[sl + 1];

        // indexes into strings s and t
        int i; // iterates through source
        int j; // iterates through target

        String[] t_j = new String[n]; // jth n-gram of t

        for (i = 0; i <= sl; i++) {
            p[i] = i;
        }

        for (j = 1; j <= tl; j++) {
            //construct t_j n-gram
            if (j < n) {
                for (int ti = 0; ti < n - j; ti++) {
                    t_j[ti] = ""; //add prefix
                }
                for (int ti = n - j; ti < n; ti++) {
                    t_j[ti] = l2.get(ti - (n - j)).toString();
                }
            } else {
                List<T> l = l2.subList(j - n, j);
                for (int ti = 0; ti < n; ti++) {
            		t_j[ti] = l.get(ti).toString();
            	}
            }
            d[0] = j;
            for (i = 1; i <= sl; i++) {
                cost = 0;
                int tn = n;
                //compare sa to t_j
                for (int ni = 0; ni < n; ni++) {
                    if (sa[i - 1 + ni] != t_j[ni]) {
                        cost++;
                    } else if (sa[i - 1 + ni].equals("")) {
                        //discount matches on prefix
                        tn--;
                    }
                }
                float ec = (float) cost / tn;
                // minimum of cell to the left+1, to the top+1,
                // diagonally left and up +cost
                d[i] = Math.min(
                        Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + ec);
            }
            // copy current distance counts to 'previous row' distance counts
            d2 = p;
            p = d;
            d = d2;
        }

        // our last action in the above loop was to switch d and p, so p now
        // actually has the most recent cost counts
        return 1 - Double.valueOf(p[sl] / Math.max(tl, sl));
	}
}
