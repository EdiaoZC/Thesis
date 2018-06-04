var $;
layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery'], function () {
        var form = layui.form(),
            layer = parent.layer === undefined ? layui.layer : parent.layer,
            $ = layui.jquery;

        function oneValues() {
            var result;
            var url = window.location.search; //获取url中"?"符后的字串
            if (url.indexOf("?") != -1) {
                result = url.substr(url.indexOf("=") + 1);
            }
            return result;
        }

        $('.token').val(oneValues());
        var i = 0;
        var usersData = '';

        function loadDeviceType() {
            $.ajax({
                url: "/doctor/token/" + oneValues(),
                type: "GET",
                async: true,
                success: function (data) {
                    var json = JSON.parse(data.data.params);
                    var tr = "";
                    usersData = data.data.results;
                    json.forEach(function (item) {
                        var param = unescape("run[" + i + "]");
                        tr += "<tr>" +
                            "<td><input readonly='readonly' value=" + item.key + "  name=" + param + ".key  class='layui-input' /></td>" +
                            "<td><input type='text' value=" + item.value + " name=" + param + ".value class='layui-input' /></td></tr>";
                        i++;
                    });
                    $('.comment').html(data.data.comment);
                    $("#runningParam").append(tr);//向table中追加tr
                    deviceList();
                    form.render();
                    return false;
                },
                error: function () {

                }
            })
        }

        loadDeviceType();

        function deviceList() {
            function renderDate(data) {
                var dataHtml = '';
                if (data.length != 0) {
                    for (var i = 0; i < data.length; i++) {
                        dataHtml += '<tr>'
                            + '<td>' + data[i].nickname + '</td>'
                            + '<td>' + data[i].deviceType + '</td>'
                            + '<td>' + data[i].actualParam + '</td>'
                            + '<td>' + data[i].trainingResult + '</td>'
                            + '<td>' + data[i].trainingTime + '</td>'
                            + '<td>' + data[i].score + '</td>'
                            + '</tr>';
                    }
                } else {
                    dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
                }
                return dataHtml;
            }

            $(".users_content").html(renderDate(usersData));
        }

        form.on("submit(addDevice)", function (data) {
            var formdata = $(".layui-form").serialize();
            $.ajax({
                url: "/doctor",
                data: formdata,
                type: "POST",
                async: true,
                success: function () {
                    top.layer.msg("请求处理成功！");
                    setTimeout(function () {
                        top.layer.close(index);
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                        window.location.href = "/index";
                    }, 500);
                },
                error: function () {
                    top.layer.msg("失败！");
                }
            })
            var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
            return false;
        })
    }
)
