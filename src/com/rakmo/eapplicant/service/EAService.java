package com.rakmo.eapplicant.service;

import java.util.List;

import com.rakmo.eapplicant.entity.ApplicationRequest;
import com.rakmo.eapplicant.entity.Credentials;
import com.rakmo.eapplicant.entity.Student;

public interface EAService {
	List<ApplicationRequest> getAllApplnRequestsByStudent(String userName);
	List<ApplicationRequest> getAllApplnRequests();
	List<ApplicationRequest> getApprovedRequestsByIStaff();
	List<ApplicationRequest> getApprovedRequestsByVStaff();
	List<ApplicationRequest> getApprovedRequests(); // By principal - completely approved
	List<ApplicationRequest> getPendingRequests(int pendingWith,int status);
	
	boolean addApplicationRequest(ApplicationRequest request);
	boolean addStudent(Student student);
	boolean addOtherUser(Credentials credentials);//credentials
	
	ApplicationRequest getApplicationRequestById(int requestId);
	boolean verifyUser(Credentials credentials);
	
	Student getStudentWithUserName(String userName);
	boolean changeApplicationRequest(int requestId, int pendingWith, int status);
	
}
