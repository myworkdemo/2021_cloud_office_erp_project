package com.cloud.office.erp.materialqcdetails.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cloud.office.erp.entity.MaterialQcDetails;
import com.cloud.office.erp.materialqcdetails.service.MaterialQcDetailsService;

@RestController
@RequestMapping(value = "/material-qc-details")
@CrossOrigin("*")
public class MaterialQcDetailsController {
	
	@Autowired
	MaterialQcDetailsService materialQcDetailsService;

	@PostMapping(value = "/add")
	public MaterialQcDetails addMaterialQcDetails(@RequestBody MaterialQcDetails materialQcDetails) {

		return materialQcDetailsService.addMaterialQcDetails(materialQcDetails);
	}

	@PutMapping(value = "/update")
	public List<MaterialQcDetails> updateMaterialQcDetails(@RequestBody MaterialQcDetails materialQcDetails) {

		return materialQcDetailsService.updateMaterialQcDetails(materialQcDetails);
	}

	@DeleteMapping(value = "/delete/{materialQcId}")
	public void deleteMaterialQcDetails(@PathVariable("materialQcId") Long materialQcId) {

		materialQcDetailsService.deleteMaterialQcDetails(materialQcId);
	}

	@GetMapping(value = "/getAll")
	public List<MaterialQcDetails> getAllMaterialQcDetails() {

		return materialQcDetailsService.getAllMaterialQcDetails();
	}

	@GetMapping(value = "/getById/{materialQcId}")
	public List<MaterialQcDetails> getMaterialQcDetailsById(@PathVariable("materialQcId") Long materialQcId) {

		return materialQcDetailsService.getMaterialQcDetailsById(materialQcId);
	}
}
