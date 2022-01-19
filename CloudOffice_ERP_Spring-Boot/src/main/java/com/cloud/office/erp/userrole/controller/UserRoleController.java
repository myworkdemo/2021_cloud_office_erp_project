package com.cloud.office.erp.userrole.controller;

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

import com.cloud.office.erp.accesspermission.service.AccessResourcePermissionService;
import com.cloud.office.erp.entity.AccessResourcePermission;
import com.cloud.office.erp.entity.MemberDetails;
import com.cloud.office.erp.entity.Resource;
import com.cloud.office.erp.entity.UserRole;
import com.cloud.office.erp.resource.service.ResourceService;
import com.cloud.office.erp.userrole.service.UserRoleService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/user-role")
public class UserRoleController {

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	ResourceService resourceService;
	
	@Autowired
	AccessResourcePermissionService accessResourcePermissionService;
	
	@PostMapping(value = "/add")
	public UserRole addUserRole(@RequestBody UserRole userRole) {

	 userRole = userRoleService.addUserRole(userRole);
	 List<Resource> list = resourceService.getAllResource();
	 
	 Optional<AccessResourcePermission> optional = accessResourcePermissionService.findAccessResourcePermissionByUserRole(userRole.getUserRole(), userRole);
	 
	 if(!optional.isPresent()) {
		 for(Resource resource : list)
		 {
			 AccessResourcePermission accessResourcePermission = new AccessResourcePermission(userRole, resource, resource.getResourceName(), "N", "N", "N");
			 accessResourcePermissionService.addAccessResourcePermission(accessResourcePermission);
		 }	 
	 }
	 
	 return userRole;
	}
	
	@PutMapping(value = "/update")
	public UserRole updateUserRole(@RequestBody UserRole userRole) {

		return userRoleService.addUserRole(userRole);
	}

	@DeleteMapping(value = "/delete/{userRoleId}")
	public void deleteUserRole(@PathVariable("userRoleId") Long userRoleId) {

		userRoleService.deleteUserRole(userRoleId);	
	}

	@GetMapping(value = "/getAll")
	public List<UserRole> getAllUserRole() {

		return userRoleService.getAllUserRole();
	}

	@GetMapping(value = "/getById/{userRoleId}")
	public UserRole getUserRoleById(@PathVariable Long userRoleId) {

		return userRoleService.getUserRoleById(userRoleId);
	}
	
	@GetMapping(value = "/getUserRole/{pageNum}/{maxRecords}")
	public List<UserRole> getUserRole(@PathVariable("pageNum") int pageNum, @PathVariable("maxRecords") int maxRecords)
	{
		return userRoleService.getUserRole(PageRequest.of(pageNum-1, maxRecords));
	}
	
	@GetMapping(value = "/searchUserRole/{searchValue}")
	public List<UserRole> searchUserRole(@PathVariable("searchValue") String searchValue)
	{
		return userRoleService.searchUserRole(searchValue);
	}
}
