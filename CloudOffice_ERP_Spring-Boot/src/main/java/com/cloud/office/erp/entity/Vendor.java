package com.cloud.office.erp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "master_vender")
public class Vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vendorId;
	private String vendorName;
	private String emailId;
	private String mobileNo;
	private String contactPersonName;
	private String website;

	private String address;
	private String landlineNo;
	private String city;
	private String state;
	private String pincode;
	private String country;
	/*
	 * private String region; private String subregion; private String globalregion;
	 */

	private String faxNo;
	private String panNo;
	//private String cstNo;
	private String gstNo;

	public Vendor() {
	}

	public Vendor(String vendorName, String emailId, String mobileNo, String contactPersonName, String website,
			String address, String landlineNo, String city, String state, String pincode, String country, String region,
			/* String subregion, String globalregion, */ String faxNo, String panNo, /* String cstNo, */ String gstNo) {

		this.vendorName = vendorName;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.contactPersonName = contactPersonName;
		this.website = website;
		this.address = address;
		this.landlineNo = landlineNo;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.country = country;
		/*
		 * this.region = region; this.subregion = subregion; this.globalregion =
		 * globalregion;
		 */
		this.faxNo = faxNo;
		this.panNo = panNo;
		//this.cstNo = cstNo;
		this.gstNo = gstNo;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLandlineNo() {
		return landlineNo;
	}

	public void setLandlineNo(String landlineNo) {
		this.landlineNo = landlineNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	/*
	 * public String getRegion() { return region; }
	 * 
	 * public void setRegion(String region) { this.region = region; }
	 * 
	 * public String getSubregion() { return subregion; }
	 * 
	 * public void setSubregion(String subregion) { this.subregion = subregion; }
	 * 
	 * public String getGlobalregion() { return globalregion; }
	 * 
	 * public void setGlobalregion(String globalregion) { this.globalregion =
	 * globalregion; }
	 */

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	/*
	 * public String getCstNo() { return cstNo; }
	 * 
	 * public void setCstNo(String cstNo) { this.cstNo = cstNo; }
	 */

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

}
