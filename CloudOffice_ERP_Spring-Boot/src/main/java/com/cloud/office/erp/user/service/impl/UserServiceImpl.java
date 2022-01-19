package com.cloud.office.erp.user.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.office.erp.entity.User;
import com.cloud.office.erp.user.dao.UserDao;
import com.cloud.office.erp.user.service.UserService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public User addUser(User user) {
	
		return userDao.save(user);
	}

	@Override
	public User updateUser(User user) {
		
		return userDao.save(user);
	}

	@Override
	public void deleteUser(Long userId) {
		
		Optional<User> user = userDao.findById(userId);
		userDao.delete(user.get());
	}

	@Override
	public List<User> getAllUser() {
		
		return userDao.findAll();
	}

	@Override
	public Optional<User> getUserById(Long userId) {
		
		return userDao.findById(userId);
	}

	@Override
	public User findUserByUserName(String userName) {
		
		return userDao.findUserByUserName(userName);
	}

}
