package com.cloud.office.erp.incomingmaterialinfo.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.office.erp.entity.IncomingMaterial;
import com.cloud.office.erp.entity.IncomingMaterialInfo;
import com.cloud.office.erp.entity.PurchaseOrder;
import com.cloud.office.erp.incomingmaterial.dao.IncomingMaterialDao;
import com.cloud.office.erp.incomingmaterialinfo.dao.IncomingMaterialInfoDao;
import com.cloud.office.erp.incomingmaterialinfo.service.IncomingMaterialInfoService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class IncomingMaterialInfoServiceImpl implements IncomingMaterialInfoService {
	
	@Autowired
	IncomingMaterialInfoDao incomingMaterialInfoDao;
	
	@Autowired
	IncomingMaterialDao incomingMaterialDao;

	@Override
	public IncomingMaterialInfo addIncomingMaterialInfo(IncomingMaterialInfo incomingMaterialInfo) {
		
		IncomingMaterialInfo incomingMaterialInfo_2 = null;
		Optional<IncomingMaterial> incomingMaterial = null;
		
		 if(incomingMaterialInfo.getIncomingMaterial() != null) {
			 incomingMaterial = incomingMaterialDao.findById(incomingMaterialInfo.getIncomingMaterial().getIncomingMaterialId());
		 }		
		
		if(incomingMaterialInfo != null && incomingMaterial.isPresent()) {
		
			if(incomingMaterialInfo.getSrNo_temp() != null && incomingMaterialInfo.getMaterialType_temp() != null &&
				incomingMaterialInfo.getSrNo_temp().length > 0 && incomingMaterialInfo.getMaterialType_temp().length > 0) {
				
				for(int i=0; i < incomingMaterialInfo.getSrNo_temp().length; i++) {
					
					incomingMaterialInfo_2 = new IncomingMaterialInfo(incomingMaterialInfo.getSrNo_temp()[i], incomingMaterialInfo.getMaterialType_temp()[i], incomingMaterialInfo.getMaterialSubType_temp()[i], 
									incomingMaterialInfo.getMaterialName_temp()[i], incomingMaterialInfo.getDescription_temp()[i], incomingMaterialInfo.getAccountCode_temp()[i], 
									incomingMaterialInfo.getUmo_temp()[i], Integer.parseInt(incomingMaterialInfo.getPrQty_temp()[i].toString()), Integer.parseInt(incomingMaterialInfo.getPoQty_temp()[i].toString()), Integer.parseInt(incomingMaterialInfo.getBalanceQty_temp()[i].toString()), 
									Integer.parseInt(incomingMaterialInfo.getDeliveredQty_temp()[i].toString()), Double.parseDouble(incomingMaterialInfo.getUnitRate_temp()[i].toString()), Double.parseDouble(incomingMaterialInfo.getTotalLandingCost_temp()[i].toString()), incomingMaterial.get());
					incomingMaterialInfoDao.save(incomingMaterialInfo_2);
				}
			}
		}
		
		return incomingMaterialInfo_2;
		
	}

	@Override
	public IncomingMaterialInfo updateIncomingMaterialInfo(IncomingMaterialInfo incomingMaterialInfo) {
		
		Optional<IncomingMaterial> incomingMaterial = null;
		List<IncomingMaterialInfo> incomingMaterialInfoList = null;
		
		 if(incomingMaterialInfo.getIncomingMaterial() != null) {
			 incomingMaterial = incomingMaterialDao.findById(incomingMaterialInfo.getIncomingMaterial().getIncomingMaterialId());
		 }		
		
		if(incomingMaterialInfo != null && incomingMaterial.isPresent()) {
		
			if(incomingMaterialInfo.getSrNo_temp() != null && incomingMaterialInfo.getMaterialType_temp() != null &&
				incomingMaterialInfo.getSrNo_temp().length > 0 && incomingMaterialInfo.getMaterialType_temp().length > 0) {
				
				incomingMaterialInfoList = incomingMaterialInfoDao.getIncomingMaterialInfoByIncomingMaterial(incomingMaterialInfo.getIncomingMaterial());
				
				for(int i=0; i < incomingMaterialInfoList.size(); i++) {
					
					Long incomingMaterialInfoId = incomingMaterialInfoList.get(i).getIncomingMaterialInfoId();
					String incomingMaterialInfoId_Array[] = incomingMaterialInfo.getIncomingMaterialInfoId_temp();
					String incomingMaterialInfoId_String = incomingMaterialInfoId.toString();
					
					 boolean contains = Arrays.stream(incomingMaterialInfoId_Array).anyMatch(incomingMaterialInfoId_String::equals);
               	  
               	  if(contains) {
               		  
               		  IncomingMaterialInfo incomingMaterialInfo_2 = new IncomingMaterialInfo(incomingMaterialInfo.getSrNo_temp()[i], incomingMaterialInfo.getMaterialType_temp()[i], incomingMaterialInfo.getMaterialSubType_temp()[i], 
								incomingMaterialInfo.getMaterialName_temp()[i], incomingMaterialInfo.getDescription_temp()[i], incomingMaterialInfo.getAccountCode_temp()[i], 
								incomingMaterialInfo.getUmo_temp()[i], Integer.parseInt(incomingMaterialInfo.getPrQty_temp()[i].toString()), Integer.parseInt(incomingMaterialInfo.getPoQty_temp()[i].toString()), Integer.parseInt(incomingMaterialInfo.getBalanceQty_temp()[i].toString()), 
								Integer.parseInt(incomingMaterialInfo.getDeliveredQty_temp()[i].toString()), Double.parseDouble(incomingMaterialInfo.getUnitRate_temp()[i].toString()), Double.parseDouble(incomingMaterialInfo.getTotalLandingCost_temp()[i].toString()), incomingMaterialInfo.getIncomingMaterial());
               		  
               		incomingMaterialInfo_2.setIncomingMaterialInfoId(incomingMaterialInfoId);
               		incomingMaterialInfoDao.save(incomingMaterialInfo_2);
               		  
               	  }else {
               		  
               		  Optional<IncomingMaterialInfo> incomingMaterialInfo_temp = incomingMaterialInfoDao.findById(incomingMaterialInfoId);
               		  
               		  if(incomingMaterialInfo_temp.isPresent()) {
               			  incomingMaterialInfoDao.delete(incomingMaterialInfo_temp.get());
               		  }
               	  }
					
					
				}
			}
			
		}
		
		return incomingMaterialInfoDao.save(incomingMaterialInfo);
	}

	@Override
	public void deleteIncomingMaterialInfo(Long incomingMaterialInfoId) {
		
		Optional<IncomingMaterialInfo> incomingMaterialInfo = incomingMaterialInfoDao.findById(incomingMaterialInfoId);
		
		incomingMaterialInfoDao.delete(incomingMaterialInfo.get());
	}

	@Override
	public List<IncomingMaterialInfo> getAllIncomingMaterialInfo() {
		
		return incomingMaterialInfoDao.findAll();
	}

	@Override
	public List<IncomingMaterialInfo> getIncomingMaterialInfoById(IncomingMaterial incomingMaterial) {
		
		return incomingMaterialInfoDao.getIncomingMaterialInfoByIncomingMaterial(incomingMaterial);
	}

}

