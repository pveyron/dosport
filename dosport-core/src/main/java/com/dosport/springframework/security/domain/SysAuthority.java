package com.dosport.springframework.security.domain;

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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 系统权限.
 * 
 * @author pwl
 * 
 */
@Entity
@Table(name = "SYS_AUTHORITY")
public class SysAuthority implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2666643546535577429L;

	private Long id;
	private String name;
	private Integer enabled;

	private Set<SysResource> resources = new LinkedHashSet<SysResource>();

	private Set<SysRole> roles = new LinkedHashSet<SysRole>();

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

	@Column(name = "ENABLED")
	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "SYS_AUTHORITY_RESOURCE", joinColumns = { @JoinColumn(name = "AUTHORITY_ID") }, inverseJoinColumns = { @JoinColumn(name = "RESOURCE_ID") })
	public Set<SysResource> getResources() {
		return resources;
	}

	public void setResources(Set<SysResource> resources) {
		this.resources = resources;
	}

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "authorities")
	public Set<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<SysRole> roles) {
		this.roles = roles;
	}

}
