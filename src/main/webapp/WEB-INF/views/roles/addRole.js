var $;
layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;
    $.ajax({
        url: "/permission",
        type: "GET",
        async: true,
        success: function (data) {
            var permissions = data.data;
            var dataHtml = '';
            for (var i = 0; i < permissions.length; i++) {
                dataHtml += '<input type="checkbox" name="permissions" value="' + permissions[i].id +
                    '" title="' + permissions[i].perName + '" alt="'+permissions[i].perDesc+'"/>';
            }
            $('.permissions').html(dataHtml);
            form.render();
        }
    });
    form.on("submit(addUser)", function (data) {
        var formdata = $(".layui-form").serialize();
        $.ajax({
            url: "/roles",
            data: formdata,
            type: "POST",
            async: true,
            success: function () {
                if (data.code === 400) {
                    top.layer.msg(data.data);
                }
                else {
                    top.layer.msg("角色新增成功！");
                    setTimeout(function () {
                        top.layer.close(index);
                        layer.closeAll("iframe");
                        parent.location.reload();
                    }, 500);
                }
            },
            error: function () {
                top.layer.msg("失败！");
            }
        })
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        return false;
    })
})
