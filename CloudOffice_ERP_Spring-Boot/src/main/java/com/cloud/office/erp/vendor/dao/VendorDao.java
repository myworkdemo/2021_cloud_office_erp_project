package com.cloud.office.erp.vendor.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloud.office.erp.entity.UserRole;
import com.cloud.office.erp.entity.Vendor;

@Repository
public interface VendorDao extends JpaRepository<Vendor, Long>{

	@Query("FROM Vendor v ORDER BY v.vendorId ASC")
	public List<Vendor> getVendor(Pageable pageable);
	
	@Query("SELECT ur FROM Vendor ur WHERE ur.vendorName LIKE :searchValue%")
	public List<Vendor> searchVendor(String searchValue);
	
}
