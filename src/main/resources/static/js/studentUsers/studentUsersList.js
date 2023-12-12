layui.use(['form', 'layer', 'jquery', 'table'], function () {
    var layer = layui.layer,
        $ = layui.jquery,
        form = layui.form,
        table = layui.table;
    active = {
        search: function () {
            var studentId = $('#studentId');
            var name = $('#name');
            var sex = $('#sex option:selected');

            table.reload('studentUsersList', {
                page: {
                    curr: 1
                },
                where: {
                    studentId: studentId.val(),
                    name: name.val(),
                    sex: sex.val(),
                }
            });
        }
    };

    table.render({
        id: 'studentUsersList'
        , elem: '#studentUsersList'
        , url: '/studentUsers/getStudentUserList'
        , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        , defaultToolbar: ['filter', 'exports', 'print']
        , limit: 10
        , limits: [5, 10, 20, 30]
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'studentId', title: '学号', align: 'center'}
            , {field: 'password', title: '密码(加密)', align: 'center'}
            , {field: 'name', title: '姓名', align: 'center'}
            , {field: 'sex', title: '性别', align: 'center', templet: '#sexTpl', sort: true}
            , {field: 'right', title: '操作', align: 'center', toolbar: "#barDemo"}
        ]]
        , page: true // 开启分页
        , loading: true
        , where: {timestamp: (new Date()).valueOf()}
    });


    //头工具栏事件
    table.on('toolbar(studentUsersList)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'delStudentUsers':
                var data = checkStatus.data;
                if (data.length == 0) {
                    layer.msg('请选择要删除的学生信息！');
                    break;
                } else {
                    var ids = [];
                    for (var i = 0; i < data.length; i++) {
                        ids.push(data[i].id);
                    }
                    layer.confirm('确定要删除id为： ' + ids + ' 的学生信息吗？', function (index) {
                        $.ajax({
                            url: '/studentUsers/deleteStudentUsersByIds',
                            type: "POST",
                            data: {"ids": ids},
                            traditional: true,//解决Ajax异步请求中传数组参数，后台无法接收问题
                            success: function (d) {
                                if (d.code == 0) {
                                    layer.msg("删除成功！", {icon: 1});
                                    table.reload('studentUsersList');
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
                table.reload('studentUsersList');
                break;
        }
    });

    //监听行工具列表
    table.on('tool(studentUsersList)', function (obj) {
        var data = obj.data;
        if (obj.event === 'delete') {
            layer.confirm('确定要删除学号为： ' + data.studentId + ' 的信息吗？', function (index) {
                $.ajax({
                    url: '/studentUsers/deleteStudentUsersById',
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
                title: "编辑学生信息",
                area: ['450px', '400px'],
                content: "/studentUsers/editStudentUsers/" + data.id,
                success: function (layero, index) {

                }
            });
        }else if (obj.event === 'details') {
            var detailsIndex = layer.open({
                type: 2,
                title: "查看学生详细信息",
                area: ['600px', '550px'],
                content: "/studentUsers/studentUsersDetails/" + data.id,
                success: function (layero, index) {

                }
            });
        }else if(obj.event === 'reset') {
            var detailsIndex = layer.open({
                type: 2,
                title: "警告！",
                area: ['360px', '168px'],
                content: "/studentUsers/resetPass/" + data.id,
                success: function (layero, index) {

                }
            });
        }
    });

    $(".search_btn").click(function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    })

    $(".studentUsersAdd_btn").click(function () {
        var addIndex = layer.open({
            title: "添加学生信息",
            type: 2,
            area: ['450px', '400px'],
            content: "/studentUsers/addStudentUsers",
            success: function (layero, index) {

            }
        })
    })

});
