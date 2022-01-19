package com.cloud.office.erp.department.dao;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloud.office.erp.entity.Department;
import com.cloud.office.erp.entity.UserRole;

@Repository
public interface DepartmentDao extends JpaRepository<Department, Long> {

	@Query("FROM Department d ORDER BY d.deptId ASC")
	public List<Department> getDepartment(Pageable pageable);
	
	@Query("SELECT d FROM Department d WHERE d.deptName LIKE :searchValue%")
	public List<Department> searchDepartment(String searchValue);
}
