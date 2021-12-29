<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>博客空间</title>

    <link rel="shortcut icon" href="../img/favicon.ico">

    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700%7CVarela+Round" rel="stylesheet">

    <!-- Bootstrap -->
    <link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css"/>

    <!-- Owl Carousel -->
    <link type="text/css" rel="stylesheet" href="../css/owl.carousel.css"/>
    <link type="text/css" rel="stylesheet" href="../css/owl.theme.default.css"/>

    <!-- Magnific Popup -->
    <link type="text/css" rel="stylesheet" href="../css/magnific-popup.css"/>

    <!-- Font Awesome Icon -->
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">

    <!-- Custom stlylesheet -->
    <link type="text/css" rel="stylesheet" href="../css/style.css"/>

    <style>
        .editormd-html-preview {
            width: 90%;
            margin: 0 auto;
        }
    </style>
    <style type="text/css">
        .normal {
            /*background-image: url(../img/blog_show.jpg);*/
            width: 110px;
            height: 110px;
            border-radius: 50%;
            display: block;
            margin: 0 auto;
        }

        .blogs {
            width: 100%;
            height: 120px;
            border-radius: 15px;
            cursor: pointer;
            /*border: 1px #C1C144 solid;*/
            padding: 5px 5px 5px 25px;
            background-color: #FAFAFA;
            /*background-color: #F8F9F8;*/
            /*position: absolute;*/
            top: 0;
            left: 0;
            z-index: 5;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .firstTitle {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .secondTitle {
            /*height: 40px;*/
            /*width:200px;*/
            font-size: small;
            overflow: hidden;
            /* white-space: nowrap; */
            text-overflow: ellipsis;
            -webkit-line-clamp: 2;
            word-break: break-all;
            display: -webkit-box;
            -webkit-box-orient: vertical;
        }
    </style>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../js/owl.carousel.min.js"></script>
    <script type="text/javascript" src="../js/jquery.magnific-popup.js"></script>
    <script type="text/javascript" src="../js/main.js"></script>
    <script type="text/javascript" src="../js/LoginSuccess.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <script type="text/javascript" src="../js/blog-grid.js"></script>
    <script type="text/javascript" src="../js/blog-search.js"></script>
</head>

<body>

<!-- Header -->
<header>

    <!-- Nav -->
    <nav id="nav" class="navbar">
        <div class="container">

            <div class="navbar-header">

                <!-- Collapse nav button -->
                <div class="nav-collapse">
                    <span></span>
                </div>
                <!-- /Collapse nav button -->
            </div>

            <!--  Main navigation  -->
            <ul class="main-nav nav navbar-nav navbar-right">
                <li><a href="../index.jsp">首页</a></li>
                <li><a href="../blog/blog-grid.jsp">博客</a></li>
                <li>
                    <a id="login_logon" href="../login-logon/login.jsp">登录/注册</a>
                    <a id="showUsername" href=""></a>
                </li>
            </ul>
            <!-- /Main navigation -->

        </div>
    </nav>
    <!-- /Nav -->

</header>
<!-- /Header -->

<aside id="aside" style="width: 40%;height: 70%;margin: 0 auto">
    <!-- Search -->
    <div class="widget">
        <div class="widget-search">
            <input id="search_blogs" class="search-input" type="text" placeholder="Search Anything" style="background-color: lightgray" maxlength="16">
            <button class="search-btn" type="button" onclick="searchBlogs()"><i class="fa fa-search"></i></button>
        </div>
    </div>
    <!-- /Search -->
</aside>

<!-- Blog -->
<div id="blog" class="section md-padding bg-grey">
    <!-- Container -->
    <div class="container">

        <!-- Row -->
        <div class="row" id="row">

            <!-- Section header -->
            <div class="section-header text-center">
                <h2 id="Blog_Space" class="title">Blog Space</h2>
            </div>
            <!-- /Section header -->

        </div>
        <!-- /Row -->

        <div style="text-align: center;cursor:pointer;color: deepskyblue">
            <span id="load-more" style="font-size: large" onclick="showMoreBlog()">加载更多</span>
        </div>

        <div style="text-align: center;cursor:pointer;color: deepskyblue">
            <span id="load-more2" style="font-size: large" hidden></span>
        </div>

        <!-- /Container -->

    </div>
    <!-- /Blog -->

</div>
<%--Blog--%>

<!-- Footer -->
<footer id="footer" class="sm-padding bg-dark">

    <!-- Container -->
    <div class="container">

        <!-- Row -->
        <div class="row">

            <div class="col-md-12">

                <!-- footer logo -->
                <div class="footer-logo">
                    <a href="../index.jsp"><img src="../img/logo.png" alt="logo"></a>
                </div>
                <!-- /footer logo -->
            </div>

        </div>
        <!-- /Row -->


    </div>
    <!-- /Container -->

</footer>
<!-- /Footer -->

</body>

</html>
