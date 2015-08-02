<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.gxkj.taobaoservice.entitys.*,com.gxkj.common.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <jsp:include page="./site_story_meta.jsp"></jsp:include>
    <jsp:include page="./story-common-icon.jsp"></jsp:include>
   <title><%=SystemGlobals.getPreference("story.title") %>-<c:out value="${title}" escapeXml="false" /></title>
    <jsp:include page="./story-common-css.jsp"></jsp:include>
    <style type="text/css">
    /*container 下所有的a标签*/
		.container   a, 
		.container   a:focus, 
		.container  a:hover
		{
			color:#fff;
		}
		
		
		.detail .digg::after {
		    clear: both;
		    content: ".";
		    display: block;
		    height: 0;
		    visibility: hidden;
		}
		.detail .digg {
		    display: block;
		    margin: 0 0 40px;
		    text-align: center;
		}
		
		 .digg dt {
		    background: rgba(0, 0, 0, 0) url("<%=request.getContextPath() %>/resources/images/digg_bg.png") no-repeat scroll 0 0;
		    color: #fff;
		    cursor: pointer;
		    font: 18px/36px Microsoft YaHei;
		    height: 36px;
		    width: 77px;
		}
		.digg dl {
		    border: 1px solid #ccc;
		    border-radius: 3px;
		    display: inline-block;
		    margin: 0 5px;
		    padding: 1px;
		    text-align: center;
		    width: 77px;
		}
		.suojin div{text-indent:2em}
    </style>
  </head>
  <body>
  	<jsp:include page="./story-common-navbar.jsp"></jsp:include>
    <div class="container">
     	<h1><c:out value="${story.articleTitle} "/></h1>
            <c:choose> 
				  <c:when test="${story == null}"> 
				  	<div class="col-xs-12 col-sm-12 col-md-12  col-lg-12">
				  		 <div class="row">
				  		 	 <div class="col-xs-6 col-sm-4 col-md-4  col-lg-4">
				              <h2>您要找的故事飘走了^^^<br/></h2> 
				              <p><a class="btn btn-default" href="<%=request.getContextPath() %>/" role="button">去首页看看 &raquo;</a></p>
				            </div><!--/.col-xs-6 col-sm-4 col-md-3  col-lg-3-->
				  		 </div>
				  	</div>
				  </c:when> 
				  <c:otherwise>
				  		<div class="row suojin"> 
				       	<c:out value="${story.articleContent}" escapeXml="false" />
				       	</div>
				       	<div class="row">
				       		<!--    -->
				       		<div class="digg" style="float:right; text-align:right; ">
									<input type="hidden" value="2825261" id="digg_id">
									<dl class="support" >
										<dt id="support_btn">顶</dt>
										<dd id="support_btn_txt">${story.praiseNumber}</dd>
									</dl>
									
									<dl class="support">
										<dt>浏览数</dt>
										<dd>${story.hitTimes}</dd>
									</dl>
							</div>
				       	 </div>
				  </c:otherwise> 
				</c:choose>
      
      <hr/>
      <jsp:include page="./story-common-footer.jsp"></jsp:include>
    </div><!--/.container-->
    <jsp:include page="./story-common-js.jsp"></jsp:include>
    <!-- appjs -->
    <script src="<%=request.getContextPath() %>/resources/siteappjs/appjs.js"> </script>
    
     <script type="text/javascript">
    $(document).ready(function(){
    	<c:choose> 
  	  <c:when test="${story == null}"> 
  	  </c:when> 
  	  <c:otherwise>  
  	  addLookNum("<%=request.getContextPath() %>/story/look/${story.articleId}");
  	  $("#support_btn").click(function(){praiseOrTireFn("<%=request.getContextPath() %>","praise","${story.articleId}","support_btn_txt")});
  	
  	  </c:otherwise> 
  	</c:choose>
    	 
    });
    </script>
 <!-- 百度分享 -->  
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":["weixin","tsina","tqf","copy","print"],"bdPic":"","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"7","bdPos":"right","bdTop":"100"},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
  </body>
</html>
