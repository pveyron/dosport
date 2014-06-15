package com.dosport.dao.attachment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dosport.remoting.httpinvoker.Remote;
import com.dosport.service.attachment.AbstractAttachmentComponent;
import com.dosport.service.attachment.AttachmentService;

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

}
