layui.use(['upload', 'form', 'element', 'layer'], function () {
    var layer = layui.layer, $ = layui.jquery, form = layui.form, upload = layui.upload

    form.on("submit(updateAdmin)", function (data) {
        var index = layer.load(1, {
            shade: [0.5, '#000']
        });
        $.ajax({
            url: "/person/updateAdmin",
            async: false,
            type: "post",
            data: data.field,
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    layer.close(index);
                    layer.msg("修改成功!");
                } else {
                    layer.close(index);
                    layer.msg("修改失败，请重试！");
                }
            },
        });
        setTimeout(function () {

        }, 1000);
        return false;
    });

    //拖拽上传
    upload.render({
        elem: '#photo'
        , url: "/person/upload"
        , accept: 'file' //普通文件
        , exts: 'jpg|png'
        , done: function (res) {
            layer.msg('上传成功');
            layui.$('#uploadDemoView').removeClass('layui-hide').find('img').attr('src', res.data[0]);
            layui.$('#uploadDemoView1').addClass('layui-hide').find('img').attr('src', '');
            layui.$('#icon').addClass('layui-hide');
            layui.$('#icon1').addClass('layui-hide');
        }
    });
});