package com.chs.dto;

import org.springframework.stereotype.Component;

import com.chs.entity.Department;

import lombok.Data;

@Data
@Component
public class EmployeeDto {

	Long id;
	String name;
	Long salary;
	Department department;
	
}
