package com.cloud.office.erp.purchaserequisitionmaterialinfo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cloud.office.erp.entity.PurchaseRequisitionMaterialInfo;
import com.cloud.office.erp.purchaserequisitionmaterialinfo.service.PurchaseRequisitionMaterialInfoService;

@RestController
@RequestMapping(value = "/purchase-requisition-material-info")
@CrossOrigin("*")
public class PurchaseRequisitionMaterialInfoController {
	
	@Autowired
	PurchaseRequisitionMaterialInfoService purchaseRequisitionMaterialInfoService;
	
	@PostMapping(value = "/add")
	public PurchaseRequisitionMaterialInfo addPurchaseRequisitionMaterialInfo(@RequestBody PurchaseRequisitionMaterialInfo purchaseRequisitionMaterialInfo) {

		return purchaseRequisitionMaterialInfoService.addPurchaseRequisitionMaterialInfo(purchaseRequisitionMaterialInfo);
	}

	@PutMapping(value = "/update")
	public PurchaseRequisitionMaterialInfo updatePurchaseRequisitionMaterialInfo(@RequestBody PurchaseRequisitionMaterialInfo purchaseRequisitionMaterialInfo) {

		return purchaseRequisitionMaterialInfoService.updatePurchaseRequisitionMaterialInfo(purchaseRequisitionMaterialInfo);
	}

	@DeleteMapping(value = "/delete/{purchaseRequisitionMaterialInfoId}")
	public void deletePurchaseRequisitionMaterialInfo(@PathVariable("purchaseRequisitionMaterialInfoId") Long purchaseRequisitionMaterialInfoId) {

		purchaseRequisitionMaterialInfoService.deletePurchaseRequisitionMaterialInfo(purchaseRequisitionMaterialInfoId);
	}

	@GetMapping(value = "/getAll")
	public List<PurchaseRequisitionMaterialInfo> getAllPurchaseRequisitionMaterialInfo() {

		return purchaseRequisitionMaterialInfoService.getAllPurchaseRequisitionMaterialInfo();
	}

	@GetMapping(value = "/getById/{purchaseRequisitionMaterialInfoId}")
	public Optional<PurchaseRequisitionMaterialInfo> getPurchaseRequisitionMaterialInfoById(@PathVariable("purchaseRequisitionMaterialInfoId") Long purchaseRequisitionMaterialInfoId) {

		return purchaseRequisitionMaterialInfoService.getPurchaseRequisitionMaterialInfoById(purchaseRequisitionMaterialInfoId);
	}
	

}
