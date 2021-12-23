package fr.bcg.taximanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="FORMULA")
public class Formula {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;
    
    @Column(name = "City_Id", nullable = false)
    private String cityId;
	
    @Column(name = "Formula", nullable = false)
    private String formula;

	public Formula() {
	}

	public Long getId() {
		return id;
	}

	public String getCityId() {
		return cityId;
	}

	public String getFormula() {
		return formula;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
    

}
