package edu.lu.uni.serval.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FeatureReader {
	
	private File featureFile;
	private List<Double[]> features ;

	public void setFeatureFile(File featureFile) {
		this.featureFile = featureFile;
	}

	public List<Double[]> getFeatures() {
		return features;
	}

	public void readFeatures() {
		features = new ArrayList<>();
		FileInputStream fis = null;
		Scanner scanner = null;
		
		try {
			fis = new FileInputStream(featureFile);
			scanner = new Scanner(fis);
			
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if ("".equals(line)) {
					features.add(null);
				} else {
					Double[] feature = doubleParseFeature(line);
					features.add(feature);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				scanner.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Double[] doubleParseFeature(String feature) {
		String[] features = feature.split(",");
		int length = features.length;
		Double[] doubleFeatures = new Double[length];
		for (int i = 0; i < length; i ++) {
			doubleFeatures[i] = Double.parseDouble(features[i]);
		}
		return doubleFeatures;
	}
	
}
