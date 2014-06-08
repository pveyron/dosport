package com.dosport.system.domain.loginlog;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 系统登录日志.
 * 
 * @author pwl
 * 
 */
@Entity
@Table(name = "SYS_LOGIN_LOG")
public class SysLoginLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1875072457451096179L;

	private Long id;
	private Long psnId;
	/** 登录客户端. */
	private Integer client;
	private Date loginDate;
	private String ip;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PSN_ID")
	public Long getPsnId() {
		return psnId;
	}

	public void setPsnId(Long psnId) {
		this.psnId = psnId;
	}

	@Column(name = "CLIENT")
	public Integer getClient() {
		return client;
	}

	public void setClient(Integer client) {
		this.client = client;
	}

	@Column(name = "LOGIN_DATE")
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	@Column(name = "IP")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
