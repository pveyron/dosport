package com.dosport.domain.constant;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 运动常量.
 * 
 * @author pwl
 * 
 */
@Entity
@Table(name = "CONST_SPORT")
public class ConstSport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5838559293703983246L;

	private Long id;
	private String name;
	private String desc;

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

	@Column(name = "DESC")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
