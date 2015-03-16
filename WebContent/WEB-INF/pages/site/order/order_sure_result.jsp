<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals,com.gxkj.taobaoservice.util.*"%>
 <%@ page import="static com.gxkj.taobaoservice.util.SystemDbData.subTaskInfoMap, com.gxkj.common.exceptions.*"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<jsp:include page="../common/mina.jsp"></jsp:include>
<title>订单确认成功</title>
<jsp:include page="../common/css.jsp"></jsp:include>
<jsp:include page="../common/bookstrap.jsp"></jsp:include> 
<style type="text/css">
.cbc:hover{background-color:#D3D3D3;}
table td{padding:5px; height:25px; font-size:14px;}
 
.zengzhiinputwidth {width: 250px;}
 
#kinMaxShow .KMSPrefix_kinMaxShow_button{left:50%; margin-left:-33px;}

.inputwidth {width:360px;height:50px;line-height: 50px;}
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
			<a href="###">任务中心</a>&nbsp;>&nbsp;<a href="###">创建任务</a>
		</div>

			<div style="overflow:hidden;">
		<div style="margin-bottom:-5000px; padding-bottom:5000px;">
				<jsp:include page="../common/leftmenu.jsp" flush="true">
				<jsp:param name="mainmenu" value="safe"></jsp:param>
				<jsp:param name="submenu" value="czm"></jsp:param>
			</jsp:include></div>
			 
			<div style="width:990px; padding:0 40px; background-color:#FFF; padding-bottom:100px;margin-bottom:-5000px; padding-bottom:5000px;" class="fr">
				 	 <div  style="; border-radius:10px; width:400px; margin:10px auto 30px; border:solid 1px #ccc; font-size:20px; font-weight:bold; height:300px; line-height:300px;" class="tac">
						创建成功！<button class="btn btn-lg btn-success"  id="continueCreate">创建新订单？</button>
					</div> 
								 	 
			</div>

		</div>
		<div style="clear:both;"></div>
	</div>
	<div style="clear:both;"></div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(function(){ 
		$("#continueCreate").click(function(){
			window.location="<%=request.getContextPath() %>/site/order/create";
		});
	})
	 
</script>

</html> 