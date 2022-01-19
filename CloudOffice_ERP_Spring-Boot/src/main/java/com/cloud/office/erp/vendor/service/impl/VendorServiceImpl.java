package com.cloud.office.erp.vendor.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.office.erp.entity.Vendor;
import com.cloud.office.erp.vendor.dao.VendorDao;
import com.cloud.office.erp.vendor.service.VendorService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class VendorServiceImpl implements VendorService {

	@Autowired
	VendorDao vendorDao;

	@Override
	public Vendor addVendor(Vendor vendor) {
		
		if(vendor.getVendorId() != null && vendor.getVendorName().isEmpty() && vendor.getEmailId().isEmpty() 
				&& vendor.getMobileNo().isEmpty() && vendor.getContactPersonName().isEmpty()) {
			
			Vendor vendor2 = vendorDao.findById(vendor.getVendorId()).get();
			if(vendor2 != null) {
				vendor.setVendorName(vendor2.getVendorName());
				vendor.setEmailId(vendor2.getEmailId());
				vendor.setMobileNo(vendor2.getMobileNo());
				vendor.setContactPersonName(vendor2.getContactPersonName());
				vendor.setWebsite(vendor2.getWebsite());
			}
			
		}

		if(vendor != null) {
			vendor = vendorDao.save(vendor);
		}
		
		return vendor;
	}

	@Override
	public Vendor updateVendor(Vendor vendor) {
		
		return vendorDao.save(vendor);
	}
	
	@Override
	public void deleteVendor(Long vendorId) {

	   Optional<Vendor> vendor = vendorDao.findById(vendorId);
		
		vendorDao.delete(vendor.get());
	}

	@Override
	public List<Vendor> getAllVendor() {
		
		return vendorDao.findAll();
	}

	@Override
	public Optional<Vendor> getVendorById(Long vendorId) {
		
		return vendorDao.findById(vendorId);
	}

	@Override
	public List<Vendor> getVendor(Pageable pageable) {
		
		return vendorDao.getVendor(pageable);
	}

	@Override
	public List<Vendor> searchVendor(String searchValue) {
		
		return vendorDao.searchVendor(searchValue);
	}

}
