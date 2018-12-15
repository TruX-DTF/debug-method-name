package edu.lu.uni.serval.utils;

import java.util.ArrayList;
import java.util.List;

import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;

/**
 * Find the synonyms, antonyms and hypernyms of the given word.
 * 
 * Example: https://github.com/jaytaylor/jaws.
 * 
 * @author kui.liu
 *
 */
public class SynonymsFinder {
	
	private WordNetDatabase database = WordNetDatabase.getFileInstance();
	private String wordTag = null;
	private SynsetType synsetType = null;
	private boolean needAllSynonyms = false;
	
	private List<String> synonyms = new ArrayList<>();

	public void setWordTag(String wordTag) {
		this.wordTag = wordTag;
	}
	
	public void setPropertyOfWordNet(String wordnetPath) {
		System.setProperty("wordnet.database.dir", wordnetPath);
	}
	
	public void setSynsetType(SynsetType synsetType) {
		this.synsetType = synsetType;
	}
	
	public void setNeedAllSynonyms(boolean needAllSynonyms) {
		this.needAllSynonyms = needAllSynonyms;
	}
	
	public List<String> getSynonyms() {
		return this.synonyms;
	}
	
	public void retrieveSynonyms(String word) {
		
		matchSynsetType();
		
		if (synsetType != null) {
			retrieveSynonyms(word, synsetType);
		} else {
			SynsetType[] synsetTypes = SynsetType.ALL_TYPES;
			for (SynsetType synsetType : synsetTypes) {
				retrieveSynonyms(word, synsetType);
			}
		}
	}

	private void matchSynsetType() {
		if (wordTag == null) this.synsetType = null;
		else if( wordTag.trim().startsWith("JJ")) this.synsetType = SynsetType.ADJECTIVE;
		else if( wordTag.trim().startsWith("NN")) this.synsetType = SynsetType.NOUN;
		else if( wordTag.trim().startsWith("RB")) this.synsetType = SynsetType.ADVERB;
		else if( wordTag.trim().startsWith("VB")) this.synsetType = SynsetType.VERB;
    }
	
	private void retrieveSynonyms(String word, SynsetType synsetType) {
		Synset[] synsets = database.getSynsets(word, synsetType);
		if (synsets != null && synsets.length > 0) {
			if (this.needAllSynonyms) {
//				int counter = 0;
				for (Synset synset : synsets) {
					addToSynonymsList(synset, word);
//					if (++ counter >= 5) break;
				}
			} else {
				addToSynonymsList(synsets[0], word);
			}
			
			/*
			WordSense[] wordsenses = synset.getAntonyms(word);
			for (WordSense wordsense : wordsenses) {
		        antonyms.add(wordsense.getWordForm());
		    }
			
			if (synsetType != null) {
				switch (synsetType.getCode()) {
				case 1: // noun
					NounSynset nounSynset; 
					NounSynset[] hyponyms; 
					nounSynset = (NounSynset) synset; 
				    hyponyms = nounSynset.getHyponyms(); 
				    for (NounSynset hypo : hyponyms) {
						hypernyms.addAll(Arrays.asList(hypo.getWordForms()));
					}
					break;
				case 2: // verb
					VerbSynset verbSynset; 
					VerbSynset[] hyponyms1; 
					verbSynset = (VerbSynset) synset; 
				    hyponyms1 = verbSynset.getHypernyms(); 
				    for (VerbSynset hypo : hyponyms1) {
						hypernyms.addAll(Arrays.asList(hypo.getWordForms()));
					}
					break;
				default:
					break;
				}
			}
			*/
		}
	}

	private void addToSynonymsList(Synset synset, String word) {
		String[] wordForms = synset.getWordForms();
		for (String wordForm : wordForms) {
			int index = wordForm.indexOf(" ");
			if (index > 0) wordForm = wordForm.substring(0, index);
			if (!word.equals(wordForm) 
					&& !synonyms.contains(wordForm)
//					&& !wordForm.contains(" ") // Ignore phrases.
					) {
				synonyms.add(wordForm);
			}
		}
	}
}
