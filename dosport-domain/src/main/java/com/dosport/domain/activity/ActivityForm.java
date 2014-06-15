package com.dosport.domain.activity;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动Form.
 * 
 * @author pwl
 * 
 */
public class ActivityForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3727149751886488078L;

	private String title;
	private String sportName;
	private Integer sportId;
	private Long psnId;
	private Date startDate;
	private Date endDate;
	private String address;
	private Double longitude;
	private Double latitude;
	/** 公交站台. */
	private String busStop;
	/** 公交路线. */
	private String bus;
	private String note;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSportName() {
		return sportName;
	}

	public void setSportName(String sportName) {
		this.sportName = sportName;
	}

	public Integer getSportId() {
		return sportId;
	}

	public void setSportId(Integer sportId) {
		this.sportId = sportId;
	}

	public Long getPsnId() {
		return psnId;
	}

	public void setPsnId(Long psnId) {
		this.psnId = psnId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getBusStop() {
		return busStop;
	}

	public void setBusStop(String busStop) {
		this.busStop = busStop;
	}

	public String getBus() {
		return bus;
	}

	public void setBus(String bus) {
		this.bus = bus;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
