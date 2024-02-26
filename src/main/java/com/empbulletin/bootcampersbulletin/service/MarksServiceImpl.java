package com.empbulletin.bootcampersbulletin.service;

import com.empbulletin.bootcampersbulletin.model.Marks;
import com.empbulletin.bootcampersbulletin.repository.EmployeeRepository;
import com.empbulletin.bootcampersbulletin.repository.MarksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MarksServiceImpl implements MarksService{

    private final MarksRepository marksRepository;
    private final EmployeeRepository employeeRepository;

    public MarksServiceImpl(MarksRepository marksRepository, EmployeeRepository employeeRepository) {
        this.marksRepository = marksRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Marks> getAllMarks() {

        return marksRepository.findAll();
    }

    @Override
    public Marks getMarksById(Long marks_id) {
        return marksRepository.findById(marks_id).orElse(null);
    }

    @Override
    public Marks createMarks(Marks marks) {

        return marksRepository.save(marks);
    }

    @Override
    public Marks getMarks(Long emp_id, Long marks_id) {
        return marksRepository.findByEmpIdAndMarksId(emp_id, marks_id);
    }
}
