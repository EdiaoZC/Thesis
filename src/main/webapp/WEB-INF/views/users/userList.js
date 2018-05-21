layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery', 'laypage'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;

    //加载页面数据
    var usersData = '';

    function loadUser() {
        $.get("/users", function (data) {
            usersData = data;
            //执行加载数据的方法
            usersList();
        })
    }

    loadUser();


    //添加会员
    $(".usersAdd_btn").click(function () {
        var index = layui.layer.open({
            title: "添加会员",
            type: 2,
            content: "addUser.html",
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回会员列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        })
        layui.layer.full(index);
    })

    //批量删除
    $(".batchDel").click(function () {

        var index = layui.layer.open({
            title: "添加会员",
            type: 2,
            content: "test.html",
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回会员列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        })
        layui.layer.full(index);

        // var $checkbox = $('.users_list tbody input[type="checkbox"][name="checked"]');
        // var $checked = $('.users_list tbody input[type="checkbox"][name="checked"]:checked');
        // if ($checkbox.is(":checked")) {
        //     layer.confirm('确定删除选中的信息？', {icon: 3, title: '提示信息'}, function (index) {
        //         var index = layer.msg('删除中，请稍候', {icon: 16, time: false, shade: 0.8});
        //         setTimeout(function () {
        //             //删除数据
        //             for (var j = 0; j < $checked.length; j++) {
        //                 for (var i = 0; i < usersData.length; i++) {
        //                     if (usersData[i].newsId == $checked.eq(j).parents("tr").find(".news_del").attr("data-id")) {
        //                         usersData.splice(i, 1);
        //                         usersList(usersData);
        //                     }
        //                 }
        //             }
        //             $('.users_list thead input[type="checkbox"]').prop("checked", false);
        //             form.render();
        //             layer.close(index);
        //             layer.msg("删除成功");
        //         }, 2000);
        //     })
        // } else {
        //     layer.msg("请选择需要删除的文章");
        // }
    })

    //全选
    form.on('checkbox(allChoose)', function (data) {
        var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
        child.each(function (index, item) {
            item.checked = data.elem.checked;
        });
        form.render('checkbox');
    });

    //通过判断文章是否全部选中来确定全选按钮是否选中
    form.on("checkbox(choose)", function (data) {
        var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
        var childChecked = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"]):checked')
        if (childChecked.length == child.length) {
            $(data.elem).parents('table').find('thead input#allChoose').get(0).checked = true;
        } else {
            $(data.elem).parents('table').find('thead input#allChoose').get(0).checked = false;
        }
        form.render('checkbox');
    })

    //操作
    $("body").on("click", ".users_edit", function () {
        var _this = $(this);
        var index = layui.layer.open({
            title: "修改成员",
            type: 2,
            content: "editUser.html?id=" + _this.attr("data-id"),
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回会员列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        })
        layui.layer.full(index);
    })

    $("body").on("click", ".users_del", function () {  //删除
        var _this = $(this);
        layer.confirm('确定删除此用户？', {icon: 3, title: '提示信息'}, function (index) {
            for (var i = 0; i < usersData.length; i++) {
                if (usersData[i].id == _this.attr("data-id")) {
                    $.ajax({
                        type: "DELETE",
                        url: "/users/" + _this.attr("data-id"),
                        async: true,
                        success: function () {
                            layer.alert("删除成功");
                            loadUser();
                        },
                        error: function () {
                            layer.alert("删除失败");
                        }
                    })
                }
            }
            layer.close(index);
        });
    })

    //渲染用户数据
    function usersList() {
        function renderDate(data, curr) {
            var dataHtml = '';
            currData = usersData.concat().splice(curr * nums - nums, nums);
            if (currData.length != 0) {
                for (var i = 0; i < currData.length; i++) {
                    dataHtml += '<tr>'
                        + '<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
                        + '<td>' + currData[i].username + '</td>'
                        + '<td>' + currData[i].nickname + '</td>'
                        + '<td>' + currData[i].sex + '</td>'
                        + '<td>' + currData[i].status + '</td>'
                        + '<td>' + currData[i].createTime + '</td>'
                        + '<td>'
                        + '<a class="layui-btn layui-btn-mini users_edit" data-id="' + currData[i].id + '"><i class="iconfont icon-edit"></i> 编辑</a>'
                        + '<a class="layui-btn layui-btn-danger layui-btn-mini users_del" data-id="' + currData[i].id + '"><i class="layui-icon">&#xe640;</i> 删除</a>'
                        + '</td>'
                        + '</tr>';
                }
            } else {
                dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
            }
            return dataHtml;
        }

        //分页
        var nums = 13; //每页出现的数据量
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