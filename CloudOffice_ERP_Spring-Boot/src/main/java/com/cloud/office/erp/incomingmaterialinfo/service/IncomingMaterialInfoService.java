package com.cloud.office.erp.incomingmaterialinfo.service;

import java.util.List;
import java.util.Optional;

import com.cloud.office.erp.entity.IncomingMaterial;
import com.cloud.office.erp.entity.IncomingMaterialInfo;

public interface IncomingMaterialInfoService {

public IncomingMaterialInfo addIncomingMaterialInfo(IncomingMaterialInfo incomingMaterialInfo);
	
	public IncomingMaterialInfo updateIncomingMaterialInfo(IncomingMaterialInfo incomingMaterialInfo);

	public void deleteIncomingMaterialInfo(Long incomingMaterialInfoId);

	public List<IncomingMaterialInfo> getAllIncomingMaterialInfo();

	public List<IncomingMaterialInfo> getIncomingMaterialInfoById(IncomingMaterial incomingMaterial);
}
