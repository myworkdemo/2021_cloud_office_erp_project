package com.cloud.office.erp.memberemployment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.cloud.office.erp.entity.MemberDetails;
import com.cloud.office.erp.entity.MemberEmploymentDetails;

public interface MemberEmploymentDetailsService {

	public MemberEmploymentDetails addMemberEmploymentDetails(MemberEmploymentDetails memberEmploymentDetails);

	public void deleteMemberEmploymentDetails(Long memberEmploymentDetailsId);

	public List<MemberEmploymentDetails> getAllMemberEmploymentDetails();
	
	public Optional<MemberEmploymentDetails> getMemberEmploymentDetailsById(Long memberEmploymentDetailsId);
	
	public List<MemberEmploymentDetails> getMemberEmploymentDetailsByMemberDetailsId(MemberDetails memberDetails);
}
