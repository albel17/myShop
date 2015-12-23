<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
  <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">My Shop</a>
    </div>
      <% if(session.getAttribute("userID")==null) {%>
      <form class="navbar-form navbar-right" role="form" action="/login" method="post">
        <div class="form-group">
          <input type="text" placeholder="Email" class="form-control" name="login">
        </div>
        <div class="form-group">
          <input type="password" placeholder="Password" class="form-control" name="password">
        </div>
        <button type="submit" class="btn btn-success">Sign in</button>
        <FORM>
          <INPUT class="btn btn-success" Type="BUTTON" Value="Registration" Onclick="window.location.href='../../registration.jsp'">
        </FORM>
      </form>
      <%} else {%>
      <div class="navbar-right navbar-form btn-group">
        <button class="btn btn-success" Type="BUTTON" Value="Profile" Onclick="window.location.href='/profile'">Profile</button>
        <button class="btn btn-success" Type="BUTTON" Value="Logout" Onclick="window.location.href='/logout'">Logout</button>
      </div>
      <%}%>
  </div>
</div>
<div class="jumbotron">
</div>


<div class="container">
  <!-- Example row of columns -->
<c:forEach var="category" items="${categories}">
  <div class="list-group">
    <a href="/myshop/products?id=${category.id}" class="list-group-item">${category.getName()}</a>
  </div>
</c:forEach>

  <hr>

  <footer>
    <p>&copy; Company 2015</p>
  </footer>
</div> <!-- /container -->
</body>
</html>
