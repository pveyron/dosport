package com.dosport.domain.psn;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 活动参与者.
 * 
 * @author pwl
 * 
 */
@Entity
@Table(name = "PSN_ACTIVITY")
public class PsnActivity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6996589991582626006L;

	private Long id;
	private Long psnId;
	private Long activityId;
	private Date participateDate;
	private Integer status;

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

	@Column(name = "ACTIVITY_ID")
	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	@Column(name = "PARTICIPATE_DATE")
	public Date getParticipateDate() {
		return participateDate;
	}

	public void setParticipateDate(Date participateDate) {
		this.participateDate = participateDate;
	}

	@Column(name = "STATUS")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
