<%@ page import="model.Item" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Levon
  Date: 9/13/2022
  Time: 12:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
  List<Item> items = (List<Item>) request.getAttribute("items");
%>
<table border="1">
  <tr>
    <th>Image</th>
    <th>id</th>
    <th>title</th>
    <th>price</th>
    <th>category</th>
    <th>user</th>
  </tr>
  <%
    for (Item item : items) { %>
  <tr>
    <td>
      <%
        if (item.getPicUrl() == null || item.getPicUrl().length() == 0){%>
      <img src="/image/defaultImage.jpg" width="100">

      <%} else {  %>
      <img src="/getImage?PicUrl=<%=item.getPicUrl()%> " width="100">
      <%}%>
    </td>
    <td><%=item.getId()%>
    </td>
    <td><%=item.getTitle()%>
    </td>
    <td><%=item.getPrice()%>
    </td>
    <td>
      <% if (item.getCategory() != null) {%>
      <%=item.getCategory().getName()%>
      <%} else {%>
      <span style="color: red">there is no category</span>
      <%}%>
    </td>
    <td>
      <%=item.getUser().getEmail()%>
    </td>
  </tr>
  <% } %>
</table>
</body>
</html>
