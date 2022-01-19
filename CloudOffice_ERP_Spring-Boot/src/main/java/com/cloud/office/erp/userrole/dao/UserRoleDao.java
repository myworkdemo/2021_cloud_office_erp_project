package com.cloud.office.erp.userrole.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloud.office.erp.entity.UserRole;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Long> {

	@Query("FROM UserRole ur ORDER BY ur.userRoleId ASC")
	public List<UserRole> getUserRole(Pageable pageable);
	
	@Query("SELECT ur FROM UserRole ur WHERE ur.userRole LIKE :searchValue%")
	public List<UserRole> searchUserRole(String searchValue);

	@Query("FROM UserRole ur WHERE ur.userRole = :userRole")
	public UserRole getUserRoleByRole(String userRole);

	@Query("FROM UserRole ur WHERE ur.userRoleId = :userRoleId")
	public UserRole getUserRoleById(Long userRoleId);

	@Query("FROM UserRole ur WHERE ur.userRole = :userRole")
	public UserRole getUserRoleByUserRoleName(String userRole);
}
