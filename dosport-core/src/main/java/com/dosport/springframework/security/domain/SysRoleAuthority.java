package com.dosport.springframework.security.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 系统角色权限表.
 * 
 * @author pwl
 * 
 */
@Entity
@Table(name = "SYS_ROLE_AUTHORITY")
public class SysRoleAuthority implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5019812195092867045L;

	@Id
	private SysRoleAuthorityPk pkId;

	public SysRoleAuthorityPk getPkId() {
		return pkId;
	}

	public void setPkId(SysRoleAuthorityPk pkId) {
		this.pkId = pkId;
	}

}
