package edu.lu.uni.serval.git.travel;

import java.util.List;

public class MyDiffEntry {
	
	private String revFile; //commitId + file_path + file_name.
	private String prevFile;//prev_ + commitId + file_path + file_name.
	private List<ModifiedDetails> modifiedDetails;
	private String commitId;
	private String prevCommitId; // parent commit id.
	
	public MyDiffEntry() {
		
	}
	
	public MyDiffEntry(String revFile, String prevFile, List<ModifiedDetails> modifiedDetails) {
		this.revFile = revFile;
		this.prevFile = prevFile;
		this.modifiedDetails = modifiedDetails;
	}
	
	public String getCommitId() {
		return commitId;
	}

	public void setCommitId(String commitId) {
		this.commitId = commitId;
	}

	public String getPrevCommitId() {
		return prevCommitId;
	}

	public void setPrevCommitId(String prevCommitId) {
		this.prevCommitId = prevCommitId;
	}

	public String getRevFile() {
		return revFile;
	}
	
	public void setRevFile(String revFile) {
		this.revFile = revFile;
	}

	public String getPrevFile() {
		return prevFile;
	}

	public void setPrevFile(String prevFile) {
		this.prevFile = prevFile;
	}

	public List<ModifiedDetails> getModifiedDetails() {
		return modifiedDetails;
	}

	public void setModifiedDetails(List<ModifiedDetails> modifiedDetails) {
		this.modifiedDetails = modifiedDetails;
	}

	@Override
	public String toString() {
		String delAddLines = "";
		for (ModifiedDetails md : modifiedDetails) {
			delAddLines += md.toString();
		}
		return "======Previous File:" + prevFile + "======\n" + 
			   "======Revised File:" + revFile + "======\n" + delAddLines + "\n";
	}
	
	public boolean onlyContainsDelLines() {
		int addCounts = 0;
		
		for (ModifiedDetails md : modifiedDetails) {
			if (md.getAddLines() == null || md.getAddLines().equals(""))
				addCounts ++;
		}
		
		if (addCounts == modifiedDetails.size()) {
			return true;
		}
		return false;
	}
	
	public boolean onlyContainsAddLines() {
		int delCounts = 0;
		
		for (ModifiedDetails md : modifiedDetails) {
			if (md.getDelLines() == null || md.getDelLines().equals(""))
				delCounts ++;
		}

		if (delCounts == modifiedDetails.size()) {
			return true;
		}
		return false;
	}
}
