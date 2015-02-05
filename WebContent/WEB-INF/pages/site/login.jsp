<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<link href="<%=request.getContextPath() %>/resources/css/style.css" rel="stylesheet" type="text/css">
<jsp:include page="./common/js.jsp"></jsp:include> 

<body style="background:#FFF;">
	<jsp:include page="./common/head.jsp" flush="true">
		<jsp:param name="showlogin" value="false"></jsp:param>
		<jsp:param name="showreg" value="true"></jsp:param>
	</jsp:include>  
	<jsp:include page="./common/subheader.jsp" flush="true">
		<jsp:param name="showshouye" value="true"></jsp:param>
	</jsp:include>  

	<div style="border-radius:10px; width:400px; margin:10px auto 30px; padding-bottom:30px; border:solid 1px #ccc;">
			<table cellspacing="0" cellpadding="0" class="center" style="margin-top:30px;width: 80%;border: 0;">
				<tr>
						<td align="right"><span style="color:#09F; font-weight:bold">用户登录</span></td>
						<td align="right"></td>
				</tr>
				<tr>
						<td align="right">&nbsp;</td>
						<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">用户名：</td>
					<td><input type="text" name="textfield" id="textfield" style="padding:10px 5px; width:250px;"></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td><span style="color:#F00">提示</span></td>
				</tr>
				<tr>
						<td align="right">密码：</td>
						<td><input type="text" name="textfield2" id="textfield2" style="padding:10px 5px; width:250px;" placeholder=""></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td><span style="color:#F00">提示</span></td>
				</tr>
			</table>
			<div class="tac">
				<a href="###" style="display:inline-block; border-radius:5px; background-color:#09F; width:80%; color:#fff; line-height:40px; height:40px;" class="tac">登录</a>
			</div>
				<div style="text-align:right; padding-top:20px; padding-right:40px;"><a href="<%=request.getContextPath() %>/reg" style="">免费注册</a></div>

	</div>
	<jsp:include page="./common/footer.jsp"></jsp:include>
</body>
</html>