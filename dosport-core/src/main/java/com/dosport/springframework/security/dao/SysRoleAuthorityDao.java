package com.dosport.springframework.security.dao;

import org.springframework.stereotype.Repository;

import com.dosport.hibernate.utils.HibernateDao;
import com.dosport.springframework.security.domain.SysRoleAuthority;
import com.dosport.springframework.security.domain.SysRoleAuthorityPk;

/**
 * 系统角色权限dao.
 * 
 * @author pwl
 * 
 */
@Repository
public class SysRoleAuthorityDao extends HibernateDao<SysRoleAuthority, SysRoleAuthorityPk> {

}
