<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Welcome to my shop!</h2>
<c:forEach var="category" items="${categories}">
  <p>  <a href="/products?id=${category.getId()}">${category.getName()}</a> </p>
</c:forEach>

</body>
</html>
