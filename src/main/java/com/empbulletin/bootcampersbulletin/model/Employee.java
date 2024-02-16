package com.empbulletin.bootcampersbulletin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
@Getter
@Setter
@Table(name = "Employee")
public class Employee {

	@Id
	@Column(name="emp_id")
	private Long emp_id; // Assuming this is the ID for Employee

	@Column(name = "emp_name")
	private String emp_name;

	@Column(name = "emp_mail")
	private String emp_mail;

	@Column(name="password_hash")
	private String passwordHash; // Store the hashed password instead of plaintext password

	@Column(name="batchNo")
	private Integer batchNo;

	public Employee(Long emp_id, String emp_name, String emp_mail, String password, Integer batchNo) {
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.emp_mail = emp_mail;
		setPassword(password); // Hash the password before setting
		this.batchNo = batchNo;
	}

	public Employee() {
	}

	public void setEmp_id(Long emp_id) {
		this.emp_id = emp_id;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public void setEmp_mail(String emp_mail) {
		this.emp_mail = emp_mail;
	}

	public void setPassword(String password) {
		// Hash the password using BCrypt
		this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public void setBatchNo(Integer batchNo) {
		this.batchNo = batchNo;
	}

	public Long getEmp_id() {
		return emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public String getEmp_mail() {
		return emp_mail;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public Integer getBatchNo() {
		return batchNo;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"emp_id=" + emp_id +
				", emp_name='" + emp_name + '\'' +
				", emp_mail='" + emp_mail + '\'' +
				", passwordHash='" + passwordHash + '\'' +
				", batchNo=" + batchNo +
				'}';
	}
}
