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
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/jquery-1.11.2.min.js"> </script>
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
  	var url = "<%=request.getContextPath()%>/admin/dologin";
	$.ajax({
			  type:'post',
			  url: url,
			  context: document.body,
			  beforeSend:function(){
			  		// $(btn).attr('disabled','disabled');
				 },
			  data:{
				  name: name,pass:pass
			  },
			  success:function(json){
				 
			  		//json = jQuery.parseJSON(json);
				 	 var result = json["result"];
				 	 if(result){
				 	 	window.location = "<%=request.getContextPath()%>/admin/index";
				 	 }else{
				 	 	 var user = json.entity;
				 	 	 if(!user){
				 	 	 	alert("用户名或者密码错误");
				 	 	 }else {
				 	 	 	var status = user.status;
				 	 	 	if(status != 1){
				 	 	 		alert("您已经被锁定,请与管理员联系");
				 	 	 	}
				 	 	 }
				 	 }
				 	  
			  },
		      error:function(xhr,textStatus,errorThrown){
		  		var responseText = xhr.responseText;
		  		// $(btn)).removeAttr("disabled");
		  } 
  })
 }
  </script>
  </head>

  <body>

     <div class="container-fluid" >
		<div class="row-fluid">
			<div class="row-fluid">
				<div class="span12 center login-header" style="height:100px;">
					<h2>高新科技管理员系统</h2>
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
						<!-- 
							<div class="input-prepend" >
								<span class="add-on"><i class="icon-road"></i></span>
								<select name="flag"  class="input-large llwidth"   title="用户角色" data-rel="tooltip">
									<option value="1">教师</option>
									<option value="2">学生</option>
								  </select>
							 </div>
						 -->
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
