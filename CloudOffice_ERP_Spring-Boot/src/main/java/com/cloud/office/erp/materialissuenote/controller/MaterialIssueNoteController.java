package com.cloud.office.erp.materialissuenote.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cloud.office.erp.entity.MaterialIssueNote;
import com.cloud.office.erp.materialissuenote.service.MaterialIssueNoteService;

@RestController
@RequestMapping(value = "/material-issue-note")
@CrossOrigin("*")
public class MaterialIssueNoteController {

	@Autowired
	MaterialIssueNoteService materialIssueNoteService;
	
	
	@PostMapping(value = "/add")
	public MaterialIssueNote addMaterialIssueNote(@RequestBody MaterialIssueNote materialIssueNote) {

		return materialIssueNoteService.addMaterialIssueNote(materialIssueNote);
	}

	@PutMapping(value = "/update")
	public MaterialIssueNote updateMaterialIssueNote(@RequestBody MaterialIssueNote materialIssueNote) {

		return materialIssueNoteService.updateMaterialIssueNote(materialIssueNote);
	}

	@DeleteMapping(value = "/delete/{materialIssueNoteId}")
	public void deleteMaterialIssueNote(@PathVariable("materialIssueNoteId") Long materialIssueNoteId) {

		materialIssueNoteService.deleteMaterialIssueNote(materialIssueNoteId);
	}

	@GetMapping(value = "/getAll")
	public List<MaterialIssueNote> getAllMaterialIssueNote() {

		return materialIssueNoteService.getAllMaterialIssueNote();
	}

	@GetMapping(value = "/getById/{materialIssueNoteId}")
	public Optional<MaterialIssueNote> getMaterialIssueNoteById(@PathVariable("materialIssueNoteId") Long materialIssueNoteId) {

		return materialIssueNoteService.getMaterialIssueNoteById(materialIssueNoteId);
	}
	
	@PostMapping(value = "/getBySearchValues")
	public List<MaterialIssueNote> getAllMaterialIssueNoteBySearch(@RequestBody MaterialIssueNote materialIssueNote) {

		return materialIssueNoteService.getAllMaterialIssueNoteBySearch(materialIssueNote);
	}
	
}
