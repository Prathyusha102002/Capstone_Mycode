package com.empbulletin.bootcampersbulletin.repository;

import com.empbulletin.bootcampersbulletin.model.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Long> {

    @Query("SELECT mr FROM Marks mr WHERE mr.employee.emp_id = :emp_id AND mr.marks_id = :marks_id")
    Marks findByEmpIdAndMarksId(
            @Param("emp_id") Long emp_id,
            @Param("marks_id") Long marks_id
    );

}