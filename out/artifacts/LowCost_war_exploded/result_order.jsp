<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<table>
<tbody>
<tr><th>Client id</th><th>Firstname</th><th>Lastname</th></tr>
  <tr><td><c:out value="${client.id}"></c:out></td>
    <td><c:out value="${client.firstname}"></c:out></td>
    <td><c:out value="${client.lastname}"></c:out></td></tr>
</tbody>
</table>
<hr>
    <table>
        <tbody>
        <tr><th>Flight number</th><th>Time of departure</th><th>Date of departure</th><th>City of departure</th><th>Departure terminal</th><th>Time of arrival</th><th>City of arrival</th><th>Arrival terminal</th><th>Price</th></tr>
        <tr><td><c:out value="${ticket.flight_id}"></c:out> </td>
            <td><c:out value="${ticket.time_of_departure}"></c:out> </td>
            <td><c:out value="${ticket.date_of_departure}"></c:out> </td>
            <td><c:out value="${ticket.city_of_departure}"></c:out> </td>
            <td><c:out value="${ticket.departure_terminal}"></c:out> </td>
            <td><c:out value="${ticket.time_of_arrival}"></c:out> </td>
            <td><c:out value="${ticket.city_of_arrival}"></c:out> </td>
            <td><c:out value="${ticket.arrival_terminal}"></c:out> </td>
            <td><c:out value="${ticket_price} RUB"></c:out></td>

        </tr>
        </tbody>
    </table>
    <hr>
<br><br>
<form action="ServletLowCost" method="post">
    <input type="hidden" name="page_id" value="result_order">
    <input type="submit" name="confirm" value="Confirm">
</form>
</body>
</html>
