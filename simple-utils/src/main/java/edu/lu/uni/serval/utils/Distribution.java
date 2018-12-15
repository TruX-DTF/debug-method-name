package edu.lu.uni.serval.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import edu.lu.uni.serval.utils.FileHelper;
import edu.lu.uni.serval.utils.ListSorter;

public class Distribution {
	
	public enum MaxSizeType {  
		UpperWhisker, ThirdQuartile
	}
	
	public enum MinSizeType {  
		LowerWhisker, FirstQuartile
	}
	
	public static List<Integer> readSizes(File tokenFile, String sizeFilePath, String tokenFileExtension, String sizeFileExtension) throws IOException {
		List<File> sizeFiles = FileHelper.getAllFiles(sizeFilePath, sizeFileExtension);
		String sizeFileName = tokenFile.getName().replace(tokenFileExtension, sizeFileExtension);
		File sizeFile = null;
		for (File file : sizeFiles) {
			if (file.getName().contains(sizeFileName)) {
				sizeFile = file;
				break;
			}
		}
		
		return readSizes(sizeFile);
	}
	
	public static List<Integer> readSizes(File sizesFile) throws NumberFormatException, IOException {
		List<Integer> sizes = new ArrayList<>();
		String sizesStr = FileHelper.readFile(sizesFile);
		BufferedReader br = new BufferedReader(new StringReader(sizesStr));
		String line = null;
		
		while ((line = br.readLine()) != null) {
			sizes.add(Integer.parseInt(line.trim()));
		}
		
		return sizes;
	}
	
	public static List<Integer> readSizes(String sizesFile) throws NumberFormatException, IOException {
		return readSizes(new File(sizesFile));
	}
	
	public static int computeMaxSize(MaxSizeType maxSizeType, List<Integer> sizesDistribution) {
		int maxSize = 0;
		switch (maxSizeType) {
		case UpperWhisker:
			maxSize = upperWhisker(sizesDistribution);
			break;
		case ThirdQuartile:
			maxSize = thirdQuarter(sizesDistribution);
			break;
		}
		return maxSize;
	}
	
	public static int computeMinSize(MinSizeType minSizeType, List<Integer> sizesDistribution) {
		int maxSize = 0;
		switch (minSizeType) {
		case LowerWhisker:
			maxSize = lowerWhisker(sizesDistribution);
			break;
		case FirstQuartile:
			maxSize = firstQuarter(sizesDistribution);
			break;
		}
		return maxSize;
	}

	private static int lowerWhisker(List<Integer> sizesDistribution) {
		List<Integer> sizes = new ArrayList<>();
		sizes.addAll(sizesDistribution);
		ListSorter<Integer> sorter = new ListSorter<Integer>(sizes);
		sizesDistribution = sorter.sortAscending();
		int firstQuarterIndex = (int)(0.25 * sizesDistribution.size());
		int firstQuarter = sizesDistribution.get(firstQuarterIndex);
		int thirdQuarterIndex = (int)(0.75 * sizesDistribution.size());
		int thirdQuarter = sizesDistribution.get(thirdQuarterIndex);
		int lowerWhisker = firstQuarter - (int) (1.5 * (thirdQuarter - firstQuarter));
		return lowerWhisker < sizesDistribution.get(0) ? sizesDistribution.get(0) : lowerWhisker;
	}

	private static int upperWhisker(List<Integer> sizesDistribution) {
		List<Integer> sizes = new ArrayList<>();
		sizes.addAll(sizesDistribution);
		ListSorter<Integer> sorter = new ListSorter<Integer>(sizes);
		sizesDistribution = sorter.sortAscending();
		int firstQuarterIndex = (int)(0.25 * sizesDistribution.size());
		int firstQuarter = sizesDistribution.get(firstQuarterIndex);
		int thirdQuarterIndex = (int)(0.75 * sizesDistribution.size());
		int thirdQuarter = sizesDistribution.get(thirdQuarterIndex);
		int upperWhisker = thirdQuarter + (int) (1.5 * (thirdQuarter - firstQuarter));
		int maxSize = sizesDistribution.get(sizesDistribution.size() - 1);
		return upperWhisker > maxSize ? maxSize : upperWhisker;
	}

	private static int firstQuarter(List<Integer> sizesDistribution) {
		List<Integer> sizes = new ArrayList<>();
		sizes.addAll(sizesDistribution);
		ListSorter<Integer> sorter = new ListSorter<Integer>(sizes);
		sizesDistribution = sorter.sortAscending();
		int firstQuarterIndex = (int)(0.25 * sizesDistribution.size());
		int firstQuarter = sizesDistribution.get(firstQuarterIndex);
		return firstQuarter;
	}
	
	private static int thirdQuarter(List<Integer> sizesDistribution) {
		List<Integer> sizes = new ArrayList<>();
		sizes.addAll(sizesDistribution);
		ListSorter<Integer> sorter = new ListSorter<Integer>(sizes);
		sizesDistribution = sorter.sortAscending();
		int thirdQuarterIndex = (int)(0.75 * sizesDistribution.size());
		int thirdQuarter = sizesDistribution.get(thirdQuarterIndex);
		return thirdQuarter;
	}
}
