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
    <link rel="stylesheet" href="style.css">
    <script src="https://kit.fontawesome.com/your-kit-id.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
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
                        <input type="search"
                               style="border-radius: 20px; width: 200px;height: 40px; background-color: whitesmoke;border: none; outline: none;"
                               placeholder="   Tìm kiếm bài hát ...">
                        <button
                                style="border: none;border-radius: 50%; background-color: rgb(121, 116, 116);height: 40px;"
                                type="submit" class="btn btn-primary">
                            <i class="fas fa-search"></i>
                        </button>
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
                                <li><a href="songs.jsp">Bài hát</a></li>
                                <li><a href="/playlist">Playlist</a></li>
                                <li><a href="#">Chọn lọc</a>
                                    <ul class="dropdown">
                                        <li><a href="author.jsp">Tác giả</a>
                                            <ul class="dropdown">
                                                <c:forEach items="${authors}" var="author">
                                                    <li><a href="#">${author.name}</a></li>
                                                </c:forEach>
                                            </ul>
                                        </li>
                                        <li><a href="singer.jsp">Ca sĩ</a>
                                            <ul class="dropdown">
                                                <c:forEach items="${singers}" var="singer">
                                                    <li><a href="#">${singer.name}</a></li>
                                                </c:forEach>
                                            </ul>
                                        </li>
                                        <li><a href="category.jsp">Thể loại</a>
                                            <ul class="dropdown">
                                                <c:forEach items="${categories}" var="category">
                                                    <li><a href="#">${category.name}</a></li>
                                                </c:forEach>
                                            </ul>
                                        </li>

                                    </ul>
                                </li>

                            </ul>

                            <!-- Login/Register & Cart Button -->
                            <div class="login-register-cart-button d-flex align-items-center">
                                <!-- Login/Register -->
                                <c:if test="${sessionScope.user == null}">
                                    <div class="login-register-btn mr-50">
                                        <a href="/login" id="loginBtn">Login </a>
                                    </div>
                                </c:if>
                                <c:if test="${sessionScope.user != null}">
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
    <section class="breadcumb-area bg-img bg-overlay" style="background-image: url(img/bg-img/breadcumb3.jpg);">
    <div class="bradcumbContent">
        <p>Your Library</p>
        <h2>Playlist Name</h2>
    </div>
</section>
<!-- ##### Breadcumb Area End ##### -->

<!-- ##### Blog Area Start ##### -->
<div class="blog-area section-padding-100">
    <div class="container">

        <div class="row">

            <div class="col-12 col-lg-9">
                <div class="col-12 col-sm-6 col-md-4 col-lg-2">
                    <c:if test="${songs.size() > 0}">
                        <c:forEach items="${songs}" var="song">
                            <div class="single-album-area wow fadeInUp" data-wow-delay="400ms">
                                <div class="album-thumb">
                                    <img src="https://png.pngtree.com/template/20220609/ourmid/pngtree-music-vinyl-background-record-vector-image_1826055.jpg"
                                         alt="">
                                    <div class="play-icon">
                                        <a class="video--play--btn"><span class="icon-play-button"></span></a>
                                    </div>
                                </div>
                                <div class="album-info">
                                    <a href="#">
                                        <h5>${song.name}</h5>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>

                    <c:if test="${songs.size() == 0 || songs == null}">
                        <p>ADD SONG</p>
                    </c:if>
                </div>

            </div>

            <div class="col-12 col-lg-3">

            </div>
        </div>
    </div>
</div>
<%@ include file="footerU.jsp" %>