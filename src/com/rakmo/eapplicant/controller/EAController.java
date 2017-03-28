package com.rakmo.eapplicant.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rakmo.eapplicant.dto.ApplicationRequestDTO;
import com.rakmo.eapplicant.entity.ApplicationRequest;
import com.rakmo.eapplicant.entity.Credentials;
import com.rakmo.eapplicant.entity.Student;
import com.rakmo.eapplicant.service.EAService;
import com.rakmo.eapplicant.util.EApplicantMap;

@Controller
public class EAController {
	@Autowired
	private EAService eAService;// = new
								// EAServiceImpl();
	
	

	@PostConstruct
	public void display() {
		System.out.println("EAService injected");
	}

	public EAService geteAService() {
		return eAService;
	}

	public void seteAService(EAService eAService) {
		this.eAService = eAService;
	}

	@RequestMapping(value = { "/", "","welcome" })
	public ModelAndView getApplicationPage() {
		System.out.println("Application Start");
		ModelAndView modelView = new ModelAndView("welcomepage", "credentials", new Credentials());
		return modelView;
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(@ModelAttribute Credentials credentials, BindingResult result) {
		ModelAndView modelView = null;
		boolean isValidCred = eAService.verifyUser(credentials);
		// 1 - student 2 - Admin 3- IStaff 4 -HStaff 5 - VPStaff 6- PStaff
		int userType = credentials.getUserType();
		System.out.println(userType);
		System.out.println(isValidCred);
		List<ApplicationRequest> applnRequests = null;
		if (isValidCred) {
			switch (userType) {
			case 1:
				modelView = new ModelAndView("student");
				modelView.addObject("credentials", credentials);
				eAService.getAllApplnRequestsByStudent(credentials.getUserName());
				applnRequests = eAService.getAllApplnRequestsByStudent(credentials.getUserName());
				List<ApplicationRequestDTO> requests = getApplicationRequestDTOs(applnRequests);
				modelView.addObject("requests", requests);
				break;
			case 2:
				modelView = new ModelAndView("admin");
				applnRequests = eAService.getAllApplnRequests();
				List<ApplicationRequestDTO> allRequests = getApplicationRequestDTOs(applnRequests);
				modelView.addObject("requests", allRequests);
				break;
			case 3:
				modelView = new ModelAndView("istaff");
				applnRequests = eAService.getPendingRequests(3, 3);
				List<ApplicationRequestDTO> pendingReqWithIStaff = getApplicationRequestDTOs(applnRequests);
				modelView.addObject("requests", pendingReqWithIStaff);
				break;
			case 4:
				modelView = new ModelAndView("highstaff");
				applnRequests = eAService.getPendingRequests(4, 3);
				List<ApplicationRequestDTO> pendingReqWithHStaff = getApplicationRequestDTOs(applnRequests);
				modelView.addObject("requests", pendingReqWithHStaff);
				break;
			case 5:
				modelView = new ModelAndView("viceprincipal");
				applnRequests = eAService.getPendingRequests(5, 3);
				List<ApplicationRequestDTO> pendingReqWithVP = getApplicationRequestDTOs(applnRequests);
				modelView.addObject("requests", pendingReqWithVP);
				break;
			case 6:
				modelView = new ModelAndView("principal");
				applnRequests = eAService.getPendingRequests(6, 3);
				List<ApplicationRequestDTO> pendingReqWithP = getApplicationRequestDTOs(applnRequests);
				modelView.addObject("requests", pendingReqWithP);
				break;
			}
		} else {
			modelView = new ModelAndView("welcomepage", "credentials", new Credentials());
			modelView.addObject("errorMessage", "Invalid User Name / Password ");
		}
		System.out.println(modelView.getViewName());
		System.out.println("End of login action method");
		return modelView;
	}

	private List<ApplicationRequestDTO> getApplicationRequestDTOs(List<ApplicationRequest> applnRequests) {
		List<ApplicationRequestDTO> requests = new ArrayList<ApplicationRequestDTO>();

		for (ApplicationRequest request : applnRequests) {
			ApplicationRequestDTO requestDTO = new ApplicationRequestDTO();
			requestDTO.setId(request.getId());
			requestDTO.setReason(request.getReasonForRequest());
			requestDTO.setRequestedDate(request.getRequestedDate());
			requestDTO.setRequestType(EApplicantMap.getTypeOfCertificate(request.getTypeOfRequest()));
			requestDTO.setStatus(EApplicantMap.getStatus(request.getStatus()));
			requestDTO.setStudent(request.getStudent());
			if(request.getStatus() == 1)
			{
				requestDTO.setPendingWith("Approved by Everyone");
			}
			else
			{
				requestDTO.setPendingWith(EApplicantMap.getUserType(request.getPendingWith()));
			}
			requests.add(requestDTO);
		}
		return requests;
	}



	@RequestMapping("/applnRequest")
	public ModelAndView applicationRequest(@RequestParam String userName, @RequestParam int requestType,
			HttpSession session) {
		ModelAndView modelView = null;
		// 1 - student 2 - Admin 3- IStaff 4 -HStaff 5 - VPStaff 6- PStaff
		ApplicationRequest applnRequest = new ApplicationRequest();
		applnRequest.setPendingWith(3);
		applnRequest.setRequestedDate(new Date());
		applnRequest.setStatus(3);
		applnRequest.setStudent(eAService.getStudentWithUserName(userName));
		applnRequest.setTypeOfRequest(requestType);
		// applnRequest.setReasonForRequest(reasonForRequest);
		session.setAttribute("applicationRequest", applnRequest);
		modelView = new ModelAndView("reason");
		modelView.addObject("appplicatonRequest", applnRequest);

		return modelView;
	}

	@RequestMapping("/completeRequest")
	public ModelAndView completeRequest(HttpServletRequest request, HttpSession session) {
		ModelAndView modelView = null;
		// System.out.println(request.getParameterNames().nextElement().toString());
		String reason = request.getParameter("reason");

		System.out.println(reason);
		if (reason.isEmpty()) {
			modelView = new ModelAndView("reason");
			modelView.addObject("errorMessage", "Enter the Appropriate Reason");
			return modelView;
		}
		ApplicationRequest applnRequest = (ApplicationRequest) session.getAttribute("applicationRequest");
		applnRequest.setReasonForRequest(reason);
		boolean isSuccess = eAService.addApplicationRequest(applnRequest);
		if (!isSuccess) {
			modelView = new ModelAndView("reason");
			modelView.addObject("errorMessage", "Make sure that Database is Connected");
			return modelView;
		}
		modelView = new ModelAndView("student");
		List<ApplicationRequest> applnRequests = eAService
				.getAllApplnRequestsByStudent(applnRequest.getStudent().getCredentials().getUserName());
		List<ApplicationRequestDTO> requests = getApplicationRequestDTOs(applnRequests);
		modelView.addObject("requests", requests);
		return modelView;

	}

	@RequestMapping("/move")
	public ModelAndView move(@RequestParam int requestId, @RequestParam int from) {
		ModelAndView modelView = null;

		modelView = new ModelAndView();
		if (from == 3) {
			eAService.changeApplicationRequest(requestId, 5, 3);
			List<ApplicationRequest> applnRequests = eAService.getPendingRequests(3, 3);
			List<ApplicationRequestDTO> pendingReqWithIStaff = getApplicationRequestDTOs(applnRequests);
			modelView.addObject("requests", pendingReqWithIStaff);
			modelView.setViewName("istaff");
			modelView.addObject("errorMessage", "Request with " + requestId
					+ " has been successfully processed and waiting for Vice Principal Signature");

		} else if (from == 4) {
			eAService.changeApplicationRequest(requestId, 5, 3);
			List<ApplicationRequest> applnRequests = eAService.getPendingRequests(4, 3);
			List<ApplicationRequestDTO> pendingReqWithHStaff = getApplicationRequestDTOs(applnRequests);
			modelView.addObject("requests", pendingReqWithHStaff);
			modelView.setViewName("highstaff");
			modelView.addObject("errorMessage", "Request with " + requestId
					+ " has been successfully processed and waiting for Vice Principal Signature");
		} else if (from == 5) {
			eAService.changeApplicationRequest(requestId, 6, 3);
			List<ApplicationRequest> applnRequests = eAService.getPendingRequests(5, 3);
			List<ApplicationRequestDTO> pendingReqWithVP = getApplicationRequestDTOs(applnRequests);
			modelView.addObject("requests", pendingReqWithVP);
			modelView.setViewName("viceprinicpal");
			modelView.addObject("errorMessage", "Request with " + requestId
					+ " has been successfully processed and waiting for Principal Signature");
		} else if (from == 6) {
			eAService.changeApplicationRequest(requestId, 6, 1);
			List<ApplicationRequest> applnRequests = eAService.getPendingRequests(6, 3);
			List<ApplicationRequestDTO> pendingReqWithP = getApplicationRequestDTOs(applnRequests);
			modelView.addObject("requests", pendingReqWithP);
			modelView.setViewName("principal");
			ApplicationRequest req = eAService.getApplicationRequestById(requestId);
			modelView.addObject("errorMessage", "Request with Id" + requestId + " of student "+
			req.getStudent().getName()+" has been completely Approved and sent an email about Confirmation");
			 
			try
			{
			sendMailToStudent(req);
			
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				modelView.addObject("errorMessage", "Request with Id " + requestId + " has been completely Approved of student "+
			req.getStudent().getName()+" but sending mail to student got failed");
			}
			
		}

		return modelView;
	}

	private void sendMailToStudent(ApplicationRequest req) throws Exception {
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername(EApplicantMap.adminMailId);
		mailSender.setPassword(EApplicantMap.adminPassword);
		mailSender.setHost("smtp.gmail.com");
		mailSender.setProtocol("smtp");
		Properties prop = mailSender.getJavaMailProperties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		mailSender.setJavaMailProperties(prop);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(EApplicantMap.adminMailId);
		message.setTo(req.getStudent().getEmailId());
		message.setSubject("Your Request for "+ EApplicantMap.getTypeOfCertificate(req.getTypeOfRequest()) 
		+" with Id " + req.getId()+ " Approved from Principal");
		message.setText("Hi "+req.getStudent().getName() +"\n Your request got approved! Collect from Admin Office");
		mailSender.send(message);
		System.out.println("Mail sent to "+req.getStudent().getEmailId() +" successfully");
	}

	@RequestMapping("/reject")
	public ModelAndView reject(@RequestParam int requestId, @RequestParam int from) {
		ModelAndView modelView = null;
		eAService.changeApplicationRequest(requestId, 5, 2);
		modelView = new ModelAndView();
		if (from == 3) {
			modelView.setViewName("istaff");
			List<ApplicationRequest> applnRequests = eAService.getPendingRequests(3, 3);
			List<ApplicationRequestDTO> pendingReqWithIStaff = getApplicationRequestDTOs(applnRequests);
			modelView.addObject("requests", pendingReqWithIStaff);
		} if(from == 4) {
			modelView.setViewName("highstaff");
			List<ApplicationRequest> applnRequests = eAService.getPendingRequests(4, 3);
			List<ApplicationRequestDTO> pendingReqWithHStaff = getApplicationRequestDTOs(applnRequests);
			modelView.addObject("requests", pendingReqWithHStaff);
		}
		else if(from ==5)
		{
			modelView.setViewName("viceprincipal");	
			List<ApplicationRequest> applnRequests = eAService.getPendingRequests(5, 3);
			List<ApplicationRequestDTO> pendingReqWithVP = getApplicationRequestDTOs(applnRequests);
			modelView.addObject("requests", pendingReqWithVP);
		}
		else if(from ==6)
		{
			modelView.setViewName("principal");
			List<ApplicationRequest> applnRequests = eAService.getPendingRequests(6, 3);
			List<ApplicationRequestDTO> pendingReqWithP = getApplicationRequestDTOs(applnRequests);
			modelView.addObject("requests", pendingReqWithP);
		}
		modelView.addObject("errorMessage", "Request with " + requestId + " has been successfully Rejected");
		return modelView;
	}

	@RequestMapping("/signup")
	public ModelAndView signUp() {
		ModelAndView modelView = new ModelAndView("registration");
		return modelView;
	}

	@RequestMapping("/registration")
	public ModelAndView register(HttpServletRequest request, HttpSession session) {

		ModelAndView modelView = null;
		Student student = new Student();
		String name = request.getParameter("name");

		String course = request.getParameter("course");

		String dept = request.getParameter("department");

		String emailId = request.getParameter("emailId");

		String rollNo = request.getParameter("rollNo");
		String yearS = request.getParameter("year");
		String semisterS = request.getParameter("semister");

		Credentials credentials = new Credentials();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("cpassword");
		if (name.isEmpty() || course.isEmpty() || dept.isEmpty() || emailId.isEmpty() || rollNo.isEmpty()
				|| yearS.isEmpty() || semisterS.isEmpty()) {
			modelView = new ModelAndView("registration");
			modelView.addObject("errorMessage", "No Field should be Empty!!");
			return modelView;
		}
		if (password.compareTo(cpassword) != 0) {
			modelView = new ModelAndView("registration");
			modelView.addObject("errorMessage", "Password and Confirm Password should be same!!");
			return modelView;
		}
		if(eAService.getStudentWithUserName(userName) !=null )
		{
			modelView = new ModelAndView("registration");
			modelView.addObject("errorMessage", "User name already Existed! Please choose a unique name");
			return modelView;
		}
		int year,semister;
		try
		{
		year = Integer.parseInt(yearS);
		semister = Integer.parseInt(semisterS);
		}
		catch(NumberFormatException nFE)
		{
			modelView = new ModelAndView("registration");
			modelView.addObject("errorMessage", "Year and Semister should be a number only ");
			return modelView;
		}
		catch(Exception ex)
		{
			modelView = new ModelAndView("registration");
			modelView.addObject("errorMessage", "User name already Existed! Please choose a unique name");
			return modelView;
		}
		student.setDepartment(dept);
		student.setName(name);
		student.setCourse(course);
		student.setEmailId(emailId);
		student.setRollNo(rollNo);
		student.setYear(year);
		student.setSemister(semister);
		credentials.setUserType(1);// setting 1 for Student
		credentials.setUserName(userName);
		credentials.setPassword(password);
		student.setCredentials(credentials);
		eAService.addOtherUser(credentials);
		eAService.addStudent(student);
		modelView = new ModelAndView("welcomepage", "credentials", new Credentials());
		modelView.addObject("errorMessage", "Congrats! Your registration is completed Successfully");
		return modelView;
	}
	
	@RequestMapping("/viewDocument")
	public ModelAndView viewDocument(@RequestParam int requestId)
	{
		ModelAndView modelView = null;
		ApplicationRequest request = eAService.getApplicationRequestById(requestId);
		modelView = new ModelAndView("requestdocument");
		modelView.addObject("request", request);
		return modelView;
	}

	

}