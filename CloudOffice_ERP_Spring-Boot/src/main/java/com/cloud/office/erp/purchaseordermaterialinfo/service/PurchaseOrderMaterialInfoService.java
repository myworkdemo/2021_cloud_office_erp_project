package com.cloud.office.erp.purchaseordermaterialinfo.service;

import java.util.List;
import java.util.Optional;
import com.cloud.office.erp.entity.PurchaseOrderMaterialInfo;

public interface PurchaseOrderMaterialInfoService {

	public PurchaseOrderMaterialInfo addPurchaseOrderMaterialInfo(PurchaseOrderMaterialInfo purchaseOrderMaterialInfo);

	public PurchaseOrderMaterialInfo updatePurchaseOrderMaterialInfo(PurchaseOrderMaterialInfo purchaseOrderMaterialInfo);

	public void deletePurchaseOrderMaterialInfo(Long purchaseOrderId);

	public List<PurchaseOrderMaterialInfo> getAllPurchaseOrderMaterialInfo();

	public List<PurchaseOrderMaterialInfo> getPurchaseOrderMaterialInfoById(Long purchaseOrderId);

	public List<PurchaseOrderMaterialInfo> getAllPurchaseOrderMaterialInfoBySearch(
			PurchaseOrderMaterialInfo purchaseOrderMaterialInfo);
}
