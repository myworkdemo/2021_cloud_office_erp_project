package com.cloud.office.erp.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.office.erp.accesspermission.service.AccessResourcePermissionService;
import com.cloud.office.erp.entity.AccessResourcePermission;
import com.cloud.office.erp.entity.User;
import com.cloud.office.erp.entity.UserRole;
import com.cloud.office.erp.user.service.UserService;

@RestController
@CrossOrigin(origins =  "*")
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AccessResourcePermissionService accessResourcePermissionService;
	
	
	@GetMapping(value = "/getDetails/{username}")
	public List<AccessResourcePermission> getUser(@PathVariable("username") String username) {
		
		System.out.println("#username : "+username);
		
		User user = userService.findUserByUserName(username);
		UserRole userRole = new UserRole();
		userRole.setUserRoleId(user.getUserRoleId().getUserRoleId());
		
		return accessResourcePermissionService.findUserPermissionsByUserRole(userRole);
	}

}
