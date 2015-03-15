<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
						<div class="tac" style="height:80px; width:80px; border-radius:100px; color:#fff; line-height:80px; background:#4CA4EE;">提交成功</div>
					</li>
				</ul>
				<div style="border-radius:10px; width:400px; margin:10px auto 30px; border:solid 1px #ccc; font-size:20px; font-weight:bold; height:100px; line-height:100px;" class="tac">
					<a href="<%=request.getContextPath() %>/site/money/chongzhi" style="color:#09f;">提交成功，继续充值</a>
				</div>
			</div>
			</div>
		</div>
		<div style="clear:both;"></div>

	</div>

	<div style="clear:both;"></div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
 
</script>
</html>