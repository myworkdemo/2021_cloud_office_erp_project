package com.cloud.office.erp.memberdoc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.office.erp.entity.MemberDetails;
import com.cloud.office.erp.entity.MemberDocumentsDetails;
import com.cloud.office.erp.memberdoc.dao.MemberDocumentsDetailsDao;
import com.cloud.office.erp.memberdoc.service.MemberDocumentsDetailsService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class MemberDocumentsDetailsServiceImpl implements MemberDocumentsDetailsService {

	@Autowired
	MemberDocumentsDetailsDao memberDocumentsDetailsDao;
	
	@Override
	public MemberDocumentsDetails addMemberDocumentsDetails(MemberDocumentsDetails memberDocumentsDetails) {
		
		return memberDocumentsDetailsDao.save(memberDocumentsDetails);
	}

	@Override
	public void deleteMemberDocumentsDetails(MemberDocumentsDetails memberDocumentsDetails) {
		
		memberDocumentsDetailsDao.delete(memberDocumentsDetails);
	}

	@Override
	public List<MemberDocumentsDetails> getAllMemberDocumentsDetails() {
		
		return memberDocumentsDetailsDao.findAll();
	}

	@Override
	public Optional<MemberDocumentsDetails> getMemberDocumentsDetailssById(Long memberDocDetailsId) {
		
		return memberDocumentsDetailsDao.findById(memberDocDetailsId);
	}

	@Override
	public List<MemberDocumentsDetails> getAllDocumentListByMemberDetailsId(MemberDetails memberDetails) {
		
		return memberDocumentsDetailsDao.getAllDocumentListByMemberDetailsId(memberDetails);
	}

}
