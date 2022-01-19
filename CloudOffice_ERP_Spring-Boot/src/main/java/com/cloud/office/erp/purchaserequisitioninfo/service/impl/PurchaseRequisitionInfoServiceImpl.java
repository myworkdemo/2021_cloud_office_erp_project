package com.cloud.office.erp.purchaserequisitioninfo.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.cloud.office.erp.entity.PurchaseRequisitionInfo;
import com.cloud.office.erp.purchaserequisitioninfo.dao.PurchaseRequisitionInfoDao;
import com.cloud.office.erp.purchaserequisitioninfo.service.PurchaseRequisitionInfoService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class PurchaseRequisitionInfoServiceImpl implements PurchaseRequisitionInfoService{
	
	@Autowired
	PurchaseRequisitionInfoDao purchaseRequisitionInfoDao;

	@Override
	public PurchaseRequisitionInfo addPurchaseRequisitionInfo(PurchaseRequisitionInfo purchaseRequisitionInfo) {
		
		return purchaseRequisitionInfoDao.save(purchaseRequisitionInfo);
	}

	@Override
	public PurchaseRequisitionInfo updatePurchaseRequisitionInfo(PurchaseRequisitionInfo purchaseRequisitionInfo) {
		return purchaseRequisitionInfoDao.save(purchaseRequisitionInfo);
	}

	@Override
	public void deletePurchaseRequisitionInfo(Long purchaseRequisitionInfoId) {
		
		Optional<PurchaseRequisitionInfo> purchaseRequisitionInfo = purchaseRequisitionInfoDao.findById(purchaseRequisitionInfoId);
		purchaseRequisitionInfoDao.delete(purchaseRequisitionInfo.get());
	}

	@Override
	public List<PurchaseRequisitionInfo> getAllPurchaseRequisitionInfo() {
		return purchaseRequisitionInfoDao.findAll();
	}

	@Override
	public Optional<PurchaseRequisitionInfo> getPurchaseRequisitionInfoById(Long purchaseRequisitionInfoId) {
		return purchaseRequisitionInfoDao.findById(purchaseRequisitionInfoId);
	}

}
