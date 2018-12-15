package edu.lu.uni.serval.utils.similarity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Calculate the similarities between two strings or two lists of data with the Jaccard similarity coefficient.
 * https://en.wikipedia.org/wiki/Jaccard_index
 * 
 * S(V1, V2) = |V1 intersect V2| / |V1 union V2|.
 * 
 * The similarity between V1 and V2 is proportional to the value of S(V1, V2).
 *  
 * @author kui.liu
 *
 */
public class Jaccard implements Similarity {

	@Override
	public <T> Double similarity(final List<T> l1, final List<T> l2) {
		
		if (l1 == null || l2 == null) return Double.NaN;
		if (l1.containsAll(l2) && l2.containsAll(l1)) return 1d;
		/*
		 *  FIXME: if there are several same objects in one list, what should we do?
		 *  If so, it is preferred to use Kulczynski-2 algorithm.
		 */
		List<T> intersectionList = l1.stream().filter(t -> l2.contains(t)).distinct().collect(Collectors.toList());
		List<T> unionList = new ArrayList<T>();
		unionList.addAll(l1);
		unionList.addAll(l2);
		unionList = unionList.stream().distinct().collect(Collectors.toList());
		
		int intersectionNum = intersectionList.size();
		int unionNum = unionList.size();
		
		return Double.valueOf((double) intersectionNum / unionNum);
	}

	@Override
	public Double similarity(final String str1, final String str2) {
		if (str1 == null || str2 == null) return Double.NaN;
		if (str1.equals(str2)) return 1d;
		
		List<String> l1 = toStringList(str1);
		List<String> l2 = toStringList(str2);
		return similarity(l1, l2);
	}

	protected List<String> toStringList(final String str) {
		char[] charArray = str.toCharArray();
		List<String> strList = new ArrayList<>(charArray.length);
		for (int i = 0, length = charArray.length; i < length; i ++) {
			strList.add(String.valueOf(charArray[i]));
		}
		return strList;
	}

}
