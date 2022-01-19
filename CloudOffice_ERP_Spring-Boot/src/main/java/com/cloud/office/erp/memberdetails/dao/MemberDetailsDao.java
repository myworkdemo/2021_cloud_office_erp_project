package com.cloud.office.erp.memberdetails.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.cloud.office.erp.entity.MemberDetails;
import com.cloud.office.erp.entity.UserRole;

@Repository
public interface MemberDetailsDao extends JpaRepository<MemberDetails, Long>{
	
	@Query("FROM MemberDetails md ORDER BY md.memberDetailsId ASC")
	public List<MemberDetails> getMemberDetails(Pageable pageable);
	
	@Query("SELECT md FROM MemberDetails md WHERE md.memberName LIKE :searchValue%")
	public List<MemberDetails> searchMemberDetails(String searchValue);

	@Query("FROM MemberDetails md WHERE md.userLoginId = :userLoginId")
	public Optional<MemberDetails> getMemberDetailsByUserLoginId(String userLoginId);
	
	@Query("FROM MemberDetails md WHERE md.userLoginId = :userLoginId AND NOT md.memberDetailsId = :memberDetailsId")
	public Optional<MemberDetails> getMemberDetailsByEmailIdAndUserId(String userLoginId, Long memberDetailsId);

	/*
	 * @Query("UPDATE MemberDetails md SET md.memberProfilePhoto = :memberProfilePhoto WHERE md.memberDetailsId = :memberDetailsId"
	 * ) public ResponseEntity<ByteArrayResource> uploadMemberProfilePhoto(byte[]
	 * memberProfilePhoto, Long memberDetailsId);
	 */
}
