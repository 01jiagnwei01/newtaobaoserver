<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.gxkj.taobaoservice.entitys.*,com.gxkj.common.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <!-- Bootstrap 不支持 IE 古老的兼容模式。为了让 IE 浏览器运行最新的渲染模式下，建议将此 <meta> 标签加入到你的页面中： -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
      <jsp:include page="./site_story_meta.jsp"></jsp:include>
    <link rel="icon" href="../../favicon.ico">
    <title><%=SystemGlobals.getPreference("story.title") %>-<c:out value="${title}" escapeXml="false" /></title>

    <!-- Bootstrap core CSS -->
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath() %>/resources/newtaobao/offcanvas.css" rel="stylesheet">
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="<%=request.getContextPath() %>/resources/newtaobao/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="<%=request.getContextPath() %>/resources/newtaobao/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    	.#navbar-inverse{
		  background-color: #99ffff;
		  border-color:#99ffff;
		}
		
		.#navbar-inverse .navbar-nav > .active > a, 
		.#navbar-inverse .navbar-nav > .active > a:focus, 
		.#navbar-inverse .navbar-nav > .active > a:hover
		{
			background-color:#99cc00;
		}
		html, body{
			background:#00CD66 none repeat scroll 0 0;
			color:#fff;
		}
		.jumbotron{
			background-color:#00CD66;
		}
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
    </style>
  </head>

  <body>
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
            <li class="active"><a href="#">神话</a></li>
            <li><a href="<%=request.getContextPath() %>/" >返回</a></li>
            <!-- 
            <li><a href="#about">童话</a></li>
             -->
          </ul>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
    </nav><!-- /.navbar -->
 <div class="jumbotron">
    <div class="container">
         
            <h1><c:out value="${story.articleTitle} "/></h1>
            <c:choose> 
				  <c:when test="${story == null}"> 
				  	<div class="col-xs-12 col-sm-12 col-md-12  col-lg-12">
				  		 <div class="row">
				  		 	 <div class="col-xs-6 col-sm-4 col-md-4  col-lg-4">
				              <h2>您要找的故事飘走了^^^</br></h2> 
				              <p><a class="btn btn-default" href="<%=request.getContextPath() %>/" role="button">去首页看看 &raquo;</a></p>
				            </div><!--/.col-xs-6 col-sm-4 col-md-3  col-lg-3-->
				  		 </div>
				  	</div>
				  </c:when> 
				  <c:otherwise>
				  		<div class="row"> 
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
									<dl class="oppose">
										<dt id="oppose_btn">踩</dt>
										<dd id="oppose_btn_txt">${story.tiresomeNumber}</dd>
									</dl>
									<dl class="support">
										<dt>浏览数</dt>
										<dd>${story.hitTimes}</dd>
									</dl>
							</div>
				       	 </div>
				  </c:otherwise> 
				</c:choose>
          
      <hr>
      
      <footer>
        <p>&copy; Company 2015 联系方式：QQ346745719</p>
      </footer>

    </div><!--/.container-->
</div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<%=request.getContextPath() %>/resources/newtaobao/ie10-viewport-bug-workaround.js"></script>
    <script src="<%=request.getContextPath() %>/resources/newtaobao/offcanvas.js"> </script>
    <script src="<%=request.getContextPath() %>/resources/siteappjs/appjs.js"></script>
    <script type="text/javascript">
    $(document).ready(function(){
    	<c:choose> 
  	  <c:when test="${story == null}"> 
  	  </c:when> 
  	  <c:otherwise>  
  	  addLookNum("<%=request.getContextPath() %>/story/look/${story.articleId}");
  	  $("#support_btn").click(function(){praiseOrTireFn("<%=request.getContextPath() %>","praise","${story.articleId}","support_btn_txt")});
  	  $("#oppose_btn").click(function(){ praiseOrTireFn("<%=request.getContextPath() %>","tire","${story.articleId}","oppose_btn_txt")});
  	  </c:otherwise> 
  	</c:choose>
    	 
    });
    </script>
 <!-- 百度分享 -->  

<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":["weixin","tsina","tqf","copy","print"],"bdPic":"","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"7","bdPos":"right","bdTop":"100"},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
 </body>
</html>
