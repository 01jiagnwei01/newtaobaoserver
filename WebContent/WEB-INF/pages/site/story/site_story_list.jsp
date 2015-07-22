<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.gxkj.taobaoservice.entitys.*"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <!-- Bootstrap 不支持 IE 古老的兼容模式。为了让 IE 浏览器运行最新的渲染模式下，建议将此 <meta> 标签加入到你的页面中： -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>中华神话故事</title>

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
            <h1>XX道场，天天好故事</h1>
            <p>This is an example to show the potential of an offcanvas layout pattern in Bootstrap. Try some responsive-range viewport sizes to see it in action.</p>
          </div>
          <div class="row">
          	<%
          		List<StoryArticle> list = (List<StoryArticle>)request.getAttribute("list");
          		StoryArticle item = null;
          		for(int i=0,l=list.size();i<l;i++){
          			item = list.get(i);
          		}
          	
          	%>
            <div class="col-xs-6 col-sm-4 col-md-4  col-lg-4">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn btn-default" href="<%=request.getContextPath() %>/story/1" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6 col-sm-4 col-md-3  col-lg-3-->
             <div class="col-xs-6 col-sm-4 col-md-4  col-lg-4">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn btn-default" href="<%=request.getContextPath() %>/story/1" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6 col-sm-4 col-md-3  col-lg-3-->
             <div class="col-xs-6 col-sm-4 col-md-4  col-lg-4">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn btn-default" href="<%=request.getContextPath() %>/story/1" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6 col-sm-4 col-md-3  col-lg-3-->
             <div class="col-xs-6 col-sm-4 col-md-4  col-lg-4">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn btn-default" href="<%=request.getContextPath() %>/story/1" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6 col-sm-4 col-md-3  col-lg-3-->
             <div class="col-xs-6 col-sm-4 col-md-4  col-lg-4">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn btn-default" href="<%=request.getContextPath() %>/story/1" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6 col-sm-4 col-md-3  col-lg-3-->
             <div class="col-xs-6 col-sm-4 col-md-4  col-lg-4">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn btn-default" href="<%=request.getContextPath() %>/story/1" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6 col-sm-4 col-md-3  col-lg-3-->
          </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->

        
      </div><!--/row-->

      <hr>

      <footer>
        <p>&copy; Company 2015</p>
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
  </body>
</html>
