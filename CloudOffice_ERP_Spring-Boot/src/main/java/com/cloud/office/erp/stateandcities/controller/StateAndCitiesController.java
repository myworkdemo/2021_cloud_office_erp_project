package com.cloud.office.erp.stateandcities.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cloud.office.erp.stateandcities.service.StateAndCitiesService;

@RestController
@RequestMapping(value = "/state-and-cities")
@CrossOrigin("*")
public class StateAndCitiesController {

	@Autowired
	StateAndCitiesService stateAndCitiesService;
	
	@GetMapping(value = "getCitiesByState/{state}")
	public List<String> getCitiesByState(@PathVariable("state") String state)
	{
		List<String> list = stateAndCitiesService.getCitiesByState(state);
		System.out.println("LIST : "+list);
		return list;
	}
	
	@GetMapping(value = "/getAllStateList")
	public List<String> getAllStateList()
	{
		return stateAndCitiesService.getAllStateList();
	}
	
	@GetMapping(value = "/getAllCitieList")
	public List<String> getAllCities()
	{
		return stateAndCitiesService.getAllCities();
	}
	
}
