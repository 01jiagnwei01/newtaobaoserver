<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
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
	
	<div style="width:100%; background-color:#ededed; padding-bottom:135px;">

		<div class="center" style="width:1200px;">
        	<div style="height:50px; line-height:50px;">
				<a href="###">首页</a>&nbsp;>&nbsp;<a href="###">我的账户</a>&nbsp;>&nbsp;<a href="###">任务中心</a>&nbsp;>&nbsp;<a href="###">我发布的任务</a>
			</div>
            
            <div style="overflow:hidden;">
            
            <div style="margin-bottom:-5000px; padding-bottom:5000px;">

			 <jsp:include page="../common/leftmenu.jsp" flush="true">
				<jsp:param name="mainmenu" value="safe"></jsp:param>
				<jsp:param name="submenu" value="czm"></jsp:param>
			</jsp:include>
            </div>
            
			  

			<div style="width:910px; padding:0 40px; background-color:#FFF; margin-bottom:-5000px; padding-bottom:5000px;" class="fr">
            	<h2 style="background:#f8f8f8; font-weight:normal; margin-top:30px;">
                	<div style="padding:10px;"><span style="font-size:16px;">可用余额：</span><span style="color:rgb(57, 161, 234);">¥0.13</span></div>
                </h2>
				<div style="margin:20px 0;">工作时间正确填写交易号/商务订单号，资金一小时内到账</div>
				<ul style="border-bottom:1px #ccc solid; height:100px;">
					<li class="fl">
						<div class="tac" style="height:80px; width:80px; border-radius:100px; color:#FFF; line-height:80px; background:#4CA4EE;">支付宝转账</div>
					</li>
					<li class="fl" style="height:80px; line-height:80px; padding:0 20px;"><img src="<%=request.getContextPath() %>/resources/images/icon.gif"></li>
					<li class="fl">
						<div class="tac" style="height:80px; width:80px; border-radius:100px; color:#FFF; line-height:80px; background:#4CA4EE;">登记订单</div>
					</li>
					<li class="fl" style="height:80px; line-height:80px; padding:0 20px;"><img src="<%=request.getContextPath() %>/resources/images/icon.gif"></li>
					<li class="fl">
						<div class="tac" style="height:80px; width:80px; border-radius:100px; color:#000; line-height:80px; background:#d9d9d9;">提交成功</div>
					</li>
				</ul>
				
				
				<div style="color:#F00; height:40px; line-height:40px;">* 未填写可能导致无法正常到账</div>
				<table   cellspacing="0" cellpadding="0" style="width: 80%;border:0">
					<tr>
						<td align="right">交易号或商户订单号：</td>
						<td><input type="text" name="orderno" id="orderno" style="width: 260px"></td>
						<td ><span style="font-size:12px; color:#F00;" id="orderno_error"></span></td>
					</tr>
					<!-- 
					<tr>
						<td>&nbsp;</td>
						<td>订单在哪儿？<a href="#">手机</a>&nbsp;/&nbsp;<a href="#">网页版</a>&nbsp;</td>
					</tr> -->
					<tr>
							<td align="right">填写充值金额：</td>
							<td><input type="number" name="money" id="money" style="width: 260px">元</td>
							<td align="left"><span style="font-size:12px; color:#F00;" id="money_error"></span></td>
					</tr>
					<tr>
							<td align="right">&nbsp;</td>
							<td><a  id="sutmitBtn" style="display:inline-block; border-radius:5px; background-color:#09F; color:#fff; width:150px; line-height:30px; height:30px;" class="tac">提交，下一步</a></td>
					</tr>
				</table>
			</div>
            
            </div>

		</div>
		<div style="clear:both;"></div>

	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
$(function (){
	
	$("#sutmitBtn").bind('click',submitFn);
	
	$("#orderno").focus(function(){
		$("#orderno_error").html("");
	});
	
	$("#money").focus(function(){
		$("#money_error").html("");
	});

})
function submitFn(){
	
	var orderno = $("#orderno").val();
	var amount = $("#money").val();
	
	
	if (orderno == null || orderno == "" || orderno == 'undefined') {
        $("#orderno_error").html("商户订单号不能为空");
        return false;
    }

    if (orderno.indexOf(" ") != -1) {
    	$("#orderno_error").html("商户订单号不能包含空格");
        
        return false;
    }

    if (!IsNumeric(orderno)) {
    	$("#orderno_error").html("请输入正确商户订单号");
        return false;
    }

    if (orderno.length != 17 && orderno.length != 19 &&
         orderno.length != 18 && orderno.length != 30 &&
         orderno.length != 28 && orderno.length != 32
        ) {
    	$("#orderno_error").text("请输入正确填写交易号\商务订单号，长度应该为17，18，19，28，30或32");
        
        return false;
    }

    if (!IsNumeric(amount)) {
    	$("#money_error").html("请输入正确金额");
        return false;
    }

    var factAmount = parseFloat(amount);
    if (!IsNumeric(factAmount)) {
        $("#money_error").html("请输入正确金额"); 
        return false;
    }

    if (factAmount <= 0) {
    	$("#money_error").html("非即时充值需要大于0元");
      
        return false;
    }

    if (!checkMoneyFormat(factAmount)) {
    	$("#money_error").html("请输入正确金额");
        
        return false;
    }

    if (factAmount > 10000000) {
    	$("#money_error").html("请输入正确金额");
        return false;
    }
   
	var url = "<%=request.getContextPath()%>/site/money/chongzhi/dochongzhi";
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
			  orderno:orderno
			  
		  },
		  success:function(json){
			  $("#sutmitBtn").attr("disabled",false);
			  $("#sutmitBtn").html("提交");
			  var result = json["result"]; 
			  if(result){
				
				  window.location = "<%=request.getContextPath()%>/site/money/chongzhi/s3";
				  
				  return;
			  }else{
				 
			  }  
			  $("#sutmitBtn").attr("disabled",false);
	    	  $("#sutmitBtn").html("提交");
			 	  
		  },
	      error:function(xhr,textStatus,errorThrown){
	    	  $("#sutmitBtn").attr("disabled",false);
	    	  $("#sutmitBtn").html("提交");
	  		var responseText = xhr.responseText;
	  		var obj = jQuery.parseJSON(responseText);
			var errortype = obj.errortype
	  		var msg = obj.msg;
			if(errortype == 'orderno'){
				$("#orderno_error").html(msg);
			}else if(errortype == 'amount'){
				$("#money_error").html(msg);
			} 
			
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