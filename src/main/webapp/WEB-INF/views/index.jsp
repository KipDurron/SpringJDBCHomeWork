<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<html>
<body>
<h2>All customers</h2>

<c:if test="${not empty customerList}">

    <ul>
        <c:forEach var="listValue" items="${customerList}">
            <li><a href="customer/${listValue.getId()}">${listValue.getName()}</a> </li>
        </c:forEach>
    </ul>



</c:if>
</body>
</html>
