<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:forEach var="category" items="${categories}">
  <p><a href="/admin/editcategory?id=${category.getId()}">${category.getName()}</a>  <a href="/admin/removecategory?id=${category.getId()}">delete</a></p>
</c:forEach>
<p></p>
<form action="/admin/addcategory">
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