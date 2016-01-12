<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <button class="btn down" Type="BUTTON" Value="Back" Onclick="window.location.href='/myshop/profile'">
            Back
        </button>
    </div>
</div>

<div class="container">
    <h2>Address list</h2>
    <%if((boolean)request.getAttribute("errors")){%>
    <div class="text-danger">Can't delete address with orders</div>
    <%}%>
    <c:forEach var="address" items="${addresslist}">
        <div class="list-group">
            <p class="list-group-item">${address.country}, ${address.city}, ${address.street}, ${address.house}, ${address.flat}.
                Postal code:${address.postalCode}</p>
            <button class="btn btn-warning" Onclick="window.location.href='/myshop/profile/deleteaddress?id=${address.id}'">delete
            </button>
        </div>
    </c:forEach>
</div>
<p></p>

<div class="container">
    <h2>New address</h2>

    <form:form action="/myshop/profile/addaddress" modelAttribute="newaddress">
        <b>Country:</b><br><br>

        <div class="form-group">
            <form:input type="text" name="country" class="form-control" path="country"/>
            <form:errors path="country" cssClass="text-danger"/>
        </div>

        <b>City:</b><br><br>

        <div class="form-group">
            <form:input type="text" name="city" class="form-control" path="city"/>
            <form:errors path="city" cssClass="text-danger"/>
        </div>

        <b>Postal Code:</b><br><br>

        <div class="form-group">
            <form:input type="text" name="postalcode" class="form-control" path="postalCode"/>
            <form:errors path="postalCode" cssClass="text-danger"/>
        </div>

        <b>Street:</b><br><br>

        <div class="form-group">
            <form:input type="text" name="street" class="form-control" path="street"/>
            <form:errors path="street" cssClass="text-danger"/>
        </div>

        <b>House:</b><br><br>

        <div class="form-group">
            <form:input type="text" name="house" class="form-control" path="house"/>
            <form:errors path="house" cssClass="text-danger"/>
        </div>

        <b>Flat:</b><br><br>

        <div class="form-group">
            <form:input type="text" name="flat" class="form-control" path="flat"/>
            <form:errors path="flat" cssClass="text-danger"/>
        </div>
        <input type="submit" value="Add">
    </form:form>
</div>
</body>
</html>
