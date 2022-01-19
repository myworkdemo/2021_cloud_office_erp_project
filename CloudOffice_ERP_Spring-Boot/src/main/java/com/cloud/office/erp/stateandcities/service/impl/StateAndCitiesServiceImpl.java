package com.cloud.office.erp.stateandcities.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.office.erp.entity.StateAndCities;
import com.cloud.office.erp.stateandcities.dao.StateAndCitiesDao;
import com.cloud.office.erp.stateandcities.service.StateAndCitiesService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class StateAndCitiesServiceImpl implements StateAndCitiesService{

	@Autowired
	StateAndCitiesDao stateAndCitiesDao;
	
	@Override
	public StateAndCities addStateAndCities(StateAndCities stateAndCities) {
	
		return stateAndCitiesDao.save(stateAndCities);
	}

	@Override
	public StateAndCities updateStateAndCities(StateAndCities stateAndCities) {
	
		return stateAndCitiesDao.save(stateAndCities);
	}

	@Override
	public void deleteStateAndCities(Long stateAndCitiesId) {
		
	Optional<StateAndCities> stateAndCities = stateAndCitiesDao.findById(stateAndCitiesId);
		stateAndCitiesDao.delete(stateAndCities.get());
	}

	@Override
	public List<String> getAllStateList() {
	
		return stateAndCitiesDao.getAllStateList();
	}
	
	@Override
	public List<String> getAllCities() {
	
		return stateAndCitiesDao.getAllCities();
	}

	@Override
	public StateAndCities getStateAndCitiesById(Long stateAndCitiesId) {
		
		return stateAndCitiesDao.findById(stateAndCitiesId).get();
	}

	@Override
	public List<String> getCitiesByState(String state) {
		
		return stateAndCitiesDao.getCitiesByState(state);
	}

	@Override
    @Transactional
	public void truncateStateAndCitiesTable() {
		
		stateAndCitiesDao.truncateStateAndCitiesTable();
	}

	@Override
	public List<StateAndCities> getAllStateAndCities() {
		
		return stateAndCitiesDao.findAll();
	}

}
