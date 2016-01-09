<%@ page import="myApp.bin.Cart" %>
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
    <div class="container">
        <button class="btn down" Type="BUTTON" Value="Back" Onclick="window.location.href='/myshop'">
            Back
        </button>
    </div>
</div>

<div class="container">
    <h2>Filters</h2>

    <div id="spoiler" style="display:none">


        <form:form modelAttribute="filterForm" action="/myshop/filter">
            <b>Max Price:</b><br><br>

            <div class="form-group">
                <form:input type="number" class="form-control" path="maxPrice"/>
            </div>
            <b>Min Price:</b><br><br>

            <div class="form-group">
                <form:input type="number" class="form-control" path="minPrice"/>
                <form:input type="hidden" class="form-control" path="categoryId"/>
            </div>
            <b>Name:</b><br><br>

            <div class="form-group">
                <form:input type="text" class="form-control" path="name"/>
            </div>

            <c:forEach var="attributeAndValue" items="${filterForm.attributesAndValues}" varStatus="status">
                <b>${attributeAndValue.attribute.name}:</b><br><br>
                <div class="list-group">
                    <form:input type="text" class="form-control" path="attributesAndValues[${status.index}].value"/>
                </div>
            </c:forEach>
            <input type="submit" value="Apply">

        </form:form>
    </div>
    <button title="Click to show/hide content" type="button" onclick="if(document.getElementById('spoiler')
    .style.display=='none') {document.getElementById('spoiler') .style.display=''}else
    {document.getElementById('spoiler') .style.display='none'}">Show/hide
    </button>
</div>

<div class="container">
    <h2>Products:</h2>
    <c:forEach var="product" items="${products}">
        <div class="list-group">
            <a href="/myshop/productdescription?id=${product.id}" class="list-group-item">${product.name}</a>
        </div>
    </c:forEach>
</div>

<%Cart cart = (Cart)request.getAttribute("cart");
    if(!cart.ifEmpty()){%>
<div style="position:absolute;bottom: 5px;right: 5px;margin: 0;padding: 5px 3px;">
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
                <td><a href="/myshop/profile" class="btn btn-info" role="button">+</a> </td>
                <td><a href="/myshop/profile" class="btn btn-info" role="button">-</a> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%}%>
</body>
</html>
