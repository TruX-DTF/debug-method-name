package edu.lu.uni.serval.sricmn.akka;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import edu.lu.uni.serval.sricmn.info.MethodInfo;
import edu.lu.uni.serval.utils.NamesStringSimilarity;
import edu.lu.uni.serval.utils.Similarities;
import edu.lu.uni.serval.utils.similarity.StringMetrics;

public class EvaluateWorker extends UntypedActor {
	
	private static Logger log = LoggerFactory.getLogger(EvaluateWorker.class);
	
	private int topNum;
	private boolean considerReturnType;
	
	public EvaluateWorker(int topNum, boolean considerReturnType) {
		this.topNum = topNum;
		this.considerReturnType = considerReturnType;
	}

	public static Props props(final int topNum, final boolean considerReturnType) {
		return Props.create(new Creator<EvaluateWorker>() {

			private static final long serialVersionUID = 3368635349264029140L;

			@Override
			public EvaluateWorker create() throws Exception {
				return new EvaluateWorker(topNum, considerReturnType);
			}
			
		});
	}
	
	@Override
	public void onReceive(Object msg) throws Throwable {
		if (msg instanceof WorkerMessage) {
			WorkerMessage workerMsg = (WorkerMessage) msg;
			int workerId = workerMsg.getWorkerId();
			List<Double[]> testingFeatures = workerMsg.getSubTestingFeatures(); // Body feature vectors.
			List<Double[]> renamedFeatures = workerMsg.getSubRenamedFeatures(); // Name feature vectors.
			int fromIndex = workerMsg.getFromIndex();
			
			// <Index of testing instances, List<>>, List of indexes of top 100 most similar methods.
			WorkerReturnMessage wrMsg = new WorkerReturnMessage();
			Similarities sm = new Similarities();
			sm.setTopNum(topNum);
			
			if (testingFeatures == null && renamedFeatures == null) {
				// Measure similarities among names with string metrics.
				NamesStringSimilarity nStrSm = new NamesStringSimilarity();
				StringMetrics stringMetric = nStrSm.readStringMetric(workerMsg.getSimilarityType());
//				boolean isDescending = nStrSm.isDescending;
				
				Map<String, List<MethodInfo>> trainingMethodInfoMap = workerMsg.getTrainingFeatures();
				List<MethodInfo> testingMethodInfoList = workerMsg.getSubTestingMethodInfo();
				Map<Integer, Map<Integer, Double>> topSimilarInstancesMap = sm.calculateSimilarities(testingMethodInfoList, trainingMethodInfoMap,
						fromIndex, false, stringMetric, workerMsg.isSubToken());
				wrMsg.setType("SimilarNames");
				wrMsg.setTopPredictedTokens(topSimilarInstancesMap);
			} else {
				Map<String, List<MethodInfo>> trainingMethodInfo = workerMsg.getTrainingFeatures();
				if (testingFeatures != null) {
					// Measure similarities among method bodies with learned features.
					Map<Integer, Map<Integer, Double>> topSimilarInstancesMap = sm.calculateSimilarities(testingFeatures, 
							trainingMethodInfo, workerMsg.getSubTestingMethodInfo(), fromIndex, considerReturnType);
					wrMsg.setType("SimilarBodies");
					wrMsg.setTopPredictedTokens(topSimilarInstancesMap);
				} else {
					// Measure similarities among method names with learned features.
					List<Double[]> trainingMethodNameFeatures = workerMsg.getTrainingMethodNameFeatures();
					List<MethodInfo> testingMethodInfoList = workerMsg.getSubTestingMethodInfo();
					Map<Integer, Map<Integer, Double>> topSimilarInstancesMap = sm.calculateSimilarities(renamedFeatures, 
							trainingMethodNameFeatures, fromIndex, testingMethodInfoList, trainingMethodInfo);
					wrMsg.setType("SimilarNames");
					wrMsg.setTopPredictedTokens(topSimilarInstancesMap);
				}
			}
			
			log.info("Worker #" + workerId +" Finish of its work...");
			
			this.getSender().tell(wrMsg, getSelf());
		} else {
			unhandled(msg);
		}
	}

}
