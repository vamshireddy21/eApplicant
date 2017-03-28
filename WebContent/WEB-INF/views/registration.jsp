<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Registration</title>
<style>
.formclass {
    margin:10px auto;
    max-width: 400px;
    padding: 20px 12px 10px 20px;
    font: 13px "Lucida Sans Unicode", "Lucida Grande", sans-serif;
}
.formclass li {
    padding: 0;
    display: block;
    list-style: none;
    margin: 10px 0 0 0;
}
.formclass label{
    padding:0px;
    left:0%;
    text-align: left;
}
.formclass input[type=text], 
.formclass input[type=date],
.formclass input[type=datetime],
.formclass input[type=number],
.formclass input[type=search],
.formclass input[type=time],
.formclass input[type=url],
.formclass input[type=email],
.formclass input[type=password],
textarea, 
select{
    box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    border:1px solid #BEBEBE;
    padding: 7px;
    margin:0px;
    -webkit-transition: all 0.30s ease-in-out;
    -moz-transition: all 0.30s ease-in-out;
    -ms-transition: all 0.30s ease-in-out;
    -o-transition: all 0.30s ease-in-out;
    outline: none;  
}
.formclass input[type=text]:focus, 
.formclass input[type=date]:focus,
.formclass input[type=datetime]:focus,
.formclass input[type=number]:focus,
.formclass input[type=search]:focus,
.formclass input[type=time]:focus,
.formclass input[type=url]:focus,
.formclass input[type=email]:focus,
.formclass textarea:focus, 
.formclass select:focus{
    -moz-box-shadow: 0 0 8px #88D5E9;
    -webkit-box-shadow: 0 0 8px #88D5E9;
    box-shadow: 0 0 8px #88D5E9;
    border: 1px solid #88D5E9;
}
.formclass .field-divided{
    width: 49%;
}

.formclass .field-long{
    width: 100%;
}
.formclass .field-select{
    width: 100%;
}
.formclass .field-textarea{
    height: 100px;
}
.formclass input[type=submit], .formclass input[type=button]{
    background: #4B99AD;
    padding: 8px 15px 8px 15px;
    border: none;
    color: #fff;
}
.formclass input[type=submit]:hover, .formclass input[type=button]:hover{
    background: #4691A4;
    box-shadow:none;
    -moz-box-shadow:none;
    -webkit-box-shadow:none;
}
.formclass .required{
    color:red;
}
h1,h2,h3,h4,h5,h6 { color: #7c795d; font-family: 'Trocchi', serif;  font-weight: normal; line-height: 48px; margin: 0; }
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
<a href="welcome">Home</a> 
</nav>
 <h1>Student Registration Page</h1>
 <h3>${errorMessage}</h3>
 </center>
 <form action="registration" method="get" align="center">
<ul class="formclass">
    <li><input name="name" type="text" name="field1" class="field-divided" placeholder="Name" />
    </li>
     <li><input name="rollNo" type="text" name="field2" class="field-divided" placeholder="Roll No" />
     </li>
    <li>
        <input type="email" name="emailId" class="field-divided" placeholder="Mail Id" />
    </li>
    <li>
        <span ><label>Course</label> </span>
        </li>
        <li>
        <select name="course" class="field-divided">
        <option value="B.Tech">B.Tech</option>
        <option value="M.Tech">M.Tech</option>
        <option value="MSC">MSC</option>
        <option value="MCA">MCA</option>    
        </select>
            <!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
       
    </li>
    <li>
        <input type="text" name="department" class="field-divided" placeholder="Department" />
    </li>
     <li>
        <span><label>Year</label></span>
        </li>
        <li>
        <select name="year" class="field-divided">
        <option value="1">First</option>
        <option value="2">Second</option>
        <option value="3">Third</option>
        <option value="4">Fourth</option>
        <option value="5">Fifth</option>
        <option value="6">Sixth</option>
        </select>
        <!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
        
    </li>
     <li>
        <span ><label >Semister</label> </span>
        </li>
        <li>
        <select name="semister" class="field-divided" >
        <option value="1">First</option>
        <option value="2">Second</option>
        </select>
        <!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
       
    </li>
     <li>
        <input type="text" name="userName" class="field-divided" placeholder="User Name" />
    </li>
     <li>
        <input type="password" name="password" class="field-divided" placeholder="Password" />
    </li>
    <li>
        <input  type="password" name="cpassword" class="field-divided" placeholder="Confirm Password" />
    </li>
    <li>
        <input type="submit" value="Submit" />
    </li>
</ul>
</form>

</body>
</html>