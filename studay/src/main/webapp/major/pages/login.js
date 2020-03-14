$(function() {
	$("#loginSubmit").click(function() {
		Login();
		return false;
	});
});

var remove1 = function() {
	var userName = $("#userName").val();
	if (typeof userName == "undefined" || userName == null || userName == "") {
		alert("用户名不能为空！！");
		return false;
	}

}
var remove2 = function() {
	var userPassword = $("#userPassword").val();
	if (typeof userPassword == "undefined" || userPassword == null
			|| userPassword == "") {
		alert("密码不能为空！！");
		return false;
	}
}

function Login() {
	var data = $("#login").formToJson();
	$.ajax({
		url : $.ctx + '/study/user/login',
		cache : false,
		type : 'post',
		data : data,
		success : function(returnObj) {
			if (returnObj && returnObj.data != 'false') {
				$.kvSet("token",returnObj.data)
				location.href = $.forward;
			} else {
				alert(returnObj.msg);
			}
		}
	});
}
