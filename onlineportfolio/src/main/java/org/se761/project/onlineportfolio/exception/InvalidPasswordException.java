package org.se761.project.onlineportfolio.exception;

public class InvalidPasswordException extends RuntimeException {

	/**
	 * Exception thrown when an invalid password is entered by the user
	 */
	private static final long serialVersionUID = -3497031809708618648L;
	
	public InvalidPasswordException(String message){
		super(message);
	}

}
