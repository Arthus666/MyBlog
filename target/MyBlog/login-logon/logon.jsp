<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>注册</title>

    <link rel="shortcut icon" href="../img/favicon.ico">


    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700%7CVarela+Round" rel="stylesheet">

    <!-- Bootstrap -->
    <link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css" />

    <!-- Owl Carousel -->
    <link type="text/css" rel="stylesheet" href="../css/owl.carousel.css" />
    <link type="text/css" rel="stylesheet" href="../css/owl.theme.default.css" />

    <!-- Magnific Popup -->
    <link type="text/css" rel="stylesheet" href="../css/magnific-popup.css" />

    <!-- Font Awesome Icon -->
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">

    <!-- Custom stlylesheet -->
    <link type="text/css" rel="stylesheet" href="../css/style.css" />

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="../js/Captcha_code.js"></script>
    <script type="text/javascript" src="../js/LogonClick.js"></script>

    <title>注册</title>

</head>
<body>

<a href="../index.jsp">
    &nbsp;
    <i class="fa fa-reply"></i>
</a>

<!-- Contact -->
<div id="contact" class="section md-padding">

    <!-- Container -->
    <div class="container">

        <!-- Row -->
        <div class="row">

            <!-- Section-header -->
            <div class="section-header text-center">
                <h2 class="title">Get Registered</h2>
            </div>
            <!-- /Section-header -->

            <!-- contact form -->
            <div class="col-md-8 col-md-offset-2">
                <form id="LogonForm" class="contact-form" onsubmit="return false" action="##" method="post">
                    <h5>账户: <input name="username" type="text" class="input" placeholder="数字字母下划线3~16位" maxlength="16"></h5>
                    <h5>密码: <input name="password" type="password" class="input" placeholder="数字字母下划线6~16位" maxlength="16"></h5>
                    <h5>确认: <input name="confirm" type="password" class="input" placeholder="确认密码" maxlength="16"></h5>
                    <h5>邮箱: <input name="email" type="email" class="input" placeholder="个人邮箱" maxlength="256"></h5>
                    <h5>验证码:
                        <input name="captcha_code" type="text" class="input" style="width: 243px" placeholder="请输入验证码" maxlength="4">
                        <img id="code_img_logon" src="../captcha" style="width:100px;height:40px">
                    </h5>
                    <input class="main-btn" type="button" value="注册" onclick="logon()">
                    <input id="logonBack" class="main-btn" type="button" value="返回" onclick="backToLogin()">
                </form>
            </div>
            <!-- /contact form -->

        </div>
        <!-- /Row -->

    </div>
    <!-- /Container -->

</div>
<!-- /Contact -->

</body>
</html>
