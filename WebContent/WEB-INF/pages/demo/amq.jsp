<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals,com.gxkj.taobaoservice.entitys.*,com.gxkj.taobaoservice.dto.*"%>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统amqp</title>
<%@include file="../common/easyui-html5.jsp" %>
</head>
<body>
要发送的数据：<input type="text" value="1999" id="name">
<input type='button' value="发送微博数据" onclick="sendReq(1)"><br/>
<input type='button' value="发送评论数据" onclick="sendReq(2)"><br/>
<input type='button' value="获取请求" onclick="receiveMsg()"><br/>
</body>
<script type="text/javascript">
function sendReq(index){
	if(index ==1){
		var url = "<%=request.getContextPath() %>/demo/amq/sendweibo";
	}else if(index == 2){
		var url = "<%=request.getContextPath() %>/demo/amq/sendPinglun";
	}
	
	var msg = $("#name").val();
		 $.ajax({
	  	  type:'post',
			  url: url,
			  data:{
				  msg:msg
			  },
			  context: document.body,
			  success:function(json){
			 	 alert(json);
				
			  },
			  error:function(xhr,textStatus,errorThrown){
			  		var responseText = xhr.responseText;
			    	$.messager.alert('系统提示',' 失败，请刷新后重试!','error');
			  
			  } 
		});
}
function receiveMsg(){
	var url = "<%=request.getContextPath() %>//demo/amq/receiv";
	 $.ajax({
 	  type:'post',
		  url: url,
		  data:{
			  msg:"1999"
		  },
		  context: document.body,
		  success:function(json){
		 	 alert(json);
			
		  },
		  error:function(xhr,textStatus,errorThrown){
		  		var responseText = xhr.responseText;
		    	$.messager.alert('系统提示',' 失败，请刷新后重试!','error');
		  
		  } 
	});
}


</script>
</html>