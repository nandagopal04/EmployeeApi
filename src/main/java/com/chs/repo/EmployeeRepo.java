package com.chs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chs.entity.Department;
import com.chs.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	List<Employee> findAllByDepartment(Department department);
}
