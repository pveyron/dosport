package com.dosport.springframework.security.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 系统用户角色关系.
 * 
 * @author linyueqin
 * 
 */
@Entity
@Table(name = "SYS_USER_ROLE")
public class SysUserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7333130877986581829L;

	@Id
	private SysUserRolePk pkId;

	public SysUserRolePk getPkId() {
		return pkId;
	}

	public void setPkId(SysUserRolePk pkId) {
		this.pkId = pkId;
	}

}
