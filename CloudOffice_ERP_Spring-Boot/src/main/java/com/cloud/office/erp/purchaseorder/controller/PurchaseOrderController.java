package com.cloud.office.erp.purchaseorder.controller;

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
import com.cloud.office.erp.entity.PurchaseOrder;
import com.cloud.office.erp.purchaseorder.service.PurchaseOrderService;
import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/purchase-order")
@CrossOrigin("*")
public class PurchaseOrderController {

	@Autowired
	PurchaseOrderService purchaseOrderService;

	@PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public PurchaseOrder addPurchaseOrder(@RequestParam("file") MultipartFile multipartFile,
			@RequestParam("purchaseOrder") String purchaseOrder_str) {

		PurchaseOrder purchaseOrder = null;
	try {
		
		Gson gson = new Gson();
		purchaseOrder = gson.fromJson(purchaseOrder_str, PurchaseOrder.class);
		purchaseOrder.setPoAttachment(multipartFile.getBytes());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("### purchaseOrder : "+purchaseOrder);
		
		return purchaseOrderService.addPurchaseOrder(purchaseOrder);
	}

	@PutMapping(value = "/update")
	public PurchaseOrder updatePurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {

		return purchaseOrderService.updatePurchaseOrder(purchaseOrder);
	}

	@DeleteMapping(value = "/delete/{purchaseOrderId}")
	public void deletePurchaseOrder(@PathVariable("purchaseOrderId") Long purchaseOrderId) {

		purchaseOrderService.deletePurchaseOrder(purchaseOrderId);
	}

	@GetMapping(value = "/getAll")
	public List<PurchaseOrder> getAllPurchaseOrder() {

		return purchaseOrderService.getAllPurchaseOrder();
	}

	@GetMapping(value = "/getById/{purchaseOrderId}")
	public Optional<PurchaseOrder> getPurchaseOrderById(@PathVariable("purchaseOrderId") Long purchaseOrderId) {

		return purchaseOrderService.getPurchaseOrderById(purchaseOrderId);
	}

}
