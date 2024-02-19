package com.empbulletin.bootcampersbulletin.controller;


import com.empbulletin.bootcampersbulletin.model.EmployeeMarks;
import com.empbulletin.bootcampersbulletin.repository.EmployeeMarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v2/employee-marks")

public class EmployeeMarksController {

    @Autowired
    private EmployeeMarksRepository emr;

    @GetMapping
    public List<EmployeeMarks> getAllMarks() {
        return emr.findAll();
    }

    // Method to post the marks for an employee
    @PostMapping
    public ResponseEntity<EmployeeMarks> postEmployeeMarks(@RequestBody EmployeeMarks employeeMarks) {
        EmployeeMarks savedEmployeeMarks = emr.save(employeeMarks);
        return ResponseEntity.ok().body(savedEmployeeMarks);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeMarks> getEmployeeMarksById(@PathVariable Long employeeId) {
        // Find the employee marks by employee ID
        EmployeeMarks employeeMarks = emr.findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee marks not found for ID: " + employeeId));

        return ResponseEntity.ok().body(employeeMarks);
    }


}