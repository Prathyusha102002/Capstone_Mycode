package com.empbulletin.bootcampersbulletin.controller;

import com.empbulletin.bootcampersbulletin.model.Employee;
import com.empbulletin.bootcampersbulletin.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class AdminController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));
    }

    // Add new employee
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // Update existing employee
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));

        employee.setEmp_name(employeeDetails.getEmp_name());
        employee.setEmp_mail(employeeDetails.getEmp_mail());
        employee.setBatchNo(employeeDetails.getBatchNo());

        return employeeRepository.save(employee);
    }

    // Delete employee
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }
}
