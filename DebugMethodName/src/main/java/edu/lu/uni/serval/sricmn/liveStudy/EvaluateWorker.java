package edu.lu.uni.serval.sricmn.liveStudy;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import edu.lu.uni.serval.sricmn.akka.WorkerMessage;
import edu.lu.uni.serval.sricmn.akka.WorkerReturnMessage;
import edu.lu.uni.serval.sricmn.info.MethodInfo;
import edu.lu.uni.serval.utils.Similarities;

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
			List<Double[]> testingBodyFeatures = workerMsg.getSubTestingFeatures();
			List<Double[]> testingNameFeatures = workerMsg.getSubRenamedFeatures();
			int fromIndex = workerMsg.getFromIndex();
			
			// <Index of testing instances, List<>>, List of indexes of top 100 most similar methods.
			WorkerReturnMessage wrMsg = new WorkerReturnMessage();
			Similarities sm = new Similarities();
			sm.setTopNum(topNum);

			if (testingBodyFeatures != null) {
				Map<Integer, Map<Integer, Double>> topSimilarInstancesMap = sm.calculateSimilarities(testingBodyFeatures, 
						workerMsg.getTrainingFeatures(), workerMsg.getSubTestingMethodInfo(), fromIndex, considerReturnType);
				wrMsg.setType("BodyFeatures");
				wrMsg.setTopPredictedTokens(topSimilarInstancesMap);
			} else {
				Map<String, List<MethodInfo>> trainingMethodInfo = workerMsg.getTrainingFeatures();
				List<MethodInfo> testingMethodInfoList = workerMsg.getSubTestingMethodInfo();
				List<Double[]> trainingMethodNameFeatures = workerMsg.getTrainingMethodNameFeatures();
				Map<Integer, Map<Integer, Double>> topSimilarInstancesMap = sm.calculateSimilarities(testingNameFeatures, 
						trainingMethodNameFeatures, fromIndex, testingMethodInfoList, trainingMethodInfo);
				wrMsg.setType("NameFeatures");
				wrMsg.setTopPredictedTokens(topSimilarInstancesMap);
			}
			
			log.info("Worker #" + workerId +" Finish of its work...");
			
			this.getSender().tell(wrMsg, getSelf());
		} else {
			unhandled(msg);
		}
	}

}
