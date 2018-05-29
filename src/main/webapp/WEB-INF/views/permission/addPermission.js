var $;
layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;

    form.on("submit(addPermission)", function (data) {
        var formdata = $(".layui-form").serialize();
        console.log(formdata);
        $.ajax({
            url: "/permission",
            data: formdata,
            type: "POST",
            async: true,
            success: function () {
                top.layer.msg("权限添加成功！");
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
        });
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        return false;
    })

});
