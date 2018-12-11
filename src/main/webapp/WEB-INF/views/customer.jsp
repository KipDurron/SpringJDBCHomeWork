

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html xmlns:th="http://www.thymeleaf.org">
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Customer:${nameCustomer} </h2>
<br/>
<table>
    <tr>
        <th>id</th>
        <th>Account number</th>
        <th>Currency</th>
        <th>Amount</th>
        <th></th>
    </tr>
    <c:forEach var="listValue" items="${accountList}">
        <tr>
            <th>${listValue.getId()}</th>
            <th>${listValue.getAccount_number()}</th>
            <th>${listValue.getCurrency()}</th>
            <th>${listValue.getAmount()}</th>
            <th>

            </th>
        </tr>
    </c:forEach>
</table>

<br/>

<html>
<head>
</head>
<body>
<h3>Transaction panel</h3>
<form:form method="POST"
           action="sendTransaction" modelAttribute="transaction">
    <table>
        <tr>
            <td><form:label path="accountFrom">Id of account from</form:label></td>
            <td><form:input path="accountFrom"/></td>
        </tr>
        <tr>
            <td><form:label path="accountTo">Id of account to</form:label></td>
            <td><form:input path="accountTo"/></td>
        </tr>
        <tr>
            <td><form:label path="currency">
                currency(Example: RUB)</form:label></td>
            <td><form:input path="currency"/></td>
        </tr>
        <tr>
            <td><form:label path="amount">
                Amount</form:label></td>
            <td><form:input path="amount"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>

<%--<form method = "POST" action = "sendTransaction" th:object="${transaction}">--%>

    <%--<select name="idCustomerFrom" th:field="*{accountFrom}">--%>
        <%--<option value="">Choose your account</option>--%>
        <%--<c:forEach var="accountFrom" items="${accountList}">--%>
            <%--<option value="${accountFrom.getId()}">account ${accountFrom.getId()}</option>--%>
        <%--</c:forEach>--%>
    <%--</select>--%>

    <%--<select name="idCustomerTo" th:field="*{accountTo}">--%>
        <%--<option value="">Choose account of recipient</option>--%>
        <%--<c:forEach var="accountTo" items="${accountTos}">--%>
            <%--<option value="${accountTo.getId()}">account ${accountTo.getId()}</option>--%>
        <%--</c:forEach>--%>
    <%--</select>--%>
    <%--&lt;%&ndash;<ul>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<form:select path="currencyTo" items="${allCurrency}"/>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</ul>&ndash;%&gt;--%>
    <%--<select name="currencyTo" th:field="*{currency}">--%>
        <%--<option value="">Choose Currency</option>--%>
        <%--<c:forEach var="currency" items="${allCurrency}">--%>
            <%--<option value="${currency}">${currency}</option>--%>
        <%--</c:forEach>--%>
    <%--</select>--%>
    <%--<input name = "amount" placeholder="Amount" th:field="*{amount}"/>--%>
    <%--<input type="submit" value="Send transaction"/>--%>
<%--</form>--%>



</body>
</html>
