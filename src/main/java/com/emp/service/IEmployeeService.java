package com.emp.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.emp.entity.Employees;

@Component
public interface IEmployeeService {
public Employees saveEmployee(Employees emp);
public List<Employees> employeeList() ;
}
