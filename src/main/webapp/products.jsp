<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 25.11.15
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<body>
<h2>Products:</h2>
<c:forEach var="product" items="${products}">
  <p>  <a href="/productDescription?id=${product.getId()}">${product.getName()}</a> </p>
</c:forEach>
</body>
</body>
</html>
