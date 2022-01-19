package com.cloud.office.erp.memberdetails.service;

import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.cloud.office.erp.entity.MemberDetails;
import com.cloud.office.erp.entity.UserRole;

public interface MemberDetailsService {

	public MemberDetails loadDefaultMemberDetails(UserRole userRole);
	
	public MemberDetails addMemberDetails(MemberDetails memberDetails);

	public void deleteMemberDetails(Long memberDetailsId);

	public List<MemberDetails> getAllMemberDetails();

	public Optional<MemberDetails> getMemberDetailsById(Long memberDetailsId);
	
	public List<MemberDetails> getMemberDetails(Pageable pageable);
	
	public List<MemberDetails> searchMemberDetails(String searchValue);

	public Optional<MemberDetails> getMemberDetailsByUserLoginId(String emailId, Long userDetailsId);

	//public ResponseEntity<ByteArrayResource> uploadMemberProfilePhoto(byte[] memberProfilePhoto, Long memberDetailsId);
}
