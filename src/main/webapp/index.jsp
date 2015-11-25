<%@ page import="java.util.List" %>
<%@ page import="myApp.Test" %>
<%@ page import="myApp.DAO.CategoriesDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="myApp.CategoriesEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<% pageContext.setAttribute("pageCategory",request.getAttribute("categories")); %>
<h2>Welcome to my shop!</h2>
<c:forEach var="category" items="${categories}">
    <p>${category}</p>
</c:forEach>
</body>
</html>
