layui.use(['form', 'layer', 'jquery', 'table'], function () {
    var layer = layui.layer,
        $ = layui.jquery,
        form = layui.form,
        table = layui.table;
    active = {
        search: function () {
            var teamName = $('#teamName');
            var projectName = $('#projectName');
            var adviser = $('#adviser');
            var competitionTitle = $('#competitionTitle');
            var status = $('#status option:selected');
            table.reload('teamList', {
                page: {
                    curr: 1
                },
                where: {
                    teamName: teamName.val(),
                    projectName: projectName.val(),
                    adviser: adviser.val(),
                    competitionTitle: competitionTitle.val(),
                    status:status.val()
                }
            });
        }
    };

    table.render({
        id: 'teamList'
        , elem: '#teamList'
        , url: '/team/getTeamList'
        , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        , defaultToolbar: ['filter', 'exports', 'print']
        , limit: 10
        , limits: [5, 10, 20, 30]
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'teamName', title: '团队名称', align: 'center', sort: true}
            , {field: 'projectName', title: '项目名称', align: 'center', sort: true}
            , {field: 'members', title: '成员', align: 'center', sort: true}
            , {field: 'adviser', title: '指导教师', align: 'center'}
            , {field: 'content', title: '内容', align: 'center', sort: true}
            , {field: 'status', title: '状态', templet: '#statusTpl', width: 95, align: 'center'}
            , {field: 'competitionTitle', title: '赛事标题', align: 'center', sort: true}
            , {field: 'note', title: '备注', align: 'center', sort: true}
            , {field: 'right', title: '操作', align: 'center', toolbar: "#barDemo", width: 300}
        ]]
        , page: true // 开启分页
        , loading: true
        , where: {timestamp: (new Date()).valueOf()}
    });

    //头工具栏事件
    table.on('toolbar(teamList)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'delTeams':
                var data = checkStatus.data;
                if (data.length == 0) {
                    layer.msg('请选择要删除的报名信息！');
                    break;
                } else {
                    var ids = [];
                    for (var i = 0; i < data.length; i++) {
                        ids.push(data[i].id);
                    }
                    layer.confirm('确定要删除id为： ' + ids + ' 的报名信息吗？', function (index) {
                        $.ajax({
                            url: '/team/deleteTeamByIds',
                            type: "POST",
                            data: {"ids": ids},
                            traditional: true,//解决Ajax异步请求中传数组参数，后台无法接收问题
                            success: function (d) {
                                if (d.code == 0) {
                                    layer.msg("删除成功！", {icon: 1});
                                    table.reload('teamList');
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
                table.reload('teamList');
                break;
        }
    });

    //监听行工具列表
    table.on('tool(teamList)', function (obj) {
        var data = obj.data;
        if (obj.event === 'delete') {
            layer.confirm('确定要删除： ' + data.teamName + ' 参赛的信息吗？', function (index) {
                $.ajax({
                    url: '/team/deleteTeamById',
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
                area: ['100%', '100%'],
                content: "/team/editTeam/" + data.id,
                success: function (layero, index) {
                }
            });
        }else if(obj.event === 'details'){
            var detailsIndex = layer.open({
                type:2,
                title:"查看详情",
                area: ['100%', '100%'],
                content: "/team/teamDetails/" + data.id,
                success: function (layero, index) {
                }
            })
        }
    });

    $(".search_btn").click(function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    })

   /* $(".roomAdd_btn").click(function () {
        var addIndex = layer.open({
            title: "添加课室",
            type: 2,
            area: ['450px', '550px'],
            content: "/classroom/addRoom",
            success: function (layero, index) {

            }
        })
    })*/

});
