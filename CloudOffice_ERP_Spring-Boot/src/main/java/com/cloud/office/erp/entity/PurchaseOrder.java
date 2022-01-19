package com.cloud.office.erp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long purchaseOrderId;

	private String prNo;
	private String workName;
	private String faxNo;
	private String poCode;
	private String poDate;
	private String suplierName;
	private String suplierEmailId;
	private String suplierQuotationNo;
	private String suplierQuotationDate;
	private String refrenceNo;
	private String subject;
	private String cpApprove;
	private String storeInchargeApproval;
	private String sendForApproval;
	private String prBy;

	@Column(name = "poAttachment", columnDefinition = "LONGTEXT")
	private byte[] poAttachment;

	private String hideWorkNameOnPdf;

	private String gstinNo;
	private String accountNo;
	private String bankName;
	private String ifscCode;
	private String branch;

	@OneToOne
	@JoinColumn(name = "vendorId_fk")
	private Vendor vendor;

	public PurchaseOrder() {

	}

	public PurchaseOrder(String prNo, String workName, String faxNo, String poCode, String poDate, String suplierName,
			String suplierEmailId, String suplierQuotationNo, String suplierQuotationDate, String refrenceNo,
			String subject, String cpApprove, String storeInchargeApproval, String sendForApproval, String prBy,
			byte[] poAttachment, String hideWorkNameOnPdf, String gstinNo, String accountNo, String bankName,
			String ifscCode, String branch) {
		super();
		this.prNo = prNo;
		this.workName = workName;
		this.faxNo = faxNo;
		this.poCode = poCode;
		this.poDate = poDate;
		this.suplierName = suplierName;
		this.suplierEmailId = suplierEmailId;
		this.suplierQuotationNo = suplierQuotationNo;
		this.suplierQuotationDate = suplierQuotationDate;
		this.refrenceNo = refrenceNo;
		this.subject = subject;
		this.cpApprove = cpApprove;
		this.storeInchargeApproval = storeInchargeApproval;
		this.sendForApproval = sendForApproval;
		this.prBy = prBy;
		this.poAttachment = poAttachment;
		this.hideWorkNameOnPdf = hideWorkNameOnPdf;
		this.gstinNo = gstinNo;
		this.accountNo = accountNo;
		this.bankName = bankName;
		this.ifscCode = ifscCode;
		this.branch = branch;
	}

	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public String getPrNo() {
		return prNo;
	}

	public void setPrNo(String prNo) {
		this.prNo = prNo;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
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

	public String getSuplierName() {
		return suplierName;
	}

	public void setSuplierName(String suplierName) {
		this.suplierName = suplierName;
	}

	public String getSuplierEmailId() {
		return suplierEmailId;
	}

	public void setSuplierEmailId(String suplierEmailId) {
		this.suplierEmailId = suplierEmailId;
	}

	public String getSuplierQuotationNo() {
		return suplierQuotationNo;
	}

	public void setSuplierQuotationNo(String suplierQuotationNo) {
		this.suplierQuotationNo = suplierQuotationNo;
	}

	public String getSuplierQuotationDate() {
		return suplierQuotationDate;
	}

	public void setSuplierQuotationDate(String suplierQuotationDate) {
		this.suplierQuotationDate = suplierQuotationDate;
	}

	public String getRefrenceNo() {
		return refrenceNo;
	}

	public void setRefrenceNo(String refrenceNo) {
		this.refrenceNo = refrenceNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCpApprove() {
		return cpApprove;
	}

	public void setCpApprove(String cpApprove) {
		this.cpApprove = cpApprove;
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

	public String getPrBy() {
		return prBy;
	}

	public void setPrBy(String prBy) {
		this.prBy = prBy;
	}

	public byte[] getPoAttachment() {
		return poAttachment;
	}

	public void setPoAttachment(byte[] poAttachment) {
		this.poAttachment = poAttachment;
	}

	public String getHideWorkNameOnPdf() {
		return hideWorkNameOnPdf;
	}

	public void setHideWorkNameOnPdf(String hideWorkNameOnPdf) {
		this.hideWorkNameOnPdf = hideWorkNameOnPdf;
	}

	public String getGstinNo() {
		return gstinNo;
	}

	public void setGstinNo(String gstinNo) {
		this.gstinNo = gstinNo;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

}
