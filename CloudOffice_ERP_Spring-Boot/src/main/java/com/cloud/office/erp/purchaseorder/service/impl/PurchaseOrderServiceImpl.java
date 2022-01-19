package com.cloud.office.erp.purchaseorder.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.office.erp.entity.Material;
import com.cloud.office.erp.entity.PurchaseOrder;
import com.cloud.office.erp.entity.Vendor;
import com.cloud.office.erp.material.service.MaterialService;
import com.cloud.office.erp.purchaseorder.dao.PurchaseOrderDao;
import com.cloud.office.erp.purchaseorder.service.PurchaseOrderService;
import com.cloud.office.erp.vendor.service.VendorService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	PurchaseOrderDao purchaseOrderDao;

	@Autowired
	MaterialService materialService;
	
	@Autowired
	VendorService vendorService;

	@Override
	public PurchaseOrder addPurchaseOrder(PurchaseOrder purchaseOrder) {
		
		
		return purchaseOrderDao.save(purchaseOrder);
	}

	@Override
	public PurchaseOrder updatePurchaseOrder(PurchaseOrder purchaseOrder) {
		return purchaseOrderDao.save(purchaseOrder);
	}

	@Override
	public void deletePurchaseOrder(Long purchaseOrderId) {
		Optional<PurchaseOrder> purchaseOrder = purchaseOrderDao.findById(purchaseOrderId);
		purchaseOrderDao.delete(purchaseOrder.get());
	}

	@Override
	public List<PurchaseOrder> getAllPurchaseOrder() {
		return purchaseOrderDao.findAll();
	}

	@Override
	public Optional<PurchaseOrder> getPurchaseOrderById(Long purchaseOrderId) {
		return purchaseOrderDao.findById(purchaseOrderId);
	}

}
