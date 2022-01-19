package com.cloud.office.erp.contractor.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cloud.office.erp.contractor.service.ContractorService;
import com.cloud.office.erp.entity.Contractor;

@RestController
@CrossOrigin(origins =  "*")
@RequestMapping(value = "/contractor")
public class ContractorController {

	@Autowired
	ContractorService contractorService;
	
	@PostMapping(value = "/add")
	public Contractor addContractor(@RequestBody Contractor contractor) {
		
		return contractorService.addContractor(contractor);
	}
	
	@PostMapping(value = "/update")
	public Contractor updateContractor(@RequestBody Contractor contractor) {
		
		Optional<Contractor> optional = contractorService.getContractorByName(contractor.getContractorName());
		
		if(optional.isPresent()) {
			contractor = contractorService.addContractor(optional.get());
		}
		
		return contractor;
	}

	@GetMapping(value = "/delete/{contractorId}")
	public void deleteContractor(@PathVariable("contractorId") Long contractorId) {
		
		contractorService.deleteContractor(contractorId);
	}

	@GetMapping(value = "/getAll")
	public List<Contractor> getAllContractor() {
		
		return contractorService.getAllContractor();
	}

	@GetMapping(value = "/getById/{contractorId}")
	public Contractor getContractorById(@PathVariable("contractorId") Long contractorId) {
		
		return contractorService.getContractorById(contractorId).get();
	}
	
	@GetMapping(value = "/getByName/{contractorName}")
	public Contractor getContractorByName(@PathVariable("contractorName") String contractorName) {
		System.out.println("#contractorName : "+contractorName);
		Optional<Contractor> optional = contractorService.getContractorByName(contractorName);
		
		return (optional.isPresent())? optional.get() : null;
	}
}
