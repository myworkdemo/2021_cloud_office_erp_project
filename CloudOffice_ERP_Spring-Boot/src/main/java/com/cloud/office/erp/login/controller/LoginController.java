package com.cloud.office.erp.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.office.erp.entity.User;
import com.cloud.office.erp.login.service.LoginService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/userLogin")
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@GetMapping(value = "/login/{userName}/{userPassword}")
	public User userLogin(@PathVariable("userName") String userName, @PathVariable("userPassword") String userPassword)
	{
		return loginService.userLogin(userName, userPassword);
	}
	
}
