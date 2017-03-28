package com.rakmo.eapplicant.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.rakmo.eapplicant.entity.ApplicationRequest;
import com.rakmo.eapplicant.entity.Credentials;
import com.rakmo.eapplicant.entity.Student;

public class EADAOImpl implements EADAO{
	@Autowired
	SessionFactory sessionFactory;
	@PostConstruct
	public void display() {
		System.out.println("sessionFactory injected"); 
	}
	
	@Override
	public List<ApplicationRequest> getAllApplnRequests() {
		Session session = sessionFactory.openSession();
		List<ApplicationRequest> requests  = (List<ApplicationRequest>) session.createCriteria(ApplicationRequest.class).list();
		session.flush();
		session.close();
		return requests;
	}
	 // 1 - student 2 - Admin  3- IStaff 4 -VStaff 5 - PStaff


	@Override
	public List<ApplicationRequest> getApprovedRequestsByIStaff() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(ApplicationRequest.class);
		List<ApplicationRequest> requests = (List<ApplicationRequest>) criteria.add(Restrictions.eq("pendingWith",3) ).list();
		session.flush();
		session.close();
		return requests;
	}

	@Override
	public List<ApplicationRequest> getApprovedRequestsByVStaff() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(ApplicationRequest.class);
		List<ApplicationRequest> requests = (List<ApplicationRequest>) criteria.add(Restrictions.eq("pendingWith",4) ).list();
		session.flush();
		session.close();
		return requests;
	}

	@Override
	public List<ApplicationRequest> getApprovedRequests() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(ApplicationRequest.class);
		List<ApplicationRequest> requests = (List<ApplicationRequest>) criteria.add(Restrictions.eq("pendingWith",5) ).list();
		session.flush();
		session.close();
		return requests;
	}

	
	@Override
	public boolean addApplicationRequest(ApplicationRequest request) {
		

		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.saveOrUpdate(request);
			session.flush();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public boolean addStudent(Student student) {
		

		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.saveOrUpdate(student);
			session.flush();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean addOtherUser(Credentials credentials) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.saveOrUpdate(credentials);
			session.flush();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	

	@Override
	public boolean verifyUser(Credentials credentials) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Credentials.class);
		Credentials OrgCredentials = null;
		try
		{
		 OrgCredentials = (Credentials) criteria.add(Restrictions.eq("userType",credentials.getUserType() ) ).
				add(Restrictions.eq("userName", credentials.getUserName()))
				.add(Restrictions.eq("password", credentials.getPassword())).list().get(0);
		}
		catch(IndexOutOfBoundsException iOBEx)
		{
			return false;
		}
		session.flush();
		session.close();
		if(OrgCredentials == null)
			return false;
		else
			return true;
	}

	@Override
	public Student getStudentWithUserName(String userName) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Student.class);
		criteria.createCriteria("credentials","cred");
		Student student = null;
		List<Student> students =  criteria.add(Restrictions.eq("cred.userName", userName)).list();
		if(students.size() >0)
		student = students.get(0);
		session.flush();
		session.close();
		return student;
	}

	@Override
	public List<ApplicationRequest> getAllApplnRequestsByStudent(String userName) {
		List<ApplicationRequest> allRequests = getAllApplnRequests();
		List<ApplicationRequest> requestsByStudent = new ArrayList<ApplicationRequest>();
		for(ApplicationRequest request: allRequests)
		{
			if(request.getStudent().getCredentials().getUserName().equalsIgnoreCase(userName))
				requestsByStudent.add(request);
		}
		return requestsByStudent;
	}

	@Override
	public boolean changeApplicationRequest(int requestId, int pendingWith, int status) {
		Session session = sessionFactory.openSession();
		ApplicationRequest request = (ApplicationRequest) session.createCriteria(ApplicationRequest.class).
				add(Restrictions.eq("id", requestId)).list().get(0);
		request.setPendingWith(pendingWith);
		request.setStatus(status);
		System.out.println(request.getId() + " "+request.getPendingWith());
		updateRequest(request);
		session.flush();
		session.close();
		//session.flush();
		return true;
	}

	

	private void updateRequest(ApplicationRequest request) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(request);
		session.getTransaction().commit();
		session.flush();
		session.close();
		
	}

	@Override
	public List<ApplicationRequest> getPendingRequests(int pendingWith, int status) {
		Session session = sessionFactory.openSession();
		List<ApplicationRequest> requests  = new ArrayList<ApplicationRequest>();
		for(ApplicationRequest request: getAllApplnRequests())
		{
			if(request.getPendingWith() == pendingWith && request.getStatus() == status)
				requests.add(request);
		}
		
		session.flush();
		session.close();
		return requests;
	}

	@Override
	public ApplicationRequest getApplicationRequestById(int requestId) {
		Session session = sessionFactory.openSession();
		ApplicationRequest request = (ApplicationRequest) session.createCriteria(ApplicationRequest.class).
				add(Restrictions.eq("id", requestId)).list().get(0);
		session.flush();
		session.close();
		return request;
	}

	
}
