function logon() {
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "../logonServlet" ,//url
        data: $('#LogonForm').serialize(),
        success: function (result) {

            alert(result.responseWord);
            $("#code_img_logon").click();//刷新验证码

            if(result.consequence==1){

                window.location.href = "./login.jsp";
            }

        },
        error : function() {
            alert("注册失败！");
            $("#code_img_logon").click();//刷新验证码
        }
    });
}
function backToLogin() {
    window.location.href="./login.jsp";
}
