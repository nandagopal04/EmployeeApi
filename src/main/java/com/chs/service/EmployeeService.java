package com.chs.service;

import java.util.List;

import com.chs.dto.DepartmentDto;
import com.chs.dto.EmployeeDto;
import com.chs.exception.InvalidEmployeeIdException;

public interface EmployeeService {
	EmployeeDto saveEmployee(EmployeeDto employeeDto);
	EmployeeDto findEmployeeById(Long id) throws InvalidEmployeeIdException;
	EmployeeDto editEmployee(EmployeeDto employeeDto) throws InvalidEmployeeIdException;
	EmployeeDto deleteEmployee(Long id) throws InvalidEmployeeIdException;
	List<EmployeeDto> findAllEmployees();
	List<EmployeeDto> findAllEmployeesByDepartment(DepartmentDto departmentDto);
}
