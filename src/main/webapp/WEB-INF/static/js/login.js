layui.config({
    base: "js/"
}).use(['form', 'layer'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;

    //登录按钮事件
    form.on("submit(login)", function (data) {
        var formdata = $('.layui-form').serialize();
        alert(formdata)
        $.ajax({
            url: "/user/login",
            data: formdata,
            type: "POST",
            async: true,
            success: function (returnData) {
                window.location.href = returnData.data;
            },
            error: function (returnData) {
                layer.alert(returnData.msg);
                return true;
            }
        })
        return false;
    })
})
