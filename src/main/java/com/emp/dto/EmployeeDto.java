package com.emp.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Component
public class EmployeeDto {
		@NotBlank(message = "Employee ID is required")
	    private String employeeId;

	    @NotBlank(message = "First Name is required")
	    private String firstName;

	    @NotBlank(message = "Last Name is required")
	    private String lastName;

	    @Email(message = "Invalid email format")
	    @NotBlank(message = "Email is required")
	    private String email;

	    @NotNull(message = "Phone numbers are required")
	    @Size(min = 1, message = "At least one phone number is required")
	    private List<String> phoneNumbers;

	    @NotNull(message = "Date of Joining is required")
	    private LocalDate dateOfJoining;

	    // Getters and Setters

	    public String getEmployeeId() {
	        return employeeId;
	    }

	    public void setEmployeeId(String employeeId) {
	        this.employeeId = employeeId;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public List<String> getPhoneNumbers() {
	        return phoneNumbers;
	    }

	    public void setPhoneNumbers(List<String> phoneNumbers) {
	        this.phoneNumbers = phoneNumbers;
	    }

	    public LocalDate getDateOfJoining() {
	        return dateOfJoining;
	    }

	    public void setDateOfJoining(LocalDate dateOfJoining) {
	        this.dateOfJoining = dateOfJoining;
	    }

}
