package com.cloud.office.erp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purchase_requisition_info")
public class PurchaseRequisitionInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long purchaseRequisitionInfoId;

	private String prDate;
	private String prNo;
	private String departmentName;
	private String customerPoNo;
	private String workName;
	private String customerPoDate;
	
	@Column(name = "document", columnDefinition = "LONGTEXT")
	private byte[] document;
	
	private String storeInchargeApproval;
	private String sendForApproval;
	private String inchargeRemark;
	private String equipmentDetails;
	private String deliveryDate;

	public PurchaseRequisitionInfo() {
	}

	public PurchaseRequisitionInfo(String prDate, String prNo, String departmentName, String customerPoNo,
			String workName, String customerPoDate, byte[] document, String storeInchargeApproval,
			String sendForApproval, String inchargeRemark, String equipmentDetails, String deliveryDate) {
		super();
		this.prDate = prDate;
		this.prNo = prNo;
		this.departmentName = departmentName;
		this.customerPoNo = customerPoNo;
		this.workName = workName;
		this.customerPoDate = customerPoDate;
		this.document = document;
		this.storeInchargeApproval = storeInchargeApproval;
		this.sendForApproval = sendForApproval;
		this.inchargeRemark = inchargeRemark;
		this.equipmentDetails = equipmentDetails;
		this.deliveryDate = deliveryDate;
	}

	public Long getPurchaseRequisitionInfoId() {
		return purchaseRequisitionInfoId;
	}

	public void setPurchaseRequisitionInfoId(Long purchaseRequisitionInfoId) {
		this.purchaseRequisitionInfoId = purchaseRequisitionInfoId;
	}

	public String getPrDate() {
		return prDate;
	}

	public void setPrDate(String prDate) {
		this.prDate = prDate;
	}

	public String getPrNo() {
		return prNo;
	}

	public void setPrNo(String prNo) {
		this.prNo = prNo;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getCustomerPoNo() {
		return customerPoNo;
	}

	public void setCustomerPoNo(String customerPoNo) {
		this.customerPoNo = customerPoNo;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getCustomerPoDate() {
		return customerPoDate;
	}

	public void setCustomerPoDate(String customerPoDate) {
		this.customerPoDate = customerPoDate;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

	public String getStoreInchargeApproval() {
		return storeInchargeApproval;
	}

	public void setStoreInchargeApproval(String storeInchargeApproval) {
		this.storeInchargeApproval = storeInchargeApproval;
	}

	public String getSendForApproval() {
		return sendForApproval;
	}

	public void setSendForApproval(String sendForApproval) {
		this.sendForApproval = sendForApproval;
	}

	public String getInchargeRemark() {
		return inchargeRemark;
	}

	public void setInchargeRemark(String inchargeRemark) {
		this.inchargeRemark = inchargeRemark;
	}

	public String getEquipmentDetails() {
		return equipmentDetails;
	}

	public void setEquipmentDetails(String equipmentDetails) {
		this.equipmentDetails = equipmentDetails;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

}
