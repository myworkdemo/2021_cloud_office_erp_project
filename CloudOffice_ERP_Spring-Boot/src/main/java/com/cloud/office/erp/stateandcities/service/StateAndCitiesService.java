package com.cloud.office.erp.stateandcities.service;

import java.util.List;
import java.util.Optional;

import com.cloud.office.erp.entity.StateAndCities;

public interface StateAndCitiesService {

	public StateAndCities addStateAndCities(StateAndCities stateAndCities);

	public StateAndCities updateStateAndCities(StateAndCities user);

	public void deleteStateAndCities(Long stateAndCitiesId);

	public List<StateAndCities> getAllStateAndCities();

	public StateAndCities getStateAndCitiesById(Long stateAndCitiesId);

	public List<String> getCitiesByState(String state);
	
	public void truncateStateAndCitiesTable();

	public List<String> getAllStateList();
	
	public List<String> getAllCities();
}
