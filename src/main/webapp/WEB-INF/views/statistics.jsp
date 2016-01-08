<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page isELIgnored="false" %>
<html>
<head>
  <title></title>
  <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
  <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
  <script src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>

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
    <div class="navbar-right navbar-form btn-group">
      <button class="btn btn-success" Type="BUTTON" Value="Logout"
              Onclick="window.location.href='/myshop/static/spring_logout'">
        Logout
      </button>
    </div>
  </div>
</div>
<div class="jumbotron">
  <div class="container">
    <button class="btn down" Type="BUTTON" Value="Back" Onclick="window.location.href='/myshop/admin'">
      Back
    </button>
  </div>
</div>

<div class="container">
  <h2>TOP 10 CLIENTS</h2>
  <c:forEach var="client" items="${topClients}">
    <div class="list-group">
      <p class="list-group-item">${client.personsEntity.email}, ${client.money}</p>
    </div>
  </c:forEach>
</div><br>

<div class="container">
  <h2>TOP 10 PRODUCTS</h2>
  <c:forEach var="product" items="${topProducts}">
    <div class="list-group">
      <p class="list-group-item">${product.productsEntity.name}, ${product.productsEntity.currentPrice}, ${product.money}</p>
    </div>
  </c:forEach>
</div>

<div class="container">
  <h2>THIS MONTH MONEY</h2>
    <div class="list-group">
      <p class="list-group-item">${moneyForMonth}</p>
    </div>
</div>

<div class="container">
  <h2>THIS WEEK MONEY</h2>
  <div class="list-group">
    <p class="list-group-item">${moneyForWeek}</p>
  </div>
</div>
</body>
</html>
