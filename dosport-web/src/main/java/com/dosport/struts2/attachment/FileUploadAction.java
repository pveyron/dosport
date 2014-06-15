package com.dosport.struts2.attachment;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.dosport.domain.attachment.FileUploadForm;
import com.dosport.remoting.httpinvoker.BaseRemotingServiceFactory;
import com.dosport.service.attachment.AttachmentService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 文件上传Action.
 * 
 * @author pwl
 * 
 */
public class FileUploadAction extends ActionSupport implements ModelDriven<FileUploadForm>, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7803093441296799340L;

	@Autowired
	private FileUploadComponent fileUploadComponent;

	@Resource(name = "remotingServiceFactory")
	private BaseRemotingServiceFactory remotingServiceFactory;

	private AttachmentService attachmentService;

	private FileUploadForm form;

	@Override
	public FileUploadForm getModel() {

		return form;
	}

	@Override
	public void prepare() throws Exception {

		attachmentService = this.remotingServiceFactory.getMainSiteService(AttachmentService.class);
	}
}
