package com.cloud.office.erp.materialqc.service;

import java.util.List;
import java.util.Optional;
import com.cloud.office.erp.entity.MaterialQc;

public interface MaterialQcService {

	public MaterialQc addMaterialQc(MaterialQc materialQc);

	public MaterialQc updateMaterialQc(MaterialQc materialQc);

	public void deleteMaterialQc(Long materialQcId);

	public List<MaterialQc> getAllMaterialQc();

	public Optional<MaterialQc> getMaterialQcById(Long materialQcId);

}
