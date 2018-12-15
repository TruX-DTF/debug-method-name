package edu.lu.uni.serval;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import edu.lu.uni.serval.sricmn.akka.EvaluateActor;

public class Main {

	public static void main(String[] args) {
		if (args.length != 11) {
				System.out.println("Arguments: <nEpochs>, <threshold>, <true/false>, <1/2>, <dataPath>, <numOfWorkers1>, <numOfWorkers2>, <similarityType>, <isSubToken>, <considerSynonyms>, <needAllSynonyms>");
				System.exit(0);
		}
		int nEpochs = Integer.valueOf(args[0]);// 1, 10, 20.
		int topNum = Integer.valueOf(args[1]); // 100.
		boolean considerReturnType = Boolean.valueOf(args[2]);// true or false: method body with return types or not.
		int fileId = Integer.valueOf(args[3]); // 1 or 2. 1: method names without return types. 2: method names with return types.
		String inputPath = args[4];
		int numberOfTestingWorkers = Integer.valueOf(args[5]);// 50.
		int numberOfRenamedWorkers = Integer.valueOf(args[6]);// 100.
		int similarityType = Integer.valueOf(args[7]); // 0-15.
		boolean isSubToken = Boolean.valueOf(args[8]);// true or false.
		boolean considerSynonyms = Boolean.valueOf(args[9]);// true or false.
		boolean needAllSynonyms = Boolean.valueOf(args[10]);// true or false.
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
