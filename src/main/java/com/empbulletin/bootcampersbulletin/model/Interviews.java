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


    // Additional fields
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


    // Calculate average_marks
    @PostLoad
    public void calculateAverageInterviews() {
        float totalInterviews = unix + sequel + java + testing + python + aiml + azure + git + jenkins + devops;
        average_interviews = totalInterviews / 10;
        calculateInterviewsRating();
    }

    // Calculate marks_rating based on average_marks
    private void calculateInterviewsRating() {
        if (average_interviews >= 25*0.8) {
            interviews_feedback = "Excellent";
        } else if (average_interviews >= 25*0.8 | average_interviews<25*0.6) {
            interviews_feedback  = "Satisfactory";
        }
        else {
            interviews_feedback  = "Need to Improve";
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
