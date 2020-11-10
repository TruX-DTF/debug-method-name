package edu.lu.uni.serval.akka.JavaFile.getter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import akka.routing.RoundRobinPool;
import edu.lu.uni.serval.Configuration;
import edu.lu.uni.serval.utils.FileHelper;

public class JavaFileGetActor extends UntypedActor {
	
	private static Logger logger = LoggerFactory.getLogger(JavaFileGetActor.class);

	private ActorRef travelRouter;
	private final int numberOfWorkers;
	private int counter = 0;
	
	public JavaFileGetActor(int numberOfWorkers) {
		this.numberOfWorkers = numberOfWorkers;
		this.travelRouter = this.getContext().actorOf(new RoundRobinPool(numberOfWorkers)
				.props(JavaFileGetWorker.props()), "parse-method-router");
	}

	public static Props props(final int numberOfWorkers) {
		
		return Props.create(new Creator<JavaFileGetActor>() {

			private static final long serialVersionUID = 9207427376110704705L;

			@Override
			public JavaFileGetActor create() throws Exception {
				return new JavaFileGetActor(numberOfWorkers);
			}
			
		});
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof String && !message.toString().equals("SHUT_DOWN")) {
			String msg = message.toString();
			List<File> projects = new ArrayList<>();
			File[] files = new File(msg).listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					projects.add(file);
				}
			}
			
			int size = projects.size();
			int average = size / numberOfWorkers;
			int reminder = size % numberOfWorkers;
			int index = 0;
			
			for (int i = 0; i < numberOfWorkers; i ++) {
				int fromIndex = i * average + index;
				if (index < reminder) index ++;
				int toIndex = (i + 1) * average + index;
				
				
				List<File> javaProjectsOfWoker = new ArrayList<>();
				javaProjectsOfWoker.addAll(projects.subList(fromIndex, toIndex));
				final WorkerMessage pro = new WorkerMessage(i + 1, javaProjectsOfWoker);
				travelRouter.tell(pro, getSelf());
				logger.debug("Assign a task to worker #" + (i + 1) + "...");
			}
		} else if (message.toString().equals("SHUT_DOWN")) {
			counter ++;
			logger.debug(counter + " workers finished their work...");
			if (counter >= numberOfWorkers) {
				mergeData();// merge data.
				logger.info("All workers finished their work...");
				this.getContext().stop(travelRouter);
				this.getContext().stop(getSelf());
				this.getContext().system().shutdown();
			}
		} else {
			unhandled(message);
		}
	}
	
	private void mergeData() {
		String outputFileName = Configuration.JAVA_FILES_FILE;
		FileHelper.deleteFile(outputFileName);
		
		String dataPath = Configuration.JAVA_FILES_PATH;
		if (new File(dataPath).exists()) {
			List<File> files = FileHelper.getAllFiles(dataPath, ".txt");
			for (File file : files) {
				FileHelper.outputToFile(outputFileName, FileHelper.readFile(file), true);
			}
		}
	}

}
