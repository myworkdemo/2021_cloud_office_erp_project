package com.cloud.office.erp.entity;

import javax.persistence.Entity;

public class IncomingMaterialJsonEntity {

	private Long incomingMaterialId;
	private String workName;
	private String departmentName;
	private String incomingRegisterCode;
	private String incomingRegisterDate;
	private String supplierName;
	private String suplierInvoiceNo;

	private String poCode;
	private String poDate;

	private String accountCode;
	private String materialType;
	private String materialSubType;
	private String materialName;
	private String description;
	private String umo;

	public IncomingMaterialJsonEntity() {
	}

	public IncomingMaterialJsonEntity(Long incomingMaterialId, String workName, String departmentName,
			String incomingRegisterCode, String incomingRegisterDate, String supplierName, String suplierInvoiceNo,
			String poCode, String poDate, String accountCode, String materialType, String materialSubType,
			String materialName, String description, String umo) {
		super();
		this.incomingMaterialId = incomingMaterialId;
		this.workName = workName;
		this.departmentName = departmentName;
		this.incomingRegisterCode = incomingRegisterCode;
		this.incomingRegisterDate = incomingRegisterDate;
		this.supplierName = supplierName;
		this.suplierInvoiceNo = suplierInvoiceNo;
		this.poCode = poCode;
		this.poDate = poDate;

		this.accountCode = accountCode;
		this.materialType = materialType;
		this.materialSubType = materialSubType;
		this.materialName = materialName;
		this.description = description;
		this.umo = umo;
	}

	public Long getIncomingMaterialId() {
		return incomingMaterialId;
	}

	public void setIncomingMaterialId(Long incomingMaterialId) {
		this.incomingMaterialId = incomingMaterialId;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getIncomingRegisterCode() {
		return incomingRegisterCode;
	}

	public void setIncomingRegisterCode(String incomingRegisterCode) {
		this.incomingRegisterCode = incomingRegisterCode;
	}

	public String getIncomingRegisterDate() {
		return incomingRegisterDate;
	}

	public void setIncomingRegisterDate(String incomingRegisterDate) {
		this.incomingRegisterDate = incomingRegisterDate;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSuplierInvoiceNo() {
		return suplierInvoiceNo;
	}

	public void setSuplierInvoiceNo(String suplierInvoiceNo) {
		this.suplierInvoiceNo = suplierInvoiceNo;
	}

	public String getPoCode() {
		return poCode;
	}

	public void setPoCode(String poCode) {
		this.poCode = poCode;
	}

	public String getPoDate() {
		return poDate;
	}

	public void setPoDate(String poDate) {
		this.poDate = poDate;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getMaterialSubType() {
		return materialSubType;
	}

	public void setMaterialSubType(String materialSubType) {
		this.materialSubType = materialSubType;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUmo() {
		return umo;
	}

	public void setUmo(String umo) {
		this.umo = umo;
	}

}
