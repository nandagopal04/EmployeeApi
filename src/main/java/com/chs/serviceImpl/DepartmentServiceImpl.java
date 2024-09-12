package com.chs.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chs.converter.DepartmentConverter;
import com.chs.dto.DepartmentDto;
import com.chs.entity.Department;
import com.chs.exception.InvalidDepartmentIdExpception;
import com.chs.repo.DepartmentRepo;
import com.chs.service.DepartmentService;

import jakarta.transaction.Transactional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Autowired
	private DepartmentConverter departmentConverter;
	
	@Transactional
	@Override
	public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
		Department department = departmentConverter.convertDepartmentDtoToDepartment(departmentDto);
		department = departmentRepo.save(department);
		return departmentConverter.convertDepartmentToDepartmentDto(department);
	}
	
	@Override
	public DepartmentDto getDepartmentById(Long id) throws InvalidDepartmentIdExpception {
		Optional<Department> optDepartment = departmentRepo.findById(id);
		if(!optDepartment.isPresent()) {
			throw new InvalidDepartmentIdExpception("Invalid Department ID: "+id);
		}
		return departmentConverter.convertDepartmentToDepartmentDto(optDepartment.get());
	}
	
	@Transactional
	@Override
	public DepartmentDto editDepartment(DepartmentDto departmentDto) throws InvalidDepartmentIdExpception {
		Department department = departmentConverter.convertDepartmentDtoToDepartment(departmentDto);
		if(!departmentRepo.existsById(department.getId())) {
			throw new InvalidDepartmentIdExpception("Invalid department ID: "+department.getId());
		}
		department = departmentRepo.save(department);
		return departmentConverter.convertDepartmentToDepartmentDto(department);
	}
	
	@Transactional
	@Override
	public DepartmentDto deleteDepartment(Long id) throws InvalidDepartmentIdExpception{
		Department department = departmentConverter.convertDepartmentDtoToDepartment(getDepartmentById(id));
		if(!departmentRepo.existsById(department.getId())) {
			throw new InvalidDepartmentIdExpception("Invalid department ID: "+department.getId());
		}
		departmentRepo.delete(department);
		return departmentConverter.convertDepartmentToDepartmentDto(department);
	}
	
	@Override
	public List<DepartmentDto> getAllDepartments(){
		List<Department> allDepartments = departmentRepo.findAll();
		return allDepartments
				.stream()
				.map(departmentConverter::convertDepartmentToDepartmentDto)
				.collect(Collectors.toList());
	}

}
