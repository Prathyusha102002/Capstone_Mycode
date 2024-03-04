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



    @ElementCollection
    @CollectionTable(name = "subject_marks", joinColumns = @JoinColumn(name = "marks_id"))
    @MapKeyColumn(name = "subject_name")
    @Column(name = "marks")
    private Map<String, Float> subjectMarks = new HashMap<>();


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
    private Float average_marks;

    @Transient
    private String marks_feedback;

    // Calculate average_marks
    @PostLoad
    public void calculateAverageMarks() {
        float totalMarks = unix + sequel + java + testing + python + aiml + azure + git + jenkins + devops;
        average_marks = totalMarks / 10;
        calculateMarksRating();
    }

    // Calculate marks_rating based on average_marks
    private void calculateMarksRating() {
        if (average_marks >= 25*0.8) {
            marks_feedback = "Excellent";
        } else if (average_marks < 25*0.8 | average_marks>=25*0.6) {
            marks_feedback = "Satisfactory";
        }
         else {
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

    public Long getMarks_id()
    {
        return marks_id;
    }

    public void setMarks_id(Long marks_id) {
        this.marks_id = marks_id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Float getUnix() {
        return unix;
    }

    public void setUnix(Float unix) {
        this.unix = unix;
    }

    public Float getSequel() {
        return sequel;
    }

    public void setSequel(Float sequel) {
        this.sequel = sequel;
    }

    public Float getJava() {
        return java;
    }

    public void setJava(Float java) {
        this.java = java;
    }

    public Float getTesting() {
        return testing;
    }

    public void setTesting(Float testing) {
        this.testing = testing;
    }

    public Float getPython() {
        return python;
    }

    public void setPython(Float python) {
        this.python = python;
    }

    public Float getAiml() {
        return aiml;
    }

    public void setAiml(Float aiml) {
        this.aiml = aiml;
    }

    public Float getAzure() {
        return azure;
    }

    public void setAzure(Float azure) {
        this.azure = azure;
    }

    public Float getGit() {
        return git;
    }

    public void setGit(Float git) {
        this.git = git;
    }

    public Float getJenkins() {
        return jenkins;
    }

    public void setJenkins(Float jenkins) {
        this.jenkins = jenkins;
    }

    public Float getDevops() {
        return devops;
    }

    public void setDevops(Float devops) {
        this.devops = devops;
    }

}
