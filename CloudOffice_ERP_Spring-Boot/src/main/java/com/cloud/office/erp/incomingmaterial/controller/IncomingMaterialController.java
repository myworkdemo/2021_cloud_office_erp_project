package com.cloud.office.erp.incomingmaterial.controller;

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
import com.cloud.office.erp.entity.Vendor;
import com.cloud.office.erp.incomingmaterial.service.IncomingMaterialService;
import com.cloud.office.erp.vendor.service.VendorService;
import com.google.gson.Gson;

@RestController
@CrossOrigin(origins =  "*")
@RequestMapping(value = "/incoming-material")
public class IncomingMaterialController {

	@Autowired
	IncomingMaterialService incomingMaterialService;
	
	@Autowired
	VendorService vendorService; 
	
	@PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public IncomingMaterial addIncomingMaterial(@RequestParam("invoiceAttachmentFile") MultipartFile invoiceAttachmentFile,
			@RequestParam("otherAttachmentFile") MultipartFile otherAttachmentFile, 
			@RequestParam("incomingMaterial") String incomingMaterial_str) {

		IncomingMaterial incomingMaterial = null;
		
		try {
			
			Gson gson = new Gson();
			incomingMaterial = gson.fromJson(incomingMaterial_str, IncomingMaterial.class);
			incomingMaterial.setInvoiceAttachment(invoiceAttachmentFile.getBytes());
			incomingMaterial.setOtherAttachment(otherAttachmentFile.getBytes());
			
			if(incomingMaterial.getVendor() != null) {
				
				Optional<Vendor> vendor = vendorService.getVendorById(incomingMaterial.getVendor().getVendorId());
				
				if(vendor.isPresent()) {
					incomingMaterial.setVendor(vendor.get());
				}
				
			}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("### incomingMaterial : "+incomingMaterial);
			
		
		return  incomingMaterialService.addIncomingMaterial(incomingMaterial);
	}

	@PutMapping(value = "/update")
	public IncomingMaterial updateIncomingMaterial(@RequestBody IncomingMaterial incomingMaterial) {

		return incomingMaterialService.addIncomingMaterial(incomingMaterial);
	}

	@DeleteMapping(value = "/delete/{incomingMaterialId}")
	public void deleteIncomingMaterial(@PathVariable("incomingMaterialId") Long incomingMaterialId) {

		incomingMaterialService.deleteIncomingMaterial(incomingMaterialId);
	}

	@GetMapping(value = "/getAll")
	public List<IncomingMaterial> getAllIncomingMaterial() {

		return incomingMaterialService.getAllIncomingMaterial();
	}

	@GetMapping(value = "/getById/{incomingMaterialId}")
	public IncomingMaterial getIncomingMaterialById(@PathVariable("incomingMaterialId") Long incomingMaterialId) {

		Optional<IncomingMaterial> incomingMaterial = incomingMaterialService.getIncomingMaterialById(incomingMaterialId);
		return incomingMaterial.get();
	}
	
}
