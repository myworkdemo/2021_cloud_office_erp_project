package com.cloud.office.erp.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloud.office.erp.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

	@Query("FROM User u WHERE u.userName = :userName")
	public User findUserByUserName(String userName);
}
