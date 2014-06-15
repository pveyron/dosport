package com.dosport.dao.activity;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dosport.dao.exception.DaoException;
import com.dosport.domain.activity.Activity;
import com.dosport.domain.activity.ActivityForm;
import com.dosport.hibernate.utils.HibernateDao;

/**
 * 发布的运动项目Dao.
 * 
 * @author pwl
 * 
 */
@Repository
public class ActivityDao extends HibernateDao<Activity, Long> {

	/**
	 * 分页查询没有过期的活动信息.
	 * 
	 * @param form
	 * @param firstResult
	 * @param maxSize
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public List<Activity> queryActivityByPage(ActivityForm form, int firstResult, int maxSize) throws DaoException {

		return super
				.createQuery(
						"from Activity t where t.status=1 and t.endDate > ? order by abs(t.longitude - ? + t.latitude - ?)",
						new Object[] { new Date(), form.getLongitude(), form.getLatitude() })
				.setFirstResult(firstResult).setMaxResults(maxSize).list();
	}
}
