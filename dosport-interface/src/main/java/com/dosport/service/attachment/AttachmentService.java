package com.dosport.service.attachment;

import java.io.Serializable;

import com.dosport.domain.attachment.Attachment;
import com.dosport.domain.attachment.FileUploadForm;
import com.dosport.service.exception.ServiceException;

/**
 * 附件Service.
 * 
 * @author pwl
 * 
 */
public interface AttachmentService extends Serializable {

	/**
	 * 添加附件.
	 * 
	 * @param form
	 * @throws ServiceException
	 */
	public Attachment addAttachment(FileUploadForm form) throws ServiceException;
}
