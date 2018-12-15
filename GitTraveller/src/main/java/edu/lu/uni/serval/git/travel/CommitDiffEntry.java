package edu.lu.uni.serval.git.travel;

import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.revwalk.RevCommit;

public class CommitDiffEntry {
	
	private RevCommit commit;
	private RevCommit parentCommit;
	private DiffEntry diffentry;
	
	public RevCommit getCommit() {
		return commit;
	}
	
	public DiffEntry getDiffentry() {
		return diffentry;
	}
	
	public RevCommit getParentCommit() {
		return parentCommit;
	}
	
	public CommitDiffEntry(RevCommit commit, RevCommit parentCommit, DiffEntry diffentry) {
		this.commit = commit;
		this.parentCommit = parentCommit;
		this.diffentry = diffentry;
	}
	
}
