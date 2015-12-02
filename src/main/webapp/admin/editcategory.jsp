<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:forEach var="attribute" items="${attributes}">
  <p>${attribute.getName()} <a href="/admin/removeattribute">delete</a></p>
</c:forEach>
<p></p>
<form action="/admin/createattribute">
  Name:<br>
  <input type="text" name="name">
  <br>
  Description:<br>
  <input type="text" name="description">
  <input type="hidden" name="categoryId" value="${param["id"]}">
  <br>
  <input type="submit" value="Create">
</form>
</body>
</html>
