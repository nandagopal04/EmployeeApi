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

import com.chs.entity.Department;
import com.chs.entity.Employee;
import com.chs.exception.InvalidDepartmentIdExpception;
import com.chs.exception.InvalidEmployeeIdException;
import com.chs.service.DepartmentService;
import com.chs.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	EmployeeService employeeService;
	DepartmentService departmentService;
	
	@Autowired
	public void setDepartmentService(DepartmentService departService) {
		this.departmentService = departService;
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public EmployeeController(EmployeeService employeeService, DepartmentService departService) {
		super();
		this.employeeService = employeeService;
		this.departmentService = departService;
	}

	public EmployeeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	// save employee
	@PostMapping("/create")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		Employee savedEmployee = employeeService.saveEmployee(employee);
		ResponseEntity<Employee> responseEntity = new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
		return responseEntity;
	}

	// find employee by id
	@GetMapping("/{id}")
	public ResponseEntity<Employee> findEmployeeById(@PathVariable int id) throws InvalidEmployeeIdException {
		Employee findEmployee = employeeService.findEmployeeById(id);
		ResponseEntity<Employee> responseEntity = new ResponseEntity<Employee>(findEmployee, HttpStatus.FOUND);
		return responseEntity;
	}

	// find all employees
	@GetMapping("/get/all")
	public ResponseEntity<List<Employee>> findAllEmployees() {
		List<Employee> allEmployees = employeeService.findAllEmployees();
		ResponseEntity<List<Employee>> responseEntity = new ResponseEntity<List<Employee>>(allEmployees,
				HttpStatus.FOUND);
		return responseEntity;
	}

	// edit employee
	@PutMapping("/update")
	public ResponseEntity<Employee> editEmployee(@RequestBody Employee employee) throws InvalidEmployeeIdException {
		Employee eidtedEmployee = employeeService.editEmployee(employee);
		ResponseEntity<Employee> responseEntity = new ResponseEntity<Employee>(eidtedEmployee, HttpStatus.ACCEPTED);
		return responseEntity;
	}

	// delete employee
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable int id) throws InvalidEmployeeIdException {
		Employee deletedEmployee = employeeService.deleteEmployee(id);
		ResponseEntity<Employee> responseEntity = new ResponseEntity<Employee>(deletedEmployee, HttpStatus.ACCEPTED);
		return responseEntity;
	}

	// patch employee
	@PatchMapping("/patch")
	public ResponseEntity<Employee> patchEmployee(@RequestBody Employee employee) throws InvalidEmployeeIdException {
		Employee existingEmployee = employeeService.findEmployeeById(employee.getId());

		if (employee.getName() != null) {
			existingEmployee.setName(employee.getName());
		}
		if (employee.getSalary() != 0) {
			existingEmployee.setSalary(employee.getSalary());
		}
		if (employee.getDepartment() != null) {
			existingEmployee.setDepartment(employee.getDepartment());
		}
		Employee savedEmployee = employeeService.saveEmployee(existingEmployee);
		ResponseEntity<Employee> responseEntity = new ResponseEntity<Employee>(savedEmployee, HttpStatus.OK);
		return responseEntity;
	}
	
	// find all employees in department
	@GetMapping("/{id}/get")
	public ResponseEntity<List<Employee>> findAllEmployeesInDepartment(@PathVariable int id) throws InvalidDepartmentIdExpception{
		Department department = departmentService.getDepartmentById(id);
		List<Employee> employees = employeeService.findAllEmployeesByDepartment(department);
		ResponseEntity<List<Employee>> responseEntity = new ResponseEntity<List<Employee>>(employees, HttpStatus.FOUND);
		return responseEntity;
	}
}
