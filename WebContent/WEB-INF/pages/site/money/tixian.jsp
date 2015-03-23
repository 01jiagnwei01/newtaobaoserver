<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="../common/mina.jsp"></jsp:include>
<jsp:include page="../common/title.jsp" flush="true">
		<jsp:param name="titletype" value="tixian"></jsp:param>
</jsp:include>
<jsp:include page="../common/css.jsp"></jsp:include>
<jsp:include page="../common/js.jsp"></jsp:include> 
<style type="text/css">
.cbc:hover{background-color:#D3D3D3;}
table td{padding:5px; font-size:14px;}
#kinMaxShow .KMSPrefix_kinMaxShow_button{left:50%; margin-left:-33px;}
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
				<a href="###">财务中心</a>&nbsp;>&nbsp;<a href="###">取现</a>
			</div>

			<div style="overflow:hidden;">
				<div style="margin-bottom:-5000px; padding-bottom:5000px;">
					<jsp:include page="../common/leftmenu.jsp" flush="true">
						<jsp:param name="mainmenu" value="caiwu"></jsp:param>
						<jsp:param name="submenu" value="tixian"></jsp:param>
					</jsp:include>

					<div style="width:910px; padding:0 40px; background-color:#FFF; margin-bottom:-5000px; padding-bottom:5000px;" class="fr">
						 <jsp:include page="../common/user_account.jsp"></jsp:include>
						<table border="0" cellspacing="0" cellpadding="0" style="clear:both; margin:0 auto;">
							<tr>
									<td align="right">&nbsp;</td>
									<td>&nbsp;</td>
							</tr>
							<tr>
									<td align="right">付款到支付宝账号：</td>
									<td><span class="fl" style="padding-right:30px; height:33px; line-height:33px;">
											<input type="text" name="alipyAccount" id="alipyAccount" style="padding:10px 5px; width:250px;" placeholder="请输入支付宝账号">
									</span></td>
							</tr>
							<tr>
									<td align="right">取款金额：</td>
									<td><input type="text" name="amount" id="amount" style="padding:10px 5px; width:250px;" placeholder="请输入金额">
									<span style="color:#4CA4EE; font-size:20px;">元</span></td>
							</tr>
							<tr>
									<td align="right">验证码：</td>
									<td> 
									<input id="yanzhengma" type="text" name="yanzhengma"  placeholder="请输入验证码"  style="padding:10px 5px; width:180px;"  >
												<img title="点击更换" id="yanzhengmaBtn" style="width:80px;" onclick="javascript:refreshYanZhengMa(this);" src="<%=request.getContextPath()%>/yanzhengma">
									</td>
							</tr>
							<tr>
									<td align="right">操作码：</td>
									<td> <input type="password" name="caozuoma" id="caozuoma" style="padding:10px 5px; width:250px;" placeholder="请输入操作码"/></td>
							</tr>
							<tr>
									<td colspan="2"  ><span style="color:#F00" id="error_msg"></span></td>
							</tr>
							<tr>
									<td>&nbsp;</td>
									<td><a  id = "sutmitBtn"  style="display:inline-block; border-radius:5px; background-color:#09F; width:100px; color:#fff; line-height:40px; height:40px;" class="tac">确认</a></td>
							</tr>
					</table>
				  </div>
		       </div>
		 </div>
		</div>
 	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
 
</body>
<script type="text/javascript">
function refreshYanZhengMa(obj) {  $(obj).attr("src","<%=request.getContextPath()%>/yanzhengma?"+Math.random());  }
$(function(){
	$("#alipyAccount").val("");
	$("#alipyAccount").focus(); 
	$("#alipyAccount").focus(function(){
		$("#error_msg").html("");
	});
	 
 
	$("#amount").focus(function(){
		$("#error_msg").html("");
	});
	$("#yanzhengma").focus(function(){
		$("#error_msg").html("");
	}); 
	$("#caozuoma").focus(function(){
		$("#error_msg").html("");
	}); 
	
	$("#sutmitBtn").bind('click',submitFn);
	 
})

function submitFn(){
	var alipyAccount = $.trim($("#alipyAccount").val());
	var amount =  $.trim($("#amount").val());
	var yanzhengma =   $.trim($("#yanzhengma").val());
	var caozuoma =   $.trim($("#caozuoma").val());
	if(caozuoma.length == 0){
		$("#error_msg").html("操作码不能为空");
        return false;
	}
	if(yanzhengma.length == 0){
		$("#error_msg").html("验证码不能为空");
        return false;
	}
	
	if (alipyAccount == null || alipyAccount == "" || alipyAccount == 'undefined') {
        $("#error_msg").html("支付宝账号不能为空");
        return false;
    }

    if (alipyAccount.indexOf(" ") != -1) {
    	$("#error_msg").html("支付宝账号不能包含空格");
        return false;
    }
 
    if (!IsNumeric(amount)) {
    	$("#error_msg").html("请输入正确金额");
        return false;
    }

    var factAmount = parseFloat(amount);
    if (!IsNumeric(factAmount)) {
        $("#error_msg").html("请输入正确金额"); 
        return false;
    }

    if (factAmount <= 0) {
    	$("#error_msg").html("取款金额需要大于0元");
        return false;
    }

    if (!checkMoneyFormat(factAmount)) {
    	$("#error_msg").html("请输入正确金额");
        return false;
    }

    if (factAmount > 10000000) {
    	$("#error_msg").html("请输入正确金额"); 
        return false;
    }
   
	var url = "<%=request.getContextPath()%>/site/money/tixian/doapply";
  	$.ajax({
		  type:'post',
		  url: url,
		  context: document.body,
		  beforeSend:function(){
			  $("#sutmitBtn").attr("disabled",true); 
			  $("#sutmitBtn").html("正在提交中。。。");
		 },
		  data:{
			  d:new Date().getTime(),
			  amount:factAmount,
			  alipyAccount:alipyAccount,
			  amount:amount,
			  yanzhengma:yanzhengma,
			  caozuoma:caozuoma
			  
		  },
		  success:function(json){
			  $("#sutmitBtn").attr("disabled",false);
			  $("#sutmitBtn").html("提交");
			  var result = json["result"]; 
			  if(result){
				alert("申请提交成功");
				  window.location = "<%=request.getContextPath()%>/site/money/tixian?d="+new Date().getTime();
				  
				  return;
			  }else{
				 
			  }  
			  $("#sutmitBtn").attr("disabled",false);
	    	  $("#sutmitBtn").html("提交");
			 	  
		  },
	      error:function(xhr,textStatus,errorThrown){
	    	  $("#sutmitBtn").attr("disabled",false);
	    	  $("#sutmitBtn").html("提交");
	    	  refreshYanZhengMa(document.getElementById("yanzhengmaBtn") ) ;
	    	  $("#yanzhengma").val("");
	  		var responseText = xhr.responseText;
	  		var obj = jQuery.parseJSON(responseText);
			var errortype = obj.errortype
	  		var msg = obj.msg; 
				$("#error_msg").html(msg); 
			
	  		// $(btn)).removeAttr("disabled");
	  } 
	})
	
}
function IsNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}
function checkMoneyFormat(val) {
    var reg = /^(([0-9]+)|([0-9]+\.[0-9]{1,2}))$/;
    var isMoneyFormatRight = reg.test(val);
    return isMoneyFormatRight;
} 
</script>
</html>