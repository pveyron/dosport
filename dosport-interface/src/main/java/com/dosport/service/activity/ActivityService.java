package com.dosport.service.activity;

import java.io.Serializable;

import com.dosport.domain.activity.Activity;
import com.dosport.domain.activity.ActivityForm;
import com.dosport.hibernate.domain.Page;
import com.dosport.service.exception.ServiceException;

/**
 * 发布项目Service.
 * 
 * @author pwl
 * 
 */
public interface ActivityService extends Serializable {

	/**
	 * 保存发布的运动信息.
	 * 
	 * @param form
	 * @throws ServiceException
	 */
	public void addActivity(ActivityForm form) throws ServiceException;

	/**
	 * 分页查询还没过期的附近的运动.
	 * 
	 * @param form
	 * @param page
	 * @return
	 * @throws ServiceException
	 */
	public String getActivity(ActivityForm form, Page<Activity> page) throws ServiceException;

}
