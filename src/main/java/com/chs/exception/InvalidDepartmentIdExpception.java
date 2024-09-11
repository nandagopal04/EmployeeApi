package com.chs.exception;

public class InvalidDepartmentIdExpception extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1794312750242493740L;

	public InvalidDepartmentIdExpception() {
		// TODO Auto-generated constructor stub
	}

	public InvalidDepartmentIdExpception(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidDepartmentIdExpception(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidDepartmentIdExpception(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidDepartmentIdExpception(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
