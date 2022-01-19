package com.cloud.office.erp.materialqcdetails.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.office.erp.entity.MaterialQc;
import com.cloud.office.erp.entity.MaterialQcDetails;

@Repository
@Transactional
public interface MaterialQcDetailsDao extends JpaRepository<MaterialQcDetails, Long> {
	
	@Query("FROM MaterialQcDetails WHERE materialQc = :materialQc")
	public List<MaterialQcDetails> getMaterialQcDetailsByMaterialQc(MaterialQc materialQc);

	@Modifying
	@Query("DELETE FROM MaterialQcDetails WHERE materialQc = :materialQc")
	public void deleteMaterialQcDetails(MaterialQc materialQc);
	
}
