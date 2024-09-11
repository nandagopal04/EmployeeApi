package com.chs.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chs.entity.Department;
import com.chs.exception.InvalidDepartmentIdExpception;
import com.chs.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	DepartmentService departmentService;

	public DepartmentController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}

	@Autowired
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	// save department
	@PostMapping("/create")
	public ResponseEntity<Department> saveDepartment(@RequestBody Department department){
		Department savedDepartment = departmentService.saveDepartment(department);
		ResponseEntity<Department> responseEntity = new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
		return responseEntity;
	}
	
	// find department by id
	@GetMapping("/{id}")
	public ResponseEntity<Department> findDepartmentById(@PathVariable int id) throws InvalidDepartmentIdExpception{
		Department department = departmentService.getDepartmentById(id);
		ResponseEntity<Department> responseEntity = new ResponseEntity<Department>(department, HttpStatus.OK);
		return responseEntity;
	}
	
	// find all departments
	@GetMapping("/get/all")
	public ResponseEntity<List<Department>> findAllDepartments(){
		List<Department> allDepartments = departmentService.getAllDepartments();
		ResponseEntity<List<Department>> responseEntity = new ResponseEntity<List<Department>>(allDepartments, HttpStatus.OK);
		return responseEntity;
	}
	
	// edit department
	@PutMapping("/update")
	public ResponseEntity<Department> editDepartment(@RequestBody Department department) throws InvalidDepartmentIdExpception{
		Department editedDepartment = departmentService.editDepartment(department);
		ResponseEntity<Department> responseEntity = new ResponseEntity<Department>(editedDepartment, HttpStatus.OK);
		return responseEntity;
	}
	
	// patch department
	@PatchMapping("/patch")
	public ResponseEntity<Department> patchDepartment(@RequestBody Department department) throws InvalidDepartmentIdExpception{
		Department patchDepartment = departmentService.getDepartmentById(department.getId());
		if(department.getName() != null) {
			patchDepartment.setName(department.getName());
		}
		departmentService.editDepartment(patchDepartment);
		ResponseEntity<Department> responseEntity = new ResponseEntity<Department>(patchDepartment, HttpStatus.OK);
		return responseEntity;
	}
	
	// delete department
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Department> deleteDepartment(@PathVariable int id) throws InvalidDepartmentIdExpception{
		Department deletedDepartment = departmentService.deleteDepartment(id);
		ResponseEntity<Department> responseEntity = new ResponseEntity<Department>(deletedDepartment, HttpStatus.NO_CONTENT);
		return responseEntity;
	}
}
