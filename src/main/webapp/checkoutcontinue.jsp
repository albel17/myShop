<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h2>Select Address</h2>
<p></p>
<select name="address" required form="addressanddate">
    <c:forEach var="address" items="${addresslist}">
        <option value="${address.getId()}">${address.getCountry()}, ${address.getCity()}, ${address.getStreet()}, ${address.getHouse()}, ${address.getFlat()}</option>
    </c:forEach>
</select>
<p></p>
<h2>Select Delivery Date</h2>
<form action="/createorder" id="addressanddate">
    Date:<br>
    <input type="text" name="date">
    <br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
