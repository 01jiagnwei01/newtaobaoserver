<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.*,com.gxkj.taobaoservice.enums.*,com.gxkj.taobaoservice.entitys.*,com.gxkj.common.util.SessionUtil,java.util.*"%>
 <%
 
String email = "";
String telPhone = "";
 
%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<title>找回密码</title>
<jsp:include page="./common/css.jsp"></jsp:include>
<jsp:include page="./common/js.jsp"></jsp:include> 
<style type="text/css">
.cbc:hover{background-color:#D3D3D3;}
table td{padding:5px; font-size:14px; height:25px;}
#apDiv1 {
	position: absolute;
	left: 986px;
	top: 261px;
	width: 44px;
	height: 17px;
	z-index: 1;
	visibility: visible;
}
</style>


<body>
	<jsp:include page="./common/head.jsp" flush="true">
		<jsp:param name="showlogin" value="true"></jsp:param>
		<jsp:param name="showreg" value="false"></jsp:param>
	</jsp:include> 
	<jsp:include page="./common/subheader.jsp" flush="true">
		<jsp:param name="showshouye" value="true"></jsp:param>
	</jsp:include>  
	
	<div style="width:100%; background-color:#ededed; padding-bottom:35px;">

		<div class="center" style="width:1200px;">
			

			<div style="width:990px; padding:0 40px; background-color:#FFF;" class="fr">
				<div style="margin:20px 0;"></div>
				<form id = "form_">
				<table border="0" cellpadding="0" cellspacing="0" style="margin:10px auto 30px; padding-bottom:30px;   clear:both;">
					
					<tr>
						<td align="right">注册邮箱：</td>
						<td><input type="text" name="email_text"  size="40" id="email_text" style="padding:10px 5px; width:260px;" placeholder="请填写注册邮箱"></td>
						<td><span style="font-size:12px; color:#F00;width: 80px" id="email_error"> </span></td>
					</tr>
					<tr>
						<td align="right">&nbsp;</td>
						<td>
							<input type="text" name="email_code" id="email_code" style="padding:10px 5px; width:110px;" placeholder="请输入邮箱验证码">
							<a   id="getvefydata_email" style="display:inline-block; border-radius:5px; padding:8px 0; background-color:#eee; width:85px; line-height:25px; height:25px; margin-right:20px;" class="tac">发送验证码</a>
						</td>
						<td><span style="font-size:12px; color:#F00;width: 80px" id="email_code_error"> </span></td>
					</tr>
					<tr>
							<td align="right">新密码：</td>
							<td><input size="16" type="password" name="newpassword" id="newpassword" style="padding:10px 5px;  width:260px;"></td>
							<td><span style="font-size:12px; color:#F00;" id="newpassword_error"></span></td>
							</tr>
					<tr>
							<td align="right">&nbsp;</td>
							<td style="font-size:12px; color:#666;">建议使用英文字母、符号和数字的组合，长度不要超过16位。</td>
							<td>&nbsp;</td>
					</tr>
					<tr>
							<td align="right">确认密码：</td>
							<td><input size="16" type="password" name="repassword" id="repassword" style="padding:10px 5px; width:260px;"></td>
							<td><span style="font-size:12px; color:#F00;"  id="repassword_error"></span></td>
					</tr>
					<tr>
						<td align="right">验证码：</td>
						<td>
							<input id="yanzhengma" type="text" name="yanzhengma"   style="width:170px;height:30px;padding-top: 5px;  " placeholder="">
							<img title="点击更换" id="yanzhengmaBtn" style="width:80px;" onclick="javascript:refreshYanZhengMa(this);" src="<%=request.getContextPath()%>/yanzhengma">
						</td>
						<td><span style="font-size:12px; color:#F00;" id="yanzhengma_error"> </span></td>
					</tr>
					 
					<tr>
						<td>&nbsp;</td>
						<td>
							<a  id="submitbtn" style="display:inline-block; border-radius:5px; background-color:#09F; color:#fff; width:150px; line-height:30px; height:30px;" class="tac"  >提交</a>
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
					</tr>
				</table>
				</form>
			</div>

		</div>
		<div style="clear:both;"></div>

	</div>

	<div style="clear:both;"></div>
 	<jsp:include page="./common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
var endTime = 0;
var clitime = 0;
function refreshYanZhengMa(obj) {  $(obj).attr("src","<%=request.getContextPath()%>/yanzhengma?"+Math.random());  }
$(function(){
	$("#email_text").focus(function(){
		$("#email_error").html("");
	});
	$("#yanzhengma").focus(function(){
		$("#yanzhengma_error").html('');
	});
	$("#email_code").focus(function(){
		$("#email_code_error").html('');
	});
	 $("#newpassword").focus(function(){
			$("#newpassword_error").html("");
		});
	 $("#repassword").focus(function(){
			$("#repassword_error").html("");
		});
	
	$("#getvefydata_email").bind('click',sendYanZhengMa);
	$("#submitbtn").bind('click',submitFn);
})
function sendYanZhengMa(){
	 if(!checkEmailFn()) {
		 return;
	 }; 
	 if(clitime>=1)return;
	 sendAjaxGetEmailCode($("#email_text").val()); 	 
}
function checkEmailFn(){
	var email = $("#email_text").val();
	if($.trim(email).length == 0 ){
		$("#email_error").html("请输入注册邮箱");
		return false;
	}
	var result = checkEmail(email);
	if(!result){
		$("#email_error").html("请输入正确的邮箱地址");
		return false;
	}
	return true;
}
function sendAjaxGetEmailCode(mail){
	var yanzhengmaurl = "<%=request.getContextPath()%>/findbackpassword/sendmail";
  	$.ajax({
		  type:'post',
		  url: yanzhengmaurl,
		  context: document.body,
		  beforeSend:function(){
			  clitime = 1;
			  $("#getvefydata_email").attr("disabled",true); 
			  $("#getvefydata_email").unbind("click");
			  endTime = 60;
			 changeSendEmailCodeInfo();
			 window.setInterval("changeSendEmailCodeInfo()", 1000); 
		 },
		  data:{ d:new Date().getTime(),email:mail},
		  success:function(json){
			  clitime = 0;
			  var result = json["result"];  
			  //修改发送状态
			  alert("验证码已发送");
			 	  
		  },
	      error:function(xhr,textStatus,errorThrown){
	    	  clitime = 0;
	  		var responseText = xhr.responseText;
	  		var obj = jQuery.parseJSON(responseText);
			var errortype = obj.errortype
	  		var msg = obj.msg;
			if(errortype == 'email'){
				$("#email_error").html(msg);
				endTime = 0;
			}
	  } 
	})
}
function changeSendEmailCodeInfo(){
	
	if(endTime == 0){
		window.clearInterval(changeSendEmailCodeInfo);
		 $("#getvefydata_email").attr("disabled",false); 
		 $("#getvefydata_email").text( "发送验证码");
		 $("#getvefydata_email").bind('click',sendYanZhengMa);
		return;
	}
	$("#getvefydata_email").text(endTime+"秒后重发");
	endTime--;
}
function checkCode(){
	var code = $.trim($("#email_code").val());
	if(code.length == 0 ){
		$("#email_code_error").html("验证码不能为空");
		return false;
	}else if(code.length != 6 ){
		$("#email_code_error").html("验证码输入错误");
		return false;
	}
	return true;
}
 
function checkNewPassword(){
	 var newpassword = $.trim($("#newpassword").val());
	 if(newpassword.length == 0 ){
		 $("#newpassword_error").html("确认密码与新密码不一致");
			return false;
		}
		return true;
}
function checkRePassword(){
	var repassword = $.trim($("#repassword").val());
	if(repassword.length == 0){
		$("#repassword_error").html("确认密码空");
		return false;
	}
	var newpassword = $.trim($("#newpassword").val());
	if( newpassword != repassword){
		$("#repassword_error").html("确认密码与新密码不一致");
		return false;
	}
	return true;
}
function submitFn(){
	if(!checkEmailFn())return ;
	if(!checkCode())return;
	if(!checkNewPassword())return;
	if(!checkRePassword())return;
	var email =  $.trim($("#email_text").val());
	var emailCode = $.trim($("#email_code").val());
	var yanzhengma = $.trim($("#yanzhengma").val());
	var newPassword =  $.trim($("#newpassword").val());
	var rePassword =  $.trim($("#repassword").val());
	
	var yanzhengmaurl = "<%=request.getContextPath()%>/findbackpassword";
  	$.ajax({
		  type:'post',
		  url: yanzhengmaurl,
		  context: document.body,
		  beforeSend:function(){
			  $("#submitbtn").attr("disabled",true); 
			  $("#submitbtn").html("正在提交中。。。");
		 },
		  data:{
			  d:new Date().getTime(),
			  email: email,
			  emailCode: emailCode,
			  yanzhengma:yanzhengma,
			  password:newPassword,
			  surepassword:rePassword
		  },
		  success:function(json){
			  $("#submitbtn").attr("disabled",false);
			  $("#submitbtn").html("提交");
			  var result = json["result"]; 
			  if(result){
				  alert("修改成功");
				  $("#form_")[0].reset();
				  window.location.reload();
				  return;
			  }else{
				 
			  }  
			 	  
		  },
	      error:function(xhr,textStatus,errorThrown){
	    	  $("#submitbtn").attr("disabled",false);
	    	  $("#submitbtn").html("提交");
	    	  
	    	  var el = document.getElementById("yanzhengmaBtn");
	    	  var y = $("yanzhengma").val("");
	    	  refreshYanZhengMa(el);
	  		var responseText = xhr.responseText;
	  		var obj = jQuery.parseJSON(responseText);
			var errortype = obj.errortype
	  		var msg = obj.msg;
			if(errortype == 'email'){
				$("#email_error").html(msg);
			}else if(errortype == 'yanzhengma'){
				$("#yanzhengma_error").html(msg);
			}else if(errortype == 'dbyanzhengma'){
				$("#email_code_error").html(msg);
			}else if(errortype == 'password'){
				$("#newpassword_error").html(msg);
			}else if(errortype == 'rePassword'){
				$("#repassword_error").html(msg);
			} 
			
	  		// $(btn)).removeAttr("disabled");
	  } 
	})
}
</script>
</html>