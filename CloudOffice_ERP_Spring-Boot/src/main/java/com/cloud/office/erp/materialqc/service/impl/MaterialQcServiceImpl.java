package com.cloud.office.erp.materialqc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.office.erp.entity.MaterialQc;
import com.cloud.office.erp.materialqc.dao.MaterialQcDao;
import com.cloud.office.erp.materialqc.service.MaterialQcService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class MaterialQcServiceImpl implements MaterialQcService {

	@Autowired
	MaterialQcDao materialQcDao;

	@Override
	public MaterialQc addMaterialQc(MaterialQc materialQc) {

		return materialQcDao.save(materialQc);
	}

	@Override
	public MaterialQc updateMaterialQc(MaterialQc materialQc) {

		return materialQcDao.save(materialQc);
	}

	@Override
	public void deleteMaterialQc(Long materialQcId) {

		Optional<MaterialQc> materialQc = materialQcDao.findById(materialQcId);
		materialQcDao.delete(materialQc.get());
	}

	@Override
	public List<MaterialQc> getAllMaterialQc() {

		return materialQcDao.findAll();
	}

	@Override
	public Optional<MaterialQc> getMaterialQcById(Long materialQcId) {

		return materialQcDao.findById(materialQcId);
	}

}
