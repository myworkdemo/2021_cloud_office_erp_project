package com.cloud.office.erp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resource")
public class Resource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recourceId;
	private String resourceName;
	private String resourceType;

	public Resource() {
	}

	public Resource(String resourceName, String resourceType) {
		this.resourceName = resourceName;
		this.resourceType = resourceType;
	}

	public Long getRecourceId() {
		return recourceId;
	}

	public void setRecourceId(Long recourceId) {
		this.recourceId = recourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

}
