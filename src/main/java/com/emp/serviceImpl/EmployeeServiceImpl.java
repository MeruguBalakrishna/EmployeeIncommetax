package com.emp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.entity.Employees;
import com.emp.repo.EmployeeRepository;
import com.emp.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private EmployeeRepository repo;
	
	@Override
	public Employees saveEmployee(Employees emp) {
		// TODO Auto-generated method stub
		Employees empdto=new Employees();
		try {
			empdto=repo.save(emp);
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return empdto;
	}

	@Override
	public List<Employees> employeeList() {
		// TODO Auto-generated method stub
		List<Employees> emplist= repo.findAll(); 
		
		return emplist;
	}

}
