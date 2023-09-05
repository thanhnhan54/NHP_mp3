<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<style>
    .song-play-area > .audioplayer-time,
    .song-play-area > .audioplayer-playpause,
    .song-play-area > .audioplayer-time
    .song-play-area > .audioplayer-volume {
        display: none;
    }

    .song-play-area {

        width: 100% !important;
    }

    #musicSingle {
        display: none;
        width: 100%;
    }

    .audioplayer-playpause.active {
        color: #867878;
    }

    .pagination {
        list-style-type: none;
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 0;
    }

    .pagination-item {
        display: inline-block;
        margin: 0 5px;
    }

    .pagination-link {
        display: inline-block;
        padding: 5px 10px;
        text-decoration: none;
        background-color: #e0e0e0;
        color: #333;
        border-radius: 5px;
    }

    .pagination-link:hover {
        background-color: #ccc;
    }

    .pagination-link.active {
        background-color: #333;
        color: #fff;
    }

</style>
<%@ include file="headerU.jsp" %>
<!-- ##### Breadcumb Area Start ##### -->
<section class="breadcumb-area bg-img bg-overlay" style="background-image: url(img/bg-img/breadcumb3.jpg);">
    <div class="bradcumbContent">
        <p>See what’s new</p>
        <h2>Latest Albums</h2>
    </div>
</section>
<!-- ##### Breadcumb Area End ##### -->

<!-- ##### Album Catagory Area Start ##### -->
<c:if test="${requestScope['song'].size() != 0}">
    <section class="album-catagory section-padding-100-0">
        <div class="container">
            <div class="row oneMusic-albums">
                <c:forEach items="${songs}" var="song">
                    <div style="height: 300px" class="col-12 col-sm-4 col-md-3 col-lg-2 single-album-item t c p">
                        <div style="height: 280px" class="single-album">
                            <img style="height: 60%" src="${song.image}" alt="">
                            <div class="album-info">
                                <a href="javascript:void(0);" onclick="playSong(${song.id})">
                                    <h5>${song.name}</h5>
                                </a>
                                <p>${song.singer.name}</p>
                                <c:forEach items="${histories}" var="his">
                                    <c:if test="${his.song.id == song.id}">
                                        <p>Lượt xem: ${his.view}</p>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <ul class="pagination">
            <div style="text-align: center">
                <c:forEach begin="1" end="${pageable.totalPage}" var="page">
                    <li class="pagination-item">
                        <a class="pagination-link"
                           href="/list_songs?page=${page}&search=${pageable.search}&sortBy=${pageable.sortBy}&nameField=${pageable.nameField}">${page}</a>
                    </li>
                </c:forEach>
            </div>
        </ul>
    </section>
</c:if>

<div class="container-fluid">
    <div class="row">
        <!-- Single Song Area -->
        <div class="col-12">

            <div class="song-play-area" id="musicSingle">

            </div>

        </div>
    </div>
</div>


<script>

    window.addEventListener('scroll', scrolled);

    function scrolled(event) {
        var audioContainer = document.getElementById('musicSingle');
        // var footer = document.getElementById("footer");
        // var footerY = footer.scrollHeight ;
        var scrollY = window.scrollY;
        if (scrollY >= 500) {
            audioContainer.style.marginBottom = '0px';
        } else {
            audioContainer.style.marginBottom = '0px';

        }


    }

    // Lấy các phần tử cần sử dụng

    var progressBar = document.getElementById('progressBar');

    <% String products = (String) request.getAttribute("songsJSON"); %>
    let songs = <%= products %>;
    var songSelected;

    function playSong(id) {


        songSelected = songs.find(e => e.id === id);
        playAudio();
        showAudioPlayer();
    }

    function playAudio() {
        musicSingle.innerHTML = '';
        let str = `<div class="song-name" >
                            <p id="nameSong">\${songSelected.name}</p>
                        </div>
                        <div class="d-flex justify-content-between">
                            <button type="button" onclick="prev()" class="audioplayer-playpause">
                                <i class="fas fa-backward"></i>
                            </button>
                            <button type="button" onclick="next()" class="audioplayer-playpause">
                                <i class="fas fa-forward"></i>
                            </button>

                            <audio style="width: 80%;" id="audioPlayer1" preload="auto" controls onplaying="getView(\${songSelected.id})" onpause="getDuration()">
                                <source id="srcSong" src="\${songSelected.song}">
                            </audio>
                            <button type="button" onclick="toggleRandom()" class="audioplayer-playpause">
                                <i class="fas fa-random"></i>
                            </button>
                        </div>`;
        musicSingle.innerHTML = str;
        var audioPlayer = document.getElementById('audioPlayer1');
        audioPlayer.play();
    }

    function next() {
        if (isRandom) {
            const randomIndex = Math.floor(Math.random() * songs.length);
            songSelected = songs[randomIndex];
        } else {
            for (let i = 0; i < songs.length; i++) {
                if (songs[i].id === songSelected.id) {
                    songSelected = songs[(i + 1) % songs.length];
                    break;
                }
            }
        }
        playAudio();
    }

    function prev() {
        if (isRandom) {
            const randomIndex = Math.floor(Math.random() * songs.length);
            songSelected = songs[randomIndex];
        } else {
            for (let i = 0; i < songs.length; i++) {
                if (songs[i].id === songSelected.id) {
                    songSelected = songs[(i - 1 + songs.length) % songs.length];
                    break;
                }
            }
        }
        playAudio();
    }

    let isRandom = false;


    function toggleRandom() {
        isRandom = !isRandom;
        var button = document.querySelector('.audioplayer-playpause');
        button.classList.toggle('active');
    }


    function showAudioPlayer() {
        var audioContainer = document.getElementById('musicSingle');
        audioContainer.style.display = 'block';
        audioContainer.style.position = 'fixed';
        audioContainer.style.bottom = '0';
        audioContainer.style.left = '0';
        audioContainer.style.width = '100%';
        audioContainer.style.zIndex = '100';

        var footer = document.getElementById("footer");
        var footerY = footer.scrollHeight;
        var scrollY = window.scrollY;
        if (scrollY >= 500) {
            audioContainer.style.marginBottom = '0px';
        }


    }

    var countSecond;
    var startDate;
    var pauseDate = 0;
    var timeOut;

    // ?action=view&+
    function getView(idVideo) {
        startDate = new Date();
        timeOut = setTimeout(() => {
            $.ajax({
                url: 'http://localhost:8080/api?action=view&id=' + idVideo,
                method: 'GET'
            }).done(data => {
                videos = data;
                pauseDate = 0;
            }, (60 - pauseDate) * 1000)
        }
    }

    function getDuration() {
        clearTimeout(timeOut);
        let diff = (new Date() - startDate);
        pauseDate = Math.floor((diff / 1000));
    }
</script>

<%@ include file="footerU.jsp" %>



