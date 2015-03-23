<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.gxkj.taobaoservice.entitys.*" %>
<% 
UserAccount userAccount = (UserAccount)request.getAttribute("userAccount");
 
%>
 <h2 style="background:#f8f8f8; font-weight:normal; margin-top:30px;">
                	<div style="padding:10px;">
	                	<span style="font-size:16px;">
	                		可用余额：</span><span style="color:rgb(57, 161, 234);"><%=userAccount.getCurrentBalance() %>
	                	</span>
	                	 <span style="padding-left:10px;  font-size:16px;">
	                		可用点数：</span><span style="color:rgb(57, 161, 234);"><%=userAccount.getCurrentRestPoints() %>
	                	</span>
	                	<span style="font-size:16px;">
	                		冻结资金：</span><span style="color:rgb(57, 161, 234);"><%=userAccount.getLockedBalance() %>
	                	</span>
                	</div>
</h2>