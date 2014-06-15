package com.dosport.service.exception;

/**
 * 文件类型不允许上传异常.
 * 
 * @author pwl
 * 
 */
public class FileTypeDenyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2075583408660771876L;

	public FileTypeDenyException() {
		super();
	}

	public FileTypeDenyException(String arg0) {
		super(arg0);
	}

	public FileTypeDenyException(Throwable arg1) {
		super(arg1);
	}

	public FileTypeDenyException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
