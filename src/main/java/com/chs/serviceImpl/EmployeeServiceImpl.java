package com.chs.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chs.entity.Department;
import com.chs.entity.Employee;
import com.chs.exception.InvalidEmployeeIdException;
import com.chs.repo.EmployeeRepo;
import com.chs.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	EmployeeRepo employeeRepo;
	
	@Autowired
	public void setEmployeeRepo(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
		super();
		this.employeeRepo = employeeRepo;
	}

	public EmployeeServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

	@Override
	public Employee findEmployeeById(int id) throws InvalidEmployeeIdException {
		Optional<Employee> optEmployee = employeeRepo.findById(id);
		if(!optEmployee.isPresent()) {
			throw new InvalidEmployeeIdException("Employee with ID: "+id+" not found");
		}
		return optEmployee.get();
	}

	@Override
	public Employee editEmployee(Employee employee) throws InvalidEmployeeIdException {
		findEmployeeById(employee.getId());
		return employeeRepo.save(employee);
	}

	@Override
	public Employee deleteEmployee(int id) throws InvalidEmployeeIdException {
		Employee employee = findEmployeeById(id);
		employeeRepo.delete(employee);
		return employee;
	}

	@Override
	public List<Employee> findAllEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public List<Employee> findAllEmployeesByDepartment(Department department) {
		return employeeRepo.findAllByDepartment(department);
	}
	
}
