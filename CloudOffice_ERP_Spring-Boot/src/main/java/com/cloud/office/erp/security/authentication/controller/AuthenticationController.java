package com.cloud.office.erp.security.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cloud.office.erp.entity.AuthRequest;
import com.cloud.office.erp.security.authentication.jwt.util.JwtUtil;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception{
		
	try {	
		authenticationManager.authenticate(
		 
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
		);
	}catch(Exception exception) {
		return "access denied";
		//throw new Exception("invalid username/password");
	}		
		return jwtUtil.generateToken(authRequest.getUsername());
	}
}
