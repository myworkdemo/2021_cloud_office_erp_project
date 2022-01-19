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
@Table(name = "purchase_order_material_info")
public class PurchaseOrderMaterialInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long purchaseOrderMaterialInfoId;

	private String srNo;
	private String materialType;
	private String materialSubType;
	private String accountCode;
	private String materialName;
	private String description;
	private String umo;
	private String prQty;
	private String requiredDate;
	private String poQty;
	private String balanceQty;
	private String unitPrice;
	private String amount;
	private String remark;

	@Transient
	private String[] purchaseOrderMaterialInfoId_temp;
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
	private String[] umo_temp;
	@Transient
	private String[] prQty_temp;
	@Transient
	private String[] requiredDate_temp;
	@Transient
	private String[] poQty_temp;
	@Transient
	private String[] balanceQty_temp;
	@Transient
	private String[] unitPrice_temp;
	@Transient
	private String[] amount_temp;
	@Transient
	private String[] remark_temp;

	@OneToOne
	@JoinColumn(name = "purchaseOrder")
	private PurchaseOrder purchaseOrder;

	public PurchaseOrderMaterialInfo() {
	}

	public PurchaseOrderMaterialInfo(String srNo, String materialType, String materialSubType, String accountCode,
			String materialName, String description, String umo, String prQty, String requiredDate, String poQty,
			String balanceQty, String unitPrice, String amount, String remark, PurchaseOrder purchaseOrder) {
		super();
		this.srNo = srNo;
		this.materialType = materialType;
		this.materialSubType = materialSubType;
		this.accountCode = accountCode;
		this.materialName = materialName;
		this.description = description;
		this.umo = umo;
		this.prQty = prQty;
		this.requiredDate = requiredDate;
		this.poQty = poQty;
		this.balanceQty = balanceQty;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.remark = remark;
		this.purchaseOrder = purchaseOrder;
	}

	public PurchaseOrderMaterialInfo(String[] srNo_temp, String[] materialType_temp, String[] materialSubType_temp,
			String[] accountCode_temp, String[] materialName_temp, String[] description_temp, String[] umo_temp,
			String[] prQty_temp, String[] requiredDate_temp, String[] poQty_temp, String[] balanceQty_temp,
			String[] unitPrice_temp, String[] amount_temp, String[] remark_temp, PurchaseOrder purchaseOrder) {
		super();
		this.srNo_temp = srNo_temp;
		this.materialType_temp = materialType_temp;
		this.materialSubType_temp = materialSubType_temp;
		this.accountCode_temp = accountCode_temp;
		this.materialName_temp = materialName_temp;
		this.description_temp = description_temp;
		this.umo_temp = umo_temp;
		this.prQty_temp = prQty_temp;
		this.requiredDate_temp = requiredDate_temp;
		this.poQty_temp = poQty_temp;
		this.balanceQty_temp = balanceQty_temp;
		this.unitPrice_temp = unitPrice_temp;
		this.amount_temp = amount_temp;
		this.remark_temp = remark_temp;
		this.purchaseOrder = purchaseOrder;
	}

	public Long getPurchaseOrderMaterialInfoId() {
		return purchaseOrderMaterialInfoId;
	}

	public void setPurchaseOrderMaterialInfoId(Long purchaseOrderMaterialInfoId) {
		this.purchaseOrderMaterialInfoId = purchaseOrderMaterialInfoId;
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

	public String getUmo() {
		return umo;
	}

	public void setUmo(String umo) {
		this.umo = umo;
	}

	public String getPrQty() {
		return prQty;
	}

	public void setPrQty(String prQty) {
		this.prQty = prQty;
	}

	public String getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(String requiredDate) {
		this.requiredDate = requiredDate;
	}

	public String getPoQty() {
		return poQty;
	}

	public void setPoQty(String poQty) {
		this.poQty = poQty;
	}

	public String getBalanceQty() {
		return balanceQty;
	}

	public void setBalanceQty(String balanceQty) {
		this.balanceQty = balanceQty;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
	
	
	//---------------- temp methods---------------------
	
	
	public String[] getPurchaseOrderMaterialInfoId_temp() {
		return purchaseOrderMaterialInfoId_temp;
	}

	public void setPurchaseOrderMaterialInfoId_temp(String[] purchaseOrderMaterialInfoId_temp) {
		this.purchaseOrderMaterialInfoId_temp = purchaseOrderMaterialInfoId_temp;
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

	public String[] getUmo_temp() {
		return umo_temp;
	}

	public void setUmo_temp(String[] umo_temp) {
		this.umo_temp = umo_temp;
	}

	public String[] getPrQty_temp() {
		return prQty_temp;
	}

	public void setPrQty_temp(String[] prQty_temp) {
		this.prQty_temp = prQty_temp;
	}

	public String[] getRequiredDate_temp() {
		return requiredDate_temp;
	}

	public void setRequiredDate_temp(String[] requiredDate_temp) {
		this.requiredDate_temp = requiredDate_temp;
	}

	public String[] getPoQty_temp() {
		return poQty_temp;
	}

	public void setPoQty_temp(String[] poQty_temp) {
		this.poQty_temp = poQty_temp;
	}

	public String[] getBalanceQty_temp() {
		return balanceQty_temp;
	}

	public void setBalanceQty_temp(String[] balanceQty_temp) {
		this.balanceQty_temp = balanceQty_temp;
	}

	public String[] getUnitPrice_temp() {
		return unitPrice_temp;
	}

	public void setUnitPrice_temp(String[] unitPrice_temp) {
		this.unitPrice_temp = unitPrice_temp;
	}

	public String[] getAmount_temp() {
		return amount_temp;
	}

	public void setAmount_temp(String[] amount_temp) {
		this.amount_temp = amount_temp;
	}

	public String[] getRemark_temp() {
		return remark_temp;
	}

	public void setRemark_temp(String[] remark_temp) {
		this.remark_temp = remark_temp;
	}

}
