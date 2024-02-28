package com.empbulletin.bootcampersbulletin.controller;

import com.empbulletin.bootcampersbulletin.exception.ResourceNotFoundException;
import com.empbulletin.bootcampersbulletin.model.Employee;
import com.empbulletin.bootcampersbulletin.model.Interviews;
import com.empbulletin.bootcampersbulletin.model.Marks;
import com.empbulletin.bootcampersbulletin.repository.EmployeeRepository;
import com.empbulletin.bootcampersbulletin.repository.InterviewsRepository;
import com.empbulletin.bootcampersbulletin.repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Interviews")
public class InterviewsController {
    @Autowired
    private InterviewsRepository interviewsRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/save/{emp_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Interviews saveInterviewsForEmployee(@PathVariable Long emp_id, @RequestBody Interviews interviews) {
        Optional<Employee> employeeOptional = employeeRepository.findById(emp_id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            interviews.setEmployee(employee);
            return interviewsRepository.save(interviews);
        } else {
            throw new ResourceNotFoundException("Employee with id " + emp_id + " not found");
        }
    }

    @GetMapping("/{emp_id}/{interviews_id}")
    public Interviews getAssessmentResult(
            @PathVariable Long emp_id,
            @PathVariable Long interviews_id) {

        Optional<Interviews> interviewsOptional = Optional.ofNullable(interviewsRepository.findByEmpIdAndInterviewsId(emp_id, interviews_id));
        if (interviewsOptional.isPresent()) {
            return interviewsOptional.get();
        } else {
            throw new ResourceNotFoundException("Marks not found for Employee with id " + emp_id);
        }

    }

    @DeleteMapping("/{emp_id}/{interviews_id}")
    public String deleteInterviewsForEmployee(
            @PathVariable Long emp_id,
            @PathVariable Long interviews_id) {
        Optional<Interviews> interviewsOptional = Optional.ofNullable(interviewsRepository.findByEmpIdAndInterviewsId(emp_id, interviews_id));
        if (interviewsOptional.isPresent()) {
            interviewsRepository.delete(interviewsOptional.get());
            return "Deleted Successfully";
        } else {
            throw new ResourceNotFoundException("Marks with emp_id " + emp_id + " and marks_id " + interviews_id + " not found");
        }
    }





}
