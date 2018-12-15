package edu.lu.uni.serval.akka.method.parser;

import java.io.File;
import java.util.List;

public class ProjectsMessage {
	private List<String> projects = null;
	private String project = null;
	private List<File> javaFiles = null;
	private List<String> javaFilePathes = null;
	private int workerID;
	private String outputPath;
	
	public ProjectsMessage(List<String> projects, int workerID, String outputPath) {
		super();
		this.projects = projects;
		this.workerID = workerID;
		this.outputPath = outputPath;
	}
	
	public ProjectsMessage(String project, int workerID, String outputPath) {
		super();
		this.project = project;
		this.workerID = workerID;
		this.outputPath = outputPath;
	}
	
	public void setJavaFiles(List<File> javaFiles) {
		this.javaFiles = javaFiles;
	}
	
	public List<File> getJavaFiles() {
		return javaFiles;
	}
 	
	public List<String> getJavaFilePathes() {
		return javaFilePathes;
	}

	public void setJavaFilePathes(List<String> javaFilePathes) {
		this.javaFilePathes = javaFilePathes;
	}

	public List<String> getProjects() {
		return projects;
	}
	
	public String getProject() {
		return project;
	}
	
	public int getWorkerID() {
		return workerID;
	}

	public String getOutputPath() {
		return outputPath;
	}

}
