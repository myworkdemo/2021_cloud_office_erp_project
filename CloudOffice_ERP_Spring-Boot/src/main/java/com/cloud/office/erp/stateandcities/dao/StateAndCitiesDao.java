package com.cloud.office.erp.stateandcities.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloud.office.erp.entity.StateAndCities;

@Repository
public interface StateAndCitiesDao extends JpaRepository<StateAndCities, Long>{

	@Modifying
	@Query("DELETE FROM StateAndCities")
	public void truncateStateAndCitiesTable();
	
	@Query("SELECT sac.city FROM StateAndCities sac WHERE sac.state LIKE :state% ORDER BY sac.city ASC")
	public List<String> getCitiesByState(String state);

	@Query("SELECT sac.state FROM StateAndCities sac GROUP BY sac.state ORDER BY sac.state ASC")
	public List<String> getAllStateList();
	
	@Query("SELECT sac.city FROM StateAndCities sac ORDER BY sac.city ASC")
	public List<String> getAllCities();

}
