package com.rakmo.eapplicant.dto;

import java.util.Date;

import com.rakmo.eapplicant.entity.Student;

public class ApplicationRequestDTO {
	
		private int id;
		private String requestType; // 1 - TC 2- BC 3 - CC
		private String reason;
		private Student student;
		private Date requestedDate;
		private String status;// 1- approved 2 - rejected 3 - progress
		private String pendingWith; // user type entry with Whom It is pending with Ex
		public String getRequestType() {
			return requestType;
		}
		public void setRequestType(String requestType) {
			this.requestType = requestType;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
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
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getPendingWith() {
			return pendingWith;
		}
		public void setPendingWith(String pendingWith) {
			this.pendingWith = pendingWith;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
		
}