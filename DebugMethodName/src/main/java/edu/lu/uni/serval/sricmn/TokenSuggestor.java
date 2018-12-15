package edu.lu.uni.serval.sricmn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.lu.uni.serval.sricmn.info.PredictToken;
import edu.lu.uni.serval.utils.ListSorter;
import edu.lu.uni.serval.utils.MapSorter;

public class TokenSuggestor {
	
	/**
	 * Select the top-5 final most similar tokens by the times of predicted tokens.
	 * If times of several tokens are the same, comparing their similarities.
	 * @param ptList
	 * @return
	 */
	public List<String> selectTop5PredictTokens1(List<PredictToken> ptList) {
		List<String> tokensList = new ArrayList<>();
		Map<PredictToken, Integer> ptMap = new HashMap<>();
		for (PredictToken pt : ptList) {
			ptMap.put(pt, pt.getTimes());
		}
		MapSorter<PredictToken, Integer> mapSorter = new MapSorter<>();
		ptMap = mapSorter.sortByValueDescending(ptMap);

		List<PredictToken> ptList2 = new ArrayList<>();
		List<PredictToken> ptList3 = new ArrayList<>();
		int times = 0;
		
		for (Map.Entry<PredictToken, Integer> entity : ptMap.entrySet()) {
			PredictToken pToken = entity.getKey();
			if (times == 0) {
				ptList3.add(pToken);
			} else {
				if (times == entity.getValue()) {
					ptList3.add(pToken);
				} else {
					ListSorter<PredictToken> listSorter = new ListSorter<>(ptList3);
					ptList3 = listSorter.sortDescending();
					ptList2.addAll(ptList3);
					ptList3.clear();
					ptList3.add(pToken);
				}
			}
		}
		if (ptList3.size() > 0) {
			ListSorter<PredictToken> listSorter = new ListSorter<>(ptList3);
			ptList3 = listSorter.sortDescending();
			ptList2.addAll(ptList3);
			ptList3.clear();
		}
		
		for (PredictToken pToken : ptList2) {
			tokensList.add(pToken.getToken());
		}
		return tokensList.size() > 5 ? tokensList.subList(0, 5) : tokensList;
	}
	
	
	/**
	 * Select the top 5 most similar tokens by the average values of similarities.
	 * @param ptList
	 * @return
	 */
	public List<String> selectTop5PredictTokens2(List<PredictToken> ptList) {
		List<String> tokensList = new ArrayList<>();
		ListSorter<PredictToken> listSorter = new ListSorter<PredictToken>(ptList);
		ptList = listSorter.sortDescending();
		
		for (PredictToken pt : ptList) {
			tokensList.add(pt.getToken());
		}
		
		return tokensList.size() > 5 ? tokensList.subList(0, 5) : tokensList;
	}
	
	/**
	 * Select the top 5 most similar tokens by the average values of similarities.
	 * If the similarity of predicted token is very high, but the times is very low.
	 * @param ptList
	 * @return
	 */
	public List<String> selectTop5PredictTokens3(List<PredictToken> ptList) {
		List<String> tokensList = new ArrayList<>();
		
		List<PredictToken> ptList2 = new ArrayList<>();
		List<PredictToken> ptList3 = new ArrayList<>();
		
		for (PredictToken pt : ptList) {
			int times1 = pt.getTimes();
			if (times1 > 1) { // TODO: how to set this threshold?  Just remove the token with only one time.
				ptList2.add(pt);
			}
		}
		ptList.removeAll(ptList2);
		ptList3.addAll(ptList2);
		ptList3.addAll(ptList);
		
		for (PredictToken pt : ptList3) {
			tokensList.add(pt.getToken());
		}
		
		return tokensList.size() > 5 ? tokensList.subList(0, 5) : tokensList;
	}
	
}
