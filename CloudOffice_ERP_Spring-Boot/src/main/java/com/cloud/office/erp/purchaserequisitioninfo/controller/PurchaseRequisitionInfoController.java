package com.cloud.office.erp.purchaserequisitioninfo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.office.erp.entity.IncomingMaterial;
import com.cloud.office.erp.entity.PurchaseRequisitionInfo;
import com.cloud.office.erp.purchaserequisitioninfo.service.PurchaseRequisitionInfoService;
import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/purchase-requisition-info")
@CrossOrigin("*")
public class PurchaseRequisitionInfoController {
	
	@Autowired
	PurchaseRequisitionInfoService purchaseRequisitionInfoService;
	
	@PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public PurchaseRequisitionInfo addPurchaseRequisitionInfo(@RequestParam("documentFile") MultipartFile document,
			@RequestParam("purchaseRequisitionInfo") String purchaseRequisitionInfo_str) {
		
		PurchaseRequisitionInfo purchaseRequisitionInfo = null;
		try {
			
			Gson gson = new Gson();
			purchaseRequisitionInfo = gson.fromJson(purchaseRequisitionInfo_str, PurchaseRequisitionInfo.class);
			purchaseRequisitionInfo.setDocument(document.getBytes());
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("### purchaseRequisitionInfo : "+purchaseRequisitionInfo);
			

		return purchaseRequisitionInfoService.addPurchaseRequisitionInfo(purchaseRequisitionInfo);
	}

	@PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public PurchaseRequisitionInfo updatePurchaseRequisitionInfo(@RequestParam("documentFile") MultipartFile document,
			@RequestParam("purchaseRequisitionInfo") String purchaseRequisitionInfo_str) {
		
		PurchaseRequisitionInfo purchaseRequisitionInfo = null;
		try {
			
			Gson gson = new Gson();
			purchaseRequisitionInfo = gson.fromJson(purchaseRequisitionInfo_str, PurchaseRequisitionInfo.class);
			purchaseRequisitionInfo.setDocument(document.getBytes());
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("### purchaseRequisitionInfo : "+purchaseRequisitionInfo);
			

		return purchaseRequisitionInfoService.addPurchaseRequisitionInfo(purchaseRequisitionInfo);
	}

	@DeleteMapping(value = "/delete/{purchaseRequisitionInfoId}")
	public void deletePurchaseRequisitionInfo(@PathVariable("purchaseRequisitionInfoId") Long purchaseRequisitionInfoId) {

		purchaseRequisitionInfoService.deletePurchaseRequisitionInfo(purchaseRequisitionInfoId);
	}

	@GetMapping(value = "/getAll")
	public List<PurchaseRequisitionInfo> getAllPurchaseRequisitionInfo() {

		return purchaseRequisitionInfoService.getAllPurchaseRequisitionInfo();
	}

	@GetMapping(value = "/getById/{purchaseRequisitionInfoId}")
	public Optional<PurchaseRequisitionInfo> getPurchaseRequisitionInfoById(@PathVariable("purchaseRequisitionInfoId") Long purchaseRequisitionInfoId) {

		return purchaseRequisitionInfoService.getPurchaseRequisitionInfoById(purchaseRequisitionInfoId);
	}
	

}
