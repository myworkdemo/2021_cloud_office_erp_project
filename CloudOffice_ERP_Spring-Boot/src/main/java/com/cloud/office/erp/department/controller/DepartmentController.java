package com.cloud.office.erp.department.controller;

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

import com.cloud.office.erp.department.service.DepartmentService;
import com.cloud.office.erp.entity.Department;
import com.cloud.office.erp.entity.UserRole;

@RestController
@CrossOrigin(origins =  "*")
@RequestMapping(value = "/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping(value = "/test")
	public String testJwt() {
		return "JWT";
	}

	@PostMapping(value = "/add")
	public Department addDepartment(@RequestBody Department department) {

		return  departmentService.addDepartment(department);
	}

	@PutMapping(value = "/update")
	public Department updateDepartment(@RequestBody Department department) {

		System.out.println(department.getDeptId());
		return departmentService.addDepartment(department);
	}

	@DeleteMapping(value = "/delete/{deptId}")
	public void deleteDepartment(@PathVariable("deptId") Long deptId) {

		departmentService.deleteDepartment(deptId);
	}

	@GetMapping(value = "/getAll")
	public List<Department> getAllDepartment() {

		return departmentService.getAllDepartment();
	}

	@GetMapping(value = "/getById/{deptId}")
	public Department getDepartmentById(@PathVariable("deptId") Long deptId) {

		Optional<Department> department = departmentService.getDepartmentById(deptId);
		return department.get();
	}
	
	@GetMapping(value = "/getDepartment/{pageNum}/{maxRecords}")
	public List<Department> getUserRole(@PathVariable("pageNum") int pageNum, @PathVariable("maxRecords") int maxRecords)
	{
		return departmentService.getDepartment(PageRequest.of(pageNum-1, maxRecords));
	}
	
	@GetMapping(value = "/searchDepartment/{searchValue}")
	public List<Department> searchUserRole(@PathVariable("searchValue") String searchValue)
	{
		return departmentService.searchDepartment(searchValue);
	}

}
