package fr.bcg.taximanagement.model;

import java.util.Date;

public class RideMin {
	private Long id;
	private String driver;
	private Date startTime;
	private String status;
	private Double distance;

	public RideMin(Long id, String driver, Date startTime, String status, Double distance) {
		super();
		this.id = id;
		this.driver = driver;
		this.startTime = startTime;
		this.status = status;
		this.distance = distance;
	}

	public RideMin() {
	}

	public Long getId() {
		return id;
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

	public Double getDistance() {
		return distance;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setDistance(Double distance) {
		this.distance = distance;
	}

}
