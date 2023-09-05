<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="headerU.jsp" %>
<section class="breadcumb-area bg-img bg-overlay" style="background-image: url(img/bg-img/breadcumb3.jpg);">
    <div class="bradcumbContent">
        <p>Your Library</p>
        <h2>Playlists</h2>
    </div>
</section>
<!-- ##### Breadcumb Area End ##### -->

<!-- ##### Blog Area Start ##### -->
<div class="blog-area section-padding-100">
    <div class="container">
        <div class="row" >
            <form>
                <input type="text" value="My Playlist">
                <button>Create</button>
            </form>


        </div>
        <div class="row">

            <div class="col-12 col-lg-9">
                <div class="col-12 col-sm-6 col-md-4 col-lg-2">
                    <c:if test="${playlists.size() > 0}">
                    <c:forEach items="${playlists}" var="playlist">
                    <div class="single-album-area wow fadeInUp" data-wow-delay="400ms">
                        <div class="album-thumb">
                            <img src="https://png.pngtree.com/template/20220609/ourmid/pngtree-music-vinyl-background-record-vector-image_1826055.jpg"
                                 alt="">
                            <div class="play-icon">
                                <a href="/playlist?action=showListSong&id=${playlist.id}" class="video--play--btn"><span class="icon-play-button"></span></a>
                            </div>
                        </div>
                        <div class="album-info">
                            <a href="#">
                                <h5>${playlist.name}</h5>
                            </a>
                        </div>
                    </div>
                    </c:forEach>
                    </c:if>
                    <c:if test="${playlists == null}">
                        <div class="single-album-area wow fadeInUp" data-wow-delay="400ms">
                            <div class="album-thumb">
                                <img src="https://png.pngtree.com/template/20220609/ourmid/pngtree-music-vinyl-background-record-vector-image_1826055.jpg"
                                     alt="">
                                <div class="play-icon">
                                    <a href="/JSPhomeUser/userplaylist.jsp" class="video--play--btn"><span class="icon-play-button"></span></a>
                                </div>
                            </div>
                            <div class="album-info">
                                <a href="#">
                                    <h5>Playlist 1</h5>
                                </a>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${playlists.size() == 0}">
                        <div class="single-album-area wow fadeInUp" data-wow-delay="400ms">
                            <div class="album-thumb">
                                <img src="https://png.pngtree.com/template/20220609/ourmid/pngtree-music-vinyl-background-record-vector-image_1826055.jpg"
                                     alt="">
                                <div class="play-icon">
                                    <a href="/JSPhomeUser/userplaylist.jsp" class="video--play--btn"><span class="icon-play-button"></span></a>
                                </div>
                            </div>
                            <div class="album-info">
                                <a href="#">
                                    <h5>Playlist 1</h5>
                                </a>
                            </div>
                        </div>
                    </c:if>
                </div>

            </div>

            <div class="col-12 col-lg-3">

            </div>
        </div>
    </div>
</div>
<%@ include file="footerU.jsp" %>