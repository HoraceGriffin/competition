layui.use(['form', 'layer', 'jquery', 'table', 'laydate','upload'], function () {
    var layer = layui.layer, $ = layui.jquery, form = layui.form, table = layui.table, laydate = layui.laydate,upload = layui.upload;

    laydate.render({
        elem: '#createTime'
    });
    laydate.render({
        elem: '#validTime'
    });

   /* const extname = (filename) => {
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
    });*/

    form.on("submit(updateCompetition)", function (data) {
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.2});
        var index1 = parent.layer.getFrameIndex(window.name);
        var msg, flag = false;
        $.ajax({
            type: "post",
            async: false,
            url: "/competition/updateCompetition",
            data: data.field,
            dataType: "json",
            success: function (d) {
                if (d.code == 0) {
                    msg = "比赛信息更新成功！";
                    flag = true;
                } else {
                    msg = "比赛信息更新失败！";
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


// 格式化时间
function formatTime(datetime, fmt) {
    if (datetime == null || datetime == 0) {
        return "";
    }
    if (parseInt(datetime) == datetime) {
        if (datetime.length == 10) {
            datetime = parseInt(datetime) * 1000;
        } else if (datetime.length == 13) {
            datetime = parseInt(datetime);
        }
    }
    datetime = new Date(datetime);
    var o = {
        "M+": datetime.getMonth() + 1, // 月份
        "d+": datetime.getDate(), // 日
        "h+": datetime.getHours(), // 小时
        "m+": datetime.getMinutes(), // 分
        "s+": datetime.getSeconds(), // 秒
        "q+": Math.floor((datetime.getMonth() + 3) / 3), // 季度
        "S": datetime.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (datetime.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1,
                (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
                    .substr(("" + o[k]).length)));
    return fmt;
}
