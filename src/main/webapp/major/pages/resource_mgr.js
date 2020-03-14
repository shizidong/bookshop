window.onload = function() {
	
	$('#mainGrid').jqGrid({
		url : $.ctx + '/study/resource/query/allRes',
		postData : {},
		mtype : "POST",
		datatype : "json",
		colNames : [ '资源编码','资源名称','资源类型','资源状态','资源描述','资源地址','操作'],
		colModel : [ 
			{name : 'resCode',index : 'resCode',width : 10,sortable : false,frozen : true,align : "center"},
			{name : 'resName',index : 'resName',width : 15,sortable : false,frozen : true,align : "center"},
			{name : 'resType',index : 'resType',width : 10,sortable : false,frozen : true,align : "center"},
			{name : 'resStatus',index : 'resStatus',width : 10,sortable : false,frozen : true,align : "center"},
			{name : 'resDesc',index : 'resDesc',width : 15,sortable : false,frozen : true,align : "center"},
            {name : 'resAddress',index : 'resAddress',width : 30,sortable : false,frozen : true,align : "left"},
			{name : 'resId',index : 'resId',width : 15,sortable : false,align : "center",autowidth : false,key : true,title : false,
				formatter : function(value, opts, data) {
				   var html = '<button onclick="resource_fun_to_detail(\''+ data.resAddress + '\')" type="button" class="btn btn-default ui-table-btn">查看</button>'
						+ '<button onclick="resource_fun_to_edit(\''+data.resId + "','" + data.resAddress +  "','"  + data.resDesc +  "','"  + data.resStatus + "','" + data.resType + "','" + data.resName + "','" + data.resCode + '\')" type="button" class="btn btn-default ui-table-btn">修改</button>'
						+ '<button onclick="resource_fun_to_del(\''+ data.resId+ '\')" type="button" class="btn btn-default  ui-table-btn ui-table-btn">删除</button>';
				   return html;
				}
			}
		],
		rowList : [ 10, 20, 30 ],
		autowidth : true,
		pager : '#mainGridPager', // 定义分页的
		viewrecords : true, // 显示总记录数
		autoScroll: false, 
		rownumbers : true, // 是否展示行号
		sortorder : "desc", // 排序顺序，升序或降序
		jsonReader : {
			repeatitems : false,
			id : "0"
		},
		height : '100%',
	});
	$("#mainGrid").jqGrid('setLabel',0, '序号');
}
//删除资源状态
function resource_fun_to_del(data) {
    layui.use('layer', function() {
        layer.confirm('真的删除行么', function (index) {
            layer.close(index);
            //向服务端发送删除指令
            var resourceId = data;
            $.ajax({
                url: $.ctx + '/study/resource/delete/resourceSelf',
                type: 'post',
                cache: false,
                dataType: 'json',
                data: {"resourceId": resourceId},
                success: function (data) {
                    layer.msg('删除成功');
                }
            });

        });
    });
}
//查看细节
function resource_fun_to_detail(data) {
    window.open(data);
}
function resource_fun_to_edit(resId,resAddress,resDesc,resStatus,resType,resName,resCode) {
    //转去查看资源
    layui.use('form', function () {
        var form=layui.form;
        layer.open({
            type: 1,
            area: ['500px', '500px'],
            content: $("#popUpdateTest")
            , success: function (index) {
                $("#Res_name").attr("value", resName);
                $("#RES_TYPE").attr("value", resType);
                $("#RES_ADDRESS").attr("value", resAddress);
                $("#RES_DESC").attr("value", resDesc);
                $("#RES_Status").attr("value", resStatus);
            }
        });setFormValue(resId);
        function setFormValue(resId) {
            form.on('submit(demo11)', function(massage) {
                $.ajax({
                    url:'/study/resource/update/Res',
                    type:'POST',
                    ContentType:"application/json charset=utf-8",
                    dataType: 'json',
                    data:{"resId":resId,
                        "resCode":resCode,
                        "resStatus":massage.field.RES_Status,
                        "resName":massage.field.Res_name,
                        "resType":massage.field.RES_TYPE,
                        "resAddress":massage.field.RES_ADDRESS,
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
//新增资源
function resource_insert(){
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
                        "resName":massage.field.Res_name,
                        "resType":massage.field.RES_TYPE,
                        "resAddress":massage.field.RES_ADDRESS,
                        "resStatus":massage.field.RES_Status,
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