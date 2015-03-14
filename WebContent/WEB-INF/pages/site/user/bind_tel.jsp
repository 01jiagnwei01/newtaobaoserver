<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.*,com.gxkj.taobaoservice.enums.*,com.gxkj.taobaoservice.entitys.*,com.gxkj.common.util.SessionUtil,java.util.*"%>
<%
UserBase base = SessionUtil.getSiteUserInSession(request);
 
String telPhone = base.getBindTelphone();
String caozuoma =    base.getCaoZuoMa();
 
%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<title>绑定手机号</title>
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
			<a href="###">首页</a>&nbsp;>&nbsp;<a href="###">我的账户</a>&nbsp;>&nbsp;<a href="###">任务中心</a>&nbsp;>&nbsp;<a href="###">我发布的任务</a>
		</div>
			<div style="overflow:hidden;">
		<div style="margin-bottom:-5000px; padding-bottom:5000px;">
				<jsp:include page="../common/leftmenu.jsp" flush="true">
				<jsp:param name="mainmenu" value="safe"></jsp:param>
				<jsp:param name="submenu" value="update_bindemail"></jsp:param>
			</jsp:include>

			<div style="width:990px; padding:0 40px; background-color:#FFF;" class="fr">
				<div style="margin:20px 0;"></div>
				<form id = "form_">
				<table border="0" cellpadding="0" cellspacing="0" style="margin:10px auto 30px; padding-bottom:30px;   clear:both;">
					<% if(StringUtils.isNotBlank(telPhone)) {%>
					<tr>
							<td align="right">已绑定：</td>
							<td><span   style="padding:10px 5px;width:260px;"  ><%=telPhone%></span></td>
							<td style="width: 100px"> </td>
					</tr>
					<%} %>
					<tr>
						<td align="right">手机号码：</td>
						<td><input type="text" name="telNo"  size="40" id="telNo" style="padding:10px 5px; width:260px;" placeholder="请填写常用手机号码"></td>
						<td><span style="font-size:12px; color:#F00;width: 80px" id="telNo_error"> </span></td>
					</tr>
					<tr>
						<td align="right">&nbsp;</td>
						<td>
							<input type="text" name="code" id="code" style="padding:10px 5px; width:110px;" placeholder="请输入手机验证码">
							<a   id="getvefydata_code" style="display:inline-block; border-radius:5px; padding:8px 0; background-color:#eee; width:85px; line-height:25px; height:25px; margin-right:20px;" class="tac">发送验证码</a>
						</td>
						<td><span style="font-size:12px; color:#F00;width: 80px" id="code_error"> </span></td>
					</tr>
					<tr>
							<td align="right">操作码：</td>
							<td><input type="password" name="caozuoma" id="caozuoma" style="padding:10px 5px;width:260px;"></td>
							<td><span style="font-size:12px; color:#F00;" id="caozuoma_error"> </span></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>
							<a  id="submit_btn" style="display:inline-block; border-radius:5px; background-color:#09F; color:#fff; width:150px; line-height:30px; height:30px;" class="tac"  >提交</a>
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
	$("#telNo").focus(function(){
		$("#telNo_error").html("");
	});
	$("#code").focus(function(){
		$("#code_error").html('');
	});
	$("#caozuoma").focus(function(){
		$("#caozuoma_error").html('');
	});
	
	$("#getvefydata_code").bind('click',sendYanZhengMa);
	$("#submit_btn").bind('click',submitFn);
})
function sendYanZhengMa(){
	 if(!checkPhoneFn()) {
		 return;
	 }; 
	 if(clitime>=1)return;
	 sendAjaxGetEmailCode($("#telNo").val()); 	 
}
function checkPhoneFn(){
	var telNo = $("#telNo").val();
	if($.trim(telNo).length == 0 ){
		$("#telNo_error").html("请输入常用手机号码");
		return false;
	}
	var result = checkPhone(telNo);
	if(!result){
		$("#telNo_error").html("请输入正确的手机号码");
		return false;
	}
	return true;
}
function sendAjaxGetEmailCode(mail){
	var yanzhengmaurl = "<%=request.getContextPath()%>/site/bind/tel/sendcode";
  	$.ajax({
		  type:'post',
		  url: yanzhengmaurl,
		  context: document.body,
		  beforeSend:function(){
			  clitime = 1;
			  $("#getvefydata_code").attr("disabled",true); 
			  $("#getvefydata_code").unbind("click");
			  endTime = 60;
			 changeSendEmailCodeInfo();
			 window.setInterval("changeSendEmailCodeInfo()", 1000); 
		 },
		  data:{ d:new Date().getTime(),telNo:mail},
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
			if(errortype == 'telNo'){
				$("#telNo_error").html(msg);
				endTime = 0;
			}
	  } 
	})
}
function changeSendEmailCodeInfo(){
	
	if(endTime == 0){
		window.clearInterval(changeSendEmailCodeInfo);
		 $("#getvefydata_code").attr("disabled",false); 
		 $("#getvefydata_code").text( "发送验证码");
		 $("#getvefydata_code").bind('click',sendYanZhengMa);
		return;
	}
	$("#getvefydata_code").text(endTime+"秒后重发");
	endTime--;
}
function checkCode(){
	var code = $.trim($("#code").val());
	if(code.length == 0 ){
		$("#code_error").html("验证码不能为空");
		return false;
	}else if(code.length != 6 ){
		$("#code_error").html("验证码输入错误");
		return false;
	}
	return true;
}
function checkCaoZuoMa(){
	var caozuoma = $.trim($("#caozuoma").val()); 
	if(caozuoma.length == 0 ){
		$("#caozuoma_error").html("操作码不能为空");
		return false;
	}
	 
	return true;
}
function submitFn(){
	if(!checkPhoneFn())return ;
	if(!checkCode())return;
	if(!checkCaoZuoMa())return;
	var telNo =  $.trim($("#telNo").val());
	var  caozuoma = $.trim($("#caozuoma").val());
	var  code = $.trim($("#code").val());
	
	var yanzhengmaurl = "<%=request.getContextPath()%>/site/bind/tel/doupdatebyetel";
  	$.ajax({
		  type:'post',
		  url: yanzhengmaurl,
		  context: document.body,
		  beforeSend:function(){
			  $("#submit_btn").attr("disabled",true); 
			  $("#submit_btn").html("正在提交中。。。");
		 },
		  data:{
			  d:new Date().getTime(),
			  telNo: telNo,
			  caozuoma:caozuoma,
			  yanzhengma:code
		  },
		  success:function(json){
			  $("#submit_btn").attr("disabled",false);
			  $("#submit_btn").html("提交");
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
	    	  $("#submit_btn").attr("disabled",false);
	    	  $("#submit_btn").html("提交");
	  		var responseText = xhr.responseText;
	  		var obj = jQuery.parseJSON(responseText);
			var errortype = obj.errortype
	  		var msg = obj.msg;
			if(errortype == 'yanzhengma'){
				$("#code_error").html(msg);
			}else if(errortype == 'telNo'){
				$("#telNo_error").html(msg);
			}else if(errortype == 'caozuoma'){
				$("#caozuoma_error").html(msg);
			} 
			
	  		// $(btn)).removeAttr("disabled");
	  } 
	})
}
</script>
</html>