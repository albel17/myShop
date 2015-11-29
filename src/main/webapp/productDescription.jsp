<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h2>Products:</h2>
<c:forEach var="info" items="${productInfo}">
  <p>${info}</p>
</c:forEach>
<p></p>
<a href="/addtocart?id=${product.getId()}">Add to the Cart</a>
</body>
</html>
