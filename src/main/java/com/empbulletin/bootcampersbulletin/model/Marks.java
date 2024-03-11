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


    @Column(name = "unix")
    private Float unix ;

    @Column(name = "sequel")
    private Float sequel;

    @Column(name = "java")
    private Float java ;

    @Column(name = "testing")
    private Float testing ;

    @Column(name = "python")
    private Float python ;

    @Column(name = "aiml")
    private Float aiml ;

    @Column(name = "azure")
    private Float azure ;

    @Column(name = "git")
    private Float git;

    @Column(name = "jenkins")
    private Float jenkins ;

    @Column(name = "devops")
    private Float devops ;

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

        // Check each subject's marks and add to total if not null
        if (unix != null) {
            totalMarks += unix;
            numberOfSubjects++;
        }
        if (sequel != null) {
            totalMarks += sequel;
            numberOfSubjects++;
        }
        if (java != null) {
            totalMarks += java;
            numberOfSubjects++;
        }
        if (testing != null) {
            totalMarks += testing;
            numberOfSubjects++;
        }
        if (python != null) {
            totalMarks += python;
            numberOfSubjects++;
        }
        if (aiml != null) {
            totalMarks += aiml;
            numberOfSubjects++;
        }
        if (azure != null) {
            totalMarks += azure;
            numberOfSubjects++;
        }
        if (git != null) {
            totalMarks += git;
            numberOfSubjects++;
        }
        if (jenkins != null) {
            totalMarks += jenkins;
            numberOfSubjects++;
        }
        if (devops != null) {
            totalMarks += devops;
            numberOfSubjects++;
        }
        // Avoid division by zero
        if (numberOfSubjects != 0) {
            average_marks = totalMarks / numberOfSubjects;
        } else {
            // Handle the case where all marks are null
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
