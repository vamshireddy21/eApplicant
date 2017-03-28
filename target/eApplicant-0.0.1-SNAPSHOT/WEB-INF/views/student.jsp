<%@page import="java.util.Date"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta content="text/html; charset=UTF-8" http-equiv="content-type" />
<title>E Applicant System</title>
<style>
html {
  background-color: #333;
}
body {
  background-color: #fff;
  width: 750px;
  margin: 0 auto;
}
input, textarea { 
    width:12em; 
    border-radius:2px; 
    border: solid 1px #ccc; 
    padding:0.4em; 
    background-color: #f5f5f5; 
    box-shadow: inset 0 2px 3px rgba(0,0,0,0.2); 
}
textarea { 
    width:100%;
    height:10em; 
}
input[type="submit"], input[type="reset"] { 
        border: solid 1px #ccc; 
        padding:0.4em 0.7em; 
	color:white;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	-ms-border-radius: 5px;
	-o-border-radius: 5px;
	border-radius: 5px;
 /*To make the state change a little real*/
	-webkit-transition: background-color 200ms linear;	      /*Safari and Chrome*/
	-moz-transition: background-color 200ms linear; 	      /*Firefox*/
	-o-transition: background-color 200ms linear;	      /*opera*/
	-ms-transition: background-color 200ms linear;
	transition: all 200ms linear;
	background-color: #16a6b6;
	cursor: pointer;
	text-align: center;
}
input[type="submit"]:hover, input[type="reset"]:hover  , .button:hover{
	background:#ee6557;
}
input[type="submit"]:active, input[type="reset"]:active, .button:active{
	background:rgba(255,51,0,1);
}
select { 
    background-repeat:no-repeat;
    background-position:right;
    -webkit-appearance: none /* this is required for Webkit browsers */; 
    -moz-appearance:    none /* this is not supported currently */;
    appearance:         none;
    background-color:#e6e6e5;
    min-width:12em; 
     box-shadow: 2px 1px 2px 1px rgba(0,0,0,0.1); 
    border: solid 1px #ccc; 
    border-radius:5px; 
}
form {
    width: 80%;
    margin: 0 auto;
}

h1,h2,h3,h4,h5,h6 { color: #7c795d; font-family: 'Trocchi', serif;  font-weight: normal; line-height: 48px; margin: 0; }
​table, caption, tbody, tfoot, thead, tr, th, td {
  margin: 2%;
  padding: 3%;
  border: 0;
  font-size: 100%;
  font: inherit;
  vertical-align: baseline;
}
table {
  border-collapse: collapse;
  border-spacing: 0;
}
img
{
	width:100%;
	height:100%;
}
.nav ul {
  list-style: none;
  background-color: #444;
  text-align: center;
  padding: 0;
  margin: 0;
}
.nav li {
  font-family: 'Oswald', sans-serif;
  font-size: 1.2em;
  line-height: 40px;
  height: 40px;
  border-bottom: 1px solid #888;
}
 
.nav a {
  text-decoration: none;
  color: #fff;
  display: block;
  transition: .3s background-color;
}
 
.nav a:hover {
  background-color: #005f5f;
}
 
.nav a.active {
  background-color: #fff;
  color: #444;
  cursor: default;
}
</style>
</head>
<body>

<center>
<nav>|
<a href="welcome">Log out</a> 
</nav>
<h1>Student Portal</h1>
<h4>${errorMessage}</h4>
<br>
<br>
<br>
<br>
<h3>Applied Requests</h3>
<table>
		<tr>
			<th>Request Id </th>
			<th>Type of Request</th>
			<th colspan="3">Reason</th>
			<th colspan="2">Requested Date</th>
			<th>Pending with</th>
			<th>Status</th>
			<th>View Request Document</th>
		</tr>

		<c:forEach items="${requests}" var="request">
			<tr>
				<td><c:out value="${request.id}" />
				</td>
				<td><c:out value="${request.requestType}" />
				</td>
				<td colspan="3"><c:out value="${request.reason}" />
				</td>
				
				<td colspan="2"><c:set var="dat" value="${request.requestedDate }" />
				<c:out value="${dat.date}/${dat.month}/${dat.year} ${dat.hours}:${dat.minutes}  " />
				</td>
			
				<td><c:out value="${request.pendingWith}" />
				</td>
				
				<td><c:out value="${request.status}" />
				</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<c:out value="viewDocument?requestId=${request.id }" />" >View</a>
					</td>
			</tr>
		</c:forEach>
	</table>
	<h3>Apply for New Request</h3>
<table>
<tr>
<!--  
<td><a href=" "><img src="../images//bc.PNG"/></a></td>
<td><a href=" "><img src="../images/tc.PNG"/></a></td>
<td><a href=" "><img src="../images/cc.PNG"/></a></td>

-->

<td><a href="<c:url value="applnRequest?userName=${credentials.userName}&requestType=1 " />">
<img src="/eApplicant/WebContent/WEB-INF/views/tc.PNG" alt="Request for Transfer Certificate"/></a></td>
<td><a href="<c:url value="applnRequest?userName=${credentials.userName}&requestType=2 " />">
<img src="/eApplicant/WebContent/WEB-INF/views/bc.PNG" alt="Request for Bonafide Certificate"/></a></td>
<td><a href="<c:url value="applnRequest?userName=${credentials.userName}&requestType=3 " />">
<img src="/eApplicant/WebContent/WEB-INF/views/cc.PNG" alt="Request for Custodian Certificate"/></a></td>
</tr>
</table>
</center>

</body>
</html>