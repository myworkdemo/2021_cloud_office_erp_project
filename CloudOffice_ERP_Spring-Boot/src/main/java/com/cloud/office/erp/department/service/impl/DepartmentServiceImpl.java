package com.cloud.office.erp.department.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.office.erp.department.dao.DepartmentDao;
import com.cloud.office.erp.department.service.DepartmentService;
import com.cloud.office.erp.entity.Department;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentDao departmentDao;

	@Override
	public Department addDepartment(Department department) {

		if (department.getDeptId() != null) {
			department.setDeptId(department.getDeptId());
			return departmentDao.save(department);
		} else {
			return departmentDao.save(department);
		}
	}

	@Override
	public void deleteDepartment(Long deptId) {

		Optional<Department> department = departmentDao.findById(deptId);
		if (department != null) {
			departmentDao.delete(department.get());
		}
	}

	@Override
	public List<Department> getAllDepartment() {

		return departmentDao.findAll();
	}

	@Override
	public Optional<Department> getDepartmentById(Long deptId) {

		return departmentDao.findById(deptId);
	}

	@Override
	public List<Department> getDepartment(Pageable pageable) {
		
		return departmentDao.getDepartment(pageable);
	}

	@Override
	public List<Department> searchDepartment(String searchValue) {
		
		return departmentDao.searchDepartment(searchValue);
	}

}
