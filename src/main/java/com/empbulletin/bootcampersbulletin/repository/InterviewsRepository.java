package com.empbulletin.bootcampersbulletin.repository;

import com.empbulletin.bootcampersbulletin.model.Interviews;
import com.empbulletin.bootcampersbulletin.model.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InterviewsRepository extends JpaRepository<Interviews, Long> {
    @Query("SELECT ir FROM Interviews ir WHERE ir.employee.emp_id = :emp_id AND ir.interviews_id = :interviews_id")
    Interviews findByEmpIdAndInterviewsId(
            @Param("emp_id") Long emp_id,
            @Param("interviews_id") Long interviews_id
    );


}
