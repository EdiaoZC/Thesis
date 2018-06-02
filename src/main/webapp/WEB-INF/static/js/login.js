layui.config({
    base: "js/"
}).use(['form', 'layer'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;
    $('.codeImage').attr('src', '/captcha?' + Math.floor(Math.random() * 100));

    $('.codeImage').click(
        function () {
            $(this).attr('src', '/captcha?' + Math.floor(Math.random() * 100));
        }
    );


    //登录按钮事件
    form.on("submit(login)", function (data) {
        var formdata = $('.layui-form').serialize();
        $.ajax({
            url: "/captcha/verify",
            data: formdata,
            async: true,
            success: function (data) {
                if (data.code == 200) {
                    $.ajax({
                        url: "/user/login",
                        data: formdata,
                        type: "POST",
                        async: true,
                        success: function (data) {
                            window.location.href = data.data;
                            return false;
                        }
                    });
                } else {
                    layer.alert(data.msg)
                }
            }
        });

        return false;
    })
})
