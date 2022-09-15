<%@ page import="model.Category" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Levon
  Date: 9/13/2022
  Time: 11:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  List<Category> categories = (List<Category>) request.getAttribute("category");
%>
Please input Item's data:
<form action="/items/add" method="post" enctype="multipart/form-data">
  <input type="text" name="title" placeholder="Please input title"/><br>
  <input type="number" name="price" placeholder="Please input price"/><br>
  <select name="categoryId">
    <%
      for (Category category : categories ) { %>
    <option value="<%=category.getId()%>"><%=category.getName()%></option>
    <%  } %>
  </select>
  Profile Picture
  <input type="file" name="picUrl">
  <input type="submit" value="Register">

</form>
</body>
</html>
