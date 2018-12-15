package edu.lu.uni.serval.renamed.methods;

import java.io.File;

public class RunnableParser implements Runnable {

	private File prevFile;
	private File revFile;
	private File diffentryFile;
	private CodeChangeParser parser;
	
	public RunnableParser(File prevFile, File revFile, File diffentryFile, CodeChangeParser parser) {
		this.prevFile = prevFile;
		this.revFile = revFile;
		this.diffentryFile = diffentryFile;
		this.parser = parser;
	}

	@Override
	public void run() {
		parser.parse(revFile, prevFile, diffentryFile);
	}
}