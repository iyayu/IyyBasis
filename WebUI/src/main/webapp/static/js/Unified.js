

$(function () {

    var footer = "<div class=\"pull-right hidden-xs\"><b>Version</b> 2018.1.9.01</div>";
    footer += "<strong><a href=\"https://www.iyayu.cc\">爱雅玉</a></strong>基础平台&nbsp;&nbsp;&nbsp;&nbsp;前端界面基于 <a href=\"https://adminlte.io\">adminlte</a>.";
    $("#footer").html(footer);

    //获取用户信息
    $.ajax({
        url: "/backstage/systemUserAccount/getUserAccountById.do",
        method: "GET",
        timeout: 15000,
        dataType: "JSON",
        beforeSend: function (XMLHttpRequest) {
            //console.log("请求发送之前（发送请求前可修改XMLHttpRequest对象的函数，如添加自定义HTTP头。）");
        },
        success: function (result) {
            console.log(result);
            var data = "";
            if (result["code"] == "200"){
                if (result["data"] == "" || result["data"] == null) {
                    $.message({message: result["msg"], type: 'error'});
                } else {
                    data = result["data"];
                    $("#userInfo_role").html(data[0].systemUserRoleIdOrName + "<small>注册时间：" +  data[0].regTime.split(".")[0] + "</small>");
                    $("#userInfo_name").html(data[0].systemUserAccountName);
                    $("#userInfo_name1").html(data[0].systemUserAccountName);

                }
            } else {
                $.message({message: '返回用户信息异常', type: 'error'});
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $.message({message: '用户信息请求超时', type: 'error'});
        }
    });
});

//退出
function out() {
    $.ajax({
        url: '/logout.out',
        success: function () {
            window.location.reload();
        }
    });
}
