package edu.lu.uni.serval.git.travel;

public class CommitFile implements Comparable<CommitFile> {
	
	private String fileName;
	private String commitId;
	private Integer commitTime;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCommitId() {
		return commitId;
	}

	public void setCommitId(String commitId) {
		this.commitId = commitId;
	}

	public Integer getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(Integer commitTime) {
		this.commitTime = commitTime;
	}

	@Override
	public int compareTo(CommitFile cf) {
		return this.commitTime.compareTo(cf.commitTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CommitFile) {
			CommitFile cf = (CommitFile) obj;
			if (cf.fileName.equals(this.fileName) && cf.commitId.equals(this.commitId)) {
				return true;
			}
		}
		return false;
	}
	
}
