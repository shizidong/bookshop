window.onload = function () {

    $('#mainGrid').jqGrid({
        url: $.ctx + '/study/menu/query/allMenu',
        postData: {},
        mtype: "POST",
        datatype: "json",
        colNames: ['菜单编码', '菜单名称', '菜单状态', '菜单类型', '菜单路径', '菜单描述', '操作'],
        colModel: [
            {name: 'menuCode', index: 'menuCode', width: 10, sortable: false, frozen: true, align: "center"},
            {name: 'menuName', index: 'menuName', width: 10, sortable: false, frozen: true, align: "center"},
            {name: 'menuStatus', index: 'menuStatus', width: 10, sortable: false, frozen: true, align: "center"},
            {name: 'menuType', index: 'menuType', width: 10, sortable: false, frozen: true, align: "center"},
            {name: 'menuAddress', index: 'menuAddress', width: 20, sortable: false, frozen: true, align: "center"},
            {name: 'menuDesc', index: 'menuDesc', width: 20, sortable: false, frozen: true, align: "center"},
            {
                name: 'menuId',
                index: 'menuId',
                width: 15,
                sortable: false,
                align: "center",
                autowidth: false,
                key: true,
                title: false,
                formatter: function (value, opts, data) {
                    var html = '<button onclick="menu_mgr_fun_to_detail(\'' + data + '\')" type="button" class="btn btn-default ui-table-btn">查看</button>' +
                        '<button onclick="menu_mgr_fun_to_edit(\'' + data.menuId + "','" + data.menuDesc +  "','"  + data.menuAddress +  "','"  + data.menuType + "','" + data.menuStatus + "','" + data.menuName + "','" + data.menuCode + '\')" ' +
                        'type="button" class="btn btn-default ui-table-btn">修改</button>' + '<button onclick="menu_mgr_fun_to_del(\'' + data.menuId + '\')" type="button" class="btn btn-default  ui-table-btn ui-table-btn">删除</button>';
                    return html;
                }
            }
        ],
        rowList: [10, 20, 30],
        autowidth: true,
        pager: '#mainGridPager', // 定义分页的
        viewrecords: true, // 显示总记录数
        autoScroll: false,
        rownumbers: true, // 是否展示行号
        sortorder: "desc", // 排序顺序，升序或降序
        jsonReader: {
            repeatitems: false,
            id: "0"
        },
        height: '100%',
    });
    $("#mainGrid").jqGrid('setLabel', 0, '序号');
}

function menu_mgr_fun_to_detail(data1) {

    layui.use('layer', function () {
        layer.msg("这是"+data1.menuDesc);
    });
}

function menu_mgr_fun_to_edit(menuId, menuDesc, menuAddress, menuType, menuStatus, menuName, menuCode) {
    layui.use('form', function () {
        var form=layui.form;
        layer.open({
            type: 1,
            area: ['500px', '500px'],
            content: $("#popUpdateTest")
            , success: function (index) {
                $("#Res_name").attr("value", menuName);
                $("#RES_TYPE").attr("value", menuType);
                $("#RES_ADDRESS").attr("value", menuAddress);
                $("#RES_DESC").attr("value", menuDesc);
                $("#RES_status").attr("value", menuStatus);
            }
        });
        setFormValue(menuId, menuDesc, menuAddress, menuType, menuStatus, menuName, menuCode);
        function setFormValue(menuId, menuDesc, menuAddress, menuType, menuStatus, menuName, menuCode) {
            //var type=parseInt(menuType);
                form.on('submit(demo11)', function(massage) {
                    $.ajax({
                        url:'/study/menu/update/menu',
                        type:'POST',
                        ContentType:"application/json charset=utf-8",
                        data:{"menuId":menuId,
                            "menuCode":menuCode,
                            "menuStatus":massage.field.RES_status,
                            "menuName":massage.field.Res_name,
                            "menuType":massage.field.RES_TYPE,
                            "menuAddress":massage.field.RES_ADDRESS,
                            "menuDesc":massage.field.RES_DESC
                        },
                        success:function (data) {
                            layer.msg("修改成功", {icon: 5});
                        }
                    })
                })

        }
    });
}



function menu_mgr_fun_to_del(data) {
    alert("hellword" + data.menuCode);
}
function menu_insert() {
    layui.use('form', function () {
        var form=layui.form;
        layer.open({
            type: 1,
            area: ['600px', '600px'],
            content: $("#popUpdateTest")
            , success: function (index) {
            }
        });setFormValue();
        function setFormValue() {
            form.on('submit(demo11)', function(massage) {
                $.ajax({
                    url:'/study/resource/save/Res',
                    type:'POST',
                    ContentType:"application/json charset=utf-8",
                    dataType: 'json',
                    data:{
                        "menuName":massage.field.Res_name,
                        "resType":massage.field.RES_TYPE,
                        "resAddress":massage.field.RES_ADDRESS,
                        "menuStatus":massage.field.RES_Status,
                        "resDesc":massage.field.RES_DESC
                    },
                    success:function (data) {
                        layer.msg("修改成功", {icon: 5});
                    }
                })
            })

        }
    });
}