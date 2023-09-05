<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
<div class="container">
    <div class="container" style="display: block">
        <form method="get" action="/admin/songs">
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="author">Author</label>
                    <select class="form-control" id="author" name="author">
                        <option value="">All Authors</option>
                        <c:forEach items="${authors}" var="author">
                            <option value="${author.id}">${author.name}</option>
                        </c:forEach>
                    </select></div>
                <div class="form-group col-md-4">
                    <label for="singer">Singer</label>
                    <select class="form-control" id="singer" name="singer">
                        <option value="">All Singers</option>
                        <c:forEach items="${singers}" var="singer">
                            <option value="${singer.id}">${singer.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-md-4">
                    <label for="category">Category</label>
                    <select class="form-control" id="category" name="category">
                        <option value="">All Categories</option>
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <span>
                <button type="submit" class="btn btn-primary">Filter</button>
            </span>
        </form>
    </div>
    <c:if test="${requestScope['songs'].size() != 0}">
        <table class="table container border" style="border: none !important; color: white">
            <tbody class="bg-light" style="border: none !important; color: white">
            <tr style="text-align: center" class="text-center ">
                <th style="text-align: center" scope="col">
                    <c:if test="${pageable.sortBy== 'desc'}">
                        <a
                                href="../admin/songs?page=${pageable.page}&search=${pageable.search}&sortBy=asc&nameField=song.id">
                            ID
                        </a>
                    </c:if>
                    <c:if test="${pageable.sortBy== 'asc'}">
                        <a
                                href="../admin/songs?page=${pageable.page}&search=${pageable.search}&sortBy=desc&nameField=song.id">
                            ID
                        </a>
                    </c:if>
                </th>
                <th style="text-align: center" scope="col">

                    <c:if test="${pageable.sortBy== 'desc'}">
                        <a
                                href="../admin/songs?page=${pageable.page}&search=${pageable.search}&sortBy=asc&nameField=song.name">
                            name
                        </a>
                    </c:if>
                    <c:if test="${pageable.sortBy== 'asc'}">
                        <a
                                href="../admin/songs?page=${pageable.page}&search=${pageable.search}&sortBy=desc&nameField=song.name">
                            name
                        </a>
                    </c:if>
                </th>
                <th style="text-align: center" scope="col">
                    <c:if test="${pageable.sortBy== 'desc'}">
                        <a
                                href="../admin/songs?page=${pageable.page}&search=${pageable.search}&sortBy=asc&nameField=name_author">
                            Author
                        </a>
                    </c:if>
                    <c:if test="${pageable.sortBy== 'asc'}">
                        <a
                                href="../admin/songs?page=${pageable.page}&search=${pageable.search}&sortBy=desc&nameField=name_author">
                            Author
                        </a>
                    </c:if>
                </th>
                <th style="text-align: center" scope="col">
                    <c:if test="${pageable.sortBy== 'desc'}">
                        <a
                                href="../admin/songs?page=${pageable.page}&search=${pageable.search}&sortBy=asc&nameField=type">
                            Category
                        </a>
                    </c:if>
                    <c:if test="${pageable.sortBy== 'asc'}">
                        <a
                                href="../admin/songs?page=${pageable.page}&search=${pageable.search}&sortBy=desc&nameField=type">
                            Category
                        </a>
                    </c:if>
                </th>
                <th style="text-align: center" scope="col">
                    <c:if test="${pageable.sortBy== 'desc'}">
                        <a
                                href="../admin/songs?page=${pageable.page}&search=${pageable.search}&sortBy=asc&nameField=name_singer">
                            Singer
                        </a>
                    </c:if>
                    <c:if test="${pageable.sortBy== 'asc'}">
                        <a
                                href="../admin/songs?page=${pageable.page}&search=${pageable.search}&sortBy=desc&nameField=name_singer">
                            Singer
                        </a>
                    </c:if>
                </th>
                <th style="text-align: center" scope="col">Song</th>
                <th style="text-align: center" scope="col">Image</th>
            </tr>
            <c:forEach items="${songs}" var="song">
                <tr style="align-content: center">
                    <td style="align-content: center; align-items: center">${song.id}</td>
                    <td style="align-content: center; align-items: center">${song.name}</td>
                    <td style="align-content: center; align-items: center">${song.author.name}</td>
                    <td style="align-content: center; align-items: center">${song.category.name}</td>
                    <td style="align-content: center; align-items: center">${song.singer.name}</td>
                    <td style="align-content: center; align-items: center">
                            <%--                    ${song.image}--%>
                        <audio style="height: 30px" controls class="song-play" onplaying="getView(${song.id})" onpause="getDuration()" id="audio${status.index}">
                            <source src="<c:url value='/${song.song}'></c:url>" type="audio/mp3 ">
                        </audio>
                    </td>
                    <td style="align-content: center; align-items: center">
                            <%--                                            ${song.song}--%>
                        <img style="height: 40px;width: 40px;border-radius: 50%" class="image" src="${song.image}">
                    </td>
<%--                    <td>--%>
<%--                        <c:set var="check" value="false"></c:set>--%>
<%--                        <c:forEach items="${sessionScope.likes}" var="like">--%>

<%--                            <c:if test="${song.id == like.song.id && like.status == 1}">--%>
<%--                                <button id="buttonLike" onclick="like(${song.id})">Dislike</button>--%>
<%--                                <c:set var="check" value="true"></c:set>--%>
<%--                            </c:if>--%>
<%--                            <c:if test="${song.id == like.song.id && like.status != 1}">--%>
<%--                                <button id="buttonLike" onclick="like(${song.id})">Like</button>--%>
<%--                                <c:set var="check" value="true"></c:set>--%>
<%--                            </c:if>--%>
<%--                        </c:forEach>--%>
<%--                        <c:if test="${check== false}">--%>
<%--                            <button id="buttonLike" onclick="like(${song.id})">Like</button>--%>
<%--                        </c:if>--%>

<%--                        <c:if test="${check}" >--%>
<%--                            <button onclick="like(${song.id})">Dislike</button>--%>
<%--                        </c:if>--%>
<%--                        <c:if test="${!check}">--%>
<%--                            <button onclick="like(${song.id})">Like12321</button>--%>
<%--                        </c:if>--%>
<%--                    </td>--%>
                    <td style="align-content: center; align-items: center"><a href="songs?action=edit&id=${song.id}">
                        <button type="button" class="btn btn-primary mr-1"><i class="fas fa-edit"></i></button>
                    </a></td>
                    <td style="align-content: center; align-items: center"><a href="songs?action=delete&id=${song.id}"
                                                                              onclick="return confirm('Do you want to remove ${song.name}?')">
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
        let statusCurrent = document.getElementById('buttonLike');
        console.log(statusCurrent.innerHTML)
        if(statusCurrent.innerHTML == 'Like') {
            statusCurrent.innerHTML = 'Dislike';
        } else {
            statusCurrent.innerHTML = 'Like'
        }
    }
</script>
<%@ include file="footer.jsp" %>

