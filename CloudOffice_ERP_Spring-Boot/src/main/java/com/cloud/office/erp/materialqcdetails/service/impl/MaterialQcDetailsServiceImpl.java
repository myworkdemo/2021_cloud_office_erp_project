package com.cloud.office.erp.materialqcdetails.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.office.erp.entity.MaterialQc;
import com.cloud.office.erp.entity.MaterialQcDetails;
import com.cloud.office.erp.materialqc.dao.MaterialQcDao;
import com.cloud.office.erp.materialqcdetails.dao.MaterialQcDetailsDao;
import com.cloud.office.erp.materialqcdetails.service.MaterialQcDetailsService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class MaterialQcDetailsServiceImpl implements MaterialQcDetailsService {

	@Autowired
	MaterialQcDetailsDao materialQcDetailsDao;
	
    @Autowired
	MaterialQcDao materialQcDao;

	@Override
	public MaterialQcDetails addMaterialQcDetails(MaterialQcDetails materialQcDetails) {
		
		MaterialQcDetails materialQcDetails2 = null;
		Optional<MaterialQc> materialQc = null;
		
		 if(materialQcDetails.getMaterialQc() != null) {
			 materialQc = materialQcDao.findById(materialQcDetails.getMaterialQc().getMaterialQcId());
		 }		
		
		if(materialQcDetails != null && materialQc.isPresent()) {
		
			if(materialQcDetails.getSrNo_temp() != null && materialQcDetails.getMaterialType_temp() != null &&
					materialQcDetails.getSrNo_temp().length > 0 && materialQcDetails.getMaterialType_temp().length > 0) {
				
				for(int i=0; i < materialQcDetails.getSrNo_temp().length; i++) {
					
					materialQcDetails2 = new MaterialQcDetails(materialQcDetails.getSrNo_temp()[i], materialQcDetails.getMaterialType_temp()[i], materialQcDetails.getMaterialSubType_temp()[i], materialQcDetails.getAccountCode_temp()[i], 
							materialQcDetails.getMaterialName_temp()[i], materialQcDetails.getDescription_temp()[i], materialQcDetails.getUnitPrice_temp()[i], materialQcDetails.getDeliveredQty_temp()[i], materialQcDetails.getApprovedQty_temp()[i], materialQcDetails.getRejectedQty_temp()[i], materialQcDetails.getBalanceQty_temp()[i], 
							materialQcDetails.getRemark_temp()[i], materialQc.get());
				
					materialQcDetailsDao.save(materialQcDetails2);
				}
			}
		}
		
		return materialQcDetails2;
	}

	@Override
	public List<MaterialQcDetails> updateMaterialQcDetails(MaterialQcDetails materialQcDetails) {
		
		Optional<MaterialQc> materialQc = null;
		List<MaterialQcDetails> materialQcDetailsList = null;
		
		 if(materialQcDetails.getMaterialQc() != null) {
			 materialQc = materialQcDao.findById(materialQcDetails.getMaterialQc().getMaterialQcId());
		 }	
		 
		 if(materialQcDetails != null && materialQc.isPresent()) {
			 
			 if(materialQcDetails.getMeterialQcDetailsId_temp() != null && materialQcDetails.getSrNo_temp() != null && 
			    materialQcDetails.getMaterialType_temp() != null && materialQcDetails.getMeterialQcDetailsId_temp().length > 0 && 
			    materialQcDetails.getSrNo_temp().length > 0 && materialQcDetails.getMaterialType_temp().length > 0) {
			 
			materialQcDetailsList =  materialQcDetailsDao.getMaterialQcDetailsByMaterialQc(materialQc.get());
			
                  for(int i=0; i < materialQcDetailsList.size(); i++) {
                	  
                	  Long meterialQcDetailsId = materialQcDetailsList.get(i).getMeterialQcDetailsId();
                	  String meterialQcDetailsId_Array[] = materialQcDetails.getMeterialQcDetailsId_temp();
                	  String meterialQcDetailsId_String = meterialQcDetailsId.toString();
                	  
                	  boolean contains = Arrays.stream(meterialQcDetailsId_Array).anyMatch(meterialQcDetailsId_String::equals);
                	  
                	  if(contains) {
                	
                	  MaterialQcDetails materialQcDetails2 = new MaterialQcDetails(materialQcDetails.getSrNo_temp()[i], materialQcDetails.getMaterialType_temp()[i], materialQcDetails.getMaterialSubType_temp()[i], materialQcDetails.getAccountCode_temp()[i], 
							materialQcDetails.getMaterialName_temp()[i], materialQcDetails.getDescription_temp()[i], materialQcDetails.getUnitPrice_temp()[i], materialQcDetails.getDeliveredQty_temp()[i], materialQcDetails.getApprovedQty_temp()[i], materialQcDetails.getRejectedQty_temp()[i], materialQcDetails.getBalanceQty_temp()[i], 
							materialQcDetails.getRemark_temp()[i], materialQc.get());
				
                	materialQcDetails2.setMeterialQcDetailsId(meterialQcDetailsId);
					materialQcDetailsDao.save(materialQcDetails2);
					
                }else {
                	Optional<MaterialQcDetails> materialQcDetails_temp = materialQcDetailsDao.findById(meterialQcDetailsId);
                	
                	if(materialQcDetails_temp.isPresent()) {
                	materialQcDetailsDao.delete(materialQcDetails_temp.get());
                	}
                }
			}
			
		 }
			 
		 }
		 
		
		return materialQcDetailsList;
	}

	@Override
	public void deleteMaterialQcDetails(Long materialQcId) {
		
		Optional<MaterialQc> materialQc = materialQcDao.findById(materialQcId);
		
		if(materialQc.isPresent()) {
		   materialQcDetailsDao.deleteMaterialQcDetails(materialQc.get());
		   materialQcDao.delete(materialQc.get());
		}
	}

	@Override
	public List<MaterialQcDetails> getAllMaterialQcDetails() {
		
		return materialQcDetailsDao.findAll();
	}

	@Override
	public List<MaterialQcDetails> getMaterialQcDetailsById(Long materialQcId) {
		
		Optional<MaterialQc> materialQc = materialQcDao.findById(materialQcId);
		
		return materialQcDetailsDao.getMaterialQcDetailsByMaterialQc(materialQc.get());
	}

}
