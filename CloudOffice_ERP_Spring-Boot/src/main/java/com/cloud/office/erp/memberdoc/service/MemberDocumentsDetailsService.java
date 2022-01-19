package com.cloud.office.erp.memberdoc.service;

import java.util.List;
import java.util.Optional;

import com.cloud.office.erp.entity.MemberDetails;
import com.cloud.office.erp.entity.MemberDocumentsDetails;

public interface MemberDocumentsDetailsService {

	public MemberDocumentsDetails addMemberDocumentsDetails(MemberDocumentsDetails memberDocumentsDetails);

	public void deleteMemberDocumentsDetails(MemberDocumentsDetails memberDocumentsDetails);

	public List<MemberDocumentsDetails> getAllMemberDocumentsDetails();

	public Optional<MemberDocumentsDetails> getMemberDocumentsDetailssById(Long memberDocDetailsId);
	
	public List<MemberDocumentsDetails> getAllDocumentListByMemberDetailsId(MemberDetails memberDetails);
}
