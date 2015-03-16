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
	}else if("order_create".equals(titletype)){
		out.println("<title>发布新任务</title>");
	}else if("my_order_page".equals(titletype)){
		out.println("<title>我的订单列表</title>");
	}else if("task_mypublish".equals(titletype)){
		out.println("<title>我发布的任务</title>");
	}else if("task_myuncompletetasklist".equals(titletype)){
		out.println("<title>我未完成的任务</title>");
	}else if("task_mycompletetasklist".equals(titletype)){
		out.println("<title>我完成的任务</title>");
	}else if("task_detail".equals(titletype)){
		out.println("<title>任务详情</title>");
	} else if("index".equals(titletype)){
		out.println("<title>谷谷道场首页</title>");
	} else if("login".equals(titletype)){
		out.println("<title>谷谷道场登陆页</title>");
	} else if("reg".equals(titletype)){
		out.println("<title>谷谷道场注册页</title>");
	}  else if("findback_password".equals(titletype)){
		out.println("<title>谷谷道场找回密码页</title>");
	}  else if("about_us".equals(titletype)){
		out.println("<title>关于谷谷道场</title>");
	}
}
%>
 