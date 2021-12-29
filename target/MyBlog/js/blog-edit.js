function backToPerson() {

    let id=window.location.href.split("userId=")[1];
    window.location.href="../person/person_page.jsp?userId="+id;

}
function publish() {

    let id=window.location.href.split("userId=")[1];
    let cookieId=$.cookie("id");

    let first_title_Input=$("#first_title_Input").val();
    let second_title_Input=$("#second_title_Input").val();
    let tag_Input=$("#tag_Input").val();

    let content=$("#textareaCode").val();

    if (id === cookieId) {

        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "../operateInfoServlet" ,//url
            data: {operateInfo:"insertBlog",id:id,first_title_Input:first_title_Input,second_title_Input:second_title_Input,tag_Input:tag_Input,content:content},
            success: function (result) {

                alert(result.consequence);
                if (result.consequence==="发表成功！") {

                    window.location.href="../person/person_page.jsp?userId="+id;

                }

            },
            error : function() {

                alert("发表失败！");

            }
        });
    }
}
