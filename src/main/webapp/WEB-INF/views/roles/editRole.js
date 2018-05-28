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
        console.log(result);
        return result;
    }

    $(".id").val(oneValues());

    $.ajax({
        url: "/permission",
        type: "GET",
        async: true,
        success: function (data) {
            console.log("permission")
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

    $.ajax({
        url: "/roles/" + oneValues(),
        type: "GET",
        async: true,
        success: function (data) {
            console.log("roles")
            $('.name').val(data.data.name);
            $('.descr').val(data.data.descr);
            var permissions = data.data.permissions;
            for (var i = 0; i < permissions.length; i++) {
                $("input[type='checkbox'][name='permissions'][value='" + permissions[i].id + "']").prop('checked', true);
            }
            form.render();
        }
    });


    form.on("submit(editUser)", function () {
        var data = $(".edit-form").serialize();
        $.ajax({
            url: "/roles",
            data: data,
            type: "PUT",
            async: true,
            success: function (data) {
                if (data.code === 400) {
                    top.layer.msg(data.data);
                }
                else {
                    top.layer.msg("用户修改成功！");
                    setTimeout(function () {
                        top.layer.close(index);
                        layer.closeAll("iframe");
                        //刷新父页面
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
