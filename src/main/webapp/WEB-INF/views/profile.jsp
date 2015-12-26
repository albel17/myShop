<%@ page import="myApp.entity.PersonsEntity" %>
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
        <div class="navbar-right navbar-form btn-group">
            <button class="btn btn-success" Type="BUTTON" Value="Logout" Onclick="window.location.href='/logout'">
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
    <div class="list-group">
        <div class="list-group">
            <a href="/edituserinfo" class="list-group-item">Your info</a>
        </div>
        <div class="list-group">
            <a href="/addresslist" class="list-group-item">Adress list</a>
        </div>
        <div class="list-group">
            <a href="/checkout" class="list-group-item">Checkout</a>
        </div>
        <div class="list-group">
            <a href="/myorders" class="list-group-item">Orders</a>
        </div>
    </div>
</div>
</body>
</html>
