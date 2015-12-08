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
        <button class="btn down" Type="BUTTON" Value="Back" Onclick="window.location.href='/admin'">
            Main
        </button>
    </div>
</div>

<div class="container">
    <c:forEach var="category" items="${categories}">
        <div class="list-group">
            <a class="list-group-item" href="/admin/editcategory?id=${category.getId()}">${category.getName()}</a>
            <button class="btn btn-warning"
                    Onclick="window.location.href='/admin/removecategory?id=${category.getId()}'">delete
            </button>
        </div>
    </c:forEach>

    <h2>New Category</h2>

    <form action="/admin/addcategory">
        <b>Name:</b><br><br>

        <div class="form-group">
            <input type="text" name="name" class="form-control">
        </div>
        <b>Description:</b><br><br>

        <div class="form-group">
            <input type="text" name="description" class="form-control">
        </div>
        <input type="submit" value="Add" class="btn">
    </form>
</div>
</body>
</html>