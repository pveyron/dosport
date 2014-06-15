package com.dosport.springframework.security.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 系统角色权限主键.
 * 
 * @author pwl
 * 
 */
@Embeddable
public class SysRoleAuthorityPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6649028505274785436L;

	@Column(name = "ROLE_ID")
	private Long roleId;

	@Column(name = "AUTHORITY_ID")
	private Long authorityId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}

}
