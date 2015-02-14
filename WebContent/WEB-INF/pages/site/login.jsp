<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<link href="<%=request.getContextPath() %>/resources/css/style.css" rel="stylesheet" type="text/css">
<jsp:include page="./common/js.jsp"></jsp:include> 

<body style="background:#FFF;">
	<jsp:include page="./common/head.jsp" flush="true">
		<jsp:param name="showlogin" value="false"></jsp:param>
		<jsp:param name="showreg" value="true"></jsp:param>
	</jsp:include>  
	<jsp:include page="./common/subheader.jsp" flush="true">
		<jsp:param name="showshouye" value="true"></jsp:param>
	</jsp:include>  

	<div style="border-radius:10px; width:400px; margin:10px auto 30px; padding-bottom:30px; border:solid 1px #ccc;">
			<table  cellspacing="0" cellpadding="0" class="center" style="margin-top:30px;width: 80%;border: 0;">
				<tr>
						<td align="right"><span style="color:#09F; font-weight:bold">用户登录</span></td>
						<td align="right"></td>
				</tr>
				<tr>
						<td align="right">&nbsp;</td>
						<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">用户名：</td>
					<td><input id="username" type="text" name="textfield"  style="padding:10px 5px; width:250px;"></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td><span style="color:#F00" id="username_error"> </span></td>
				</tr>
				<tr>
						<td align="right">密码：</td>
						<td><input id="password" type="password" name="password"   style="padding:10px 5px; width:250px;" placeholder=""></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td><span style="color:#F00" id="password_error"></span></td>
				</tr>
				<tr>
						<td align="right">验证码：</td>
						<td>
							<input id="yanzhengma" type="text" name="yanzhengma"   style="width:170px;height:30px;padding-top: 5px;  " placeholder="">
							<img title="点击更换" id="yanzhengmaBtn" style="width:80px;" onclick="javascript:refreshYanZhengMa(this);" src="<%=request.getContextPath()%>/yanzhengma">
						</td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td><span style="color:#F00" id="yanzhengma_error"></span></td>
				</tr>
			</table>
			<div class="tac">
				<a href="javascript:loginfn(this);" style="display:inline-block; border-radius:5px; background-color:#09F; width:80%; color:#fff; line-height:40px; height:40px;" class="tac" id = "loginBtn">登录</a>
			</div>
				<div style="text-align:right; padding-top:20px; padding-right:40px;"><a href="<%=request.getContextPath() %>/reg" style="">免费注册</a></div>

	</div>
	<jsp:include page="./common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
$(function(){
	$("#username").val("");
	$("#username").focus(); 
	$("#username").focus(function(){
		$("#username_error").html("");
	});
	 
 
	$("#password").focus(function(){
		$("#password_error").html("");
	});
	$("#yanzhengma").focus(function(){
		$("#yanzhengma_error").html("");
	}); 
	 
})
function refreshYanZhengMa(obj) {  $(obj).attr("src","<%=request.getContextPath()%>/yanzhengma?"+Math.random());  }
function loginfn(btn){
  	var name = $("#username").val();
  	var pass = $("#password").val();
  	var yanzhengma = $("#yanzhengma").val();
  	/** **/
  	var usernameresult = checkUserName(name);
  	if(!usernameresult)return;
  	var passresult = checkpassword();
  	if(!passresult)return;
  	var yanzhengmaResult = checkyanzhengma();
  	if(!yanzhengmaResult)return;
  	
  	var url = "<%=request.getContextPath()%>/login";
	$.ajax({
			  type:'post',
			  url: url,
			  context: document.body,
			  beforeSend:function(){
			  		// $(btn).attr('disabled','disabled');
				  $("#loginBtn").attr("disabled",true); 
				  $("#loginBtn").html("正在登录中。。。");
				  
				 },
			  data:{
				  username: name,password:pass,yanzhengma:yanzhengma
			  },
			  success:function(json){
				  $("#loginBtn").attr("disabled",false); 
				  $("#loginBtn").html("登录");
			  		//json = jQuery.parseJSON(json);
				 	 var result = json["result"];
				 	 if(result){
				 	 	window.location = "<%=request.getContextPath()%>/useraccount";
				 	 }else{
				 	 	log.info("登录失败："+json); 
				 	 }
				 	  
			  },
		      error:function(xhr,textStatus,errorThrown){
		    	  $("#loginBtn").attr("disabled",false); 
		    	  $("#loginBtn").html("登录");
		    	  
		    	 
		    	  refreshYanZhengMa( );
		  		 
		  		var responseText = xhr.responseText;
		  		var obj = jQuery.parseJSON(responseText);
				var errortype = obj.errortype
		  		var msg = obj.msg;
				if(errortype == 'password'){
					$("#password_error").html(msg);
				} else if(errortype == 'username'){
					$("#username_error").html(msg);
				} else if(errortype == 'yanzhengma'){
					$("#yanzhengma_error").html(msg);
				}
		  } 
  })
 }
function checkEmail(str){
    var re = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/gi;
    if(re.test(str)){
       	return true;
    }else{
       return false;
    }
}
function checkUserName(){
	var username = $("#username").val();
	if($.trim(username).length == 0 ){
		$("#user_name_error").html("请输入用户名");
		return false;
	}else if($.trim(username).length >= 41 ){
		$("#user_name_error").html("用户名长度不能超过40");
		return false;
	}else if($.trim(username).length <= 3 ){
		$("#user_name_error").html("用户名长度至少4位");
		return false;
	}
	return true;
}
function checkpassword(){
	var password = $.trim($("#password").val());
	if(password.length == 0 ){
		$("#password_error").html("密码不能为空");
		return false;
	} 
	return true;
}
function checkyanzhengma(){
	var yanzhengma = $.trim($("#yanzhengma").val());
	if(yanzhengma.length == 0 ){
		$("#yanzhengma_error").html("验证码不能为空");
		return false;
	}else if(yanzhengma.length != 4 ){
		$("#yanzhengma_error").html("验证码输入错误");
		return false;
	}
	return true;
}
</script>
</html>