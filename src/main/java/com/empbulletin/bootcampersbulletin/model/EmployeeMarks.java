package com.empbulletin.bootcampersbulletin.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "Employee_Marks")

public class EmployeeMarks {

    @Id
    private long emp_id; // Foreign key and primary key

    @MapsId
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @Column(name = "Unix")
    private float unix;

    @Column(name = "SQL_Database")
    private float sqldb;

    @Column(name = "Java")
    private float java;

    @Column(name = "Testing")
    private float st;

    @Column(name = "Python")
    private float python;

    @Column(name = "AIML")
    private float aiml;

    @Column(name = "Azure")
    private float azure;

    @Column(name = "GIT")
    private float git;

    @Column(name = "Jenkins")
    private float jenkins;

    @Column(name = "Devops")
    private float devops;

    public EmployeeMarks() {
    }

    public EmployeeMarks(long emp_id, Employee employee, float unix, float sqldb, float java, float st, float python, float aiml, float azure, float git, float jenkins, float devops) {
        this.emp_id = emp_id;
        this.employee = employee;
        this.unix = unix;
        this.sqldb = sqldb;
        this.java = java;
        this.st = st;
        this.python = python;
        this.aiml = aiml;
        this.azure = azure;
        this.git = git;
        this.jenkins = jenkins;
        this.devops = devops;
    }

    @Override
    public String toString() {
        return "EmployeeMarks{" +
                "emp_id=" + emp_id +
                ", employee=" + employee +
                ", unix=" + unix +
                ", sqldb=" + sqldb +
                ", java=" + java +
                ", st=" + st +
                ", python=" + python +
                ", aiml=" + aiml +
                ", azure=" + azure +
                ", git=" + git +
                ", jenkins=" + jenkins +
                ", devops=" + devops +
                '}';
    }

}
