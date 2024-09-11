package com.chs.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chs.entity.Department;
import com.chs.exception.InvalidDepartmentIdExpception;
import com.chs.repo.DepartmentRepo;
import com.chs.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	DepartmentRepo departmentRepo;

	@Autowired
	public void setDepartmentRepo(DepartmentRepo departmentRepo) {
		this.departmentRepo = departmentRepo;
	}

	public DepartmentServiceImpl(DepartmentRepo departmentRepo) {
		super();
		this.departmentRepo = departmentRepo;
	}

	public DepartmentServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Department saveDepartment(Department department) {
		return departmentRepo.save(department);
	}
	@Override
	public Department getDepartmentById(int id) throws InvalidDepartmentIdExpception {
		Optional<Department> optDepartment = departmentRepo.findById(id);
		if(!optDepartment.isPresent()) {
			throw new InvalidDepartmentIdExpception("Invalid Department ID: "+id);
		}
		return optDepartment.get();
	}
	@Override
	public Department editDepartment(Department department) throws InvalidDepartmentIdExpception {
		getDepartmentById(department.getId());
		return departmentRepo.save(department);
	}
	@Override
	public Department deleteDepartment(int id) throws InvalidDepartmentIdExpception{
		Department department = getDepartmentById(id);
		departmentRepo.delete(department);
		return department;
	}
	@Override
	public List<Department> getAllDepartments(){
		return departmentRepo.findAll();
	}

}
