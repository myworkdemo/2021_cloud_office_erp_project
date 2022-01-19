package com.cloud.office.erp.material.controller;

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

import com.cloud.office.erp.entity.Material;
import com.cloud.office.erp.material.service.MaterialService;

@RestController
@RequestMapping(value = "/material")
@CrossOrigin("*")
public class MaterialController {
	
	@Autowired
	MaterialService materialService;
	
	@PostMapping(value = "/add")
	public Material addMaterial(@RequestBody Material material) {

		return materialService.addMaterial(material);
	}

	@PutMapping(value = "/update")
	public Material updateMaterial(@RequestBody Material material) {

		return materialService.updateMaterial(material);
	}

	@DeleteMapping(value = "/delete/{materialId}")
	public void deleteMaterial(@PathVariable("materialId") Long materialId) {

		materialService.deleteMaterial(materialId);
	}

	@GetMapping(value = "/getAll")
	public List<Material> getAllMaterial() {

		return materialService.getAllMaterial();
	}

	@GetMapping(value = "/getById/{materialId}")
	public Optional<Material> getMaterialById(@PathVariable("materialId") Long materialId) {

		return materialService.getMaterialById(materialId);
	}
	
	@GetMapping(value = "/getMaterial/{pageNum}/{maxRecords}")
	public List<Material> getMaterial(@PathVariable("pageNum") int pageNum, @PathVariable("maxRecords") int maxRecords)
	{
		return materialService.getMaterial(PageRequest.of(pageNum-1, maxRecords));
	}
	
	@GetMapping(value = "/searchMaterial/{searchValue}")
	public List<Material> searchMaterial(@PathVariable("searchValue") String searchValue)
	{
		return materialService.searchMaterial(searchValue);
	}

}
