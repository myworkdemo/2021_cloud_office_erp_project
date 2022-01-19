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
@Table(name = "purchase_requisition_material_info")
public class PurchaseRequisitionMaterialInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long prMaterialInfoId;
	private String srNo;
	private String materialType;
	private String materialSubType;
	private String materialName;
	private String description;
	private String accountCode;
	private String umo;
	private String requiredQty;
	private String stock;
	private String deliveryRequirement;
	private String actualCostPerUnit;
	private String actualTotal;
	private String sellingPricePerUnit;
	private String prRemark;

	@Transient
	private String[] prMaterialInfoId_temp;
	@Transient
	private String[] srNo_temp;
	@Transient
	private String[] materialType_temp;
	@Transient
	private String[] materialSubType_temp;
	@Transient
	private String[] materialName_temp;
	@Transient
	private String[] description_temp;
	@Transient
	private String[] accountCode_temp;
	@Transient
	private String[] umo_temp;
	@Transient
	private String[] requiredQty_temp;
	@Transient
	private String[] stock_temp;
	@Transient
	private String[] deliveryRequirement_temp;
	@Transient
	private String[] actualCostPerUnit_temp;
	@Transient
	private String[] actualTotal_temp;
	@Transient
	private String[] sellingPricePerUnit_temp;
	@Transient
	private String[] prRemark_temp;
	
	@OneToOne
	@JoinColumn(name = "purchaseRequisitionInfo")
	private PurchaseRequisitionInfo purchaseRequisitionInfo;

	public PurchaseRequisitionMaterialInfo() {
	}

	public PurchaseRequisitionMaterialInfo(String srNo, String materialType, String materialSubType,
			String materialName, String description, String accountCode, String umo, String requiredQty, String stock,
			String deliveryRequirement, String actualCostPerUnit, String actualTotal, String sellingPricePerUnit,
			String prRemark, PurchaseRequisitionInfo purchaseRequisitionInfo) {
		super();
		this.srNo = srNo;
		this.materialType = materialType;
		this.materialSubType = materialSubType;
		this.materialName = materialName;
		this.description = description;
		this.accountCode = accountCode;
		this.umo = umo;
		this.requiredQty = requiredQty;
		this.stock = stock;
		this.deliveryRequirement = deliveryRequirement;
		this.actualCostPerUnit = actualCostPerUnit;
		this.actualTotal = actualTotal;
		this.sellingPricePerUnit = sellingPricePerUnit;
		this.prRemark = prRemark;
		
		this.purchaseRequisitionInfo = purchaseRequisitionInfo;
	}

	public Long getPrMaterialInfoId() {
		return prMaterialInfoId;
	}

	public void setPrMaterialInfoId(Long prMaterialInfoId) {
		this.prMaterialInfoId = prMaterialInfoId;
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

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getUmo() {
		return umo;
	}

	public void setUmo(String umo) {
		this.umo = umo;
	}

	public String getRequiredQty() {
		return requiredQty;
	}

	public void setRequiredQty(String requiredQty) {
		this.requiredQty = requiredQty;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getDeliveryRequirement() {
		return deliveryRequirement;
	}

	public void setDeliveryRequirement(String deliveryRequirement) {
		this.deliveryRequirement = deliveryRequirement;
	}

	public String getActualCostPerUnit() {
		return actualCostPerUnit;
	}

	public void setActualCostPerUnit(String actualCostPerUnit) {
		this.actualCostPerUnit = actualCostPerUnit;
	}

	public String getActualTotal() {
		return actualTotal;
	}

	public void setActualTotal(String actualTotal) {
		this.actualTotal = actualTotal;
	}

	public String getSellingPricePerUnit() {
		return sellingPricePerUnit;
	}

	public void setSellingPricePerUnit(String sellingPricePerUnit) {
		this.sellingPricePerUnit = sellingPricePerUnit;
	}

	public String getPrRemark() {
		return prRemark;
	}

	public void setPrRemark(String prRemark) {
		this.prRemark = prRemark;
	}
	
	public PurchaseRequisitionInfo getPurchaseRequisitionInfo() {
		return purchaseRequisitionInfo;
	}

	public void setPurchaseRequisitionInfo(PurchaseRequisitionInfo purchaseRequisitionInfo) {
		this.purchaseRequisitionInfo = purchaseRequisitionInfo;
	}
	
	
	// temp methods

	
	public String[] getPrMaterialInfoId_temp() {
		return prMaterialInfoId_temp;
	}

	public void setPrMaterialInfoId_temp(String[] prMaterialInfoId_temp) {
		this.prMaterialInfoId_temp = prMaterialInfoId_temp;
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

	public String[] getAccountCode_temp() {
		return accountCode_temp;
	}

	public void setAccountCode_temp(String[] accountCode_temp) {
		this.accountCode_temp = accountCode_temp;
	}

	public String[] getUmo_temp() {
		return umo_temp;
	}

	public void setUmo_temp(String[] umo_temp) {
		this.umo_temp = umo_temp;
	}

	public String[] getRequiredQty_temp() {
		return requiredQty_temp;
	}

	public void setRequiredQty_temp(String[] requiredQty_temp) {
		this.requiredQty_temp = requiredQty_temp;
	}

	public String[] getStock_temp() {
		return stock_temp;
	}

	public void setStock_temp(String[] stock_temp) {
		this.stock_temp = stock_temp;
	}

	public String[] getDeliveryRequirement_temp() {
		return deliveryRequirement_temp;
	}

	public void setDeliveryRequirement_temp(String[] deliveryRequirement_temp) {
		this.deliveryRequirement_temp = deliveryRequirement_temp;
	}

	public String[] getActualCostPerUnit_temp() {
		return actualCostPerUnit_temp;
	}

	public void setActualCostPerUnit_temp(String[] actualCostPerUnit_temp) {
		this.actualCostPerUnit_temp = actualCostPerUnit_temp;
	}

	public String[] getActualTotal_temp() {
		return actualTotal_temp;
	}

	public void setActualTotal_temp(String[] actualTotal_temp) {
		this.actualTotal_temp = actualTotal_temp;
	}

	public String[] getSellingPricePerUnit_temp() {
		return sellingPricePerUnit_temp;
	}

	public void setSellingPricePerUnit_temp(String[] sellingPricePerUnit_temp) {
		this.sellingPricePerUnit_temp = sellingPricePerUnit_temp;
	}

	public String[] getPrRemark_temp() {
		return prRemark_temp;
	}

	public void setPrRemark_temp(String[] prRemark_temp) {
		this.prRemark_temp = prRemark_temp;
	}

}
