package com.cloud.office.erp.memberemployment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.office.erp.entity.MemberEmploymentDetails;
import com.cloud.office.erp.memberemployment.service.MemberEmploymentDetailsService;

@RestController
@RequestMapping(value = "/member-employment-details")
@CrossOrigin("*")
public class MemberEmploymentDetailsController {

	@Autowired
	MemberEmploymentDetailsService employmentDetailsService;

	@PostMapping(value = "/add")
	public MemberEmploymentDetails addMemberEmploymentDetails(@RequestBody MemberEmploymentDetails memberEmploymentDetails) {

		return employmentDetailsService.addMemberEmploymentDetails(memberEmploymentDetails);
	}

	@DeleteMapping(value = "/delete/{memberEmploymentId}")
	public void deleteMemberEmploymentDetails(@PathVariable Long memberEmploymentId) {

		employmentDetailsService.deleteMemberEmploymentDetails(memberEmploymentId);
	}

	public List<MemberEmploymentDetails> getAllMemberEmploymentDetails() {

		return employmentDetailsService.getAllMemberEmploymentDetails();
	}

	public Optional<MemberEmploymentDetails> getMemberEmploymentDetailsById(Long memberEmploymentDetailsId) {

		return employmentDetailsService.getMemberEmploymentDetailsById(memberEmploymentDetailsId);
	}
}
