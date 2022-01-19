package com.cloud.office.erp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "state_and_cities")
public class StateAndCities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stateAndCitiesId;
	private String city;
	private String state;
	
	public StateAndCities() {}
	
	public StateAndCities(String city, String state) {
		this.city = city;
		this.state = state;
	}

	public Long getStateAndCitiesId() {
		return stateAndCitiesId;
	}

	public void setStateAndCitiesId(Long stateAndCitiesId) {
		this.stateAndCitiesId = stateAndCitiesId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
