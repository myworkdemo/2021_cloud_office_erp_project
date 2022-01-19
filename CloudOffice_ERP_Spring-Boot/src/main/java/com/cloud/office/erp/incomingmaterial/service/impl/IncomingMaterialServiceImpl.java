package com.cloud.office.erp.incomingmaterial.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.office.erp.entity.IncomingMaterial;
import com.cloud.office.erp.entity.IncomingMaterialJsonEntity;
import com.cloud.office.erp.incomingmaterial.dao.IncomingMaterialDao;
import com.cloud.office.erp.incomingmaterial.service.IncomingMaterialService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class IncomingMaterialServiceImpl implements IncomingMaterialService {
	
	@Autowired
	IncomingMaterialDao incomingMaterialDao;

	@Override
	public IncomingMaterial addIncomingMaterial(IncomingMaterial incomingMaterial) {
		
		return incomingMaterialDao.save(incomingMaterial);
	}

	@Override
	public IncomingMaterial updateIncomingMaterial(IncomingMaterial incomingMaterial) {
		
		return incomingMaterialDao.save(incomingMaterial);
	}

	@Override
	public void deleteIncomingMaterial(Long incomingMaterialId) {
		
		Optional<IncomingMaterial> incomingMaterial = incomingMaterialDao.findById(incomingMaterialId);
		
		incomingMaterialDao.delete(incomingMaterial.get());
	}

	@Override
	public List<IncomingMaterial> getAllIncomingMaterial() {
		
		return incomingMaterialDao.findAll();
	}

	@Override
	public Optional<IncomingMaterial> getIncomingMaterialById(Long incomingMaterialId) {
		
		return incomingMaterialDao.findById(incomingMaterialId);
	}

	@Override
	public List<IncomingMaterialJsonEntity> getAllRecords() {
		
		return incomingMaterialDao.getAllRecords();
	}

}
