package com.cloud.office.erp.purchaseordermaterialinfo.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.office.erp.entity.PurchaseOrder;
import com.cloud.office.erp.entity.PurchaseOrderMaterialInfo;
import com.cloud.office.erp.purchaseorder.dao.PurchaseOrderDao;
import com.cloud.office.erp.purchaseordermaterialinfo.dao.PurchaseOrderMaterialInfoDao;
import com.cloud.office.erp.purchaseordermaterialinfo.service.PurchaseOrderMaterialInfoService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class PurchaseOrderMaterialInfoServiceImpl implements PurchaseOrderMaterialInfoService{

	@Autowired
	PurchaseOrderMaterialInfoDao purchaseOrderMaterialInfoDao;
	
	@Autowired
	PurchaseOrderDao purchaseOrderDao;
	
	@Override
	public PurchaseOrderMaterialInfo addPurchaseOrderMaterialInfo(PurchaseOrderMaterialInfo purchaseOrderMaterialInfo) {
		
		PurchaseOrderMaterialInfo materialInfo = null;
		Optional<PurchaseOrder> purchaseOrder = null;
		
		 if(purchaseOrderMaterialInfo.getPurchaseOrder() != null) {
			 purchaseOrder = purchaseOrderDao.findById(purchaseOrderMaterialInfo.getPurchaseOrder().getPurchaseOrderId());
		 }		
		
		if(purchaseOrderMaterialInfo != null && purchaseOrder.isPresent()) {
		
			if(purchaseOrderMaterialInfo.getSrNo_temp() != null && purchaseOrderMaterialInfo.getMaterialType_temp() != null &&
				purchaseOrderMaterialInfo.getSrNo_temp().length > 0 && purchaseOrderMaterialInfo.getMaterialType_temp().length > 0) {
				
				for(int i=0; i < purchaseOrderMaterialInfo.getSrNo_temp().length; i++) {
					
					materialInfo = new PurchaseOrderMaterialInfo(purchaseOrderMaterialInfo.getSrNo_temp()[i], purchaseOrderMaterialInfo.getMaterialType_temp()[i], purchaseOrderMaterialInfo.getMaterialSubType_temp()[i], purchaseOrderMaterialInfo.getAccountCode_temp()[i], 
							purchaseOrderMaterialInfo.getMaterialName_temp()[i], purchaseOrderMaterialInfo.getDescription_temp()[i], purchaseOrderMaterialInfo.getUmo_temp()[i], purchaseOrderMaterialInfo.getPrQty_temp()[i], purchaseOrderMaterialInfo.getRequiredDate_temp()[i], purchaseOrderMaterialInfo.getPoQty_temp()[i], purchaseOrderMaterialInfo.getBalanceQty_temp()[i], 
							purchaseOrderMaterialInfo.getUnitPrice_temp()[i], purchaseOrderMaterialInfo.getAmount_temp()[i], purchaseOrderMaterialInfo.getRemark_temp()[i], purchaseOrder.get());
				
					purchaseOrderMaterialInfoDao.save(materialInfo);
				}
			}
		}
		
		return materialInfo;
	}

	@Override
	public PurchaseOrderMaterialInfo updatePurchaseOrderMaterialInfo(PurchaseOrderMaterialInfo purchaseOrderMaterialInfo) {
		
		Optional<PurchaseOrder> purchaseOrder = null;
		List<PurchaseOrderMaterialInfo> purchaseOrderMaterialInfoList = null;
		
		if(purchaseOrderMaterialInfo.getPurchaseOrder() != null) {
			purchaseOrder = purchaseOrderDao.findById(purchaseOrderMaterialInfo.getPurchaseOrder().getPurchaseOrderId());
		}
		
		if(purchaseOrderMaterialInfo != null && purchaseOrder.isPresent()) {
			
			if(purchaseOrderMaterialInfo.getPurchaseOrderMaterialInfoId_temp() != null 
					&& purchaseOrderMaterialInfo.getPurchaseOrderMaterialInfoId_temp().length > 0) {
				
				purchaseOrderMaterialInfoList = purchaseOrderMaterialInfoDao.getPurchaseOrderMaterialInfoByPurchaseOrder(purchaseOrderMaterialInfo.getPurchaseOrder());
				
				for(int i=0; i < purchaseOrderMaterialInfoList.size(); i++) {
					
					Long purchaseOrderMaterialInfoId = purchaseOrderMaterialInfoList.get(i).getPurchaseOrderMaterialInfoId();
					String purchaseOrderMaterialInfoId_Array[] = purchaseOrderMaterialInfo.getPurchaseOrderMaterialInfoId_temp();
					String purchaseOrderMaterialInfoId_String = purchaseOrderMaterialInfoId.toString();
					
					 boolean contains = Arrays.stream(purchaseOrderMaterialInfoId_Array).anyMatch(purchaseOrderMaterialInfoId_String::equals);
               	  
               	  if(contains) {
               		  
               		  PurchaseOrderMaterialInfo purchaseOrderMaterialInfo2 = new PurchaseOrderMaterialInfo(purchaseOrderMaterialInfo.getSrNo_temp()[i], purchaseOrderMaterialInfo.getMaterialType_temp()[i], 
               				purchaseOrderMaterialInfo.getMaterialSubType_temp()[i], purchaseOrderMaterialInfo.getAccountCode_temp()[i], purchaseOrderMaterialInfo.getMaterialName_temp()[i], 
               				purchaseOrderMaterialInfo.getDescription_temp()[i], purchaseOrderMaterialInfo.getUmo_temp()[i], purchaseOrderMaterialInfo.getPrQty_temp()[i], purchaseOrderMaterialInfo.getRequiredDate_temp()[i], 
               				purchaseOrderMaterialInfo.getPoQty_temp()[i], purchaseOrderMaterialInfo.getBalanceQty_temp()[i], purchaseOrderMaterialInfo.getUnitPrice_temp()[i], purchaseOrderMaterialInfo.getAmount_temp()[i], 
               				purchaseOrderMaterialInfo.getRemark_temp()[i], purchaseOrder.get());
               		  
               		purchaseOrderMaterialInfo2.setPurchaseOrderMaterialInfoId(purchaseOrderMaterialInfoId);
               		purchaseOrderMaterialInfoDao.save(purchaseOrderMaterialInfo2);
               		  
               	  }else {
               		  
               		  Optional<PurchaseOrderMaterialInfo> purchaseOrderMaterialInfo_temp = purchaseOrderMaterialInfoDao.findById(purchaseOrderMaterialInfoId);
               		  
               		  if(purchaseOrderMaterialInfo_temp.isPresent()) {
               			  purchaseOrderMaterialInfoDao.delete(purchaseOrderMaterialInfo_temp.get());
               		  }
               	  }
					
					
				}
			}
			
		}
		
		return purchaseOrderMaterialInfoDao.save(purchaseOrderMaterialInfo);
	}

	@Override
	public void deletePurchaseOrderMaterialInfo(Long purchaseOrderId) {
		
		Optional<PurchaseOrder> purchaseOrder = purchaseOrderDao.findById(purchaseOrderId);
		
		if(purchaseOrder.isPresent()) {
			purchaseOrderMaterialInfoDao.deletePurchaseOrderMaterialInfo(purchaseOrder.get());
			purchaseOrderDao.delete(purchaseOrder.get());
		}
	}

	@Override
	public List<PurchaseOrderMaterialInfo> getAllPurchaseOrderMaterialInfo() {
		return purchaseOrderMaterialInfoDao.findAll();
	}

	@Override
	public List<PurchaseOrderMaterialInfo> getPurchaseOrderMaterialInfoById(Long purchaseOrderId) {
		
		Optional<PurchaseOrder> purchaseOrder = purchaseOrderDao.findById(purchaseOrderId);
		
		return purchaseOrderMaterialInfoDao.getPurchaseOrderMaterialInfoByPurchaseOrder(purchaseOrder.get());
	}

	@Override
	public List<PurchaseOrderMaterialInfo> getAllPurchaseOrderMaterialInfoBySearch(PurchaseOrderMaterialInfo purchaseOrderMaterialInfo) {
		
		String materialType = (!purchaseOrderMaterialInfo.getMaterialType().isEmpty())? purchaseOrderMaterialInfo.getMaterialType() : null;
		String materialSubType = (!purchaseOrderMaterialInfo.getMaterialSubType().isEmpty())? purchaseOrderMaterialInfo.getMaterialSubType() : null;
		
		String accountCode = (!purchaseOrderMaterialInfo.getAccountCode().isEmpty())? purchaseOrderMaterialInfo.getAccountCode() : null;
		String materialName = (!purchaseOrderMaterialInfo.getMaterialName().isEmpty())? purchaseOrderMaterialInfo.getMaterialName() : null;
		
		return purchaseOrderMaterialInfoDao.getAllPurchaseOrderMaterialInfoBySearch(materialType, materialSubType, accountCode, materialName);
	}

}
