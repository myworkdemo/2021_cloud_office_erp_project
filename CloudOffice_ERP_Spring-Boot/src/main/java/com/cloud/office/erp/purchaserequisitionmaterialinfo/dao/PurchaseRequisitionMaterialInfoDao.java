package com.cloud.office.erp.purchaserequisitionmaterialinfo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloud.office.erp.entity.PurchaseRequisitionInfo;
import com.cloud.office.erp.entity.PurchaseRequisitionMaterialInfo;

@Repository
public interface PurchaseRequisitionMaterialInfoDao extends JpaRepository<PurchaseRequisitionMaterialInfo, Long> {

	@Query("FROM PurchaseRequisitionMaterialInfo WHERE purchaseRequisitionInfo = :purchaseRequisitionInfo")
	public List<PurchaseRequisitionMaterialInfo> getPurchaseRequisitionMaterialInfoByPurchaseRequisitionInfo(PurchaseRequisitionInfo purchaseRequisitionInfo);
}
