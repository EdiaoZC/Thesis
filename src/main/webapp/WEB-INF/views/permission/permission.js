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