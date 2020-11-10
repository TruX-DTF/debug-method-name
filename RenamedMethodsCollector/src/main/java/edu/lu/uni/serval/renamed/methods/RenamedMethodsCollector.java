package edu.lu.uni.serval.renamed.methods;

import java.io.File;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * Collect renamed methods from the commit history of a Java repo.
 * 
 * @author kui.liu
 *
 */
public class RenamedMethodsCollector {

	private static final String INPUT_DATA_PATH = Configuration.COMMIT_DIFF_PATH;
	private static final String OUTPUT_DATA_PATH = Configuration.RENAMED_METHODS_PATH;
	
	public static void main(String[] args) {
		System.out.println("Argus: " + args[0]);
		List<String> projectList = CommitDiffs.readList(Configuration.JAVA_REPO_NAMES_FILE);
		int index;
		try {
			index = Integer.valueOf(args[0]);
		} catch (NumberFormatException e1) {
			System.err.println("Wrong parameter: " + args[0]);
			return;
		}
		if (index >= projectList.size()) {
			System.err.println("The value of index is out of bound: " + args[0]);
			return;
		}
		
		String projectName = projectList.get(index);// projectPath.getName();
		String filePath = INPUT_DATA_PATH + projectName;
		if (!new File(filePath).exists()) {
			File projectPath = new File(Configuration.JAVA_REPOS_PATH + projectName);
			if (!projectPath.exists()) return;
			File[] projectFiles = projectPath.listFiles();
			if (projectFiles.length == 0) {
				projectPath.delete();
				return;
			}
			
			File projectFile = projectFiles[0];
			String projectGit = projectFile.getPath() + "/.git";
			filePath = INPUT_DATA_PATH + projectName;
			
			if (!new File(filePath).exists()) {
				CommitDiffs.traverseGitRepos(projectName, projectGit);
			}
			
			String outputPath = OUTPUT_DATA_PATH + projectName + "/";
			parseRenamedMethods(filePath, outputPath);
		} else {
			String outputPath = OUTPUT_DATA_PATH + projectName + "/";
			parseRenamedMethods(filePath, outputPath);
		}
	}

	private static void parseRenamedMethods(String inputProject, String outputPath) {
		ActorSystem system = null;
		ActorRef gitTravelActor = null;
		try {
			system = ActorSystem.create("methodNames-system");
			gitTravelActor = system.actorOf(ParseActor.props(inputProject, outputPath), "parse-actor");
			gitTravelActor.tell("BEGIN", ActorRef.noSender());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
