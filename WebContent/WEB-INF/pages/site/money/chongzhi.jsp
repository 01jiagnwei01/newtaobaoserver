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
<jsp:include page="../common/mina.jsp"></jsp:include>
<jsp:include page="../common/title.jsp" flush="true">
		<jsp:param name="titletype" value="chongzhi"></jsp:param>
</jsp:include>
<jsp:include page="../common/css.jsp"></jsp:include>
<jsp:include page="../common/js.jsp"></jsp:include> 
<style type="text/css">
.cbc:hover{background-color:#D3D3D3;}
table td{padding:5px; font-size:14px;}
</style>
	
<style type="text/css">
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
				<a href="###">财务中心</a>&nbsp;>&nbsp;<a href="###">充值</a>
			</div>
            
            <div style="overflow:hidden;">
            
            <div style="margin-bottom:-5000px; padding-bottom:5000px;">
			<jsp:include page="../common/leftmenu.jsp" flush="true">
				<jsp:param name="mainmenu" value="caiwu"></jsp:param>
				<jsp:param name="submenu" value="chongzhi"></jsp:param>
			</jsp:include>
            </div>

			<div style="width:910px; padding:0 40px; background-color:#FFF; margin-bottom:-5000px; padding-bottom:5000px;" class="fr">
            	<h2 style="background:#f8f8f8; font-weight:normal; margin-top:30px;">
                	<div style="padding:10px;"><span style="font-size:16px;">可用余额：</span><span style="color:rgb(57, 161, 234);">${userAccount.currentBalance }</span></div>
                </h2>
				<div style="margin:20px 0;">工作时间正确填写交易号/商务订单号，资金一小时内到账</div>
				<ul style="border-bottom:1px #ccc solid; height:100px;">
					<li class="fl">
						<div class="tac" style="height:80px; width:80px; border-radius:100px; color:#FFF; line-height:80px; background:#4CA4EE;">支付宝转账</div>
					</li>
					<li class="fl" style="height:80px; line-height:80px; padding:0 20px;"><img src="<%=request.getContextPath() %>/resources/images/icon.gif"></li>
					<li class="fl">
						<div class="tac" style="height:80px; width:80px; border-radius:100px; color:#000; line-height:80px; background:#d9d9d9;">登记订单</div>
					</li>
					<li class="fl" style="height:80px; line-height:80px; padding:0 20px;"><img src="<%=request.getContextPath() %>/resources/images/icon.gif"></li>
					<li class="fl">
						<div class="tac" style="height:80px; width:80px; border-radius:100px; color:#000; line-height:80px; background:#d9d9d9;">提交成功</div>
					</li>
				</ul>
				
				
				<table width="80%" border="0" cellspacing="0" cellpadding="0" style="margin:0 auto;">
					<tr>
							<td align="right">&nbsp;</td>
							<td>&nbsp;</td>
					</tr>
					<tr>
						<td align="right">用PC端支付：</td>
						<td>转账至346745719@qq.com</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><a href="javascript:bindTranferAlipay();" style="color:#06f;">【前往支付宝转账】</a></td>
					</tr>
					<tr>
							<td align="right">&nbsp;</td>
							<td valign="bottom" style="font-size:12px; color:#666;">&nbsp;</td>
					</tr>
					<tr>
							<td align="right">用支付宝支付：</td>
							<td valign="bottom" style="font-size:12px; color:#666;">扫描二维码支付</td>
					</tr>
					<tr>
							<td align="right">&nbsp;</td>
							<td><img src="<%=request.getContextPath() %>/resources/images/code.gif" width="200" height="200"></td>
					</tr>
					<tr>
							<td align="right">&nbsp;</td>
							<td>&nbsp;</td>
					</tr>
					<tr>
							<td align="right">&nbsp;</td>
							<td><a href="<%=request.getContextPath() %>/site/money/chongzhi/s2" style="display:inline-block; border-radius:5px; background-color:#09F; color:#fff; width:150px; line-height:30px; height:30px;" class="tac">转账完成,登记订单</a>
								<br />
								<span style="color:#F00; font-size:12px;">完成转账后，记得回来填写订单号，否则可能造成充值失败！</span>
							</td>
					</tr>
					<tr>
							<td align="right">&nbsp;</td>
							<td>&nbsp;</td>
					</tr>
				</table>
				<table width="80%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<p><span style="font-weight:bold; color:#06f;">常见问题：</span></p>
							<p><strong>1、什么是“非即时到账充值”？</strong></p>
							<p style="color:#666; text-indent:2em;">通过支付宝收款服务进行充值，资金1小时内可充入账户，到账后会短信提醒。交易号或商户订单号（二选一任意填写）是您充值的唯一凭据，请务必正确填写。</p>
							<p><strong>2、哪些人享受非即时到账充值免费？</strong></p>
							<p style="color:#666; text-indent:2em;">为了感谢和鼓励广大在谷谷道场合作的朋友，我们对大家施行非即时到账充值免费策略。</p>
						</td>
					</tr>
					<tr>
						<td align="right">&nbsp;</td>
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
function bindTranferAlipay(){
	 window.open("https://shenghuo.alipay.com/send/payment/fill.htm?optEmail=346745719@qq.com");
}

</script>
</html>