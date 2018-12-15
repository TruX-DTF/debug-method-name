package edu.lu.uni.serval.akka.method.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import edu.lu.uni.serval.jdt.method.Method;
import edu.lu.uni.serval.jdt.parser.JavaFileParser;
import edu.lu.uni.serval.method.parser.MethodParser;
import edu.lu.uni.serval.method.parser.util.MethodExporter;

public class ParseProjectWorker extends UntypedActor {
	
	private static Logger log = LoggerFactory.getLogger(ParseProjectWorker.class);
	
	public ParseProjectWorker() {
	}

	public static Props props() {
		return Props.create(new Creator<ParseProjectWorker>() {

			private static final long serialVersionUID = -7615153844097275009L;

			@Override
			public ParseProjectWorker create() throws Exception {
				return new ParseProjectWorker();
			}
			
		});
	}

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof ProjectsMessage) {
			ProjectsMessage pro = (ProjectsMessage) message;
			List<String> projects = pro.getProjects();
			String outputPath = pro.getOutputPath();
			int workerId = pro.getWorkerID();
			
			int numberOfMethods = 0;
			int numberOfNonNullMethods = 0;
			MethodParser parser = new MethodParser();
			
			if (projects != null) {
				for (String project : projects) {
					List<Method> methods = parser.parseMethods(project);// samples, examples
					if (methods != null) {
						int size = methods.size(); 
						if (size > 0) {
							numberOfMethods += size;
							int nonNullMethods = exportParsedMethods(methods, outputPath, workerId);
							numberOfNonNullMethods += nonNullMethods;
							log.debug("Worker #" + workerId +" Finish of parsing " + size + " methods " + nonNullMethods + " in project " + project + "...");
							methods.clear();
						}
					}
				}
			} else {
				List<File> javaFiles = pro.getJavaFiles();

				String projectPath = pro.getProject();
				List<Method> allMethods = new ArrayList<>();
				String projectName = projectPath.substring(projectPath.lastIndexOf("/") + 1);
				
				if (javaFiles != null) {
					for (File javaFile : javaFiles) {
						JavaFileParser jfp = new JavaFileParser();
						jfp.parseJavaFile(projectName, javaFile);
						List<Method> methods= jfp.getMethods();
						if (methods.isEmpty()) continue;
						allMethods.addAll(methods);
						int size = allMethods.size();
						if (size >= 500) {
							numberOfMethods += size;
							int nonNullMethods = exportParsedMethods(allMethods, outputPath, workerId);
							numberOfNonNullMethods += nonNullMethods;
							allMethods.clear();
						}
					}
				} else {
					List<String> javaFilePathes = pro.getJavaFilePathes();
					for (String javaFilePath : javaFilePathes) {
						JavaFileParser jfp = new JavaFileParser();
						jfp.parseJavaFile(projectName, new File(javaFilePath));
						List<Method> methods= jfp.getMethods();
						if (methods.isEmpty()) continue;
						allMethods.addAll(methods);
						int size = allMethods.size();
						if (size >= 500) {
							numberOfMethods += size;
							int nonNullMethods = exportParsedMethods(allMethods, outputPath, workerId);
							numberOfNonNullMethods += nonNullMethods;
							allMethods.clear();
						}
					}
				}

				int size = allMethods.size();
				numberOfMethods += size;
				int nonNullMethods = exportParsedMethods(allMethods, outputPath, workerId);
				numberOfNonNullMethods += nonNullMethods;
				allMethods.clear();
			}
			
			log.debug("Worker #" + workerId +" Finish of parsing " + numberOfMethods + " methods");
			
			this.getSender().tell("SHUT_DOWN:" + numberOfMethods + ":" + numberOfNonNullMethods, getSelf());
		} else {
			unhandled(message);
		}
	}
	
	/**
	 * Export parsed methods.
	 * 
	 * @param parsedMethods
	 * @param outputPath
	 * @param tokenFileExtension
	 * @param numericFileExtension
	 * @param tokenTypes
	 * @return
	 */
	private int exportParsedMethods(List<Method> parsedMethods, String outputPath, int workerId) {
		int numberOfNonNullMethods = 0;
		MethodExporter exporter = new MethodExporter(outputPath);
		numberOfNonNullMethods += exporter.outputMethods(parsedMethods, workerId);
		return numberOfNonNullMethods;
	}
}
