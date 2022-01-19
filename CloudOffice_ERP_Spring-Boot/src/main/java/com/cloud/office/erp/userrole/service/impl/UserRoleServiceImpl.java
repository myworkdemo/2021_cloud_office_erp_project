package com.cloud.office.erp.userrole.service.impl;

import java.util.List;
import java.util.Optional;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.office.erp.entity.MemberDetails;
import com.cloud.office.erp.entity.UserRole;
import com.cloud.office.erp.userrole.dao.UserRoleDao;
import com.cloud.office.erp.userrole.service.UserRoleService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	public UserRole loadDefaultUserRole() {
		
		UserRole userRole = null;
		
		userRole = userRoleDao.getUserRoleByRole("Admin");
		if(userRole == null) {
			UserRole userRole_temp = new UserRole("Admin", "Admin");
			userRole = userRoleDao.save(userRole_temp);
		}
		
		return userRole;
	}

	@Override
	public UserRole addUserRole(UserRole userRole) {

		if (userRole.getUserRoleId() != null) {
			userRole.setUserRoleId(userRole.getUserRoleId());
			return userRoleDao.save(userRole);
		} else {
			return userRoleDao.save(userRole);
		}
	}

	@Override
	public void deleteUserRole(Long userRoleId) {

		Optional<UserRole> userRole = userRoleDao.findById(userRoleId);
		if (userRole != null) {
			userRoleDao.delete(userRole.get());
		}
	}

	@Override
	public List<UserRole> getAllUserRole() {

		return userRoleDao.findAll();
	}

	@Override
	public UserRole getUserRoleById(Long userRoleId) {

		return userRoleDao.getUserRoleById(userRoleId);
	}

	@Override
	public List<UserRole> getUserRole(Pageable pageable) {
		
		return userRoleDao.getUserRole(pageable);
	}

	@Override
	public List<UserRole> searchUserRole(String searchValue) {
		
		return userRoleDao.searchUserRole(searchValue);
	}

	@Override
	public UserRole getUserRoleByUserRoleName(String userRoleName) {
		
		return userRoleDao.getUserRoleByUserRoleName(userRoleName);
	}

}
