layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery', 'laypage'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;

    //加载页面数据
    var usersData = '';

    function loadDeviceType() {
        $.get("/doctor/unHandleRequest", function (data) {
            usersData = data;
            deviceList();
        })
    }

    loadDeviceType();


    //渲染设备数据
    function deviceList() {
        function renderDate(data, curr) {
            var dataHtml = '';
            if (usersData.code == 402) {
                dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
            } else {
                currData = usersData.data.splice(curr * nums - nums, nums);
                if (currData.length != 0) {
                    for (var i = 0; i < currData.length; i++) {
                        dataHtml += '<tr>'
                            + '<td><p onclick="doclick(' + "\'" + currData[i] + "\'" + ')">请求' + i + 1 + '</p></td></tr>'
                    }
                } else {
                    dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
                }
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