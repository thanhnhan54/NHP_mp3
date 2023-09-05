<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>NHP-Nhachayphet.mp3</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Carlos Alvarez - Alvarez.is">


    <link rel="stylesheet" type="text/css" href="<c:url value="/bootstrap/css/bootstrap.min.css"></c:url>"/>

    <link href="../css/main.css" rel="stylesheet">
    <link href="../css/font-style.css" rel="stylesheet">
    <link href="../css/flexslider.css" rel="stylesheet">



    <!-- NOTY JAVASCRIPT -->


    <style type="text/css">
        body {
            padding-top: 60px;
        }
        .selected {
            background-color: #3d3d3d; /* Thay đổi màu nền thành màu đỏ (#ff0000) */
        }
        #liii{
            color: #9d9d9d;
            position: relative;
            padding-right: 10px;
            padding-left: 10px;
            padding-top: 15px;
            padding-bottom: 15px;
        }
        .ulll{
            color: #9d9d9d;
            display: none;
            position: absolute;
            background-color: rgba(0, 0, 0, 0.7); /* Màu sắc nền của khung mờ */
            padding: 20px;
            border-radius: 10px;
            color: white;
            width: 100%;
            left: 0;
        }
        #liii:hover{
            color: white;
        }
        #liii:hover .ulll{

            list-style: none;
            display: block;
        }


        .aa{
            color: #9d9d9d;
            padding-bottom: 5px;
            padding-top: 5px;
        }
        .aa:hover{
            color: white;
        }
        .myself a {
            color: inherit; /* Kế thừa màu chữ từ phần tử cha */
            background-color: inherit; /* Kế thừa màu nền từ phần tử cha */
            text-decoration: none; /* Bỏ gạch chân của liên kết */
        }
        .audioplayer {
            display: none;
        }
    </style>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->


    <!-- Google Fonts call. Font Used Open Sans & Raleway -->
    <link href="http://fonts.googleapis.com/css?family=Raleway:400,300" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">


    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


</head>
<body>
<script>
    function onClearSearch() {
        searchButton.click();
    }
    $(document).ready(function() {
        var currentPage = window.location.pathname; // Lấy đường dẫn hiện tại

        $('.myself').removeClass('selected'); // Loại bỏ lớp `selected` từ tất cả các mục
        $('.myself a[href="' + currentPage + '"]').parent().addClass('selected'); // Thêm lớp `selected` cho mục trùng khớp với đường dẫn hiện tại

        // Lưu trạng thái đã chọn vào session storage
        sessionStorage.setItem('selectedLink', currentPage);
    });
</script>
<!-- NAVIGATION MENU -->

<div class="navbar-nav navbar-inverse navbar-fixed-top">

    <div class="container" style="display: flex ;position: relative">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/admin/songs"> NHP mp3</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="myself"><a href="/admin/songs"><i class="icon-home icon-white"></i>Song Management</a></li>

                <li class="myself"><a href="/admin/users"><i class="icon-user icon-white"></i> User Management</a></li>
                <li  id="liii" class="myself"><i class="icon-user icon-white"></i>ACS management
                    <ul class="ulll">
                        <li class="aa"><a href="/admin/authors">Author</a></li>
                        <li class="aa"><a href="/admin/categorys">Category</a></li>
                        <li class="aa"><a href="/admin/singers">Singer</a></li>
                    </ul>
                </li>
                <c:if test="${sessionScope.user == null}">
                    <li><a href="/login"><i class="icon-lock icon-white"></i> Login</a></li>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                    <li><a href="/home?action=logout"><i class="icon-lock icon-white"></i> Logout</a></li>
                </c:if>


<%--<<<<<<< Updated upstream--%>
<%--                <li><a href="/admin/songs"><i class="icon-th icon-white"></i> Song Management</a></li>--%>
<%--                <li><a href="/admin/users"><i class="icon-user icon-white"></i> User Management</a></li>--%>
<%--                <c:if test="${sessionScope.user == null}">--%>
<%--                    <li><a href="/login"><i class="icon-lock icon-white"></i> Login</a></li>--%>
<%--                </c:if>--%>
<%--                <c:if test="${sessionScope.user != null}">--%>
<%--                    <li><a href="/home?action=logout"><i class="icon-lock icon-white"></i> Logout</a></li>--%>
<%--                </c:if>--%>
<%--=======--%>

            </ul>
        </div><!--/.nav-collapse -->
        <div style="display: flex;  position: absolute; right: 0;align-content: center; height: 100%">
            <h1>${action}</h1>

            <div class="d-flex justify-content-between"
                 style="padding-left: 4%; margin-bottom: auto;margin-top: auto;align-content: center">
                <form action="/admin/songs" method="get" style="padding-right: 4%; display: flex">
                    <div style=" padding-right: 50px">
                        <a href="/admin/songs?action=create">
                            <button style="border-radius: 20px;height: 40px" type="button" class="btn btn-success mr-1"><i class="fas fa-plus"></i> Create Song</button>
                        </a>

                    </div>
                    <div style="display: flex">
                        <input style=" height: 40px;width: 300px;border-radius: 20px" type="search"
                               name="search" id="search" value="${pageable.search}" onsearch="onClearSearch()"/>
                        <button style="border-radius: 50%;width: 40px; height: 40px" id="searchButton" class="btn btn-primary" type="submit"><i class="fas fa-search"></i>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


