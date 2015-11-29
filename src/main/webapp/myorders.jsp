<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:forEach var="order" items="${orderslist}">
  <p>${order.getId()}, ${order.getOrderStatus()}, ${order.getCost()}</p>
</c:forEach>
</body>
</html>
