<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:forEach var="order" items="${orderslist}">
  <p>${order.getId()}, ${order.getOrderStatus()}, ${order.getCost()}<form action="/editorderstatus"><select name="status" required>
    <option value="created">Created</option>
    <option value="closed">Closed</option>
  </select><input type="hidden" name="orderid" value="${order.getId()}"><input type="submit" value="Edit"></form></p>
  <c:forEach var="orderitem" items="${order.getOrderItemsById()}">
    <p>----${orderitem.getProductsByProductId().getName()}, ${orderitem.getAmount()}, ${orderitem.getPrice()}</p>
  </c:forEach>
</c:forEach>
</body>
</html>
