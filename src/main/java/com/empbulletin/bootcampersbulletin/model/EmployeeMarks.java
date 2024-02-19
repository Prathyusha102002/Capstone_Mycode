package com.empbulletin.bootcampersbulletin.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employee_Marks")

public class EmployeeMarks {

    public long getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(long emp_id) {
        this.emp_id = emp_id;
    }

    @Id
    private long emp_id; // Foreign key and primary key

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @MapsId
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id")
    private Employee employee;


    public float getUnix() {
        return unix;
    }

    public void setUnix(float unix) {
        this.unix = unix;
    }

    @Column(name = "Unix")
    private float unix;

    public float getSqldb() {
        return sqldb;
    }

    public void setSqldb(float sqldb) {
        this.sqldb = sqldb;
    }

    @Column(name = "SQL_Database")
    private float sqldb;

    public float getJava() {
        return java;
    }

    public void setJava(float java) {
        this.java = java;
    }

    @Column(name = "Java")
    private float java;

    public float getSt() {
        return st;
    }

    public void setSt(float st) {
        this.st = st;
    }

    @Column(name = "Testing")
    private float st;

    public float getPython() {
        return python;
    }

    public void setPython(float python) {
        this.python = python;
    }

    @Column(name = "Python")
    private float python;

    public float getAiml() {
        return aiml;
    }

    public void setAiml(float aiml) {
        this.aiml = aiml;
    }

    @Column(name = "AIML")
    private float aiml;

    public float getAzure() {
        return azure;
    }

    public void setAzure(float azure) {
        this.azure = azure;
    }

    @Column(name = "Azure")
    private float azure;

    public float getGit() {
        return git;
    }

    public void setGit(float git) {
        this.git = git;
    }

    @Column(name = "GIT")
    private float git;

    public float getJenkins() {
        return jenkins;
    }

    public void setJenkins(float jenkins) {
        this.jenkins = jenkins;
    }

    @Column(name = "Jenkins")
    private float jenkins;

    public float getDevops() {
        return devops;
    }

    public void setDevops(float devops) {
        this.devops = devops;
    }

    @Column(name = "Devops")
    private float devops;

}
