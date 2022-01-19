package com.cloud.office.erp.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.office.erp.entity.User;
import com.cloud.office.erp.login.dao.LoginDao;
import com.cloud.office.erp.login.service.LoginService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDao loginDao;
	
	@Override
	public User userLogin(String userName, String userPassword) {
		
		return loginDao.userLogin(userName, userPassword);
	}

}
