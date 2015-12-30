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
        <% if (session.getAttribute("userID") == null) {%>
        <form class="navbar-form navbar-right" role="form" action="/login" method="post">
            <div class="form-group">
                <input type="text" placeholder="Email" class="form-control" name="login">
            </div>
            <div class="form-group">
                <input type="password" placeholder="Password" class="form-control" name="password">
            </div>
            <button type="submit" class="btn btn-success">Sign in</button>
            <FORM>
                <INPUT class="btn btn-success" Type="BUTTON" Value="Registration"
                       Onclick="window.location.href='registration.jsp'">
            </FORM>
        </form>
        <%} else {%>
        <div class="navbar-right navbar-form btn-group">
            <button class="btn btn-success" Type="BUTTON" Value="Profile" Onclick="window.location.href='/profile'">
                Profile
            </button>
            <button class="btn btn-success" Type="BUTTON" Value="Logout" Onclick="window.location.href='/logout'">
                Logout
            </button>
        </div>
        <%}%>
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
    <form:form role="form" modelAttribute="userForm" action="/myshop/reg" method="post">
        <b>Name:</b><br><br>

        <div class="form-group">
            <form:input type="text" class="form-control" path="name"/>
        </div>
        <b>Surname:</b><br><br>

        <div class="form-group">
            <form:input type="text" class="form-control" path="surname"/>
        </div>
        <b>BirthDate:</b><br><br>

        <div class="form-group">
            <form:input type="text" class="form-control" path="birthdate"/>
        </div>
        <b>Email:</b><br><br>

        <div class="form-group">
            <form:input type="text" class="form-control" path="email"/>
        </div>
        <b>Password:</b><br><br>

        <div class="form-group">
            <form:input type="text" class="form-control" path="password"/>
        </div>
        <button type="submit">Register</button>
    </form:form>
</div>
</body>
</html>