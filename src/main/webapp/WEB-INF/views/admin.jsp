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
            <button class="btn btn-success" Type="BUTTON" Value="Logout" Onclick="window.location.href='/myshop/static/spring_logout'">
                Logout
            </button>
        </div>
    </div>
</div>
<div class="jumbotron">
    <div class="container">
        <button class="btn down" Type="BUTTON" Value="Back" Onclick="window.location.href='/myshop'">
            Main
        </button>
    </div>
</div>

<div class="container">
    <h2>ADMIN</h2>

    <div class="list-group">
        <a href="/myshop/admin/allorders" class="list-group-item">Orders</a>
    </div>

    <div class="list-group">
        <a href="/myshop/admin/allcategories" class="list-group-item">Categories</a>
    </div>

    <div class="list-group">
        <a href="/myshop/admin/allproducts" class="list-group-item">Products</a>
    </div>

    <div class="list-group">
        <a href="/myshop/admin/statistics" class="list-group-item">Statistics</a>
    </div>
</div>
</body>
</html>
