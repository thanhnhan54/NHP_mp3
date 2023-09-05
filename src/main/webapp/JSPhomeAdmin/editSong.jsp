<%--
  Created by IntelliJ IDEA.
  User: quocphu
  Date: 13/06/2023
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
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
    .error-massage{
        color: red;
    }
    </style>

</head>
<body style="background: #1f1f1f">
<c:if test="${requestScope['message'] != null}">
    <span>${message}</span>
</c:if>
    <div class="container">
        <div class="overlay">
            <form id="form-edit-song" action="/admin/songs?action=edit&id=${song.id}" method="post">
                <div>
                    <label for="name">Name</label>
                    <input type="text" name="name" id="name" value="${song.name}" /><br>
                    <label class="error-massage" id="message-name"></label>
                </div>
                <div>
                    <label for="author">Author</label>
                    <select name="author" id="author">
                        <option value="null">--None--</option>

                        <c:forEach items="${authors}" var="author">
                            <c:if test="${song.author.id == author.id}">
                                <option selected value="${author.id}">${author.name}</option>
                            </c:if>
                            <c:if test="${song.author.id != author.id}">
                                <option value="${author.id}">${author.name}</option>
                            </c:if>
                        </c:forEach>
                    </select><br>
                    <label class="error-massage" id="message-author"></label>
                </div>
                <div>
                    <label for="category">Category</label>
                    <select name="category" id="category" >
                        <option value="null">--None--</option>
                        <c:forEach items="${categorys}" var="category">
                            <c:if test="${song.category.id == category.id}">
                                <option selected value="${category.id}">${category.name}</option>
                            </c:if>
                            <c:if test="${song.category.id != category.id}">
                                <option value="${category.id}">${category.name}</option>
                            </c:if>
                        </c:forEach>
                    </select><br>
                    <label class="error-massage" id="message-category"></label>
                </div>
                <div>
                    <label for="singer">Category</label>
                    <select name="singer" id="singer">
                        <option value="null">--None--</option>
                        <c:forEach items="${singers}" var="singer">
                            <c:if test="${song.singer.id == singer.id}">
                                <option selected value="${singer.id}">${singer.name}</option>
                            </c:if>
                            <c:if test="${song.singer.id != singer.id}">
                                <option value="${singer.id}">${singer.name}</option>
                            </c:if>
                        </c:forEach>
                    </select><br>
                    <label class="error-massage" id="message-singer"></label>
                </div>
                <div>
                    <label for="musicFile">Music File:</label>
                    <input type="file" id="musicFile" name="filePart"><br><br>
                    <label class="error-massage" id="message_music"></label>
                </div>
                <div>
                    <label for="image">Image</label>
                    <input type="text" name="image" id="image" value="${song.image}"/>
                    <label class="error-massage" id="message-image"></label>
                </div>
<%--                <div>--%>
<%--                    <label for="song">Song</label>--%>
<%--                    <input type="text" name="song" id="song" value="${song.song}" /><br>--%>
<%--                    <label class="error-massage" id="message-song"></label>--%>
<%--                </div>--%>
<%--                <div>--%>
<%--                    <label for="image">Ảnh</label>--%>
<%--                        <input type="text" name="image" id="image" value="${song.image}" /><br>--%>
<%--                        <label class="error-massage" id="message-image"></label>--%>
<%--                </div>--%>
                <a href="#" onclick="submitForm()">Edit</a>
            </form>
            <a href="../admin/songs">Back</a>
        </div>
    </div>
</body>
<script>
    function submitForm(){
        if (validateFormEditSong()){
            document.getElementById("form-edit-song").submit();
            alert("Cập nhật thành công")
        }
    }
    function validateString(inputString) {
        var regex = /[^\x00-\x7f]+/g;
        return regex.test(inputString);
    }
    function validateFormEditSong(){
        const name = document.getElementById("name").value;
        const author = document.getElementById("author").value;
        const category = document.getElementById("category").value;
        const singer = document.getElementById("singer").value;
        const music = document.getElementById("song").value;
        const image = document.getElementById("image").value;

        var result = true;
        if (!name || name.trim().length <= 0  ){
            result = false;
            document.getElementById("message-name").innerHTML = "Tên bài hát không được để trống"
        }
        if ( name.trim().length > 50 ){
            result = false;
            document.getElementById("message-name").innerHTML = "Tên bài hát không được quá 50 ký tự"
        }
        if (!validateString(name)){
            result = false;
            document.getElementById("message-name").innerHTML = "Tên bài hát không được có ký tực đặc biệt"
        }
        if (!author || author === "null"){
            result = false;
            document.getElementById("message-author").innerHTML = "Vui lòng chọn tên nhạc sĩ"
        }
        if (!category || category === "null"){
            result = false
            document.getElementById("message-category").innerHTML = "Vui lòng chọn thể loại"
        }
        if (!singer || singer === "null"){
            result = false
            document.getElementById("message-singer").innerHTML = "Vui lòng chọn tên ca sĩ"
        }
        if (!music || music.trim().length <= 0){
            result = false
            document.getElementById("message_music").innerHTML = "Vui lòng nhập bài hát"
        }
        if (!image || image <= 0){
            result = false
            document.getElementById("message-image").innerHTML = "Link ảnh không được để trống"
        }
        return result
    }
</script>
</html>
