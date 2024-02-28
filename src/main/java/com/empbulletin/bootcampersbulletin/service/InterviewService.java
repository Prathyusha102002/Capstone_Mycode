package com.empbulletin.bootcampersbulletin.service;

import com.empbulletin.bootcampersbulletin.model.Interviews;
import com.empbulletin.bootcampersbulletin.model.Marks;

import java.util.List;

public interface InterviewService {
    List<Interviews> getAllInterviews();
    Interviews getInterviewsById(Long interviews_id);
    Interviews createInterviews(Interviews interviews);

   Interviews getInterviews(Long emp_id, Long interviews_id);
}
