package com.chs.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chs.dto.DepartmentDto;
import com.chs.dto.EmployeeDto;
import com.chs.exception.InvalidDepartmentIdExpception;
import com.chs.exception.InvalidEmployeeIdException;
import com.chs.service.DepartmentService;
import com.chs.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	DepartmentService departmentService;
	

	// save employee
	@PostMapping("/create")
	public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);
		ResponseEntity<EmployeeDto> responseEntity = new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
		return responseEntity;
	}

	// find employee by id
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable Long id) throws InvalidEmployeeIdException {
		EmployeeDto findEmployeeDto = employeeService.findEmployeeById(id);
		ResponseEntity<EmployeeDto> responseEntity = new ResponseEntity<EmployeeDto>(findEmployeeDto, HttpStatus.FOUND);
		return responseEntity;
	}

	// find all employees
	@GetMapping("/get/all")
	public ResponseEntity<List<EmployeeDto>> findAllEmployees() {
		List<EmployeeDto> allEmployeeDtos = employeeService.findAllEmployees();
		ResponseEntity<List<EmployeeDto>> responseEntity = new ResponseEntity<List<EmployeeDto>>(allEmployeeDtos, HttpStatus.FOUND);
		return responseEntity;
	}

	// edit employee
	@PutMapping("/update")
	public ResponseEntity<EmployeeDto> editEmployee(@RequestBody EmployeeDto employeeDto) throws InvalidEmployeeIdException {
		EmployeeDto eidtedEmployeeDto = employeeService.editEmployee(employeeDto);
		ResponseEntity<EmployeeDto> responseEntity = new ResponseEntity<EmployeeDto>(eidtedEmployeeDto, HttpStatus.ACCEPTED);
		return responseEntity;
	}

	// delete employee
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable Long id) throws InvalidEmployeeIdException {
		EmployeeDto deletedEmployeeDto = employeeService.deleteEmployee(id);
		ResponseEntity<EmployeeDto> responseEntity = new ResponseEntity<EmployeeDto>(deletedEmployeeDto, HttpStatus.ACCEPTED);
		return responseEntity;
	}

	// patch employee
	@PatchMapping("/patch")
	public ResponseEntity<EmployeeDto> patchEmployee(@RequestBody EmployeeDto employeeDto) throws InvalidEmployeeIdException {
		EmployeeDto existingEmployeeDto = employeeService.findEmployeeById(employeeDto.getId());

		if (employeeDto.getName() != null) {
			existingEmployeeDto.setName(employeeDto.getName());
		}
		if (employeeDto.getSalary() != 0) {
			existingEmployeeDto.setSalary(employeeDto.getSalary());
		}
		if (employeeDto.getDepartment() != null) {
			existingEmployeeDto.setDepartment(employeeDto.getDepartment());
		}
		EmployeeDto savedEmployeeDto = employeeService.saveEmployee(existingEmployeeDto);
		ResponseEntity<EmployeeDto> responseEntity = new ResponseEntity<EmployeeDto>(savedEmployeeDto, HttpStatus.OK);
		return responseEntity;
	}
	
	// find all employees in department
	@GetMapping("/{id}/get")
	public ResponseEntity<List<EmployeeDto>> findAllEmployeesInDepartment(@PathVariable Long id) throws InvalidDepartmentIdExpception{
		DepartmentDto departmentDto = departmentService.getDepartmentById(id);
		List<EmployeeDto> employeeDtos = employeeService.findAllEmployeesByDepartment(departmentDto);
		ResponseEntity<List<EmployeeDto>> responseEntity = new ResponseEntity<List<EmployeeDto>>(employeeDtos, HttpStatus.FOUND);
		return responseEntity;
	}
}
