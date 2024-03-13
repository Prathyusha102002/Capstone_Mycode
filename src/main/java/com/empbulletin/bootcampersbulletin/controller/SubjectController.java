package com.empbulletin.bootcampersbulletin.controller;

import com.empbulletin.bootcampersbulletin.exception.SubjectNotFoundException;
import com.empbulletin.bootcampersbulletin.model.Subject;
import com.empbulletin.bootcampersbulletin.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.empbulletin.bootcampersbulletin.repository.ScoresRepository;
import java.util.Map;
import java.util.Optional;
import com.empbulletin.bootcampersbulletin.service.SubjectService;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }
    @Autowired
    private ScoresRepository scoresRepository;
    @PostMapping("/add")
    public ResponseEntity<String> addSubject(@RequestBody Subject subject) {
        // Check if the subject already exists
        if (subjectRepository.existsBySubjectName(subject.getSubjectName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Subject already exists.");
        }

        // Save the subject
        subjectRepository.save(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body("Subject added successfully.");
    }
    @Transactional
    @DeleteMapping("/delete/{subjectName}")
    public ResponseEntity<String> deleteSubject(@PathVariable String subjectName) {
        // Check if the subject exists
        Subject subject = subjectRepository.findBySubjectName(subjectName)
                .orElseThrow(() -> new SubjectNotFoundException("Subject with name " + subjectName + " not found."));

        // Delete all scores associated with the subject
        scoresRepository.deleteAllBySubject(subject);

        // Delete the subject
        subjectRepository.delete(subject);

        return ResponseEntity.ok("Subject " + subjectName + " and associated scores deleted successfully.");
    }


}
