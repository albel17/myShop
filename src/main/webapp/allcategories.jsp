<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:forEach var="category" items="${categories}">
  <p>${category.getName()}  <a href="/removecategory?id=${category.getId()}">delete</a></p>
</c:forEach>
<p></p>
<form action="/addcategory">
  Name:<br>
  <input type="text" name="name">
  <br>
  Description:<br>
  <input type="text" name="description">
  <br><br>
  <input type="submit" value="Add">
</form>
</body>
</html>