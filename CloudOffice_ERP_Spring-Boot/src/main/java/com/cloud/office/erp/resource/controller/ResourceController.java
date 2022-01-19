package com.cloud.office.erp.resource.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.office.erp.entity.Resource;
import com.cloud.office.erp.resource.service.ResourceService;

@RestController
@RequestMapping(value = "/resource")
@CrossOrigin("*")
public class ResourceController {

	@Autowired
	ResourceService resourceService;
	
	@GetMapping(value = "/getAll")
	public List<Resource> getAllResource()
	{
		return resourceService.getAllResource();
	}
}
