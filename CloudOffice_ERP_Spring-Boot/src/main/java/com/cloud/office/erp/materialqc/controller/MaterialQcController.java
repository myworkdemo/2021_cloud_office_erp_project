package com.cloud.office.erp.materialqc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.office.erp.entity.MaterialQc;
import com.cloud.office.erp.materialqc.service.MaterialQcService;
import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/material-qc")
@CrossOrigin("*")
public class MaterialQcController {

	@Autowired
	MaterialQcService materialQcService;

	@PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public MaterialQc addMaterialQc(@RequestParam("file") MultipartFile multipartFile,
			@RequestParam("materialQc") String materialQc_str) {

		MaterialQc materialQc = null;
	try {
		
		Gson gson = new Gson();
		materialQc = gson.fromJson(materialQc_str, MaterialQc.class);
		materialQc.setQcAttachment(multipartFile.getBytes());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("### materialQc : "+materialQc);
		
		return materialQcService.addMaterialQc(materialQc);
	}

	@PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public MaterialQc updateMaterialQc(@RequestParam("file") MultipartFile multipartFile,
			@RequestParam("materialQc") String materialQc_str) {

		MaterialQc materialQc = null;
	try {
		
		Gson gson = new Gson();
		materialQc = gson.fromJson(materialQc_str, MaterialQc.class);
		materialQc.setQcAttachment(multipartFile.getBytes());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("### materialQc : "+materialQc);
		
		return materialQcService.updateMaterialQc(materialQc);
	}

	@DeleteMapping(value = "/delete/{materialQcId}")
	public void deleteMaterialQc(@PathVariable("materialQcId") Long materialQcId) {

		materialQcService.deleteMaterialQc(materialQcId);
	}

	@GetMapping(value = "/getAll")
	public List<MaterialQc> getAllMaterialQc() {

		return materialQcService.getAllMaterialQc();
	}

	@GetMapping(value = "/getById/{materialQcId}")
	public Optional<MaterialQc> getMaterialQcById(@PathVariable("materialQcId") Long materialQcId) {

		return materialQcService.getMaterialQcById(materialQcId);
	}
}
