package com.empbulletin.bootcampersbulletin.controller;

import com.empbulletin.bootcampersbulletin.DTO.EmployeeDTO;
import com.empbulletin.bootcampersbulletin.DTO.MarksDTO;
import com.empbulletin.bootcampersbulletin.exception.ResourceNotFoundException;
import com.empbulletin.bootcampersbulletin.model.Employee;
import com.empbulletin.bootcampersbulletin.model.Marks;
import com.empbulletin.bootcampersbulletin.repository.EmployeeRepository;
import com.empbulletin.bootcampersbulletin.repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
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
    public ResponseEntity<MarksDTO> getAssessmentResult(
            @PathVariable Long emp_id,
            @PathVariable Long marks_id) {

        Optional<Marks> marksOptional = Optional.ofNullable(marksRepository.findByEmpIdAndMarksId(emp_id, marks_id));
        if (marksOptional.isPresent()) {
            Marks marks = marksOptional.get();
            MarksDTO responseDTO = new MarksDTO();
            responseDTO.setMarks_id(marks.getMarks_id());

            // Map employee details
            EmployeeDTO employeeDTO = new EmployeeDTO();
            Employee employee = marks.getEmployee();
            employeeDTO.setEmp_id(employee.getEmp_id());
            employeeDTO.setEmp_name(employee.getEmp_name());
            employeeDTO.setEmp_mail(employee.getEmp_mail());
            employeeDTO.setBatchNo(employee.getBatchNo());
            responseDTO.setEmployee(employeeDTO);

            // Set subject marks
            responseDTO.setUnix(marks.getUnix());
            responseDTO.setSequel(marks.getSequel());
            responseDTO.setJava(marks.getJava());
            responseDTO.setTesting(marks.getTesting());
            responseDTO.setPython(marks.getPython());
            responseDTO.setAiml(marks.getAiml());
            responseDTO.setAzure(marks.getAzure());
            responseDTO.setGit(marks.getGit());
            responseDTO.setJenkins(marks.getJenkins());
            responseDTO.setDevops(marks.getDevops());

            return ResponseEntity.ok(responseDTO);
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

    @PutMapping("/{emp_id}/{marks_id}")
    public ResponseEntity<String> updateMarksForEmployee(
            @PathVariable Long emp_id,
            @PathVariable Long marks_id,
            @RequestBody Map<String, Float> updatedMarks) { // Accept a map of subject marks
        Optional<Marks> marksOptional = Optional.ofNullable(marksRepository.findByEmpIdAndMarksId(emp_id, marks_id));
        if (marksOptional.isPresent()) {
            Marks existingMarks = marksOptional.get();
            // Update only the subjects that are present in the request
            updatedMarks.forEach((subject, marks) -> {
                switch (subject.toLowerCase()) {
                    case "unix":
                        existingMarks.setUnix(marks);
                        break;
                    case "sequel":
                        existingMarks.setSequel(marks);
                        break;
                    case "java":
                        existingMarks.setJava(marks);
                        break;
                    case "testing":
                        existingMarks.setTesting(marks);
                        break;
                    case "python":
                        existingMarks.setPython(marks);
                        break;
                    case "aiml":
                        existingMarks.setAiml(marks);
                        break;
                    case "azure":
                        existingMarks.setAzure(marks);
                        break;
                    case "git":
                        existingMarks.setGit(marks);
                        break;
                    case "jenkins":
                        existingMarks.setJenkins(marks);
                        break;
                    case "devops":
                        existingMarks.setDevops(marks);
                        break;
                    default:
                        // Ignore unknown subjects
                        break;
                }
            });

            marksRepository.save(existingMarks);
            return ResponseEntity.ok("Marks updated successfully");
        } else {
            throw new ResourceNotFoundException("Marks with emp_id " + emp_id + " and marks_id " + marks_id + " not found");
        }
    }

}
