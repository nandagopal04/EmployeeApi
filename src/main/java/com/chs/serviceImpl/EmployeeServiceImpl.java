package com.chs.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chs.converter.DepartmentConverter;
import com.chs.converter.EmployeeConverter;
import com.chs.dto.DepartmentDto;
import com.chs.dto.EmployeeDto;
import com.chs.entity.Department;
import com.chs.entity.Employee;
import com.chs.exception.InvalidEmployeeIdException;
import com.chs.repo.EmployeeRepo;
import com.chs.service.EmployeeService;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	EmployeeConverter employeeConverter;
	
	@Autowired
	DepartmentConverter departmentConverter;

	@Override
	@Transactional
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		Employee employee = employeeConverter.convertEmployeeDtoToEmployee(employeeDto);
		employee = employeeRepo.save(employee);
		return employeeConverter.convertEmployeeToEmployeeDto(employee);
	}

	@Override
	public EmployeeDto findEmployeeById(Long id) throws InvalidEmployeeIdException {
		Optional<Employee> optEmployee = employeeRepo.findById(id);
		if(!optEmployee.isPresent()) {
			throw new InvalidEmployeeIdException("Employee with ID: "+id+" not found");
		}
		return employeeConverter.convertEmployeeToEmployeeDto(optEmployee.get());
	}

	@Override
	@Transactional
	public EmployeeDto editEmployee(EmployeeDto employeeDto) throws InvalidEmployeeIdException {
		Employee employee = employeeConverter.convertEmployeeDtoToEmployee(employeeDto);
		if(!employeeRepo.existsById(employee.getId())) {
			throw new InvalidEmployeeIdException("Invalid employee id: "+employee.getId()+". Please provide a proper employee id");
		}
		employee = employeeRepo.save(employee);
		return employeeConverter.convertEmployeeToEmployeeDto(employee);
	}

	@Override
	@Transactional
	public EmployeeDto deleteEmployee(Long id) throws InvalidEmployeeIdException {
		if(!employeeRepo.existsById(id)) {
			throw new InvalidEmployeeIdException("Invalid Employee ID: "+id);
		}
		EmployeeDto employeeDto = findEmployeeById(id);
		employeeRepo.delete(employeeConverter.convertEmployeeDtoToEmployee(employeeDto));
		return employeeDto;
	}

	@Override
	public List<EmployeeDto> findAllEmployees() {
		List<Employee> allEmployees = employeeRepo.findAll();
		return convertEmployeesToEmployeeDtos(allEmployees);
	}

	@Override
	public List<EmployeeDto> findAllEmployeesByDepartment(DepartmentDto departmentDto) {
		Department department = departmentConverter.convertDepartmentDtoToDepartment(departmentDto);
		List<Employee> allEmployeesInDepartment = employeeRepo.findAllByDepartment(department);
		return convertEmployeesToEmployeeDtos(allEmployeesInDepartment);
	}
	
	private List<EmployeeDto> convertEmployeesToEmployeeDtos(List<Employee> employees){
		return employees
				.stream()
				.map(employeeConverter::convertEmployeeToEmployeeDto)
				.collect(Collectors.toList());
	}
}
