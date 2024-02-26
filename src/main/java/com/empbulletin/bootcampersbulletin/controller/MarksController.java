package com.empbulletin.bootcampersbulletin.controller;

import com.empbulletin.bootcampersbulletin.exception.ResourceNotFoundException;
import com.empbulletin.bootcampersbulletin.model.Employee;
import com.empbulletin.bootcampersbulletin.model.Marks;
import com.empbulletin.bootcampersbulletin.repository.EmployeeRepository;
import com.empbulletin.bootcampersbulletin.repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/marks")
public class MarksController {
    @Autowired
    private MarksRepository marksRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Enter marks for Employee
    @PostMapping("/save/{emp_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Marks saveMarksForEmployee(@PathVariable Long emp_id, @RequestBody Marks marks) {
        Optional<Employee> employeeOptional = employeeRepository.findById(emp_id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            marks.setEmployee(employee);
            return marksRepository.save(marks);
        } else {
            throw new ResourceNotFoundException("Employee with id " + emp_id + " not found");
        }
    }

    // Fetch Employee Marks details
    @GetMapping("/{emp_id}/{marks_id}")
    public Marks getAssessmentResult(
            @PathVariable Long emp_id,
            @PathVariable Long marks_id) {

        Optional<Marks> marksOptional = Optional.ofNullable(marksRepository.findByEmpIdAndMarksId(emp_id, marks_id));
        if (marksOptional.isPresent()) {
            return marksOptional.get();
        } else {
            throw new ResourceNotFoundException("Marks not found for Employee with id " + emp_id);
        }

    }

    // Delete Employee Marks details
    @DeleteMapping("/{emp_id}/{marks_id}")
    public String deleteMarksForEmployee(
            @PathVariable Long emp_id,
            @PathVariable Long marks_id) {
        Optional<Marks> marksOptional = Optional.ofNullable(marksRepository.findByEmpIdAndMarksId(emp_id, marks_id));
        if (marksOptional.isPresent()) {
            marksRepository.delete(marksOptional.get());
            return "Deleted Successfully";
        } else {
            throw new ResourceNotFoundException("Marks with emp_id " + emp_id + " and marks_id " + marks_id + " not found");
        }
    }

}
