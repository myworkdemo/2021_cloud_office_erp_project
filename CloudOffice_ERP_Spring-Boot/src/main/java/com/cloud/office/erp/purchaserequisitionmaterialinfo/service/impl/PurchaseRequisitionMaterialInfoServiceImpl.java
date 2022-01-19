package com.cloud.office.erp.purchaserequisitionmaterialinfo.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.office.erp.entity.PurchaseOrder;
import com.cloud.office.erp.entity.PurchaseOrderMaterialInfo;
import com.cloud.office.erp.entity.PurchaseRequisitionInfo;
import com.cloud.office.erp.entity.PurchaseRequisitionMaterialInfo;
import com.cloud.office.erp.purchaserequisitioninfo.dao.PurchaseRequisitionInfoDao;
import com.cloud.office.erp.purchaserequisitionmaterialinfo.dao.PurchaseRequisitionMaterialInfoDao;
import com.cloud.office.erp.purchaserequisitionmaterialinfo.service.PurchaseRequisitionMaterialInfoService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class PurchaseRequisitionMaterialInfoServiceImpl implements PurchaseRequisitionMaterialInfoService{
	
	@Autowired
	PurchaseRequisitionMaterialInfoDao purchaseRequisitionMaterialInfoDao;

	@Autowired
	PurchaseRequisitionInfoDao purchaseRequisitionInfoDao;

	@Override
	public PurchaseRequisitionMaterialInfo addPurchaseRequisitionMaterialInfo(PurchaseRequisitionMaterialInfo purchaseRequisitionMaterialInfo) {
		
		PurchaseRequisitionMaterialInfo materialInfo = null;
		Optional<PurchaseRequisitionInfo> purchaseRequisitionInfo = null;
		
		 if(purchaseRequisitionMaterialInfo.getPurchaseRequisitionInfo() != null) {
			 Long purchaseRequisitionInfoId = purchaseRequisitionMaterialInfo.getPurchaseRequisitionInfo().getPurchaseRequisitionInfoId();
			 purchaseRequisitionInfo = purchaseRequisitionInfoDao.findById(purchaseRequisitionInfoId);
		 }		
		
		if(purchaseRequisitionMaterialInfo != null && purchaseRequisitionInfo.isPresent()) {
		
			if(purchaseRequisitionMaterialInfo.getSrNo_temp() != null && purchaseRequisitionMaterialInfo.getMaterialType_temp() != null &&
					purchaseRequisitionMaterialInfo.getSrNo_temp().length > 0 && purchaseRequisitionMaterialInfo.getMaterialType_temp().length > 0) {
				
				for(int i=0; i < purchaseRequisitionMaterialInfo.getSrNo_temp().length; i++) {
					
					materialInfo = new PurchaseRequisitionMaterialInfo(purchaseRequisitionMaterialInfo.getSrNo_temp()[i], purchaseRequisitionMaterialInfo.getMaterialType_temp()[i], purchaseRequisitionMaterialInfo.getMaterialSubType_temp()[i], 
							purchaseRequisitionMaterialInfo.getMaterialName_temp()[i], purchaseRequisitionMaterialInfo.getDescription_temp()[i], purchaseRequisitionMaterialInfo.getAccountCode_temp()[i], purchaseRequisitionMaterialInfo.getUmo_temp()[i], purchaseRequisitionMaterialInfo.getRequiredQty_temp()[i], purchaseRequisitionMaterialInfo.getStock_temp()[i], 
							purchaseRequisitionMaterialInfo.getDeliveryRequirement_temp()[i], purchaseRequisitionMaterialInfo.getActualCostPerUnit_temp()[i], purchaseRequisitionMaterialInfo.getActualTotal_temp()[i], purchaseRequisitionMaterialInfo.getSellingPricePerUnit_temp()[i], purchaseRequisitionMaterialInfo.getPrRemark_temp()[i], purchaseRequisitionInfo.get());
				
					purchaseRequisitionMaterialInfoDao.save(materialInfo);
				}
			}
		}
		
		return materialInfo;
	}

	@Override
	public PurchaseRequisitionMaterialInfo updatePurchaseRequisitionMaterialInfo(PurchaseRequisitionMaterialInfo purchaseRequisitionMaterialInfo) {
		
		Optional<PurchaseRequisitionInfo> purchaseRequisitionInfo = null;
		List<PurchaseRequisitionMaterialInfo> purchaseRequisitionMaterialInfoList = null;
		
		if(purchaseRequisitionMaterialInfo.getPurchaseRequisitionInfo() != null) {
			purchaseRequisitionInfo = purchaseRequisitionInfoDao.findById(purchaseRequisitionMaterialInfo.getPurchaseRequisitionInfo().getPurchaseRequisitionInfoId());
		}
		
		if(purchaseRequisitionInfo != null && purchaseRequisitionInfo.isPresent()) {
			
			if(purchaseRequisitionMaterialInfo.getPrMaterialInfoId_temp() != null 
					&& purchaseRequisitionMaterialInfo.getPrMaterialInfoId_temp().length > 0) {
				
				purchaseRequisitionMaterialInfoList = purchaseRequisitionMaterialInfoDao.getPurchaseRequisitionMaterialInfoByPurchaseRequisitionInfo(purchaseRequisitionMaterialInfo.getPurchaseRequisitionInfo());
				
				for(int i=0; i < purchaseRequisitionMaterialInfoList.size(); i++) {
					
					Long purchaseRequisitionMaterialInfoId = purchaseRequisitionMaterialInfoList.get(i).getPrMaterialInfoId();
					String purchaseRequisitionMaterialInfoId_Array[] = purchaseRequisitionMaterialInfo.getPrMaterialInfoId_temp();
					String purchaseRequisitionMaterialInfoId_String = purchaseRequisitionMaterialInfoId.toString();
					
					 boolean contains = Arrays.stream(purchaseRequisitionMaterialInfoId_Array).anyMatch(purchaseRequisitionMaterialInfoId_String::equals);
               	  
               	  if(contains) {
               		  
               		PurchaseRequisitionMaterialInfo purchaseRequisitionMaterialInfo2 = new PurchaseRequisitionMaterialInfo(purchaseRequisitionMaterialInfo.getSrNo_temp()[i], purchaseRequisitionMaterialInfo.getMaterialType_temp()[i], purchaseRequisitionMaterialInfo.getMaterialSubType_temp()[i], 
  							purchaseRequisitionMaterialInfo.getMaterialName_temp()[i], purchaseRequisitionMaterialInfo.getDescription_temp()[i], purchaseRequisitionMaterialInfo.getAccountCode_temp()[i], purchaseRequisitionMaterialInfo.getUmo_temp()[i], purchaseRequisitionMaterialInfo.getRequiredQty_temp()[i], purchaseRequisitionMaterialInfo.getStock_temp()[i], 
  							purchaseRequisitionMaterialInfo.getDeliveryRequirement_temp()[i], purchaseRequisitionMaterialInfo.getActualCostPerUnit_temp()[i], purchaseRequisitionMaterialInfo.getActualTotal_temp()[i], purchaseRequisitionMaterialInfo.getSellingPricePerUnit_temp()[i], purchaseRequisitionMaterialInfo.getPrRemark_temp()[i], purchaseRequisitionInfo.get());
  				
               		  
               		purchaseRequisitionMaterialInfo2.setPrMaterialInfoId(purchaseRequisitionMaterialInfoId);
               		purchaseRequisitionMaterialInfoDao.save(purchaseRequisitionMaterialInfo2);
               		  
               	  }else {
               		  
               		  Optional<PurchaseRequisitionMaterialInfo> purchaseRequisitionMaterialInfo_temp = purchaseRequisitionMaterialInfoDao.findById(purchaseRequisitionMaterialInfoId);
               		  
               		  if(purchaseRequisitionMaterialInfo_temp.isPresent()) {
               			  purchaseRequisitionMaterialInfoDao.delete(purchaseRequisitionMaterialInfo_temp.get());
               		  }
               	  }
					
					
				}
			}
			
		}
		
		return purchaseRequisitionMaterialInfoDao.save(purchaseRequisitionMaterialInfo);
	}

	@Override
	public void deletePurchaseRequisitionMaterialInfo(Long purchaseRequisitionMaterialInfoId) {
		
		Optional<PurchaseRequisitionMaterialInfo> purchaseRequisitionMaterialInfo = purchaseRequisitionMaterialInfoDao.findById(purchaseRequisitionMaterialInfoId);
		purchaseRequisitionMaterialInfoDao.delete(purchaseRequisitionMaterialInfo.get());
	}

	@Override
	public List<PurchaseRequisitionMaterialInfo> getAllPurchaseRequisitionMaterialInfo() {
		return purchaseRequisitionMaterialInfoDao.findAll();
	}

	@Override
	public Optional<PurchaseRequisitionMaterialInfo> getPurchaseRequisitionMaterialInfoById(Long purchaseRequisitionMaterialInfoId) {
		return purchaseRequisitionMaterialInfoDao.findById(purchaseRequisitionMaterialInfoId);
	}

}
