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
        <button class="btn down" Type="BUTTON" Value="Back" Onclick="window.location.href='/myshop/profile/checkout'">
            Back
        </button>
    </div>
</div>

<div class="container">
    <%if (request.getAttribute("paymentmethod").equals("card")) {%>
    <form role="form" method="post">
        <h2>Card number:</h2><br><br>

        <div class="form-group">
            <input type="text" class="form-control" id="cardid"/>
        </div><br><br>
        <h2>CIV:</h2><br><br>

        <div class="form-group">
            <input type="text" class="form-control" id="civ"/>
        </div><br><br>
    </form>
    <%}%>
    <%if (request.getAttribute("deliverymethod").equals("delivery")) {%>
    <h2>Select Address</h2>

    <p></p>
    <select name="address" required form="addressanddate" class="form-control">
        <c:forEach var="address" items="${addresslist}">
            <option value="${address.id}">${address.country}, ${address.city}, ${address.street},
                    ${address.house}, ${address.flat}</option>
        </c:forEach>
    </select>

    <p></p>

    <form action="/myshop/profile/createorder" id="addressanddate" method="post">
        <input type="hidden" name="paymentmethod" value="${paymentmethod}" class="form-control">
        <input type="hidden" name="deliverymethod" value="${deliverymethod}" class="form-control">
        <input type="hidden" name="deliverydate" value="${deliverydate}" class="form-control">
        <input type="submit" value="Continue">
    </form>
    <%} else {%>
    <div class="form-control">Our address is ULITSA PUSHKINA, DOM KOLOTUSHKINA.</div>
    <br><br>

    <form action="/myshop/profile/createorder" id="addressanddate" method="post">
        <input type="hidden" name="paymentmethod" value="${paymentmethod}" class="form-control">
        <input type="hidden" name="deliverymethod" value="${deliverymethod}" class="form-control">
        <input type="hidden" name="deliverydate" value="${deliverydate}" class="form-control">
        <input type="hidden" name="address" value="-1" class="form-control">
        <input type="submit" value="Continue">
    </form>
    <%}%>
</div>
</body>
</html>
