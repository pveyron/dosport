package com.dosport.springframework.security.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 系统权限资源.
 * 
 * @author pwl
 * 
 */
@Entity
@Table(name = "SYS_AUTHORITY_RESOURCE")
public class SysAuthorityResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5124327144173430301L;

	@Id
	private SysAuthorityResourcePk pkId;

	public SysAuthorityResourcePk getPkId() {
		return pkId;
	}

	public void setPkId(SysAuthorityResourcePk pkId) {
		this.pkId = pkId;
	}

}
