package com.cloud.office.erp.userrole.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.cloud.office.erp.entity.MemberDetails;
import com.cloud.office.erp.entity.UserRole;

public interface UserRoleService {

	public UserRole loadDefaultUserRole();
	
	public UserRole addUserRole(UserRole userRole);

	public void deleteUserRole(Long userRoleId);

	public List<UserRole> getAllUserRole();

	public UserRole getUserRoleById(Long userRoleId);
	
	public List<UserRole> getUserRole(Pageable pageable);
	
	public List<UserRole> searchUserRole(String searchValue);

	public UserRole getUserRoleByUserRoleName(String userRoleName);
}
