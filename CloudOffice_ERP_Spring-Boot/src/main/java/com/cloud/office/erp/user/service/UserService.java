package com.cloud.office.erp.user.service;

import java.util.List;
import java.util.Optional;

import com.cloud.office.erp.entity.User;

public interface UserService {

	public User addUser(User user);

	public User updateUser(User user);

	public void deleteUser(Long userId);

	public List<User> getAllUser();

	public Optional<User> getUserById(Long userId);
	
	public User findUserByUserName(String userName);
}
