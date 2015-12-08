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
        <button class="btn down" Type="BUTTON" Value="Back" Onclick="window.location.href='/checkout'">
            Back
        </button>
    </div>
</div>

<div class="container">
    <h2>Select Address</h2>

    <p></p>
    <select name="address" required form="addressanddate" class="form-control">
        <c:forEach var="address" items="${addresslist}">
            <option value="${address.getId()}">${address.getCountry()}, ${address.getCity()}, ${address.getStreet()}, ${address.getHouse()}, ${address.getFlat()}</option>
        </c:forEach>
    </select>

    <p></p>

    <h2>Select Delivery Date</h2>

    <form action="/createorder" id="addressanddate">
        Date:<br>
        <input type="text" name="date" class="form-control">
        <br>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
