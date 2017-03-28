package com.rakmo.eapplicant.dao;
import java.util.List;
/*
 * 
 * Flow:
1)Student logins and request the application
2) Admin validates the information and moves it to I.Staff
3) I.Staff moves the pending applications first it to V.Staff
4) V.Staff applies signature and movies it to P.Staff
5) P.staff approves for making the hard copies
 */

import com.rakmo.eapplicant.entity.ApplicationRequest;
import com.rakmo.eapplicant.entity.Credentials;
import com.rakmo.eapplicant.entity.Student;
public interface EADAO {
	List<ApplicationRequest> getAllApplnRequestsByStudent(String userName);
	List<ApplicationRequest> getAllApplnRequests();
	List<ApplicationRequest> getApprovedRequestsByIStaff();
	List<ApplicationRequest> getApprovedRequestsByVStaff();
	List<ApplicationRequest> getApprovedRequests(); // By principal - completely approved
	List<ApplicationRequest> getPendingRequests(int pendingWith,int status);
	
	boolean addStudent(Student student);
	boolean addOtherUser(Credentials credentials);//credentials
	boolean addApplicationRequest(ApplicationRequest request);
	
	Student getStudentWithUserName(String userName);
	ApplicationRequest getApplicationRequestById(int requestId);
	boolean verifyUser(Credentials credentials);
	
	boolean changeApplicationRequest(int requestId, int pendingWith, int status);
	
	

	
	}
