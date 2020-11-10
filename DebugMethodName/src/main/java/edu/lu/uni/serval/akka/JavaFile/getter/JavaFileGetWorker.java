package edu.lu.uni.serval.akka.JavaFile.getter;

import java.io.File;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import edu.lu.uni.serval.Configuration;
import edu.lu.uni.serval.utils.FileHelper;

public class JavaFileGetWorker extends UntypedActor {
	
	private static Logger log = LoggerFactory.getLogger(JavaFileGetWorker.class);
	
	public JavaFileGetWorker() {
	}

	public static Props props() {
		return Props.create(new Creator<JavaFileGetWorker>() {

			private static final long serialVersionUID = -7615153844097275009L;

			@Override
			public JavaFileGetWorker create() throws Exception {
				return new JavaFileGetWorker();
			}
			
		});
	}

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof WorkerMessage) {
			WorkerMessage msg = (WorkerMessage) message;
			List<File> javaProjects = msg.getJavaProjects();
			int workerId = msg.getWorkderId();
			
			for (File javaProject : javaProjects) {
				if (javaProject.isDirectory()) {
					obtainAllJavaFiles(javaProject);
				}
			}
			
			log.debug("Worker #" + workerId +" Finish of reading java files... ========== " + javaProjects.get(0).getParentFile().getName() + " = " + counter);
			this.getSender().tell("SHUT_DOWN", getSelf());
		} else {
			unhandled(message);
		}
	}
	
	/**
	 * Recursively list all files in the file.
	 * 
	 * @param projectPath
	 * @return
	 */
	private void obtainAllJavaFiles(File projectPath) {
		StringBuilder pathesBuilder = new StringBuilder();
		pathesBuilder = getAllFiles(projectPath, ".java", projectPath.getPath().length() + 1);
		String javaFileNamesPath = Configuration.JAVA_FILES_PATH + projectPath.getName() + ".txt";
		FileHelper.outputToFile(javaFileNamesPath, pathesBuilder, false);
		pathesBuilder.setLength(0);
	}
	
	int counter = 0;
	
	private StringBuilder getAllFiles(File file, String type, int length) {
		StringBuilder pathesBuilder = new StringBuilder();
		
		if (!file.exists()) {
			return pathesBuilder;
		}
		
		File[] files = file.listFiles();
		if (files == null || files.length == 0) {
			return pathesBuilder;
		}
		
		for (File f : files) {
			if (f.isFile()) { // Filter out test, sample, example, and template Java code files.
				String filePath = f.getPath();
				if (filePath.endsWith(type)) {
					String filePathTmp = filePath.substring(length).toLowerCase(Locale.ROOT);
					if (filePathTmp.contains("test") 
							|| filePathTmp.contains("sample") 
							|| filePathTmp.contains("example") 
							|| filePathTmp.contains("template")) continue;
					pathesBuilder.append(filePath).append("\n");
					counter ++;
				}
			} else {
				pathesBuilder.append(getAllFiles(f, type, length));
			}
		}
		
		return pathesBuilder;
	}

}
