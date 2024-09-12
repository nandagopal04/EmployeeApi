package com.chs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chs.entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {

}
