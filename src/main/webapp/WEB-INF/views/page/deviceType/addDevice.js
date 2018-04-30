var $;
layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;


    var i = 0;
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

    form.on("submit(addDevice)", function (data) {
        var formdata = $(".layui-form").serialize();
        console.log(formdata);
        $.ajax({
            url: "/deviceType",
            data: formdata,
            type: "POST",
            async: true,
            success: function () {
                top.layer.msg("设备添加成功！");
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
