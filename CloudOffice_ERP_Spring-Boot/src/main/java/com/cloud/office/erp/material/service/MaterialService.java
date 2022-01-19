package com.cloud.office.erp.material.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.cloud.office.erp.entity.Material;

public interface MaterialService {

	public Material addMaterial(Material material);

	public Material updateMaterial(Material material);

	public void deleteMaterial(Long materialId);

	public List<Material> getAllMaterial();

	public Optional<Material> getMaterialById(Long materialId);
	
	public List<Material> getMaterial(Pageable pageable);
	
	public List<Material> searchMaterial(String searchValue);
}
