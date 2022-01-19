package com.cloud.office.erp.materialissuenote.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloud.office.erp.entity.MaterialIssueNote;


@Repository
public interface MaterialIssueNoteDao extends JpaRepository<MaterialIssueNote, Long>{

	//AND (:departmentName IS NULL OR mi.departmentName LIKE %:departmentName%) AND (:workName IS NULL OR mi.workName LIKE %:workName%)
	@Query("FROM MaterialIssueNote mi WHERE (:departmentName IS NULL OR mi.departmentName LIKE %:departmentName%) AND (:workName IS NULL OR mi.workName LIKE %:workName%)")
	public List<MaterialIssueNote> getAllMaterialIssueNoteBySearch(String departmentName, String workName);

	@Query("FROM MaterialIssueNote mi WHERE (:departmentName IS NULL OR mi.departmentName LIKE %:departmentName%) AND (:workName IS NULL OR mi.workName LIKE %:workName%)"
			+ " AND mi.minDate BETWEEN :startDate AND :endDate")
	public List<MaterialIssueNote> getAllMaterialIssueNoteBySearch(String departmentName, String workName, Date startDate, Date endDate);

}
