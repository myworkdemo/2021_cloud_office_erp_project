package com.cloud.office.erp.incomingmaterial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloud.office.erp.entity.IncomingMaterial;
import com.cloud.office.erp.entity.IncomingMaterialJsonEntity;

@Repository
public interface IncomingMaterialDao extends JpaRepository<IncomingMaterial, Long>{

	@Query("SELECT new com.cloud.office.erp.entity.IncomingMaterialJsonEntity(im.incomingMaterialId, im.workName, im.departmentName, "
			+ "im.incomingRegisterCode, im.incomingRegisterDate, im.supplierName, im.suplierInvoiceNo, po.poCode, po.poDate, pomi.accountCode, "
			+ "pomi.materialType, pomi.materialSubType, pomi.materialName, pomi.description, pomi.umo) "
			+ "FROM IncomingMaterial im "
			+ "INNER JOIN im.vendor v ON v.vendorId = im.vendor "
			+ "INNER JOIN PurchaseOrder po ON po.vendor = v.vendorId "
			+ "INNER JOIN PurchaseOrderMaterialInfo pomi ON pomi.purchaseOrder = po.purchaseOrderId")
	public List<IncomingMaterialJsonEntity> getAllRecords();
}
