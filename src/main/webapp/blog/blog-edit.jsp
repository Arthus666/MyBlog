<%--
  Created by IntelliJ IDEA.
  User: Arthus
  Date: 2021/11/22
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>写文章</title>

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

    <link rel="stylesheet" href="https://www.jq22.com/jquery/bootstrap-3.3.4.css">
    <link rel="stylesheet" type="text/css" href="./css/codemirror.min.css"/>
    <link rel="stylesheet" type="text/css" href="./css/style.css"/>
    <script src="https://www.jq22.com/jquery/jquery-3.3.1.js"></script>
    <script src="./js/1.js" type="text/javascript" charset="utf-8"></script>
    <script src="./js/showdown.js" type="text/javascript" charset="utf-8"></script>
    <script src="./js/highlight.pack.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="./css/zenburn.css"/>
    <style type="text/css">
            #htmls pre {
            padding: 0px;
            border-radius: 6px;
        }

        .page-header h3, .panel-heading {
            text-align: center;
        }
    </style>
</head>
<body>

<div class="page-header">
    <h4 style="text-align:center">
        文章标题:<input id="first_title_Input" type="text" style="text-align:center;width: 320px" maxlength="16">
    </h4>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <!--						<a href="https://www.appinn.com/markdown/">Markdown语法</a>-->
                    <small>WRITE</small>
                </div>
                <div class="panel-body" style="padding: 0px;">
                    <textarea class="form-control" id="textareaCode" name="textareaCode"
                              style="display: block; height: 350px;padding: 0px 0px;" onkeyup="run()"></textarea>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <small>SHOW</small>
                </div>
                <div class="panel-body" style="padding: 0px;">
                    <div id="iframewrapper" style="height: 350px;">
                        <div id="htmls"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<h5 style="text-align:center">
    副标题:<input id="second_title_Input" type="text" style="text-align:center;width: 180px" maxlength="16">
    标签：
    <select id="tag_Input" name="tag_type" class="input">
        <option value="C">C</option>
        <option value="C++">C++</option>
        <option value="Java">Java</option>
        <option value="Python">Python</option>
    </select>
</h5>
<div style="text-align:center">
    <button class="main-btn" onclick="publish()">发表</button>
    <button class="main-btn" onclick="backToPerson()">返回</button>
</div>

</body>

<script type="text/javascript">

    window.onresize = function () {
        let w = $(".textarea-group")[0];
        let tw = $("#textareaCode")[0];
        let pw = $(".panel-body").width();
        w.style.width = pw + 'px';
        tw.style.width = pw - 28 + 'px';
    }
    $("#textareaCode").setTextareaCount({
        width: "30px",
        bgColor: "#f5f5f5",
        color: "#282c34",
        display: "block",
        borderColor: "#979797"
    });

    function run() {
        var text = $("#textareaCode")[0].value;
        var ctr = new showdown.Converter();
        var html = ctr.makeHtml(text);
        $("#htmls")[0].innerHTML = html;
        $(".panel-body")[0].style.overflow = "auto";
        $("#iframewrapper")[0].style.overflow = "auto";
        $('pre code').each(function (i, block) {
            hljs.highlightBlock(block);
        });
    }
</script>
<script type="text/javascript" src="../js/blog-edit.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
</html>
