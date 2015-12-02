<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<script src="js/bootstrap.min.js"></script>
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
        <% if (session.getAttribute("userID") == null) {%>
        <form class="navbar-form navbar-right" role="form" action="/login" method="post">
            <div class="form-group">
                <input type="text" placeholder="Email" class="form-control" name="login">
            </div>
            <div class="form-group">
                <input type="password" placeholder="Password" class="form-control" name="password">
            </div>
            <button type="submit" class="btn btn-success">Sign in</button>
            <FORM>
                <INPUT class="btn btn-success" Type="BUTTON" Value="Registration"
                       Onclick="window.location.href='registration.jsp'">
            </FORM>
        </form>
        <%} else {%>
        <div class="navbar-right navbar-form btn-group">
            <button class="btn btn-success" Type="BUTTON" Value="Profile" Onclick="window.location.href='/profile'">
                Profile
            </button>
            <button class="btn btn-success" Type="BUTTON" Value="Logout" Onclick="window.location.href='/logout'">
                Logout
            </button>
        </div>
        <%}%>
    </div>
</div>
<div class="jumbotron">
    <div class="container">
        <button class="btn down" Type="BUTTON" Value="Back" Onclick="window.location.href='/start'">
            Back
        </button>
    </div>
</div>

<div class="container">
    <h2>Products:</h2>
    <c:forEach var="product" items="${products}">
        <div class="list-group">
            <a href="/productDescription?id=${product.getId()}" class="list-group-item">${product.getName()}</a>
        </div>
    </c:forEach>
</div>
</body>
</html>
