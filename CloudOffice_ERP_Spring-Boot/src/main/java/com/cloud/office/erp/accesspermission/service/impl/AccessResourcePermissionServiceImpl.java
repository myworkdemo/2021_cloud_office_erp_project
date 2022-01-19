package com.cloud.office.erp.accesspermission.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.office.erp.accesspermission.dao.AccessResourcePermissionDao;
import com.cloud.office.erp.accesspermission.service.AccessResourcePermissionService;
import com.cloud.office.erp.entity.AccessResourcePermission;
import com.cloud.office.erp.entity.User;
import com.cloud.office.erp.entity.UserRole;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class AccessResourcePermissionServiceImpl implements AccessResourcePermissionService {

	@Autowired
	AccessResourcePermissionDao accessResourcePermissionDao;
	
	@Override
	public AccessResourcePermission addAccessResourcePermission(AccessResourcePermission accessResourcePermission) {
		
		return accessResourcePermissionDao.save(accessResourcePermission);
	}

	@Override
	public void deleteAccessResourcePermission(Long accessPerId) {
		
		Optional<AccessResourcePermission> optional = accessResourcePermissionDao.findById(accessPerId);
		accessResourcePermissionDao.delete(optional.get());
	}

	@Override
	public List<AccessResourcePermission> getAllAccessResourcePermission() {
		
		return accessResourcePermissionDao.findAll();
	}

	@Override
	public Optional<AccessResourcePermission> getAccessResourcePermissionById(Long accessPerId) {
		
		return accessResourcePermissionDao.findById(accessPerId);
	}

	@Override
	public AccessResourcePermission findUserByUserIdAndResourceName(UserRole userRoleId, String resourceName) {
		
		return accessResourcePermissionDao.findUserByUserIdAndResourceName(userRoleId, resourceName);
	}

	@Override
	public List<AccessResourcePermission> findUserPermissionsByUserRole(UserRole userRole) {
		
		return accessResourcePermissionDao.findUserPermissionsByUserRole(userRole);
	}

	@Override
	@Transactional
	public void truncateAccessResourcePermissionTable() {
		
		accessResourcePermissionDao.truncateAccessResourcePermissionTable();
	}

	@Override
	public Optional<AccessResourcePermission> findAccessResourcePermissionByUserRole(String resourceName, UserRole userRole) {
		
		return accessResourcePermissionDao.findAccessResourcePermissionByUserRole(resourceName, userRole);
	}

	@Override
	public List<AccessResourcePermission> findAccessResourcePermissionByUserRole(UserRole userRole) {
		
		return accessResourcePermissionDao.findUserPermissionsByUserRole(userRole);
	}

}
