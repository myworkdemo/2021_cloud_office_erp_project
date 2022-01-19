package com.cloud.office.erp.incomingmaterialinfo.controller;

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

import com.cloud.office.erp.entity.IncomingMaterial;
import com.cloud.office.erp.entity.IncomingMaterialInfo;
import com.cloud.office.erp.entity.IncomingMaterialJsonEntity;
import com.cloud.office.erp.incomingmaterial.service.IncomingMaterialService;
import com.cloud.office.erp.incomingmaterialinfo.service.IncomingMaterialInfoService;
import com.google.gson.Gson;

@RestController
@CrossOrigin(origins =  "*")
@RequestMapping(value = "/incoming-material-info")
public class IncomingMaterialInfoController {

	@Autowired
	IncomingMaterialInfoService incomingMaterialInfoService;
	
	@Autowired
	IncomingMaterialService incomingMaterialService; 
	
	@PostMapping(value = "/add")
	public IncomingMaterialInfo addIncomingMaterialInfo(@RequestBody IncomingMaterialInfo incomingMaterialInfo) {

		return  incomingMaterialInfoService.addIncomingMaterialInfo(incomingMaterialInfo);
	}

	@PutMapping(value = "/update")
	public IncomingMaterialInfo updateIncomingMaterialInfo(@RequestBody IncomingMaterialInfo incomingMaterialInfo) {

		return incomingMaterialInfoService.addIncomingMaterialInfo(incomingMaterialInfo);
	}

	@DeleteMapping(value = "/delete/{incomingMaterialInfoId}")
	public void deleteIncomingMaterialInfo(@PathVariable("incomingMaterialInfoId") Long incomingMaterialInfoId) {

		incomingMaterialInfoService.deleteIncomingMaterialInfo(incomingMaterialInfoId);
	}

	@GetMapping(value = "/getAll")
	public List<IncomingMaterialJsonEntity> getAllIncomingMaterialInfo() {

		List<IncomingMaterialJsonEntity> list = incomingMaterialService.getAllRecords();
		
		Gson gson = new Gson();
		String list2 =  gson.toJson(list);
		
		
		System.out.println("##LIST : "+list);
		System.out.println("##LIST2 : "+list2);
		
		return list;
	}

	@GetMapping(value = "/getById/{incomingMaterialId}")
	public List<IncomingMaterialInfo> getIncomingMaterialInfoById(@PathVariable("incomingMaterialId") Long incomingMaterialId) {

		Optional<IncomingMaterial> mIncomingMaterial = incomingMaterialService.getIncomingMaterialById(incomingMaterialId);
		
		List<IncomingMaterialInfo> incomingMaterialInfoList = null;
		if(mIncomingMaterial.isPresent()) {
			incomingMaterialInfoList = incomingMaterialInfoService.getIncomingMaterialInfoById(mIncomingMaterial.get());
		}
		
		return incomingMaterialInfoList;
	}
	
}

