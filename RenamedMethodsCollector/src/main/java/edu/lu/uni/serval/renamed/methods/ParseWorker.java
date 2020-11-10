package edu.lu.uni.serval.renamed.methods;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import edu.lu.uni.serval.utils.FileHelper;

public class ParseWorker extends UntypedActor {

	private static Logger log = LoggerFactory.getLogger(ParseWorker.class);

	private String OUTPUT_PATH1;
	private String OUTPUT_PATH2;
	private String projectName;
	
	public ParseWorker(String rootPath, String projectName) {
		OUTPUT_PATH1 = rootPath + "RenamedMethods/";
		OUTPUT_PATH2 = rootPath + "MethodBodies/";
		this.projectName = projectName;
	}

	public static Props props(final String rootPath, final String projectName) {
		return Props.create(new Creator<ParseWorker>() {

			private static final long serialVersionUID = -2972414308929536455L;

			@Override
			public ParseWorker create() throws Exception {
				return new ParseWorker(rootPath, projectName);
			}
		});
	}

	@Override
	public void onReceive(Object message) throws Throwable {
		if (message instanceof MessageFiles) {
			MessageFiles messageFiles = (MessageFiles) message;
			List<File> revFiles = messageFiles.getRevFiles();
			int id = messageFiles.getId();
			
			StringBuilder builder = new StringBuilder();
			StringBuilder methodBodiesBuilder = new StringBuilder();
			int counter = 0;
			for (File revFile : revFiles) {
				String fileName = revFile.getName();
				if (!fileName.endsWith(".java")) continue;
				
				String parentPath = revFile.getParent() + "/";
				File prevFile = new File(parentPath.replace("/revFiles/", "/prevFiles/") + "prev_" + fileName);
				File diffentryFile = new File(parentPath.replace("/revFiles/", "/DiffEntries/") + fileName.replace(".java", ".txt"));
				
				String filePath = fileName.toLowerCase(Locale.ROOT);//revFile.getPath().toLowerCase(Locale.ROOT);
				if (filePath.contains("test") || filePath.contains("example") || filePath.contains("template") || filePath.contains("sample")) {
					revFile.delete();
					prevFile.delete();
					diffentryFile.delete();
					continue;
				}
				
				if (!revFile.exists() || !prevFile.exists() || !diffentryFile.exists()) {
					revFile.delete();
					prevFile.delete();
					diffentryFile.delete();
					continue;
				}
				String oldCommitId = readPreviousCommitId(diffentryFile);
				
				CodeChangeParser parser = new CodeChangeParser();
				final ExecutorService executor = Executors.newSingleThreadExecutor();
				// schedule the work
				final Future<?> future = executor.submit(new RunnableParser(prevFile, revFile, diffentryFile, parser));
				try {
					future.get(150L, TimeUnit.SECONDS);
					// project_name : old_commit_id : new_commit_id : file_path : old_method_name : new_method_name : old_line : new_line : return_type : arguments : tokens.
					List<String> renamedMethods = parser.getRenamedMethods();
					if (renamedMethods.isEmpty()) {
						revFile.delete();
						prevFile.delete();
						diffentryFile.delete();
					} else {
						methodBodiesBuilder.append(parser.getMethodBodies());
						for (String renamedMethod : renamedMethods) {
							builder.append(projectName).append(":");
							builder.append(oldCommitId).append(":");
							builder.append(renamedMethod).append("\n");
							counter ++;
							if (counter % 100 == 0) {
								FileHelper.outputToFile(OUTPUT_PATH1 + "RenamedMethods_" + id + ".txt", builder, true);
								builder.setLength(0);
								FileHelper.outputToFile(OUTPUT_PATH2 + "MethodBodies_" + id + ".txt", methodBodiesBuilder, true);
								methodBodiesBuilder.setLength(0);
							}
						}
					}
				} catch (TimeoutException e) {
					future.cancel(true);
					System.err.println("#Timeout: " + revFile.getName());
				} catch (InterruptedException e) {
					System.err.println("#TimeInterrupted: " + revFile.getName());
				} catch (ExecutionException e) {
					System.err.println("#TimeAborted: " + revFile.getPath());
					e.printStackTrace();
				} finally {
					executor.shutdownNow();
				}
			}
			FileHelper.outputToFile(OUTPUT_PATH1 + "RenamedMethods_" + id + ".txt", builder, true);
			builder.setLength(0);
			FileHelper.outputToFile(OUTPUT_PATH2 + "MethodBodies_" + id + ".txt", methodBodiesBuilder, true);
			methodBodiesBuilder.setLength(0);
			log.debug("Worker #" + id +" Finish of parsing " + counter + " renamed methods...");
			this.getSender().tell("END", getSelf());
		} else {
			unhandled(message);
		}
	}

	private String readPreviousCommitId(File diffentryFile) {
		String previousCommitId = "";
		String diffentry = FileHelper.readFile(diffentryFile);
		BufferedReader reader = new BufferedReader(new StringReader(diffentry));
		try {
			previousCommitId = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (previousCommitId.length() != 6) {
			System.err.println("WRONG COMMIT ID: " + diffentryFile.toString());
		}
		return previousCommitId;
	}

}