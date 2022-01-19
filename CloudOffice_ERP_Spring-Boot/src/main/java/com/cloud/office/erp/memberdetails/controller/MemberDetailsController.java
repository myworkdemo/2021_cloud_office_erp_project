package com.cloud.office.erp.memberdetails.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.cloud.office.erp.entity.MemberDetails;
import com.cloud.office.erp.entity.MemberDocumentsDetails;
import com.cloud.office.erp.entity.MemberEmploymentDetails;
import com.cloud.office.erp.memberdetails.service.MemberDetailsService;
import com.cloud.office.erp.memberdoc.service.MemberDocumentsDetailsService;
import com.cloud.office.erp.memberemployment.service.MemberEmploymentDetailsService;
import com.cloud.office.erp.user.service.UserService;

@RestController
@RequestMapping(value = "/member-details")
@CrossOrigin("*")
public class MemberDetailsController {

	@Autowired
	private MemberDetailsService memberDetailsService;
	
	@Autowired
	private MemberEmploymentDetailsService memberEmploymentDetailsService;
	
	@Autowired
	private MemberDocumentsDetailsService memberDocumentsDetailsService;
	
	@Autowired
	UserService userService;
	
	//private Long MEMBER_DETAILS_ID = 0L;
	
	@GetMapping(value = "/get")
	public String getMsg() {
		return "Hello User";
	}

	@PostMapping(value = "/add")
	public MemberDetails addMemberDetails(@RequestBody MemberDetails memberDetails) {

		System.out.println("#addMemberDetails() : " + memberDetails);
		// System.out.println("#addMemberDetails() : "+memberDetails.getProjectNames());
  
		return memberDetailsService.addMemberDetails(memberDetails);
	}

	@PutMapping(value = "/update")
	public MemberDetails updateMemberDetails(@RequestBody MemberDetails memberDetails) {

		return memberDetailsService.addMemberDetails(memberDetails);
	}

	@DeleteMapping(value = "/delete/{memberDetailsId}")
	public void deleteMemberDetails(@PathVariable("memberDetailsId") Long memberDetailsId) {

		memberDetailsService.deleteMemberDetails(memberDetailsId);
	}

	@GetMapping(value = "/getAll")
	public List<MemberDetails> getAllMemberDetails() {

		return memberDetailsService.getAllMemberDetails();
	}

	@GetMapping(value = "/getById/{memberDetailsId}")
	public MemberDetails getMemberDetailsById(@PathVariable("memberDetailsId") String memberDetailsId) {
		
		MemberDetails memberDetails = null;
			
		if(!memberDetailsId.equals("0")) {
			memberDetails = memberDetailsService.getMemberDetailsById(Long.parseLong(memberDetailsId)).get();	
		}
		
		//MemberDetails memberDetails = (object != null && object.isPresent())? object.get() : new MemberDetails();
		
		return memberDetails;
	}
	
	@GetMapping(value = "/getByEmailId/{emailId}/{userId}")
	public MemberDetails getMemberDetailsByEmailId(@PathVariable("emailId") String emailId, @PathVariable("userId") String userId) {
		
		Long userDetailsId = (userId.isEmpty() || userId.length()==0)? 0L : Long.parseLong(userId);
		
		Optional<MemberDetails> object = memberDetailsService.getMemberDetailsByUserLoginId(emailId, userDetailsId);
		
		MemberDetails memberDetails = (object.isPresent())? object.get() : new MemberDetails();
		
		return memberDetails;
	}
	
	@GetMapping(value = "/getMemberEmploymentDetailsList/byMemberId/{memberId}")
	public List<MemberEmploymentDetails> getMemberEmploymentDetailsListByMemberId(@PathVariable("memberId") Long memberId) {

		Optional<MemberDetails> memberDetails = memberDetailsService.getMemberDetailsById(memberId);
		
		List<MemberEmploymentDetails> list = null;
				
		if(memberDetails.isPresent()) {
				list = memberEmploymentDetailsService.getMemberEmploymentDetailsByMemberDetailsId(memberDetails.get());
		}		
		
		return list;
	}

	@GetMapping(value = "/getMemberDetails/{pageNum}/{maxRecords}")
	public List<MemberDetails> getMemberEmploymentDetails(@PathVariable("pageNum") int pageNum, @PathVariable("maxRecords") int maxRecords)
	{
		return memberDetailsService.getMemberDetails(PageRequest.of(pageNum-1, maxRecords));
	}
	
	@GetMapping(value = "/searchMemberDetails/{searchValue}")
	public List<MemberDetails> searchMemberDetails(@PathVariable("searchValue") String searchValue)
	{
		return memberDetailsService.searchMemberDetails(searchValue);
	}
	
	@PostMapping(value = "/uploadMemberDocFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public List<MemberDocumentsDetails> uploadMemberDocFile(@RequestParam("file") MultipartFile multipartFile, 
			@RequestParam("documentType") String documentType, @RequestParam("memberDetailsId") Long memberDetailsId) {

		Optional<MemberDetails> memberDetails = memberDetailsService.getMemberDetailsById(memberDetailsId);
		MemberDocumentsDetails memberDocumentsDetails = null;
		
		  System.out.println("#multipartFile Name : "+multipartFile.getOriginalFilename()); 
		  System.out.println("#ContentType : "+multipartFile.getContentType());
		  System.out.println("#OriginalFilename : "+multipartFile.getOriginalFilename());
		 
		if(memberDetails != null) {
			try {
				memberDocumentsDetails = new MemberDocumentsDetails(multipartFile.getBytes(), documentType, multipartFile.getContentType(), memberDetails.get(), multipartFile.getOriginalFilename());
				memberDocumentsDetailsService.addMemberDocumentsDetails(memberDocumentsDetails);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return memberDocumentsDetailsService.getAllDocumentListByMemberDetailsId(memberDetails.get());
	}
	
	@GetMapping(value = "/downloadFile/{fileId}/{memberDetailsId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("fileId") Long fileId, @PathVariable("memberDetailsId") Long memberDetailsId){
		
		MemberDocumentsDetails documentsDetails = memberDocumentsDetailsService.getMemberDocumentsDetailssById(fileId).get();
		ResponseEntity<ByteArrayResource> response= null;
		
		if(documentsDetails.getMemberDetails().getMemberDetailsId() == memberDetailsId)
		{
		  response = ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(documentsDetails.getDocContentType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\""+documentsDetails.getDocumentType()+"\"")
				.body(new ByteArrayResource(documentsDetails.getDocData()));
		}
		
		return response;
	}
	
	@GetMapping(value = "/getAllDocumentListByMemberDetailsId/{memberDetailsId}")
	public List<MemberDocumentsDetails> getAllDocumentListByMemberDetailsId(@PathVariable("memberDetailsId") Long memberDetailsId)
	{
		MemberDetails memberDetails = memberDetailsService.getMemberDetailsById(memberDetailsId).get();
		
		return memberDocumentsDetailsService.getAllDocumentListByMemberDetailsId(memberDetails);
	}
	
	@GetMapping(value = "/deleteFile/{fileId}/{memberDetailsId}")
	public List<MemberDocumentsDetails> deleteMemberDocumentByDocIdAndMemberId(@PathVariable("fileId") Long fileId, @PathVariable("memberDetailsId") Long memberDetailsId){
		
        MemberDetails memberDetails = memberDetailsService.getMemberDetailsById(memberDetailsId).get();
		
        MemberDocumentsDetails memberDocumentsDetails = memberDocumentsDetailsService.getMemberDocumentsDetailssById(fileId).get();
        
        if(memberDocumentsDetails.getMemberDetails().getMemberDetailsId() == memberDetailsId)
        {
        	  memberDocumentsDetailsService.deleteMemberDocumentsDetails(memberDocumentsDetails);	
        }
        
		return memberDocumentsDetailsService.getAllDocumentListByMemberDetailsId(memberDetails);
	}
	
	@PostMapping(value = "/uploadMemberProfilePhoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public MemberDetails uploadMemberProfilePhoto(@RequestParam("file") MultipartFile multipartFile, 
			String documentType, @RequestParam("memberDetailsId") String memberDetailsId) {

		ResponseEntity<ByteArrayResource> responseEntity=null;
		Optional<MemberDetails> memberDetails_temp = null;
		MemberDetails memberDetails = null;
		
	try{
		    //Long memberId = (memberDetailsId.equals("0"))? MEMBER_DETAILS_ID : Long.parseLong(memberDetailsId);
		
		if(!memberDetailsId.equals("0")) {
			
			memberDetails_temp = memberDetailsService.getMemberDetailsById(Long.parseLong(memberDetailsId));		
		}
			
			memberDetails = (memberDetails_temp != null && memberDetails_temp.isPresent())? memberDetails_temp.get() : new MemberDetails();
		
			memberDetails.setMemberProfilePhoto(multipartFile.getBytes());
			memberDetails.setProfilePhotoContentType(multipartFile.getContentType());
			memberDetails = memberDetailsService.addMemberDetails(memberDetails);	
			
			responseEntity = ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(memberDetails.getProfilePhotoContentType()))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\""+memberDetails.getMemberName()+"\"")
					.body(new ByteArrayResource(memberDetails.getMemberProfilePhoto()));
		
			//MEMBER_DETAILS_ID = memberDetails.getMemberDetailsId();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		
		return memberDetails;
	}
	
	@GetMapping(value = "/getProfilePhoto/{memberDetailsId}")
	public ResponseEntity<ByteArrayResource> getProfilePhoto(@PathVariable("memberDetailsId") String memberDetailsId){
		
		//Long memberId = (memberDetailsId.equals("0"))? MEMBER_DETAILS_ID : Long.parseLong(memberDetailsId);	
		//System.out.println("MEMBER_DETAILS_ID : "+MEMBER_DETAILS_ID);
		ResponseEntity<ByteArrayResource> response = null;
	try {

		//if(!memberDetailsId.equals("0")) {
			
		Optional<MemberDetails> object = memberDetailsService.getMemberDetailsById(Long.parseLong(memberDetailsId));
	
		  MemberDetails memberDetails = object.get();
		  response = ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(memberDetails.getProfilePhotoContentType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\""+memberDetails.getMemberName()+"\"")
				.body(new ByteArrayResource(memberDetails.getMemberProfilePhoto()));
		//}
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}  
		return response;
	}
	
	//uploadMemberProfilePhoto
}
