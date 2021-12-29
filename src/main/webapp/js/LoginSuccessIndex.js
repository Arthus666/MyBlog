// onload动态注册事件
window.onload = function () {

    let cookieId=$.cookie("id");

    if (cookieId==undefined) {

        $("#showUsername").hide();
        $("#login_logon").show();

    } else{

        //使用ajax通过url中的id获取用户名username
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "./getInfoServlet" ,//url
            data: {getInfo:"getUserNameById",param:cookieId.toString()},
            success: function (result) {

                if (result.getUsername != null) {

                    $("#showUsername").html(result.getUsername);
                    $("#login_logon").hide();
                    $("#showUsername").attr("href", "./person/person_page.jsp?userId=" + cookieId.toString());

                } else {
                    $("#showUsername").hide();
                    $("#login_logon").show();
                    console.log("未找到用户名");
                }

            },
            error : function() {
                console.log("未找到用户名");
            }
        });

    }

}
