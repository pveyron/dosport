package com.dosport.springframework.security.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dosport.service.exception.ServiceException;
import com.dosport.springframework.security.dao.SysAuthorityDao;
import com.dosport.springframework.security.dao.SysResourceDao;
import com.dosport.springframework.security.dao.SysRoleDao;
import com.dosport.springframework.security.dao.SysUserDao;
import com.dosport.springframework.security.domain.SysAuthority;
import com.dosport.springframework.security.domain.SysRole;
import com.dosport.springframework.security.domain.SysUser;

/**
 * 系统用户serviceImpl.
 * 
 * @author pwl
 * 
 */
@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7814464906154192796L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SysUserDao sysUsersDao;
	@Autowired
	private SysRoleDao sysRolesDao;
	@Autowired
	private SysAuthorityDao sysAuthoritiesDao;
	@Autowired
	private SysResourceDao sysResourcesDao;

	@Override
	public SysUser getUserByLoginName(String loginName) throws ServiceException {
		try {
			return this.sysUsersDao.queryUserByLoginName(loginName);
		} catch (Exception e) {
			logger.error("通过登录名loginName={}获取用户信息出现异常：", loginName, e);
			throw new ServiceException(e);
		}
	}

	@Override
	public String getPasswordByLoginName(String loginName) throws ServiceException {
		try {
			return this.sysUsersDao.queryPasswordByLoginName(loginName);
		} catch (Exception e) {
			logger.error("通过登录名loginName={}获取用户密码出现异常：{}", loginName, e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SysRole> getRolesByUserId(Long userId) throws ServiceException {
		try {
			return this.sysRolesDao.queryRolesByUserId(userId);
		} catch (Exception e) {
			logger.error("通过用户userId={}获取用户的角色id出现异常：{}", userId, e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Long> getRoleIdByUserId(Long userId) throws ServiceException {
		try {
			return this.sysRolesDao.queryRoleIdByUserId(userId);
		} catch (Exception e) {
			logger.error("通过用户userId={}获取用户的角色出现异常：{}", userId, e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SysAuthority> getAuthoritiesByRoleId(Long roleId) throws ServiceException {
		try {
			return this.sysAuthoritiesDao.queryAuthoritiesByRoleId(roleId);
		} catch (Exception e) {
			logger.error("通过角色roleId={}获取角色的权限出现异常：{}", roleId, e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SysAuthority> getAuthoritiesByLoginName(String loginName) throws ServiceException {
		try {
			return this.sysAuthoritiesDao.queryAuthoritiesByLoginName(loginName);
		} catch (Exception e) {
			logger.error("通过登陆名loginName={}查询权限出现异常：{}", loginName, e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SysAuthority> getAuthorityList(Long userId) throws ServiceException {
		try {
			return this.sysAuthoritiesDao.queryAuthorityList(userId);
		} catch (Exception e) {
			logger.error(String.format("查询用户userId=%s具有的权限出现异常：", userId), e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<String> getAllAuthoritiyName() throws ServiceException {
		try {
			return this.sysAuthoritiesDao.queryAllAuthorityName();
		} catch (Exception e) {
			logger.error("查询系统所有的权限名称出现异常：", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SysAuthority> getAllAuthoritiy() throws ServiceException {
		try {
			return this.sysAuthoritiesDao.getAll();
		} catch (Exception e) {
			logger.error("查询系统所有的权限名称出现异常：", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<String> getResourceValueByAuthoritiyId(Long authorityId) throws ServiceException {
		try {
			return this.sysResourcesDao.queryResourceValueByAuthorityId(authorityId);
		} catch (Exception e) {
			logger.error("查询权限authorityId={}所能访问的资源出现异常：{}", authorityId, e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SysRole> getAllRoles() throws ServiceException {
		try {
			return sysRolesDao.getAll();
		} catch (Exception e) {
			logger.error("查询系统所有角色出现异常：", e);
			throw new ServiceException(e);
		}
	}

}
