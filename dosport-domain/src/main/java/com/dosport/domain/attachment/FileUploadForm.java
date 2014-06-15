package com.dosport.domain.attachment;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

/**
 * 文件上传Form.
 * 
 * @author pwl
 * 
 */
public class FileUploadForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9212938153205797411L;

	private File fileData;
	private String fileDataFileName;
	private String fileDataContentType;
	private Date uploadDate;
	private String fileSaveName;

	public File getFileData() {
		return fileData;
	}

	public void setFileData(File fileData) {
		this.fileData = fileData;
	}

	public String getFileDataFileName() {
		return fileDataFileName;
	}

	public void setFileDataFileName(String fileDataFileName) {
		this.fileDataFileName = fileDataFileName;
	}

	public String getFileDataContentType() {
		return fileDataContentType;
	}

	public void setFileDataContentType(String fileDataContentType) {
		this.fileDataContentType = fileDataContentType;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getFileSaveName() {
		return fileSaveName;
	}

	public void setFileSaveName(String fileSaveName) {
		this.fileSaveName = fileSaveName;
	}

}
