package com.cloud.office.erp.contractor.service;

import java.util.List;
import java.util.Optional;

import com.cloud.office.erp.entity.Contractor;

public interface ContractorService {

	public Contractor addContractor(Contractor contractor);

	public void deleteContractor(Long contractorId);

	public List<Contractor> getAllContractor();

	public Optional<Contractor> getContractorById(Long contractorId);
	
	public Optional<Contractor> getContractorByName(String contractorName);
}
