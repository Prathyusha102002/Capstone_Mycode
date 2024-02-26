package com.empbulletin.bootcampersbulletin.service;

import com.empbulletin.bootcampersbulletin.model.Marks;

import java.util.List;

public interface MarksService {

    List<Marks> getAllMarks();
    Marks getMarksById(Long marks_id);
    Marks createMarks(Marks marks);

    Marks getMarks(Long emp_id, Long marks_id);
}
