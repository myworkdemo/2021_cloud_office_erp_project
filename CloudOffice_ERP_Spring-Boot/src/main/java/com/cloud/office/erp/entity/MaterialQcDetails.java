package com.cloud.office.erp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "meterialQc_details")
public class MaterialQcDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long meterialQcDetailsId;

	private String srNo;
	private String materialType;
	private String materialSubType;
	private String accountCode;
	private String materialName;
	private String description;
	private String unitPrice;
	private String deliveredQty;
	private String approvedQty;
	private String rejectedQty;
	private String balanceQty;
	private String remark;

	@Transient
	private String[]  meterialQcDetailsId_temp;
	@Transient
	private String[] srNo_temp;
	@Transient
	private String[] materialType_temp;
	@Transient
	private String[] materialSubType_temp;
	@Transient
	private String[] accountCode_temp;
	@Transient
	private String[] materialName_temp;
	@Transient
	private String[] description_temp;
	@Transient
	private String[] unitPrice_temp;
	@Transient
	private String[] deliveredQty_temp;
	@Transient
	private String[] approvedQty_temp;
	@Transient
	private String[] rejectedQty_temp;
	@Transient
	private String[] balanceQty_temp;
	@Transient
	private String[] remark_temp;
	
	@OneToOne
	@JoinColumn(name = "materialQc")
	private MaterialQc materialQc;

	public MaterialQcDetails() {
	}

	public MaterialQcDetails(String srNo, String materialType, String materialSubType, String accountCode,
			String materialName, String description, String unitPrice, String deliveredQty, String approvedQty,
			String rejectedQty, String balanceQty, String remark, MaterialQc materialQc) {
		super();
		this.srNo = srNo;
		this.materialType = materialType;
		this.materialSubType = materialSubType;
		this.accountCode = accountCode;
		this.materialName = materialName;
		this.description = description;
		this.unitPrice = unitPrice;
		this.deliveredQty = deliveredQty;
		this.approvedQty = approvedQty;
		this.rejectedQty = rejectedQty;
		this.balanceQty = balanceQty;
		this.remark = remark;
		
		this.materialQc = materialQc;
	}

	public Long getMeterialQcDetailsId() {
		return meterialQcDetailsId;
	}

	public void setMeterialQcDetailsId(Long meterialQcDetailsId) {
		this.meterialQcDetailsId = meterialQcDetailsId;
	}

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
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

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
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

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDeliveredQty() {
		return deliveredQty;
	}

	public void setDeliveredQty(String deliveredQty) {
		this.deliveredQty = deliveredQty;
	}

	public String getApprovedQty() {
		return approvedQty;
	}

	public void setApprovedQty(String approvedQty) {
		this.approvedQty = approvedQty;
	}

	public String getRejectedQty() {
		return rejectedQty;
	}

	public void setRejectedQty(String rejectedQty) {
		this.rejectedQty = rejectedQty;
	}

	public String getBalanceQty() {
		return balanceQty;
	}

	public void setBalanceQty(String balanceQty) {
		this.balanceQty = balanceQty;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	//---------------- temp methods---------------------
	
	public String[] getMeterialQcDetailsId_temp() {
		return meterialQcDetailsId_temp;
	}

	public void setMeterialQcDetailsId_temp(String[] meterialQcDetailsId_temp) {
		this.meterialQcDetailsId_temp = meterialQcDetailsId_temp;
	}

	public String[] getSrNo_temp() {
		return srNo_temp;
	}

	public void setSrNo_temp(String[] srNo_temp) {
		this.srNo_temp = srNo_temp;
	}

	public String[] getMaterialType_temp() {
		return materialType_temp;
	}

	public void setMaterialType_temp(String[] materialType_temp) {
		this.materialType_temp = materialType_temp;
	}

	public String[] getMaterialSubType_temp() {
		return materialSubType_temp;
	}

	public void setMaterialSubType_temp(String[] materialSubType_temp) {
		this.materialSubType_temp = materialSubType_temp;
	}

	public String[] getAccountCode_temp() {
		return accountCode_temp;
	}

	public void setAccountCode_temp(String[] accountCode_temp) {
		this.accountCode_temp = accountCode_temp;
	}

	public String[] getMaterialName_temp() {
		return materialName_temp;
	}

	public void setMaterialName_temp(String[] materialName_temp) {
		this.materialName_temp = materialName_temp;
	}

	public String[] getDescription_temp() {
		return description_temp;
	}

	public void setDescription_temp(String[] description_temp) {
		this.description_temp = description_temp;
	}

	public String[] getUnitPrice_temp() {
		return unitPrice_temp;
	}

	public void setUnit_Price_temp(String[] unitPrice_temp) {
		this.unitPrice_temp = unitPrice_temp;
	}

	public String[] getDeliveredQty_temp() {
		return deliveredQty_temp;
	}

	public void setDeliveredQty_temp(String[] deliveredQty_temp) {
		this.deliveredQty_temp = deliveredQty_temp;
	}

	public String[] getApprovedQty_temp() {
		return approvedQty_temp;
	}

	public void setApprovedQty_temp(String[] approvedQty_temp) {
		this.approvedQty_temp = approvedQty_temp;
	}

	public String[] getRejectedQty_temp() {
		return rejectedQty_temp;
	}

	public void setRejectedQty_temp(String[] rejectedQty_temp) {
		this.rejectedQty_temp = rejectedQty_temp;
	}

	public String[] getBalanceQty_temp() {
		return balanceQty_temp;
	}

	public void setBalanceQty_temp(String[] balanceQty_temp) {
		this.balanceQty_temp = balanceQty_temp;
	}

	public String[] getRemark_temp() {
		return remark_temp;
	}

	public void setRemark_temp(String[] remark_temp) {
		this.remark_temp = remark_temp;
	}

	public MaterialQc getMaterialQc() {
		return materialQc;
	}

	public void setMaterialQc(MaterialQc materialQc) {
		this.materialQc = materialQc;
	}
	
}
