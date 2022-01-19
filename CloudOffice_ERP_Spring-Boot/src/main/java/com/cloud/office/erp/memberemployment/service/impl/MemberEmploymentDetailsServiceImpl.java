package com.cloud.office.erp.memberemployment.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.cloud.office.erp.entity.MemberEmploymentDetails;
import com.cloud.office.erp.memberdetails.dao.MemberDetailsDao;
import com.cloud.office.erp.entity.MemberDetails;
import com.cloud.office.erp.memberemployment.dao.MemberEmploymentDetailsDao;
import com.cloud.office.erp.memberemployment.service.MemberEmploymentDetailsService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class MemberEmploymentDetailsServiceImpl implements MemberEmploymentDetailsService {

	@Autowired
	MemberEmploymentDetailsDao memberEmploymentDetailsDao;
	
	@Autowired
	MemberDetailsDao memberDetailsDao;
	
	@Override
	public MemberEmploymentDetails addMemberEmploymentDetails(MemberEmploymentDetails memberEmploymentDetails) {
		
		MemberEmploymentDetails memberEmploymentDetails_2 = null;
		Optional<MemberDetails> memberDetails = null;
		
		 if(memberEmploymentDetails.getMemberDetails() != null) {
			 memberDetails = memberDetailsDao.findById(memberEmploymentDetails.getMemberDetails().getMemberDetailsId());
		 }		
		 
			 
	if(memberEmploymentDetails_2 == null) {
			 
		if(memberEmploymentDetails != null && memberDetails.isPresent()) {
		
			if(memberEmploymentDetails.getCompanyNames_temp() != null && memberEmploymentDetails.getDescription_temp() != null &&
				memberEmploymentDetails.getCompanyNames_temp().length > 0 && memberEmploymentDetails.getDescription_temp().length > 0) {
				
				for(int i=0; i < memberEmploymentDetails.getCompanyNames_temp().length; i++) {
					
					memberEmploymentDetails_2 = null;
					
					if(memberEmploymentDetails.getMemberEmploymentId_temp()[i] != null) {
						   Long memberEmploymentId = Long.parseLong(memberEmploymentDetails.getMemberEmploymentId_temp()[i]);
						 
						   memberEmploymentDetails_2 = memberEmploymentDetailsDao.findById(memberEmploymentId).get();
					}   
						 
					if(memberEmploymentDetails_2 == null) {
					memberEmploymentDetails_2 = new MemberEmploymentDetails(memberEmploymentDetails.getCompanyNames_temp()[i], memberEmploymentDetails.getStartDate_temp()[i], 
									memberEmploymentDetails.getEndDate_temp()[i], memberEmploymentDetails.getDescription_temp()[i], memberEmploymentDetails.getCtc_temp()[i], memberDetails.get());
					memberEmploymentDetailsDao.save(memberEmploymentDetails_2);
					
					}
					
				
				}
			}
		}
			 
	}
		
		return memberEmploymentDetails_2;
	}

	@Override
	public void deleteMemberEmploymentDetails(Long memberEmploymentDetailsId) {
		
		Optional<MemberEmploymentDetails> memberEmploymentDetails = memberEmploymentDetailsDao.findById(memberEmploymentDetailsId);
		
		memberEmploymentDetailsDao.delete(memberEmploymentDetails.get());
	}

	@Override
	public List<MemberEmploymentDetails> getAllMemberEmploymentDetails() {
		
		return memberEmploymentDetailsDao.findAll();
	}

	@Override
	public Optional<MemberEmploymentDetails> getMemberEmploymentDetailsById(Long memberEmploymentDetailsId) {
		
		return memberEmploymentDetailsDao.findById(memberEmploymentDetailsId);
	}

	@Override
	public List<MemberEmploymentDetails> getMemberEmploymentDetailsByMemberDetailsId(MemberDetails memberDetails) {
		
		List<MemberEmploymentDetails> list = memberEmploymentDetailsDao.getMemberEmploymentDetailsByMemberDetailsId(memberDetails);
		
		list.add(list.size(), new MemberEmploymentDetails("", "", "", "", "", null));
	
		System.out.println("#LIST : "+list);
		
		return list;
	}

}
