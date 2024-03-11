package com.empbulletin.bootcampersbulletin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Interviews")
public class Interviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interviews_id;

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
    private Float average_interviews;

    @Transient
    private String interviews_feedback;

    public Interviews() {
    }

    public Interviews(Long interviews_id, Employee employee, Float unix, Float sequel, Float java, Float testing, Float python, Float aiml, Float azure, Float git, Float jenkins, Float devops) {
        this.interviews_id = interviews_id;
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


    // Calculating average_marks

    @PostLoad
    public void calculateAverageInterviews() {
        float totalInterviews = 0;
        int numberOfSubjects = 0;

        // Check each subject's interviews and add to total if not null
        if (unix != null) {
            totalInterviews += unix;
            numberOfSubjects++;
        }
        if (sequel != null) {
            totalInterviews += sequel;
            numberOfSubjects++;
        }
        if (java != null) {
            totalInterviews += java;
            numberOfSubjects++;
        }
        if (testing != null) {
            totalInterviews += testing;
            numberOfSubjects++;
        }
        if (python != null) {
            totalInterviews += python;
            numberOfSubjects++;
        }
        if (aiml != null) {
            totalInterviews += aiml;
            numberOfSubjects++;
        }
        if (azure != null) {
            totalInterviews += azure;
            numberOfSubjects++;
        }
        if (git != null) {
            totalInterviews += git;
            numberOfSubjects++;
        }
        if (jenkins != null) {
            totalInterviews += jenkins;
            numberOfSubjects++;
        }
        if (devops != null) {
            totalInterviews += devops;
            numberOfSubjects++;
        }
        // Avoid division by zero
        if (numberOfSubjects != 0) {
            average_interviews = totalInterviews / numberOfSubjects;
        } else {
            // Handle the case where all marks are null
            average_interviews = 0.0f;
        }
        calculateInterviewsRating();
    }


    // Calculating Interview Feedback
    private void calculateInterviewsRating() {
        float percentage = (average_interviews / 10) * 100; // Assuming maximum interview ratings is 10 for each subject
        if (percentage >= 80) {
            interviews_feedback = "Excellent";
        } else if (percentage >= 60 | percentage <80) {
            interviews_feedback = "Satisfactory";
        } else {
            interviews_feedback = "Need to Improve";
        }
    }


    @Override
    public String toString() {
        return "Interviews{" +
                "interviews_id=" + interviews_id +
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
                '}';
    }

}
