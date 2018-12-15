package edu.lu.uni.serval.sricmn.liveStudy;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class AkkaEvaluation {

	public static void main(String[] args) {
		if (args.length != 9) {
			System.out.println("Arguments: <nEpochs>, <threshold>, <true/false>, <1/2>, <dataPath>, <numOfWorkers1>, <numOfWorkers2>");
			System.exit(0);
		}
		
		int nEpochs = Integer.valueOf(args[0]);//1, 10, 20
		int topNum = Integer.valueOf(args[1]); //100
		boolean considerReturnType = Boolean.valueOf(args[2]);// true or false.
		int fileId = Integer.valueOf(args[3]); // 1 or 2.
		String inputPath = args[4];
		int numberOfBodyFeatureWorkers = Integer.valueOf(args[5]);// 50.
		int numberOfNameFeatureWorkers = Integer.valueOf(args[6]);// 100.
		int startIndex = Integer.valueOf(args[7]);
		int endIndex = Integer.valueOf(args[8]);
		AkkaEvaluation akkaEval = new AkkaEvaluation();
		akkaEval.evaluate(nEpochs, topNum, considerReturnType, fileId, inputPath, numberOfBodyFeatureWorkers, numberOfNameFeatureWorkers, startIndex, endIndex);
	}
	
	@SuppressWarnings("deprecation")
	public void evaluate(int nEpochs, int topNum, boolean considerReturnType, int fileId, String inputPath, int numberOfBodyFeatureWorkers, int numberOfNameFeatureWorkers, int startIndex, int endIndex) {
		ActorSystem system = null;
		ActorRef parsingActor = null;
		try {
			system = ActorSystem.create("Evaluation-System");
			parsingActor = system.actorOf(EvaluateActor.props(numberOfBodyFeatureWorkers, numberOfNameFeatureWorkers, inputPath, nEpochs, topNum, considerReturnType, fileId, startIndex, endIndex), "evaluate-actor");
			parsingActor.tell("BEGIN", ActorRef.noSender());
		} catch (Exception e) {
			system.shutdown();
			e.printStackTrace();
		}
		
	}

}
