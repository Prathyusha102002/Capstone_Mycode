package com.empbulletin.bootcampersbulletin.service;

import com.empbulletin.bootcampersbulletin.model.Admin;
import com.empbulletin.bootcampersbulletin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    public boolean authenticateAdmin(String name, String password) {
        Admin admin = adminRepository.findByNameAndPassword(name, password);
        return admin != null;
    }
}
