package com.empbulletin.bootcampersbulletin.repository;

import com.empbulletin.bootcampersbulletin.model.EmployeeMarks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeMarksRepository extends JpaRepository<EmployeeMarks, Long> {

    //all crud operations

}
