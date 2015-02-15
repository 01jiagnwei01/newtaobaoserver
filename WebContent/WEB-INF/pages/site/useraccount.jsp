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
			<jsp:include page="./common/leftmenu.jsp" flush="true">
				<jsp:param name="mainmenu" value="safe"></jsp:param>
				<jsp:param name="submenu" value="czm"></jsp:param>
			</jsp:include>
			

			<div style="width:910px; padding:0 40px; background-color:#FFF;" class="fr">
				<div style="margin:20px 0;"></div>
				
				<table    cellspacing="0" cellpadding="0" 
				style="border-bottom:dotted 1px #ccc; margin-bottom:20px;width: 80%;border: 0;">
					<tr>
						<td>
							<div style="float:left;">通过操作码修改</div>
							<div style="float:right;" id="open1">
								<a href="#" style="color:#06f;" onClick="showTable('xiugai1')">展开修改</a>
							</div>
							<div style="float:right; display:none;" id="close1">
								<a href="#" style="color:#06f;" onClick="hideTable('xiugai1')">收起修改</a>
							</div>
							<div style="clear:both;"></div>
							
							<div id="xiugai1" style="background-color:#fafafa; padding:15px 0; margin-top:15px; display:none;">
								<%
								if(StringUtils.isBlank(caozuoma)){
									%>
									<table  cellpadding="0" cellspacing="0" style="margin:0 auto;border: 0">
									<tr>
										<td align="center" colspan="3"><span style="color:#F7971A;">
										您以前没有设置过操作码呢
										</span></td>
									</tr>
									</table>	
									<%
								}else{
									%>
								<table   cellpadding="0" cellspacing="0" style="margin:0 auto;border:0">
									<tr>
										<td align="right">旧操作码：</td>
										<td><input size="16" type="text" name="old_caozuoMa" id="old_caozuoMa" style="width:260px;"></td>
										<td><span style="font-size:12px; color:#F00;" id="old_caozuoMa_error"></span></td>
									</tr>
									<tr>
										<td align="right">新操作码：</td>
										<td><input size="16" type="text" name="f1_caozuoma" id="f1_caozuoma" style="width:260px;"></td>
										<td><span style="font-size:12px; color:#F00;" id="f1_caozuoma_error"></span></td>
									</tr>
									<tr>
										<td align="right">确认操作码：</td>
										<td><input size="16" type="text" name="f1_recaozuoma" id="f1_recaozuoma" style="width:260px;"></td>
										<td><span  style="font-size:12px; color:#F00;" id="f1_recaozuoma_error"></span></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><a id="f1_submitbtn" style="display:inline-block; border-radius:5px; background-color:#09F; color:#fff; width:150px; line-height:30px; height:30px;" class="tac" >立即修改</a></td>
										<td>&nbsp;</td>
									</tr>
								</table>
								<%
										} 
								%>
							</div>
						</td>
					</tr>
					<tr>
						<td style="height:10px;"></td>
					</tr>
				</table>
					
				<table cellspacing="0" cellpadding="0" 
					style="border-bottom:dotted 1px #ccc; margin-bottom:20px;border: 0;width:80% ">
					<tr>
						<td>
							<div style="float:left;">通过邮箱修改</div>
							<div style="float:right;" id="open2">
								<a href="#" style="color:#06f;" onClick="showTable('xiugai2')">展开修改</a>
							</div>
							<div style="float:right; display:none;" id="close2">
								<a href="#" style="color:#06f;" onClick="hideTable('xiugai2')">收起修改</a>
							</div>
							<div style="clear:both;"></div>
							
							<div id="xiugai2" style="background-color:#fafafa; padding:15px 0; margin-top:15px; display:none;">
								<%
								if(StringUtils.isBlank(email)){
									%>
									<table  cellpadding="0" cellspacing="0" style="margin:0 auto;border: 0">
									<tr>
										<td align="center" colspan="3"><span style="color:#F7971A;">
										您还没有绑定邮箱呢
										</span></td>
									</tr>
									</table>	
									<%
								}else{
									%>
								
								<table border="0" cellpadding="0" cellspacing="0" style="margin:0 auto;">
									<tr>
										<td align="right">绑定的邮箱：</td>
										<td><span style="color:#F7971A;"><%=email %></span></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td align="right">验证码：</td>
										<td>
											<input type="text" name="textfield4" id="email_code">
											<a style="display:inline-block; padding:2px 10px; background:#eee;" id="getvefydata_email">获取确认码</a>
										</td>
										<td><span style="font-size:12px; color:#F00;" id="email_code_error"></span></td>
									</tr>
									
									<tr>
										<td align="right">新操作码：</td>
										<td><input type="password" name="caozuoma_mail" id="caozuoma_mail" style="width:260px;"></td>
										<td><span style="font-size:12px; color:#F00;" id="caozuoma_mail_error"> </span></td>
									</tr>
									<tr>
										<td align="right">确认操作码：</td>
										<td><input type="password" name="recaozuoma_mail" id="recaozuoma_mail" style="width:260px;"></td>
										<td><span style="font-size:12px; color:#F00;" id="recaozuoma_mail_error"></span></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>
											<a style="display:inline-block; border-radius:5px; background-color:#09F; color:#fff; width:150px; line-height:30px; height:30px;" class="tac"
												id="email_submit"
											>立即修改</a>
										</td>
										<td>&nbsp;</td>
									</tr>
								</table>	<%
								}
								%>
							</div>
						</td>
					</tr>
					<tr>
						<td style="height:10px;"></td>
					</tr>
				</table>
				<!--  
				<table width="80%" border="0" cellspacing="0" cellpadding="0" style="margin-bottom:20px;">
					<tr>
						<td>
							<div style="float:left;">通过手机号修改</div>
							<div style="float:right;" id="open3">
								<a href="#" style="color:#06f;" onClick="showTable('xiugai3')">展开修改</a>
							</div>
							<div style="float:right; display:none;" id="close3">
								<a href="#" style="color:#06f;" onClick="hideTable('xiugai3')">收起修改</a>
							</div>
							<div style="clear:both;"></div>
							<div id="xiugai3" style="background-color:#fafafa; padding:15px 0; margin-top:15px; display:none;">
								<%
								if(StringUtils.isBlank(telPhone)){
									%>
									<table  cellpadding="0" cellspacing="0" style="margin:0 auto;border: 0">
										<tr>
											<td align="center" colspan="3"><span style="color:#F7971A;">
											您还没有绑定手机呢
											</span></td>
										</tr>
									</table>	
									<%
								}else{
									%>
								<table border="0" cellpadding="0" cellspacing="0" style="margin:0 auto;">
									<tr>
										<td align="right">绑定的手机：</td>
										<td><span style="color:#F7971A;">123456</span></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td align="right">确认码：</td>
										<td>
											<input type="text" name="textfield4" id="textfield6">
											<a style="display:inline-block; padding:2px 10px; background:#eee;">获取确认码</a>
										</td>
										<td><span style="font-size:12px; color:#F00;">错误提示</span></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><a href="#" style="display:inline-block; border-radius:5px; background-color:#09F; color:#fff; width:150px; line-height:30px; height:30px;" class="tac">立即修改</a></td>
										<td>&nbsp;</td>
									</tr>
								</table>
									<%
								} 
									%>
							</div>
						</td>
					</tr>
				</table> -->
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
$(function(){
	 
	$("#email_code").focus(function(){
		$("#email_code_error").html("");
	});
	$("#caozuoma_mail").focus(function(){
		$("#caozuoma_mail_error").html("");
	});
	$("#recaozuoma_mail").focus(function(){
		$("#recaozuoma_mail_error").html("");
	});
	 
	
	$("#old_caozuoMa").focus(function(){
		$("#old_caozuoMa_error").html("");
	});
	$("#f1_caozuoma").focus(function(){
		$("#f1_caozuoma_error").html("");
	});
	$("#f1_recaozuoma").focus(function(){
		$("#f1_recaozuoma_error").html("");
	});
	
	 $("#getvefydata_email").bind('click',sendYanZhengMa);
	 
	 $("#email_submit").bind('click',emailSubmitFn);
	 $("#f1_submitbtn").bind('click',f1SubmitFn);
	 
	 var showTabId = getCookie("caozuoma_show");
	 if(showTabId == ""){
		 showTable("xiugai2");
	 } 
	 showTable(showTabId);
	 
})
var ids = [{tableId:"xiugai1",expandId:'open1',closeId:'close1'},
	           {tableId:"xiugai2",expandId:'open2',closeId:'close2'},
	           {tableId:"xiugai3",expandId:'open3',closeId:'close3'}];
	           
function showTable(showTableId){
	if(!showTableId)return;
	for(var i=0;i<ids.length;i++){
		var obj = ids[i];
		if(showTableId == obj['tableId'] ){
			var table = $("#"+obj['tableId']);
			if(table){
				table.show();
				$("#"+obj['expandId']).hide();
				$("#"+obj['closeId']).show();
			}
		}else{
			var table = $("#"+obj['tableId']);
			if(table){
				table.hide();
				$("#"+obj['expandId']).show();
				$("#"+obj['closeId']).hide();
			}
		}
		
	}
}
function hideTable(hideTableId){
	for(var i=0;i<ids.length;i++){
		var obj = ids[i];
		if(hideTableId == obj['tableId'] ){
			var table = $("#"+obj['tableId']);
			if(table){
				table.hide();
				$("#"+obj['expandId']).show();
				$("#"+obj['closeId']).hide();
			}
		} 
		
	}
}
function sendYanZhengMa(){
	 if(clitime>=1)return; 
	 sendAjaxGetEmailCode();
}
function sendAjaxGetEmailCode(){
	var yanzhengmaurl = "<%=request.getContextPath()%>/caozuoma/sendmail";
  	$.ajax({
		  type:'post',
		  url: yanzhengmaurl,
		  context: document.body,
		  beforeSend:function(){
			  clitime = 1;
			  $("#getvefydata_email").attr("disabled",true); 
			  $("#getvefydata_email").unbind("click");
			  //修改发送状态
			  endTime = 60;
			 changeSendEmailCodeInfo();
			 window.setInterval("changeSendEmailCodeInfo()", 1000);
		 },
		  data:{ d:new Date().getTime()},
		  success:function(json){
			  clitime = 0;
			  var result = json["result"];  
			  
			 alert("已经向邮箱<%=email %>发送了验证码");
		  },
	      error:function(xhr,textStatus,errorThrown){
	    	  clitime = 0;
	  		var responseText = xhr.responseText; 
	  		var obj = jQuery.parseJSON(responseText);
			var errortype = obj.errortype
	  		var msg = obj.msg;
			if(errortype == 'email'){
				$("#email_code_error").html(msg);
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
function emailSubmitFn(){
	 
	var code = $("#email_code").val();
	if($.trim(code).length != 6){
		$("#email_code_error").html("验证码错误");
		return;
	}
	var caozuoma = $.trim($("#caozuoma_mail").val());
	var recaozuoma = $.trim($("#recaozuoma_mail").val());
	if(caozuoma.length == 0 ){
		$("#caozuoma_mail_error").html("操作码不能为空");
		return;
	}else if(caozuoma != recaozuoma){
		$("#recaozuoma_mail_error").html("确认操作码与操作码不一致");
		return;
	}
	
	var url = "<%=request.getContextPath()%>/caozuoma/mailsubmit";
  	$.ajax({
		  type:'post',
		  url: url,
		  context: document.body,
		  beforeSend:function(){
			  $("#email_submit").attr("disabled",true); 
			  $("#email_submit").html("正在提交中。。。");
			  
		 },
		  data:{ d:new Date().getTime(),caozuoma:caozuoma,recaozuoma:recaozuoma,code:code},
		  success:function(json){
			  addCookie("caozuoma_show","xiugai2",10*60*60);
			  var result = json["result"];
			  alert("修改成功");
			  window.location.reload(); 
		  },
	      error:function(xhr,textStatus,errorThrown){
	    	  $("#email_submit").attr("disabled",false); 
			  $("#email_submit").html("立即修改");
	  		var responseText = xhr.responseText; 
	  		var obj = jQuery.parseJSON(responseText);
			var errortype = obj.errortype
	  		var msg = obj.msg;
			if(errortype == 'email'){
				$("#email_code_error").html(msg);
			}else if(errortype=="caozuoma"){
				$("#caozuoma_mail_error").html(msg);
			}else if(errortype=="recaozuoma"){
				$("#recaozuoma_mail_error").html(msg);
			}else if(errortype=="yanzhengma"){
				$("#email_code_error").html(msg);
			}
	  } 
	})
}
function f1SubmitFn(){
	var oldCaoZuoMa = $.trim($("#old_caozuoMa").val());
	var f1_caozuoma = $.trim($("#f1_caozuoma").val());
	var f1_recaozuoma = $.trim($("#f1_recaozuoma").val());
	
	if(oldCaoZuoMa.length == 0){
		$("#old_caozuoMa_error").html("请输入旧操作码");
		return;
	}
	if($.trim(f1_caozuoma).length == 0){
		$("#f1_caozuoma_error").html("请输入新操作码");
		return;
	}
	if($.trim(f1_recaozuoma).length == 0){
		$("#f1_recaozuoma_error").html("请输入确认操作码");
		return;
	}
	  if(f1_caozuoma != f1_recaozuoma){
		$("#f1_recaozuoma_error").html("确认操作码与操作码不一致");
		return;
	}
	
	var url = "<%=request.getContextPath()%>/caozuoma/caozuomasubmit";
  	$.ajax({
		  type:'post',
		  url: url,
		  context: document.body,
		  beforeSend:function(){
			  $("#f1_submitbtn").attr("disabled",true); 
			  $("#f1_submitbtn").html("正在提交中。。。");
			  
		 },
		  data:{ d:new Date().getTime(),caozuoma:f1_caozuoma,recaozuoma:f1_recaozuoma,oldcode:oldCaoZuoMa},
		  success:function(json){
			  addCookie("caozuoma_show","xiugai1",10*60*60);
			  var result = json["result"];
			  alert("修改成功");
			  window.location.reload(); 
		  },
	      error:function(xhr,textStatus,errorThrown){
	    	  $("#f1_submitbtn").attr("disabled",false); 
			  $("#f1_submitbtn").html("立即修改");
	  		var responseText = xhr.responseText; 
	  		var obj = jQuery.parseJSON(responseText);
			var errortype = obj.errortype
	  		var msg = obj.msg;
			if(errortype == 'oldcode'){
				$("#old_caozuoMa_error").html(msg);
			}else if(errortype=="caozuoma"){
				$("#f1_caozuoma_error").html(msg);
			}else if(errortype=="recaozuoma"){
				$("#f1_recaozuoma_error").html(msg);
			} 
	  } 
	})
}
</script>
</html>