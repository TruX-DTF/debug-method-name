package edu.lu.uni.serval.deeplearner;

import java.io.File;
import java.io.FileNotFoundException;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.paragraphvectors.ParagraphVectors;
import org.deeplearning4j.models.word2vec.VocabWord;
import org.deeplearning4j.models.word2vec.wordstore.inmemory.AbstractCache;
import org.deeplearning4j.text.documentiterator.LabelsSource;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;

import edu.lu.uni.serval.utils.FileHelper;

public class SentenceEncoder {
	
	public ParagraphVectors vec;

	@SuppressWarnings("deprecation")
	public void encodeSentences(File inputFile, int minWordFrequency, int layerSize, int windowSize, String outputFileName) throws FileNotFoundException {
        SentenceIterator iter = new BasicLineIterator(inputFile);
        AbstractCache<VocabWord> cache = new AbstractCache<>();

        TokenizerFactory t = new DefaultTokenizerFactory();
        t.setTokenPreProcessor(new MyTokenPreprocessor()); // CommonPreprocessor

        /*
             if you don't have LabelAwareIterator handy, you can use synchronized labels generator
              it will be used to label each document/sequence/line with it's own label.
              But if you have LabelAwareIterator ready, you can can provide it, for your in-house labels
        */
        LabelsSource source = new LabelsSource("SEN_");

        ParagraphVectors vec = new ParagraphVectors.Builder()
                .minWordFrequency(minWordFrequency)
                .iterations(5)
                .epochs(1)
                .layerSize(layerSize)
                .learningRate(0.025)
                .labelsSource(source)
                .windowSize(windowSize)
                .iterate(iter)
                .trainWordVectors(false)
                .vocabCache(cache)
                .tokenizerFactory(t)
                .sampling(0)
                .build();

        vec.fit();
        this.vec = vec;

        FileHelper.makeDirectory(outputFileName);
        String modelName = outputFileName.substring(0, outputFileName.lastIndexOf(".")) + ".zip";
        WordVectorSerializer.writeWord2VecModel(vec, new File(modelName)); // output model to a file
        WordVectorSerializer.writeWordVectors(vec, outputFileName);
	}

}
