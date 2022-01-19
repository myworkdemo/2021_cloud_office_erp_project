package com.cloud.office.erp.purchaseorder.service;

import java.util.List;
import java.util.Optional;

import com.cloud.office.erp.entity.PurchaseOrder;

public interface PurchaseOrderService {

	public PurchaseOrder addPurchaseOrder(PurchaseOrder purchaseOrder);
	
	public PurchaseOrder updatePurchaseOrder(PurchaseOrder purchaseOrder);

	public void deletePurchaseOrder(Long purchaseOrderId);

	public List<PurchaseOrder> getAllPurchaseOrder();

	public Optional<PurchaseOrder> getPurchaseOrderById(Long purchaseOrderId);
}
