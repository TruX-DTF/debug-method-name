package edu.lu.uni.serval.git.exception;

public class GitRepositoryNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8060210508833740142L;

	public GitRepositoryNotFoundException() {
		
	}
	
	public GitRepositoryNotFoundException(String message) {
		super(message);
	}
}
