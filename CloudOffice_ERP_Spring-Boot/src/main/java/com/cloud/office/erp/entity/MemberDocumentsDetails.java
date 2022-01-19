package com.cloud.office.erp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "master_member_documents_details")
public class MemberDocumentsDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberDocumentsId;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "memberDetails_fk")
	private MemberDetails memberDetails;

	private String documentType;
	private String docContentType;

	@Column(name = "docData", columnDefinition = "LONGTEXT")
	private byte[] docData;

	private String docOriginalName;

	public MemberDocumentsDetails() {

	}

	public MemberDocumentsDetails(byte[] docData, String documentType, String docContentType, MemberDetails memberDetails,
			String docOriginalName) {
		this.docData = docData;
		this.documentType = documentType;
		this.docContentType = docContentType;
		this.memberDetails = memberDetails;
		this.docOriginalName = docOriginalName;
	}

	public Long getMemberDocumentsId() {
		return memberDocumentsId;
	}

	public void setMemberDocumentsId(Long memberDocumentsId) {
		this.memberDocumentsId = memberDocumentsId;
	}

	public MemberDetails getMemberDetails() {
		return memberDetails;
	}

	public void setMemberDetails(MemberDetails memberDetails) {
		this.memberDetails = memberDetails;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocContentType() {
		return docContentType;
	}

	public void setDocContentType(String docContentType) {
		this.docContentType = docContentType;
	}

	public byte[] getDocData() {
		return docData;
	}

	public void setDocData(byte[] docData) {
		this.docData = docData;
	}

	public String getDocOriginalName() {
		return docOriginalName;
	}

	public void setDocOriginalName(String docOriginalName) {
		this.docOriginalName = docOriginalName;
	}

}
