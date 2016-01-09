<%@ page import="myApp.bin.Cart" %>
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
        <security:authorize access="isAnonymous()">
            <s:url var="authUrl" value="/static/spring_security_check"/>
            <form class="navbar-form navbar-right" role="form" action="${authUrl}" method="post">
                <div class="form-group">
                    <input type="text" placeholder="Email" class="form-control" name="username">
                </div>
                <div class="form-group">
                    <input type="password" placeholder="Password" class="form-control" name="password">
                </div>
                <button type="submit" class="btn btn-success">Sign in</button>
                <FORM>
                    <INPUT class="btn btn-success" Type="BUTTON" Value="Registration"
                           Onclick="window.location.href='/myshop/registration'">
                </FORM>
            </form>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <div class="navbar-right navbar-form btn-group">
                <button class="btn btn-success" Type="BUTTON" Value="Profile"
                        Onclick="window.location.href='/myshop/profile'">
                    Profile
                </button>
                <button class="btn btn-success" Type="BUTTON" Value="Logout"
                        Onclick="window.location.href='/myshop/static/spring_logout'">
                    Logout
                </button>
            </div>
        </security:authorize>
    </div>
</div>
<div class="jumbotron">
</div>


<div class="container">
    <!-- Example row of columns -->
    <c:forEach var="category" items="${categories}">
        <div class="list-group">
            <a href="/myshop/products?id=${category.id}" class="list-group-item">${category.name}</a>
        </div>
    </c:forEach>

    <hr>

    <footer>
        <p>&copy; Company 2015</p>
    </footer>
</div>
<%Cart cart = (Cart)request.getAttribute("cart");
    if(!cart.ifEmpty()){%>
<div style="position:absolute;bottom: 5px;right: 5px;margin: 0;padding: 5px 3px;" class="bg-success">
    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Amount</th>
            <th>Add</th>
            <th>Remove</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${cart.items}">
            <tr>
                <td>${item.product.name}</td>
                <td>${item.amount}</td>
                <td><a href="/myshop/addtocart?id=${item.product.id}&index=1" class="btn btn-info" role="button">+</a> </td>
                <td><a href="/myshop/removefromcart?id=${item.product.id}&index=1" class="btn btn-danger" role="button">-</a> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%}%>
</body>
</html>
