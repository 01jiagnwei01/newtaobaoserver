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

    <title><%=SystemGlobals.getPreference("story.title") %></title>

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
            <!-- 
            <li><a href="#about">童话</a></li>
             -->
          </ul>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
    </nav><!-- /.navbar -->

    <div class="container">

      <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-12 col-md-12  col-lg-12">
          
          <div class="jumbotron">
            <h1>咕咕道场</h1>
            <p>咕咕鸟给你讲好故事啦，把你想听的故事发qq告诉我们。</p>
          </div>
          <div class="row">
          	<c:forEach var="story"  items="${list}" >
			     <div class="col-xs-6 col-sm-4 col-md-4  col-lg-4">
	              <h2>${story.articleTitle }</h2>
	              <p>${story.storySummary }</p>
	              <p><a class="btn btn-default" href="<%=request.getContextPath() %>/story/${story.articleId }" target="_blank" role="button">去看看 &raquo;</a></p>
	            </div><!--/.col-xs-6 col-sm-4 col-md-3  col-lg-3-->
			</c:forEach>
          </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->
        
      </div><!--/row-->
      <hr/>
      <footer>
        <p>联系方式：QQ346745719</p>
        <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1254570389'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/stat.php%3Fid%3D1254570389%26show%3Dpic2' type='text/javascript'%3E%3C/script%3E"));</script>
      	<p>&copy; Company 2015 </p>
      </footer>

    </div><!--/.container-->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<%=request.getContextPath() %>/resources/newtaobao/ie10-viewport-bug-workaround.js"></script>
    <script src="<%=request.getContextPath() %>/resources/newtaobao/offcanvas.js"></script>
    <!-- appjs -->
    <script src="<%=request.getContextPath() %>/resources/siteappjs/appjs.js">
    </script>
  </body>
</html>
