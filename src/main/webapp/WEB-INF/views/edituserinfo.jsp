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
    <form:form role="form" modelAttribute="person" action="/myshop/profile/submituserchange">
        <b>Name:</b><br><br>

        <div class="form-group">
            <form:input type="text" class="form-control" name="name" path="name"/>
        </div>
        <b>Surname:</b><br><br>

        <div class="form-group">
            <form:input type="text" class="form-control" name="surname" path="surname"/>
        </div>
        <b>BirthDate:</b><br><br>

        <div class="form-group">
            <form:input type="date" class="form-control" name="birthdate" path="birthdate"/>
        </div>
        <b>Email:</b><br><br>

        <div class="form-group">
            <form:input type="text" class="form-control" name="email" path="email"/>
        </div>
        <!--
        <b>New password:</b><br><br>

        <div class="form-group">
            <input type="text" class="form-control" name="newpassword">
        </div>
        -->
        <b>Password:</b><br><br>

        <div class="form-group">
            <form:input type="text" class="form-control" value="" name="password" path="password"/>
        </div>
        <button type="submit">Edit</button>
    </form:form>
</div>
</body>
</html>
