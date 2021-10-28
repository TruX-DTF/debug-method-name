package edu.lu.uni.serval.dlMethods;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.nn.api.Model;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.inputs.InputType;
import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.lu.uni.Configuration;
import edu.lu.uni.serval.utils.FileHelper;
import lombok.NonNull;

/**
 * Feature learning.
 * 
 * @author kui.liu
 *
 */
public class MethodBodyCodeLearner {
	
	private static Logger log = LoggerFactory.getLogger(CNNFeatureExtractor.class);

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		int nEpochs = 1;//Integer.valueOf(args[0]);//1, 10, 20
		String rootPath = Configuration.DL_DATA_PATH;
		String inputPath = Configuration.DL_INPUT_DATA_PATH;
		String outputPath = rootPath + "DLoutput_" + nEpochs + "/";
		File modelFile = new File(outputPath + "CNNoutput.zip");
		
		int batchSize = Configuration.BATCH_SIZE;
		
		File[] files = new File(inputPath).listFiles();
		File trainingDataFile = null;
		File testingDataFile = null;
		int maxSize = 0;
		
		for (File file : files) {
			String fileName = file.getName();
			if (fileName.startsWith("TrainingData_")) {
				trainingDataFile = file;
				maxSize = Integer.parseInt(fileName.substring("TrainingData_Tokens_MaxSize=".length(), fileName.lastIndexOf(".csv")));
			} else if (fileName.startsWith("RenamedData_")) {
				testingDataFile = file;
			}
		}
		
		if (modelFile.exists()) {
			CNNFeatureExtractor learner = new MethodBodyCodeLearner().new CNNFeatureExtractor(batchSize);
			learner.setOutputPath(outputPath);
			learner.setModelFile(modelFile);
			learner.setTestingData(testingDataFile);
			learner.extracteFeaturesWithCNNByLoadingModel();
		} else {
			FileHelper.deleteDirectory(outputPath);
			
			int sizeOfTokensVector = maxSize;
			int sizeOfEmbeddedVector = 300;
			int sizeOfFeatureVector = Configuration.SIZE_OF_FEATURE_VECTOR;
			
			CNNFeatureExtractor learner = new MethodBodyCodeLearner().new CNNFeatureExtractor(trainingDataFile, sizeOfTokensVector, sizeOfEmbeddedVector, batchSize, sizeOfFeatureVector);
			learner.setNumberOfEpochs(nEpochs);
			learner.setSeed(123);
			learner.setNumOfOutOfLayer1(20);
			learner.setNumOfOutOfLayer2(50);
			learner.setOutputPath(outputPath);
			
			learner.extracteFeaturesWithCNN();
			
			learner.setModelFile(modelFile);
			learner.setTestingData(testingDataFile);
			learner.extracteFeaturesWithCNNByLoadingModel();
		}
	}
	
	class CNNFeatureExtractor {
		
		private File inputFile;
	    int sizeOfVector = 0;     // The size of a tokens' vector.
	    int sizeOfTokenVec = 0;   // The size of an embedded token vector.
		private int batchSize;
		private int sizeOfFeatureVector; // The size of feature vector, which is the extracted features of each instance.

		private final int nChannels = 1; // Number of input channels.
		private final int iterations = 1;// Number of training iterations. 
		                                 // Multiple iterations are generally only used when doing full-batch training on very small data sets.
		private int nEpochs = 1;         // Number of training epochs
		private int seed = 123;
		
		private int numOfOutOfLayer1 = 20;
		private int numOfOutOfLayer2 = 50;
		
		private int outputNum; // The number of possible outcomes
		
		private String outputPath;
		private File testingData;
		private File modelFile;
		
		public CNNFeatureExtractor(int batchSize) {
			this.batchSize = batchSize;
		}
		
		public CNNFeatureExtractor(File inputFile, int sizeOfVector, int sizeOfTokenVec, int batchSize, int sizeOfFeatureVector) {
			this.inputFile = inputFile;
			this.sizeOfVector = sizeOfVector;
			this.sizeOfTokenVec = sizeOfTokenVec;
			this.batchSize = batchSize;
			this.sizeOfFeatureVector = sizeOfFeatureVector;
			/*
			 * If the deep learning is unsupervised learning, the number of outcomes is the size of input vector.
			 * If the deep learning is supervised learning, the number of outcomes is the number of classes.
			 */
			outputNum = sizeOfVector * sizeOfTokenVec;
		}
		
		public void setNumberOfEpochs(int nEpochs) {
			this.nEpochs = nEpochs;
		}
		
		public void setSeed(int seed) {
			this.seed = seed;
		}
		
		public void setNumOfOutOfLayer1(int numOfOutOfLayer1) {
			this.numOfOutOfLayer1 = numOfOutOfLayer1;
		}

		public void setNumOfOutOfLayer2(int numOfOutOfLayer2) {
			this.numOfOutOfLayer2 = numOfOutOfLayer2;
		}

		public void setOutputPath(String outputPath) {
			this.outputPath = outputPath;
		}

		public void setTestingData(File testingData) {
			this.testingData = testingData;
		}

		public void setModelFile(File modelFile) {
			this.modelFile = modelFile;
		}

		public void extracteFeaturesWithCNN() throws FileNotFoundException, IOException, InterruptedException {
	        log.info("Load data....");
	        RecordReader trainingDataReader = new CSVRecordReader();
	        trainingDataReader.initialize(new FileSplit(inputFile));
	        DataSetIterator trainingDataIter = new RecordReaderDataSetIterator(trainingDataReader, batchSize);
	        
	        /*
	         *  Construct the neural network
	         */
	        log.info("Build model....");
	        MultiLayerConfiguration.Builder builder = new NeuralNetConfiguration.Builder()
	                .seed(seed)
	                .iterations(iterations) // Training iterations as above
	                .regularization(true).l2(0.0005)
	                /**
	                 * Some simple advice is to start by trying three different learning rates – 1e-1, 1e-3, and 1e-6 
	                 */
	                .learningRate(.01)
	                /**
	                 * XAVIER weight initialization is usually a good choice for this. 
	                 * For networks with rectified linear (relu) or leaky relu activations, 
	                 * RELU weight initialization is a sensible choice.
	                 */
	                .weightInit(WeightInit.XAVIER)
	                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
	                .updater(Updater.NESTEROVS).momentum(0.9)
	                .list()
	                .layer(0, new ConvolutionLayer.Builder(2, sizeOfTokenVec)
	                        //nIn and nOut specify depth. nIn here is the nChannels and nOut is the number of filters to be applied
	                        .nIn(nChannels)
	                        .stride(1, sizeOfTokenVec)
	                        .nOut(numOfOutOfLayer1)
	                        .activation(Activation.RELU)
	                        .build())
	                .layer(1, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
	                        .kernelSize(2,1)
	                        .stride(2,1)
	                        .build())
	                .layer(2, new ConvolutionLayer.Builder(2, 1)
	                        .stride(1, 1)
	                        .nOut(numOfOutOfLayer2)
	                        .activation(Activation.RELU)
	                        .build())
	                .layer(3, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
	                        .kernelSize(2,1)
	                        .stride(2,1)
	                        .build())
	                .layer(4, new DenseLayer.Builder().activation(Activation.RELU)
	                        .nOut(sizeOfFeatureVector).build())
	                .layer(5, new OutputLayer.Builder(LossFunctions.LossFunction.MEAN_ABSOLUTE_ERROR)
	                        .nOut(outputNum)
	                        .activation(Activation.SOFTMAX)
	                        .build())
	                .setInputType(InputType.convolutionalFlat(sizeOfVector,sizeOfTokenVec,1))
	                .backprop(true).pretrain(false);

	        MultiLayerConfiguration conf = builder.build();
	        MultiLayerNetwork model = new MultiLayerNetwork(conf);
	        model.init();

	        log.info("Train model....");
	        model.setListeners(new ScoreIterationListener(1));
	        
	        String fileName = "CNNoutput.csv";
	        StringBuilder features = new StringBuilder();
	        for( int i=0; i<nEpochs; i++ ) {
	        	String outputFileName = outputPath + (i + 1) + "_" + fileName;
	        	int batchers = 0;
	        	while (trainingDataIter.hasNext()) {
	        		DataSet next = trainingDataIter.next();
	        		// During the process of fitting, each training instance is used to calibrate the parameters of neural network.
	                model.fit(next);
	                
	                INDArray input = model.getOutputLayer().input();
	            	features.append(input.toString().replace("[[", "").replaceAll("\\],", "")
	            			.replaceAll(" \\[", "").replace("]]", "") + "\n");
	            	
	            	batchers ++;
	            	if ((batchers * batchSize) >= 100000) {
	            		FileHelper.outputToFile(outputFileName, features, true);
	            		features.setLength(0);
	            	}
	            	log.info("batch: " + batchers);
	        	}
	        	FileHelper.outputToFile(outputFileName, features, true);
	        	features.setLength(0);
	        	log.info("*** Completed epoch {} ***", i);
	        	
	    		trainingDataIter.reset();
	        }
	        
	        //Save the model
	        String modelFile = outputPath + fileName.substring(0, fileName.lastIndexOf(".")) + ".zip";
//	        File locationToSave = new File(modelFile);  //Where to save the network. Note: the file is in .zip format - can be opened externally
	        boolean saveUpdater = true;    //Updater: i.e., the state for Momentum, RMSProp, Adagrad etc. Save this if you want to train your network more in the future
//	        ModelSerializer.writeModel(model, locationToSave, saveUpdater);
	        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(modelFile))) {
	        	writeModel(model, stream, saveUpdater);
	        }
	        
	        log.info("****************Extracting features finished****************");
		}
		
		public void extracteFeaturesWithCNNByLoadingModel() throws IOException, InterruptedException {
			// testingData, modelFile, batchSize
			log.info("Load testing data....");
			RecordReader testingDataReader = new CSVRecordReader();
	        testingDataReader.initialize(new FileSplit(testingData));
	        DataSetIterator testingDataIter = new RecordReaderDataSetIterator(testingDataReader, batchSize);
	        
	        //Load the model
	        log.info("Load a model....");
	        MultiLayerNetwork restored = ModelSerializer.restoreMultiLayerNetwork(modelFile);
	        MultiLayerNetwork model = restored.clone();

	        StringBuilder featuresOfTestingData = new StringBuilder();
			String testingFileName = testingData.getName();
	        String featureFileName = outputPath + "TestingFeatures_" + testingFileName;
	        
	        while (testingDataIter.hasNext()) {
				DataSet ds = testingDataIter.next();
				List<INDArray> outputs = model.feedForward(ds.getFeatureMatrix(), false);
				INDArray testingFeatures = outputs.get(outputs.size() - 2);

				featuresOfTestingData.append(testingFeatures.toString().replace("[[", "").replaceAll("\\],", "")
						.replaceAll(" \\[", "").replace("]]", "") + "\n");
			}

			FileHelper.outputToFile(featureFileName, featuresOfTestingData, false);

			log.info("****************Deep learning finished********************");
		}
		
		public void writeModel(@NonNull Model model, @NonNull OutputStream stream, boolean saveUpdater)
                throws IOException {
		    ZipOutputStream zipfile = new ZipOutputStream(stream);
		
		    // Save configuration as JSON
		    String json = "";
		    if (model instanceof MultiLayerNetwork) {
		        json = ((MultiLayerNetwork) model).getLayerWiseConfigurations().toJson();
		    } else if (model instanceof ComputationGraph) {
		        json = ((ComputationGraph) model).getConfiguration().toJson();
		    }
		    ZipEntry config = new ZipEntry("configuration.json");
		    zipfile.putNextEntry(config);
		    zipfile.write(json.getBytes());
		
		    // Save parameters as binary
		    ZipEntry coefficients = new ZipEntry("coefficients.bin");
		    zipfile.putNextEntry(coefficients);
		    DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(zipfile));
		    try {
		        Nd4j.write(model.params(), dos);
		    } finally {
		        dos.flush();
		        if(!saveUpdater) dos.close();
		    }
		
		    if (saveUpdater) {
		        INDArray updaterState = null;
		        if (model instanceof MultiLayerNetwork) {
		            updaterState = ((MultiLayerNetwork) model).getUpdater().getStateViewArray();
		        } else if (model instanceof ComputationGraph) {
		            updaterState = ((ComputationGraph) model).getUpdater().getStateViewArray();
		        }
		
		        if (updaterState != null && updaterState.length() > 0) {
		            ZipEntry updater = new ZipEntry("updaterState.bin");
		            zipfile.putNextEntry(updater);
		
		            try {
		                Nd4j.write(updaterState, dos);
		            } finally {
		                dos.flush();
		                dos.close();
		            }
		        }
		    }
		
		    zipfile.flush();
		    zipfile.close();
		}
	}
}
