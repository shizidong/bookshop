/**
 * 对jquery能力的扩展。
 * 分成两个部分：
 * 1. 对jquery本身能力的扩展，主要提供一些jquery的函数；使用 $.extend({...});
 * 2. 写一些小组件。使用$.fn.extend({...})扩展。
 */
$.browser={};
$.browser.msie = false;
$.browser.version = '9.0';
// 第一部分。
$.extend({
	
	//键值操作-放value
	kvSet : function(key,value){
		var ssg = window.sessionStorage;
		if(ssg){
			ssg.setItem(key,value);
		}
	},
	//键值操作-得到value
	kvGet : function(key){
		var ssg = window.sessionStorage;
		if(ssg && ssg.getItem(key)){
			return ssg.getItem(key);
		}else{
			return false;
		}
	},
	//拿到当前用户的登录账号
	getUserName : function(){
		return $.getCookie("userName");
	},
	
	//拿到地址栏里面的参数
	getUrlParam : function(name){
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return null;
	},
	getCookie : function(name){
	    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	    if(arr=document.cookie.match(reg)){
	    	return unescape(arr[2]);
	    }else{
	    	return null;
	    } 
	},
	/**
     * 写入cookie  expiresHours过期时间，单位是小时
     */
    addCookie : function(name,value) {
    	var Days = 30;
    	var exp = new Date(); 
    	exp.setTime(exp.getTime() + Days*24*60*60*1000);
    	var cookieString=name+"="+escape(value); 
    	document.cookie=cookieString + ' ;expires=' + exp.toGMTString()+ ' ;path=/';
    },
	escape2Html : function(str) {//去掉特殊字符
	   	var arrEntities={'lt':'<','gt':'>','nbsp':' ','amp':'&','quot':'"'};
	   	return str.replace(/&(lt|gt|nbsp|amp|quot);/ig,function(all,t){return arrEntities[t];});
	},
	setCurrentToken : function(token,refreshToken,userName){
		var minute = 480;//默认保存8个小时（单位为分钟）
		$.ajax({
			async:false,
			url:$.ctx + "/api/config/jauthSpringConfig",
			data:{"key":"demo.security.jwt.tokenExpirationTime"},
			type:"post",
			success:function(data){
				if(data.data){
					minute = parseInt(data.data);
				}
			}
		})
		var exp  = new Date();  //获得当前时间
		exp.setTime(exp.getTime() + minute*60*1000);  //换成毫秒
		if($.ctx){
			document.cookie = "token="+ token + ";expires=" + exp.toGMTString()+";path="+$.ctx;
			if(userName){
				document.cookie = "userName="+ userName.toLowerCase() + ";expires=" + exp.toGMTString()+";path="+$.ctx;
			}
		}else{
			document.cookie = "token="+ token + ";expires=" + exp.toGMTString()+";path=/";
			if(userName){
				document.cookie = "userName="+ userName.toLowerCase() + ";expires=" + exp.toGMTString()+";path=/";
			}
		}
	},
	isExistsToken : function(){
		var token=$.getCookie("token");
		
		var tokenStr = token;
		if(!tokenStr){
			return false;
		}else{
			return true;
		}
	},
	getCurrentToken : function(){
		var token=$.getCookie("token");
		return token;
	},
	//通用异步请求
	commAjax	: function(options, el) {
		options = $.extend({
			url			: '',
			isShowMask	: false,
			type		: 'POST',
			postData	: {},
			beforeSend	: false,
			onSuccess	: false,
			onFailure	: false,
			timeout		: 1800000,
			async		: true,
			checkSubmitted:false,
			maskMassage	: 'Loading' // 等待提示信息
		}, options);
		
		if(options.checkSubmitted){
			//判断当前是否已经有提交动作，如果有则不提交
			if($.checkRun()){
				return;
			}
		}
		
		if (!el) {
			el = $('body');
		}
		if (options.isShowMask && el.length > 0) {
			el.mask({
				top		: el.offset().top,
				left	: el.offset().left,
				width	: el.width(),
				height	: el.height()+$(document).scrollTop(),
				message	: options.maskMassage
			});
		}
		
		var tokenStr=$.getCurrentToken();
		
		$.ajax({
			headers 	: {'X-Authorization': tokenStr},
			url			: options.url,
			type		: options.type,
			data		: options.postData,
			beforeSend	: options.beforeSend,
			timeout		: options.timeout,
			async		: options.async,
			cache		: false,
			complete	: function(req, st) {
				
				if (options.isShowMask) {
					el.unmask();
				}
				// status：200为服务中成功的状态，0为本地打开时的成功状态
				if ((req.status == 200 || req.status == 0)) { //TODO
					var obj;
					try {
						obj = jQuery.parseJSON(req.responseText);
					} catch (e) {
						obj = req.responseText;
					}
					if (obj && obj.status != 200) {
						if ($.isFunction(options.onFailure)) {
							try {
								console.log(obj.exception);
								options.onFailure.call(this, obj);
							} catch (e) {
								$.alert("系统无法响应请求，请联系管理员");
							}
						} else if (obj.msg) {
							$.alert(obj.msg);
							try {console.log(obj.exception);} catch (e) {}
						}
					} else {
						if ($.isFunction(options.onSuccess))
							options.onSuccess.call(this, obj);
					}
				} else if (st == 'error') {
					if(req.status == "404"){
						$.alert('未找到对应请求。');
					}else if(req.status == "401"){
						$.alert('登录超时，点击确认重新登录。',function(){
							 location.href = $.ctx ? $.ctx : "/";
						});
					}
				} else if (st == 'timeout') {
					$.alert('连接超时，请刷新后重试。');
				} else {
					$.alert('连接失败，请检查网络。');
				}
			}
		});
	},
	strLen		: function(str) {// 判断字符长度(汉字算三个长度)
		str = $.trim(str);
		var len = 0;
		for (var i = 0; i < str.length; i++) {
			if (str.charCodeAt(i) > 256) {
				len += 3;
			} else {
				len++;
			}
		}
		return len;
	},
	isNull	: function(obj) {
		if (obj == null || (typeof(obj) == 'string' && $.trim(obj) == '')
		        || (typeof(obj) == 'object' && $.isEmptyObject(obj))) {
			return true;
		}
		return false;
	},
	dateFormat : function(date, format){
		if(!format){
			format = 'yyyy-MM-dd';
		}
		var o = {   
	      "M+" : date.getMonth()+1, //month  
	      "d+" : date.getDate(),    //day  
	      "H+" : date.getHours(),   //hour  
	      "m+" : date.getMinutes(), //minute  
	      "s+" : date.getSeconds(), //second  ‘
		  //quarter  
	      "q+" : Math.floor((date.getMonth()+3)/3), 
	      "S" : date.getMilliseconds() //millisecond  
	    }   
	    if(/(y+)/.test(format)) format=format.replace(RegExp.$1,(date.getFullYear()+"").substr(4 - RegExp.$1.length));      
	    for(var k in o){
	   	   if(new RegExp("("+ k +")").test(format)){   
	      	   format = format.replace(RegExp.$1,   
	        	 RegExp.$1.length==1 ? o[k] :    
	          	("00"+ o[k]).substr((""+ o[k]).length));  
	       }
	    } 
	    return format;  
	},
	
});
