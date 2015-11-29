<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h2>Your order</h2>
<c:forEach var="item" items="${cartItems}">
  <p><a href="/productDescription?id=${item.getProductId()}">${item.getProductName()}</a> ${item.getAmount()}</p>
</c:forEach>
<p></p>
<p>Total: ${sum}</p>
<p></p>
<form action="/checkoutcontinue">
<h2>Delivery method</h2>
  <input type="radio" name="deliverymethod" value="self" checked>Customer Pickup
  <br>
  <input type="radio" name="deliverymethod" value="delivery">Delivery
<p></p>
<h2>Payment method</h2>
  <input type="radio" name="paymentmethod" value="cash" checked>Cash
  <br>
  <input type="radio" name="paymentmethod" value="card">Non-cash
<br><br>
<input type="submit" value="Continue">
</form>
</body>
</html>
