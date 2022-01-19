package com.cloud.office.erp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "master_user_role")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userRoleId;
	private String userRole;
	private String reportToLevel;

	public UserRole() {
	}

	public UserRole(String userRole, String reportToLevel) {
		this.userRole = userRole;
		this.reportToLevel = reportToLevel;
	}

	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getReportToLevel() {
		return reportToLevel;
	}

	public void setReportToLevel(String reportToLevel) {
		this.reportToLevel = reportToLevel;
	}

}
