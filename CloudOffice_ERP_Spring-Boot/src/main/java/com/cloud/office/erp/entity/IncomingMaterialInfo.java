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
@Table(name = "incoming_material_info")
public class IncomingMaterialInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long incomingMaterialInfoId;

	private String srNo;
	private String materialType;
	private String materialSubType;
	private String materialName;
	private String description;
	private String accountCode;
	private String umo;
	private Integer prQty;
	private Integer poQty;
	private Integer balanceQty;
	private Integer deliveredQty;
	private Double unitRate;
	private Double totalLandingCost;

	@Transient
	private String[] incomingMaterialInfoId_temp;
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
	private String[] prQty_temp;
	@Transient
	private String[] poQty_temp;
	@Transient
	private String[] balanceQty_temp;
	@Transient
	private String[] deliveredQty_temp;
	@Transient
	private String[] unitRate_temp;
	@Transient
	private String[] totalLandingCost_temp;

	@OneToOne
	@JoinColumn(name = "incoming_material_fk")
	private IncomingMaterial incomingMaterial;

	public IncomingMaterialInfo() {
	}

	public IncomingMaterialInfo(String srNo, String materialType, String materialSubType, String materialName,
			String description, String accountCode, String umo, Integer prQty, Integer poQty, Integer balanceQty,
			Integer deliveredQty, Double unitRate, Double totalLandingCost, IncomingMaterial incomingMaterial) {
		super();
		this.srNo = srNo;
		this.materialType = materialType;
		this.materialSubType = materialSubType;
		this.materialName = materialName;
		this.description = description;
		this.accountCode = accountCode;
		this.umo = umo;
		this.prQty = prQty;
		this.poQty = poQty;
		this.balanceQty = balanceQty;
		this.deliveredQty = deliveredQty;
		this.unitRate = unitRate;
		this.totalLandingCost = totalLandingCost;
		
		this.incomingMaterial = incomingMaterial;
	}

	public Long getIncomingMaterialInfoId() {
		return incomingMaterialInfoId;
	}

	public void setIncomingMaterialInfoId(Long incomingMaterialInfoId) {
		this.incomingMaterialInfoId = incomingMaterialInfoId;
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

	public Integer getPrQty() {
		return prQty;
	}

	public void setPrQty(Integer prQty) {
		this.prQty = prQty;
	}

	public Integer getPoQty() {
		return poQty;
	}

	public void setPoQty(Integer poQty) {
		this.poQty = poQty;
	}

	public Integer getBalanceQty() {
		return balanceQty;
	}

	public void setBalanceQty(Integer balanceQty) {
		this.balanceQty = balanceQty;
	}

	public Integer getDeliveredQty() {
		return deliveredQty;
	}

	public void setDeliveredQty(Integer deliveredQty) {
		this.deliveredQty = deliveredQty;
	}

	public Double getUnitRate() {
		return unitRate;
	}

	public void setUnitRate(Double unitRate) {
		this.unitRate = unitRate;
	}

	public Double getTotalLandingCost() {
		return totalLandingCost;
	}

	public void setTotalLandingCost(Double totalLandingCost) {
		this.totalLandingCost = totalLandingCost;
	}

	public String[] getIncomingMaterialInfoId_temp() {
		return incomingMaterialInfoId_temp;
	}

	public void setIncomingMaterialInfoId_temp(String[] incomingMaterialInfoId_temp) {
		this.incomingMaterialInfoId_temp = incomingMaterialInfoId_temp;
	}

	// temp methods

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

	public String[] getPrQty_temp() {
		return prQty_temp;
	}

	public void setPrQty_temp(String[] prQty_temp) {
		this.prQty_temp = prQty_temp;
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

	public String[] getDeliveredQty_temp() {
		return deliveredQty_temp;
	}

	public void setDeliveredQty_temp(String[] deliveredQty_temp) {
		this.deliveredQty_temp = deliveredQty_temp;
	}

	public String[] getUnitRate_temp() {
		return unitRate_temp;
	}

	public void setUnitRate_temp(String[] unitRate_temp) {
		this.unitRate_temp = unitRate_temp;
	}

	public String[] getTotalLandingCost_temp() {
		return totalLandingCost_temp;
	}

	public void setTotalLandingCost_temp(String[] totalLandingCost_temp) {
		this.totalLandingCost_temp = totalLandingCost_temp;
	}

	public IncomingMaterial getIncomingMaterial() {
		return incomingMaterial;
	}

	public void setIncomingMaterial(IncomingMaterial incomingMaterial) {
		this.incomingMaterial = incomingMaterial;
	}

}
