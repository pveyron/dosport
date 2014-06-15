package com.dosport.dao.psn;

import org.springframework.stereotype.Repository;

import com.dosport.dao.exception.DaoException;
import com.dosport.domain.psn.Person;
import com.dosport.hibernate.utils.HibernateDao;

/**
 * 人员信息Dao.
 * 
 * @author pwl
 * 
 */
@Repository
public class PersonDao extends HibernateDao<Person, Long> {

	/**
	 * 
	 * @param psnId
	 * @return
	 * @throws DaoException
	 */
	public String queryViewNameByPsnId(Long psnId) throws DaoException {

		return (String) super.createQuery("select t.viewName from Person t where t.id = ?", psnId).uniqueResult();
	}
}
