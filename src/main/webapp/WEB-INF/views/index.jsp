<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<html>
<body>
<h2>Spring MVC and List Example</h2>

<c:if test="${not empty customerList}">

    <ul>
        <c:forEach var="listValue" items="${customerList}">
            <li>${customerList}</li>
        </c:forEach>
    </ul>

</c:if>
</body>
</html>
