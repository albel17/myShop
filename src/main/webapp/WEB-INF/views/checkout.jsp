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
    <h2>Your order</h2>
    <c:forEach var="item" items="${cartItems}">
        <div class="list-group">
            <a class="list-group-item"
               href="/myshop/productdescription?id=${item.id}">${item.productName}</a>
            <h4>${item.amount}</h4>
        </div>
    </c:forEach>
    <p></p>

    <p>Total: ${sum}</p>
    <%if(!request.getAttribute("amountErrorString").equals("")){%>
    <p><div class="text-danger">${amountErrorString}</div></p>
    <%}%>
</div>
<p></p>

<div class="container">
    <form action="/myshop/profile/checkoutcontinue" method="post">
        <h2>Delivery method</h2>
        <input type="radio" name="deliverymethod" value="self" checked>Customer Pickup
        <br>
        <input type="radio" name="deliverymethod" value="delivery">Delivery
        <p></p>

        <h2>Payment method</h2>
        <input type="radio" name="paymentmethod" value="cash" checked>Cash
        <br>
        <input type="radio" name="paymentmethod" value="card">Non-cash
        <p></p>

        <h2>Delivery date</h2>
        <input type="date" name="deliverydate" required/>
        <%if(!(boolean)request.getAttribute("isFuture")){%>
        <div class="text-danger">Enter future date.</div>
        <%}%>
        <br><br>
        <input type="submit" value="Continue">
    </form>
</div>
</body>
</html>
