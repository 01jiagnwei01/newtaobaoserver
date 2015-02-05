<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head><title>Exception!</title></head>
<body>
<% Exception e = (Exception)request.getAttribute("ex"); %>
<H2>未知错误: <%= e.getClass().getSimpleName()%></H2>
<hr />
<P>错误描述：</P>
<%= e.getMessage()%>
<P>错误信息：</P>
<%  
StackTraceElement[] els =   e.getStackTrace(); 
for(int i=0,l=els.length;i<l;i++){
	%>
		<%= els[i]%><br/>
	<% 
}

%>
</body>
</html>