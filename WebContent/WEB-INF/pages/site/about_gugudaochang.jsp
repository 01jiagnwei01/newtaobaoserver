<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.*,com.gxkj.taobaoservice.enums.*,com.gxkj.taobaoservice.entitys.*,com.gxkj.common.util.SessionUtil,java.util.*"%>

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
				<jsp:param name="submenu" value="update_bindemail"></jsp:param>
			</jsp:include>

			<div style="width:910px; padding:0 40px; background-color:#FFF;" class="fr">
				<div style="margin:20px 0;"></div>
			 	<pre>
				  谷谷道场的使命

					让所有的担保变得可视化。
					协力中国信用体系的建设，发现中国人的信用价值。
				</pre>
				<pre> 
				谷谷道场的灵魂
				
				梦想 以公益的心态，去实现改变中国，造福人民，创造社会价值的伟大梦想。
				
				创新 坚持技术创新的方式，以符合自然规律的方式发展，并集中精力把技术创新做到极致。
				
				耐心 保持耐心，让时间成为我们的武器和朋友，以长远的眼光看待我们的事业。
				</pre>
				<pre> 
				联系我们：
					群主qq：346745719
					</pre>
								 
			</div>

		</div>
		<div style="clear:both;"></div>

	</div>

	<div style="clear:both;"></div>
 	<jsp:include page="./common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
 
</script>
</html>