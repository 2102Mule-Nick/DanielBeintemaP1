package com.beintema.exception;

public class UserNameTaken extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5158848996947592839L;

	public UserNameTaken() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserNameTaken(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserNameTaken(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserNameTaken(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserNameTaken(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
