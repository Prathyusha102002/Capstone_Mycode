package com.empbulletin.bootcampersbulletin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@Table(name = "Marks")
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long marks_id;

    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
    private Employee employee;



//    @ElementCollection
//    @CollectionTable(name = "subject_marks", joinColumns = @JoinColumn(name = "marks_id"))
//    @MapKeyColumn(name = "subject_name")
//    @Column(name = "marks")
//    private Map<String, Float> subjectMarks = new HashMap<>();


    @Column(name = "unix", columnDefinition = "FLOAT DEFAULT 0.0")
    private Float unix = 0.0f;

    @Column(name = "sequel", columnDefinition = "FLOAT DEFAULT 0.0")
    private Float sequel = 0.0f;

    @Column(name = "java", columnDefinition = "FLOAT DEFAULT 0.0")
    private Float java = 0.0f;

    @Column(name = "testing", columnDefinition = "FLOAT DEFAULT 0.0")
    private Float testing = 0.0f;

    @Column(name = "python", columnDefinition = "FLOAT DEFAULT 0.0")
    private Float python = 0.0f;

    @Column(name = "aiml", columnDefinition = "FLOAT DEFAULT 0.0")
    private Float aiml = 0.0f;

    @Column(name = "azure", columnDefinition = "FLOAT DEFAULT 0.0")
    private Float azure = 0.0f;

    @Column(name = "git", columnDefinition = "FLOAT DEFAULT 0.0")
    private Float git = 0.0f;

    @Column(name = "jenkins", columnDefinition = "FLOAT DEFAULT 0.0")
    private Float jenkins = 0.0f;

    @Column(name = "devops", columnDefinition = "FLOAT DEFAULT 0.0")
    private Float devops = 0.0f;

    // Transient variables
    @Transient
    private Float average_marks;

    @Transient
    private String marks_feedback;

    // Calculating average_marks
    @PostLoad
    public void calculateAverageMarks() {
        float totalMarks = 0;
        int numberOfSubjects = 0;

        // Check each subject's marks and add to total if not zero
        if (unix != 0) {
            totalMarks += unix;
            numberOfSubjects++;
        }
        if (sequel != 0) {
            totalMarks += sequel;
            numberOfSubjects++;
        }
        if (java !=0)
        {
            totalMarks += java;
            numberOfSubjects++;
        }
        if (testing !=0)
        {
            totalMarks += testing;
            numberOfSubjects++;
        }if (python !=0)
        {
            totalMarks += python;
            numberOfSubjects++;
        }if (aiml !=0)
        {
            totalMarks += aiml;
            numberOfSubjects++;
        }if (azure !=0)
        {
            totalMarks += azure;
            numberOfSubjects++;
        }if (git !=0)
        {
            totalMarks += git;
            numberOfSubjects++;
        }if (jenkins !=0)
        {
            totalMarks += jenkins;
            numberOfSubjects++;
        }if (devops !=0)
        {
            totalMarks += devops;
            numberOfSubjects++;
        }
        // Avoid division by zero
        if (numberOfSubjects != 0) {
            average_marks = totalMarks / numberOfSubjects;
        } else {
            // Handle the case where all marks are zero
            average_marks = 0.0f;
        }
        calculateMarksRating();
    }

    // Calculating Marks Feedback
    private void calculateMarksRating() {
        float percentage = (average_marks / 25) * 100; // Assuming maximum marks is 25 for each subject
        if (percentage >= 80) {
            marks_feedback = "Excellent";
        } else if (percentage >= 60 | percentage <80) {
            marks_feedback = "Satisfactory";
        } else {
            marks_feedback = "Need to Improve";
        }
    }


    @Override
    public String toString() {
        return "Marks{" +
                "marks_id=" + marks_id +
                ", employee=" + employee +
                ", unix=" + unix +
                ", sequel=" + sequel +
                ", java=" + java +
                ", testing=" + testing +
                ", python=" + python +
                ", aiml=" + aiml +
                ", azure=" + azure +
                ", git=" + git +
                ", jenkins=" + jenkins +
                ", devops=" + devops +
                ", devops=" + devops +
                '}';
    }

    public Marks() {
    }

    public Marks(Long marks_id, Employee employee, Float unix, Float sequel, Float java, Float testing, Float python, Float aiml, Float azure, Float git, Float jenkins, Float devops)
    {
        this.marks_id = marks_id;
        this.employee = employee;
        this.unix = unix;
        this.sequel = sequel;
        this.java = java;
        this.testing = testing;
        this.python = python;
        this.aiml = aiml;
        this.azure = azure;
        this.git = git;
        this.jenkins = jenkins;
        this.devops = devops;
    }

}
