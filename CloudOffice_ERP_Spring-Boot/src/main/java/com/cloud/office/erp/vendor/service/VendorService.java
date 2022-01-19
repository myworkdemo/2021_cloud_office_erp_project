package com.cloud.office.erp.vendor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.cloud.office.erp.entity.Vendor;

public interface VendorService {

	public Vendor addVendor(Vendor vendor);
	
	public Vendor updateVendor(Vendor vendor);

	public void deleteVendor(Long vendorId);

	public List<Vendor> getAllVendor();

	public Optional<Vendor> getVendorById(Long vendorId);
	
	public List<Vendor> getVendor(Pageable pageable);
	
	public List<Vendor> searchVendor(String searchValue);
}
