package edu.lu.uni.serval.utils.similarity;

import java.util.LinkedList;
import java.util.List;

/**
 * Sift4 - a general purpose string distance algorithm inspired by JaroWinkler
 * and Longest Common Subsequence.
 * Original JavaScript algorithm by siderite, java port by Nathan Fischer 2016.
 * https://siderite.blogspot.com/2014/11/super-fast-and-accurate-string-distance.html
 * 
 * The similarity between two strings is inversely proportional to the similarity value.
 *
 */
public class Sift4 implements Similarity {

    private int max_offset = 10; //Default value.

    /**
     * Set the maximum distance to search for character transposition.
     * Compute cost of algorithm is O(n . max_offset)
     * @param max_offset
     */
    public final void setMaxOffset(final int max_offset) {
        this.max_offset = max_offset;
    }

    /**
     * Sift4 - a general purpose string distance algorithm inspired by
     * JaroWinkler and Longest Common Subsequence.
     * Original JavaScript algorithm by siderite, java port by Nathan Fischer
     * 2016.
     * https://siderite.blogspot.com/2014/11/super-fast-and-accurate-string-
     * distance.html
     *
     * @param s1
     * @param s2
     * @return
     */
    @Override
    public Double similarity(final String s1, final String s2) {
    	if (s1 == null || s2 == null) return Double.NaN;
    	
    	if (s1.equals(s2)) return 1d;
        if (s1.isEmpty() || s2.isEmpty()) {
            return 0d;//Double.valueOf(s1.length());
        }

        int l1 = s1.length();
        int l2 = s2.length();

        int c1 = 0;  //cursor for string 1
        int c2 = 0;  //cursor for string 2
        int lcss = 0;  //largest common subsequence
        int local_cs = 0; //local common substring
        int trans = 0;  //number of transpositions ('ab' vs 'ba')

        // offset pair array, for computing the transpositions
        LinkedList<Offset> offset_arr = new LinkedList<Offset>();

        while ((c1 < l1) && (c2 < l2)) {
            if (s1.charAt(c1) == s2.charAt(c2)) {
                local_cs++;
                boolean is_trans = false;
                // see if current match is a transposition
                int i = 0;
                while (i < offset_arr.size()) {
                    Offset ofs = offset_arr.get(i);
                    if (c1 <= ofs.c1 || c2 <= ofs.c2) {
                        // when two matches cross, the one considered a
                        // transposition is the one with the largest difference
                        // in offsets
                        is_trans =
                                Math.abs(c2 - c1) >= Math.abs(ofs.c2 - ofs.c1);
                        if (is_trans) {
                            trans++;
                        } else {
                            if (!ofs.trans) {
                                ofs.trans = true;
                                trans++;
                            }
                        }

                        break;
                    } else {
                        if (c1 > ofs.c2 && c2 > ofs.c1) {
                            offset_arr.remove(i);
                        } else {
                            i++;
                        }
                    }
                }
                offset_arr.add(new Offset(c1, c2, is_trans));

            } else {
                // s1.charAt(c1) != s2.charAt(c2)
                lcss += local_cs;
                local_cs = 0;
                if (c1 != c2) {
                    //using min allows the computation of transpositions
                    c1 = Math.min(c1, c2);
                    c2 = c1;
                }

                // if matching characters are found, remove 1 from both cursors
                // (they get incremented at the end of the loop)
                // so that we can have only one code block handling matches
                for (int i = 0;
                        i < max_offset && (c1 + i < l1 || c2 + i < l2);
                        i++) {

                    if ((c1 + i < l1) && (s1.charAt(c1 + i) == s2.charAt(c2))) {
                        c1 += i - 1;
                        c2--;
                        break;
                    }

                    if ((c2 + i < l2) && (s1.charAt(c1) == s2.charAt(c2 + i))) {
                        c1--;
                        c2 += i - 1;
                        break;
                    }
                }
            }
            c1++;
            c2++;
            // this covers the case where the last match is on the last token
            // in list, so that it can compute transpositions correctly
            if ((c1 >= l1) || (c2 >= l2)) {
                lcss += local_cs;
                local_cs = 0;
                c1 = Math.min(c1, c2);
                c2 = c1;
            }
        }
        lcss += local_cs;
        // add the cost of transpositions to the final result
        return 1d - (double) Math.round(Math.max(l1, l2) - lcss + trans) / l1;// Math.round(Math.max(l1, l2) - lcss + trans)
    }

	@Override
	public <T> Double similarity(List<T> l1, List<T> l2) {
		if (l1 == null || l2 == null) return Double.NaN;
		
		if (l1.containsAll(l2)) return 1d;
		if (l1.isEmpty() || l2.isEmpty()) {
            return 0d;
        }

        int s1 = l1.size();
        int s2 = l2.size();

        int c1 = 0;  //cursor for string 1
        int c2 = 0;  //cursor for string 2
        int lcss = 0;  //largest common subsequence
        int local_cs = 0; //local common substring
        int trans = 0;  //number of transpositions ('ab' vs 'ba')

        // offset pair array, for computing the transpositions
        LinkedList<Offset> offset_arr = new LinkedList<Offset>();

        while ((c1 < s1) && (c2 < s2)) {
            if (l1.get(c1).equals(l2.get(c2))) {
                local_cs++;
                boolean is_trans = false;
                // see if current match is a transposition
                int i = 0;
                while (i < offset_arr.size()) {
                    Offset ofs = offset_arr.get(i);
                    if (c1 <= ofs.c1 || c2 <= ofs.c2) {
                        // when two matches cross, the one considered a
                        // transposition is the one with the largest difference
                        // in offsets
                        is_trans =
                                Math.abs(c2 - c1) >= Math.abs(ofs.c2 - ofs.c1);
                        if (is_trans) {
                            trans++;
                        } else {
                            if (!ofs.trans) {
                                ofs.trans = true;
                                trans++;
                            }
                        }

                        break;
                    } else {
                        if (c1 > ofs.c2 && c2 > ofs.c1) {
                            offset_arr.remove(i);
                        } else {
                            i++;
                        }
                    }
                }
                offset_arr.add(new Offset(c1, c2, is_trans));

            } else {

                // s1.charAt(c1) != s2.charAt(c2)
                lcss += local_cs;
                local_cs = 0;
                if (c1 != c2) {
                    //using min allows the computation of transpositions
                    c1 = Math.min(c1, c2);
                    c2 = c1;
                }

                // if matching characters are found, remove 1 from both cursors
                // (they get incremented at the end of the loop)
                // so that we can have only one code block handling matches
                for (int i = 0;
                        i < max_offset && (c1 + i < s1 || c2 + i < s2);
                        i++) {

                    if ((c1 + i < s1) && (l1.get(c1 + i).equals(l2.get(c2)))) {
                        c1 += i - 1;
                        c2--;
                        break;
                    }

                    if ((c2 + i < s2) && (l1.get(c1).equals(l2.get(c2 + i)))) {
                        c1--;
                        c2 += i - 1;
                        break;
                    }
                }
            }
            c1++;
            c2++;
            // this covers the case where the last match is on the last token
            // in list, so that it can compute transpositions correctly
            if ((c1 >= s1) || (c2 >= s2)) {
                lcss += local_cs;
                local_cs = 0;
                c1 = Math.min(c1, c2);
                c2 = c1;
            }
        }
        lcss += local_cs;
        // add the cost of transpositions to the final result
        return 1d - (double) Math.round(Math.max(s1, s2) - lcss + trans) / s1;//Double.valueOf(Math.round(Math.max(s1, s2) - lcss + trans));
	}
	
	 /**
     * Used to store relation between same character in different positions
     * c1 and c2 in the input strings.
     */
    class Offset {

        private final int c1;
        private final int c2;
        private boolean trans;

        Offset(final int c1, final int c2, final boolean trans) {
            this.c1 = c1;
            this.c2 = c2;
            this.trans = trans;
        }
    }
}
