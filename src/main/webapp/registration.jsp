<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h2>Registration</h2>
<%if(request.getAttribute("error")!=null){%>
<%=request.getAttribute("error")%>
<%}%>
<p></p>
<form action="/registration">
  Name:<br>
  <input type="text" name="name">
  <br>
  Surname:<br>
  <input type="text" name="surname">
  <br>
  BirthDate:<br>
  <input type="text" name="birthdate">
  <br>
  Email:<br>
  <input type="text" name="email">
  <br>
  Password:<br>
  <input type="text" name="password">
  <br><br>
  <input type="submit" value="Register">
</form>
</body>
</html>
