<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.*,com.gxkj.taobaoservice.enums.*,com.gxkj.taobaoservice.entitys.*,com.gxkj.common.util.SessionUtil,java.util.*"%>
<%
UserBase base = SessionUtil.getSiteUserInSession(request);
String bindAlipay = base.getBindAlipay();
String caozuoma =    base.getCaoZuoMa();
 
%>
<!DOCTYPE html>
<html>
<jsp:include page="../common/mina.jsp"></jsp:include>
<jsp:include page="../common/title.jsp" flush="true">
		<jsp:param name="titletype" value="bind_alipay"></jsp:param>
</jsp:include>
<jsp:include page="../common/css.jsp"></jsp:include>
<jsp:include page="../common/js.jsp"></jsp:include> 
<style type="text/css">
.cbc:hover{background-color:#D3D3D3;}
table td{padding:5px; font-size:14px; height:25px;}
</style>
 
<body>
	<jsp:include page="../common/head.jsp" flush="true">
		<jsp:param name="showlogin" value="true"></jsp:param>
		<jsp:param name="showreg" value="false"></jsp:param>
	</jsp:include> 
	<jsp:include page="../common/subheader.jsp" flush="true">
		<jsp:param name="showshouye" value="true"></jsp:param>
	</jsp:include>  
	
	<div  class="content">
		<div class="center" style="width:1200px;">
			<div style="height:50px; line-height:50px;">
				<a href="###">安全中心</a>&nbsp;>&nbsp;<a href="###">绑定淘宝号</a>
			</div>
			<div style="overflow:hidden;">
				<div style="margin-bottom:-5000px; padding-bottom:5000px;">
					<jsp:include page="../common/leftmenu.jsp" flush="true">
						<jsp:param name="mainmenu" value="safe"></jsp:param>
						<jsp:param name="submenu" value="bind_alipay"></jsp:param>
					</jsp:include>
	
					<div style="width:910px; padding:0 40px; background-color:#FFF; margin-bottom:-5000px; padding-bottom:5000px;" class="fr">
           				<div style="margin:20px 0;"></div>
						<form id = "form_">
						<table border="0" cellpadding="0" cellspacing="0" style="margin:10px auto 30px; padding-bottom:30px;   clear:both;">
							<% if(StringUtils.isNotBlank(bindAlipay)) {%>
							<tr>
									<td align="right">已绑定淘宝小号：</td>
									<td><span   style="padding:10px 5px;width:260px;"  ><%=bindAlipay%></span></td>
									<td style="width: 100px"> </td>
							</tr>
							<%} %>
							<tr>
								<td align="right">淘宝小号：</td>
								<td><input type="text" name="alipay"  size="40" id="alipay" style="padding:10px 5px; width:260px;" placeholder="请填写常用淘宝小号"></td>
								<td><span style="font-size:12px; color:#F00;width: 80px" id="alipay_error"> </span></td>
							</tr>
							 <tr>
								<td align="right">验证码：</td>
								<td>
									<input id="yanzhengma" type="text" name="yanzhengma"   style="width:170px;height:30px;padding-top: 5px;  " placeholder="">
									<img title="点击更换" id="yanzhengmaBtn" style="width:80px;" onclick="javascript:refreshYanZhengMa(this);" src="<%=request.getContextPath()%>/yanzhengma">
								</td>
								<td><span style="font-size:12px; color:#F00;width: 80px" id="yanzhengma_error"> </span></td>
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
		</div>
	</div>

 
 	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
var endTime = 0;
var clitime = 0;
function refreshYanZhengMa(obj) {  $(obj).attr("src","<%=request.getContextPath()%>/yanzhengma?"+Math.random());  }
$(function(){
	$("#alipay").focus(function(){
		$("#alipay_error").html("");
	});
	$("#yanzhengma").focus(function(){
		$("#yanzhengma_error").html('');
	});
	$("#caozuoma").focus(function(){
		$("#caozuoma_error").html('');
	});
	 
	$("#submit_btn").bind('click',submitFn);
})
  
function checkCode(){
	var code = $.trim($("#yanzhengma").val());
	if(code.length == 0 ){
		$("#yanzhengma_error").html("验证码不能为空");
		return false;
	}else if(code.length != 4 ){
		$("#yanzhengma_error").html("验证码输入错误");
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
	 
	if(!checkCode())return;
	if(!checkCaoZuoMa())return;
	var yanzhengma =  $.trim($("#yanzhengma").val());
	var caozuoma = $.trim($("#caozuoma").val());
	var alipay = $.trim($("#alipay").val());
	
	var yanzhengmaurl = "<%=request.getContextPath()%>/site/bind/alipay/dobindalipay";
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
			  yanzhengma: yanzhengma,
			  caozuoma:caozuoma,
			  alipay:alipay
		  },
		  success:function(json){
			  $("#submit_btn").attr("disabled",false);
			  $("#submit_btn").html("提交");
			  $("#yanzhengma").val("");
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
	    	  $("#yanzhengma").val("");
	    	  refreshYanZhengMa(document.getElementById("yanzhengmaBtn"))
	  		var responseText = xhr.responseText;
	  		var obj = jQuery.parseJSON(responseText);
			var errortype = obj.errortype
	  		var msg = obj.msg;
			if(errortype == 'alipay'){
				$("#alipay_error").html(msg);
			}else if(errortype == 'caozuoma'){
				$("#caozuoma_error").html(msg);
			}else if(errortype == 'yanzhengma'){
				$("#yanzhengma_error").html(msg);
			}
	  } 
	})
}
</script>
</html>