package com.dosport.domain.activity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 活动附件.
 * 
 * @author pwl
 * 
 */
@Entity
@Table(name = "ACTIVITY_FILE")
public class ActivityFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9154600838368121039L;

	private Long id;
	private Long activityId;
	private Long attachmentId;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ACTIVITY_ID")
	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	@Column(name = "ATTACHMENT_ID")
	public Long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}

}
