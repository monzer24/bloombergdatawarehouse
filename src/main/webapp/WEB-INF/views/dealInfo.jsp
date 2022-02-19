<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<body>
<div>
    <div>
        <h1>View Bloomberg Deal</h1>
    </div>
    <table>
        <form:form action="" method="post" modelAttribute="deal" id="deal-form">
            <tr>
                <td>
                    Deal ID:
                </td>
                <td>
                    <form:input path="dealCode" id="id" disabled="true"/>
                </td>
            </tr>
            <tr>
                <td>
                    Ordering currency:
                </td>
                <td>
                    <form:input id="orderingCurrency" path="orderCurrency" disabled="true"/>
                </td>
            </tr>
            <tr>
                <td>
                    To currency:
                </td>
                <td>
                    <form:input id="toCurrency" path="toCurrency" disabled="true"/>
                </td>
            </tr>
            <tr>
                <td>
                    Deal Date:
                </td>
                <td>
                    <form:input path="dealDate" disabled="true"/>
                </td>
            </tr>
            <tr>
                <td>
                    Deal Amount:
                </td>
                <td>
                    <form:input path="dealAmount" id="amount" disabled="true" />
                </td>
            </tr>
        </form:form>

    </table>
</div>
<div>
    <button type="button">
        <a href="/">Add new deal</a>
    </button>
    <button type="button">
        <a href="/deals">View all deals</a>
    </button>
</div>
</body>
</html>