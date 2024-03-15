package com.empbulletin.bootcampersbulletin.controller;

import com.empbulletin.bootcampersbulletin.DTO.EmployeeDTO;
import com.empbulletin.bootcampersbulletin.exception.ResourceNotFoundException;
import com.empbulletin.bootcampersbulletin.exception.SubjectNotFoundException;
import com.empbulletin.bootcampersbulletin.model.*;
import com.empbulletin.bootcampersbulletin.repository.AdminRepository;
import com.empbulletin.bootcampersbulletin.repository.EmployeeRepository;
import com.empbulletin.bootcampersbulletin.repository.ScoresRepository;
import com.empbulletin.bootcampersbulletin.service.AdminService;
import com.empbulletin.bootcampersbulletin.service.EmployeeService;
import com.empbulletin.bootcampersbulletin.service.ScoresService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.empbulletin.bootcampersbulletin.repository.SubjectRepository;
import com.empbulletin.bootcampersbulletin.DTO.SubjectMarksRequest;
import com.empbulletin.bootcampersbulletin.DTO.SubjectInterviewRequest;

import java.util.*;

import com.empbulletin.bootcampersbulletin.model.Subject;

import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;



@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private ScoresService scoresService;
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ScoresRepository scoresRepository;

    //Adding an employee
    @PostMapping("/addEmployee")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        try {
            // Check if the empId already exists
            Optional<Employee> existingEmployeeOpt = employeeRepository.findById(employee.getEmpId());
            if (existingEmployeeOpt.isPresent()) {
                return new ResponseEntity<>("Employee with this empId already exists", HttpStatus.CONFLICT);
            }

            // If empId doesn't exist, save the employee to the database
            employeeRepository.save(employee);
            return new ResponseEntity<>("Employee added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            // If there's an error, return an error response
            return new ResponseEntity<>("Failed to add employee: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Updating employee details by emp_id



    @Transactional
    @DeleteMapping("/{empId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long empId) {
        // Check if the employee exists
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + empId + " not found."));

        // Delete all scores associated with the employee
        scoresRepository.deleteAllByEmployee(employee);

        // Delete the employee
        employeeRepository.delete(employee);

        return ResponseEntity.ok("Employee with ID " + empId + " and associated scores deleted successfully.");
    }


    @PostMapping("/addMarks")
    public ResponseEntity<String> addSubjectMarks(@RequestBody SubjectMarksRequest request) {
        Long empId = request.getEmpId();
        String subjectName = request.getSubjectName();
        Float marks = request.getMarks();

        // Check if the employee exists
        Employee employee = employeeRepository.findById(empId).orElse(null);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with ID " + empId + " not found.");
        }

        // Check if the subject exists
        Subject subject = subjectRepository.findBySubjectName(subjectName).orElse(null);
        if (subject == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject with name " + subjectName + " not found.");
        }

        // Check if the combination of empId and subjectName exists in Scores table
        Scores existingScore = scoresRepository.findByEmployeeAndSubject(employee, subject);
        if (existingScore != null) {
            // If the row exists, update the marks
            existingScore.setSubjectMarks(marks);
            scoresRepository.save(existingScore);
            return ResponseEntity.status(HttpStatus.OK).body("Marks updated successfully.");
        } else {
            // If the row does not exist, create a new row with marks
            Scores newScore = new Scores(employee, subject, marks, null);
            scoresRepository.save(newScore);
            return ResponseEntity.status(HttpStatus.OK).body("Marks added successfully.");
        }
    }
    @PostMapping("/addInterviews")
    public ResponseEntity<String> addSubjectInterviews(@RequestBody SubjectInterviewRequest request) {
        Long empId = request.getEmpId();
        String subjectName = request.getSubjectName();
        Float interviews = request.getInterviews();

        // Check if the employee exists
        Employee employee = employeeRepository.findById(empId).orElse(null);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with ID " + empId + " not found.");
        }

        // Check if the subject exists
        Subject subject = subjectRepository.findBySubjectName(subjectName).orElse(null);
        if (subject == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject with name " + subjectName + " not found.");
        }

        // Check if the combination of empId and subjectName exists in Scores table
        Scores existingScore = scoresRepository.findByEmployeeAndSubject(employee, subject);
        if (existingScore != null) {
            // If the row exists, update the interviews
            existingScore.setSubjectInterviews(interviews);
            scoresRepository.save(existingScore);
            return ResponseEntity.status(HttpStatus.OK).body("Interviews updated successfully.");
        } else {
            // If the row does not exist, create a new row with interviews
            Scores newScore = new Scores(employee, subject, null, interviews);
            scoresRepository.save(newScore);
            return ResponseEntity.status(HttpStatus.OK).body("Interviews added successfully.");
        }
    }
//    @GetMapping("/averageMarksFeedback/{empId}")
//    public ResponseEntity<Object> getAverageMarksAndFeedback(@PathVariable Long empId) {
//        // Get subject-wise marks for the employee
//        Map<String, Float> subjectMarksMap = scoresService.getSubjectMarksByEmployeeId(empId);
//
//        if (subjectMarksMap.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found for employee ID: " + empId);
//        }
//
//        // Calculate average marks
//        Float averageMarks = scoresService.calculateAverageMarks(subjectMarksMap);
//
//        // Generate feedback based on average marks
//        String feedback = scoresService.generateFeedback(averageMarks);
//
//        // Construct response JSON
//        Map<String, Object> response = new HashMap<>();
//        response.put("employeeId", empId);
//        response.put("averagemarks", averageMarks);
//        response.put("feedback", feedback);
//        response.put("subjectMarks", subjectMarksMap);
//
//        // Return response
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
@GetMapping("/averageMarksFeedback/{empId}")
public ResponseEntity<Object> getAverageMarksAndFeedback(@PathVariable Long empId) {
    // Get subject-wise marks for the employee
    Map<String, Float> subjectMarksMap = scoresService.getSubjectMarksByEmployeeId(empId);

    if (subjectMarksMap.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found for employee ID: " + empId);
    }

    // Calculate average marks
    Float averageMarks = scoresService.calculateAverageMarks(subjectMarksMap);

    // Generate feedback based on average marks
    String feedback = scoresService.generateMarksFeedback(subjectMarksMap,averageMarks);

    // Construct response JSON
    Map<String, Object> response = new LinkedHashMap<>();  // Use LinkedHashMap to maintain insertion order
    response.put("empId", empId);  // Place empId at the beginning
    response.put("subjectMarks", subjectMarksMap);
    response.put("averagemarks", averageMarks);
    response.put("feedback", feedback);

    // Return response
    return ResponseEntity.status(HttpStatus.OK).body(response);
}
    @GetMapping("/averageInterviewsFeedback/{empId}")
    public ResponseEntity<Object> getAverageInterviewsAndFeedback(@PathVariable Long empId) {
        // Get subject-wise interviews for the employee
        Map<String, Float> subjectInterviewsMap = scoresService.getSubjectInterviewsByEmployeeId(empId);

        if (subjectInterviewsMap.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found for employee ID: " + empId);
        }

        // Calculate average interviews
        Float averageInterviews = scoresService.calculateAverageInterviews(subjectInterviewsMap);

        // Generate feedback based on average interviews
        String feedback = scoresService.generateInterviewsFeedback(subjectInterviewsMap,averageInterviews);

        // Construct response JSON
        Map<String, Object> response = new LinkedHashMap<>();  // Use LinkedHashMap to maintain insertion order
        response.put("empId", empId);  // Place empId at the beginning
        response.put("subjectInterviews", subjectInterviewsMap);
        response.put("averageInterviews", averageInterviews);
        response.put("feedback", feedback);

        // Return response
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


//    @PostMapping("/addMarks")
//    public ResponseEntity<String> addSubjectMarks(
//            @RequestParam Long empId,
//            @RequestParam String subjectName,
//            @RequestParam Float marks
//    ) {
//        // Check if the employee exists
//        Employee employee = employeeRepository.findById(empId).orElse(null);
//        if (employee == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with ID " + empId + " not found.");
//        }
//
//        // Check if the subject exists
//        Subject subject =subjectRepository.findBySubjectName(subjectName).orElse(null);
//        if(subject==null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject with name " + subjectName + " not found.");
//        }
//
//
//        // Check if the combination of empId and subjectName exists in Scores table
//        Scores existingScore = scoresRepository.findByEmployeeAndSubject(employee, subject);
//        if (existingScore != null) {
//            // If the row exists, update the marks
//            existingScore.setSubjectMarks(marks);
//            scoresRepository.save(existingScore);
//            return ResponseEntity.status(HttpStatus.OK).body("Marks updated successfully.");
//        } else {
//            // If the row does not exist, create a new row with marks
//            Scores newScore = new Scores(employee, subject, marks, null);
//            scoresRepository.save(newScore);
//            return ResponseEntity.status(HttpStatus.OK).body("Marks added successfully.");
//        }
//    }


    @Autowired
    private AdminRepository adminRepository;

    // Admin login
    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(@RequestBody Admin admin) {
        // Find admin by adminName
        Admin foundAdmin = adminRepository.findByAdminName(admin.getAdminName());
        if (foundAdmin == null) {
            return new ResponseEntity<>("Admin with username " + admin.getAdminName() + " not found", HttpStatus.NOT_FOUND);
        }

        // Check if passwords match
        if (!foundAdmin.getAdminPassword().equals(admin.getAdminPassword())) {
            return new ResponseEntity<>("Invalid password for admin " + admin.getAdminName(), HttpStatus.UNAUTHORIZED);
        }

        // If admin is found and password matches, return success
        return ResponseEntity.ok("Admin login successful");
    }

    // Get all employees
    @GetMapping("/allEmployees")
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> {
                    EmployeeDTO dto = new EmployeeDTO();
                    dto.setEmpId(employee.getEmpId());
                    dto.setEmpName(employee.getEmpName());
                    dto.setEmpMail(employee.getEmpMail());
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
            dto.setEmpId(emp.getEmpId());
            dto.setEmpName(emp.getEmpName());
            dto.setEmpMail(emp.getEmpMail());
            dto.setBatchNo(emp.getBatchNo());
            return dto;
        } else {
            throw new ResourceNotFoundException("Employee with id " + id + " not found");
        }
    }

    //Get all employees under the Batch_number
    @GetMapping("/batch/{batchNo}")
    public List<EmployeeDTO> getEmployeesByBatchNo(@PathVariable Integer batchNo) {
        List<Employee> employees = employeeRepository.findByBatchNo(batchNo);
        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("Employees in batch number " + batchNo + " not found");
        }
        return employees.stream()
                .map(employee -> {
                    EmployeeDTO dto = new EmployeeDTO();
                    dto.setEmpId(employee.getEmpId());
                    dto.setEmpName(employee.getEmpName());
                    dto.setEmpMail(employee.getEmpMail());
                    dto.setBatchNo(employee.getBatchNo());
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
