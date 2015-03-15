<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<% 
String titletype = request.getParameter("titletype"); 
if(StringUtils.isBlank(titletype)){
	
}else {
	if("bind_tel".equals(titletype)){
		out.println("<title>绑定手机号</title>");
	}else if("bind_qq".equals(titletype)){
		out.println("<title>绑定QQ号</title>");
	}else if("bind_password".equals(titletype)){
		out.println("<title>修改密码</title>");
	}else if("bind_caozuoma".equals(titletype)){
		out.println("<title>绑定操作码</title>");
	}else if("bind_email".equals(titletype)){
		out.println("<title>绑定邮箱</title>");
	}else if("bind_alipay".equals(titletype)){
		out.println("<title>绑定淘宝号</title>");
	}else if("chongzhi".equals(titletype)){
		out.println("<title>充值</title>");
	}else if("chongzhirecord".equals(titletype)){
		out.println("<title>充值记录</title>");
	}else if("tixian".equals(titletype)){
		out.println("<title>提现</title>");
	}else if("tixian_recordpage".equals(titletype)){
		out.println("<title>提现记录</title>");
	}else if("useraccount_log".equals(titletype)){
		out.println("<title>资金记录</title>");
	}else if("point_card".equals(titletype)){
		out.println("<title>购买点卡</title>");
	}
}
%>
 