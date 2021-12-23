package fr.bcg.taximanagement.model;

import java.util.Date;

public class RideDetail {

	private Long id;
	private String driver;
	private Date startTime;
	private String status;
	private Long duration;
	private Double distance;
	private Double charge;
	private String cityId;

	public RideDetail() {
	}

	public Double getDistance() {
		return distance;
	}

	public Double getCharge() {
		return charge;
	}

	public String getCityId() {
		return cityId;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDriver() {
		return driver;
	}

	public Date getStartTime() {
		return startTime;
	}

	public String getStatus() {
		return status;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDuration(Date duration) {
		this.duration = duration.getTime();
	}

}
