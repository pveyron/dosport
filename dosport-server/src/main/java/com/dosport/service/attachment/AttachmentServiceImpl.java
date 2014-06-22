package com.dosport.service.attachment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dosport.dao.attachment.AttachmentDao;
import com.dosport.domain.attachment.Attachment;
import com.dosport.domain.attachment.FileUploadForm;
import com.dosport.remoting.httpinvoker.Remote;
import com.dosport.security.utils.SecurityUtils;
import com.dosport.service.exception.ServiceException;
import com.dosport.system.utils.LogUtils;

/**
 * 附件ServiceImpl
 * 
 * @author pwl
 * 
 */
@Service("attachmentService")
@Remote(remoteInterface = AttachmentService.class)
@Transactional(rollbackFor = Exception.class)
public class AttachmentServiceImpl extends AbstractAttachmentComponent implements AttachmentService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2272852277405197397L;

	@Autowired
	private AttachmentDao attachmentDao;

	@Override
	public Attachment addAttachment(FileUploadForm form) throws ServiceException {

		try {
			Attachment attachment = new Attachment(SecurityUtils.getCurrentPsnId(), form.getFileDataFileName(),
					form.getFileSaveName(), form.getFileDataContentType(), form.getUploadDate());
			this.attachmentDao.save(attachment);
			return attachment;
		} catch (Exception e) {
			LogUtils.error(e, "保存附件信息出现异常");
			throw new ServiceException(e);
		}
	}

}
