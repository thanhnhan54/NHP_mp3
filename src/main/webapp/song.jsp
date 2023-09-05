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
</head>
<style>
    .image{
        width: 100px;
        height: 100px;
    }
</style>
<body>
<h1>${action}</h1>
<a href="/admin/songs?action=create">Create Book</a>
<c:if test="${requestScope['songs'].size() != 0}">
    <table border="1">
        <tr>
            <td>Id</td>
            <td>Name</td>
            <td>Author</td>
            <td>Category</td>
            <td>Singer</td>
            <td>Song</td>
            <td>Image</td>
        </tr>
        <c:forEach items="${songs}" var="song" >
            <tr>
                <td>${song.id}</td>
                <td>${song.name}</td>
                <td>${song.author.name}</td>
                <td>${song.category.name}</td>
                <td>${song.singer.name}</td>
                <td>
                    <audio controls class="song-play" onplay="getView(${song.id})" onpause="getDuration()" id="audio${status.index}">
                        <source src="${song.song}" type="audio/mp3">
                    </audio>
                </td>
                <td >

                    <img class="image" src="${song.image}">
                </td>

                <td><a href="admin/songs?action=edit&id=${song.id}">Edit</a> </td>
                <td><a href="admin/songs?action=delete&id=${song.id}" onclick="return confirm('Do you want to remove ${song.name}?')">Delete</a> </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
<script>
    var countSecond;
    var startDate;
    var pauseDate = 0;
    var timeOut;
        // ?action=view&+
    function getView(idsong){
       timeOut = setTimeout(() => {
            fetch('http://localhost:8080/api?id=' + idsong)
        }, (60 - pauseDate) * 1000)
    }
    function getDuration(){
        clearTimeout(timeOut);
          let diff = (new Date() - startDate);
         pauseDate = Math.floor((diff / 1000));
    }

</script>
</html>
