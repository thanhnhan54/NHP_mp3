<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>BLOCKS - Bootstrap Dashboard Theme</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Carlos Alvarez - Alvarez.is">

    <!-- Le styles -->
    <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.min.css"/>

    <link href="../css/login.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Tilt+Prism&display=swap" rel="stylesheet">

    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" src="../bootstrap/js/bootstrap.min.js"></script>

    <style type="text/css">
        body {
            padding-top: 30px;
        }

        pbfooter {
            position: relative;
        }
        @import url('//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css');
        * {
            padding: 0px;
            margin: 0px;
        }
        body{
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            background: #00071C;
        }
        ul {
            position: relative;
            display: flex;
            transform: rotate(-1deg) skew(25deg);
            transform-style: preserve-3d;
        }
        ul li {
            position: relative;
            list-style: none;
            width: 60px;
            height: 60px;
            margin: 0px 20px;
        }
        ul li:before{
            content: '';
            position: absolute;
            bottom: -10px;
            left: -5px;
            width: 100%;
            height: 10px;
            background: #2a2a2a;
            trnasform-origin: top;
            transform: skewX(-41deg);
        }
        ul li:after{
            content: '';
            position: absolute;
            top:5px;
            left: -9px;
            width: 9px;
            height: 100%;
            background: #2a2a2a;
            trnasform-origin: right;
            transform: skewY(-49deg);
        }
        ul li span{
            position: absolute;
            top: 0;
            lef: 0;
            width: 100%;
            height: 100%;
            display: flex !important;
            background: #2a2a2a;
            justify-content: center;
            align-items: center;
            color: #fff;
            font-size: 16px !important;
            transition: 1.5s ease-out;
        }
        ul li:hover span {
            z-index: 1000;
            transition: .3s;
            color: #fff;
            box-shadw: -1px 1px 1px rgba(0, 0, 0, .5);
        }
        ul li:hover span:nth-child(5){
            transform: translate(40px, -40px);
            opacity: 1;
        }
        ul li:hover span:nth-child(4){
            transform: translate(30px, -30px);
            opacity: .8;
        }
        ul li:hover span:nth-child(3){
            transform: translate(20px, -20px);
            opacity: .6;
        }
        ul li:hover span:nth-child(2){
            transform: translate(10px, -10px);
            opacity: .4;
        }ul li:hover span:nth-child(1){
             transform: translate(0px, 0px);
             opacity: .2;
         }
        ul li:nth-child(1):hover span{
            background: #52E19F !important;
        }
        ul li:nth-child(2):hover span{
            background: #2C3456 !important;
        }
        ul li:nth-child(3):hover span{
            background: #EA6E96 !important;
        }
        ul li:nth-child(4):hover span{
            background: #FCEB00 !important;
        }
    </style>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Google Fonts call. Font Used Open Sans & Raleway -->
    <link href="http://fonts.googleapis.com/css?family=Raleway:400,300" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">

    <!-- Jquery Validate Script -->
    <script type="text/javascript" src="../js/jquery.validate.js"></script>

    <!-- Jquery Validate Script - Validation Fields -->
    <script type="text/javascript">



        $().ready(function () {
            // validate the comment form when it is submitted
            $("#commentForm").validate();

            // validate signup form on keyup and submit
            $("#signupForm").validate({
                rules: {
                    firstname: "required",
                    lastname: "required",
                    username: {
                        required: true,
                        minlength: 1
                    },
                    password: {
                        required: true,
                        minlength: 1
                    },
                    confirm_password: {
                        required: true,
                        minlength: 2,
                        equalTo: "#password"
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    topic: {
                        required: "#newsletter:checked",
                        minlength: 2
                    },
                    agree: "required"
                },
                messages: {
                    firstname: "Please enter your firstname",
                    lastname: "Please enter your lastname",
                    username: {
                        required: "Please enter a username",
                        minlength: "Your username must consist of at least 1 character"
                    },
                    password: {
                        required: "Please provide a password",
                        minlength: "Your password must be at least 1 character long"
                    },
                    confirm_password: {
                        required: "Please provide a password",
                        minlength: "Your password must be at least 5 characters long",
                        equalTo: "Please enter the same password as above"
                    },
                    email: "Please enter a valid email address",
                    agree: "Please accept our policy"
                }
            });

            // propose username by combining first- and lastname
            $("#username").focus(function () {
                var firstname = $("#firstname").val();
                var lastname = $("#lastname").val();
                if (firstname && lastname && !this.value) {
                    this.value = firstname + "." + lastname;
                }
            });

            //code to hide topic selection, disable for demo
            var newsletter = $("#newsletter");
            // newsletter topics are optional, hide at first
            var inital = newsletter.is(":checked");
            var topics = $("#newsletter_topics")[inital ? "removeClass" : "addClass"]("gray");
            var topicInputs = topics.find("input").attr("disabled", !inital);
            // show when newsletter is checked
            newsletter.click(function () {
                topics[this.checked ? "removeClass" : "addClass"]("gray");
                topicInputs.attr("disabled", !this.checked);
            });
        });
    </script>

</head>

<style>

    .pbfooter {
        position: relative;
    }

</style>

<body style="background:url('https://i.pinimg.com/564x/c5/95/4c/c5954ce81bd89cd1170d5bf57b74cb5b.jpg') no-repeat fixed center ; height:700px;">

<!-- NAVIGATION MENU -->

<%--<div class="navbar-nav navbar-inverse navbar-fixed-top">--%>
<%--    <div class="container">--%>
<%--        <div class="navbar-header">--%>
<%--            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">--%>
<%--                <span class="icon-bar"></span>--%>
<%--                <span class="icon-bar"></span>--%>
<%--                <span class="icon-bar"></span>--%>
<%--            </button>--%>
<%--            <a class="navbar-brand" href="/home"> NHP mp3</a>--%>
<%--        </div>--%>
<%--        <div class="navbar-collapse collapse">--%>
<%--&lt;%&ndash;            <ul class="nav navbar-nav">&ndash;%&gt;--%>
<%--&lt;%&ndash;                <li class="active"><a href="/home"><i class="icon-home icon-white"></i> Home</a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;&lt;%&ndash;                <li><a href="/admin/songs"><i class="icon-th icon-white"></i> Song Management</a></li>&ndash;%&gt;&ndash;%&gt;--%>
<%--&lt;%&ndash;&lt;%&ndash;                <li><a href="/admin/users"><i class="icon-user icon-white"></i> User Management</a></li>&ndash;%&gt;&ndash;%&gt;--%>
<%--&lt;%&ndash;                <li><a href="/login"><i class="icon-user icon-white"></i> Login</a></li>&ndash;%&gt;--%>

<%--&lt;%&ndash;            </ul>&ndash;%&gt;--%>
<%--        </div><!--/.nav-collapse -->--%>
<%--    </div>--%>
<%--</div>--%>

<div class="container">
    <div class="row">
        <div class="col-lg-offset-4 col-lg-4" style="margin-top:100px">
            <h1 class="text-danger text-center" style="font-size: 46px;font-family: 'Tilt Prism', cursive; color: whitesmoke"><strong> WELLCOME TO NHP MP3 WEBSITE</strong></h1>
            <div class="block-unit" style="text-align:center; padding:8px 8px 8px 8px;">
                <form class="cmxform" id="signupForm" method="post" action="/login">

                    <fieldset>
                        <h2 class="text-center text-primary" style="font-size: 40px; color: whitesmoke">Login</h2>
                        <p style="margin-bottom: 40px">
                            <input class="form-control-static" id="username" name="username" type="text" placeholder="Username">
                            <input class="form-control-static" id="password" name="password" type="password" placeholder="Password">
                        </p>
<%--                        <input class="submit btn-success btn btn-large" type="submit" value="Login">--%>
                        <div class="row d-flex mr-3" style="margin-bottom: 20px">
                            <div>
                                <ul style="justify-content: center">
                                    <li>
                                    <button style="width: 100%;height: 100%; background: #2a2a2a" class="submit btn-success btn btn-large">login
                                    </button>
                                    </li>
                                    <li>
                                        <a href="/signup">
                                            <button type="button" style="width: 100%;height: 100%; background: #2a2a2a" class="submit btn-success btn btn-large">signup
                                            </button>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>


                    </fieldset>

                </form>


            </div>

        </div>


    </div>
</div>


<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script type="text/javascript" src="../js/bootstrap.js"></script>


</body>
</html>