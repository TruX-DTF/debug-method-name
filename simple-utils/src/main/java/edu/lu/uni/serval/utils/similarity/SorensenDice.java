package edu.lu.uni.serval.utils.similarity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Calculate similarities between two strings or two lists of data with the Sorensen-Dice coefficient.
 * https://en.wikipedia.org/wiki/S%C3%B8rensen%E2%80%93Dice_coefficient
 * 
 * S(V1, V2) = 2 * |V1 intersect V2| / (|V1| + |V2|) 
 * 
 * The similarity between V1 and V2 is proportional to the value of S(V1, V2).
 * 
 * @author kui.liu
 *
 */
public class SorensenDice extends Jaccard {

	@Override
	public Double similarity(final String str1, final String str2) {
		if (str1 == null || str2 == null) return Double.NaN;
		
		List<String> l1 = toStringList(str1);
		List<String> l2 = toStringList(str2);
		return similarity(l1, l2);
	}

	@Override
	public <T> Double similarity(final List<T> l1, final List<T> l2) {
		if (l1 == null || l2 == null) return Double.NaN;
		
		List<T> intersectionList = l1.stream().filter(t -> l2.contains(t)).collect(Collectors.toList());
		int intersectionNum = intersectionList.size();
		
		return Double.valueOf(2d * intersectionNum / (l1.size() + l2.size()));
	}

}
