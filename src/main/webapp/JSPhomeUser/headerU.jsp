<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>NHP.mp3 - nhạc hay phết</title>

    <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="../style.css">
    <script src="https://kit.fontawesome.com/your-kit-id.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <script>
        function onClearSearch() {
            searchButton.click();
        }
    </script>
    <style>
        .single-album-area {
            position: relative;
        }

        .album-actions {
            display: flex;
            justify-content: space-between;
            align-items: center;
            position: absolute;
            top: 10px;
            right: 10px;
            z-index: 1;
        }

        .heart-btn,
        .add-btn {
            background-color: transparent;
            border: none;
            cursor: pointer;
            padding: 0;
            outline: none;
            font-size: 24px;
            position: relative;
        }

        .heart-btn .icon-heart,
        .add-btn .icon-plus {
            color: #000;
            transition: color 0.3s ease;
        }

        .heart-btn.clicked .icon-heart {
            color: red;
        }

        .plus-animation {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            border-radius: 50%;
            background-color: #000;
            opacity: 0;
            transform: scale(0);
            transition: opacity 0.3s ease, transform 0.3s ease;
        }

        .add-btn:hover .plus-animation {
            opacity: 0.5;
            transform: scale(1);
        }
    </style>

</head>

<body >
<!-- Preloader -->
<div class=" preloader d-flex align-items-center justify-content-center">
    <div class="lds-ellipsis">
        <div></div>
        <div></div>
        <div></div>
        <div></div>
    </div>
</div>

<!-- phần head -->
<header class="header-area">
    <!-- Navbar Area -->
    <div class="oneMusic-main-menu">
        <div class="classy-nav-container breakpoint-off">
            <div class="container">
                <!-- Menu -->
                <nav class="classy-navbar justify-content-between" id="oneMusicNav">

                    <!-- Nav brand -->
                    <a href="/home" class="nav-brand">
                        <h2 style="font-style:italic; color: whitesmoke;">NHP mp3</h2>
                    </a>

                    <!-- Navbar Toggler -->
                    <div class="classy-navbar-toggler">
                        <span class="navbarToggler"><span></span><span></span><span></span></span>
                    </div>
                    <div>
                        <form action="/list_songs" method="get" style="padding-right: 4%; display: flex">
                        <div style="display: flex">
                            <input style=" height: 40px;width: 300px;border-radius: 20px" type="search"
                                   name="search" id="search" value="${pageable.search}" onsearch="onClearSearch()"/>
                            <button style="border-radius: 50%;width: 40px; height: 40px" id="searchButton" class="btn btn-primary" type="submit"><i class="fas fa-search"></i>
                            </button>
                        </div>
                        </form>
                    </div>

                    <!-- Menu -->
                    <div class="classy-menu">

                        <!-- Close Button -->
                        <div class="classycloseIcon">
                            <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                        </div>

                        <!-- Nav Start -->
                        <div class="classynav">
                            <ul>
                                <li><a href="/home">Trang chủ</a></li>
                     <li><a href="/list_songs">Bài hát</a></li>
                                <li><a href="/playlists">Playlist</a></li>

                                <li><a href="#">Chọn lọc</a>
                                    <ul class="dropdown">
                                        <li><a >Tác giả</a>
                                            <ul class="dropdown">
                                                <c:forEach items="${authors}" var="author">
                                                    <li><a href="/list_songs?author=${author.id}">${author.name}</a></li>
                                                </c:forEach>
                                            </ul>
                                        </li>
                                        <li><a>Ca sĩ</a>
                                            <ul class="dropdown">
                                                <c:forEach items="${singers}" var="singer">
                                                    <li><a href="/list_songs?singer=${singer.id}">${singer.name}</a></li>
                                                </c:forEach>
                                            </ul>
                                        </li>
                                        <li><a> Thể loại</a>
                                            <ul class="dropdown">
                                                <c:forEach items="${categories}" var="category">
                                                    <li><a href="/list_songs?category=${category.id}">${category.name}</a></li>
                                                </c:forEach>
                                            </ul>
                                        </li>

                                    </ul>
                                </li>

                            </ul>

                            <!-- Login/Register & Cart Button -->
                            <div class="login-register-cart-button d-flex align-items-center">
                                <!-- Login/Register -->
                                <c:set var="user1" value="${sessionScope.user.username}"/>
                                <c:set var="user2" value="guest@gmail.com"/>
                                <c:if test="${user1 eq user2}">
                                <div class="login-register-btn mr-50">
                                    <a href="/login" id="loginBtn">Login </a>
                                </div>
                                </c:if>
                                <c:if test="${user1 ne user2}">
                                    <div class="login-register-btn mr-50">
                                        <a href="/home?action=logout" id="loginBtn2">Logout </a>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <!-- Nav End -->

                    </div>
                </nav>
            </div>
        </div>
    </div>
</header>