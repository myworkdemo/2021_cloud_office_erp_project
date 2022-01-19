package com.cloud.office.erp.purchaseordermaterialinfo.controller;

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
import com.cloud.office.erp.entity.PurchaseOrderMaterialInfo;
import com.cloud.office.erp.purchaseordermaterialinfo.service.PurchaseOrderMaterialInfoService;

@RestController
@RequestMapping(value = "/purchase-order-material-info")
@CrossOrigin("*")
public class PurchaseOrderMaterialInfoController {

	@Autowired
	PurchaseOrderMaterialInfoService purchaseOrderMaterialInfoMaterialInfoService;

	@PostMapping(value = "/add")
	public PurchaseOrderMaterialInfo addPurchaseOrderMaterialInfo(@RequestBody PurchaseOrderMaterialInfo purchaseOrderMaterialInfo) {

		return purchaseOrderMaterialInfoMaterialInfoService.addPurchaseOrderMaterialInfo(purchaseOrderMaterialInfo);
	}

	@PutMapping(value = "/update")
	public PurchaseOrderMaterialInfo updatePurchaseOrderMaterialInfo(@RequestBody PurchaseOrderMaterialInfo purchaseOrderMaterialInfo) {

		return purchaseOrderMaterialInfoMaterialInfoService.updatePurchaseOrderMaterialInfo(purchaseOrderMaterialInfo);
	}

	@DeleteMapping(value = "/delete/{purchaseOrderId}")
	public void deletePurchaseOrderMaterialInfo(@PathVariable("purchaseOrderId") Long purchaseOrderId) {

		purchaseOrderMaterialInfoMaterialInfoService.deletePurchaseOrderMaterialInfo(purchaseOrderId);
	}

	@GetMapping(value = "/getAll")
	public List<PurchaseOrderMaterialInfo> getAllPurchaseOrderMaterialInfo() {

		return purchaseOrderMaterialInfoMaterialInfoService.getAllPurchaseOrderMaterialInfo();
	}

	@GetMapping(value = "/getById/{purchaseOrderId}")
	public List<PurchaseOrderMaterialInfo> getPurchaseOrderMaterialInfoById(@PathVariable("purchaseOrderId") Long purchaseOrderId) {

		return purchaseOrderMaterialInfoMaterialInfoService.getPurchaseOrderMaterialInfoById(purchaseOrderId);
	}
	
	@PostMapping(value = "/getBySearchValues")
	public List<PurchaseOrderMaterialInfo> getAllPurchaseOrderMaterialInfoBySearch(@RequestBody PurchaseOrderMaterialInfo purchaseOrderMaterialInfo) {

		return purchaseOrderMaterialInfoMaterialInfoService.getAllPurchaseOrderMaterialInfoBySearch(purchaseOrderMaterialInfo);
	}
	
}
