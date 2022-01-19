package com.cloud.office.erp.resource.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloud.office.erp.entity.Resource;

@Repository
public interface ResourceDao extends JpaRepository<Resource, Long> {

	@Query("FROM Resource r ORDER BY r.recourceId ASC")
	public List<Resource> getAllResource();

	@Query("FROM Resource r WHERE r.resourceName = :resourceName")
	public Resource getResourceByResourceName(String resourceName);
}
