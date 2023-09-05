<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="headerU.jsp" %>

<section class="breadcumb-area bg-img bg-overlay" style="background-image: url(img/bg-img/breadcumb3.jpg);">
    <div class="bradcumbContent">

        <h2>Login</h2>
    </div>
</section>
<!-- ##### Breadcumb Area End ##### -->

<!-- ##### Login Area Start ##### -->
<section class="login-area section-padding-100">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-lg-8">
                <div class="login-content">
                    <h3>Welcome Back</h3>
                    <!-- Login Form -->
                    <div class="login-form">
                        <form action="#" method="post">
                            <div class="form-group">
                                <label for="exampleInputEmail1">Tài khoản</label>
                                <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter E-mail" name="username">
                                <small id="emailHelp" class="form-text text-muted"><i class="fa fa-lock mr-2"></i>We'll never share your email with anyone else.</small>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Mật khẩu</label>
                                <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="password">
                            </div>
                            <button type="submit" class="btn oneMusic-btn mt-30">Login</button>
                            <a href="/signup"><button type="button" class="btn oneMusic-btn mt-30" >Sign Up</button></a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="footerU.jsp" %>
