<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page import="java.math.*,java.text.*,com.gxkj.common.util.ListPager,org.apache.commons.lang3.*,com.gxkj.taobaoservice.enums.*,com.gxkj.taobaoservice.entitys.*,com.gxkj.common.util.SessionUtil,java.util.*"%>

<!DOCTYPE html>  
<html>
<jsp:include page="../common/mina.jsp"></jsp:include>
<jsp:include page="../common/title.jsp" flush="true">
		<jsp:param name="titletype" value="point_card"></jsp:param>
</jsp:include>
<jsp:include page="../common/css.jsp"></jsp:include>
<jsp:include page="../common/bookstrap.jsp"></jsp:include> 
<style type="text/css">
.cbc:hover{background-color:#D3D3D3;}
table td{padding:5px; font-size:14px; height:25px;}
#kinMaxShow .KMSPrefix_kinMaxShow_button{left:50%; margin-left:-33px;}
</style>
<%
 
%> 
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
				<a href="###">财务中心</a>&nbsp;>&nbsp;<a href="###">购买点卡</a>
			</div>
			<div style="overflow:hidden;">
				<div style="margin-bottom:-5000px; padding-bottom:5000px;">
					<jsp:include page="../common/leftmenu.jsp" flush="true">
						<jsp:param name="mainmenu" value="caiwu"></jsp:param>
						<jsp:param name="submenu" value="point_card"></jsp:param>
					</jsp:include>
				</div>
				<div style="width:990px; padding:0 40px; background-color:#FFF; padding-bottom:5000px; margin-bottom:-5000px;" class="fr">
				 	  <h2 style="background:#f8f8f8; font-weight:normal; margin-top:30px;">
		                	<div style="padding:10px;"><span style="font-size:16px;">可用余额：</span><span style="color:rgb(57, 161, 234);">${userAccount.currentBalance }</span></div>
		              </h2>
				 	  <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:20px;">
							<tr>
									<td align="center" bgcolor="#4CA4EE" style="color:#fff;">卡名称</td>
									<td align="center" bgcolor="#4CA4EE" style="color:#fff;" >需要金额</td>
									<td align="center" bgcolor="#4CA4EE" style="color:#fff;">获取点数</td>
									<td align="center" bgcolor="#4CA4EE" style="color:#fff;" >购买</td> 
							</tr>
							 
							<%
							List<PointCard> datas = (List<PointCard>)request.getAttribute("cards");
								if(datas == null || datas.size() == 0){
									%>
									<tr>
										<td colspan="4" align="center">没有可售点卡</td> 
									</tr>
									<%
								}else{
									
									//Collections.reverse(datas);
									for(int i=0,l=datas.size();i<l;i++){
										PointCard item = datas.get(i);
										%>
										<tr>
											<td align="center"><%=item.getTitle() %></td>
											<td align="center"><%=item.getMoney() %></td>
											<td align="center"><%=item.getPoints()%></td>
											<td align="center"><button type="button" class="btn btn-default " onclick="buy_product(<%=item.getId() %>)" id="buy_btn_<%=item.getId() %>">购买</button>
											</td> 
										</tr>
										
										<%
									}
									
								}
							%>
					</table>
					 
				</div>
			</div>
		</div>
	</div>
<jsp:include page="../common/footer.jsp"></jsp:include> 
</body>
<script type="text/javascript">

$(function(){
	 
	 
})
var datamap = {};
<%
						 
if(datas != null && datas.size() > 0){
	for(int i=0,l=datas.size();i<l;i++){
		PointCard item = datas.get(i);
		%>
			datamap["id_"+<%=item.getId() %>] ="您将购买<%=item.getTitle() %>,需要支付<%=item.getMoney() %>元，请确认";
		<%
	}
	
}
%>
function buy_product(id){
	var result = window.confirm(datamap["id_"+id]);
	if(result){
		var url = "<%=request.getContextPath()%>/site/products/pointcard/dobuy";
	  	$.ajax({
			  type:'post',
			  url: url,
			  context: document.body,
			  beforeSend:function(){
				  $("#buy_btn_"+id).attr("disabled",true); 
				  $("#buy_btn_"+id).text("提交中"); 
				  
			 },
			  data:{
				  d:new Date().getTime(),
				  cardId:id
			  },
			  success:function(json){
				  
				  var result = json["result"]; 
				  $("#buy_btn_"+id).text("购买");
				  if(result){
					alert("购买成功");
					  $("#buy_btn_"+id).attr("disabled",false); 
					window.location.reload();
					  return;
				  }else{
					  alert("购买失败");
					  $("#buy_btn_"+id).attr("disabled",false); 
				  }  
				 
				  
				 	  
			  },
		      error:function(xhr,textStatus,errorThrown){
		    	  $("#buy_btn_"+id).attr("disabled",false); 
		    	  $("#buy_btn_"+id).text("购买"); 
		    	   
		    	  
		  		var responseText = xhr.responseText;
		  		var obj = jQuery.parseJSON(responseText);
				var errortype = obj.errortype
		  		var msg = obj.msg; 
				 alert(msg);
				
		  		// $(btn)).removeAttr("disabled");
		  } 
		})
	}
}

</script>
</html>
