<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 18.11.2015
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="ServletLowCost" method="post">

  <input type="hidden" name="page_name" value="registration" /> <br />

  Login: <input type="text" name="login" value="" /> <br />

  Password: <input type="text" name="password" value="" /> <br />

  Firstname: <input type="text" name="firstname" value="" /> <br />

  Lastname: <input type="text" name="lastname" value="" /> <br />

  Birthday time: <input type="date" name="birth_date" value="" /> <br />

  Mobile phone:<input type="text" name="mobile_phone" value="" /> <br />

  Email:<input type="text" name="email" value="" /> <br />

  <input type="submit" name="registration"  value="Confirm" />

</form>

</body>
</html>
