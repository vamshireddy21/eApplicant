package com.rakmo.eapplicant.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * 
	Name
	Email-id
	Phone number
	Gender
	Date of Birth
	Address
	Username
	Password
	Re-enter Password

 */
@Entity
@Table(name="students")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String course;
	private String department;
	private String emailId;
	private String rollNo;
	private int year;
	private int semister;
	private String mobileNo;
	private String fatherName;
	@OneToOne
	private Credentials credentials;
	@OneToMany(mappedBy="student", fetch= FetchType.EAGER)
	private Set<ApplicationRequest> applicationRequests;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public Credentials getCredentials() {
		return credentials;
	}
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	public Set<ApplicationRequest> getApplicationRequests() {
		return applicationRequests;
	}
	public void setApplicationRequests(Set<ApplicationRequest> applicationRequests) {
		this.applicationRequests = applicationRequests;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getSemister() {
		return semister;
	}
	public void setSemister(int semister) {
		this.semister = semister;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	
}
