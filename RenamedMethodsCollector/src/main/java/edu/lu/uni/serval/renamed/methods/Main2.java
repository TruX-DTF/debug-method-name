package edu.lu.uni.serval.renamed.methods;

import java.util.List;

public class Main2 {
	/**
	 * Collect commits for one single project.
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> projectList = CommitDiffs.readList(Configuration.JAVA_REPO_NAMES_FILE);
		int index = Integer.parseInt(args[0]);
		String project = projectList.get(index);
		CommitDiffs.traverseGitRepos(project, Configuration.JAVA_REPOS_PATH + project + "/.git");
	}

}
