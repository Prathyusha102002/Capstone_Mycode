package com.empbulletin.bootcampersbulletin.controller;

import java.util.List;

import com.empbulletin.bootcampersbulletin.exception.ResourceNotFoundException;
import com.empbulletin.bootcampersbulletin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


import com.empbulletin.bootcampersbulletin.model.Employee;
import com.empbulletin.bootcampersbulletin.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/employees")

public class EmployeeController {

	@Autowired
	private EmployeeRepository eR;
	
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
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createEmployee(@RequestBody Employee employee) {
		return eR.save(employee);
	}

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/login")
	public String login(@RequestParam Long emp_id, @RequestParam String password) {
		if (employeeService.authenticate(emp_id, password)) {
			return "Login successful";
		} else {
			return "Login failed";
		}
	}
	
}
