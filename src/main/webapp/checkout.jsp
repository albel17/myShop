<%@ page import="java.util.ArrayList" %>
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
        <div class="navbar-right navbar-form btn-group">
            <button class="btn btn-success" Type="BUTTON" Value="Logout" Onclick="window.location.href='/logout'">
                Logout
            </button>
        </div>
    </div>
</div>
<div class="jumbotron">
    <div class="container">
        <button class="btn down" Type="BUTTON" Value="Back" Onclick="window.location.href='/profile'">
            Back
        </button>
    </div>
</div>

<div class="container">
    <h2>Your order</h2>
    <c:forEach var="item" items="${cartItems}">
        <div class="list-group">
            <a class="list-group-item"
               href="/productDescription?id=${item.getProductId()}">${item.getProductName()}</a>
            <h4>${item.getAmount()}</h4>
        </div>
    </c:forEach>
    <p></p>

    <p>Total: ${sum}</p>
</div>
<p></p>

<div class="container">
    <form action="/checkoutcontinue">
        <h2>Delivery method</h2>
        <input type="radio" name="deliverymethod" value="self" checked>Customer Pickup
        <br>
        <input type="radio" name="deliverymethod" value="delivery">Delivery
        <p></p>

        <h2>Payment method</h2>
        <input type="radio" name="paymentmethod" value="cash" checked>Cash
        <br>
        <input type="radio" name="paymentmethod" value="card">Non-cash
        <br><br>
        <input type="submit" value="Continue">
    </form>
</div>
</body>
</html>
