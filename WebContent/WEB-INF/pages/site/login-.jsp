<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals,java.util.*"%>
 <!DOCTYPE html>
<html lang="en">
<head><%-- 
<jsp:include page="../common/bootstrap.jsp"   >
	<jsp:param name="title" value="登陆页面"/>
</jsp:include>--%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- The styles -->
	<link id="bs-css" href="<%=request.getContextPath() %>/resources/charisma/css/bootstrap-cerulean.css" rel="stylesheet">
	<link id="bs-css" href="<%=request.getContextPath() %>/resources/charisma/css/bootstrap-responsive.css" rel="stylesheet">
	<link id="bs-css" href="<%=request.getContextPath() %>/resources/charisma/css/charisma-app.css" rel="stylesheet">
	<link href='<%=request.getContextPath() %>/resources/charisma/css/noty_theme_default.css' rel='stylesheet'>
	<link   href="<%=request.getContextPath() %>/resources/charisma/css/jquery.noty.css" rel='stylesheet'>
	<script src="<%=request.getContextPath() %>/resources/charisma/js/jquery-1.7.2.min.js"></script>
	<script src="<%=request.getContextPath() %>/resources/charisma/js/jquery.noty.js"></script>
	<script type="text/javascript">if(window.top != window){
			window.top.location = window.location;
		
		}</script>
	<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  
	  .llwidth{
	  	width:200px;
	  }
	</style>
	<script type="text/javascript">

  function loginfn(btn){
  
  	var name = $("#username").val();
  	var pass = $("#password").val();
  	var yanzhengma = $("#yanzhengma").val();
  	var url = "<%=request.getContextPath()%>/login";
	$.ajax({
			  type:'post',
			  url: url,
			  context: document.body,
			  beforeSend:function(){
			  		// $(btn).attr('disabled','disabled');
				 },
			  data:{
				  username: name,password:pass,yanzhengma:yanzhengma
			  },
			  success:function(json){
				 
			  		//json = jQuery.parseJSON(json);
				 	 var result = json["result"];
				 	 if(result){
				 	 	window.location = "<%=request.getContextPath()%>";
				 	 }else{
				 	 	 var msg = json.msg;
				 	 	var options = {"text":msg,"layout":"topRight","type":"error"};
		 	 			noty(options);
				 	 }
				 	  
			  },
		      error:function(xhr,textStatus,errorThrown){
		  		var responseText = xhr.responseText;
		  		// $(btn)).removeAttr("disabled");
		  } 
  })
 }
  function refresh(obj) {        obj.src = "<%=request.getContextPath()%>/yanzhengma?"+Math.random();    }
  </script>
  </head>

  <body>

     <div class="container-fluid" >
		<div class="row-fluid">
			<div class="row-fluid">
				<div class="span12 center login-header" style="height:100px;">
					<h2>谷谷道场信息化系统</h2>
				</div><!--/span-->
			</div><!--/row-->
			<div class="row-fluid">
				<div class="well span5 center login-box"   >
					<% 
						Boolean userNameError = (Boolean)request.getAttribute("userNameError");
						 Boolean userPasswordError = (Boolean)request.getAttribute("userPasswordError");
						if(userNameError  !=null && userNameError.booleanValue()){
						 %>
					<div class="alert alert-info">
						用户名不正确
					</div>
					<%
						}
						if(userPasswordError!=null && userPasswordError.booleanValue()){
					 %>
						<div class="alert alert-info">
							用户名或者密码不正确
						</div>
						<%
							}
							
						%>
					
					<form class="form-horizontal" action="" method="post">
						<fieldset>
							<div class="clearfix"></div>
							<div class="input-prepend" title="用户名" data-rel="tooltip">
								<span class="add-on"><i class="icon-user"></i></span><input autofocus class="input-large llwidth" name="username" id="username" type="text" value="" />
							</div>
							<div class="clearfix"></div>

							<div class="input-prepend" title="密码" data-rel="tooltip">
								<span class="add-on"><i class="icon-lock"></i></span>
								<input class="input-large llwidth" name="password" id="password" type="password" value="" />
							</div>
								<div class="clearfix"></div>
							<div class="input-prepend" title="验证码" data-rel="tooltip">
								<span class="add-on"><i class="icon-road"></i></span>
								<input class="input-large llwidth" name="yanzhengma" id="yanzhengma" type="text" value="" />
								 <img title="点击更换" onclick="javascript:refresh(this);" src="<%=request.getContextPath()%>/yanzhengma">
							</div>
							<div class="clearfix"></div>
							<p class="center span5">
								<button type="button" class="btn btn-primary"   onclick="loginfn(this);">登陆</button>
							</p>
						</fieldset>
					</form>
				</div><!--/span-->
			</div><!--/row-->
				</div><!--/fluid-row-->
		
	</div><!--/.fluid-container-->
  </body>
</html>
