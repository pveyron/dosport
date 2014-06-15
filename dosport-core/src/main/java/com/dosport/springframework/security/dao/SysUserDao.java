package com.dosport.springframework.security.dao;

import org.springframework.stereotype.Repository;

import com.dosport.dao.exception.DaoException;
import com.dosport.hibernate.utils.HibernateDao;
import com.dosport.springframework.security.domain.SysUser;

/**
 * 系统用户dao.
 * 
 * @author pwl
 * 
 */
@Repository
public class SysUserDao extends HibernateDao<SysUser, Long> {

	public SysUser queryUserByLoginName(String loginName) throws DaoException {

		return (SysUser) super.createQuery("from SysUsers t where t.loginName=?", loginName).uniqueResult();
	}

	public String queryPasswordByLoginName(String loginName) throws DaoException {

		return (String) super.createQuery("select t.password from SysUsers t where t.loginName=?", loginName)
				.uniqueResult();
	}
}
