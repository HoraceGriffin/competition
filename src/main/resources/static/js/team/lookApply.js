layui.use(['form', 'layer', 'jquery'], function () {
    var layer = layui.layer, $ = layui.jquery, form = layui.form;
    var id = $('#id').val();

    $('.layui-layer-btnYes').on('click', function () {
        layer.prompt({
            formType: 2,
            title: '请填写原因（注：选填）',
            area: ['500px', '150px'],
            btnAlign: 'c',
            yes: function (index, layero) {
                // 获取文本框输入的值
                var value = layero.find(".layui-layer-input").val();
                    var index1 = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.2});
                    var index2 = parent.layer.getFrameIndex(window.name);
                    var msg, flag = false;
                    $.ajax({
                        type: "post",
                        async: false,
                        url: "/team/yes",
                        data: {
                            id: id,
                            reason: value
                        },
                        dataType: "json",
                        success: function (d) {
                            if (d.code == 0) {
                                msg = "提交成功！";
                                flag = true;
                            } else {
                                msg = "提交失败！";
                            }
                        },
                        error: function () {
                            msg = '网络错误，请检查网络！';
                            layer.closeAll();
                            return false;
                        }
                    });
                    setTimeout(function () {
                        parent.layer.close(index2);
                        top.layer.close(index1);
                        if (flag) {
                            top.layer.msg(msg, {icon: 1});
                            layer.close(index);
                        } else {
                            top.layer.msg(msg, {icon: 5});
                        }
                        parent.location.reload();
                    }, 1000);
                    return false;
            }
        });
    });

    $('.layui-layer-btnNo').on('click', function () {
        layer.prompt({
            formType: 2,
            title: '请填写原因（注：必填项）',
            area: ['500px', '150px'],
            btnAlign: 'c',
            yes: function (index, layero) {
                // 获取文本框输入的值
                var value = layero.find(".layui-layer-input").val();
                if (value) {
                    var index1 = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.2});
                    var index2 = parent.layer.getFrameIndex(window.name);
                    var msg, flag = false;
                    $.ajax({
                        type: "post",
                        async: false,
                        url: "/team/no",
                        data: {
                            id: id,
                            reason: value
                        },
                        dataType: "json",
                        success: function (d) {
                            if (d.code == 0) {
                                msg = "提交成功！";
                                flag = true;
                            } else {
                                msg = "提交失败！";
                            }
                        },
                        error: function () {
                            msg = '网络错误，请检查网络！';
                            layer.closeAll();
                            return false;
                        }
                    });
                    setTimeout(function () {
                        parent.layer.close(index2);
                        top.layer.close(index1);
                        if (flag) {
                            top.layer.msg(msg, {icon: 1});
                            layer.close(index);
                        } else {
                            top.layer.msg(msg, {icon: 5});
                        }
                        parent.location.reload();
                    }, 1000);
                    return false;
                } else {
                    layer.msg("输入值为空！");
                }
            }
        });
    });

    $('.layui-layer-btnReturn').on('click', function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);//关闭当前页
    });


});