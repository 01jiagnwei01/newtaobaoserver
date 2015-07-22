<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
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
 <div class="jumbotron">
    <div class="container">
         
            <h1>精卫填海</h1>
            
			<p>太阳神的女儿精卫，变成了一只鸟，她每天都衔木填海1</p>
			<p>太阳神的女儿精卫，变成了一只鸟，她每天都衔木填海2</p>
			<p>太阳神的女儿精卫，变成了一只鸟，她每天都衔木填海3</p>
			
          
      <hr>
      
      <footer>
        <p>&copy; Company 2015</p>
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
    <script src="<%=request.getContextPath() %>/resources/newtaobao/offcanvas.js"></script>
  </body>
</html>
