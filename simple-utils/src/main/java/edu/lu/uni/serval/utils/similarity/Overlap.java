package edu.lu.uni.serval.utils.similarity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Calculate similarities between two strings or two lists of data with the Overlap coefficient.
 * https://en.wikipedia.org/wiki/Overlap_coefficient
 * 
 * S(V1, V2) = |V1 intersect V2| / min(|V1|,|V2|) 
 * 
 * The similarity between V1 and V2 is proportional to the value of S(V1, V2).
 * 
 * @author kui.liu
 *
 */
public class Overlap extends Jaccard {

	@Override
	public Double similarity(String str1, String str2) {
		if (str1 == null || str2 == null) return Double.NaN;
		
		List<String> l1 = toStringList(str1);
		List<String> l2 = toStringList(str2);
		return similarity(l1, l2);
	}

	@Override
	public <T> Double similarity(List<T> l1, List<T> l2) {
		if(l1 == null || l2 == null) return Double.NaN;
		
		List<T> intersectionList = l1.stream().filter(t -> l2.contains(t)).distinct().collect(Collectors.toList());
		int intersectionNum = intersectionList.size();
		int minNum = l1.size() > l2.size() ? l2.size() : l1.size();
		
		return Double.valueOf((double) intersectionNum / minNum);
	}

}
