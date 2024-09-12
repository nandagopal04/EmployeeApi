package com.chs.service;

import java.util.List;

import com.chs.dto.DepartmentDto;
import com.chs.exception.InvalidDepartmentIdExpception;

public interface DepartmentService{
	DepartmentDto saveDepartment(DepartmentDto departmentDto);
	DepartmentDto getDepartmentById(Long id) throws InvalidDepartmentIdExpception;
	DepartmentDto editDepartment(DepartmentDto departmentDto) throws InvalidDepartmentIdExpception;
	DepartmentDto deleteDepartment(Long id) throws InvalidDepartmentIdExpception;
	List<DepartmentDto> getAllDepartments();
}
