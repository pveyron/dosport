package com.dosport.springframework.security.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dosport.dao.exception.DaoException;
import com.dosport.hibernate.utils.HibernateDao;
import com.dosport.springframework.security.domain.SysResource;

/**
 * 系统资源dao.
 * 
 * @author pwl
 * 
 */
@Repository
public class SysResourceDao extends HibernateDao<SysResource, Long> {

	/**
	 * 通过权限查询权限所能访问的资源.
	 * 
	 * @param authorityId
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public List<String> queryResourceValueByAuthorityId(Long authorityId) throws DaoException {

		return super
				.createQuery(
						"select sr.value from SysResources sr where exists(select 1 from SysAuthorityResource sar where sr.id = sar.pkId.resourceId and sar.pkId.authorityId = ?)",
						authorityId).list();
	}
}
