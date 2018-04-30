layui.config({
    base: "js/"
}).use(['form', 'layer'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;

    //登录按钮事件
    form.on("submit(login)", function (data) {
        var formdata = $('.layui-form').serialize();
        $.ajax({
            url: "/user/login",
            data: formdata,
            type: "POST",
            async: true,
            success: function (data) {
                window.location.href = data.data;
                return false;
            },
            error: function (response, data) {
                if (response.status == 401) {
                    layer.alert("登陆失败");
                }
            }
        });

        return false;
    })
})
