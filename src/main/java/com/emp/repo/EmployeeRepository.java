package com.emp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emp.dto.EmployeeDto;
import com.emp.entity.Employees;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long>{

	EmployeeDto save(EmployeeDto emp);

}
