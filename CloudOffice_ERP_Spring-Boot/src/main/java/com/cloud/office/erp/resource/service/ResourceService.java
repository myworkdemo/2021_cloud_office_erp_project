package com.cloud.office.erp.resource.service;

import java.util.List;
import java.util.Optional;

import com.cloud.office.erp.entity.Resource;

public interface ResourceService {

	public void loadDefaultResource();
	
	public Resource addResource(Resource resource);

	public void deleteResource(Long resourceId);

	public List<Resource> getAllResource();

	public Resource getResourceById(Long resourceId);

	public Resource getResourceByResourceName(String resourceName);
}
