<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="com.gxkj.common.util.SessionUtil,com.gxkj.taobaoservice.entitys.UserBase" %>
<% 
String showlogin = request.getParameter("showlogin"); 
String showreg = request.getParameter("showreg");
boolean showloginBoolean = StringUtils.isBlank(showlogin)?false:"true".equals(showlogin)?true:false;
boolean showregBoolean = StringUtils.isBlank(showreg)?false:"true".equals(showreg)?true:false;
UserBase userBase = SessionUtil.getSiteUserInSession(request);
%>
<div style="width:100%; background-color:#E8E8E8;">
	<div class="center tar" style="width:1200px;">
	<%if(userBase != null){
		%>
		欢迎您：<a href="<%=request.getContextPath() %>/site/useraccount/log" style="margin-right:15px; color:#4CA4EE;"><%=userBase.getUserName() %></a>编号:<span style="padding-right:15px; font-weight:bold;"><%=userBase.getId() %></span>
		<a href="<%=request.getContextPath() %>/logout" style="display:inline-block; padding:5px 15px; background-color:#09f; color:#fff;">退出</a>
		<%
	} else {
	 
		if(showregBoolean){
		%>
			<a href="<%=request.getContextPath() %>/reg" style="display:inline-block; padding:5px 15px; background-color:#09f; color:#fff; margin-right:15px;">注册</a>
		<%
		}
		if(showloginBoolean){
		%>
		<a href="<%=request.getContextPath() %>/login" style="display:inline-block; padding:5px 15px; background-color:#09f; color:#fff;">登录</a>
		<%
		} 
	}
	%>
	</div>
</div>