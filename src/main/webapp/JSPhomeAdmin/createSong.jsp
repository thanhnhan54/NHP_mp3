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
        .error-massage{
            color: red;
        }
    </style>
</head>
<body style="background: #1f1f1f">
<c:if test="${not empty requestScope['message']}">
    <span>${message}</span>
</c:if>

<div class="container">
    <div class="overlay">
        <form id="form-create-song" action="/admin/songs?action=create" method="post" enctype="multipart/form-data">
            <div>
                <label for="name">Name</label>
                <input type="text" name="name" id="name" value="${song.name}"/>
                <label class="error-massage" id="message-name"></label>
            </div>
            <div>
                <label for="author">Author</label>
                <select name="author" id="author">
                    <option value="null">--None--</option>
                    <c:forEach items="${authors}" var="author">
                        <c:if test="${author.id == song.author.id}">
                            <option value="${author.id}" selected>${author.name}</option>
                        </c:if>
                        <c:if test="${author.id != song.author.id}">
                            <option value="${author.id}">${author.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <label class="error-massage" id="message-author"></label>
            </div>
            <div>
                <label for="category">Category</label>
                <select name="category" id="category">
                    <option value="null">--None--</option>
                    <c:forEach items="${categorys}" var="category">
                        <c:if test="${category.id == song.category.id}">
                            <option value="${category.id}" selected>${category.name}</option>
                        </c:if>
                        <c:if test="${category.id != song.category.id}">
                            <option value="${category.id}">${category.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <label class="error-massage" id="message-category"></label>
            </div>
            <div>
                <label for="singer">Singer</label>
                <select name="singer" id="singer">
                    <option value="null">--None--</option>
                    <c:forEach items="${singers}" var="singer">
                        <c:if test="${singer.id == song.singer.id}">
                            <option value="${singer.id}" selected>${singer.name}</option>
                        </c:if>
                        <c:if test="${singer.id != song.singer.id}">
                            <option value="${singer.id}">${singer.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <label class="error-massage" id="message-singer"></label>
            </div>
            <div>
                <label for="musicFile">Music File:</label>
                <input type="file" id="musicFile" name="filePart1"><br><br>
                <label class="error-massage" id="message_music"></label>
            </div>
            <div>
                <label for="image">Image</label>
                <input type="file" name="filePart2" id="image" />
                <label class="error-massage" id="message-image"></label>
            </div>
            <a href="#" onclick="submitForm()">Create</a>
        </form>
        <a href="../admin/songs">Back</a>
    </div>
</div>
</body>
<script>
    function submitForm(){
        if (ValidateFormCreateSong()){
            document.getElementById("form-create-song").submit();
            alert("Thêm mới thành công")
        }
    }
    function validateString(inputString) {
        var regex = /[^\x00-\x7f]+/g;
        return regex.test(inputString);
    }
    function ValidateFormCreateSong(){
        const name = document.getElementById("name").value;
        const author = document.getElementById("author").value;
        const category = document.getElementById("category").value;
        const singer = document.getElementById("singer").value;
        const music = document.getElementById("musicFile").value;
        console.log(music)
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
        if (!music || music === "null"){
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