//开始文件上传
function ziyuanshangchaun(){
    $("#iframe").attr('src','fileUpload.html');
}
var menuList = [];



window.onload = function() {
	var userAdmin= false;
	var token=$.kvGet("token");
	var status=token.charAt(token.length-1);
	
	if($.kvGet("address")==""){
		$.kvSet("address","./home_mgr.html")
	}
	$('iframe').attr('src', $.kvGet("address"));
	$.ajax({
		url : $.ctx + '/study/menu/query/allMenu',
		type : 'post',
		cache : false,
		contentType : 'application/json',
		dataType : 'json',
		data : "",
		success : function(data) {
			menuList=data;
			if(status =="1"){
				new Vue({ el:'#menuList', data: {menuList:data} });
			}
		}
	});
}
// 控制下拉
layui.use('element', function() {
	var element = layui.element;
});

//选则菜单修改内容区
var changMenu = function(menuAddress) {
	for (var i = 0; i < menuList.length; i++) {
		var menu = menuList[i];
		if (menu.menuName == menuAddress) {
			$.kvSet("address",menu.menuAddress)
			return ;
		}
	}
}


/*点击四级资料生成的列表*/
function sijiziliao() {
    layui.use(['table','form'], function(){
        var table = layui.table;
        var form=layui.form;
        $("#iframe").hide();
        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 600
            ,url: '/study/resource/query/sijiziliao' //数据接口
            ,page: true //开启分页
            ,method:"post"
            ,cols: [[ //表头
                {field: 'resId', title: '资源ID', width:150, sort: true, fixed: 'left'}
                ,{field: 'resName', title: '资源名称', width:150}
                ,{field: 'resAddress', title: '资源地址', width:500, sort: true}
                ,{field: 'resType', title: '资源类型', width:150}
                ,{field: 'resStatus', title: '资源状态', width:150 }
                ,{field: 'resDesc', title: '资源描述', width: 150, sort: true}
                ,{fixed: 'right', width: 100, align:'center', toolbar: '#barDemo'}
            ]]
        });
        /*//监听头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                ,data = checkStatus.data; //获取选中的数据
            switch(obj.event){
                case 'add':
                    layer.msg('添加');
                    break;
                case 'update':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else if(data.length > 1){
                        layer.msg('只能同时编辑一个');
                    } else {
                        layer.alert('编辑 [id]：'+ checkStatus.data[0].id);
                    }
                    break;
                case 'delete':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else {
                        layer.msg('删除');
                    }
                    break;
            };
        });*/
        /*--------*/
        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                //layer.msg('查看操作'+data.resAddress);
                window.location.href=data.resAddress;
            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                    var  resourceId=data.resId;
                    $.ajax({
                        url : $.ctx + '/study/resource/delete/resourceSelf',
                        type : 'post',
                        cache : false,
                        dataType : 'json',
                        data : {"resourceId":resourceId},
                        success : function(msg) {
                            layer.msg('删除成功');
                        }
                    });

                });
            } else if(layEvent === 'edit'){

                layer.open({
                    type:1,
                    area: ['500px', '500px'],
                    content: $("#popUpdateTest")
                    ,success:function (index) {
                       $("#Res_name").attr("value",data.resName);
                       $("#RES_TYPE").attr("value",data.resType);
                        $("#RES_ADDRESS").attr("value",data.resAddress);
                        $("#RES_DESC").attr("value",data.resDesc);
                    }
                });
                setFormValue(data);
            }
        });
        function setFormValue(data){
            layer.msg("这是id"+data.resId);
            form.on('submit(demo11)', function(massage) {

                $.ajax({
                    url:'/study/resource//update/Res',
                    type:'POST'
                    ,dataType:'text'
                    ,data:{"RES_ID":data.resId,
                        "RES_NAME":massage.field.Res_name,
                        "RES_TYPE":massage.field.RES_TYPE,
                        "RES_ADDRESS":massage.field.RES_ADDRESS,
                        "RES_DESC":massage.field.RES_DESC,
                    },
                    success:function (data) {
                        layer.closeAll('loading');
                    }
                })
            })

        }
    });
}
/*点击六级资料生成的列表*/
function liujiziliao() {
    layui.use('table', function(){
        var form=layui.form;
        var table = layui.table;
        $("#iframe").hide();
        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 600
            ,url: '/study/resource/query/liujiziliao' //数据接口
            ,page: true //开启分页
            ,method:"post"
            ,cols: [[ //表头
                {field: 'resId', title: '资源ID', width:150, sort: true, fixed: 'left'}
                ,{field: 'resName', title: '资源名称', width:150}
                ,{field: 'resAddress', title: '资源地址', width:500, sort: true}
                ,{field: 'resType', title: '资源类型', width:150}
                ,{field: 'resStatus', title: '资源状态', width:150 }
                ,{field: 'resDesc', title: '资源描述', width: 150, sort: true}
                ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
            ]]
        });
        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                window.location.href=data.resAddress;

            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                    var  resourceId=data.resId;
                    $.ajax({
                        url : $.ctx + '/study/resource/delete/resourceSelf',
                        type : 'post',
                        cache : false,
                        dataType : 'json',
                        data : {"resourceId":resourceId},
                        success : function(data) {
                            layer.msg('删除成功');
                        }
                    });

                });
            } else if(layEvent === 'edit'){
                layer.open({
                    type:1,
                    area: ['500px', '500px'],
                    content: $("#popUpdateTest")
                    ,success:function (index) {
                        $("#Res_name").attr("value",data.resName);
                        $("#RES_TYPE").attr("value",data.resType);
                        $("#RES_ADDRESS").attr("value",data.resAddress);
                        $("#RES_DESC").attr("value",data.resDesc);
                    }
                });
                setFormValue(data);
            }
        });
        function setFormValue(data){
            layer.msg("这是id"+data.resId);
            form.on('submit(demo11)', function(massage) {

                $.ajax({
                    url:'/study/resource//update/Res',
                    type:'POST',
                    data:{"RES_ID":data.resId,
                        "RES_NAME":massage.field.Res_name,
                        "RES_TYPE":massage.field.RES_TYPE,
                        "RES_ADDRESS":massage.field.RES_ADDRESS,
                        "RES_DESC":massage.field.RES_DESC,
                    },
                    success:function (data) {
                        layer.msg("修改失败", {icon: 5});
                    }
                })
            })

        }

    });
}
/*点击四级视频生成的列表*/
function sijishiping() {
    layui.use('table', function(){
        var form=layui.form;
        var table = layui.table;
        $("#iframe").hide();
        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 600
            ,url: '/study/resource/query/sijishiping' //数据接口
            ,page: true //开启分页
            ,method:"post"
            ,cols: [[ //表头
                {field: 'resId', title: '资源ID', width:150, sort: true, fixed: 'left'}
                ,{field: 'resName', title: '资源名称', width:150}
                ,{field: 'resAddress', title: '资源地址', width:500, sort: true}
                ,{field: 'resType', title: '资源类型', width:150}
                ,{field: 'resStatus', title: '资源状态', width:150 }
                ,{field: 'resDesc', title: '资源描述', width: 150, sort: true}
                ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
            ]]
        });
        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                window.location.href=data.resAddress;

            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                    var  resourceId=data.resId;
                    $.ajax({
                        url : $.ctx + '/study/resource/delete/resourceSelf',
                        type : 'post',
                        cache : false,
                        dataType : 'json',
                        data : {"resourceId":resourceId},
                        success : function(data) {
                            layer.msg('删除成功');
                        }
                    });

                });
            } else if(layEvent === 'edit'){
                layer.open({
                    type:1,
                    area: ['500px', '500px'],
                    content: $("#popUpdateTest")
                    ,success:function (index) {
                        $("#Res_name").attr("value",data.resName);
                        $("#RES_TYPE").attr("value",data.resType);
                        $("#RES_ADDRESS").attr("value",data.resAddress);
                        $("#RES_DESC").attr("value",data.resDesc);
                    }
                });
                setFormValue(data);
            }
        });
        function setFormValue(data){
            layer.msg("这是id"+data.resId);
            form.on('submit(demo11)', function(massage) {

                $.ajax({
                    url:'/study/resource//update/Res',
                    type:'POST',
                    data:{"RES_ID":data.resId,
                        "RES_NAME":massage.field.Res_name,
                        "RES_TYPE":massage.field.RES_TYPE,
                        "RES_ADDRESS":massage.field.RES_ADDRESS,
                        "RES_DESC":massage.field.RES_DESC,
                    },
                    success:function (data) {
                        layer.msg("修改失败", {icon: 5});
                    }
                })
            })

        }

    });

}
/*点击六级视频生成的列表*/
function liujishiping() {
    layui.use('table', function(){
        var table = layui.table;
        var form=layui.form;
        $("#iframe").hide();
        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 600
            ,url: '/study/resource/query/liujishiping' //数据接口
            ,page: true //开启分页
            ,method:"post"
            ,cols: [[ //表头
                {field: 'resId', title: '资源ID', width:150, sort: true, fixed: 'left'}
                ,{field: 'resName', title: '资源名称', width:150}
                ,{field: 'resAddress', title: '资源地址', width:500, sort: true}
                ,{field: 'resType', title: '资源类型', width:150}
                ,{field: 'resStatus', title: '资源状态', width:150 }
                ,{field: 'resDesc', title: '资源描述', width: 150, sort: true}
                ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
            ]]
        });
        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                window.location.href=data.resAddress;

            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                    var  resourceId=data.resId;
                    $.ajax({
                        url : $.ctx + '/study/resource/delete/resourceSelf',
                        type : 'post',
                        cache : false,
                        dataType : 'json',
                        data : {"resourceId":resourceId},
                        success : function(data) {
                            layer.msg('删除成功');
                        }
                    });

                });
            } else if(layEvent === 'edit'){
                layer.open({
                    type:1,
                    area: ['500px', '500px'],
                    content: $("#popUpdateTest")
                    ,success:function (index) {
                        $("#Res_name").attr("value",data.resName);
                        $("#RES_TYPE").attr("value",data.resType);
                        $("#RES_ADDRESS").attr("value",data.resAddress);
                        $("#RES_DESC").attr("value",data.resDesc);
                    }
                });
                setFormValue(data);
            }
        });
        function setFormValue(data){
            layer.msg("这是id"+data.resId);
            form.on('submit(demo11)', function(massage) {

                $.ajax({
                    url:'/study/resource//update/Res',
                    type:'POST',
                    data:{"RES_ID":data.resId,
                        "RES_NAME":massage.field.Res_name,
                        "RES_TYPE":massage.field.RES_TYPE,
                        "RES_ADDRESS":massage.field.RES_ADDRESS,
                        "RES_DESC":massage.field.RES_DESC,
                    },
                    success:function (data) {
                        layer.msg("修改失败", {icon: 5});
                    }
                })
            })

        }

    });

}
/*点击数学资料生成的列表*/
function shuxueziliao() {
    layui.use('table', function(){
        var form=layui.form;
        var table = layui.table;
        $("#iframe").hide();
        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 600
            ,url: '/study/resource/query/shuxueziliao' //数据接口
            ,page: true //开启分页
            ,method:"post"
            ,cols: [[ //表头
                {field: 'resId', title: '资源ID', width:150, sort: true, fixed: 'left'}
                ,{field: 'resName', title: '资源名称', width:150}
                ,{field: 'resAddress', title: '资源地址', width:500, sort: true}
                ,{field: 'resType', title: '资源类型', width:150}
                ,{field: 'resStatus', title: '资源状态', width:150 }
                ,{field: 'resDesc', title: '资源描述', width: 150, sort: true}
                ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
            ]]
        });
        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                window.location.href=data.resAddress;

            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                    var  resourceId=data.resId;
                    $.ajax({
                        url : $.ctx + '/study/resource/delete/resourceSelf',
                        type : 'post',
                        cache : false,
                        dataType : 'json',
                        data : {"resourceId":resourceId},
                        success : function(data) {
                            layer.msg('删除成功');
                        }
                    });

                });
            } else if(layEvent === 'edit'){
                layer.open({
                    type:1,
                    area: ['500px', '500px'],
                    content: $("#popUpdateTest")
                    ,success:function (index) {
                        $("#Res_name").attr("value",data.resName);
                        $("#RES_TYPE").attr("value",data.resType);
                        $("#RES_ADDRESS").attr("value",data.resAddress);
                        $("#RES_DESC").attr("value",data.resDesc);
                    }
                });
                setFormValue(data);
            }
        });
        function setFormValue(data){
            layer.msg("这是id"+data.resId);
            form.on('submit(demo11)', function(massage) {

                $.ajax({
                    url:'/study/resource//update/Res',
                    type:'POST',
                    data:{"RES_ID":data.resId,
                        "RES_NAME":massage.field.Res_name,
                        "RES_TYPE":massage.field.RES_TYPE,
                        "RES_ADDRESS":massage.field.RES_ADDRESS,
                        "RES_DESC":massage.field.RES_DESC,
                    },
                    success:function (data) {
                        layer.msg("修改失败", {icon: 5});
                    }
                })
            })

        }
    });

}
/*function setFormValue(data){
    layer.msg("这是id"+data.resId);
    form.on('submit(demo11)', function(massage) {

        $.ajax({
            url:'/study/resource//update/Res',
            type:'POST',
            data:{"RES_ID":data.resId,
                "RES_NAME":massage.field.Res_name,
                "RES_TYPE":massage.field.RES_TYPE,
                "RES_ADDRESS":massage.field.RES_ADDRESS,
                "RES_DESC":massage.field.RES_DESC,
            },
            success:function (data) {
                layer.msg("修改失败", {icon: 5});
            }
        })
    })

}*/
/*点击数学视频生成的列表*/
function shuxueshiping() {
    layui.use('table', function(){
        var table = layui.table;
        var form=layui.form;
        $("#iframe").hide();
        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 600
            ,url: '/study/resource/query/shuxueship' //数据接口
            ,page: true //开启分页
            ,method:"post"
            ,cols: [[ //表头
                {field: 'resId', title: '资源ID', width:150, sort: true, fixed: 'left'}
                ,{field: 'resName', title: '资源名称', width:150}
                ,{field: 'resAddress', title: '资源地址', width:500, sort: true}
                ,{field: 'resType', title: '资源类型', width:150}
                ,{field: 'resStatus', title: '资源状态', width:150 }
                ,{field: 'resDesc', title: '资源描述', width: 150, sort: true}
                ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
            ]]
        });
        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                window.location.href=data.resAddress;

            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                    var  resourceId=data.resId;
                    $.ajax({
                        url : $.ctx + '/study/resource/delete/resourceSelf',
                        type : 'post',
                        cache : false,
                        dataType : 'json',
                        data : {"resourceId":resourceId},
                        success : function(data) {
                            layer.msg('删除成功');
                        }
                    });

                });
            } else if(layEvent === 'edit'){
                layer.open({
                    type:1,
                    area: ['500px', '500px'],
                    content: $("#popUpdateTest")
                    ,success:function (index) {
                        $("#Res_name").attr("value",data.resName);
                        $("#RES_TYPE").attr("value",data.resType);
                        $("#RES_ADDRESS").attr("value",data.resAddress);
                        $("#RES_DESC").attr("value",data.resDesc);
                    }
                });
                setFormValue(data);
            }
        });
        function setFormValue(data){
            layer.msg("这是id"+data.resId);
            form.on('submit(demo11)', function(massage) {

                $.ajax({
                    url:'/study/resource//update/Res',
                    type:'POST',
                    data:{"RES_ID":data.resId,
                        "RES_NAME":massage.field.Res_name,
                        "RES_TYPE":massage.field.RES_TYPE,
                        "RES_ADDRESS":massage.field.RES_ADDRESS,
                        "RES_DESC":massage.field.RES_DESC,
                    },
                    success:function (data) {
                        layer.msg("修改失败", {icon: 5});
                    }
                })
            })

        }
    });

}
/*点击计算机资料生成的列表*/
function jisuanjiziyuan() {
    layui.use('table', function(){
        var table = layui.table;
        var form=layui.form;
        $("#iframe").hide();
        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 600
            ,url: '/study/resource/query/jisuanjiziliao' //数据接口
            ,page: true //开启分页
            ,method:"post"
            ,cols: [[ //表头
                {field: 'resId', title: '资源ID', width:150, sort: true, fixed: 'left'}
                ,{field: 'resName', title: '资源名称', width:150}
                ,{field: 'resAddress', title: '资源地址', width:500, sort: true}
                ,{field: 'resType', title: '资源类型', width:150}
                ,{field: 'resStatus', title: '资源状态', width:150 }
                ,{field: 'resDesc', title: '资源描述', width: 150, sort: true}
                ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
            ]]
        });
        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                window.location.href=data.resAddress;

            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                    var  resourceId=data.resId;
                    $.ajax({
                        url : $.ctx + '/study/resource/delete/resourceSelf',
                        type : 'post',
                        cache : false,
                        dataType : 'json',
                        data : {"resourceId":resourceId},
                        success : function(data) {
                            layer.msg('删除成功');
                        }
                    });

                });
            } else if(layEvent === 'edit'){
                layer.open({
                    type:1,
                    area: ['500px', '500px'],
                    content: $("#popUpdateTest")
                    ,success:function (index) {
                        $("#Res_name").attr("value",data.resName);
                        $("#RES_TYPE").attr("value",data.resType);
                        $("#RES_ADDRESS").attr("value",data.resAddress);
                        $("#RES_DESC").attr("value",data.resDesc);
                    }
                });
                setFormValue(data);
                layer.msg("操作成功");
            }
        });
        function setFormValue(data){
            layer.msg("这是id"+data.resId);
            form.on('submit(demo11)', function(massage) {
                $.ajax({
                    url:'/study/resource/update/Res',
                    type:'POST',
                    data:{"RES_ID":data.resId,
                        "RES_NAME":massage.field.Res_name,
                        "RES_TYPE":massage.field.RES_TYPE,
                        "RES_ADDRESS":massage.field.RES_ADDRESS,
                        "RES_DESC":massage.field.RES_DESC,
                    },
                    success:function (data) {

                    }
                })
            })

        }
    });

}
//打卡时间进行
function daka(){
    layui.use('layer', function(){
                var layer = layui.layer;
                layer.msg('打卡成功');

    });

}
