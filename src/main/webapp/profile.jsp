<%@ page import="myApp.entity.PersonsEntity" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27.11.15
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<p>Hello, <%=((PersonsEntity)request.getAttribute("person")).getName()%></p>
<p></p>
<p><a href="/edituserinfo">Edit user info</a> </p>
</body>
</html>
