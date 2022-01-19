package com.cloud.office.erp.material.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.office.erp.entity.Material;
import com.cloud.office.erp.material.dao.MaterialDao;
import com.cloud.office.erp.material.service.MaterialService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	MaterialDao materialDao;
	
	@Override
	public Material addMaterial(Material material) {
	
		Long serialNum = materialDao.findSerialNumberOfLastRecord();
		System.out.println("#serialNum : "+serialNum);
		return materialDao.save(material);
	}

	@Override
	public Material updateMaterial(Material material) {
		
		return materialDao.save(material);
	}

	@Override
	public void deleteMaterial(Long materialId) {
		
		Optional<Material> material = materialDao.findById(materialId);
		materialDao.delete(material.get());
	}

	@Override
	public List<Material> getAllMaterial() {
		
		return materialDao.findAll();
	}

	@Override
	public Optional<Material> getMaterialById(Long materialId) {
	
		return materialDao.findById(materialId);
	}

	@Override
	public List<Material> getMaterial(Pageable pageable) {
		
		return materialDao.getMaterial(pageable);
	}

	@Override
	public List<Material> searchMaterial(String searchValue) {
		
		return materialDao.searchMaterial(searchValue);
	}

}
