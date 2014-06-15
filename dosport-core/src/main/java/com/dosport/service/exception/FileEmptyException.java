package com.dosport.service.exception;

/**
 * 空文件异常.
 * 
 * @author pwl
 * 
 */
public class FileEmptyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1127720425583965160L;

	public FileEmptyException() {
		super();
	}

	public FileEmptyException(String arg0) {
		super(arg0);
	}

	public FileEmptyException(Throwable arg1) {
		super(arg1);
	}

	public FileEmptyException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
