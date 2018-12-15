package edu.lu.uni.serval.renamed.methods;

import java.io.File;
import java.util.List;

public class MessageFiles {
	private int id;
	private List<File> revFiles;
	
	public MessageFiles(int id) {
		this.id = id;
	}
	
	public List<File> getRevFiles() {
		return revFiles;
	}

	public void setRevFiles(List<File> revFiles) {
		this.revFiles = revFiles;
	}

	public int getId() {
		return id;
	}
	
}
