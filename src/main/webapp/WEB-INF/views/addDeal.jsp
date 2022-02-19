<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<body>
<div>
    <div>
        <h1>Add Bloomberg Deal</h1>
    </div>
    <c:if test="${not empty errorMsg}">
        <span style="color: red">
            <c:out value="${errorMsg}"/>
        </span>
    </c:if>
<table>
    <form:form action="/" method="post" modelAttribute="deal" id="deal-form">
        <tr>
            <td>
                Deal ID:
            </td>
            <td>
                <form:input path="dealId" id="id" />
                <span style="color: red">
                    <form:errors path="dealId" />
                </span>
            </td>
        </tr>
        <tr>
            <td>
                Ordering currency:
            </td>
            <td>
                <form:select id="orderingCurrency" path="orderCurrencyIsoCode">
                    <form:options  items="${supportedCurrencies}"/>
                </form:select>
                <span style="color: red">
                    <form:errors path="orderCurrencyIsoCode" />
                </span>
            </td>
        </tr>
        <tr>
            <td>
                To currency:
            </td>
            <td>
                <form:select id="toCurrency" path="toCurrencyIsoCode">
                    <form:options items="${supportedCurrencies}"/>
                </form:select>
                <span style="color: red">
                    <form:errors path="toCurrencyIsoCode" />
                </span>
            </td>
        </tr>
        <tr>
            <td>
                Deal Date:
            </td>
            <td>
                <form:input type="datetime-local" path="dealDate" />
                <span style="color: red">
                    <form:errors path="dealDate" />
                </span>
            </td>
        </tr>
        <tr>
            <td>
                Deal Amount:
            </td>
            <td>
                <form:input path="dealAmount" id="amount" />
                <span style="color: red">
                    <form:errors path="dealAmount" />
                </span>
            </td>
        </tr>
        <tr><div>
            <button type="submit" >
                Submit
            </button></div>
        </tr>
    </form:form>

</table>
</div>
<div>
    <button type="button">
        <a href="/deals">View all deals</a>
    </button>
</div>
</body>
</html>