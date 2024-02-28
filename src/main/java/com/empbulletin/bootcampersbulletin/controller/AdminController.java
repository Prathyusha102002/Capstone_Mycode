package com.empbulletin.bootcampersbulletin.controller;

import com.empbulletin.bootcampersbulletin.DTO.EmployeeDTO;
import com.empbulletin.bootcampersbulletin.exception.ResourceNotFoundException;
import com.empbulletin.bootcampersbulletin.model.Employee;
import com.empbulletin.bootcampersbulletin.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/employees")
public class AdminController {

    @Autowired
    private EmployeeRepository employeeRepository;


    // Get all employees
    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> {
                    EmployeeDTO dto = new EmployeeDTO();
                    dto.setEmp_id(employee.getEmp_id());
                    dto.setEmp_name(employee.getEmp_name());
                    dto.setEmp_mail(employee.getEmp_mail());
                    dto.setBatchNo(employee.getBatchNo());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            Employee emp = employee.get();
            EmployeeDTO dto = new EmployeeDTO();
            dto.setEmp_id(emp.getEmp_id());
            dto.setEmp_name(emp.getEmp_name());
            dto.setEmp_mail(emp.getEmp_mail());
            dto.setBatchNo(emp.getBatchNo());
            return dto;
        } else {
            throw new ResourceNotFoundException("Employee with id " + id + " not found");
        }
    }
    @GetMapping("/batch/{batchNo}")
    public List<EmployeeDTO> getEmployeesByBatchNo(@PathVariable Integer batchNo) {
        List<Employee> employees = employeeRepository.findByBatchNo(batchNo);
        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("Employees in batch number " + batchNo + " not found");
        }
        return employees.stream()
                .map(employee -> {
                    EmployeeDTO dto = new EmployeeDTO();
                    dto.setEmp_id(employee.getEmp_id());
                    dto.setEmp_name(employee.getEmp_name());
                    dto.setEmp_mail(employee.getEmp_mail());
                    dto.setBatchNo(employee.getBatchNo());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // Add new employee
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // Update existing employee
//    @PutMapping("/{id}")
//    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));
//
//        employee.setEmp_name(employeeDetails.getEmp_name());
//        employee.setEmp_mail(employeeDetails.getEmp_mail());
//        employee.setBatchNo(employeeDetails.getBatchNo());
//
//        return employeeRepository.save(employee);
//    }



    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Map<String, Object> employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));

        // Update only the fields present in the request body
        employeeDetails.forEach((key, value) -> {
            switch (key) {
                case "emp_name":
                    employee.setEmp_name((String) value);
                    break;
                case "emp_mail":
                    employee.setEmp_mail((String) value);
                    break;
                case "batchNo":
                    employee.setBatchNo((Integer) value);
                    break;
                default:
                    // Ignore other fields
                    break;
            }
        });

        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }


    // Delete employee
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }


//    Have to do.............!!!!!!!!!!!!
//    @PostMapping("/login")
//    public String login(@RequestParam String name, @RequestParam String password) {
//        if (adminService.authenticate(name, password)) {
//            return "Login successful";
//        } else {
//            return "Login failed";
//        }
//    }
}
