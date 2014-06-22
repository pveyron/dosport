package com.dosport.domain.attachment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 附件.
 * 
 * @author pwl
 * 
 */
@Entity
@Table(name = "ATTACHMENT")
public class Attachment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6739001536322599706L;

	private Long id;
	private Long psnId;
	private String name;
	private String saveName;
	private String type;
	private Date uploadDate;

	public Attachment() {
	}

	public Attachment(Long psnId, String name, String saveName, String type, Date uploadDate) {
		this.psnId = psnId;
		this.name = name;
		this.saveName = saveName;
		this.type = type;
		this.uploadDate = uploadDate;
	}

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

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SAVE_NAME")
	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "UPLOAD_DATE")
	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

}
