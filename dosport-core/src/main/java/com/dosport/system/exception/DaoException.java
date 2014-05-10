package com.dosport.system.exception;

/**
 * DAO层异常报告.
 * 
 * @author pwl
 * 
 */
public class DaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2609538663717723163L;

	public DaoException() {
		super();
	}

	public DaoException(String arg0) {
		super(arg0);
	}

	public DaoException(Throwable arg1) {
		super(arg1);
	}

	public DaoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
