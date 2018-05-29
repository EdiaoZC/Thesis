layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery', 'laypage'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;

    //加载页面数据
    var usersData = '';

    function loadRoles() {
        $.get("/permission", function (data) {
            usersData = data;
            deviceList();
        })
    }

    $(".permissionAdd_btn").click(function () {
        var index = layui.layer.open({
            title: "添加角色",
            type: 2,
            content: "addPermission.html",
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        });
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);
    });


    $("body").on("click", ".users_edit", function () {
        var _this = $(this);
        var index = layui.layer.open({
            title: "修改成员",
            type: 2,
            content: "editPermission.html?id=" + _this.attr("data-id"),
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回权限列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        });
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);
    });

    $("body").on("click", ".users_del", function () {  //删除
        var _this = $(this);
        layer.confirm('确定删除此角色？', {icon: 3, title: '提示信息'}, function (index) {
            var currData = usersData.data;
            for (var i = 0; i < currData.length; i++) {
                if (currData[i].id == _this.attr("data-id")) {
                    $.ajax({
                        type: "DELETE",
                        url: "/permission/" + _this.attr("data-id"),
                        async: true,
                        success: function () {
                            layer.alert("删除成功");
                            loadRoles();
                        },
                        error: function () {
                            layer.alert("删除失败");
                        }
                    })
                }
            }
            layer.close(index);
        });
    });

    loadRoles();

    //渲染角色数据
    function deviceList() {
        function renderDate(data, curr) {
            var dataHtml = '';
            currData = usersData.data.concat().splice(curr * nums - nums, nums);
            if (currData.length != 0) {
                for (var i = 0; i < currData.length; i++) {
                    dataHtml += '<tr>'
                        + '<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
                        + '<td>' + currData[i].perName + '</td>'
                        + '<td>' + currData[i].perUrl + '</td>'
                        + '<td>' + currData[i].perDesc + '</td>'
                        + '<td>'
                        + '<a class="layui-btn layui-btn-mini users_edit" data-id="' + currData[i].id + '"><i class="iconfont icon-edit"></i> 编辑</a>'
                        + '<a class="layui-btn layui-btn-danger layui-btn-mini users_del" data-id="' + currData[i].id + '"><i class="layui-icon">&#xe640;</i> 删除</a>'
                        + '</td>';
                }
            } else {
                dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
            }
            return dataHtml;
        }

        //分页
        var nums = 10; //每页出现的数据量
        laypage({
            cont: "page",
            pages: Math.ceil(usersData.length / nums),
            jump: function (obj) {
                $(".users_content").html(renderDate(usersData, obj.curr));
                $('.users_list thead input[type="checkbox"]').prop("checked", false);
                form.render();
            }
        })
    }

})