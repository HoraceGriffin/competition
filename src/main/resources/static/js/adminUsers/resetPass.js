layui.use(['form', 'layer', 'jquery', 'table', 'laydate'], function () {
    var layer = layui.layer, $ = layui.jquery, form = layui.form, table = layui.table;
    var username = $("#username").val();

    $("#username").blur(function () {
        if (username != $("#username").val()) {
            $.ajax({
                type: "get",
                url: "/adminUsers/checkAdminByName/" + $("#username").val(),
                success: function (data) {
                    if (data.code != 0) {
                        top.layer.msg(data.msg);
                        $("#username").val("");
                        $("#username").focus();
                    }
                }
            });
        }
    });


    form.on("submit(resetPass)", function (data) {
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.2});
        var index1 = parent.layer.getFrameIndex(window.name);
        var msg, flag = false;
        $.ajax({
            type: "post",
            async: false,
            url: "/adminUsers/updatePass",
            data: data.field,
            dataType: "json",
            success: function (d) {
                if (d.code == 0) {
                    msg = "密码重置成功！";
                    flag = true;
                } else {
                    msg = "密码重置失败！";
                }
            },
            error: function () {
                msg = '网络错误，请检查网络！';
                layer.closeAll();
                return false;
            }

        });
        setTimeout(function () {
            parent.layer.close(index1);
            top.layer.close(index);
            if (flag) {
                top.layer.msg(msg, {icon: 1});
            } else {
                top.layer.msg(msg, {icon: 5});
            }
            parent.location.reload();
        }, 2000);
        return false;
    })

});

