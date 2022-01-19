package com.cloud.office.erp.materialissuenote.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.cloud.office.erp.entity.MaterialIssueNote;
import com.cloud.office.erp.materialissuenote.dao.MaterialIssueNoteDao;
import com.cloud.office.erp.materialissuenote.service.MaterialIssueNoteService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class MaterialIssueNoteServiceImpl implements MaterialIssueNoteService {

	@Autowired
	MaterialIssueNoteDao materialIssueNoteDao;
	
	@Override
	public MaterialIssueNote addMaterialIssueNote(MaterialIssueNote materialIssueNote) {
		
		return materialIssueNoteDao.save(materialIssueNote);
	}

	@Override
	public MaterialIssueNote updateMaterialIssueNote(MaterialIssueNote materialIssueNote) {
		
		return materialIssueNoteDao.save(materialIssueNote);
	}

	@Override
	public void deleteMaterialIssueNote(Long materialIssueNoteId) {
		
		Optional<MaterialIssueNote> materialIssueNote = materialIssueNoteDao.findById(materialIssueNoteId);
		materialIssueNoteDao.delete(materialIssueNote.get());
		
	}

	@Override
	public List<MaterialIssueNote> getAllMaterialIssueNote() {
		
		return materialIssueNoteDao.findAll();
	}

	@Override
	public Optional<MaterialIssueNote> getMaterialIssueNoteById(Long materialIssueNoteId) {
		
		return materialIssueNoteDao.findById(materialIssueNoteId);
	}

	@Override
	public List<MaterialIssueNote> getAllMaterialIssueNoteBySearch(MaterialIssueNote materialIssueNote) {
		
		List<MaterialIssueNote> list = null;
		
		if(materialIssueNote.getStartDate() != null && materialIssueNote.getEndDate() != null) {
		   list = materialIssueNoteDao.getAllMaterialIssueNoteBySearch(materialIssueNote.getDepartmentName(), materialIssueNote.getWorkName(), materialIssueNote.getStartDate(), materialIssueNote.getEndDate());	
		}else {
			list = materialIssueNoteDao.getAllMaterialIssueNoteBySearch(materialIssueNote.getDepartmentName(), materialIssueNote.getWorkName());
		}
		
		return list;
	}

}
