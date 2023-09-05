<%--
  Created by IntelliJ IDEA.
  User: Windows 10
  Date: 12/06/2023
  Time: 2:01 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/playlist?action=create" method="post">
    <label for="pl_name"></label>
    <input type="text" name = "pl_name" id="pl_name">
    <input type="hidden" name="id_user" value="${sessionScope.user.id}">
</form>
<c:forEach items="${list_playlist}" var="playlist">
    <a href="#" value="${playlist.id}">${playlist.name}</a>
</c:forEach>
</body>
</html>
