<%--
  Created by IntelliJ IDEA.
  User: quocphu
  Date: 14/06/2023
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    .container {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .overlay {
        background-color: rgba(0, 0, 0, 0.5); /* Màu sắc nền của khung mờ */
        padding: 20px;
        border-radius: 10px;
        text-align: center;
        color: white;
        width: 30%;
    }

    form {
        font-family: Arial, sans-serif;
    }

    label, input, select {
        font-size: 16px;
    }

    input[type="text"],
    select {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
    }

    select {
        appearance: none;
        background-repeat: no-repeat;
        background-position: right center;
        padding-right: 25px;
    }

    input[type="text"]:hover,
    input[type="text"]:focus,
    select:hover,
    select:focus {
        border-color: #0099ff;
        outline: none;
    }

    button[type="submit"] {
        padding: 10px 20px;
        background-color: #0099ff;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    button[type="submit"]:hover {
        background-color: #0077cc;
    }

    a {
        color: #0099ff;
        text-decoration: none;
    }

    a:hover {
        text-decoration: underline;
    }

</style>
<body style="background: #1f1f1f">
<c:if test="${requestScope['message'] != null}">
    <span>${message}</span>
</c:if>
<div class="container">
    <div class="overlay">
        <form id="form-edit-singer" action="/admin/singers?action=edit&id=${singers.id}" method="post">
            <div>
                <label for="name">Name</label>
                <input type="text" name="name" id="name" value="${singers.name}" />
                <label style="color: red" id="error-name"></label>
            </div>
            <a href="#" onclick="submitForm()">Create</a>
        </form>
        <a href="../admin/singers">Back</a>
    </div>
</div>
</body>
<script>
    function submitForm(){
        if (validateFormEditSinger()){
            document.getElementById("form-edit-singer").submit();
        }
    }
    function validateFullName(fullName) {
        var regex = /^[\p{L} .'-]+$/u;
        return regex.test(fullName);
    }
    function validateFormEditSinger(){
        const name = document.getElementById("name").value

        let ruselt = true;
        if (!name || name.trim().length <= 0 || name.trim().length >50){
            result = false;
            document.getElementById("error_name").innerHTML = "Tên ca sĩ  không được để trống"
        }
        if ( name.trim().length >50){
            result = false;
            document.getElementById("error_name").innerHTML = "Tên ca sĩ  không được quá 50 ký tự"
        }
        if (!validateFullName(name)){
            result = false;
            document.getElementById("error_name").innerHTML = "Tên ca sĩ không được có ký tự đặc biệt hoặc số"
        }
        return ruselt;
    }
</script>
</html>
