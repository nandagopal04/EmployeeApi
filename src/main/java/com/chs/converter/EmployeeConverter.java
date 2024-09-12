package com.chs.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.chs.dto.EmployeeDto;
import com.chs.entity.Employee;

@Configuration
public class EmployeeConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public EmployeeDto convertEmployeeToEmployeeDto(Employee employee) {
		EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
		return employeeDto;
	}
	
	public Employee convertEmployeeDtoToEmployee(EmployeeDto employeeDto) {
		Employee employee = modelMapper.map(employeeDto, Employee.class);
		return employee;
	}
	
}
