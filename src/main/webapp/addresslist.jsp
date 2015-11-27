<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27.11.15
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h2>Adress list</h2>
<p></p>
<c:forEach var="address" items="${addresslist}">
  <p>${address.getCountry()}, ${address.getCity()}, ${address.getStreet()}, ${address.getHouse()}, ${address.getFlat()}. Postal code:${address.getPostalCode()}    <a href="/deleteaddress?id=${address.getId()}">delete</a></p>
</c:forEach>
<p></p>
<h2>New address</h2>
<form action="/addaddress">
  Country:<br>
  <input type="text" name="country">
  <br>
  City:<br>
  <input type="text" name="city">
  <br>
  Postal Code:<br>
  <input type="text" name="postalcode">
  <br>
  Street:<br>
  <input type="text" name="street">
  <br>
  House:<br>
  <input type="text" name="house">
  <br>
  Flat:<br>
  <input type="text" name="flat">
  <br><br>
  <input type="submit" value="Add">
</form>
</body>
</html>
