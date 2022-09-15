<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Category" %>
<%@ page import="manager.CategoryManager" %><%--
  Created by IntelliJ IDEA.
  User: Levon
  Date: 9/12/2022
  Time: 11:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>

<%
    CategoryManager categoryManager = new CategoryManager();
    List<Category> categories = categoryManager.getAll();
    User user = (User) session.getAttribute("user");
%>

<%if (user != null) {%>
<h1> Hello <%=user.getName()%>
</h1>
<a style="float: right" href="/items/add">Add Item</a>
<a style="float: right" href="/logout">Logout</a>
<a href="/myItems">My items</a>
<%} else {%>
<a style="float: right" href="/register"><h3>Register</h3></a> <a style="float: right" href="/login"><h3>Login</h3></a>
<a href="/items">HomePage</a>
<%
    for (Category category : categories) {
%>
<div style="margin: 0 auto">
    <a href="/ItemsByCategoryId?category_id=<%=category.getId()%>"><%=category.getName()%></a>
</div>
<%
    }
%>
<%}%>
</body>
</html>
