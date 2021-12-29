$(function () {

    let article_id = window.location.href.split("article_id=")[1];

    //使用ajax通过url中的article_id获取文章并展示
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "../getInfoServlet",//url
        data: {getInfo: "getBlogByArticleId", param: article_id},
        success: function (result) {

            if (result.getBlog != null) {

                let blogDate = new Date(result.getBlog.time);

                justShowUsername(result.getBlog.id);
                justShowTagsNumber(result.getBlog.id);
                $('#first_title').html(result.getBlog.first_title);
                $('#second_title').html(result.getBlog.second_title);
                $('#dateTime').append(blogDate.toLocaleDateString());
                $('#showTags').append(result.getBlog.tag);
                showMdType(result.getBlog);
            } else {

                console.log("未找到该博客");

            }

        },
        error: function () {
            console.log("未找到该博客");
        }
    });

    justShowComments(article_id);

})

function justShowUsername(id) {

    //使用ajax通过文章中的id获取用户名username并展示
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "../getInfoServlet",//url
        data: {getInfo: "getUserNameById", param: id},
        success: function (result) {

            if (result.getUsername != null) {

                $("#showUsername2").html(result.getUsername);
                $('#right_down').append(result.getUsername);


            } else {

                console.log("未找到用户名");

            }

        },
        error: function () {
            console.log("未找到用户名");
        }
    });
}

function justShowTagsNumber(id) {

    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "../getInfoServlet",//url
        data: {getInfo: "getTagsById", param: id},
        success: function (result) {

            if (result.getTags != null) {
                showTagsNumber(result.getTags);
            }

        },
        error: function () {
            console.log("未找到标签");
        }
    });

}

function showTagsNumber(tags) {

    $("#Tag_C").html(tags[0]);
    $("#Tag_Cpp").html(tags[1]);
    $("#Tag_Java").html(tags[2]);
    $("#Tag_Python").html(tags[3]);

}

//将内容解析展示为markdown格式
function showMdType(blog) {

    var converter = new showdown.Converter();
    var blog_content = converter.makeHtml(blog.content);
    $('#blog_content').html(blog_content);
    $('pre code').each(function (i, block) {
        hljs.highlightBlock(block);
    });
}

function makeComments() {

    let article_id = window.location.href.split("article_id=")[1];

    let userId = $.cookie("id");

    if (userId == undefined) {

        alert("请先登录！");

    } else {

        let comment_contents = $("#comment_contents").val();

        if (comment_contents == "") {

            alert("评论不能为空！");

        } else {

            $.ajax({
                //几个参数需要注意一下
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "../operateInfoServlet",//url
                data: {
                    operateInfo: "insertComment",
                    article_id: article_id,
                    userId: userId,
                    comment_contents: comment_contents
                },
                success: function (result) {

                    let date=new Date();
                    // alert(result.consequence);
                    // window.location.href = "../blog/blog-single.jsp?article_id=" + article_id;
                    showAppendComment(userId,comment_contents);
                },
                error: function () {

                    alert("发表失败！");

                }
            });
        }

    }

}

function justShowComments(article_id) {

    //使用Ajax获取文章评论并展示
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "../getInfoServlet",//url
        data: {getInfo: "getCommentsByArticleId", param: article_id},
        success: function (result) {

            if (result != null) {
                showEachComment(result.getComments);
            } else {

                console.log("未找到该评论");

            }

        },
        error: function () {
            console.log("未找到该评论");
        }
    });

}

function showEachComment(comments) {

    for (let i = 0; i < comments.length; i++) {

        //使用ajax通过文章中的id获取用户名username并展示
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "../getInfoServlet",//url
            async: false,
            data: {getInfo: "getUserNameById", param: comments[i].user_id},
            success: function (result) {

                if (result.getUsername != null) {

                    let blogDate = new Date(comments[i].comment_time);

                    let temp = "<span style=\"color: black\" id=\"comment_user\">" + result.getUsername + ":&nbsp;&nbsp;&nbsp;</span>\n" +
                        "<span style=\"color: black;font-size: large\" id=\"comment_content\">" + comments[i].user_comment + "</span><br>\n" +
                        "<span style=\"font-size: small\">" + blogDate.toLocaleDateString() + "</span><br>";

                    $("#Blog_comments").append(temp);

                } else {

                    console.log("未找到用户名");

                }

            },
            error: function () {
                console.log("未找到用户名");
            }
        });

    }

}

function showAppendComment(user_id,user_comment) {

    //使用ajax通过文章中的id获取用户名username并展示
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "../getInfoServlet",//url
        data: {getInfo: "getUserNameById", param: user_id},
        success: function (result) {

            if (result.getUsername != null) {

                let blogDate = new Date();

                let temp = "<span style=\"color: black\" id=\"comment_user\">" + result.getUsername + ":&nbsp;&nbsp;&nbsp;</span>\n" +
                    "<span style=\"color: black;font-size: large\" id=\"comment_content\">" + user_comment + "</span><br>\n" +
                    "<span style=\"font-size: small\">" + blogDate.toLocaleDateString() + "</span><br>";
                $("#Blog_comments").append(temp);
                $("#comment_contents").val("");


            } else {

                console.log("未找到用户名");

            }

        },
        error: function () {
            console.log("未找到用户名");
        }
    });

}
