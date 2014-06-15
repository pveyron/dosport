package com.dosport.springframework.security.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dosport.dao.exception.DaoException;
import com.dosport.hibernate.utils.HibernateDao;
import com.dosport.springframework.security.domain.SysAuthority;

/**
 * 系统权限dao.
 * 
 * @author pwl
 * 
 */
@Repository
public class SysAuthorityDao extends HibernateDao<SysAuthority, Long> {

	/**
	 * 通过角色id查询角色所具有的权限.
	 * 
	 * @param roleId
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public List<SysAuthority> queryAuthoritiesByRoleId(Long roleId) throws DaoException {

		return super
				.createQuery(
						"from SysAuthorities t1 where exists(select 1 from SysRoleAuthority t2 where t1.id = t2.pkId.authorityId and t2.pkId.roleId = ?)",
						roleId).list();
	}

	/**
	 * 通过登陆名查询权限.
	 * 
	 * @param loginName
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public List<SysAuthority> queryAuthoritiesByLoginName(String loginName) throws DaoException {
		StringBuffer hql = new StringBuffer("from SysAuthorities t1 where");
		hql.append(" exists(select 1 from SysRoleAuthority t2, SysUserRole t3, SysUsers t4")
				.append(" where t1.id = t2.pkId.authorityId and t3.pkId.roleId = t2.pkId.roleId and t3.pkId.userId = t4.id and t4.loginName = ?)")
				.append(" order by t1.id desc");
		return super.createQuery(hql.toString(), loginName).list();
	}

	/**
	 * 通过用户id查询权限.
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public List<SysAuthority> queryAuthorityList(Long userId) throws DaoException {
		StringBuffer hql = new StringBuffer("from SysAuthority sa where");
		hql.append(" exists(select 1 from SysRoleAuthority sra where sa.id = sra.pkId.authorityId")
				.append(" and exists (select 1 from SysUserRole sur where sur.pkId.roleId = sra.pkId.roleId and sur.pkId.userId = ?)) order by sa.id desc");
		return super.createQuery(hql.toString(), userId).list();
	}

	/**
	 * 获取系统所有的权限名称.
	 * 
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public List<String> queryAllAuthorityName() throws DaoException {
		return super.createQuery("select t.name from SysAuthorities t").list();
	}
}
