<%@ include file="../JSPhomeAdmin/header.jsp" %>
<style>
    .containerr {
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





    a {
        color: #0099ff;
        text-decoration: none;
    }

    a:hover {
        text-decoration: underline;
    }

</style>
<div class="containerr">
<div class="overlay">
<h1>${action}</h1>
<form action="/admin/users" method="get">
    <input type="search" name="search" id="search" value="${pageable.search}" onsearch="onClearSearch()" />
    <button id="searchButton">Search</button>
</form>
</br>
<c:if test="${requestScope['users'].size() != 0}">
    <table border="1">
        <tr>
            <td>
                <c:if test="${pageable.sortBy== 'desc'}">
                    <a href="/admin/users?page=${pageable.page}&search=${pageable.search}&sortby=asc&nameField=user.id">
                        Id
                    </a>
                </c:if>
                <c:if test="${pageable.sortBy== 'asc'}">
                    <a href="/admin/users?page=${pageable.page}&search=${pageable.search}&sortby=desc&nameField=user.id">
                        Id
                    </a>
                </c:if>
            </td>
            <td>Name</td>
<%--            <td>Password</td>--%>
            <td>Role</td>
            <td>Action</td>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
<%--                <td>${user.password}</td>--%>
                <td>${user.role.name}</td>
                <td><a href="/admin/users?action=edit&id=${user.id}">Edit</a> </td>
                <td><a href="/admin/users?action=delete&id=${user.id}" onclick="return confirm('Do you want to remove ${user.username}?')">Delete</a> </td>
            </tr>
        </c:forEach>
    </table>
    <ul>
        <c:forEach begin="1" end="${pageable.totalPage}" var="page">
            <li><a href="/admin/users?page=${page}&search=${pageable.search}&sortby=${pageable.sortBy}&nameField=${pageable.nameField}">${page}</a> </li>
        </c:forEach>

    </ul>
</c:if>
</div>
</div>
<%@ include file="../JSPhomeAdmin/footer.jsp" %>
