<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<script src="../js/bootstrap.min.js"></script>
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
        <button class="btn down" Type="BUTTON" Value="Back" Onclick="window.location.href='/admin/allproducts'">
            Back
        </button>
    </div>
</div>

<div class="container">
    <form action="/admin/submitproductchange">
        <b>Name:</b><br><br>

        <div class="form-group">
            <input type="text" name="name" value="${product.getName()}" class="form-control">
        </div>

        <b>Current Price:</b><br><br>

        <div class="form-group">
            <input type="text" name="currentprice" value="${product.getCurrentPrice()}" class="form-control">
        </div>

        <b>Size:</b><br><br>

        <div class="form-group">
            <input type="text" name="size" value="${product.getSize()}" class="form-control">
        </div>

        <b>Weight:</b><br><br>

        <div class="form-group">
            <input type="text" name="weight" value="${product.getWeight()}" class="form-control">
        </div>

        <b>Description:</b><br><br>

        <div class="form-group">
            <input type="text" name="description" value="${product.getDescription()}" class="form-control">
        </div>
        <input type="hidden" name="productId" value="${param["id"]}">
        <c:forEach var="attribute" items="${attributes}" varStatus="theCount">
            <br>
            <b>${attribute.getName()}:</b><br><br>

            <div class="form-group">
                <input type="text" name="${attribute.getId()}" value="${values.get(theCount.index)}" class="form-control">
            </div>
        </c:forEach>
        <br><br>
        <input type="submit" value="Edit">
    </form>
</div>
</body>
</html>
