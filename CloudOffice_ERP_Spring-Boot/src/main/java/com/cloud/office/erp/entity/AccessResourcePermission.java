package com.cloud.office.erp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "access_resource_permission")
public class AccessResourcePermission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accessPermissionId;

	@OneToOne
	@JoinColumn(name = "userRole_fk")
	private UserRole userRole;

	@OneToOne
	@JoinColumn(name = "resource_fk")
	private Resource resource;
	
	private String resourceName;

	private String addPermission;
	private String modifyPermission;
	private String deletePermission;

	public AccessResourcePermission() {
	}

	public AccessResourcePermission(UserRole userRole, Resource resource, String resourceName, String addPermission,
			String modifyPermission, String deletePermission) {

		this.userRole = userRole;
		this.resource = resource;
		this.resourceName = resourceName;
		this.addPermission = addPermission;
		this.modifyPermission = modifyPermission;
		this.deletePermission = deletePermission;
	}

	public Long getAccessPermissionId() {
		return accessPermissionId;
	}

	public void setAccessPermissionId(Long accessPermissionId) {
		this.accessPermissionId = accessPermissionId;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getAddPermission() {
		return addPermission;
	}

	public void setAddPermission(String addPermission) {
		this.addPermission = addPermission;
	}

	public String getModifyPermission() {
		return modifyPermission;
	}

	public void setModifyPermission(String modifyPermission) {
		this.modifyPermission = modifyPermission;
	}

	public String getDeletePermission() {
		return deletePermission;
	}

	public void setDeletePermission(String deletePermission) {
		this.deletePermission = deletePermission;
	}

}
