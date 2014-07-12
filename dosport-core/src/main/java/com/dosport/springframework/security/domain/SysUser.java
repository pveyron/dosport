package com.dosport.springframework.security.domain;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 系统用户.
 * 
 * @author pwl
 * 
 */
@Entity
@Table(name = "SYS_USER")
public class SysUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3163816513699864309L;

	private Long id;
	private String loginName;
	private String password;
	private Integer enabled;
	private Integer isSys;

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

	@Column(name = "LOGIN_NAME")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "ENABLED")
	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@Column(name = "IS_SYS")
	public Integer getIsSys() {
		return isSys;
	}

	public void setIsSys(Integer isSys) {
		this.isSys = isSys;
	}

	/**
	 * 使用@JoinTable标签的name属性注解第三方表名称；使用joinColumns属性来注解当前实体类在第三方表中的字段名称并指向该对象；
	 * 使用inverseJoinColumns属性来注解当前实体类持有引用对象在第三方表中的字段名称并指向被引用对象表
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "SYS_USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	public Set<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<SysRole> roles) {
		this.roles = roles;
	}

}
