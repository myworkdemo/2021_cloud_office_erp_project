package com.cloud.office.erp.materialqcdetails.service;

import java.util.List;
import java.util.Optional;

import com.cloud.office.erp.entity.MaterialQcDetails;

public interface MaterialQcDetailsService {

	public MaterialQcDetails addMaterialQcDetails(MaterialQcDetails materialQcDetails);
	
	public List<MaterialQcDetails> updateMaterialQcDetails(MaterialQcDetails materialQcDetails);

	public void deleteMaterialQcDetails(Long materialQcId);

	public List<MaterialQcDetails> getAllMaterialQcDetails();

	public List<MaterialQcDetails> getMaterialQcDetailsById(Long materialQcId);
}
