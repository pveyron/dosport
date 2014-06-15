package com.dosport.springframework.security.dao;

import org.springframework.stereotype.Repository;

import com.dosport.hibernate.utils.HibernateDao;
import com.dosport.springframework.security.domain.SysUserRole;
import com.dosport.springframework.security.domain.SysUserRolePk;

/**
 * 用户角色dao.
 * 
 * @author pwl
 * 
 */
@Repository
public class SysUserRoleDao extends HibernateDao<SysUserRole, SysUserRolePk> {

}
