package edu.lu.uni.serval.deeplearner;

import java.io.File;
import java.io.IOException;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.lu.uni.serval.utils.FileHelper;

public class Word2VecEncoder {
	private static Logger log = LoggerFactory.getLogger(Word2VecEncoder.class);
	
	private int windowSize = 4;
	
	public void setWindowSize(int windowSize) {
		this.windowSize = windowSize;
	}

	@SuppressWarnings("deprecation")
	public void embedTokens(File inputFile, int minWordFrequency, int layerSize, String inputFilePath, String outputFilePath) throws IOException {
		String fileName = inputFile.getPath();

        log.info("Load & Vectorize Sentences....");
        // Strip white space before and after for each line
        SentenceIterator iter = new BasicLineIterator(inputFile);
        // Split on white spaces in the line to get words
        TokenizerFactory t = new DefaultTokenizerFactory();

        /*
            CommonPreprocessor will apply the following regex to each token: [\d\.:,"'\(\)\[\]|/?!;]+
            So, effectively all numbers, punctuation symbols and some special symbols are stripped off.
            Additionally it forces lower case for all tokens.
         */
        t.setTokenPreProcessor(new MyTokenPreprocessor());

        log.info("****************Building model****************");
        Word2Vec vec = new Word2Vec.Builder()
        		.epochs(1)
//        		.batchSize(100)
//        		.useAdaGrad(reallyUse)
                .iterations(1)
                .learningRate(.01)
                .seed(50)
                .windowSize(windowSize)
                .minWordFrequency(minWordFrequency)
                .layerSize(layerSize)
                .iterate(iter)
                .tokenizerFactory(t)
                .build();

        log.info("****************Fitting Word2Vec model****************");
        vec.fit();

        log.info("****************Writing word vectors to text file****************");
        // Write word vectors to file
        fileName = fileName.replace(inputFilePath, outputFilePath + minWordFrequency + "/");
        FileHelper.makeDirectory(fileName);
//        WordVectorSerializer.writeWord2VecModel(vec, new File(fileName)); // output model to a file
        WordVectorSerializer.writeWordVectors(vec, fileName);
        log.info("****************Finish off embedding****************\n");
        // Evaluation
//        try {
//			log.info("Evaluation: Closest Words");
//			StringBuilder sb = new StringBuilder();
//			String[] tokens = {"IfStatement", "if", "boolean", "true", "false", "ReturnStatement", "return",
//					"WhileStatement", "while", "ForStatement", "for", "toString", "instanceof", "continue", "ContinueStatement",
//					"break", "BreakStatement", "do", "this", "throw", "else", "super", "BooleanLiteral", "NumberLiteral", "NullLiteral",
//					"null", "StringLiteral"};
//			for (String token : tokens) {
//				sb.append(token + "\n");
//				System.out.println(token);
//				Collection<String> lst = vec.wordsNearest(token, 100); // continue, break,
//				System.out.println("10 Words closest to 'true': " + lst);
//				sb.append(lst + "\n");
//				
////				similarTokens.add(lst);
//				double cosinSim = 0;
//				for (String str : lst) {
//					cosinSim = vec.similarity(token, str);
//					System.out.print(cosinSim + ", ");
//					sb.append(cosinSim + ",");
//				}
//				sb.append("\n");
//				System.out.println();
//			}
//			null,
//			FileHelper.outputToFile("OUTPUT/adjusting/parameters.txt", sb, false);
//			Collection<String> lst = vec.wordsNearest("null", 10); // continue, break, true, false, ! ==
//			System.out.println("10 Words closest to 'true': " + lst);
//			similarTokens.add(lst);
//			for (int i = 0; i < tokens1.length; i ++) {
//				cosinSimilarityList.add(vec.similarity(tokens1[i], tokens2[i]));
//			}
//		} catch (java.lang.NullPointerException e) {
//			e.printStackTrace();
//		}
	}
	
	@SuppressWarnings("deprecation")
	public void embedTokens(File inputFile, int minWordFrequency, int layerSize, String outputFileName) throws IOException {
		log.info("Load & Vectorize Sentences....");
        // Strip white space before and after for each line
        SentenceIterator iter = new BasicLineIterator(inputFile);
        // Split on white spaces in the line to get words
        TokenizerFactory t = new DefaultTokenizerFactory();

        /*
            CommonPreprocessor will apply the following regex to each token: [\d\.:,"'\(\)\[\]|/?!;]+
            So, effectively all numbers, punctuation symbols and some special symbols are stripped off.
            Additionally it forces lower case for all tokens.
         */
        t.setTokenPreProcessor(new MyTokenPreprocessor());

        log.info("****************Building model****************");
        Word2Vec vec = new Word2Vec.Builder()
        		.epochs(1)
//        		.batchSize(100)
//        		.useAdaGrad(reallyUse)
                .iterations(1)
                .learningRate(.01)
                .seed(50)
                .windowSize(windowSize)
                .minWordFrequency(minWordFrequency)
                .layerSize(layerSize)
                .iterate(iter)
                .tokenizerFactory(t)
                .build();

        log.info("****************Fitting Word2Vec model****************");
        vec.fit();

        log.info("****************Writing word vectors to text file****************");
        // Write word vectors to file
        FileHelper.makeDirectory(outputFileName);
//        WordVectorSerializer.writeWord2VecModel(vec, new File(fileName)); // output model to a file
        WordVectorSerializer.writeWordVectors(vec, outputFileName);
        log.info("****************Finish off embedding****************\n");
        // Evaluation
	}
}
