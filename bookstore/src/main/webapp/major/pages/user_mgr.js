window.onload = function() {
	
	$('#mainGrid').jqGrid({
		url : $.ctx + '/study/user/query/allUser',
		postData : {},
		mtype : "POST",
		datatype : "json",
		colNames : [ '用户编码','用户昵称','真实姓名','用户状态','是否为管理员','联系电话','密码','操作'],
		colModel : [ 
			{name : 'userCode',index : 'userCode',width : 15,sortable : false,frozen : true,align : "center"},
			{name : 'userName',index : 'userName',width : 15,sortable : false,frozen : true,align : "center"},
			{name : 'userRealname',index : 'userRealname',width : 10,sortable : false,frozen : true,align : "center"},
			{name : 'userStatus',index : 'userStatus',width : 10,sortable : false,frozen : true,align : "center"},
			{name : 'userIsadmin',index : 'userIsadmin',width : 5,sortable : false,frozen : true,align : "center"},
            {name : 'userPhone',index : 'userPhone',width : 5,sortable : false,frozen : true,align : "center"},
			{name : 'userPassword',index : 'userPassword',width : 5,sortable : false,frozen : true,align : "center"},
			{name : 'userId',index : 'userId',width : 15,sortable : false,align : "center",autowidth : false,key : true,title : false,
				formatter : function(value, opts, data) {
				   var html = '<button onclick="user_fun_to_detail(\''+ data.userIsadmin + '\')" type="button" class="btn btn-default ui-table-btn">查看</button>'
						+ '<button onclick="user_fun_to_edit(\''+ data.userId+"','"+data.userPassword+"','"+data.userPhone+"','"+data.userIsadmin+
					   "','"+data.userStatus+"','"+data.userRealname+ "','"+data.userName+ "','"+data.userCode+'\')" type="button" class="btn btn-default ui-table-btn">修改</button>'
						;
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
function user_fun_to_detail(data) {
    layui.use('layer', function() {
        if (data==1){
			layer.msg("该用户是管理员用户");

        }else {
            layer.msg("该用户是普通用户");
		}

	});

}
function user_fun_to_edit(resId,userPassword,userPhone,userIsadmin,userStatus,userRealname,userName,userCode) {
    //转去查看用户资料
    layui.use('form', function () {
        var form=layui.form;
        layer.open({
            type: 1,
            area: ['600px', '600px'],
            content: $("#popUpdateTest")
            , success: function (index) {
                $("#user_name").attr("value", userName);
                $("#user_real_name").attr("value", userRealname);
                $("#user_status").attr("value", userStatus);
                $("#user_isAdmin").attr("value", userIsadmin);
                $("#user_number").attr("value", userPhone);
                $("#user_passord").attr("value", userPassword);
            }
        });setFormValue(resId);
        function setFormValue(resId) {
            form.on('submit(demo11)', function(massage) {
                $.ajax({
                    url:'/study/user/update/user',
                    type:'POST',
                    ContentType:"application/json charset=utf-8",
                    dataType: 'json',
                    data:{"userId":resId,
                        "userName":massage.field.user_name,
                        "userPassword":massage.field.user_passord,
                        "userCode":userCode,
                        "userRealname":massage.field.user_real_name,
                        "userStatus":massage.field.user_status,
                        "userPhone":massage.field.user_number,
                        "userIsadmin":massage.field.user_isAdmin
                    },
                    success:function (data) {
                        layer.msg("修改成功", {icon: 5});
                    }
                })
            })

        }
    });
}
//新增用户
function user_fun_newuser(){
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
                    url:'/study/user/insert/user',
                    type:'POST',
                    ContentType:"application/json charset=utf-8",
                    dataType: 'json',
                    data:{
                        "userName":massage.field.user_name,
                        "userPassword":massage.field.user_passord,
                        "userRealname":massage.field.user_real_name,
                        "userStatus":massage.field.user_status,
                        "userPhone":massage.field.RES_ADDRESS,
                        "userIsadmin":massage.field.user_isAdmin
                    },
                    success:function (data) {
                        layer.msg("修改成功", {icon: 5});
                    }
                })
            })

        }
    });
}