package com.dosport.service.psn;

import java.io.Serializable;

import com.dosport.service.exception.ServiceException;

/**
 * 用户信息管理Service.
 * 
 * @author pwl
 * 
 */
public interface PersonManager extends Serializable {

	/**
	 * 获取用户的显示名称.
	 * 
	 * @param psnId
	 * @return
	 * @throws ServiceException
	 */
	public String getPsnVieName(Long psnId) throws ServiceException;
}
