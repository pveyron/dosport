package com.dosport.security.domain;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 系统资源.
 * 
 * @author pwl
 * 
 */
@Entity
@Table(name = "SYS_RESOURCE")
public class SysResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3131903014310106934L;

	private Long id;
	private String name;
	private String resType;
	private String value;
	private Integer enabled;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "resources")
	private Set<SysAuthoritie> authorities = new LinkedHashSet<SysAuthoritie>();

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "RES_TYPE")
	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	@Column(name = "VALUE")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "ENABLED")
	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Set<SysAuthoritie> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<SysAuthoritie> authorities) {
		this.authorities = authorities;
	}

}
