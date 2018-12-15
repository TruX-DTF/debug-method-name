package edu.lu.uni.serval.renamed.methods;

public class Main {

	public static void main(String[] args) {
		CommitDiffs.main(null); // Collect all commits and generate previous java files and revised java files from Java repositories.
		
		for (int i = 0; i < 430; i ++) {
			args = new String[] {"" + i};
			RenamedMethodsCollector.main(args); // Collect renamed methods.
		}
	}

}
