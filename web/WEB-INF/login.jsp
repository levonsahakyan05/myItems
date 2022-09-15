<%--
  Created by IntelliJ IDEA.
  User: Levon
  Date: 9/13/2022
  Time: 11:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    String msg = (String) request.getAttribute("msg");
%>
<%
    if (msg != null) {%>
<p style="color: red"><%=msg%>
</p>
<%}%>
<form action="/login" method="post">
    <input type="email" name="email" placeholder="Please input email"><br>
    <input type="password" name="password" placeholder="Please input password"><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
