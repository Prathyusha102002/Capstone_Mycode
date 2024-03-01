package com.empbulletin.bootcampersbulletin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "EmployeeMarks")
public class EmployeeMarks {

    @Id
    @Column(name = "EMP_ID")
    private Long empID;

}
