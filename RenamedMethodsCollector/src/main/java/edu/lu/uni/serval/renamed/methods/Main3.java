package edu.lu.uni.serval.renamed.methods;

public class Main3 {
	/**
	 * Collect renamed methods for one single project.
	 * @param args
	 */
	public static void main(String[] args) {
//		RenamedMethodsCollector.main(args); // Collect renamed methods.
		int start = 0;
		int end = 1;
		for (int i = start; start < end; start ++) {
			String[] argss = {"" + i};
			RenamedMethodsCollector.main(argss); // Collect renamed methods.
		}
		
	}

}
