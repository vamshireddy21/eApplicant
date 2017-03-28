package com.rakmo.eapplicant.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "applications_requests")

public class ApplicationRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@JoinColumn(name = "request_type")
	//Transfer Certificate/Bonafide Certificate/Custodian Certificate
	private int typeOfRequest; // 1 - TC 2- BC 3 - CC
	@JoinColumn(name = "request_reason", nullable = false)
	private String reasonForRequest;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Student student;
	private Date requestedDate;
	private int status;// 1- approved 2 - rejected 3 - progress
	private int pendingWith; // user type entry with Whom It is pending with Ex
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTypeOfRequest() {
		return typeOfRequest;
	}
	public void setTypeOfRequest(int typeOfRequest) {
		this.typeOfRequest = typeOfRequest;
	}
	public String getReasonForRequest() {
		return reasonForRequest;
	}
	public void setReasonForRequest(String reasonForRequest) {
		this.reasonForRequest = reasonForRequest;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Date getRequestedDate() {
		return requestedDate;
	}
	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPendingWith() {
		return pendingWith;
	}
	public void setPendingWith(int pendingWith) {
		this.pendingWith = pendingWith;
	}
	
}
