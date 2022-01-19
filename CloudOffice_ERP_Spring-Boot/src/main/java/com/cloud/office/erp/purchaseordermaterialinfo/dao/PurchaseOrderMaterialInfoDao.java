package com.cloud.office.erp.purchaseordermaterialinfo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.office.erp.entity.PurchaseOrder;
import com.cloud.office.erp.entity.PurchaseOrderMaterialInfo;

@Repository
@Transactional
public interface PurchaseOrderMaterialInfoDao extends JpaRepository<PurchaseOrderMaterialInfo, Long> {

	@Query("FROM PurchaseOrderMaterialInfo WHERE purchaseOrder = :purchaseOrder")
	public List<PurchaseOrderMaterialInfo> getPurchaseOrderMaterialInfoByPurchaseOrder(PurchaseOrder purchaseOrder);
	
	@Query("FROM PurchaseOrderMaterialInfo pomi WHERE (:materialType IS NULL OR pomi.materialType LIKE %:materialType%) AND (:materialSubType IS NULL OR pomi.materialSubType LIKE %:materialSubType%)"
			+ " AND (:accountCode IS NULL OR pomi.accountCode LIKE %:accountCode%) AND (:materialName IS NULL OR pomi.materialName LIKE %:materialName%)")
	public List<PurchaseOrderMaterialInfo> getAllPurchaseOrderMaterialInfoBySearch(String materialType, String materialSubType, String accountCode, String materialName);

	@Modifying
	@Query("DELETE FROM PurchaseOrderMaterialInfo WHERE purchaseOrder = :purchaseOrder")
	public void deletePurchaseOrderMaterialInfo(PurchaseOrder purchaseOrder);

}
