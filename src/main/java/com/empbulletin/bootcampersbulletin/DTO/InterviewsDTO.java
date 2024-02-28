package com.empbulletin.bootcampersbulletin.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InterviewsDTO {

    private Long interviews_id;
    private EmployeeDTO employee;
    private Float unix;
    private Float sequel;
    private Float java;
    private Float testing;
    private Float python;
    private Float aiml;
    private Float azure;
    private Float git;
    private Float jenkins;
    private Float devops;
}
