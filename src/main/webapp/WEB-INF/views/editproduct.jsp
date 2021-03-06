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
            <button class="btn btn-success" Type="BUTTON" Value="Logout"
                    Onclick="window.location.href='/myshop/static/spring_logout'">
                Logout
            </button>
        </div>
    </div>
</div>
<div class="jumbotron">
    <div class="container">
        <button class="btn down" Type="BUTTON" Value="Back" Onclick="window.location.href='/myshop/admin'">
            Back
        </button>
    </div>
</div>

<div class="container">
    <form action="/myshop/admin/submitproductchange">
        <b>Name:</b><br><br>

        <div class="form-group">
            <input type="text" name="name" value="${product.name}" class="form-control">
        </div>

        <b>Current Price:</b><br><br>

        <div class="form-group">
            <input type="number" name="currentprice" value="${product.currentPrice}" class="form-control">
        </div>

        <b>Size:</b><br><br>

        <div class="form-group">
            <input type="number" name="size" value="${product.size}" class="form-control">
        </div>

        <b>Weight:</b><br><br>

        <div class="form-group">
            <input type="number" name="weight" value="${product.weight}" class="form-control">
        </div>

        <b>Description:</b><br><br>

        <div class="form-group">
            <input type="text" name="description" value="${product.description}" class="form-control">
        </div>

        <b>Amount:</b><br><br>

        <div class="form-group">
            <input type="text" name="amount" value="${amount}" class="form-control">
        </div>
        <input type="hidden" name="productId" value="${param["id"]}">
        <c:forEach var="attribute" items="${attributes}" varStatus="theCount">
            <br>
            <b>${attribute.name}:</b><br><br>

            <div class="form-group">
                <input type="text" name="${attribute.id}" value="${values.get(theCount.index)}" class="form-control">
            </div>
        </c:forEach>
        <br><br>
        <input type="submit" value="Edit">
    </form>
</div>
</body>
</html>
