package com.dosport.system.exception;

/**
 * 业务层操作异常.
 * 
 * @author pwl
 * 
 */
public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5935980051201926719L;

	public ServiceException() {
		super();
	}

	public ServiceException(String arg0) {
		super(arg0);
	}

	public ServiceException(Throwable arg1) {
		super(arg1);
	}

	public ServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
