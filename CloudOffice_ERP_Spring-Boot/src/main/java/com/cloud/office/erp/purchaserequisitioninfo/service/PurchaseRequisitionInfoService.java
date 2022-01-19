package com.cloud.office.erp.purchaserequisitioninfo.service;

import java.util.List;
import java.util.Optional;
import com.cloud.office.erp.entity.PurchaseRequisitionInfo;

public interface PurchaseRequisitionInfoService {

	public PurchaseRequisitionInfo addPurchaseRequisitionInfo(PurchaseRequisitionInfo purchaseRequisitionInfo);

	public PurchaseRequisitionInfo updatePurchaseRequisitionInfo(PurchaseRequisitionInfo purchaseRequisitionInfo);

	public void deletePurchaseRequisitionInfo(Long purchaseRequisitionInfoId);

	public List<PurchaseRequisitionInfo> getAllPurchaseRequisitionInfo();

	public Optional<PurchaseRequisitionInfo> getPurchaseRequisitionInfoById(Long purchaseRequisitionInfoId);
}
