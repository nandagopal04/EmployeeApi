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

import com.chs.dto.DepartmentDto;
import com.chs.exception.InvalidDepartmentIdExpception;
import com.chs.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	// save department
	@PostMapping("/create")
	public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
		DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
		ResponseEntity<DepartmentDto> responseEntity = new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
		return responseEntity;
	}
	
	// find department by id
	@GetMapping("/{id}")
	public ResponseEntity<DepartmentDto> findDepartmentById(@PathVariable Long id) throws InvalidDepartmentIdExpception{
		DepartmentDto departmentDto = departmentService.getDepartmentById(id);
		ResponseEntity<DepartmentDto> responseEntity = new ResponseEntity<DepartmentDto>(departmentDto, HttpStatus.OK);
		return responseEntity;
	}
	
	// find all departments
	@GetMapping("/get/all")
	public ResponseEntity<List<DepartmentDto>> findAllDepartments(){
		List<DepartmentDto> allDepartments = departmentService.getAllDepartments();
		ResponseEntity<List<com.chs.dto.DepartmentDto>> responseEntity = new ResponseEntity<List<DepartmentDto>>(allDepartments, HttpStatus.OK);
		return responseEntity;
	}
	
	// edit department
	@PutMapping("/update")
	public ResponseEntity<DepartmentDto> editDepartment(@RequestBody DepartmentDto departmentDto) throws InvalidDepartmentIdExpception{
		DepartmentDto editedDepartment = departmentService.editDepartment(departmentDto);
		ResponseEntity<DepartmentDto> responseEntity = new ResponseEntity<DepartmentDto>(editedDepartment, HttpStatus.OK);
		return responseEntity;
	}
	
	// patch department
	@PatchMapping("/patch")
	public ResponseEntity<DepartmentDto> patchDepartment(@RequestBody DepartmentDto departmentDto) throws InvalidDepartmentIdExpception{
		DepartmentDto patchDepartmentDto = departmentService.getDepartmentById(departmentDto.getId());
		if(departmentDto.getName() != null) {
			patchDepartmentDto.setName(departmentDto.getName());
		}
		departmentService.editDepartment(patchDepartmentDto);
		ResponseEntity<DepartmentDto> responseEntity = new ResponseEntity<DepartmentDto>(patchDepartmentDto, HttpStatus.OK);
		return responseEntity;
	}
	
	// delete department
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<DepartmentDto> deleteDepartment(@PathVariable Long id) throws InvalidDepartmentIdExpception{
		DepartmentDto deletedDepartmentDto = departmentService.deleteDepartment(id);
		ResponseEntity<DepartmentDto> responseEntity = new ResponseEntity<DepartmentDto>(deletedDepartmentDto, HttpStatus.NO_CONTENT);
		return responseEntity;
	}
}
