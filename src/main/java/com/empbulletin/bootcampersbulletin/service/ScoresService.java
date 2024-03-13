package com.empbulletin.bootcampersbulletin.service;

import com.empbulletin.bootcampersbulletin.model.Scores;
import com.empbulletin.bootcampersbulletin.repository.ScoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScoresService {
    private final ScoresRepository scoresRepository;

    @Autowired
    public ScoresService(ScoresRepository scoresRepository) {
        this.scoresRepository = scoresRepository;
    }

    public Float calculateAverageMarks(Map<String, Float> subjectMarksMap) {
        // Calculate average marks
        float totalMarks = 0f;
        int count = 0;
        for (Float marks : subjectMarksMap.values()) {
            if (marks != null) {
                totalMarks += marks;
                count++;
            }
        }
        return count > 0 ? (totalMarks / count) : 0f; // Return 0 if no non-null marks are found to avoid division by zero
    }


    public String generateMarksFeedback(Map<String, Float> subjectMarksMap,Float averageMarks) {

        // Generate feedback based on average marks
        int count=0;
        for (Float marks : subjectMarksMap.values()) {
            if (marks != null) {
                count++;
            }
        }
        if(count==0)
        {
            return "No Exams Conducted";
        }
        float percentage = (averageMarks/25)*100;
        if (percentage >= 80) {
            return "Excellent.";
        } else if (percentage >= 60 | percentage <80) {
            return "Satisfactory.";
        } else {
            return "Needs improvement.";
        }
    }
    public String generateInterviewsFeedback(Map<String, Float> subjectInterviewsMap,Float averageInterviews) {
        // Generate feedback based on average marks
        int count = 0;
        for (Float interviews : subjectInterviewsMap.values()) {
            if (interviews != null) {
                count++;
            }
        }
        if(count==0)
        {
            return "No Interviews held.";
        }
        float percentage = (averageInterviews/10)*100;
        if (percentage >= 80) {
            return "Excellent.";
        } else if (percentage >= 60 | percentage <80) {
            return "Satisfactory.";
        } else {
            return "Needs improvement.";
        }
    }


    public Float calculateAverageInterviews(Map<String, Float> subjectInterviewsMap) {
        // Calculate average interviews
        float totalInterviews = 0f;
        int count = 0;
        for (Float interviews : subjectInterviewsMap.values()) {
            if (interviews != null) {
                totalInterviews += interviews;
                count++;
            }
        }
        return count > 0 ? (totalInterviews / count) : 0f; // Return 0 if no non-null interviews are found to avoid division by zero
    }


    public Map<String, Float> getSubjectInterviewsByEmployeeId(Long empId) {
        Map<String, Float> subjectInterviewsMap=new HashMap<>();
        List<Scores> scoresList=scoresRepository.findByEmployee_EmpId(empId);
        for (Scores score : scoresList) {
            String subjectName = score.getSubject().getSubjectName();
            Float interviews = score.getSubjectInterviews();
            subjectInterviewsMap.put(subjectName, interviews);
        }
        return subjectInterviewsMap;
    }
    public Map<String, Float> getSubjectMarksByEmployeeId(Long empId) {
        Map<String, Float> subjectMarksMap = new HashMap<>();

        // Retrieve scores for the given employee ID
        List<Scores> scoresList = scoresRepository.findByEmployee_EmpId(empId);

        // Populate subject marks map
        for (Scores score : scoresList) {
            String subjectName = score.getSubject().getSubjectName();
            Float marks = score.getSubjectMarks();
            subjectMarksMap.put(subjectName, marks);
        }

        return subjectMarksMap;
    }




}
