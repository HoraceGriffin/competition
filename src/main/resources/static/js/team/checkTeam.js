layui.use(['form', 'layer', 'jquery', 'table', 'element', 'laydate'], function () {
    var layer = layui.layer, $ = layui.jquery, form = layui.form, table = layui.table, laydate = layui.laydate,
        element = layui.element;
    var nowTime = new Date().valueOf();
    var status = 1;

    $(function () {
        $('#sel').find('li').each(function () {
            $(this).click(function () {
                status = $(this).index();
            })
        })
    })

    active = {
        search: function () {
            var teamName = $('#teamName');
            var projectName = $('#projectName');
            var adviser = $('#adviser');
            var competitionTitle = $('#competitionTitle');
            table.reload('teamList' + status, {
                page: {
                    curr: 1
                },
                where: {
                    teamName: teamName.val(),
                    projectName: projectName.val(),
                    adviser: adviser.val(),
                    competitionTitle: competitionTitle.val(),
                }
            });
        }
    };

    /*待审核*/
    table.render({
        id: 'teamList1'
        , elem: '#teamList1'
        , url: '/team/checkWait'
        , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        , defaultToolbar: ['filter', 'exports', 'print']
        , limit: 10
        , limits: [5, 10, 20, 30]
        , cols: [[
            {field: 'id', title: 'ID', align: 'center', width: 80, sort: true}
            , {field: 'teamName', title: '团队名称', align: 'center', width: 110}
            , {field: 'projectName', title: '项目名称名称', align: 'center', width: 110}
            , {field: 'members', title: '团队成员', align: 'center', width: 120}
            , {field: 'adviser', title: '指导教师', align: 'center', width: 120}
            , {field: 'content', title: '概述', align: 'center'}
            , {field: 'status', title: '状态', align: 'center', width: 100, templet: '#statusTpl'}
            , {field: 'competitionTitle', title: '赛事标题', align: 'center', width: 220}
            , {field: 'note', title: '备注', align: 'center', width: 120}
            , {field: 'right', title: '操作', align: 'center', width: 100, toolbar: "#barDemo"}
        ]]
        , page: true // 开启分页
        , loading: true
        , where: {timestamp: (new Date()).valueOf()}
    });

    /*审核通过*/
    table.render({
        id: 'teamList2'
        , elem: '#teamList2'
        , url: '/team/checkYes'
        , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        , defaultToolbar: ['filter', 'exports', 'print']
        , limit: 10
        , limits: [5, 10, 20, 30]
        , cols: [[
            {field: 'id', title: 'ID', align: 'center', width: 80, sort: true}
            , {field: 'teamName', title: '团队名称', align: 'center', width: 110}
            , {field: 'projectName', title: '项目名称名称', align: 'center', width: 110}
            , {field: 'members', title: '团队成员', align: 'center', width: 120}
            , {field: 'adviser', title: '指导教师', align: 'center', width: 120}
            , {field: 'content', title: '概述', align: 'center'}
            , {field: 'status', title: '状态', align: 'center', width: 100, templet: '#statusTpl'}
            , {field: 'competitionTitle', title: '赛事标题', align: 'center', width: 220}
            , {field: 'note', title: '备注', align: 'center', width: 120}
            , {field: 'right', title: '操作', align: 'center', width: 100, toolbar: "#barDemo"}
        ]]
        , page: true // 开启分页
        , loading: true
        , where: {timestamp: (new Date()).valueOf()}
    });

    /*审核不通过*/
    table.render({
        id: 'teamList3'
        , elem: '#teamList3'
        , url: '/team/checkNo'
        , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        , defaultToolbar: ['filter', 'exports', 'print']
        , limit: 10
        , limits: [5, 10, 20, 30]
        , cols: [[
            {field: 'id', title: 'ID', align: 'center', width: 80, sort: true}
            , {field: 'teamName', title: '团队名称', align: 'center', width: 110}
            , {field: 'projectName', title: '项目名称名称', align: 'center', width: 110}
            , {field: 'members', title: '团队成员', align: 'center', width: 120}
            , {field: 'adviser', title: '指导教师', align: 'center', width: 120}
            , {field: 'content', title: '概述', align: 'center'}
            , {field: 'status', title: '状态', align: 'center', width: 100, templet: '#statusTpl'}
            , {field: 'competitionTitle', title: '赛事标题', align: 'center', width: 220}
            , {field: 'note', title: '备注', align: 'center', width: 120}
            , {field: 'reason', title: '不通过原因', align: 'center', width: 220}
            , {field: 'right', title: '操作', align: 'center', width: 100, toolbar: "#barDemo"}
        ]]
        , page: true // 开启分页
        , loading: true
        , where: {timestamp: (new Date()).valueOf()}
    });

    /*显示失效的参赛信息*/
   /* table.render({
        id: 'teamList4'
        , elem: '#teamList4'
        , url: '/team/checkFailure'
        , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        , defaultToolbar: ['filter', 'exports', 'print']
        , limit: 10
        , limits: [5, 10, 20, 30]
        , cols: [[
            {field: 'id', title: 'ID', align: 'center', width: 80, sort: true}
            , {field: 'teamName', title: '团队名称', align: 'center', width: 110}
            , {field: 'projectName', title: '项目名称名称', align: 'center', width: 110}
            , {field: 'members', title: '团队成员', align: 'center', width: 120}
            , {field: 'adviser', title: '指导教师', align: 'center', width: 120}
            , {field: 'content', title: '概述', align: 'center'}
            , {field: 'status', title: '状态', align: 'center', width: 100, templet: '#statusTpl'}
            , {field: 'competitionTitle', title: '赛事标题', align: 'center', width: 220}
            , {field: 'note', title: '备注', align: 'center', width: 120}
        ]]
        , page: true // 开启分页
        , loading: true
        , where: {timestamp: (new Date()).valueOf()}
    });*/


    $(".search_btn").click(function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    })

    //头工具栏事件
    table.on('toolbar(teamList1)', function (obj) {
        Reload(obj, 1)
    });

    //头工具栏事件
    table.on('toolbar(teamList2)', function (obj) {
        Reload(obj, 2)
    });

    //头工具栏事件
    table.on('toolbar(teamList3)', function (obj) {
        Reload(obj, 3)
    });

    //头工具栏事件
 /*   table.on('toolbar(teamList4)', function (obj) {
        Reload(obj, 4)
    });*/

    function Reload(obj, i) {
        switch (obj.event) {
            case 'reload':
                table.reload('teamList' + i);
                break;
        }
    }

    //监听行工具列表
    table.on('tool(teamList1)', function (obj) {
        var data = obj.data;
        if (obj.event === 'check') {
            var applyIndex = layer.open({
                type: 2,
                title: "审核参赛信息",
                area: ['800px', '600px'],
                content: "/team/checkLook/" + data.id,
                success: function (layero, index) {

                }
            });
        }
    });

    table.on('tool(teamList2)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            var applyIndex = layer.open({
                type: 2,
                title: "修改审核",
                area: ['800px', '600px'],
                content: "/team/checkLook/" + data.id,
                success: function (layero, index) {

                }
            });
        }
    });

    table.on('tool(teamList3)', function (obj) {
        var data = obj.data;
        if (obj.event === 'look') {
            var applyIndex = layer.open({
                type: 2,
                title: "查看审核",
                area: ['800px', '600px'],
                content: "/team/checkLook/" + data.id,
                success: function (layero, index) {

                }
            });
        }
    });

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
