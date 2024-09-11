package com.chs.service;

import java.util.List;

import com.chs.entity.Department;
import com.chs.exception.InvalidDepartmentIdExpception;

public interface DepartmentService{
	Department saveDepartment(Department department);
	Department getDepartmentById(int id) throws InvalidDepartmentIdExpception;
	Department editDepartment(Department department) throws InvalidDepartmentIdExpception;
	Department deleteDepartment(int id) throws InvalidDepartmentIdExpception;
	List<Department> getAllDepartments();
}
