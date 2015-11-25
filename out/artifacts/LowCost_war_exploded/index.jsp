<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 16.11.2015
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
  <head>
    <title></title>
  </head>
  <body>
  <form action="ServletLowCost" method="post">

      <input type="hidden" name="page_name" value="index" /> <br />

      <input type="hidden" name="local" value="ru" />

      Login: <input type="text" name="login" value="" /> <br />

      Password: <input type="text" name="password" value="" /> <br />

      <input type="submit" name="sign_in"  value="Sign in" />
      <input type="submit" name="registration" value="Registration"/>


  </form>
  </body>
</html>
