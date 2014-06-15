package com.dosport.struts2.attachment;

import java.io.IOException;
import java.util.Date;

import com.dosport.domain.attachment.FileUploadForm;
import com.dosport.service.attachment.AbstractAttachmentComponent;
import com.dosport.service.exception.FileEmptyException;
import com.dosport.service.exception.FileSizeToLongException;
import com.dosport.service.exception.FileTypeDenyException;

/**
 * 上传文件组件.
 * 
 * @author pwl
 * 
 */
public class FileUploadComponent extends AbstractAttachmentComponent {

	/**
	 * 上传文件.
	 * 
	 * @param fileData
	 * @param fileType
	 * @param fileName
	 */
	public FileUploadForm uploadFile(FileUploadForm form) throws IOException, FileSizeToLongException,
			FileEmptyException, FileTypeDenyException {

		Date uploadDate = new Date();
		String filePath = this.generateFilePath(uploadDate);
		String fileSaveName = this.writeFile(this.fileRootPath, filePath, form.getFileData());
		form.setUploadDate(uploadDate);
		form.setFileSaveName(fileSaveName);
		return form;
	}
}
