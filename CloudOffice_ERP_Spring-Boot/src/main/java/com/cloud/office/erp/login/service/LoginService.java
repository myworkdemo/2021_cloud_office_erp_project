package com.cloud.office.erp.login.service;

import com.cloud.office.erp.entity.User;

public interface LoginService {

	public User userLogin(String userName, String userPassword);
}
