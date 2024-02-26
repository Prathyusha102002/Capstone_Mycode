package com.empbulletin.bootcampersbulletin.service;

import com.empbulletin.bootcampersbulletin.model.Interviews;
import com.empbulletin.bootcampersbulletin.model.Marks;
import com.empbulletin.bootcampersbulletin.repository.EmployeeRepository;
import com.empbulletin.bootcampersbulletin.repository.InterviewsRepository;
import com.empbulletin.bootcampersbulletin.repository.MarksRepository;

import java.util.List;

public class InterviewsServiceImpl implements InterviewService {
    private final InterviewsRepository interviewsRepository;
    private final EmployeeRepository employeeRepository;

    public InterviewsServiceImpl(InterviewsRepository interviewsRepository, EmployeeRepository employeeRepository) {
        this.interviewsRepository = interviewsRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Interviews> getAllInterviews() {
        return interviewsRepository.findAll();
    }

    @Override
    public Interviews getInterviewsById(Long interviews_id) {
        return interviewsRepository.findById(interviews_id).orElse(null);
    }

    @Override
    public Interviews createInterviews(Interviews interviews) {
        return interviewsRepository.save(interviews);
    }

    @Override
    public Interviews getInterviews(Long emp_id, Long interviews_id) {
        return interviewsRepository.findByEmpIdAndInterviewsId(emp_id, interviews_id);

    }
}
