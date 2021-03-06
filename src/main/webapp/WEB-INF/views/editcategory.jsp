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
        <button class="btn down" Type="BUTTON" Value="Back"
                Onclick="window.location.href='/myshop/admin/allcategories'">
            Back
        </button>
    </div>
</div>

<div class="container">
    <c:forEach var="attribute" items="${attributes}">
        <div class="list-group">
            <p class="list-group-item">${attribute.name}</p>
            <button class="btn btn-warning"
                    Onclick="window.location.href='/myshop/admin/removeattribute?id=${attribute.id}'">delete
            </button>
        </div>
    </c:forEach>
</div>

<div class="container">
    <h2>New Attribute</h2>
    <%if ((boolean) request.getAttribute("isEmpty")) {%>
    <div class="text-danger">Name and description must not be empty.</div>
    <%}%>
    <form action="/myshop/admin/createattribute">
        <b>Name:</b><br><br>

        <div class="form-group">
            <input type="text" name="name" class="form-control">
        </div>

        <b>Description:</b><br><br>

        <div class="form-group">
            <input type="text" name="description" class="form-control">
        </div>
        <input type="hidden" name="categoryId" value="${param["id"]}">
        <br>
        <input type="submit" value="Create" class="btn">
    </form>
</div>
</body>
</html>
