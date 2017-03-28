<%@page import="com.rakmo.eapplicant.entity.ApplicationRequest"%>
<%@page import="java.util.Date"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
pre {
   font-family: "courier new", courier, monospace;
   font-size: 18px;
}
</style>
</head>
<body>
 <c:set var="name" value="${request.student.name }" />
<c:set var="nameInCaps" value="${fn:toUpperCase(name)}" />
<c:set var="year" value="${request.student.year }"/>
<c:set var="semister" value="${request.student.semister }"/>
<c:set var="requestType" value="${request.typeOfRequest }"/>
<c:set var="pendingPerson" value="${request.pendingWith }"/>
<c:set var="status" value="${request.status }"/>
 <%
    int year =  Integer.valueOf(""+pageContext.getAttribute("year")); 
 	int semister =  Integer.valueOf(""+pageContext.getAttribute("semister"));
 	int requestType = Integer.valueOf(""+pageContext.getAttribute("requestType"));
	int pendingPerson =  Integer.valueOf(""+pageContext.getAttribute("pendingPerson"));
 	int status = Integer.valueOf(""+pageContext.getAttribute("status"));
 	
 	String vpSignsrc = "";
 	String pSignsrc = "";
 	
 	if(status == 1)
 	{
 		pSignsrc = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Autograph_of_Benjamin_Franklin.svg/220px-Autograph_of_Benjamin_Franklin.svg.png";
 		vpSignsrc = "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTSByW6wqiCp_5FGUQuHDHqJXxzr9Xx5qvHGh4ddYBLdiUILYrZ";
 	}
 	if(pendingPerson == 6 && status == 3)
 	{
 		 vpSignsrc = "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTSByW6wqiCp_5FGUQuHDHqJXxzr9Xx5qvHGh4ddYBLdiUILYrZ";
 	}
 	
 	
 	
    String romanYear = null;
    String romanSemister = null;
    String requestTypeStr = null;
    if(year ==1)
    	romanYear = "I";
    else if(year ==2)
    	romanYear = "II";
    else if(year ==3)
    	romanYear ="III";
    else if(year == 4)
    	romanYear = "IV";
    else if(year ==5)
    	romanYear = "V";
    else
    	romanYear = "VI";
    
    if(semister ==1)
    	romanSemister = "I";
    else
    	romanSemister = "II";
    
    if(requestType == 1)
    	requestTypeStr = "Transfer Certificate";
    else if(requestType == 2)
    	requestTypeStr = "Bonafied Certificate";
    else
    	requestTypeStr = "Custodian Certificate";
    
  %> 
<pre>
From										Date:<c:set var="dat" value="${request.requestedDate }" /><c:out value="${dat.date}/${dat.month}/${dat.year}   " />
Name of the Student:<c:out value="${request.student.name}" />
Roll No:<c:out value="${request.student.rollNo}" />
Name of the Course:<c:out value="${request.student.course}" />
Name of the Department:<c:out value="${request.student.department}" />
JNTU College of Engineering,
Kukatpally, Hyderabad.
Mobile No:<c:out value="${request.student.mobileNo}" />
E-mail ID:<c:out value="${request.student.emailId}" />

To
The Principal,
JNTUH,
College of Engineering,
Kukatpally, Hyderabad.
	Sub: Request for <%=requestTypeStr %>
I, <u></u><c:out value="${request.student.name}" /></u>   (S/o)/(D/o)<u></u><c:out value="${request.student.fatherName}" /></u> 
a student of       <u><%=romanYear %></u>  year   <u><c:out value="${request.student.course}" />       </u> course in <u><%=romanSemister %></u>  Semester 
M.Tech <u><c:out value="${request.student.department}" /></u>(Specialization) bearing Roll.No.<u><c:out value="${request.student.rollNo}" /></u> would   to 
apply for   <u><%=requestTypeStr %></u> for the purpose of   <u><c:out value="${request.reasonForRequest}" /></u>
I shall be obliged if you could issue the same to me.  
Thanking You.
                                                                                               
								Yours Faithfully,
								(Signature)
					(NAME OF THE STUDENT IN CAPITAL LETTERS)
                                                                     ${nameInCaps}  

</pre>
<table>
<tr>
<td>
<span style="float:left">
Vice Princiapl Signature

</span>
</td>
<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<td colspan="8"></td>
<td colspan="3">
<span style="float:right">
Principal Signature
</span>
</td>
</tr>

<tr>
<td colspan="3">
<span style="float:left">
<%
out.print("<img src=\""+vpSignsrc+"\" alt=\"Vice Principal Signature Pending\" " );

%>

</span>
</td>
<td colspan="8">
</td>
<td colspan="3">
<span style="float:right">
<%

out.print("<img src=\""+pSignsrc+"\" alt=\" Principal Signature Pending\" " );
%>
</span>
</td>
</tr>
</table>

</body>
</html>