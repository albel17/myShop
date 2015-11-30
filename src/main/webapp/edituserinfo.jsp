<%@ page import="myApp.entity.PersonsEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
PersonsEntity person = ((PersonsEntity) request.getAttribute("person"));
%>

<form action="/submituserchange">
  Name:<br>
  <input type="text" name="name" value="${person.getName()}">
  <br>
  Surname:<br>
  <input type="text" name="surname" value="${person.getSurname()}">
  <br>
  BirthDate:<br>
  <input type="text" name="birthdate" value="${person.getBirthdate()}">
  <br>
  Email:<br>
  <input type="text" name="email" value="${person.getEmail()}">
  <br>
  New password:<br>
  <input type="text" name="newpassword">
  <br>
  Password:<br>
  <input type="text" name="password">
  <br><br>
  <input type="submit" value="Edit">
</form>
</body>
</html>
