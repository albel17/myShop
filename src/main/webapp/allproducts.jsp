<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:forEach var="category" items="${categories}">
  <p>  <a href="/editproducts?id=${category.getId()}">${category.getName()}</a> </p>
</c:forEach>
</body>
</html>
