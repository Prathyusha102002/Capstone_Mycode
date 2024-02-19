package com.empbulletin.bootcampersbulletin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Interview")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer interview_id;

    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
    private Employee employee;

    @Column(name = "unix")
    private Float unix;
    @Column(name = "sequel")
    private Float  sequel;
    @Column(name = "java")
    private Float  java;
    @Column(name = "testing")
    private Float testing;
    @Column(name = "python")
    private Float python;
    @Column(name = "aiml")
    private Float aiml;
    @Column(name = "azure")
    private Float azure;
    @Column(name = "git")
    private Float git;
    @Column(name = "jenkins")
    private Float jenkins;
    @Column(name = "devops")
    private Float devops;

    public Interview(Integer interview_id, Employee employee, Float unix, Float sequel, Float java, Float testing, Float python, Float aiml, Float azure, Float git, Float jenkins, Float devops) {
        this.interview_id = interview_id;
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

    public Interview() {
    }

    @Override
    public String toString() {
        return "Interview{" +
                "mark_id=" + interview_id +
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
