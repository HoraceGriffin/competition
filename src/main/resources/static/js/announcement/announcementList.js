layui.use(['form', 'layer', 'jquery', 'table', 'laydate'], function () {
    var layer = layui.layer, $ = layui.jquery, form = layui.form, table = layui.table, laydate = layui.laydate;
    var nowTime = new Date().valueOf();
    var max = null;
    active = {
        search: function () {
            var title = $('#title');
            var content = $('#content');
            var type = $('#type option:selected')
            var createTimeStart = $('#createTimeStart');
            var createTimeEnd = $('#createTimeEnd');


            table.reload('announcementList', {
                page: {
                    curr: 1
                },
                where: {
                    title: title.val(),
                    content: content.val(),
                    type: type.val(),
                    createTimeStart: createTimeStart.val(),
                    createTimeEnd: createTimeEnd.val(),
                }
            });
        }
    };

    var createTimeS = laydate.render({
        elem: '#createTimeStart',
        type: 'datetime',
        max: nowTime,
        done: function (value, date) {
            endMax = createTimeE.config.max;
            createTimeE.config.min = date;
            createTimeE.config.min.month = date.month - 1;
        }
    });

    var createTimeE = laydate.render({
        elem: '#createTimeEnd',
        type: 'datetime',
        max: nowTime,
        done: function (value, date) {
            if ($.trim(value) == '') {
                var curDate = new Date();
                date = {
                    'date': curDate.getDate(),
                    'month': curDate.getMonth() + 1,
                    'year': curDate.getFullYear()
                };
            }
            createTimeE.config.max = date;
            createTimeE.config.max.month = date.month - 1;
        }
    });


    table.render({
        id: 'announcementList'
        , elem: '#announcementList'
        , url: '/announcement/getAllAnnouncementList'
        , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        , defaultToolbar: ['filter', 'exports', 'print']
        , limit: 10
        , limits: [5, 10, 20, 30]
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'title', title: '标题', align: 'center', width: 350}
            , {field: 'content', title: '内容', align: 'center'}
            , {
                field: 'createTime',
                title: '发布时间',
                align: 'center',
                templet: '<div>{{ formatTime(d.createTime,"yyyy-MM-dd hh:mm:ss")}}</div>',
                width: 230
            }
            , {field: 'type', title: '类型', templet: '#typeTpl', width: 95, align: 'center'}
            , {field: 'right', title: '操作', align: 'center', toolbar: "#barDemo", width: 260}
        ]]
        , page: true // 开启分页
        , loading: true
        , where: {timestamp: (new Date()).valueOf()}
    });

    //头工具栏事件
    table.on('toolbar(announcementList)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'delAnnouncements':
                var data = checkStatus.data;
                if (data.length == 0) {
                    layer.msg('请选择要删除的公告信息！');
                    break;
                } else {
                    var ids = [];
                    for (var i = 0; i < data.length; i++) {
                        ids.push(data[i].id);
                    }
                    layer.confirm('确定要删除id为： ' + ids + ' 的公告信息吗？', function (index) {
                        $.ajax({
                            url: '/announcement/deleteAnnouncementByIds',
                            type: "POST",
                            data: {"ids": ids},
                            traditional: true,//解决Ajax异步请求中传数组参数，后台无法接收问题
                            success: function (d) {
                                if (d.code == 0) {
                                    layer.msg("删除成功！", {icon: 1});
                                    table.reload('announcementList');
                                } else {
                                    layer.msg("删除失败！", {icon: 5});
                                }
                            },
                            error: function () {
                                layer.msg("删除失败！网络错误！", {icon: 5});
                            }
                        })
                        layer.close(index);
                    });
                    break;
                }
            case 'getCheckLength':
                var data = checkStatus.data;
                layer.msg('选中了：' + data.length + ' 个');
                break;
            case 'reload':
                table.reload('announcementList');
                break;
        }
    });

    //监听行工具列表
    table.on('tool(announcementList)', function (obj) {
        var data = obj.data;
        if (obj.event === 'delete') {
            layer.confirm('确定要删除： ' + data.title + ' 的信息吗？', function (index) {
                $.ajax({
                    url: '/announcement/deleteAnnouncementById',
                    type: "POST",
                    data: {"id": data.id},
                    cache: false,
                    success: function (d) {
                        if (d.code == 0) {
                            layer.msg("删除成功！", {icon: 1});
                            obj.del();
                        } else {
                            layer.msg("删除失败！", {icon: 5});
                        }
                    },
                    error: function () {
                        layer.msg("删除失败！网络错误！", {icon: 5});
                    }
                })
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            var editIndex = layer.open({
                type: 2,
                title: "编辑信息",
                area: ['800px', '480px'],
                content: "/announcement/editAnnouncement/" + data.id,
                success: function (layero, index) {

                }
            });
        } else if (obj.event === 'details') {
            var detailsIndex = layer.open({
                type:2,
                title:"查看详情",
                area:['100%','100%'],
                content: "/announcement/announcementDetails/" + data.id,
                success: function (layero, index) {

                }
            });
        }
    });

    $(".search_btn").click(function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    })

    $(".announcementAdd_btn").click(function () {
        var addIndex = layer.open({
            title: "添加公告",
            type: 2,
            area: ['800px', '520px'],
            content: "/announcement/addAnnouncement",
            success: function (layero, index) {

            }
        })
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
        "S": datetime.getMilliseconds()// 毫秒
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
