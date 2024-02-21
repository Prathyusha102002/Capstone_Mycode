package com.empbulletin.bootcampersbulletin.service;

import com.empbulletin.bootcampersbulletin.model.Employee;
import com.empbulletin.bootcampersbulletin.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean authenticate(Long emp_id, String password) {
        Employee employee = employeeRepository.findById(emp_id).orElse(null);
        if (employee != null) {
            return BCrypt.checkpw(password, employee.getPasswordHash());
        }
        return false;
    }
}
