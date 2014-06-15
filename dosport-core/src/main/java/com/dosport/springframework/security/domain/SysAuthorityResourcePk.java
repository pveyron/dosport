package com.dosport.springframework.security.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 系统权限资源主键.
 * 
 * @author pwl
 * 
 */
@Embeddable
public class SysAuthorityResourcePk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 691386401263012782L;

	@Column(name = "AUTHORITY_ID")
	private Long authorityId;

	@Column(name = "RESOURCE_ID")
	private Long resourceId;

	public Long getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

}
