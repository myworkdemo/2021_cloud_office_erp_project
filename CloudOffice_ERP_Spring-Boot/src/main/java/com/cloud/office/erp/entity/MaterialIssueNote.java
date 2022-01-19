package com.cloud.office.erp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "master_material_issue_note")
public class MaterialIssueNote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long materialIssueNoteId;

	private String minNo;
	private String workName;
	
	@Temporal(TemporalType.DATE)
	private Date minDate;
	private String departmentName;
	
	@Transient
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Transient
	@Temporal(TemporalType.DATE)
	private Date endDate;

	public MaterialIssueNote() {
	}

	public MaterialIssueNote(String minNo, String workName, Date minDate, String departmentName) {
		super();
		this.minNo = minNo;
		this.workName = workName;
		this.minDate = minDate;
		this.departmentName = departmentName;
	}

	public Long getMaterialIssueNoteId() {
		return materialIssueNoteId;
	}

	public void setMaterialIssueNoteId(Long materialIssueNoteId) {
		this.materialIssueNoteId = materialIssueNoteId;
	}

	public String getMinNo() {
		return minNo;
	}

	public void setMinNo(String minNo) {
		this.minNo = minNo;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	

}
