package com.dosport.springframework.security.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 系统用户角色主键.
 * 
 * @author linyueqin
 * 
 */
@Embeddable
public class SysUserRolePk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2367940038141458548L;

	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "ROLE_ID")
	private Long roleId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
