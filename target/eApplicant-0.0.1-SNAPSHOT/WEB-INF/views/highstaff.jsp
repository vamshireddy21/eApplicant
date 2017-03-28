<%@page import="java.util.Date"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
	width: 12em;
	border-radius: 2px;
	border: solid 1px #ccc;
	padding: 0.4em;
	background-color: #f5f5f5;
	box-shadow: inset 0 2px 3px rgba(0, 0, 0, 0.2);
}

textarea {
	width: 100%;
	height: 10em;
}

input[type="submit"], input[type="reset"] {
	border: solid 1px #ccc;
	padding: 0.4em 0.7em;
	color: white;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	-ms-border-radius: 5px;
	-o-border-radius: 5px;
	border-radius: 5px;
	/*To make the state change a little real*/
	-webkit-transition: background-color 200ms linear;
	/*Safari and Chrome*/
	-moz-transition: background-color 200ms linear; /*Firefox*/
	-o-transition: background-color 200ms linear; /*opera*/
	-ms-transition: background-color 200ms linear;
	transition: all 200ms linear;
	background-color: #16a6b6;
	cursor: pointer;
	text-align: center;
}

input[type="submit"]:hover, input[type="reset"]:hover, .button:hover {
	background: #ee6557;
}

input[type="submit"]:active, input[type="reset"]:active, .button:active
	{
	background: rgba(255, 51, 0, 1);
}

select {
	background-repeat: no-repeat;
	background-position: right;
	-webkit-appearance: none /* this is required for Webkit browsers */;
	-moz-appearance: none /* this is not supported currently */;
	appearance: none;
	background-color: #e6e6e5;
	min-width: 12em;
	box-shadow: 2px 1px 2px 1px rgba(0, 0, 0, 0.1);
	border: solid 1px #ccc;
	border-radius: 5px;
}

form {
	width: 80%;
	margin: 0 auto;
}

h1, h2, h3, h4, h5, h6 {
	color: #7c795d;
	font-family: 'Trocchi', serif;
	font-weight: normal;
	line-height: 48px;
	margin: 0;
}

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
<nav>|
<a href="welcome">Log out</a> 
</nav>
	
	<center>
		<h1>High Staff Portal</h1>
		<h4>${errorMessage}</h4>
		<br> <br> <br> <br>
		<h3>Applied Requests by Students</h3>
		<table>
			<tr>
			<th>Request Id</th>
				<th>Roll no</th>
				<th>Type of Request</th>
				<th colspan="3">Reason</th>
				<th colspan="2">Requested Date</th>
				<th>Approve</th>
				<th>Reject</th>
			</tr>

			<c:forEach items="${requests}" var="request">
				<tr>
					<td><c:out value="${request.id}" /></td>
					<td><c:out value="${request.student.rollNo}" /></td>
					<td><c:out value="${request.requestType}" /></td>
					<td colspan="2"><c:out value="${request.reason}" /></td>

					<td colspan="2"><c:set var="dat"
							value="${request.requestedDate }" /> <c:out
							value="${dat.date}/${dat.month}/${dat.year} ${dat.hours}:${dat.minutes}  " />
					</td>
					
						<td><a href="<c:out value=" move?requestId=${request.id }&from=4" />" >Process It</a>
					</td>
						<td><a href="<c:out value="reject?requestId=${request.id }&from=4" />" >Reject It</a>
					</td>
				</tr>
			</c:forEach>
		</table>

	</center>

</body>
</html>