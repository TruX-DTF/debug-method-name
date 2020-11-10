package edu.lu.uni.serval.renamed.methods;

import java.util.List;

public class Main {
	/**
	 * Collect renamed methods for all project.
	 * @param args
	 */
	public static void main(String[] args) {
		CommitDiffs.main(null); // Collect all commits and generate previous java files and revised java files from Java repositories.
		List<String> projects = CommitDiffs.readList(Configuration.JAVA_REPO_NAMES_FILE);
		for (int i = 0; i < projects.size(); i ++) {
			args = new String[] {"" + i};
			RenamedMethodsCollector.main(args); // Collect renamed methods.
		}
	}

}
