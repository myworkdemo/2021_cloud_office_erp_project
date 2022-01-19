package com.cloud.office.erp.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "master_member_details")
public class MemberDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberDetailsId;
	private String alternateEmailId;
	//private String reportTo;
	private String memberStatus;
	private String gender;
	//private String maritalStatus;
	private String salutation;
	private String memberName;
	private String dateOfBirth;
	//private String bloodGroup;
	private String email;
	
	@Column(name = "memberProfilePhoto", columnDefinition = "LONGTEXT")
	private byte[] memberProfilePhoto;
	private String profilePhotoContentType;
	
	private String userRole;
	private String leaveApproverName;
	private String costPerHour;
	private String workInDepartment;

	private String address;
	private String alternateMobileNumber;
	private String mobileNumber;
	private String state;
	private String city;
	private String pincode;
	//private String empergenceyNumber;

	private String userLoginId;
	private String userPassword;

	private String memberType;
	private String contractThrough;
	private String contractorName;
	private String plannedStartDate;
	private String plannedEndDate;
	//private String rate;
	//private String pre;

	@Transient
	private String[] companyNames;
	@Transient
	private String[] startDate;
	@Transient
	private String[] endDate;
	@Transient
	private String[] description;
	@Transient
	private String[] ctc;
	
	@OneToOne
	@JoinColumn(name = "userRoleId")
	private UserRole userRoleId;

	public MemberDetails() {

	}

	
	public MemberDetails(String alternateEmailId, /* String reportTo, */ String memberStatus, String gender,
			/* String maritalStatus, */ String salutation, String memberName, String dateOfBirth, /*String bloodGroup,*/
			String email, String userRole, /*String leaveApproverName,*/ /*String costPerHour,*/ String workInDepartment,
			String address, String alternateMobileNumber, String mobileNumber, String state, String city, String pincode,
			/* String empergenceyNumber, */ String userLoginId, String userPassword, String memberType, String contractThrough,
			String contractorName, String plannedStartDate, String plannedEndDate, /* String rate, String pre, */
			String[] companyNames, String[] startDate, String[] endDate, String[] description, String[] ctc) {
		
		this.alternateEmailId = alternateEmailId;
		//this.reportTo = reportTo;
		this.memberStatus = memberStatus;
		this.gender = gender;
		//this.maritalStatus = maritalStatus;
		this.salutation = salutation;
		this.memberName = memberName;
		this.dateOfBirth = dateOfBirth;
		//this.bloodGroup = bloodGroup;
		this.email = email;
		this.userRole = userRole;
		//this.leaveApproverName = leaveApproverName;
		//this.costPerHour = costPerHour;
		this.workInDepartment = workInDepartment;
		this.address = address;
		this.alternateMobileNumber = alternateMobileNumber;
		this.mobileNumber = mobileNumber;
		this.state = state;
		this.city = city;
		this.pincode = pincode;
		//this.empergenceyNumber = empergenceyNumber;
		this.userLoginId = userLoginId;
		this.userPassword = userPassword;
		this.memberType = memberType;
		this.contractThrough = contractThrough;
		this.contractorName = contractorName;
		this.plannedStartDate = plannedStartDate;
		this.plannedEndDate = plannedEndDate;
		/*
		 * this.rate = rate; this.pre = pre;
		 */
		this.companyNames = companyNames;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.ctc = ctc;
	}

	

	public Long getMemberDetailsId() {
		return memberDetailsId;
	}

	public void setMemberDetailsId(Long memberDetailsId) {
		this.memberDetailsId = memberDetailsId;
	}

	public String getAlternateEmailId() {
		return alternateEmailId;
	}

	public void setAlternateEmailId(String alternateEmailId) {
		this.alternateEmailId = alternateEmailId;
	}

	/*
	 * public String getReportTo() { return reportTo; }
	 * 
	 * public void setReportTo(String reportTo) { this.reportTo = reportTo; }
	 */

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	/*
	 * public String getMaritalStatus() { return maritalStatus; }
	 * 
	 * public void setMaritalStatus(String maritalStatus) { this.maritalStatus =
	 * maritalStatus; }
	 */

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/*
	 * public String getBloodGroup() { return bloodGroup; }
	 * 
	 * public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup;
	 * }
	 */

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public byte[] getMemberProfilePhoto() {
		return memberProfilePhoto;
	}

	public void setMemberProfilePhoto(byte[] memberProfilePhoto) {
		this.memberProfilePhoto = memberProfilePhoto;
	}
	
	public String getProfilePhotoContentType() {
		return profilePhotoContentType;
	}

	public void setProfilePhotoContentType(String profilePhotoContentType) {
		this.profilePhotoContentType = profilePhotoContentType;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getLeaveApproverName() {
		return leaveApproverName;
	}

	public void setLeaveApproverName(String leaveApproverName) {
		this.leaveApproverName = leaveApproverName;
	}

	public String getCostPerHour() {
		return costPerHour;
	}

	public void setCostPerHour(String costPerHour) {
		this.costPerHour = costPerHour;
	}

	public String getWorkInDepartment() {
		return workInDepartment;
	}

	public void setWorkInDepartment(String workInDepartment) {
		this.workInDepartment = workInDepartment;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAlternateMobileNumber() {
		return alternateMobileNumber;
	}

	public void setAlternateMobileNumber(String alternateMobileNumber) {
		this.alternateMobileNumber = alternateMobileNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	/*
	 * public String getEmpergenceyNumber() { return empergenceyNumber; }
	 * 
	 * public void setEmpergenceyNumber(String empergenceyNumber) {
	 * this.empergenceyNumber = empergenceyNumber; }
	 */

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public String getUserLoginId() {
		return userLoginId;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getContractThrough() {
		return contractThrough;
	}

	public void setContractThrough(String contractThrough) {
		this.contractThrough = contractThrough;
	}

	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public String getPlannedStartDate() {
		return plannedStartDate;
	}

	public void setPlannedStartDate(String plannedStartDate) {
		this.plannedStartDate = plannedStartDate;
	}

	public String getPlannedEndDate() {
		return plannedEndDate;
	}

	public void setPlannedEndDate(String plannedEndDate) {
		this.plannedEndDate = plannedEndDate;
	}

	/*
	 * public String getRate() { return rate; }
	 * 
	 * public void setRate(String rate) { this.rate = rate; }
	 * 
	 * public String getPre() { return pre; }
	 * 
	 * public void setPre(String pre) { this.pre = pre; }
	 */

	public String[] getCompanyNames() {
		return companyNames;
	}

	public void setCompanyNames(String[] companyNames) {
		this.companyNames = companyNames;
	}

	public String[] getStartDate() {
		return startDate;
	}

	public void setStartDate(String[] startDate) {
		this.startDate = startDate;
	}

	public String[] getEndDate() {
		return endDate;
	}

	public void setEndDate(String[] endDate) {
		this.endDate = endDate;
	}

	public String[] getDescription() {
		return description;
	}

	public void setDescription(String[] description) {
		this.description = description;
	}
	
	public String[] getCtc() {
		return ctc;
	}

	public void setCtc(String[] ctc) {
		this.ctc = ctc;
	}

	public UserRole getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(UserRole userRoleId) {
		this.userRoleId = userRoleId;
	}

	@Override
	public String toString() {
		return "MemberDetails [memberDetailsId=" + memberDetailsId + ", alternateEmailId=" + alternateEmailId
				+ /* ", reportTo=" + reportTo + */ ", memberStatus=" + memberStatus + ", gender=" + gender
				+ /* ", maritalStatus=" + maritalStatus + */ ", salutation=" + salutation + ", memberName=" + memberName
				+ ", dateOfBirth=" + dateOfBirth + /* ", bloodGroup=" + bloodGroup + */ ", email=" + email + ", userRole="
				+ userRole + ", leaveApproverName=" + leaveApproverName + ", costPerHour=" + costPerHour
				+ ", workInDepartment=" + workInDepartment + ", address=" + address + ", alternateMobileNumber=" + alternateMobileNumber
				+ ", mobileNumber=" + mobileNumber + ", state=" + state + ", city=" + city + ", pincode=" + pincode
				+ /* ", empergenceyNumber=" + empergenceyNumber + */ ", userLoginId=" + userLoginId + ", userPassword="
				+ userPassword + ", memberType=" + memberType + ", contractThrough=" + contractThrough
				+ ", contractorName=" + contractorName + ", plannedStartDate=" + plannedStartDate + ", plannedEndDate="
				+ plannedEndDate + /* ", rate=" + rate + ", pre=" + pre + */ ", companyNames=" + Arrays.toString(companyNames)
				+ ", startDate=" + Arrays.toString(startDate) + ", endDate=" + Arrays.toString(endDate)
				+ ", description=" + Arrays.toString(description) + ", ctc=" + Arrays.toString(ctc) + "]";
	}

}
