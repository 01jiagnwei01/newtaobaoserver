<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<link href="<%=request.getContextPath() %>/resources/css/style.css" rel="stylesheet" type="text/css">
<jsp:include page="./common/js.jsp"></jsp:include> 
<body style="background:#FFF;">
	<jsp:include page="./common/head.jsp" flush="true">
		<jsp:param name="showlogin" value="true"></jsp:param>
		<jsp:param name="showreg" value="false"></jsp:param>
	</jsp:include>  
	<jsp:include page="./common/subheader.jsp" flush="true">
		<jsp:param name="showshouye" value="true"></jsp:param>
	</jsp:include>  

	<div style="border-radius:10px; width:400px; margin:10px auto 30px; padding-bottom:30px; border:solid 1px #ccc;">
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
					<td align="right">邮箱：</td>
					<td><input type="text" name="textfield" id="email_text" style="padding:10px 5px; width:200px;" placeholder="请填写常用邮箱"></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td ><span style="color:#F00" id="email_error"><!-- 该邮箱已经使用过&nbsp;&nbsp;输入邮箱地址错误 --></span></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td>
						<input type="text" name="textfield5" id="email_code" style="padding:10px 5px; width:110px;" placeholder="请输入邮箱验证码">
						<a href="###" id="getvefydata_email" style="display:inline-block; border-radius:5px; padding:8px 0; background-color:#eee; width:85px; line-height:25px; height:25px; margin-right:20px;" class="tac">发送验证码</a>
					</td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td><span style="color:#F00" id="email_code_error"></span></td>
				</tr>
				<tr>
					<td align="right">用户名：</td>
					<td><input type="text" name="user_name" id="user_name" style="padding:10px 5px; width:200px;" placeholder=""></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td><span style="color:#F00" id="user_name_error"></span></td>
				</tr>
				<tr>
					<td align="right">密码：</td>
					<td><input type="text" name="password" id="password"  style="padding:10px 5px; width:200px;" placeholder="请输入8-16位字母或数字"></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td><span style="color:#F00" id="password_error"></span></td>
				</tr>
				<tr>
					<td align="right">确认密码：</td>
					<td><input type="text" name="repassword" id="repassword" style="padding:10px 5px; width:200px;" placeholder="请再次输入"></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td><span style="color:#F00"  id="repassword_error"></span></td>
				</tr>
			</table>
			<div class="tac"><a href="###" style="display:inline-block; border-radius:5px; background-color:#09F; width:275px; color:#fff; line-height:40px; height:40px;" class="tac">注册</a></div>

	</div>

	<!--<div style="border-radius:10px; width:400px; margin:10px auto 30px; border:solid 1px #ccc; font-size:20px; font-weight:bold; height:300px; line-height:300px;" class="tac">
		<a href="###" style="color:#09f;">激活成功，3秒后跳到首页</a>
	</div>-->

	<!--<div style="border-radius:10px; width:400px; margin:10px auto 30px; border:solid 1px #ccc; font-size:20px; font-weight:bold; height:300px; line-height:300px;" class="tac">
		激活时间已过！期激活不成功，请重新注册
	</div>-->
	
	
	<div style="width:100%; background-color:#8d8d8d; position:fixed; left:0; bottom:0;">
		<div class="center tac" style="width:1200px; line-height:100px; height:100px; color:#FFF;">谷谷道场</div>
	</div> 
</body>
<script type="text/javascript">
var endTime = 0;
$(function(){
	$("#email_text").val("");
	$("#email_text").focus(); 
	$("#email_text").focus(function(){
		$("#email_error").html("");
	});
	$("#getvefydata_email").click(sendYanZhengMa);
})
function checkEmail(str){
	
    var re = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/gi;
    if(re.test(str)){
       	return true;
    }else{
       return false;
    }
}
function sendYanZhengMa(){
	 if(!checkEmailFn()) return; 
	// document.getElementById("getvefydata_email").style.disable = true;
	 $("#getvefydata_email").attr("disabled",true); 
	 $("#getvefydata_email").unbind();
	 endTime = 60;
	 changeSendEmailCodeInfo();
	 window.setInterval("changeSendEmailCodeInfo()", 1000); 
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
function changeSendEmailCodeInfo(){
	
	if(endTime == 0){
		window.clearInterval(changeSendEmailCodeInfo);
		 $("#getvefydata_email").attr("disabled",false); 
		 $("#getvefydata_email").text( "发送验证码");
		 $("#getvefydata_email").click(sendYanZhengMa);
		return;
	}
	$("#getvefydata_email").text(endTime+"秒后重发");
	endTime--;
}
function sendAjaxGetEmailCode(mail){
	var yanzhengmaurl = "<%=request.getContextPath()%>/reg/sendmail";
  	$.ajax({
		  type:'GET',
		  url: yanzhengmaurl,
		  context: document.body,
		  beforeSend:function(){
		  		 
		 },
		  data:{ d:new Date().getTime(),tomail:mail},
		  success:function(json){
			  
			  var result = json["result"];
			  var yanzhengmaget = json["entity"];
			 	  
		  },
	      error:function(xhr,textStatus,errorThrown){
	    	  
	  		var responseText = xhr.responseText;
	  		// $(btn)).removeAttr("disabled");
	  } 
}
}
  
</script>
</html>