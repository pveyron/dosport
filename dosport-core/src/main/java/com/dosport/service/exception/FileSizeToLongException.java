package com.dosport.service.exception;

/**
 * 文件太大异常.
 * 
 * @author pwl
 * 
 */
public class FileSizeToLongException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8814880290337097609L;

	public FileSizeToLongException() {
		super();
	}

	public FileSizeToLongException(String arg0) {
		super(arg0);
	}

	public FileSizeToLongException(Throwable arg1) {
		super(arg1);
	}

	public FileSizeToLongException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
