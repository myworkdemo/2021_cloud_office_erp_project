package com.cloud.office.erp.materialissuenote.service;

import java.util.List;
import java.util.Optional;

import com.cloud.office.erp.entity.MaterialIssueNote;

public interface MaterialIssueNoteService {

	public MaterialIssueNote addMaterialIssueNote(MaterialIssueNote materialIssueNote);

	public MaterialIssueNote updateMaterialIssueNote(MaterialIssueNote materialIssueNote);

	public void deleteMaterialIssueNote(Long materialIssueNoteId);

	public List<MaterialIssueNote> getAllMaterialIssueNote();

	public Optional<MaterialIssueNote> getMaterialIssueNoteById(Long materialIssueNoteId);

	public List<MaterialIssueNote> getAllMaterialIssueNoteBySearch(MaterialIssueNote materialIssueNote);
}
