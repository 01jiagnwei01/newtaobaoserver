<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.gxkj.taobaoservice.entitys.*,com.gxkj.common.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <jsp:include page="./site_story_meta.jsp"></jsp:include>
    <jsp:include page="./story-common-icon.jsp"></jsp:include>
    <title><%=SystemGlobals.getPreference("story.title") %></title>
    <jsp:include page="./story-common-css.jsp"></jsp:include>
  </head>

  <body>
  	<jsp:include page="./story-common-navbar.jsp"  ></jsp:include>
    <div class="container">
      <div class="row row-offcanvas row-offcanvas-right">
        <div class="col-xs-12 col-sm-12 col-md-12  col-lg-12">
          <div class="jumbotron">
            <h1>咕咕道场</h1>
            <p>咕咕鸟给你讲好故事啦，把你想听的故事发qq告诉我们。</p>
          </div>
          <div class="row">
          	<c:forEach var="story"  items="${list}" >
			     <div class="col-xs-6 col-sm-6 col-md-6  col-lg-4">
	              <h2>${story.articleTitle }</h2>
	              <p>${story.storySummary }</p>
	              <p><a class="btn btn-default" href="<%=request.getContextPath() %>/story/${story.articleId }" target="_blank" role="button">去看看 &raquo;</a></p>
	            </div><!--/.col-xs-6 col-sm-6 col-md-6  col-lg-4-->
			</c:forEach>
          </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->
      </div><!--/row-->
      <hr/>
      <jsp:include page="./story-common-footer.jsp"></jsp:include>
    </div><!--/.container-->
    <jsp:include page="./story-common-js.jsp"></jsp:include>
    <!-- appjs -->
    <script src="<%=request.getContextPath() %>/resources/siteappjs/appjs.js"> </script>
  </body>
</html>
