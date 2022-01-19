package com.cloud.office.erp.contractor.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.office.erp.contractor.dao.ContractorDao;
import com.cloud.office.erp.contractor.service.ContractorService;
import com.cloud.office.erp.entity.Contractor;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class ContractorServiceImpl implements ContractorService {

	@Autowired
	ContractorDao contractorDao;
	
	@Override
	public Contractor addContractor(Contractor contractor) {
		
		return contractorDao.save(contractor);
	}

	@Override
	public void deleteContractor(Long contractorId) {
		
		Contractor contractor = contractorDao.findById(contractorId).get();
		contractorDao.delete(contractor);
	}

	@Override
	public List<Contractor> getAllContractor() {
		
		return contractorDao.findAll();
	}

	@Override
	public Optional<Contractor> getContractorById(Long contractorId) {
		
		return contractorDao.findById(contractorId);
	}

	@Override
	public Optional<Contractor> getContractorByName(String contractorName) {
		
		return contractorDao.getContractorByName(contractorName);
	}
	
}
