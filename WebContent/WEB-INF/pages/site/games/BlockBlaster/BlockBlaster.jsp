<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.gxkj.taobaoservice.entitys.*,com.gxkj.common.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <jsp:include page="../../story/site_story_meta.jsp"></jsp:include>
    <jsp:include page="../../story/story-common-icon.jsp"></jsp:include>
    <title><%=SystemGlobals.getPreference("story.title") %>-有奖游戏</title>
    <jsp:include page="../../story/story-common-css.jsp"></jsp:include>
    <style type="text/css">
     
    </style>
  </head>
  <body>
  	<jsp:include page="../../story/story-common-navbar.jsp"  >
  			<jsp:param name="active" value="games" />
  	</jsp:include>
    <div class="container">
         <div class="col-xs-12 col-sm-12 col-md-12  col-lg-12" id="div1">
				<canvas id="canvas" width="1024" height="460" style="margin-top:0px;"></canvas>
 		</div>   
      <hr/>
      <jsp:include page="../../story/story-common-footer.jsp"></jsp:include>
    </div><!--/.container-->
    <script src="<%=request.getContextPath() %>/resources/games/BlockBlaster/js/BlockBlaster.js"></script>
    <jsp:include page="../../story/story-common-js.jsp"></jsp:include>
     <script type="text/javascript">
     $(window).resize(resizeCanvas);
     function resizeCanvas() {
     	 var h = 470;
     	 var w = $("#div1").width();
     	 
     	 if(w>=1140){
     		 h = 470;
     	 }
            $("#canvas").attr("width", w);
            $("#canvas").attr("height",h);
            //alert($("#div1").width());
           // alert($("#div1").height());
            context.fillRect(0, 0, $("#canvas").width(), $("#canvas").height());
            //alert(2);
     };
     resizeCanvas();
    </script>
 <!-- 百度分享 -->  
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":["weixin","tsina","tqf","copy","print"],"bdPic":"","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"7","bdPos":"right","bdTop":"100"},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
  </body>
</html>
