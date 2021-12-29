function searchBlogs() {

    var search=$('#search_blogs').val();

    //使用ajax查找所有用户所写博客blog并展示
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "../getInfoServlet",//url
        data: {getInfo: "getSearchBlogs", param: search},
        success: function (result) {

            if (result.getSearchBlogs == null) {

                $("#row").html("");//清空原有博客
                $("#load-more2").text("已找到所有匹配内容");

            } else {

                searchBlogView(result.getSearchBlogs);
                $("#load-more2").text("已找到所有匹配内容");
            }

        },
        error: function () {
            console.log("未找到匹配内容");
        }
    });

}

function searchBlogView(blogs) {

    if (blogs.length == 0) {

        $("#row").html("");//清空原有博客
        $("#load-more").hide();
        $("#load-more2").show();
        $("#load-more2").text("已找到所有匹配内容");

    } else {

        $("#row").html("");//清空原有博客

        for (var i = 0; i < blogs.length; i++) {

            let username=getUsernameById(blogs[i].id);
            let blogDate=new Date(blogs[i].time);

            blogs[i].first_title = blogs[i].first_title.substr(0, 16);
            blogs[i].second_title = blogs[i].second_title.substr(0, 16);

            let res=" " ;

            res += '<!-- blog -->\n' +
                '            <div class="col-md-4">\n' +
                '                <div class="blog">\n' +
                '                    <div class="blog-content">\n' +
                '                        <ul class="blog-meta">\n' +
                '                            <a href="../person/person_page.jsp?userId='+ blogs[i].id+'"><li><i class="fa fa-user"></i>' + username + '</li></a>\n' +
                '                            <li><i class="fa fa-clock-o"></i>' + blogDate.toLocaleDateString() + '</li>\n' +
                '                            <li><i class="fa fa-comments"></i>' + 0 + '</li>\n' +
                '                        </ul>\n' +
                '                        <a href="./blog-single.jsp?article_id=' + blogs[i].article_id + '"><h3 style="word-break: break-all">' + blogs[i].first_title + '</h3>'+"<br>" +'\n' +
                '                        <p style="word-break: break-all;color: black">' + blogs[i].second_title + '</p>\n'+ "<br>"+' ' +
                '                        Read more</a>\n' +
                '                    </div>\n' +
                '                </div>\n' +
                '            </div>\n' +
                '            <!-- /blog -->';

            $("#row").append(res);//展现检索博客

            $("#load-more").hide();
            $("#load-more2").show();

        }
    }
}

function getUsernameById(id) {

    let username;
    //使用ajax通过文章中的id获取用户名username并展示
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "../getInfoServlet" ,//url
        data: {getInfo:"getUserNameById",param:id},
        async: false,
        success: function (result) {

            if (result.getUsername != null) {

                username=result.getUsername;

            } else {

                username="Who";
                console.log("未找到用户名");
            }

        },
        error : function() {
            console.log("未找到用户名");
        }
    });

    return username;
}
