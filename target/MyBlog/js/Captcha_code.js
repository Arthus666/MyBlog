$(function () {
    $("#code_img_logon").click(function () {
        this.src="../captcha"+"?d="+new Date();
    });
    $("#code_img_login").click(function () {
        this.src="../captcha"+"?d="+new Date();
    });
    $("#code_img_back_login").click(function () {
        this.src="../captcha"+"?d="+new Date();
    });
});
