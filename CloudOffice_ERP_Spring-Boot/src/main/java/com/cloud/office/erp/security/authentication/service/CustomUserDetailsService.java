package com.cloud.office.erp.security.authentication.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cloud.office.erp.entity.MemberDetails;
import com.cloud.office.erp.entity.User;
import com.cloud.office.erp.memberdetails.service.MemberDetailsService;
import com.cloud.office.erp.user.service.UserService;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UserService userService;
	
	@Autowired
	MemberDetailsService memberDetailsService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		MemberDetails memberDetails = null;
		try{
			//user = userService.findUserByUserName(username);
			memberDetails = memberDetailsService.getMemberDetailsByUserLoginId(username, 0L).get();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new org.springframework.security.core.userdetails.User(memberDetails.getUserLoginId(), memberDetails.getUserPassword(), new ArrayList<>());
	}

}
