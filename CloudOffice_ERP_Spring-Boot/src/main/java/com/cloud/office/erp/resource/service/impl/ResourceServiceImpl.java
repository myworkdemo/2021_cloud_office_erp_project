package com.cloud.office.erp.resource.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.office.erp.entity.Resource;
import com.cloud.office.erp.resource.dao.ResourceDao;
import com.cloud.office.erp.resource.service.ResourceService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	ResourceDao resourceDao;
	
	@Override
	public void loadDefaultResource() {
		
		List<String> resourceList = List.of("User Role", "Department", "User Details", "Vendor", "Material");
		Resource resource = null;
		for(String resourceName : resourceList) {
			
			resource = resourceDao.getResourceByResourceName(resourceName);
			
			if(resource == null) {
			resource = new Resource(resourceName, "");
			resourceDao.save(resource);
			}
		}
		
	}
	
	@Override
	public Resource addResource(Resource resource) {
		
		return resourceDao.save(resource);
	}

	@Override
	public void deleteResource(Long resourceId) {
		
		Optional<Resource> resource = resourceDao.findById(resourceId);
		resourceDao.delete(resource.get());
	}

	@Override
	public List<Resource> getAllResource() {
	
		return resourceDao.getAllResource();
	}

	@Override
	public Resource getResourceById(Long resourceId) {
		
		return resourceDao.findById(resourceId).get();
	}

	@Override
	public Resource getResourceByResourceName(String resourceName) {
		
		return resourceDao.getResourceByResourceName(resourceName);
	}

}
