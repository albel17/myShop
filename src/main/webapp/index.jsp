<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Welcome to my shop!</h2>
<% if(session.getAttribute("userID")==null) {%>
<p><a href="registration.jsp">Registration</a> </p>
<form action="/login">
  Login:<br>
  <input type="text" name="login">
  <br>
  Password:<br>
  <input type="text" name="password">
  <br><br>
  <input type="submit" value="Log In">
</form>
<%} %>
<p></p>
<c:forEach var="category" items="${categories}">
  <p>  <a href="/products?id=${category.getId()}">${category.getName()}</a> </p>
</c:forEach>
</body>
</html>
