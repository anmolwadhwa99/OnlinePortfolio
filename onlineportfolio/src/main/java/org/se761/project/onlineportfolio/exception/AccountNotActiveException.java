package org.se761.project.onlineportfolio.exception;

public class AccountNotActiveException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1965269315997273600L;
	
	public AccountNotActiveException(String message){
		super(message);
	}
	

}
