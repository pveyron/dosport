package com.dosport.domain.psn;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 人员信息.
 * 
 * @author pwl
 * 
 */
@Entity
@Table(name = "PERSON")
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2147329179625352604L;

	private Long id;
	private String name;
	private String viewName;
	private Integer sex;
	private String tel;
	/** 个性签名. */
	private String signature;
	/** 最感兴趣的运动. */
	private String interesteSport;
	private Integer sprotId;
	private String address;
	private Date regDate;
	private Integer status;
	/** 活跃度. */
	private Integer degree;

	@Id
	@Column(name = "ID")
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

	@Column(name = "VIEW_NAME")
	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	@Column(name = "SEX")
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name = "TEL")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "SIGNATURE")
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Column(name = "INTERESTE_SPROT")
	public String getInteresteSport() {
		return interesteSport;
	}

	public void setInteresteSport(String interesteSport) {
		this.interesteSport = interesteSport;
	}

	@Column(name = "SPORT_ID")
	public Integer getSprotId() {
		return sprotId;
	}

	public void setSprotId(Integer sprotId) {
		this.sprotId = sprotId;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "REG_DATE")
	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Column(name = "STATUS")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "DEGREE")
	public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}
}
