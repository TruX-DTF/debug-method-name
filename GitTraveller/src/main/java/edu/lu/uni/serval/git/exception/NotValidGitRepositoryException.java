package edu.lu.uni.serval.git.exception;

public class NotValidGitRepositoryException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1187015266656167209L;

	public NotValidGitRepositoryException() {
		
	}
	
	public NotValidGitRepositoryException(String message) {
		super(message);
	}
}
