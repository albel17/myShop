<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <button class="btn down" Type="BUTTON" Value="Back" Onclick="window.location.href='/myshop/admin/allproducts'">
            Back
        </button>
    </div>
</div>

<div class="container">
    <c:forEach var="product" items="${products}">
        <div class="list-group">
            <a href="/myshop/admin/editproduct?id=${product.id}" class="list-group-item">${product.name}</a>
            <button class="btn btn-warning" Onclick="window.location.href='/myshop/admin/removeproduct?id=${product.id}'">
                delete
            </button>
        </div>
    </c:forEach>
</div>

<div class="container">
    <h2>New Product</h2>

    <form:form action="/myshop/admin/addproduct" modelAttribute="newProduct">
        <b>Name:</b><br><br>

        <div class="form-group">
            <form:input type="text" name="name" class="form-control" path="name"/>
        </div>

        <b>Current price:</b><br><br>

        <div class="form-group">
            <form:input type="number" name="currentprice" class="form-control" path="currentPrice"/>
        </div>

        <b>Size:</b><br><br>

        <div class="form-group">
            <form:input type="number" name="size" class="form-control" path="size"/>
        </div>

        <b>Weight:</b><br><br>

        <div class="form-group">
            <form:input type="number" name="weight" class="form-control" path="weight"/>
        </div>

        <b>Description:</b><br><br>

        <div class="form-group">
            <form:input type="text" name="description" class="form-control" path="description"/>
        </div>
        <input type="hidden" name="categoryId" value="${param["id"]}">
        <c:forEach var="attribute" items="${attributes}" varStatus="status">
            <b>${attribute.name}:</b><br><br>

            <div class="form-group">
                <form:input type="text" name="${attribute.id}" class="form-control" path="newAttributes[${status.index}]"/>
            </div>
        </c:forEach>
        <b>Amount:</b><br><br>

        <div class="form-group">
            <input type="number" name="amount" class="form-control" value="0"/>
        </div><br><br>
        <input type="submit" value="Add">
    </form:form>
</div>
</body>
</html>
