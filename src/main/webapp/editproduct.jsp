<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="/submitproductchange">
  Name:<br>
  <input type="text" name="name" value="${product.getName()}">
  <br>
  Current Price:<br>
  <input type="text" name="currentprice" value="${product.getCurrentPrice()}">
  <br>
  Size:<br>
  <input type="text" name="size" value="${product.getSize()}">
  <br>
  Weight:<br>
  <input type="text" name="weight" value="${product.getWeight()}">
  <br>
  Description:<br>
  <input type="text" name="description" value="${product.getDescription()}">
  <input type="hidden" name="productId" value="${param["id"]}">
  <c:forEach var="attribute" items="${attributes}" varStatus="theCount">
    <br>
    ${attribute.getName()}:<br>
    <input type="text" name="${attribute.getId()}" value="${values.get(theCount.index)}">
  </c:forEach>
  <br><br>
  <input type="submit" value="Edit">
</form>
</body>
</html>
