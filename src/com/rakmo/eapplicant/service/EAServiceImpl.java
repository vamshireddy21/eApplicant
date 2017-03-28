package com.rakmo.eapplicant.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.rakmo.eapplicant.dao.EADAO;
import com.rakmo.eapplicant.entity.ApplicationRequest;
import com.rakmo.eapplicant.entity.Credentials;
import com.rakmo.eapplicant.entity.Student;

public class EAServiceImpl implements EAService{

	@Autowired
	private EADAO eADAOImpl;
	
	@PostConstruct
	public void display()
	{
		System.out.println("EADAOImpl injected");
	}

	@Override
	public List<ApplicationRequest> getAllApplnRequests() {
		return eADAOImpl.getAllApplnRequests();
	}


	@Override
	public List<ApplicationRequest> getApprovedRequestsByIStaff() {
		return eADAOImpl.getApprovedRequestsByIStaff();
	}

	@Override
	public List<ApplicationRequest> getApprovedRequestsByVStaff() {
		return eADAOImpl.getApprovedRequestsByVStaff();
	}

	@Override
	public List<ApplicationRequest> getApprovedRequests() {
		return eADAOImpl.getApprovedRequests();
	}

	@Override
	public boolean addStudent(Student student) {
		return eADAOImpl.addStudent(student);
	}

	@Override
	public boolean addOtherUser(Credentials credentials) {
		return eADAOImpl.addOtherUser(credentials);
	}

	@Override
	public boolean verifyUser(Credentials credentials) {
		return eADAOImpl.verifyUser(credentials);
	}

	@Override
	public Student getStudentWithUserName(String userName) {
		return eADAOImpl.getStudentWithUserName(userName);
	}

	@Override
	public boolean addApplicationRequest(ApplicationRequest request) {
		return eADAOImpl.addApplicationRequest(request);
	}

	@Override
	public List<ApplicationRequest> getAllApplnRequestsByStudent(String userName) {
		return eADAOImpl.getAllApplnRequestsByStudent(userName);
	}

	@Override
	public boolean changeApplicationRequest(int requestId, int pendingWith, int status) {
		try
		{
		eADAOImpl.changeApplicationRequest(requestId, pendingWith, status);
		}
		catch(Exception ex)
		{
			return false;
		}
		return true;
	}

	@Override
	public List<ApplicationRequest> getPendingRequests(int pendingWith, int status) {
		List<ApplicationRequest> requests = new ArrayList<ApplicationRequest>();
		if((pendingWith == 4) && (status == 3))
		{
			List<ApplicationRequest> iStaffRequests = 
			  eADAOImpl.getPendingRequests(3, 3);
			for(ApplicationRequest req: iStaffRequests)
			{
				long timeDiffMilliSeconds = new Date().getTime() -
						req.getRequestedDate().getTime();
				long timeDiffInHours = TimeUnit.MILLISECONDS.toHours(timeDiffMilliSeconds);
				
				
				if(timeDiffInHours > 1)
				{
					changeApplicationRequest(req.getId(), 4, 3);
					System.out.println("Pending Request "+req.getId() +" moved to High Staff for time gap");
				}				
					
			}
			
		}
		return eADAOImpl.getPendingRequests(pendingWith, status);
	}

	@Override
	public ApplicationRequest getApplicationRequestById(int requestId) {
		return eADAOImpl.getApplicationRequestById(requestId);
	}

	

	

	
	
}
