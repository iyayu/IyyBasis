var _menus = "";

$(function () {
    $("body").show();
    NProgress.start();
    $('.sidebar-menu').tree();

    $.ajax({
        url: "/backstage/systemUserAccount/getMenu.do",
        type: 'GET',
        timeout: 15000,
        dataType: "JSON",
        beforeSend: function (XMLHttpRequest) {
            //console.log("请求发送之前（发送请求前可修改XMLHttpRequest对象的函数，如添加自定义HTTP头。）");
        },
        success: function (data) {
            console.log(data);

            if (data["code"] != "200") {
                $.message({message: '哦天啊, 平台出错了, 联系管理员吧', type: 'error'});
                return;
            }

            if (data["count"] != "0") {
                _menus = data["data"];
                InitLeftMenu();
            } else {
                $.message({message: '哦天啊, 平台返回了未知的数据', type: 'error'});
            }

            NProgress.done();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("请求失败时调用此函数");
            $.message({message: '菜单请求超时', type: 'error'});
            NProgress.done();
        }
    });
});

//初始化左侧
function InitLeftMenu() {
    //$("#nav").accordion({animate: false});
    var dMenu = "<li class=\"header\">您的导航菜单</li>";

    $.each(_menus, function (i, n) {
        // 没有子菜单
        if (n["childMenus"] == null || n["childMenus"] == "") {
            dMenu += '<li>';
            dMenu += '   <a href="' + n.url + '">';
            dMenu += '      <i class="fa ' + n.icon + '"></i>';
            dMenu += '      <span>' + n.name + '</span>';
            dMenu += '      <span class="pull-right-container"></span>';
            dMenu += '   </a>';
            dMenu += '</li>';

            // 有子菜单
        } else {
            dMenu += '<li class="treeview">';
            dMenu += '   <a href="#">';
            dMenu += '      <i class="fa ' + n.icon + '"></i>';
            dMenu += '      <span>' + n.name + '</span>';
            dMenu += '      <span class="pull-right-container">';
            dMenu += '          <i class="fa fa-angle-left pull-right"></i>';
            dMenu += '      </span>';
            dMenu += '   </a>';

            dMenu += '   <ul class="treeview-menu">';

            //二级菜单获取
            dMenu += getChildMenus(n.id, n["childMenus"]);

            dMenu += '   </ul>';
            dMenu += '</li>';
        }
    });

    //添加菜单
    $("#dMenu").html(dMenu);
    //console.log(dMenu);
}

//获取子菜单集合
//父级菜单id, childMenus子菜单集合
function getChildMenus(parentId, childMenus) {
    var childMenu = '';

    $.each(childMenus, function (j, o) {
        childMenu += '<li><a href="' + o.url + '"><i class="fa fa-circle-o"></i>' + o.name + '</a></li>';
    });

    return childMenu;
}