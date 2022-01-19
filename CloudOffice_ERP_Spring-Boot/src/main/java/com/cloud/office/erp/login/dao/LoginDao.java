package com.cloud.office.erp.login.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloud.office.erp.entity.User;

@Repository
public interface LoginDao extends JpaRepository<User, Long> {

	@Query("FROM User u WHERE u.userName= :userName AND u.userPassword= :userPassword")
	public User userLogin(String userName, String userPassword);
}
