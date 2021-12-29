function login() {
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "../loginServlet" ,//url
        data: $('#LoginForm').serialize(),
        success: function (result) {

            alert(result.responseWord);
            $("#code_img_login").click();//刷新验证码

            if(result.consequence===1){
                window.location.href = "../index.jsp";
            }

        },
        error : function() {
            alert("登录失败！");
            $("#code_img_login").click();//刷新验证码
        }
    });
}
