<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%
String active=request.getParameter("active");
if(StringUtils.isBlank(active)){
	active = "shenghua";
}
%>
<nav class="navbar navbar-fixed-top navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
         <a class="navbar-brand" href="<%=request.getContextPath() %>/">故事网</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li <%if("shenghua".equals(active)) { %> class="active" <%} %>><a href="<%=request.getContextPath() %>/">神话</a></li>
            <li <%if("games".equals(active)) { %> class="active" <%} %>><a href="<%=request.getContextPath() %>/games">有奖游戏</a></li>
            <!-- 
            <li><a href="#about">童话</a></li>
             -->
          </ul>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
    </nav><!-- /.navbar -->