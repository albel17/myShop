<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26.11.15
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
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
<a href="/addToBin?id=${product.getId()}">Add to the Bin</a>
<p></p>
<p><%=session.getAttribute("bin")%></p>
</body>
</html>
