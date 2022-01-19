package com.cloud.office.erp.memberdetails.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.cloud.office.erp.entity.MemberDetails;
import com.cloud.office.erp.entity.MemberEmploymentDetails;
import com.cloud.office.erp.entity.UserRole;
import com.cloud.office.erp.memberdetails.dao.MemberDetailsDao;
import com.cloud.office.erp.memberdetails.service.MemberDetailsService;
import com.cloud.office.erp.memberemployment.dao.MemberEmploymentDetailsDao;
import com.cloud.office.erp.user.service.UserService;
import com.cloud.office.erp.userrole.service.UserRoleService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class MemberDetailsServiceImpl implements MemberDetailsService {

	@Autowired
	MemberDetailsDao memberDetailsDao;
	
	@Autowired
	MemberEmploymentDetailsDao memberEmploymentDetailsDao;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRoleService userRoleService;
	
	@Override
	public MemberDetails loadDefaultMemberDetails(UserRole userRole) {
		
		MemberDetails memberDetails = null;
		
		if(!memberDetailsDao.getMemberDetailsByUserLoginId("admin@mail.com").isPresent()) {
			
		    MemberDetails memberDetails_temp = new MemberDetails("admin@mail.com", "Active", "Male", "Mr", "Admin", 
				"2021-01-01", "admin@mail.com", userRole.getUserRole(), "", "H123 Cross Street", "", "7064555457", 
				"Maharashtra", "Mumbai", "416908", "admin@mail.com", "admin@12345", "Permanent", "", "", 
				"", null, null, null, null, null, null);
		    memberDetails_temp.setUserRoleId(userRole);
		    memberDetails = memberDetailsDao.save(memberDetails_temp);
		}
		
		return memberDetails;
	}

	@Override
	public MemberDetails addMemberDetails(MemberDetails memberDetails) {

		if (memberDetails != null) {
			if (memberDetails.getCompanyNames() != null) {

				System.out.println("#SIZE : "+memberDetails.getCompanyNames().length);
				
			MemberDetails details = memberDetails;
	
			List<MemberEmploymentDetails> list = memberEmploymentDetailsDao.getMemberEmploymentDetailsByMemberDetailsId(details);
			if(list != null)
			{
				memberEmploymentDetailsDao.deleteMemberEmploymentDetailsByMemberDetailsId(details);
			}
			
				for (int i=0; i < memberDetails.getCompanyNames().length; i++) {
					
					if(!memberDetails.getCompanyNames()[i].isEmpty() && memberDetails.getCompanyNames()[i] != null)
					{
					  MemberEmploymentDetails memberEmploymentDetails = new MemberEmploymentDetails(memberDetails.getCompanyNames()[i], memberDetails.getStartDate()[i], memberDetails.getEndDate()[i], memberDetails.getDescription()[i], memberDetails.getCtc()[i], memberDetails);
					  memberEmploymentDetailsDao.save(memberEmploymentDetails);
					}
				}
				
			}else {
				
				String userRoleName = (memberDetails.getUserRole() != null)? memberDetails.getUserRole() : "";
				UserRole userRole = userRoleService.getUserRoleByUserRoleName(userRoleName);
				
				memberDetails.setUserRoleId(userRole);
				memberDetails = memberDetailsDao.save(memberDetails);
			}
			
			/*
			 * if(memberDetails.getUserName() != null && memberDetails.getUserPassword() !=
			 * null) {
			 * 
			 * String userRoleName = (memberDetails.getUserRole() != null)?
			 * memberDetails.getUserRole() : ""; UserRole userRole =
			 * userRoleService.getUserRoleByUserRoleName(userRoleName);
			 * 
			 * User user = new User(memberDetails.getUserName(),
			 * memberDetails.getUserPassword()); user.setUserRoleId(userRole);
			 * 
			 * userService.addUser(user); }
			 */
			
		}
		
		return memberDetails;
	}

	@Override
	public void deleteMemberDetails(Long memberDetailsId) {

		Optional<MemberDetails> memberDetails = memberDetailsDao.findById(memberDetailsId);

		if (memberDetails != null) {
			memberDetailsDao.delete(memberDetails.get());
		}
	}

	@Override
	public List<MemberDetails> getAllMemberDetails() {

		return memberDetailsDao.findAll();
	}

	@Override
	public Optional<MemberDetails> getMemberDetailsById(Long memberDetailsId) {

		return memberDetailsDao.findById(memberDetailsId);
	}

	@Override
	public List<MemberDetails> getMemberDetails(Pageable pageable) {
		
		return memberDetailsDao.getMemberDetails(pageable);
	}

	@Override
	public List<MemberDetails> searchMemberDetails(String searchValue) {
		
		return memberDetailsDao.searchMemberDetails(searchValue);
	}

	@Override
	public Optional<MemberDetails> getMemberDetailsByUserLoginId(String emailId, Long userDetailsId) {
		Optional<MemberDetails> optional=null;
		
		optional = (userDetailsId==0L)? 
				memberDetailsDao.getMemberDetailsByUserLoginId(emailId) : 
				memberDetailsDao.getMemberDetailsByEmailIdAndUserId(emailId, userDetailsId);
		
		return optional;
	}

	/*
	 * @Override public ResponseEntity<ByteArrayResource>
	 * uploadMemberProfilePhoto(byte[] memberProfilePhoto, Long memberDetailsId) {
	 * 
	 * return memberDetailsDao.uploadMemberProfilePhoto(memberProfilePhoto,
	 * memberDetailsId); }
	 */
	
}
