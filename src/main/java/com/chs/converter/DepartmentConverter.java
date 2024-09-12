package com.chs.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.chs.dto.DepartmentDto;
import com.chs.entity.Department;

@Configuration
public class DepartmentConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public DepartmentDto convertDepartmentToDepartmentDto(Department department) {
		DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
		return departmentDto;
	}
	
	public Department convertDepartmentDtoToDepartment(DepartmentDto departmentDto) {
		Department department = modelMapper.map(departmentDto, Department.class);
		return department;
	}
	
}
