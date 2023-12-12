layui.use(['form', 'layer', 'jquery', 'laydate'], function () {
    var layer = parent.layer === undefined ? layui.layer : parent.layer,
        laydate = layui.laydate,
        $ = layui.jquery,
        form = layui.form;

    laydate.render({
        elem: '#createTime' // 指定元素
        , max: 'new Date()'
        , trigger: 'click'
    });

    form.on("submit(addAnnouncement)", function (data) {
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.2});
        var index1 = parent.layer.getFrameIndex(window.name);
        var msg, flag = false;
        $.ajax({
            type: "post",
            url: "/announcement/insertAnnouncement",
            async: false,
            data: data.field,
            dataType: "json",
            success: function (d) {
                if (d.code == 0) {
                    msg = '公告信息添加成功！';
                    flag = true;
                    $("#auf")[0].reset();
                } else {
                    msg = d.msg;
                }
            },
            error: function () {
                msg = '添加失败！';
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