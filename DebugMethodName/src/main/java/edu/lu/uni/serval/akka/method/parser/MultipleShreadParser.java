package edu.lu.uni.serval.akka.method.parser;

import java.util.List;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class MultipleShreadParser {
	
	private ProjectsMessage msg;
	private int numberOfWorkers;
	
	public MultipleShreadParser(List<String> projectList, String outputPath, int numberOfWorkers) {
		this.msg = new ProjectsMessage(projectList, 0, outputPath);
		this.numberOfWorkers = numberOfWorkers;
	}
	
	public MultipleShreadParser(String project, String outputPath, int numberOfWorkers) {
		this.msg = new ProjectsMessage(project, 0, outputPath);
		this.numberOfWorkers = numberOfWorkers;
	}

	@SuppressWarnings("deprecation")
	public void parseMethods() {
		ActorSystem system = null;
		ActorRef parsingActor = null;
		
		try {
			system = ActorSystem.create("Parsing-Method-System");
			parsingActor = system.actorOf(ParseProjectActor.props(numberOfWorkers), "parse-method-actor");
			parsingActor.tell(msg, ActorRef.noSender());
		} catch (Exception e) {
			system.shutdown();
			e.printStackTrace();
		}
		
	}
}
