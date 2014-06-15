package com.dosport.springframework.security.service;

import java.io.Serializable;
import java.util.List;

import com.dosport.service.exception.ServiceException;
import com.dosport.springframework.security.domain.SysAuthority;
import com.dosport.springframework.security.domain.SysRole;
import com.dosport.springframework.security.domain.SysUser;

/**
 * 系统用户service.
 * 
 * @author pwl
 * 
 */
public interface UserService extends Serializable {

	/**
	 * 通过登录名获取用户信息.
	 * 
	 * @param loginName
	 * @return
	 * @throws ServiceException
	 */
	public SysUser getUserByLoginName(String loginName) throws ServiceException;

	/**
	 * 根据登录名查询用户密码.
	 * 
	 * @param loginName
	 * @return
	 * @throws ServiceException
	 */
	public String getPasswordByLoginName(String loginName) throws ServiceException;

	/**
	 * 根据用户id查询用户的角色.
	 * 
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public List<SysRole> getRolesByUserId(Long userId) throws ServiceException;

	/**
	 * 根据用户id查询用户的角色id.
	 * 
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public List<Long> getRoleIdByUserId(Long userId) throws ServiceException;

	/**
	 * 根据角色id查询角色所具有的权限.
	 * 
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	public List<SysAuthority> getAuthoritiesByRoleId(Long roleId) throws ServiceException;

	/**
	 * 根据登陆名查询用户所具有的权限.
	 * 
	 * @param loginName
	 * @return
	 * @throws ServiceException
	 */
	public List<SysAuthority> getAuthoritiesByLoginName(String loginName) throws ServiceException;

	/**
	 * 查询用户具体角色所具有的权限.
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	public List<SysAuthority> getAuthorityList(Long userId) throws ServiceException;

	/**
	 * 获取系统所有的权限名称.
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<String> getAllAuthoritiyName() throws ServiceException;

	/**
	 * 获取系统或有的权限.
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<SysAuthority> getAllAuthoritiy() throws ServiceException;

	/**
	 * 通过权限查询资源路径.
	 * 
	 * @param authorityId
	 * @return
	 * @throws ServiceException
	 */
	public List<String> getResourceValueByAuthoritiyId(Long authorityId) throws ServiceException;

	/**
	 * 获取系统所有角色.
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<SysRole> getAllRoles() throws ServiceException;

}
