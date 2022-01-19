package com.cloud.office.erp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meterial_qc")
public class MaterialQc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long materialQcId;

	private String qcCode;
	private String qcDate;
	private String workName;
	private String irCode;
	private String irDate;
	private String poCode;
	private String suplierInvoiceNo;
	private String suplierName;
	private String departmentName;
	private String sendForApproval;
	private String storeInchargeApproval;
	
	@Column(name = "qcAttachment", columnDefinition = "LONGTEXT")
	private byte[] qcAttachment;

	public MaterialQc() {
	}

	public MaterialQc(String qcCode, String qcDate, String workName, String irCode, String irDate, String poCode,
			String suplierInvoiceNo, String suplierName, String departmentName, String sendForApproval,
			String storeInchargeApproval, byte[] qcAttachment) {
		super();
		this.qcCode = qcCode;
		this.qcDate = qcDate;
		this.workName = workName;
		this.irCode = irCode;
		this.irDate = irDate;
		this.poCode = poCode;
		this.suplierInvoiceNo = suplierInvoiceNo;
		this.suplierName = suplierName;
		this.departmentName = departmentName;
		this.sendForApproval = sendForApproval;
		this.storeInchargeApproval = storeInchargeApproval;
		this.qcAttachment = qcAttachment;
	}

	public Long getMaterialQcId() {
		return materialQcId;
	}

	public void setMaterialQcId(Long materialQcId) {
		this.materialQcId = materialQcId;
	}

	public String getQcCode() {
		return qcCode;
	}

	public void setQcCode(String qcCode) {
		this.qcCode = qcCode;
	}

	public String getQcDate() {
		return qcDate;
	}

	public void setQcDate(String qcDate) {
		this.qcDate = qcDate;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getIrCode() {
		return irCode;
	}

	public void setIrCode(String irCode) {
		this.irCode = irCode;
	}

	public String getIrDate() {
		return irDate;
	}

	public void setIrDate(String irDate) {
		this.irDate = irDate;
	}

	public String getPoCode() {
		return poCode;
	}

	public void setPoCode(String poCode) {
		this.poCode = poCode;
	}

	public String getSuplierInvoiceNo() {
		return suplierInvoiceNo;
	}

	public void setSuplierInvoiceNo(String suplierInvoiceNo) {
		this.suplierInvoiceNo = suplierInvoiceNo;
	}

	public String getSuplierName() {
		return suplierName;
	}

	public void setSuplierName(String suplierName) {
		this.suplierName = suplierName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getSendForApproval() {
		return sendForApproval;
	}

	public void setSendForApproval(String sendForApproval) {
		this.sendForApproval = sendForApproval;
	}

	public String getStoreInchargeApproval() {
		return storeInchargeApproval;
	}

	public void setStoreInchargeApproval(String storeInchargeApproval) {
		this.storeInchargeApproval = storeInchargeApproval;
	}

	public byte[] getQcAttachment() {
		return qcAttachment;
	}

	public void setQcAttachment(byte[] qcAttachment) {
		this.qcAttachment = qcAttachment;
	}

}
