package com.cloud.office.erp.material.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloud.office.erp.entity.Material;

@Repository
public interface MaterialDao extends JpaRepository<Material, Long> {

	@Query("SELECT MAX(m.serialNo) FROM Material m")
	public Long findSerialNumberOfLastRecord();
	
	@Query("FROM Material m ORDER BY m.materialId ASC")
	public List<Material> getMaterial(Pageable pageable);
	
	@Query("SELECT m FROM Material m WHERE m.materialName LIKE :searchValue%")
	public List<Material> searchMaterial(String searchValue);
}
