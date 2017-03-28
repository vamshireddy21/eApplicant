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
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
}

table {
	border-collapse: collapse;
	border-spacing: 0;
}
</style>
</head>
<!--    1 - student 2 - Admin  3- IStaff 4 -HStaff 5 - VPStaff 6- PStaff 
-->
<body bgcolor="white">

	<h1>e Applicant System</h1>
	<form:form method="post" action="login" commandName="credentials">
		<h4>${errorMessage}</h4>
		<table>


			<tr>

				<td><form:label path="userName">User Name</form:label></td>
				<td><form:input path="userName" type="text" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:input path="password" type="password" /></td>
			</tr>
			<!--    1 - student 2 - Admin  3- IStaff 4 -HStaff 5 - VPStaff 6- PStaff -->
			<tr>
				<td><form:label path="userType">User Type</form:label></td>
				<td><form:select path="userType">
						<form:option value="1" selected='selected'>Student</form:option>
						<form:option value="2">Admin</form:option>
						<form:option value="3">Internal Staff</form:option>
						<form:option value="4">High Staff</form:option>
						<form:option value="5">Vice Principal</form:option>
						<form:option value="6">Principal</form:option>

					</form:select></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Login" /></td>
			</tr>
			<tr>
				<td colspan="2">Are you a Student and not a User Yet? <a
					href="signup">Sign up</a>
				</td>
			</tr>
		</table>

	</form:form>

</body>
</html>
