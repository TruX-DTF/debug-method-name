package edu.lu.uni.serval;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import edu.lu.uni.serval.sricmn.akka.EvaluateActor;

/**
 * Sport and refactor inconsistent method names.
 * 
 * @author kui.liu
 *
 */
public class Main {

	public static void main(String[] args) {
		int nEpochs = 1;// 1, 10, 20.
		int topNum = 100;
		boolean considerReturnType = false;// true or false: method body with return types or not.
		int fileId = 2; // 1 or 2. 1: method names without return types. 2: method names with return types.
		String inputPath = Configuration.OUTPUT_PATH;
		int numberOfTestingWorkers = 50;
		int numberOfRenamedWorkers = 100;
		int similarityType = 0; // 0-15.
		boolean isSubToken = false; // true or false.
		boolean considerSynonyms = false;
		boolean needAllSynonyms = false;
		Main akkaEval = new Main();
		akkaEval.evaluate(nEpochs, topNum, considerReturnType, fileId, inputPath, numberOfTestingWorkers, numberOfRenamedWorkers, 
				similarityType, isSubToken, considerSynonyms, needAllSynonyms);
	}
	
	@SuppressWarnings("deprecation")
	public void evaluate(int nEpochs, int topNum, boolean considerReturnType, int fileId, String inputPath, int numberOfTestingWorkers, 
			int numberOfRenamedWorkers, int similarityType, boolean isSubToken, boolean considerSynonyms, boolean needAllSynonyms) {
		ActorSystem system = null;
		ActorRef parsingActor = null;
		String[] inputPaths = {inputPath + "/DL_Data/", inputPath + "/Detect_Data/"};
		
		try {
			system = ActorSystem.create("Evaluation-System");
			parsingActor = system.actorOf(EvaluateActor.props(numberOfTestingWorkers, numberOfRenamedWorkers, inputPaths, nEpochs, topNum, considerReturnType, 
					fileId, similarityType, isSubToken, considerSynonyms, needAllSynonyms), "evaluate-actor");
			parsingActor.tell("BEGIN", ActorRef.noSender());
		} catch (Exception e) {
			system.shutdown();
			e.printStackTrace();
		}
		
	}

}
