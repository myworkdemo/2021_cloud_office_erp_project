package com.cloud.office.erp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "master_department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long deptId;
	private String deptName;
	private String memberOrUser;
	private String deptDescription;

	public Department() {
	}

	public Department(String deptName, String memberOrUser, String deptDescription) {
		this.deptName = deptName;
		this.memberOrUser = memberOrUser;
		this.deptDescription = deptDescription;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getMemberOrUser() {
		return memberOrUser;
	}

	public void setMemberOrUser(String memberOrUser) {
		this.memberOrUser = memberOrUser;
	}

	public String getDeptDescription() {
		return deptDescription;
	}

	public void setDeptDescription(String deptDescription) {
		this.deptDescription = deptDescription;
	}

}
