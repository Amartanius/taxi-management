package fr.bcg.taximanagement.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "RIDE")
public class Ride extends Auditable<String> {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;
    
    @Column(name = "Driver_Id", length = 64, nullable = false)
    private String driver;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Start_Time", nullable = false)
    private Date startTime;
    
    @Column(name = "Status", nullable = false)
    private String status;
	
    @Column(name = "Distance", nullable = true)
    private Double distance;
    
    @Temporal(TemporalType.TIME)
    @Column(name = "Duration", nullable = true)
    private Date duration;
        
    @Column(name = "Charge", nullable = true)
    private Double charge;
    
    @Column(name = "City_Id", nullable = false)
    private String cityId;

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

	public Date getDuration() {
		return duration;
	}

	public Double getCharge() {
		return charge;
	}

	public String getCityId() {
		return cityId;
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

	public void setDuration(Date duration) {
		this.duration = duration;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
    
}
