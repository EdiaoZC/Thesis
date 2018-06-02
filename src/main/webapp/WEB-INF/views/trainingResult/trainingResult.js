layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery', 'laypage'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;

    //加载页面数据
    var usersData = '';

    function loadDeviceType() {
        $.get("/trainingResult", function (data) {
            usersData = data;
            deviceList();
        })
    }

    loadDeviceType();


    //查询
    $(".search_btn").click(function () {
        var userArray = [];
        if ($(".search_input").val() != '') {
            var index = layer.msg('查询中，请稍候', {icon: 16, time: false, shade: 0.8});
            setTimeout(function () {
                for (var i = 0; i < usersData.length; i++) {
                    var usersStr = usersData[i];
                    var selectStr = $(".search_input").val();

                    function changeStr(data) {
                        var dataStr = '';
                        var showNum = data.split(eval("/" + selectStr + "/ig")).length - 1;
                        if (showNum > 1) {
                            for (var j = 0; j < showNum; j++) {
                                dataStr += data.split(eval("/" + selectStr + "/ig"))[j] + "<i style='color:#03c339;font-weight:bold;'>" + selectStr + "</i>";
                            }
                            dataStr += data.split(eval("/" + selectStr + "/ig"))[showNum];
                            return dataStr;
                        } else {
                            dataStr = data.split(eval("/" + selectStr + "/ig"))[0] + "<i style='color:#03c339;font-weight:bold;'>" + selectStr + "</i>" + data.split(eval("/" + selectStr + "/ig"))[1];
                            return dataStr;
                        }
                    }

                    //用户名
                    if (usersStr.nickname.indexOf(selectStr) > -1) {
                        usersStr["nickname"] = changeStr(usersStr.nickname);
                    }
                    if (usersStr.nickname.indexOf(selectStr) > -1) {
                        userArray.push(usersStr);
                    }
                }
                usersData = userArray;
                deviceList(usersData);

                layer.close(index);
            }, 2000);
        }
        else {
            layer.msg("请输入需要查询的内容");
        }
    })

    //渲染设备数据
    function deviceList() {
        function renderDate(data, curr) {
            var dataHtml = '';
            currData = usersData.concat().splice(curr * nums - nums, nums);
            if (currData.length != 0) {
                for (var i = 0; i < currData.length; i++) {
                    dataHtml += '<tr>'
                        + '<td>' + currData[i].nickname + '</td>'
                        + '<td>' + currData[i].deviceType + '</td>'
                        + '<td>' + currData[i].actualParam + '</td>'
                        + '<td>' + currData[i].trainingResult + '</td>'
                        + '<td>' + currData[i].trainingTime + '</td>'
                        + '<td>' + currData[i].score + '</td>'
                        + '</tr>';
                }
            } else {
                dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
            }
            return dataHtml;
        }

        //分页
        var nums = 10; //每页出现的数据量
        laypage({
            cont: "page",
            pages: Math.ceil(usersData.length / nums),
            jump: function (obj) {
                $(".users_content").html(renderDate(usersData, obj.curr));
                $('.users_list thead input[type="checkbox"]').prop("checked", false);
                form.render();
            }
        })
    }

})