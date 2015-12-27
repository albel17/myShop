<%@ page import="myApp.entity.PersonsEntity" %>
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
        <button class="btn down" Type="BUTTON" Value="Back" Onclick="window.location.href='/profile'">
            Back
        </button>
    </div>
</div>

<div class="container">
    <form role="form" action="/submituserchange">
        <b>Name:</b><br><br>
        <div class="form-group">
            <input type="text" class="form-control" name="name" value="${person.getName()}">
        </div>
        <b>Surname:</b><br><br>
        <div class="form-group">
            <input type="text" class="form-control"  name="surname" value="${person.getSurname()}">
        </div>
        <b>BirthDate:</b><br><br>
        <div class="form-group">
            <input type="text" class="form-control"  name="birthdate" value="${person.getBirthdate()}">
        </div>
        <b>Email:</b><br><br>
        <div class="form-group">
            <input type="text" class="form-control"  name="email" value="${person.getEmail()}">
        </div>
        <b>New password:</b><br><br>
        <div class="form-group">
            <input type="text" class="form-control"  name="newpassword">
        </div>
        <b>Password:</b><br><br>
        <div class="form-group">
            <input type="text" class="form-control"  name="password">
        </div>
        <button type="submit">Edit</button>
    </form>
</div>

<!--
<form action="/submituserchange">
    Name:<br>
    <input type="text" name="name" value="${person.getName()}">
    <br>
    Surname:<br>
    <input type="text" name="surname" value="${person.getSurname()}">
    <br>
    BirthDate:<br>
    <input type="text" name="birthdate" value="${person.getBirthdate()}">
    <br>
    Email:<br>
    <input type="text" name="email" value="${person.getEmail()}">
    <br>
    New password:<br>
    <input type="text" name="newpassword">
    <br>
    Password:<br>
    <input type="text" name="password">
    <br><br>
    <input type="submit" value="Edit">
</form>
-->
</body>
</html>
