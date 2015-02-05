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
  
  	var username = $("#username").val();
  	var password = $("#password").val();
  	var repassword = $("#repassword").val();
  	
  	var yanzhengma = $("#yanzhengma").val();
  	var email = $("#email").val();
  	/***/
  	if(!email ){
  		
  		alert("邮箱不能为空 ");
  		return;
  	}else if(!isEmail(email)){
  		alert("邮箱格式不对  ");
  		return;
  	}
	if(!password ){
  		
  		alert("密码不能为空 ");
  		return;
  	}
	if(!repassword){
  		alert("确认密码不能为空 ");
  		return;
  	}
	if(repassword != password){
  		alert("确认密码与密码不一致  ");
  		return;
  	}
	if(!yanzhengma  ){
		alert("验证码不能为空");
		return;
	}else if ( yanzhengma.length!=4){
		alert("验证码输入错误");
		return;
	}
	
  	var yanzhengmaurl = "<%=request.getContextPath()%>/yanzhengma/get";
  	
  	$.ajax({
		  type:'GET',
		  url: yanzhengmaurl,
		  context: document.body,
		  beforeSend:function(){
		  		 
			 },
		  data:{ d:new Date().getTime()},
		  success:function(json){
			  
			  var result = json["result"];
			  var yanzhengmaget = json["entity"];
			if(yanzhengmaget.toUpperCase() != yanzhengma.toUpperCase()){
				alert("验证码输入错误");
				return;
			}else{
				doReg(username,email,password,repassword,yanzhengma);
			}
			 	  
		  },
	      error:function(xhr,textStatus,errorThrown){
	    	  
	  		var responseText = xhr.responseText;
	  		// $(btn)).removeAttr("disabled");
	  } 
})
  
 }
 function doReg(username,email,password,repassword,yanzhengma){
	 var url = "<%=request.getContextPath()%>/reg";
	 var data = {
		  userName: username,
		  email:email,
		  password:password,
		  rePassword:repassword,
		  yanzhengma:yanzhengma,
		  d:new Date().getTime()
	  }
	 $.ajax({
		  type:'POST',
		  url: url,
		  context: document.body,
		  beforeSend:function(){
			 },
		  data:data,
		  success:function(json){
			  //alert(json);
			  var result = json.result;
			  if(result){
				  alert("已经向您的邮箱发送了激活信息");
			  } 
			 	  
		  },
	      error:function(xhr,textStatus,errorThrown){
	  		var responseText = xhr.responseText;
	  		json = $.parseJSON(responseText);
	  		alert(json.msg);
	  		// $(btn)).removeAttr("disabled");
	  } 
	})
		 
 }
 function refresh(obj) {        obj.src = "<%=request.getContextPath()%>/yanzhengma?"+Math.random();    }
 function isEmail(str){ 
	  var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/; 
	  return reg.test(str); 
} 
 
 function sendMail(){
	 var url = "<%=request.getContextPath()%>/reg/sendmail";
		var email = $("#email").val();
	 var data = {
			 tomail:email,
			  d:new Date().getTime()
		  }
		 $.ajax({
			  type:'POST',
			  url: url,
			  context: document.body,
			  beforeSend:function(){
				 },
			  data:data,
			  success:function(json){
				  //alert(json);
				  var result = json.result;
				  if(result){
					  alert("已经向您的邮箱发送了激活信息");
				  }else {
					  msg = json.msg;
					  if(msg == 'Invalid Addresses'){
						  alert("您的邮箱地址无效");
					  }else if (msg = 'emailNoValid'){
						  alert("邮箱地址无效");
					  }else {
						  alert(msg)
					  }
					  
				  }
				 	  
			  },
		      error:function(xhr,textStatus,errorThrown){
		  		var responseText = xhr.responseText;
		  		json = $.parseJSON(responseText);
		  		alert(json.msg);
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
					<h2>谷谷道场信息化系统<br/>注册页面</h2>
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
							<div class="input-prepend" title="邮箱" data-rel="tooltip">
								<span class="add-on"><i class="icon-flag"></i></span><input autofocus class="input-large llwidth" name="email" id="email" type="text" value="" />
							</div>
							<div class="clearfix"></div>
							<div class="input-prepend" title="会员名称" data-rel="tooltip">
								<span class="add-on"><i class="icon-flag"></i></span><input autofocus class="input-large llwidth" name="username" id="username" type="text" value="" />
							</div>
							<div class="clearfix"></div>

							<div class="input-prepend" title="密码" data-rel="tooltip">
								<span class="add-on"><i class="icon-lock"></i></span>
								<input class="input-large llwidth" name="password" id="password" type="password" value="" />
							</div>
								<div class="clearfix"></div>
							<div class="input-prepend" title="确认密码" data-rel="tooltip">
								<span class="add-on"><i class="icon-lock"></i></span>
								<input class="input-large llwidth" name="repassword" id="repassword" type="password" value="" />
							</div>
								<div class="clearfix"></div>
							<div class="input-prepend" title="验证码" data-rel="tooltip">
								<span class="add-on"><i class="icon-road"></i></span>
								<input class="input-large llwidth" name="yanzhengma" id="yanzhengma" type="text" value="" />
								 <img title="点击更换" onclick="javascript:refresh(this);" src="<%=request.getContextPath()%>/yanzhengma">
							</div>
							<div class="clearfix"></div>
							<p class="center span5">
								<button type="button" class="btn btn-primary"   onclick="sendMail()">发送验证码</button>
							</p>
						</fieldset>
					</form>
				</div><!--/span-->
			</div><!--/row-->
				</div><!--/fluid-row-->
		
	</div><!--/.fluid-container-->
  </body>
</html>
