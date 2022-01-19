package com.cloud.office.erp.memberdoc.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloud.office.erp.entity.MemberDetails;
import com.cloud.office.erp.entity.MemberDocumentsDetails;

@Repository
public interface MemberDocumentsDetailsDao extends JpaRepository<MemberDocumentsDetails, Long>{

	@Query("FROM MemberDocumentsDetails WHERE memberDetails= :memberDetails")
	public List<MemberDocumentsDetails> getAllDocumentListByMemberDetailsId(MemberDetails memberDetails);
}
