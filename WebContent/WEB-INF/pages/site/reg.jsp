<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<link href="<%=request.getContextPath() %>/resources/css/style.css" rel="stylesheet" type="text/css">

<body style="background:#FFF;">
	<div style="width:100%; height:28px; background-color:#E8E8E8;">
		<div class="center tar" style="width:1200px;">
		<a href="<%=request.getContextPath() %>/login" style="display:inline-block; padding:5px 15px; background-color:#09f; color:#fff;">登录</a>
		</div>
	</div>


	<div class="center tar" style="width:1200px; height:80px; line-height:80px; background:url(<%=request.getContextPath() %>/resources/images/logo.gif) left center no-repeat;">
		<a class="fs16" href="###">我要发布任务</a>
		<a class="fs16" href="###" style="margin-left:70px;">我要兼职</a>
		<a class="fs16" href="###" style="margin-left:70px;">我的账户</a>
		<a class="fs16" href="###" style="margin-left:70px;">关于谷谷道场</a>
	</div>

	<div style="border-radius:10px; width:400px; margin:10px auto 30px; padding-bottom:30px; border:solid 1px #ccc;">
			<table width="90%" border="0" cellspacing="0" cellpadding="0" class="center" style="margin-top:30px;">
				<tr>
						<td align="right"><span style="color:#09F; font-weight:bold">新用户注册</span></td>
						<td align="right"><a href="#">已有账号</a></td>
				</tr>
				<tr>
						<td align="right">&nbsp;</td>
						<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">邮箱：</td>
					<td><input type="text" name="textfield" id="textfield" style="padding:10px 5px; width:200px;" placeholder="请填写常用邮箱"></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td><span style="color:#F00">该邮箱已经使用过&nbsp;&nbsp;输入邮箱地址错误</span></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td>
						<input type="text" name="textfield5" id="textfield5" style="padding:10px 5px; width:110px;" placeholder="请输入邮箱验证码">
						<a href="#" style="display:inline-block; border-radius:5px; padding:8px 0; background-color:#eee; width:85px; line-height:25px; height:25px; margin-right:20px;" class="tac">发送验证码</a>
					</td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td><span style="color:#F00">提示</span></td>
				</tr>
				<tr>
					<td align="right">用户名：</td>
					<td><input type="text" name="textfield2" id="textfield2" style="padding:10px 5px; width:200px;" placeholder=""></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td><span style="color:#F00">提示</span></td>
				</tr>
				<tr>
					<td align="right">密码：</td>
					<td><input type="text" name="textfield3" id="textfield3" style="padding:10px 5px; width:200px;" placeholder="请输入8-16位字母或数字"></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td><span style="color:#F00">提示</span></td>
				</tr>
				<tr>
					<td align="right">确认密码：</td>
					<td><input type="text" name="textfield4" id="textfield4" style="padding:10px 5px; width:200px;" placeholder="请再次输入"></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td><span style="color:#F00">提示</span></td>
				</tr>
			</table>
			<div class="tac"><a href="###" style="display:inline-block; border-radius:5px; background-color:#09F; width:275px; color:#fff; line-height:40px; height:40px;" class="tac">注册</a></div>

	</div>

	<!--<div style="border-radius:10px; width:400px; margin:10px auto 30px; border:solid 1px #ccc; font-size:20px; font-weight:bold; height:300px; line-height:300px;" class="tac">
		<a href="###" style="color:#09f;">激活成功，3秒后跳到首页</a>
	</div>-->

	<!--<div style="border-radius:10px; width:400px; margin:10px auto 30px; border:solid 1px #ccc; font-size:20px; font-weight:bold; height:300px; line-height:300px;" class="tac">
		激活时间已过！期激活不成功，请重新注册
	</div>-->
	
	
	<div style="width:100%; background-color:#8d8d8d; position:fixed; left:0; bottom:0;">
		<div class="center tac" style="width:1200px; line-height:100px; height:100px; color:#FFF;">谷谷道场</div>
	</div>

	
</body>
</html>