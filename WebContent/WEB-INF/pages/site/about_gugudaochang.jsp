<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.*,com.gxkj.taobaoservice.enums.*,com.gxkj.taobaoservice.entitys.*,com.gxkj.common.util.SessionUtil,java.util.*"%>

<!DOCTYPE html>
<html>
<jsp:include page="./common/mina.jsp"></jsp:include>
<jsp:include page="./common/title.jsp" flush="true">
		<jsp:param name="titletype" value="about_us"></jsp:param>
</jsp:include>
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
li{height:30px; line-height:30px; list-style:disc; margin-left:30px;}
</style>




<body>
	<jsp:include page="./common/head.jsp" flush="true">
		<jsp:param name="showlogin" value="true"></jsp:param>
		<jsp:param name="showreg" value="false"></jsp:param>
	</jsp:include> 
	<jsp:include page="./common/subheader.jsp" flush="true">
		<jsp:param name="showshouye" value="true"></jsp:param>
	</jsp:include>  
	
	<div style="width:100%; background-color:#ededed; padding-bottom:135px;">

		<div class="center" style="width:1200px;">

			<div style="height:50px; line-height:50px;">
				<a href="<%=request.getContextPath() %>/">首页</a>&nbsp;>&nbsp;<a href="###">关于谷谷道场</a>
			</div>

			<div style="padding:40px; padding-bottom:50px; background:url(<%=request.getContextPath() %>/resources/images/about_bg.jpg) no-repeat right 0 #fff;;">
			 	<h2>谷谷道场的使命</h2>
				<ul>
                	<li>让所有的担保变得可视化。</li>
               		<li>协力中国信用体系的建设，发现中国人的信用价值。</li>
				</ul> 
				<h2>谷谷道场的灵魂</h2>
				<ul>
                	<li>梦想 以公益的心态，去实现改变中国，造福人民，创造社会价值的伟大梦想。</li>
					<li>创新 坚持技术创新的方式，以符合自然规律的方式发展，并集中精力把技术创新做到极致。</li>
					<li>耐心 保持耐心，让时间成为我们的武器和朋友，以长远的眼光看待我们的事业。</li>
				</ul>
			</div>

		</div>
		<div style="clear:both;"></div>

	</div>

 	<jsp:include page="./common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
 
</script>
</html>