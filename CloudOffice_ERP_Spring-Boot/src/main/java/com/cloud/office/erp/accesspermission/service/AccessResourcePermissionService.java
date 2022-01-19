package com.cloud.office.erp.accesspermission.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cloud.office.erp.entity.AccessResourcePermission;
import com.cloud.office.erp.entity.User;
import com.cloud.office.erp.entity.UserRole;

public interface AccessResourcePermissionService {

	public AccessResourcePermission addAccessResourcePermission(AccessResourcePermission accessResourcePermission);

	public void deleteAccessResourcePermission(Long accessPerId);

	public List<AccessResourcePermission> getAllAccessResourcePermission();

	public Optional<AccessResourcePermission> getAccessResourcePermissionById(Long accessPerId);
	
	public AccessResourcePermission findUserByUserIdAndResourceName(UserRole userRole, String resourceName);
	
	public List<AccessResourcePermission> findUserPermissionsByUserRole(UserRole userRole);
	
	public Optional<AccessResourcePermission> findAccessResourcePermissionByUserRole(String resourceName, UserRole userRole);
	
	public void truncateAccessResourcePermissionTable();

	public List<AccessResourcePermission> findAccessResourcePermissionByUserRole(UserRole userRole);
}
