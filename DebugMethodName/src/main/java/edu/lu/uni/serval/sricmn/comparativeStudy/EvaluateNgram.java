package edu.lu.uni.serval.sricmn.comparativeStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import edu.lu.uni.serval.utils.FileHelper;

public class EvaluateNgram {

	public static void main(String[] args) throws IOException {
		int size = 0;
		int falseNegatives = 0;
		int trueNegatives = 0;
		int falsePositives = 0;
		int truePositives = 0;
		
		String content = FileHelper.readFile("OUTPUT/N-gram.results");
		BufferedReader reader = new BufferedReader(new StringReader(content));
		String line = reader.readLine();
		while ((line = reader.readLine()) != null) {
			String[] elements = line.split(",");
			int predictedResult = Integer.parseInt(elements[0]);
			int realResult = Integer.parseInt(elements[1]);
			if (realResult == 0) { // inconsistent name
				if (predictedResult == 0) {
					truePositives ++;
				} else {
					falseNegatives ++;
				}
			} else {
				if (predictedResult == 1) {
					trueNegatives ++;
				} else {
					falsePositives ++;
				}
			}
			size++;
		}
		reader.close();
		System.out.println(size);
		System.out.println("=======Identify inconsistent methods======");
		System.out.println(" Positives Negative ");
		System.out.println("True " + truePositives + " " + trueNegatives + " " + (truePositives + trueNegatives));
		System.out.println("False " + falsePositives + " " + falseNegatives);
		edu.lu.uni.serval.utils.Evaluation eval = new edu.lu.uni.serval.utils.Evaluation(truePositives, trueNegatives, falsePositives, falseNegatives);
		eval.evaluate();
		System.out.println("------------------------------------------");
	}

}
