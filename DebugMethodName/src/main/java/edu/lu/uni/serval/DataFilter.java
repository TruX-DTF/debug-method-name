package edu.lu.uni.serval;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import akka.routing.RoundRobinPool;
import edu.lu.uni.serval.method.parser.MethodNameParser;
import edu.lu.uni.serval.utils.FileHelper;

/**
 * Filter out noise data.
 * 
 * @author kui.liu
 *
 */
@Deprecated
public class DataFilter {

	public static void main(String[] args) {
		ActorSystem system = null;
		ActorRef parsingActor = null;
		
		try {
			system = ActorSystem.create("Parsing-Method-System");
			parsingActor = system.actorOf(JavaFileGetActor.props(), "parse-method-actor");
			parsingActor.tell("BEGIN", ActorRef.noSender());
		} catch (Exception e) {
			system.shutdown();
			e.printStackTrace();
		}
	}

	static class JavaFileGetActor extends UntypedActor {
		
		private ActorRef travelRouter;
		private int numberOfWorkers = 1000;
		private int counter = 0;
		
		public JavaFileGetActor() {
			this.travelRouter = this.getContext().actorOf(new RoundRobinPool(numberOfWorkers)
					.props(JavaFileGetWorker.props()), "parse-method-router");
		}

		public static Props props() {
			
			return Props.create(new Creator<JavaFileGetActor>() {

				private static final long serialVersionUID = 9207427376110704705L;

				@Override
				public JavaFileGetActor create() throws Exception {
					return new JavaFileGetActor();
				}
				
			});
		}
		
		@Override
		public void onReceive(Object message) throws Exception {
			if (message instanceof String && message.toString().equals("BEGIN")) {
				File path = new File(Configuration.TOKENIZED_METHODS_PATH);
				List<File> projects = new ArrayList<>();
				File[] files = path.listFiles();
				for (File file : files) {
					if (file.isDirectory()) {
						projects.add(file);
					}
				}
				System.out.println(projects.size());
				
				int size = projects.size();
				numberOfWorkers = size;
				
				for (int i = 0; i < numberOfWorkers; i ++) {
					final WorkerMessage pro = new WorkerMessage(i + 1, projects.get(i));
					travelRouter.tell(pro, getSelf());
				}
			} else if (message.toString().equals("SHUT_DOWN")) {
				counter ++;
				if (counter >= numberOfWorkers) {
					System.out.println("All workers finished their work...");
					this.getContext().stop(travelRouter);
					this.getContext().stop(getSelf());
					this.getContext().system().shutdown();
				}
			} else {
				unhandled(message);
			}
		}
	}
	
	static class JavaFileGetWorker extends UntypedActor {
		
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
				String javaProjectPath = msg.getJavaProject().getPath();
				// method_bodies.txt, sizes.csv, tokens.txt. ParsedMethodName.txt
				File tokensFile = new File(javaProjectPath + "/tokens.txt");
				File tokensFileTmp = new File(javaProjectPath + "/tokens.txt.temp");
				if (tokensFileTmp.exists()) tokensFileTmp.delete();
				
				int workerId = msg.getWorkderId();
				if (tokensFile.exists()) {
					List<Integer> indexes = new ArrayList<>();
					
					StringBuilder parsedMethodNames = new StringBuilder();
					FileInputStream fis = new FileInputStream(tokensFile);
					Scanner scanner = new Scanner(fis);
					StringBuilder builder = new StringBuilder();
					int index = -1;
					while (scanner.hasNextLine()) {
						index ++;
						String line = scanner.nextLine();
						String[] elements = line.split(":");
						String className = elements[2].toLowerCase(Locale.ROOT);
						if (className.contains("test") || className.contains("example")
								|| className.contains("sample") || className.contains("template")) {
							continue;
						}
						String packageName = elements[1].toLowerCase(Locale.ROOT);
						if (packageName.contains("test") || packageName.contains("example")
								|| packageName.contains("sample") || packageName.contains("template")) {
							continue;
						}
						
						String methodName = elements[3];
						String parsedMethodNameSubTokens = new MethodNameParser().parseMethodName(methodName);
						String methodInfo = line.substring(0, line.indexOf("#") + 1);
						parsedMethodNames.append(methodInfo).append(parsedMethodNameSubTokens).append("\n");
						
						indexes.add(index);
						builder.append(line).append("\n");
						if (index % 1000 == 0) {
							FileHelper.outputToFile(tokensFileTmp, builder, true);
							builder.setLength(0);
						}
					}
					scanner.close();
					fis.close();
					FileHelper.outputToFile(tokensFileTmp, builder, true);
					builder.setLength(0);
					FileHelper.outputToFile(javaProjectPath + "/ParsedMethodNames.txt", parsedMethodNames, false);
					tokensFile.delete();
					tokensFileTmp.renameTo(tokensFile);

					selectMethodBodies(indexes, "/method_bodies.txt", javaProjectPath);
					selectData(indexes, "sizes", ".csv", javaProjectPath);
//					selectData(indexes, "ParsedMethodName", ".txt", javaProjectPath);
					
					System.out.println("Worker #" + workerId +" Finish the work... ========== ");
				} else {
					System.out.println("Worker #" + workerId +" Finish the work... ========== No data." +  javaProjectPath + "/tokens.txt" );
				}
				this.getSender().tell("SHUT_DOWN", getSelf());
			} else {
				unhandled(message);
			}
		}

		private void selectData(List<Integer> indexes, String type, String fileType, String javaProjectPath) throws IOException {
			String methodDataPath = javaProjectPath + "/" + type + fileType;
			if (!new File(methodDataPath).exists()) {
				mergeData(javaProjectPath, methodDataPath, type, fileType);
			}
			String selectedDataPath = methodDataPath + ".temp";
			new File(selectedDataPath).delete();
			
			FileInputStream fis = new FileInputStream(methodDataPath);
			Scanner scanner = new Scanner(fis);
			StringBuilder builder = new StringBuilder();
			int index = -1;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				index ++;
				if (indexes.contains(index)) {
					builder.append(line).append("\n");
				}
			}
			scanner.close();
			fis.close();

			FileHelper.outputToFile(selectedDataPath, builder, false);
			builder.setLength(0);
			
			File f = new File(methodDataPath);
			f.delete();
			new File(selectedDataPath).renameTo(f);
		}

		private void selectMethodBodies(List<Integer> indexes, String fileName, String javaProjectPath) throws IOException {
			String methodBodiesPath = javaProjectPath + fileName;
			if (!new File(methodBodiesPath).exists()) {
				mergeData(javaProjectPath, methodBodiesPath, "method_bodies", ".txt");
			}
			String selectedMethodsPath = methodBodiesPath + ".temp";
			new File(selectedMethodsPath).delete();
			
			FileInputStream fis = new FileInputStream(methodBodiesPath);
			Scanner scanner = new Scanner(fis);
			StringBuilder singleMethod = new StringBuilder();
			StringBuilder selectedMethods = new StringBuilder();
			int index = -1;
			int counter = 0;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if ("#METHOD_BODY#========================".equals(line)) {
					if (indexes.contains(index)) {
						selectedMethods.append(singleMethod.toString()).append("\n");
						if (counter % 1000 == 0) {
							FileHelper.outputToFile(selectedMethodsPath, selectedMethods, true);
							selectedMethods.setLength(0);
						}
						counter ++;
					}
					singleMethod.setLength(0);
					index ++;
				}
				singleMethod.append(line).append("\n");
			}
			if (indexes.contains(index)) {
				selectedMethods.append(singleMethod.toString()).append("\n");
			}
			scanner.close();
			fis.close();

			FileHelper.outputToFile(selectedMethodsPath, selectedMethods, true);
			selectedMethods.setLength(0);
			
			File f = new File(methodBodiesPath);
			f.delete();
			new File(selectedMethodsPath).renameTo(f);
		}
		
		private void mergeData(String javaProjectPath, String outputFileName, String type, String fileType) {
			FileHelper.deleteFile(outputFileName);
			
			for (int i = 1; i <= 1000; i ++) {
				File file = new File(javaProjectPath + "/" + type + "/" + type + "_" + i + fileType);
				if (file.exists()) {
					FileHelper.outputToFile(outputFileName, FileHelper.readFile(javaProjectPath + "/" + type + "/" + type + "_" + i + fileType), true);
					file.delete();
				}
			}
			FileHelper.deleteDirectory(javaProjectPath + "/" + type);
		}
	}
}

class WorkerMessage {
	private int workderId;
	private File javaProject;
	
	public WorkerMessage(int workderId, File javaProject) {
		super();
		this.workderId = workderId;
		this.javaProject = javaProject;
	}

	public int getWorkderId() {
		return workderId;
	}

	public File getJavaProject() {
		return javaProject;
	}
	
}
