package com.empbulletin.bootcampersbulletin.DTO;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {


    private Long emp_id;


    private String emp_name;


    private String emp_mail;


    private Integer batchNo;


}
