package com.cloud.office.erp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "master_member_employment_details")
public class MemberEmploymentDetails {

	//@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberEmploymentId;

	private String companyNames;
	private String startDate;
	private String endDate;
	private String description;
	private String ctc;

	@Transient
	private String[] memberEmploymentId_temp;
	@Transient
	private String[] companyNames_temp;
	@Transient
	private String[] startDate_temp;
	@Transient
	private String[] endDate_temp;
	@Transient
	private String[] description_temp;
	@Transient
	private String[] ctc_temp;

	//@JsonIgnore
	@OneToOne
	@JoinColumn(name = "memberDetails")
	private MemberDetails memberDetails;

	public MemberEmploymentDetails() {
	}

	public MemberEmploymentDetails(String companyNames, String startDate, String endDate, String description,
			String ctc, MemberDetails memberDetails) {

		this.companyNames = companyNames;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.ctc = ctc;

		this.memberDetails = memberDetails;
	}

	public Long getMemberEmploymentId() {
		return memberEmploymentId;
	}

	public void setMemberEmploymentId(Long memberEmploymentId) {
		this.memberEmploymentId = memberEmploymentId;
	}

	public MemberDetails getMemberDetails() {
		return memberDetails;
	}

	public void setMemberDetails(MemberDetails memberDetails) {
		this.memberDetails = memberDetails;
	}

	public String getCompanyNames() {
		return companyNames;
	}

	public void setCompanyNames(String companyNames) {
		this.companyNames = companyNames;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCtc() {
		return ctc;
	}

	public void setCtc(String ctc) {
		this.ctc = ctc;
	}

	// temp methods
	
	public String[] getMemberEmploymentId_temp() {
		return memberEmploymentId_temp;
	}

	public void setMemberEmploymentId_temp(String[] memberEmploymentId_temp) {
		this.memberEmploymentId_temp = memberEmploymentId_temp;
	}

	public String[] getCompanyNames_temp() {
		return companyNames_temp;
	}

	public void setCompanyNames_temp(String[] companyNames_temp) {
		this.companyNames_temp = companyNames_temp;
	}

	public String[] getStartDate_temp() {
		return startDate_temp;
	}

	public void setStartDate_temp(String[] startDate_temp) {
		this.startDate_temp = startDate_temp;
	}

	public String[] getEndDate_temp() {
		return endDate_temp;
	}

	public void setEndDate_temp(String[] endDate_temp) {
		this.endDate_temp = endDate_temp;
	}

	public String[] getDescription_temp() {
		return description_temp;
	}

	public void setDescription_temp(String[] description_temp) {
		this.description_temp = description_temp;
	}

	public String[] getCtc_temp() {
		return ctc_temp;
	}

	public void setCtc_temp(String[] ctc_temp) {
		this.ctc_temp = ctc_temp;
	}

}
