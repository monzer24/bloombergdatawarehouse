<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<head>
    <style>
        td{
            margin: 5px;
        }
    </style>
</head>
<body>
<div>
    <div>
        <h1>All Bloomberg Deals</h1>
    </div>
    <table border="2px">
        <tbody>
            <tr>
                <td>
                    Deal ID
                </td>
                <td>
                    Ordering Currency
                </td>
                <td>
                    To Currency
                </td>
                <td>
                    Deal Date
                </td>
                <td>
                    Deal Amount
                </td>
                <td>
                    Action
                </td>
            </tr>
        </tbody>
        <c:forEach items="${deals}" var="deal">
            <tr>
                <td>
                    <c:out value="${deal.dealCode}"/>
                </td>
                <td>
                    <c:out value="${deal.orderCurrency}"/>
                </td>
                <td>
                    <c:out value="${deal.toCurrency}"/>
                </td>
                <td>
                    <c:out value="${deal.dealDate}"/>
                </td>
                <td>
                    <c:out value="${deal.dealAmount}"/>
                </td>
                <td>
                    <button type="button">
                        <a href="/${deal.dealCode}">View</a>
                    </button>
                </td>
            </tr>
        </c:forEach>

    </table>
</div>
<button type="button">
    <a href="/">Add new deal</a>
</button>
</body>
</html>
