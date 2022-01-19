package com.cloud.office.erp.incomingmaterialinfo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloud.office.erp.entity.IncomingMaterial;
import com.cloud.office.erp.entity.IncomingMaterialInfo;

@Repository
public interface IncomingMaterialInfoDao extends JpaRepository<IncomingMaterialInfo, Long> {

	@Query("FROM IncomingMaterialInfo WHERE incomingMaterial = :incomingMaterial")
	public List<IncomingMaterialInfo> getIncomingMaterialInfoByIncomingMaterial(IncomingMaterial incomingMaterial);
}
