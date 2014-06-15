package com.dosport.springframework.security.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dosport.dao.exception.DaoException;
import com.dosport.hibernate.utils.HibernateDao;
import com.dosport.springframework.security.domain.SysRole;

/**
 * 系统角色dao.
 * 
 * @author pwl
 * 
 */
@Repository
public class SysRoleDao extends HibernateDao<SysRole, Long> {

	/**
	 * 根据用户id查询用户具有的角色.
	 * 
	 * @param userId
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public List<SysRole> queryRolesByUserId(Long userId) throws DaoException {

		return super
				.createQuery(
						"from SysRoles t1 where exists(select 1 from SysUserRole t2 where t1.id = t2.pkId.roleId and t2.pkId.userId = ?)",
						userId).list();
	}

	@SuppressWarnings("unchecked")
	public List<Long> queryRoleIdByUserId(Long userId) throws DaoException {

		return super
				.createQuery(
						"select t1.id from SysRoles t1 where exists(select 1 from SysUserRole t2 where t1.id = t2.pkId.roleId and t2.pkId.userId = ?)",
						userId).list();
	}

}
