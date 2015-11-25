<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 19.11.2015
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title></title>
</head>
<body>
Please fill additional fields to make an order.
  <form action="ServletLowCost" method="post">
    <input type="hidden" name="page_name" value="order">
      Credit card number:<input type="text" name="credit_card_number" value=""/></br>

      Number of passport:<input type="text" name="number_of_passport" value=""/></br>

    <input type="submit" name="submit" value="Confirm">
    </form>
</body>
</html>
