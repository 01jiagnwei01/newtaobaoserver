<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.*,com.gxkj.taobaoservice.enums.*,com.gxkj.taobaoservice.entitys.*,com.gxkj.common.util.SessionUtil,java.util.*"%>
<%
UserBase base = SessionUtil.getSiteUserInSession(request);
String email = base.getBindEmail();
String telPhone = base.getBindTelphone();
String caozuoma =    base.getCaoZuoMa();
 
%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<jsp:include page="../common/css.jsp"></jsp:include>
<jsp:include page="../common/js.jsp"></jsp:include> 
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
	<jsp:include page="../common/head.jsp" flush="true">
		<jsp:param name="showlogin" value="true"></jsp:param>
		<jsp:param name="showreg" value="false"></jsp:param>
	</jsp:include> 
	<jsp:include page="../common/subheader.jsp" flush="true">
		<jsp:param name="showshouye" value="true"></jsp:param>
	</jsp:include>  
	
	<div style="width:100%; background-color:#ededed; padding-bottom:35px;">

		<div class="center" style="width:1200px;">

			<div style="height:50px; line-height:50px;">
				<a href="#">首页</a>&nbsp;>&nbsp;<a href="#">我的账户</a>&nbsp;>&nbsp;<a href="#">修改密码</a>
			</div>
			<jsp:include page="../common/leftmenu.jsp" flush="true">
				<jsp:param name="mainmenu" value="safe"></jsp:param>
				<jsp:param name="submenu" value="update_bindemail"></jsp:param>
			</jsp:include>
			 

			<div style="width:910px; padding:0 40px; background-color:#FFF;" class="fr">
			
				<div style="margin:20px 0;"></div>
				 
				<form id = "form_">
				<table border="0" cellpadding="0" cellspacing="0" style="margin:10px auto 30px; padding-bottom:30px;   clear:both;">
					<% if(StringUtils.isNotBlank(email)) {%>
					<tr>
							<td align="right">已绑定邮箱：</td>
							<td><span   style="padding:10px 5px;width:260px;"  ><%=email%></span></td>
							<td style="width: 100px"> </td>
					</tr>
					<%} %>
					<tr>
						<td align="right">邮箱：</td>
						<td><input type="text" name="email_text"  size="40" id="email_text" style="padding:10px 5px; width:260px;" placeholder="请填写常用邮箱"></td>
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
							<td align="right">操作码：</td>
							<td><input type="password" name="email_caozuoma" id="email_caozuoma" style="padding:10px 5px;width:260px;"></td>
							<td><span style="font-size:12px; color:#F00;" id="email_caozuoma_error"> </span></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>
							<a  id="email_submit" style="display:inline-block; border-radius:5px; background-color:#09F; color:#fff; width:150px; line-height:30px; height:30px;" class="tac"  >提交</a>
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
 	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
var endTime = 0;
var clitime = 0;
function refreshYanZhengMa(obj) {  $(obj).attr("src","<%=request.getContextPath()%>/yanzhengma?"+Math.random());  }
$(function(){
	$("#email_text").focus(function(){
		$("#email_error").html("");
	});
	$("#email_code").focus(function(){
		$("#email_code_error").html('');
	});
	$("#email_caozuoma").focus(function(){
		$("#email_caozuoma_error").html('');
	});
	
	$("#getvefydata_email").bind('click',sendYanZhengMa);
	$("#email_submit").bind('click',submitFn);
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
function sendAjaxGetEmailCode(mail){
	var yanzhengmaurl = "<%=request.getContextPath()%>/site/bind/email/sendmail";
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
		  data:{ d:new Date().getTime(),tomail:mail},
		  success:function(json){
			  clitime = 0;
			  var result = json["result"];  
			  //修改发送状态
			 	  
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
function checkCaoZuoMa(){
	var caozuoma = $.trim($("#email_caozuoma").val()); 
	if(caozuoma.length == 0 ){
		$("#email_caozuoma_error").html("操作码不能为空");
		return false;
	}
	 
	return true;
}
function submitFn(){
	if(!checkEmailFn())return ;
	if(!checkCode())return;
	if(!checkCaoZuoMa())return;
	var email =  $.trim($("#email_text").val());
	var email_caozuoma = $.trim($("#email_caozuoma").val());
	var email_code = $.trim($("#email_code").val());
	
	var yanzhengmaurl = "<%=request.getContextPath()%>/site/bind/email/doupdatebyemail";
  	$.ajax({
		  type:'post',
		  url: yanzhengmaurl,
		  context: document.body,
		  beforeSend:function(){
			  $("#email_submit").attr("disabled",true); 
			  $("#email_submit").html("正在提交中。。。");
		 },
		  data:{
			  d:new Date().getTime(),
			  email: email,
			  caozuoma:email_caozuoma,
			  yanzhengma:email_code
		  },
		  success:function(json){
			  $("#email_submit").attr("disabled",false);
			  $("#email_submit").html("提交");
			  var result = json["result"]; 
			  if(result){
				  alert("修改成功");
				  $("#form_").reset();
				  window.location.reload();
				  return;
			  }else{
				 
			  }  
			 	  
		  },
	      error:function(xhr,textStatus,errorThrown){
	    	  $("#email_submit").attr("disabled",false);
	    	  $("#email_submit").html("提交");
	  		var responseText = xhr.responseText;
	  		var obj = jQuery.parseJSON(responseText);
			var errortype = obj.errortype
	  		var msg = obj.msg;
			if(errortype == 'yanzhengma'){
				$("#email_code_error").html(msg);
			}else if(errortype == 'email'){
				$("#email_error").html(msg);
			}else if(errortype == 'caozuoma'){
				$("#email_caozuoma_error").html(msg);
			} 
			
	  		// $(btn)).removeAttr("disabled");
	  } 
	})
}
</script>
</html>