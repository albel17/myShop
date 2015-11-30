<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:forEach var="product" items="${products}">
  <p>  <a href="/editproduct?id=${product.getId()}">${product.getName()}</a> </p>
</c:forEach>
<p></p>
<form action="/addproduct">
    Name:<br>
    <input type="text" name="name">
    <br>
    Current price:<br>
    <input type="text" name="currentprice">
    <br>
    Size:<br>
    <input type="text" name="size">
    <br>
    weight:<br>
    <input type="text" name="weight">
    <br>
    Description:<br>
    <input type="text" name="description">
    <input type="hidden" name="categoryId" value="${param["id"]}">
    <c:forEach var="attribute" items="${attributes}">
        <br>
        ${attribute.getName()}:<br>
        <input type="text" name="${attribute.getId()}">
    </c:forEach>
    <br><br>
    <input type="submit" value="Add">
    </form>
</body>
</html>
