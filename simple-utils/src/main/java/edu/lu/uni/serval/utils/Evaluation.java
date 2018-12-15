package edu.lu.uni.serval.utils;

import java.text.DecimalFormat;

public class Evaluation {
	
	private static final DecimalFormat FORMAT = new DecimalFormat("0.000");
	
	private int truePositives;
	private int trueNegatives;
	private int falsePositives;
	private int falseNegatives;
	
	private double accuracy;
	private double precision;
	private double recall;
	private double f1_measure;
	
	public Evaluation(int truePositives, int trueNegatives, int falsePositives, int falseNegatives) {
		super();
		this.truePositives = truePositives;
		this.trueNegatives = trueNegatives;
		this.falsePositives = falsePositives;
		this.falseNegatives = falseNegatives;
	}
	
	public void evaluate() {
		accuracy = (double) (this.truePositives + this.trueNegatives) / (this.truePositives + this.trueNegatives + this.falsePositives + this.falseNegatives);
		precision = (double) this.truePositives / (this.truePositives + this.falsePositives);
		recall = (double) this.truePositives / (this.truePositives + this.falseNegatives);
		f1_measure = (double) 2 * this.truePositives / (2 * this.truePositives + this.falsePositives + this.falseNegatives);
		outputResults();
	}

	private void outputResults() {
		System.out.println("==========================================");
		System.out.println("Accuracy:   " + FORMAT.format(accuracy * 100) + "%");
		System.out.println("Precision:  " + FORMAT.format(precision * 100) + "%");
		System.out.println("Recall:     " + FORMAT.format(recall * 100) + "%");
		System.out.println("F1-measure: " + FORMAT.format(f1_measure * 100) + "%");
		System.out.println("==========================================");
	}
	
}
