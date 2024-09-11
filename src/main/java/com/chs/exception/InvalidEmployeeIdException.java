package com.chs.exception;

public class InvalidEmployeeIdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3155859495688795461L;

	public InvalidEmployeeIdException() {
		// TODO Auto-generated constructor stub
	}

	public InvalidEmployeeIdException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidEmployeeIdException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidEmployeeIdException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidEmployeeIdException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
