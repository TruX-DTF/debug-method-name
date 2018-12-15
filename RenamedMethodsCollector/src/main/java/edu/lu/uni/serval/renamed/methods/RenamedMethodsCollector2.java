package edu.lu.uni.serval.renamed.methods;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * Collect renamed methods from the commit history of a Java repo.
 * 
 * @author kui.liu
 *
 */
@Deprecated
public class RenamedMethodsCollector2 {

	private static final String INPUT_DATA_PATH = Configuration.COMMIT_DIFF_PATH;
	private static final String OUTPUT_DATA_PATH = Configuration.RENAMED_METHODS_PATH;
	
	public static void main(String[] args) throws IOException {
		System.out.println("========Argus: " + args[0]);
		List<String> projectList = readProjects();
		int index;
		try {
			index = Integer.valueOf(args[0]);
		} catch (NumberFormatException e1) {
			System.out.println("Wrong parameter: " + args[0]);
			return;
		}
		if (index >= projectList.size()) {
			System.out.println("The value of index is out of bound: " + args[0]);
			return;
		}
		
		String projectName = projectList.get(index);
		String filePath = INPUT_DATA_PATH + projectName;
		String outputPath = OUTPUT_DATA_PATH + projectName + "/";
		int startIndex = Integer.valueOf(args[1]);
		int endIndex = Integer.valueOf(args[2]);
		System.out.println(startIndex + " --- " + endIndex);
		parseRenamedMethods(filePath, outputPath, startIndex, endIndex);
	}

	private static List<String> readProjects() throws IOException {
		List<String> projectList = new ArrayList<>();
		
		File projectsFile = new File(Configuration.JAVA_REPOS_PATH);
		File[] files = projectsFile.listFiles();
		for (File file : files) {
			if (file.isDirectory() && !file.getName().startsWith(".")) {
				projectList.add(file.getName());
			}
		}
		return projectList;
	}

	private static void parseRenamedMethods(String inputProject, String outputPath, int startIndex, int endIndex) {
		ActorSystem system = null;
		ActorRef gitTravelActor = null;
		try {
			system = ActorSystem.create("methodNames-system");
			gitTravelActor = system.actorOf(ParseActor.props(inputProject, outputPath, startIndex, endIndex), "parse-actor");
			gitTravelActor.tell("BEGIN", ActorRef.noSender());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
