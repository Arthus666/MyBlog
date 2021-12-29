$(function () {

    let id = window.location.href.split("userId=")[1];
    let cookieId = $.cookie("id");

    //如果是本人的个人中心则显示写文章按钮
    if (id === cookieId) {
        $("#newBlog").append("<a href=\"../blog/blog-edit.jsp?userId=" + id + "\" " + "" +
            " onclick='markdownClick()'>\n" +
            "<label style=\"color: whitesmoke;font-weight: normal;border: 10px solid lightcoral;border-radius: 10px;background-color: lightcoral\">" +
            "" + "写文章" + "" +
            "</label>\n" + "</a>");

        $("#newBlog").append("<button class=\"main-btn\" onclick=\"logout()\">退出登录</button>\n");
    }

    //使用ajax通过url中的id获取用户名username并展示
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "../getInfoServlet",//url
        data: {getInfo: "getUserNameById", param: id},
        success: function (result) {

            if (result.getUsername != null) {

                $("#showUsername2").html(result.getUsername);

            } else {

                console.log("未找到用户名");

            }

        },
        error: function () {
            console.log("未找到用户名");
        }
    });

    //使用ajax通过url中的id获取用户所写博客blog并展示
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "../getInfoServlet",//url
        data: {getInfo: "getBlogById", param: id},
        success: function (result) {

            showPersonalBlogs(result.getBlogs);

        },
        error: function () {
            console.log("未找到博客");
        }
    });

    //使用ajax通过url中的id获取用户所写博客标签数并展示在分栏
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "../getInfoServlet",//url
        data: {getInfo: "getTagsById", param: id},
        success: function (result) {

            showTagsNumber(result.getTags);

        },
        error: function () {
            console.log("未找到标签");
        }
    });

})

function showPersonalBlogs(blogs) {

    //按时间顺序输出个人博客
    for (let i = blogs.length - 1; i >= 0; i--) {

        let blogDate = new Date(blogs[i].time);

        let temp = "<ul>\n" +
            "                        <a href=../blog/blog-single.jsp?article_id=" + blogs[i].article_id + " style=\"color: black\">\n" +
            "                            <li class=\"blogs\">\n" +
            "                                <h3 class='firstTitle'>" + blogs[i].first_title + "</h3>\n" +
            "                                <p class='secondTitle'>" + blogs[i].second_title + "</p>\n" +
            "                                <p align=\"right\"><i class=\"fa fa-clock-o\" aria-hidden=\"true\" style=\"color: #1A8BE8\"></i>" + blogDate.toLocaleDateString() + "</p>\n" +
            "                            </li><br/></a>\n" +
            "                    </ul>";
        $("#blog_list").append(temp);

    }

}

function showTagsNumber(tags) {

    $("#Tag_C").html(tags[0]);
    $("#Tag_Cpp").html(tags[1]);
    $("#Tag_Java").html(tags[2]);
    $("#Tag_Python").html(tags[3]);

}

function logout() {
    $.removeCookie('id',{ path: '/MyBlog'}); //path为指定路径，直接删除该路径下的cookie
    alert("已退出登录");
    window.location.href = "../index.jsp";
}

//写博客
function markdownClick() {
    window.location.href = "../blog/blog-edit.jsp?userId=" + id;
}
