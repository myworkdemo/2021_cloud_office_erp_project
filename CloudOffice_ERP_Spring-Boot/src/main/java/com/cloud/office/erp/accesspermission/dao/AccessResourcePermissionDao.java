package com.cloud.office.erp.accesspermission.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloud.office.erp.entity.AccessResourcePermission;
import com.cloud.office.erp.entity.User;
import com.cloud.office.erp.entity.UserRole;

@Repository
public interface AccessResourcePermissionDao extends JpaRepository<AccessResourcePermission, Long> {

	@Query("FROM AccessResourcePermission arp WHERE arp.userRole = :userRole AND arp.resourceName = :resourceName GROUP BY arp.userRole")
	public AccessResourcePermission findUserByUserIdAndResourceName(UserRole userRole, String resourceName);
	
	@Query("FROM AccessResourcePermission arp WHERE arp.userRole = :userRole ORDER BY accessPermissionId ASC")
	public List<AccessResourcePermission> findUserPermissionsByUserRole(UserRole userRole);
	
	@Modifying
	@Query("DELETE FROM AccessResourcePermission")
	public void truncateAccessResourcePermissionTable();

	@Query("FROM AccessResourcePermission arp WHERE arp.resourceName = :resourceName AND arp.userRole = :userRole")
	public Optional<AccessResourcePermission> findAccessResourcePermissionByUserRole(String resourceName, UserRole userRole);

	/*
	 * @Query("FROM AccessResourcePermission arp WHERE arp.userRole = :userRole ORDER BY accessPermissionId ASC"
	 * ) public List<AccessResourcePermission>
	 * findAccessResourcePermissionByUserRole(UserRole userRole);
	 */
}
