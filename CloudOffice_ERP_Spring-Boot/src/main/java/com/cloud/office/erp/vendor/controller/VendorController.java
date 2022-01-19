package com.cloud.office.erp.vendor.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.office.erp.entity.MemberDetails;
import com.cloud.office.erp.entity.Vendor;
import com.cloud.office.erp.vendor.service.VendorService;

@RestController
@RequestMapping(value = "/vendor")
@CrossOrigin("*")
public class VendorController {

	@Autowired
	VendorService vendorService;

	@PostMapping(value = "/add")
	public Vendor addVendor(@RequestBody Vendor vendor) {

		return vendorService.addVendor(vendor);
	}

	@PutMapping(value = "/update")
	public Vendor updateVendor(@RequestBody Vendor vendor) {

		return vendorService.updateVendor(vendor);
	}

	@DeleteMapping(value = "/delete/{vendorId}")
	public void deleteVendor(@PathVariable("vendorId") Long vendorId) {

		vendorService.deleteVendor(vendorId);
	}

	@GetMapping(value = "/getAll")
	public List<Vendor> getAllVendor() {

		return vendorService.getAllVendor();
	}

	@GetMapping(value = "/getById/{vendorId}")
	public Optional<Vendor> getVendorById(@PathVariable("vendorId") Long vendorId) {

		return vendorService.getVendorById(vendorId);
	}
	
	@GetMapping(value = "/getVendor/{pageNum}/{maxRecords}")
	public List<Vendor> getVendor(@PathVariable("pageNum") int pageNum, @PathVariable("maxRecords") int maxRecords)
	{
		return vendorService.getVendor(PageRequest.of(pageNum-1, maxRecords));
	}
	
	@GetMapping(value = "/searchVendor/{searchValue}")
	public List<Vendor> searchVendor(@PathVariable("searchValue") String searchValue)
	{
		return vendorService.searchVendor(searchValue);
	}
}
