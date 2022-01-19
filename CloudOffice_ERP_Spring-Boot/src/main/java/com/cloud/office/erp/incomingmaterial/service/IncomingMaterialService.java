package com.cloud.office.erp.incomingmaterial.service;

import java.util.List;
import java.util.Optional;
import com.cloud.office.erp.entity.IncomingMaterial;
import com.cloud.office.erp.entity.IncomingMaterialJsonEntity;

public interface IncomingMaterialService {

	public IncomingMaterial addIncomingMaterial(IncomingMaterial incomingMaterial);
	
	public IncomingMaterial updateIncomingMaterial(IncomingMaterial incomingMaterial);

	public void deleteIncomingMaterial(Long incomingMaterialId);

	public List<IncomingMaterial> getAllIncomingMaterial();

	public Optional<IncomingMaterial> getIncomingMaterialById(Long incomingMaterialId);
	
	public List<IncomingMaterialJsonEntity> getAllRecords();
}
