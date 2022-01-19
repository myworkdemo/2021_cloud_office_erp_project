package com.cloud.office.erp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "incoming_material")
public class IncomingMaterial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long incomingMaterialId;
	private String incomingRegisterCode;
	private String incomingRegisterDate;
	private String workName;
	private String supplierName;
	private String mrnNo;
	private String suplierInvoiceNo;
	private String suplierInvoiceDate;
	private String departmentName;
	private String fileAttachment;

	@OneToOne
	@JoinColumn(name = "vendorId_fk")
	private Vendor vendor;

	@Column(name = "invoiceAttachment", columnDefinition = "LONGTEXT")
	private byte invoiceAttachment[];
	@Column(name = "otherAttachment", columnDefinition = "LONGTEXT")
	private byte otherAttachment[];

	public IncomingMaterial() {
	}

	public IncomingMaterial(String incomingRegisterCode, String incomingRegisterDate, String workName,
			String supplierName, String mrnNo, String suplierInvoiceNo, String suplierInvoiceDate,
			String departmentName, String fileAttachment, byte[] invoiceAttachment, byte[] otherAttachment) {
		super();
		this.incomingRegisterCode = incomingRegisterCode;
		this.incomingRegisterDate = incomingRegisterDate;
		this.workName = workName;
		this.supplierName = supplierName;
		this.mrnNo = mrnNo;
		this.suplierInvoiceNo = suplierInvoiceNo;
		this.suplierInvoiceDate = suplierInvoiceDate;
		this.departmentName = departmentName;
		this.fileAttachment = fileAttachment;
		this.invoiceAttachment = invoiceAttachment;
		this.otherAttachment = otherAttachment;
	}

	public Long getIncomingMaterialId() {
		return incomingMaterialId;
	}

	public void setIncomingMaterialId(Long incomingMaterialId) {
		this.incomingMaterialId = incomingMaterialId;
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

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getMrnNo() {
		return mrnNo;
	}

	public void setMrnNo(String mrnNo) {
		this.mrnNo = mrnNo;
	}

	public String getSuplierInvoiceNo() {
		return suplierInvoiceNo;
	}

	public void setSuplierInvoiceNo(String suplierInvoiceNo) {
		this.suplierInvoiceNo = suplierInvoiceNo;
	}

	public String getSuplierInvoiceDate() {
		return suplierInvoiceDate;
	}

	public void setSuplierInvoiceDate(String suplierInvoiceDate) {
		this.suplierInvoiceDate = suplierInvoiceDate;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getFileAttachment() {
		return fileAttachment;
	}

	public void setFileAttachment(String fileAttachment) {
		this.fileAttachment = fileAttachment;
	}

	public byte[] getInvoiceAttachment() {
		return invoiceAttachment;
	}

	public void setInvoiceAttachment(byte[] invoiceAttachment) {
		this.invoiceAttachment = invoiceAttachment;
	}

	public byte[] getOtherAttachment() {
		return otherAttachment;
	}

	public void setOtherAttachment(byte[] otherAttachment) {
		this.otherAttachment = otherAttachment;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

}
