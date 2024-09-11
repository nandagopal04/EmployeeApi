package com.chs.service;

import java.util.List;

import com.chs.entity.Department;
import com.chs.entity.Employee;
import com.chs.exception.InvalidEmployeeIdException;

public interface EmployeeService {
	Employee saveEmployee(Employee employee);
	Employee findEmployeeById(int id) throws InvalidEmployeeIdException;
	Employee editEmployee(Employee employee) throws InvalidEmployeeIdException;
	Employee deleteEmployee(int id) throws InvalidEmployeeIdException;
	List<Employee> findAllEmployees();
	List<Employee> findAllEmployeesByDepartment(Department department);
}
