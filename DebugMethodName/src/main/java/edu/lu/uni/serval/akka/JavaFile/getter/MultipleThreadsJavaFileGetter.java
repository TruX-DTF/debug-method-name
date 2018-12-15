package edu.lu.uni.serval.akka.JavaFile.getter;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * Use multiple threads to get all java files to improve the efficiency of parsing Java methods.
 * 
 * @author kui.liu
 *
 */
public class MultipleThreadsJavaFileGetter {
	
	private String projectsPath;
	private int numberOfWorkers;
	
	public MultipleThreadsJavaFileGetter(String projectsPath, int numberOfWorkers) {
		this.projectsPath = projectsPath;
		this.numberOfWorkers = numberOfWorkers;
	}


	@SuppressWarnings("deprecation")
	public void getJavaFiles() {
		ActorSystem system = null;
		ActorRef parsingActor = null;
		
		try {
			system = ActorSystem.create("Parsing-Method-System");
			parsingActor = system.actorOf(JavaFileGetActor.props(numberOfWorkers), "parse-method-actor");
			parsingActor.tell(projectsPath, ActorRef.noSender());
		} catch (Exception e) {
			system.shutdown();
			e.printStackTrace();
		}
		
	}
}
