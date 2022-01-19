package com.cloud.office.erp.memberemployment.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.office.erp.entity.MemberDetails;
import com.cloud.office.erp.entity.MemberEmploymentDetails;

@Repository
public interface MemberEmploymentDetailsDao extends JpaRepository<MemberEmploymentDetails, Long> {

	@Query("FROM MemberEmploymentDetails WHERE memberDetails= :memberDetails")
	public List<MemberEmploymentDetails> getMemberEmploymentDetailsByMemberDetailsId(MemberDetails memberDetails);

	@Transactional
	@Modifying
	@Query("DELETE FROM MemberEmploymentDetails WHERE memberDetails= :memberDetails")
	public void deleteMemberEmploymentDetailsByMemberDetailsId(MemberDetails memberDetails);
}
