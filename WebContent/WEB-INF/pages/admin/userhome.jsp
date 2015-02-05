<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.gxkj.common.util.SystemGlobals,java.util.*,com.gxkj.taobaoservice.entitys.*"%>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户中心</title>
	<link  href="<%=request.getContextPath() %>/resources/charisma/css/bootstrap-cerulean.css" rel="stylesheet">
	<link  href="<%=request.getContextPath() %>/resources/charisma/css/bootstrap-responsive.css" rel="stylesheet">
	<link   href="<%=request.getContextPath() %>/resources/charisma/css/charisma-app.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/resources/charisma/css/colorbox.css" rel='stylesheet'>
	<link href='<%=request.getContextPath() %>/resources/charisma/css/noty_theme_default.css' rel='stylesheet'>
	<link   href="<%=request.getContextPath() %>/resources/charisma/css/jquery.noty.css" rel='stylesheet'>
</head>
<body  >
	<div class="row-fluid sortable" >
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2>修改密码</h2>
					</div>
					<div class="box-content"  >
						<form class="form-horizontal">
						  <fieldset>
							<div class="control-group">
							  <label class="control-label" for="newpassword">新密码</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="newpassword" type="password" name="newpassword"  value="${newpassword }">
								</div>
							</div>
							<div class="control-group">
							  <label class="control-label" for="confirmpassword">确认密码</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="confirmpassword" type="password" name="confirmpassword"  value="${confirmpassword }">
								</div>
							</div>
							<div class="form-actions">
							  <button type="button" class="btn btn-primary" onclick="submitFn()">保存</button>
							  <button type="reset" class="btn">重新输入</button>
							</div>
						  </fieldset>
						</form>   

					</div>
				</div><!--/span-->

			</div>
 
</body>
<script src="<%=request.getContextPath() %>/resources/charisma/js/jquery-1.7.2.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/charisma/js/jquery.noty.js"></script>
<script type="text/javascript">
function submitFn(){
	var newpassword = $.trim($("#newpassword").val());
	var confirmpassword = $.trim($("#confirmpassword").val());
	
	if(newpassword.length == 0){
		var options = {"text":"新密码不能为空","layout":"topRight","type":"error"};
		noty(options);
		return;
	}
	if(confirmpassword.length == 0){
		var options = {"text":"确认密码不能为空","layout":"topRight","type":"error"};
		noty(options);
		return;
	}
	if(confirmpassword != newpassword){
		var options = {"text":"确认密码与新密码不一致","layout":"topRight","type":"error"};
		noty(options);
		return;
	}
	$.ajax({
				  	  	  type:'post',
						  url: "<%=request.getContextPath() %>/admin/user/setmypassword",
						  context: document.body,
						  data:{
						  	  
						  	 password:newpassword,
						  	 confirmpassword:confirmpassword
						  },
						  success:function(json){
						 //	json = jQuery.parseJSON(json);
						 	if(!json['result']){
						 	 	var msg = json["msg"];
								var options = {"text":msg,"layout":"topRight","type":"error"};
									noty(options);
								
						 	}else{
						 		var options = {"text":"保存成功","layout":"topRight","type":"success",timeout:1000};
									noty(options);
						 	}
						 	 
							
						  },
						  error:function(xhr,textStatus,errorThrown){
						  		 
						  
						  } 
					});
	
}
</script>
</html>