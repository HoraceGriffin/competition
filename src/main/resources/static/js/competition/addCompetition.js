layui.use(['form', 'layer', 'jquery', 'laydate','upload'], function () {
    var layer = parent.layer === undefined ? layui.layer : parent.layer,
        laydate = layui.laydate,
        $ = layui.jquery,
        upload = layui.upload,
        form = layui.form;

    laydate.render({
        elem: '#validTime' // 指定元素
        , trigger: 'click'
    });

    laydate.render({
        elem: '#createTime' // 指定元素
        , max: 'new Date()'
        , trigger: 'click'
    });

    const extname = (filename) => {
        var str = "";
        var index1 = filename.lastIndexOf(".");
        var index2 = filename.length;
        if(index1>=1){
            str = filename.substring(index1,index2);
        }
        return str;
    }
    //图片上传
    var uploadInst = upload.render({
        elem: '#photo'         //绑定点击按钮
        , url: '/competition/upload'     //访问后台路径
        , accept: 'images'               //图片格式
        , auto: false                     //取消自动上传
        , method: 'post'                 //请求上传的 http 类型
        , bindAction: '#hideUpload'       //绑定真正的上传按钮
        , size: 5*1024                    //限制图片大小
        , data: {                         //访问后台提交的数据
            photoName: () => {
                return $('#activity_photo').val();//官方文档说明：实现动态传值
            }
        }
        , choose: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#activity_photo').attr("value", (this.uuid() + '_' + new Date().getTime()+extname(file.name)))
                $('#demo').attr({src: result, width: "220", height: "300"});//图片链接（base64）
            });
        }
        , error: function () {
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });

    form.on("submit(addCompetition)", function (data) {
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.2});
        var index1 = parent.layer.getFrameIndex(window.name);
        var msg, flag = false;
        $.ajax({
            type: "post",
            url: "/competition/insertCompetition",
            async: false,
            data: data.field,
            dataType: "json",
            success: function (d) {
                if (d.code == 0) {
                    $('#hideUpload').trigger('click');
                    msg = '比赛信息添加成功！';
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

function uuid() {
    var d = new Date().getTime();
    if (window.performance && typeof window.performance.now === "function") {
        d += performance.now(); //use high-precision timer if available
    }
    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = (d + Math.random() * 16) % 16 | 0;    // d是随机种子
        d = Math.floor(d / 16);
        return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
    return uuid;
}