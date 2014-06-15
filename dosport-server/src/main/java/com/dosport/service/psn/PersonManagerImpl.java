package com.dosport.service.psn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dosport.dao.psn.PersonDao;
import com.dosport.remoting.httpinvoker.Remote;
import com.dosport.service.exception.ServiceException;
import com.dosport.system.utils.LogUtils;

/**
 * 用户信息管理ServiceImpl.
 * 
 * @author pwl
 * 
 */
@Service("personManager")
@Remote(remoteInterface = PersonManager.class)
@Transactional(rollbackFor = Exception.class)
public class PersonManagerImpl implements PersonManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9047685271112305313L;

	@Autowired
	private PersonDao personDao;

	@Override
	public String getPsnVieName(Long psnId) throws ServiceException {

		try {
			return this.personDao.queryViewNameByPsnId(psnId);
		} catch (Exception e) {
			LogUtils.error(e, "查询用户psnId={}的显示名称实现异常", psnId);
			throw new ServiceException(e);
		}
	}

}
