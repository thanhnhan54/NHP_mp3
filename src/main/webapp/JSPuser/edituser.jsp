<%--
  Created by IntelliJ IDEA.
  User: Windows 10
  Date: 14/06/2023
  Time: 1:52 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${requestScope['message'] != null}">
    <span>${message}</span>
</c:if>
<a  href="/admin/users">Back</a><br>
<form id="form-user" action="/admin/users?action=edit&id=${user.id}" method="post">
    <input type="hidden" name="id" value="${user.id}">
    <label for="username">Username</label>
    <input type="hidden" name="username" id="name" value="${user.username}" />
    <label id="username" >${user.username}</label>
    <br>
    <label for="password">Password</label>
    <input type="text" name="password" id="password"  />
    <label  id="error-password"></label>
    <br>
    <div>

        <label for="role">Role</label>
        <select name="role" id="role">
            <option value="null">--None--</option>
            <c:forEach items="${roles}" var="role">
                <c:if test="${user.role.id == role.id}">
                    <option selected value="${role.id}">${role.name}</option>
                </c:if>
                <c:if test="${user.role.id != role.id}">
                    <option value="${role.id}">${role.name}</option>
                </c:if>
            </c:forEach>
        </select>
        <label id="error-role"></label>
    </div>
    <a href="#" onclick="submitFrom()">Edit</a>
</form>
<script>
    function submitFrom(){
        if (validateFormUserEdit()){
            document.getElementById("form-user").submit();
        }
    }
    function validateFormUserEdit(){
        const role = document.getElementById("role").value;
        const password =document.getElementById("password").value;

        var result = true;
        if (!role || role === "null"){
            result = false;
            document.getElementById("error-role").innerHTML = "Phải chọn quyền"
        }
        if (!password || password.trim().length <= 0 || password.trim().length >30){
            result = false;
            document.getElementById("error-password").innerHTML = "Mật khẩu không được để trống và không quá 30 ký tự"
        }
        return result;
    }
</script>
</body>
</html>