package com.cloud.office.erp.department.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.cloud.office.erp.entity.Department;

public interface DepartmentService {

	public Department addDepartment(Department department);

	public void deleteDepartment(Long deptId);

	public List<Department> getAllDepartment();

	public Optional<Department> getDepartmentById(Long deptId);

	public List<Department> getDepartment(Pageable pageable);

	public List<Department> searchDepartment(String searchValue);
}
