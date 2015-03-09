<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<% 
String showshouye = request.getParameter("showshouye");  
boolean subheaderBoolean = StringUtils.isBlank(showshouye)?false:"true".equals(showshouye)?true:false; 
%>
<div style="width:100%; height:80px; background-color:#fff;">
		<div class="center tar" style="width:1200px; height:80px; line-height:80px; background:url(<%=request.getContextPath() %>/resources/images/logo.gif) left center no-repeat;">
			<%
				if(subheaderBoolean){
			%>
			<a class="fs16" href="<%=request.getContextPath() %>">首页</a>
			<a class="fs16" href="<%=request.getContextPath() %>/site/order/create" style="margin-left:70px;">我要发布任务</a>
			<%
				}else{
			%>
			<a class="fs16" href="<%=request.getContextPath() %>/site/order/create" >我要发布任务</a>
			<%
				}
			%>
			<a class="fs16" href="<%=request.getContextPath() %>/site/task/list" style="margin-left:70px;">我要兼职</a>
			<a class="fs16" href="<%=request.getContextPath() %>/site/useraccount" style="margin-left:70px;">我的账户</a>
			<a class="fs16" href="<%=request.getContextPath() %>/about" style="margin-left:70px;">关于谷谷道场</a>
		</div>
	</div>