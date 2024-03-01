package com.empbulletin.bootcampersbulletin.controller;

import com.empbulletin.bootcampersbulletin.DTO.EmployeeDTO;
import com.empbulletin.bootcampersbulletin.DTO.InterviewsDTO;
import com.empbulletin.bootcampersbulletin.DTO.MarksDTO;
import com.empbulletin.bootcampersbulletin.exception.ResourceNotFoundException;
import com.empbulletin.bootcampersbulletin.model.Employee;
import com.empbulletin.bootcampersbulletin.model.Interviews;
import com.empbulletin.bootcampersbulletin.repository.EmployeeRepository;
import com.empbulletin.bootcampersbulletin.repository.InterviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
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
    public ResponseEntity<InterviewsDTO> getInterviewResult(
            @PathVariable Long emp_id,
            @PathVariable Long interviews_id) {

        Optional<Interviews> interviewsOptional = Optional.ofNullable(interviewsRepository.findByEmpIdAndInterviewsId(emp_id, interviews_id));
        if (interviewsOptional.isPresent()) {
            Interviews interviews = interviewsOptional.get();
            InterviewsDTO responseDTO = new InterviewsDTO();
            responseDTO.setInterviews_id(interviews.getInterviews_id());

            // Map employee details
            EmployeeDTO employeeDTO = new EmployeeDTO();
            Employee employee = interviews.getEmployee();
            employeeDTO.setEmp_id(employee.getEmp_id());
            employeeDTO.setEmp_name(employee.getEmp_name());
            employeeDTO.setEmp_mail(employee.getEmp_mail());
            employeeDTO.setBatchNo(employee.getBatchNo());
            responseDTO.setEmployee(employeeDTO);

            // Set subject marks
            responseDTO.setUnix(interviews.getUnix());
            responseDTO.setSequel(interviews.getSequel());
            responseDTO.setJava(interviews.getJava());
            responseDTO.setTesting(interviews.getTesting());
            responseDTO.setPython(interviews.getPython());
            responseDTO.setAiml(interviews.getAiml());
            responseDTO.setAzure(interviews.getAzure());
            responseDTO.setGit(interviews.getGit());
            responseDTO.setJenkins(interviews.getJenkins());
            responseDTO.setDevops(interviews.getDevops());
            // Calculate and set average_Interviews
            float sum = interviews.getUnix() + interviews.getSequel() + interviews.getJava() + interviews.getTesting() +
                    interviews.getPython() + interviews.getAiml() + interviews.getAzure() + interviews.getGit() +
                    interviews.getJenkins() + interviews.getDevops();
            float averageMarks = sum / 10;
            responseDTO.setAverage_interviews(averageMarks);

            // Set marks_rating based on average_marks
            String interviewFeedback;
            if (averageMarks >= 8) {
                interviewFeedback = "Excellent";
            } else if (averageMarks >= 6) {
                interviewFeedback = "Good";
            } else if (averageMarks >= 4) {
                interviewFeedback = "Average";
            } else {
                interviewFeedback = "Poor";
            }
            responseDTO.setInterviews_feedback(interviewFeedback);

            return ResponseEntity.ok(responseDTO);
        } else {
            throw new ResourceNotFoundException("Interviews not found for Employee with id " + emp_id);
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
            throw new ResourceNotFoundException("Interviews with emp_id " + emp_id + " and interviews_id " + interviews_id + " not found");
        }
    }


    @PutMapping("/{emp_id}/{interviews_id}")
    public ResponseEntity<String> updateInterviewsForEmployee(
            @PathVariable Long emp_id,
            @PathVariable Long interviews_id,
            @RequestBody Map<String, Float> updatedInterviews) { // Accept a map of subject marks
        Optional<Interviews> interviewsOptional = Optional.ofNullable(interviewsRepository.findByEmpIdAndInterviewsId(emp_id, interviews_id));
        if (interviewsOptional.isPresent()) {
            Interviews existingInterviews = interviewsOptional.get();
            // Update only the subjects that are present in the request
            updatedInterviews.forEach((subject, interviews) -> {
                switch (subject.toLowerCase()) {
                    case "unix":
                        existingInterviews.setUnix(interviews);
                        break;
                    case "sequel":
                        existingInterviews.setSequel(interviews);
                        break;
                    case "java":
                        existingInterviews.setJava(interviews);
                        break;
                    case "testing":
                        existingInterviews.setTesting(interviews);
                        break;
                    case "python":
                        existingInterviews.setPython(interviews);
                        break;
                    case "aiml":
                        existingInterviews.setAiml(interviews);
                        break;
                    case "azure":
                        existingInterviews.setAzure(interviews);
                        break;
                    case "git":
                        existingInterviews.setGit(interviews);
                        break;
                    case "jenkins":
                        existingInterviews.setJenkins(interviews);
                        break;
                    case "devops":
                        existingInterviews.setDevops(interviews);
                        break;
                    default:
                        // Ignore unknown subjects
                        break;
                }
            });

            InterviewsDTO responseDTO = new InterviewsDTO();
            responseDTO.setInterviews_id(existingInterviews.getInterviews_id());
            // Calculate and set average_marks
            float sum = existingInterviews.getUnix() + existingInterviews.getSequel() + existingInterviews.getJava() + existingInterviews.getTesting() +
                    existingInterviews.getPython() +existingInterviews.getAiml() + existingInterviews.getAzure() + existingInterviews.getGit() +
                    existingInterviews.getJenkins() +existingInterviews.getDevops();
            float averageInterviews = sum / 10;

            responseDTO.setAverage_interviews(averageInterviews);

            // Set marks_rating based on average_marks
            String interviewsFeedback;
            if (averageInterviews >= 8) {
                interviewsFeedback = "Excellent";
            } else if (averageInterviews >= 6) {
                interviewsFeedback = "Good";
            } else if (averageInterviews >= 4) {
                interviewsFeedback = "Average";
            } else {
                interviewsFeedback = "Poor";
            }
            responseDTO.setInterviews_feedback(interviewsFeedback);
            interviewsRepository.save(existingInterviews);
            return ResponseEntity.ok("Interview Ratings updated successfully");
        } else {
            throw new ResourceNotFoundException("Interview Ratings with emp_id " + emp_id + " and interviews_id " + interviews_id + " not found");
        }
    }

}
