package com.empbulletin.bootcampersbulletin.controller;

import java.util.List;

import com.empbulletin.bootcampersbulletin.exception.ResourceNotFoundException;
import com.empbulletin.bootcampersbulletin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;


import com.empbulletin.bootcampersbulletin.model.Employee;
import com.empbulletin.bootcampersbulletin.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/employees")

public class EmployeeController {

	@Autowired
	private EmployeeRepository eR;
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public List<Employee> getAllEmployees() {
		return eR.findAll();
	}
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable Long id) {
		Optional<Employee> employee = eR.findById(id);
		if (employee.isPresent()) {
			return employee.get();
		} else {
			// Handle the case where employee with given id doesn't exist
			throw new ResourceNotFoundException("Employee with id " + id + " not found");
		}
	}

	@GetMapping("/batch/{batchNo}")
	public List<Employee> getEmployeesByBatchNo(@PathVariable Integer batchNo) {
		List<Employee> employees = eR.findByBatchNo(batchNo);
		if (employees.isEmpty()) {
			throw new ResourceNotFoundException("Employees in batch number " + batchNo + " not found");
		}
		return employees;
	}




	@PostMapping("/login")
	public String login(@RequestParam Long emp_id, @RequestParam String password) {
		if (employeeService.authenticate(emp_id, password)) {
			return "Login successful";
		} else {
			return "Login failed";
		}
	}



}
