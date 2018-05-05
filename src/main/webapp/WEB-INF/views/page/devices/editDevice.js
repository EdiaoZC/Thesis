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

    function loadDeviceType() {
        $.ajax({
            url: "/deviceType",
            type: "GET",
            async: false,
            success: function (data) {
                currData = data.data;
                html = "";
                if (currData.length != 0) {
                    for (var i = 0; i < currData.length; i++) {
                        html = '<option value="' + currData[i].id + '">';
                        html += currData[i].deviceType + '</option>';
                        console.log(html);
                        $('.deviceType').append($(html));
                    }
                } else {
                    $('.deviceType').append($(html));
                }
                form.render();
                return false;
            },
            error: function () {

            }
        })
    }

    loadDeviceType();

    $(".id").val(oneValues());
    $.ajax({
        url: "/devices/id/" + oneValues(),
        type: "GET",
        async: true,
        success: function (data) {
            $('.deviceId').val(data.deviceId);
            $('.deviceType').val(data.equipmentType);
            form.render();
        }
    })

    form.on("submit(editDevice)", function () {
        var data = $(".edit-form").serialize();
        $.ajax({
            url: "/devices",
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
