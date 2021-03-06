<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<jsp:include page="./common/css.jsp"></jsp:include>
<jsp:include page="./common/mina.jsp"></jsp:include>
<jsp:include page="./common/title.jsp" flush="true">
		<jsp:param name="titletype" value="reg"></jsp:param>
</jsp:include>
<jsp:include page="./common/js.jsp"></jsp:include> 
<body style="background:#FFF;">
	<jsp:include page="./common/head.jsp" flush="true">
		<jsp:param name="showlogin" value="true"></jsp:param>
		<jsp:param name="showreg" value="false"></jsp:param>
	</jsp:include>  
	<jsp:include page="./common/subheader.jsp" flush="true">
		<jsp:param name="showshouye" value="true"></jsp:param>
	</jsp:include>  

	<div  id="reg_form"  style="border-radius:10px; width:400px; margin:10px auto 30px; padding-bottom:140px; border:solid 1px #ccc;">
			<table  cellspacing="0" cellpadding="0" class="center" style="margin-top:30px;width:90%;border: 0 ">
				<tr>
						<td align="right"><span style="color:#09F; font-weight:bold">新用户注册</span></td>
						<td align="right"><a href="<%=request.getContextPath() %>/login">已有账号</a></td>
				</tr>
				<tr>
						<td align="right">&nbsp;</td>
						<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">手机号码：</td>
					<td><input type="text" name="textfield"  size="40" id="phone_text" style="padding:10px 5px; width:200px;" placeholder="请填写常用手机号"></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td ><span style="color:#F00" id="phone_error"><!-- 该邮箱已经使用过&nbsp;&nbsp;输入邮箱地址错误 --></span></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td>
						<input type="text" name="textfield5" id="phone_code" style="padding:10px 5px; width:110px;" placeholder="请输入手机验证码">
						<a   id="getvefydata_phone" style="display:inline-block; border-radius:5px; padding:8px 0; background-color:#eee; width:85px; line-height:25px; height:25px; margin-right:20px;" class="tac">发送验证码</a>
					</td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td><span style="color:#F00" id="phone_code_error"></span></td>
				</tr>
				<tr>
					<td align="right">用户名：</td>
					<td><input type="text" name="user_name" id="user_name" size="40" style="padding:10px 5px; width:200px;" placeholder=""></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td><span style="color:#F00" id="user_name_error"></span></td>
				</tr>
				<tr>
					<td align="right">密码：</td>
					<td><input type="password" name="password" id="password"  size="20" style="padding:10px 5px; width:200px;" placeholder="请输入6-20位字母或数字"></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td><span style="color:#F00" id="password_error"></span></td>
				</tr>
				<tr>
					<td align="right">确认密码：</td>
					<td><input type="password" name="repassword" id="repassword" size="20" style="padding:10px 5px; width:200px;" placeholder="请再次输入"></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td><span style="color:#F00"  id="repassword_error"></span></td>
				</tr>
			</table>
			<div class="tac"><a href="javascript:doRegFn(this)" style="display:inline-block; border-radius:5px; background-color:#09F; width:275px; color:#fff; line-height:40px; height:40px;" class="tac" id="reg_btn">注册</a></div>

	</div>

	 <div id="reg_success" style="display:none; border-radius:10px; width:400px; margin:10px auto 30px; border:solid 1px #ccc; font-size:20px; font-weight:bold; height:300px; line-height:300px;" class="tac">
		<a href="<%=request.getContextPath() %>/login" style="color:#09f;">激活成功，请到登陆页面登陆</a>
	</div> 

	<!--<div style="border-radius:10px; width:400px; margin:10px auto 30px; border:solid 1px #ccc; font-size:20px; font-weight:bold; height:300px; line-height:300px;" class="tac">
		激活时间已过！期激活不成功，请重新注册
	</div>-->
	
	<jsp:include page="./common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
var endTime = 0;
var clitime = 0;
$(function(){
	$("#phone_text").val("");
	$("#phone_text").focus(); 
	$("#phone_text").focus(function(){
		$("#phone_error").html("");
	});
	 $("#getvefydata_phone").bind('click',sendYanZhengMa);
	$("#user_name").focus(function(){
		$("#user_name_error").html("");
	});
	$("#password").focus(function(){
		$("#password_error").html("");
	});
	$("#repassword").focus(function(){
		$("#repassword_error").html("");
	}); 
	$("#phone_code").focus(function(){
		$("#phone_code_error").html("");
	});
	
})

function sendYanZhengMa(){
	 if(!checkPhoneFn()) {
		 return;
	 }; 
	 if(clitime>=1)return;
	 sendAjaxGetCode($("#phone_text").val(),'phone'); 	 
}
function checkPhoneFn(){
	var phone = $("#phone_text").val();
	if($.trim(phone).length == 0 ){
		$("#phone_error").html("请输入常用手机号码");
		return false;
	}
	var result = checkPhone(phone);
	if(!result){
		$("#phone_error").html("请输入正确的手机号码");
		return false;
	}
	return true;
}
 
function checkEmailFn(){
	var email = $("#email_text").val();
	if($.trim(email).length == 0 ){
		$("#email_error").html("请输入常用邮箱");
		return false;
	}
	var result = checkEmail(email);
	if(!result){
		$("#email_error").html("请输入正确的邮箱地址");
		return false;
	}
	return true;
}
function changeSendPhoneCodeInfo(){
	
	if(endTime == 0){
		window.clearInterval(changeSendPhoneCodeInfo);
		 $("#getvefydata_phone").attr("disabled",false); 
		 $("#getvefydata_phone").text( "发送验证码");
		 $("#getvefydata_phone").bind('click',sendYanZhengMa);
		return;
	}
	$("#getvefydata_phone").text(endTime+"秒后重发");
	endTime--;
}
function sendAjaxGetCode(value,type){
	var yanzhengmaurl = "<%=request.getContextPath()%>/reg/sendmail";
	 var data = null;
	if(type == 'phone'){
		 yanzhengmaurl = "<%=request.getContextPath()%>/reg/sendphone";
		  data =  { d:new Date().getTime(),phone:value};
	}
	
  	$.ajax({
		  type:'post',
		  url: yanzhengmaurl,
		  context: document.body,
		  beforeSend:function(){
			  clitime = 1;
			  $("#getvefydata_phone").attr("disabled",true); 
			  $("#getvefydata_phone").unbind("click");
			  endTime = 60;
			 changeSendPhoneCodeInfo();
			 window.setInterval("changeSendPhoneCodeInfo()", 1000); 
		 },
		  data:data,
		  success:function(json){
			  clitime = 0;
			  var result = json["result"];  
			  //修改发送状态 
			  alert("验证码已发出");
			 	  
		  },
	      error:function(xhr,textStatus,errorThrown){
	    	  clitime = 0;
	    	  $("#getvefydata_phone").attr("disabled",true); 
	    	  $("#getvefydata_phone").bind('click',sendYanZhengMa);
	    	  var responseText = xhr.responseText;
		  		var obj = jQuery.parseJSON(responseText);
				var errortype = obj.errortype
		  		var msg = obj.msg;
				if(errortype == 'email'){
					$("#email_error").html(msg);
					endTime = 0;
				}else if(errortype == 'phone'){
					$("#phone_error").html(msg);
					endTime = 0;
				}
	  } 
	})
}
function doRegFn(athis){
	if(!checkPhoneFn())return;
	if(!checkCode())return;
	if(!checkUserName())return;
	if(!checkpassword())return;
	 
	var phone =  $.trim($("#phone_text").val());
	var code = $.trim($("#phone_code").val());
	var username = $.trim($("#user_name").val());
	var password = $.trim($("#password").val());
	var repassword = $.trim($("#repassword").val());
	
	var yanzhengmaurl = "<%=request.getContextPath()%>/reg/doreg";
  	$.ajax({
		  type:'post',
		  url: yanzhengmaurl,
		  context: document.body,
		  beforeSend:function(){
			  $("#reg_btn").attr("disabled",true); 
			  $("#reg_btn").html("正在注册中。。。");
		 },
		  data:{
			  d:new Date().getTime(),
			  userName: username,
			  phone: phone,
			  password: password,
			  rePassword: repassword,
			  yanzhengma: code,
			  type:'phone'
		  },
		  success:function(json){
			  $("#reg_btn").attr("disabled",false);
			  $("#reg_btn").html("注册");
			  var result = json["result"]; 
			  if(result){
				  
				  $("#reg_form").hide();
				  $("#reg_success").show();
				  return;
			  }else{
				 
			  } 
			   
		  },
	      error:function(xhr,textStatus,errorThrown){
	    	  $("#reg_btn").attr("disabled",false);
	    	  $("#reg_btn").html("注册");
	  		var responseText = xhr.responseText;
	  		var obj = jQuery.parseJSON(responseText);
			var errortype = obj.errortype
	  		var msg = obj.msg;
			 
			if(errortype == 'password'){
				$("#password_error").html(msg);
			}else if(errortype == 'rePassword'){
				$("#repassword_error").html(msg);
			}else if(errortype == 'userName'){
				
				$("#user_name_error").html(msg);
			}else if(errortype == 'email'){
				$("#email_error").html(msg);
			}else if(errortype == 'code'){
				$("#phone_code_error").html(msg);
			}else if( errortype == 'phone'){
				$("#phone_code_error").html(msg);
			}
			
	  		// $(btn)).removeAttr("disabled");
	  } 
	})
}
function checkUserName(){
	var username = $("#user_name").val();
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
	var repassword =  $.trim($("#repassword").val());
	if(password.length == 0 ){
		$("#password_error").html("密码不能为空");
		return false;
	}
	if(repassword.length == 0 ){
		$("#repassword_error").html("确认密码不能为空");
		return false;
	}
	if(repassword != password  ){
		$("#repassword_error").html("确认密码与密码不一致");
		return false;
	}
	return true;
}
function checkCode(){
	var code = $.trim($("#phone_code").val());
	if(code.length == 0 ){
		$("#phone_code_error").html("验证码不能为空");
		return false;
	}else if(code.length != 6 ){
		$("#phone_code_error").html("验证码输入错误");
		return false;
	}
	return true;
}
 
</script>
</html>