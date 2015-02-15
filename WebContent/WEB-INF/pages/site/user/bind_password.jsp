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
				 <table border="0" cellpadding="0" cellspacing="0" style="margin:0 auto; clear:both;">
					<tr>
							<td align="right">新密码：</td>
							<td><input size="16" type="password" name="newpassword" id="newpassword" style="width:260px;"></td>
							<td><span style="font-size:12px; color:#F00;" id="newpassword_error"></span></td>
							</tr>
					<tr>
							<td align="right">&nbsp;</td>
							<td style="font-size:12px; color:#666;">请使用8-16个字符的英文字母、符号和数字的组合。</td>
							<td>&nbsp;</td>
					</tr>
					<tr>
							<td align="right">确认密码：</td>
							<td><input size="16" type="password" name="repassword" id="repassword" style="width:260px;"></td>
							<td><span style="font-size:12px; color:#F00;"  id="repassword_error"></span></td>
					</tr>
					<tr>
							<td align="right">&nbsp;</td>
							<td style="font-size:12px; color:#666;">请再输一次新密码</td>
							<td>&nbsp;</td>
					</tr>
					<tr>
							<td align="right">操作码：</td>
							<td><input type="text" name="caozuoma" id="caozuoma" size="16" style="width:260px;"></td>
							<td><span style="font-size:12px; color:#F00;" id="caozuoma_error"></span></td>
					</tr>
					 
					<tr>
							<td>&nbsp;</td>
							<td>
									<a href="#" style="display:inline-block; border-radius:5px; background-color:#09F; color:#fff; width:150px; line-height:30px; height:30px;" class="tac" onclick="alert('修改成功或失败')">提交</a>
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
 $(function(){
	 
})
function submitFn(){
	 if(!checkNewPassword())return;
	 if(!checkRePassword())return;
	 if(!checkCaoZuoMa())return;
	 var newpassword = $.trim($("#newpassword").val());
	 var repassword = $.trim($("#repassword").val());
	 var caozuoma = $.trim($("#caozuoma").val());
	 
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
 function checkNewPassword(){
	 var newpassword = $.trim($("#newpassword").val());
	 if(newpasswor.length == 0 ){
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
function checkCaoZuoMa(){
	var caozuoma = $.trim($("#caozuoma").val());
	if(caozuoma.length == 0){
		$("#caozuoma_error").html("操作码空");
		return false;
	}
	return true;
}

</script>
</html>