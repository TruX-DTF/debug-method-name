package edu.lu.uni.serval.deeplearner;

import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;

public class MyTokenPreprocessor implements TokenPreProcess {

	@Override
	public String preProcess(String token) {
		return token;
	}

}
