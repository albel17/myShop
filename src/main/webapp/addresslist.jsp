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
    <h2>Adress list</h2>
    <c:forEach var="address" items="${addresslist}">
        <div class="list-group">
            <p class="list-group-item">${address.getCountry()}, ${address.getCity()}, ${address.getStreet()}, ${address.getHouse()}, ${address.getFlat()}.
                Postal code:${address.getPostalCode()}</p>
            <button class="btn btn-warning" Onclick="window.location.href='/deleteaddress?id=${address.getId()}'">delete</button>
        </div>
    </c:forEach>
</div>
<p></p>

<div class="container">
    <h2>New address</h2>

    <form action="/addaddress">
        <b>Country:</b><br><br>

        <div class="form-group">
            <input type="text" name="country" class="form-control">
        </div>

        <b>City:</b><br><br>

        <div class="form-group">
            <input type="text" name="city" class="form-control">
        </div>

        <b>Postal Code:</b><br><br>

        <div class="form-group">
            <input type="text" name="postalcode" class="form-control">
        </div>

        <b>Street:</b><br><br>
        <div class="form-group">
        <input type="text" name="street" class="form-control">
            </div>

        <b>House:</b><br><br>
        <div class="form-group">
        <input type="text" name="house" class="form-control">
        </div>

        <b>Flat:</b><br><br>
        <div class="form-group">
        <input type="text" name="flat" class="form-control">
            </div>
        <input type="submit" value="Add">
    </form>
</div>
</body>
</html>
