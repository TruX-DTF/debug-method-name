package edu.lu.uni.serval.git.travel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BugRelatedWords {
	
	public enum CommitMessageKeyWords {
		bug,
		error,
		fault,
		fix,
		patch,
		repair
	}

	public static final List<String> BUG_RELATED_KEY_WORDS = new ArrayList<>();
	
	static {
		List<?> tokens = Arrays.asList(CommitMessageKeyWords.values());
		for (Object obj : tokens) {
			BUG_RELATED_KEY_WORDS.add(obj.toString());
		}
	}
	
	public static void main(String[] args) {
		System.out.println(BUG_RELATED_KEY_WORDS);
	}
}
