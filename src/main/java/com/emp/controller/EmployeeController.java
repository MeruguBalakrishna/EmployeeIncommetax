package com.emp.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.dto.TaxDeductionDTO;
import com.emp.entity.Employees;
import com.emp.service.IEmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private IEmployeeService employeeServices;
	@PostMapping
    public ResponseEntity<Employees> createEmployee(@Valid @RequestBody Employees emp, BindingResult result) {
     System.out.println("data controller,,,,,:");
		if (result.hasErrors()) {
            return new ResponseEntity<Employees>(HttpStatus.BAD_REQUEST);
        }

		Employees savedEmployee = employeeServices.saveEmployee(emp);
        return new ResponseEntity<Employees>(savedEmployee, HttpStatus.CREATED);
    }
		
	@GetMapping("/welcome")
		public String welcomeKit(){
			return "welcome kit";
		}
		
		@GetMapping("/tax-deductions")
	    public List<TaxDeductionDTO> getTaxDeductions() {
	        List<Employees> employees = employeeServices.employeeList();
	        List<TaxDeductionDTO> taxDeductions = new ArrayList<>();

	        for (Employees employee : employees) {
	            double totalSalary = calculateTotalSalary(employee);
	            double taxAmount = calculateTax(totalSalary);
	            double cessAmount = calculateCess(totalSalary);

	            TaxDeductionDTO taxDeduction = new TaxDeductionDTO();
	            taxDeduction.setEmployeeId(employee.getEmployeeId());
	            taxDeduction.setFirstName(employee.getFirstName());
	            taxDeduction.setLastName(employee.getLastName());
	            taxDeduction.setEmail(employee.getEmail());
	            taxDeduction.setPhoneNumbers(employee.getPhoneNumbers());
	            taxDeduction.setDateOfJoining(employee.getDateOfJoining());
	            taxDeduction.setSalary(totalSalary * 12);
	            taxDeduction.setTaxAmount(taxAmount);
	            taxDeduction.setCessAmount(cessAmount);

	            taxDeductions.add(taxDeduction);
	        }

	        return taxDeductions;
	    }

	    private double calculateTotalSalary(Employees employee) {
	        // Implement logic to calculate total salary based on DOJ
	    	LocalDate currentDate = LocalDate.now();

	        // Determine the start date and end date of the financial year (April to March)
	        LocalDate startFinancialYear = LocalDate.of(currentDate.getYear(), 4, 1);
	        LocalDate endFinancialYear = LocalDate.of(currentDate.getYear() + 1, 3, 31);

	        // Determine the number of months the employee has worked in the current financial year
	        //Employees emplo = new Employees()
	        LocalDate joinDate = employee.getDateOfJoining();
	        LocalDate lastDayOfWork = joinDate.plusMonths(1).withDayOfMonth(1).minusDays(1); // Last day of the join month
	        long monthsWorked = ChronoUnit.MONTHS.between(lastDayOfWork, endFinancialYear.plusDays(1));

	        // If the employee has not worked in the current financial year, return 0
	        if (monthsWorked <= 0) {
	            return 0.0;
	        }

	        // Calculate the salary for each month
	        double monthlySalary = employee.getSalary();
	        double totalSalary = monthlySalary * monthsWorked;

	        return totalSalary;
	    }

	    private double calculateTax(double yearlySalary) {
	        // Implement logic to calculate tax based on tax slabs
	    	double tax = 0.0;

	        if (yearlySalary <= 250000) {
	            // No tax for yearly salary <= 250000
	            tax = 0.0;
	        } else if (yearlySalary <= 500000) {
	            // 5% tax for yearly salary > 250000 and <= 500000
	            tax = (yearlySalary - 250000) * 0.05;
	        } else if (yearlySalary <= 1000000) {
	            // 5% tax for first 250000, 10% tax for the next 250000 (total 500000)
	            // Remaining salary will be taxed at 20%
	            tax = (250000 * 0.05) + ((yearlySalary - 500000) * 0.10);
	        } else {
	            // 5% tax for first 250000, 10% tax for the next 250000 (total 500000)
	            // 20% tax for the next 500000 (total 1000000)
	            // Remaining salary will be taxed at 20%
	            tax = (250000 * 0.05) + (250000 * 0.10) + (500000 * 0.20) + ((yearlySalary - 1000000) * 0.20);
	        }

	        return tax;
	    }

	    private double calculateCess(double yearlySalary) {
	        // Implement logic to calculate cess for the amount exceeding 2500000
	    	double cess = 0.0;

	        if (yearlySalary > 2500000) {
	            double excessAmount = yearlySalary - 2500000;
	            cess = excessAmount * 0.02; // 2% cess for the amount exceeding 2500000
	        }

	        return cess;
	    }
	}

