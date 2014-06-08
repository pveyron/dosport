package com.dosport.domain.activity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 发布的运动项目.
 * 
 * @author pwl
 * 
 */
@Entity
@Table(name = "ACTIVITY")
public class Activity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1503482131985186778L;

	private Long id;
	private String title;
	private String sportName;
	private Integer sportId;
	private Long psnId;
	private Date startDate;
	private Date endDate;
	private String address;
	/** 公交站台. */
	private String busStop;
	/** 公交路线. */
	private String bus;
	private Date createDate;
	private String note;
	private Integer status;
	/** 参与人总数. */
	private Integer participatorCount;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "SPORT_NAME")
	public String getSportName() {
		return sportName;
	}

	public void setSportName(String sportName) {
		this.sportName = sportName;
	}

	@Column(name = "SPORT_ID")
	public Integer getSportId() {
		return sportId;
	}

	public void setSportId(Integer sportId) {
		this.sportId = sportId;
	}

	@Column(name = "PSN_ID")
	public Long getPsnId() {
		return psnId;
	}

	public void setPsnId(Long psnId) {
		this.psnId = psnId;
	}

	@Column(name = "START_DATE")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "END_DATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "BUS_STOP")
	public String getBusStop() {
		return busStop;
	}

	public void setBusStop(String busStop) {
		this.busStop = busStop;
	}

	@Column(name = "BUS")
	public String getBus() {
		return bus;
	}

	public void setBus(String bus) {
		this.bus = bus;
	}

	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "NOTE")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "STATUS")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "PARTICIPATOR_COUNT")
	public Integer getParticipatorCount() {
		return participatorCount;
	}

	public void setParticipatorCount(Integer participatorCount) {
		this.participatorCount = participatorCount;
	}

}
