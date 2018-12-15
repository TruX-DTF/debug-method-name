package edu.lu.uni.serval.git.filter;

import org.eclipse.jgit.revwalk.RevCommit;

public class CommitFilter {
	
	public static boolean filterInitializeCommit(RevCommit revCommit) {
		boolean initializeCommit = false;
		if (revCommit.getParentCount() == 0) {
			initializeCommit = true;
		}
		return initializeCommit;
	}
	
	public static boolean filterMergeCommit(RevCommit revCommit) {
		boolean mergeCommit = false;
		if (revCommit.getParentCount() > 1 || revCommit.getShortMessage().toLowerCase().startsWith("merge ")
				|| revCommit.getShortMessage().toLowerCase().startsWith("merged ")) {
			mergeCommit = true;
		}
		return mergeCommit;
	}
}
