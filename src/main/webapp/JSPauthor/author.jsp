<%--
  Created by IntelliJ IDEA.
  User: quocphu
  Date: 14/06/2023
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>${action}</h1>--%>
<%--<a href="authors?action=create">Create Book</a>--%>
<%--<c:if test="${requestScope['authors'].size() != 0}">--%>
<%--    <table border="1">--%>
<%--        <tr>--%>
<%--            <td>Id</td>--%>
<%--            <td>Name</td>--%>
<%--        </tr>--%>
<%--        <c:forEach items="${authors}" var="author">--%>
<%--            <tr>--%>
<%--                <td>${author.id}</td>--%>
<%--                <td>${author.name}</td>--%>
<%--                <td><a href="authors?action=edit&id=${author.id}">Edit</a> </td>--%>
<%--                <td><a href="authors?action=delete&id=${author.id}" onclick="return confirm('Do you want to remove ${author.name}?')">Delete</a> </td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--    </table>--%>
<%--</c:if>--%>
<%--<a href="/admin/songs">Back</a>--%>
<%--</body>--%>
<%--</html>--%>
<%@ include file="../JSPhomeAdmin/header.jsp" %>
<div class="container">
    <div class="container">
        <form method="get" action="/admin/songs">

            <div>
                <a class="btn btn-primary" href="authors?action=create">Create Author</a>
            </div>
        </form>
    </div>
    <c:if test="${requestScope['authors'].size() != 0}">
        <table class="table container border" style="border: none !important; color: white">
            <tbody class="bg-light" style="border: none !important; color: white">
            <tr style="text-align: center" class="text-center ">
                <th style="text-align: left" scope="col">ID</th>
                <th style="text-align: left" scope="col">Author</th>
                <th style="text-align: center" scope="col">
            </tr>
            <c:forEach items="${authors}" var="author">
                <tr style="align-content: center">
                    <td style="align-content: center; align-items: center">${author.id}</td>
                    <td style="align-content: center; align-items: center">${author.name}</td>

                    <td style="align-content: center; align-items: center"><a href="/admin/authors?action=edit&id=${author.id}">
                        <button type="button" class="btn btn-primary mr-1"><i class="fas fa-edit"></i></button>
                    </a></td>
                    <td style="align-content: center; align-items: center"><a href="/admin/authors?action=delete&id=${author.id}"
                                                                              onclick="return confirm('Do you want to remove ${author.name}?')">
                        <button type="button" class="btn btn-danger"><i class="fas fa-trash"></i></button>
                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <ul>
            <div style="text-align: center">
                <c:forEach begin="1" end="${pageable.totalPage}" var="page">
        <span>
        <a href="/admin/songs?page=${page}&search=${pageable.search}&sortBy=${pageable.sortBy}&nameField=${pageable.nameField}"><button>${page}</button></a>
        </span>
                </c:forEach>
            </div>

        </ul>
    </c:if>

</div>

<script>
    var countSecond;
    var startDate;
    var pauseDate = 0;
    var timeOut;
    // ?action=view&+
    function getView(idsong){
        startDate = new Date();
        timeOut = setTimeout(() => {
            fetch('http://localhost:8080/api?action=view&id=' + idsong)
            pauseDate =0;
        }, (60 - pauseDate) * 1000)
    }
    function getDuration(){
        clearTimeout(timeOut);
        let diff = (new Date() - startDate);
        pauseDate = Math.floor((diff / 1000));
    }
    function like(idsong){
        fetch('http://localhost:8080/api?action=like&id=' + idsong)
    }
</script>
<%@ include file="../JSPhomeAdmin/footer.jsp" %>
