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

    $(".id").val(oneValues());
    $.ajax({
        url: "/users/" + oneValues(),
        type: "GET",
        async: true,
        success: function (data) {
            $('.userName').val(data.username);
            $('.nickname').val(data.nickname);
            var index = 0;
            if (data.sex === 'Female') {
                index = 1;
            }
            $('[name=sex]').eq(index).prop('checked', true);
            if ((data.status & 8) == 8) {
                $("input[type='checkbox'][name='status'][value='8']").prop('checked', true);
            }
            if ((data.status & 1) == 1) {
                $("input[type='checkbox'][name='status'][value='1']").prop('checked', true);
            }
            form.render();
        }
    })

    form.on("submit(editUser)", function () {
        var data = $(".edit-form").serialize();
        $.ajax({
            url: "/users",
            data: data,
            type: "PUT",
            async: true,
            success: function () {
                top.layer.msg("用户修改成功！");
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
