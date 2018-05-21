var $;
layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage;
    $ = layui.jquery;

    function oneValues() {
        var result;
        var url = window.location.search; //获取url中"?"符后的字串
        if (url.indexOf("?") != -1) {
            result = url.substr(url.indexOf("=") + 1);
        }
        return result;
    }

    var i = 0;
    $(".id").val(oneValues());
    $.ajax({
        url: "/deviceType/" + oneValues(),
        type: "GET",
        async: true,
        success: function (data) {
            $('.name').val(data.deviceType);
            var json = JSON.parse(data.runningParam);
            var tr = "";
            json.forEach(function (item) {
                var param = unescape("run[" + i + "]");
                tr += "<tr>" +
                    "<td><input type=\"checkbox\" name=\"checked\" lay-skin=\"primary\" lay-filter=\"choose\"></td>" +
                    "<td><input type='text' value=" + item.key + "  name=" + param + ".key class='layui-input' placeholder='轻输入选项值'/></td>" +
                    "<td><input type='text' value=" + item.value + " name=" + param + ".value class='layui-input' placeholder='轻输入参数值'/></td></tr>";
                i++;
            });
            $("#runningParam").append(tr);//向table中追加tr
            form.render();
        }
    })

    $("#addRows").click(function () {
        var param = unescape("run[" + i + "]");
        var tr = "<tr>" +
            "<td><input type=\"checkbox\" name=\"checked\" lay-skin=\"primary\" lay-filter=\"choose\"></td>" +
            "<td><input type='text' name=" + param + ".key class='layui-input' placeholder='轻输入选项值'/></td>" +
            "<td><input type='text' name=" + param + ".value class='layui-input' placeholder='轻输入参数值'/></td></tr>";
        $("#runningParam").append(tr);//向table中追加tr
        form.render();
        i++;
    });
    $("#delRows").click(function () {
        $("input:checked").parent().parent("tr").remove();//移除选中的行
        form.render();
    });

    form.on("submit(editDevice)", function () {
        var data = $(".layui-form").serialize();
        $.ajax({
            url: "/deviceType",
            data: data,
            type: "PUT",
            async: true,
            success: function () {
                top.layer.msg("修改成功！");
                setTimeout(function () {
                    top.layer.close(index);
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                }, 500);
            },
            error: function () {
                top.layer.msg("失败！");
            }
        })
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        return false;
    })
})
