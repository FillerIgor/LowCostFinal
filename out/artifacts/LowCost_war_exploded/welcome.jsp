<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 18.11.2015
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tld/taglib.tld"%>
<html>
<head>
    <title></title>
</head>
<body>
<ex:mytag message="Welcome to LowCost.You can make order. Please fill the fields below and press button \"Order\".">
</ex:mytag>
<br>
<form action="ServletLowCost" method="post">
  <input type="hidden" name="page_name" value="welcome">
 Date of departure: <input type="date" name="date_of_departure" value=""/> </br>
  City of departure:<input  list="city_of_departure" name="city_of_departure" >
    <datalist id="city_of_departure">
        <option value="Saint-Peterburg">
        <option value="Moscow">
        <option value="Simferopol">
        <option value="Minsk">
    </datalist></br>

City of arrival:<input list="city_of_arrival" name="city_of_arrival">
    <datalist id="city_of_arrival">
        <option value="Saint-Peterburg">
        <option value="Moscow">
        <option value="Simferopol">
        <option value="Minsk">
    </datalist></br>

Baggage:<input type="checkbox" name="baggage" value="true"/></br>
Priority registration and landing:<input type="checkbox" name="priority_regist_land" value="true"/></br>
    <input type="submit" name="submit" value="Order">
</form>
</body>
</html>
