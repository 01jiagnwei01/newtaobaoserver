<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">

<jsp:include page="./common/mina.jsp"></jsp:include>
<jsp:include page="./common/title.jsp" flush="true">
		<jsp:param name="titletype" value="index"></jsp:param>
</jsp:include>
<jsp:include page="./common/css.jsp"></jsp:include>
<jsp:include page="./common/js.jsp"></jsp:include> 
<style type="text/css">
#kinMaxShow .KMSPrefix_kinMaxShow_button{left:50%; margin-left:-33px;}
 
.a_withlink1 :link,.a_withlink2 :link {
				text-decoration: none;
				color:#fff;
			}
.a_withlink1 :visited,.a_withlink2 :visited {
				text-decoration: none;
				color:#fffs;
			}
.a_withlink1 :hover,.a_withlink2 :hover {
				text-decoration: underline;
				color:#fff;
			}
.a_withlink1 :active,.a_withlink2 :active {
				text-decoration: none;
				color:#fff;
			}

</style>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/resources/js/jquery.kinMaxShow-1.0.min.js"></script>
<body>
	<jsp:include page="./common/head.jsp" flush="true">
		<jsp:param name="showlogin" value="true"></jsp:param>
		<jsp:param name="showreg" value="true"></jsp:param>
	</jsp:include>  
	<jsp:include page="./common/subheader.jsp" flush="true">
		<jsp:param name="showshouye" value="false"></jsp:param>
	</jsp:include>   
<!-- 顶部占位符-->
<div>
	<div id="kinMaxShow">
		<div>
			
			<img src="<%=request.getContextPath() %>/resources/images/01.jpg" />
		</div>
		<div>
			<img src="<%=request.getContextPath() %>/resources/images/02.jpg" />
		</div>
		<div>
			<img src="<%=request.getContextPath() %>/resources/images/03.jpg" />
		</div>
	</div>
</div>
<!-- 底部占位符-->

	<div class="center" style="width:1200px; height:250px;;margin-bottom:550px;">
		<div class="fl tac" style="width:1200px; height:250px; background-color:#ddd;">
			<ul style="margin-top:20px;">
				<li class="fl" style="width:220px; margin-left:135px; background:url(<%=request.getContextPath() %>/resources/images/anquan.gif) no-repeat center 40px;">
					<div style="font-size:18px; margin-bottom:150px;">安全</div>
					<div>投资有收益<br />安全有保障</div>
				</li>
				<li class="fl" style="width:220px; margin:0 135px; background:url(<%=request.getContextPath() %>/resources/images/zhuanye.gif) no-repeat center 40px;">
					<div style="font-size:18px; margin-bottom:150px;">专业</div>
					<div>专业的运营团队<br />保证你的投资收益</div>
				</li>
				<li class="fl" style="width:220px; background:url(<%=request.getContextPath() %>/resources/images/wending.gif) no-repeat center 40px;">
					<div style="font-size:18px; margin-bottom:150px;">稳定</div>
					<div>谷谷道场总能让<br />你的收益稳定增长</div>
				</li>
			</ul>
		</div>
		 
        <div class="fl" style="width:1198px; border:1px solid #ccc; background:url(<%=request.getContextPath() %>/resources/images/bg.gif) no-repeat center 40px #fff; height:370px; position:relative;">
	        <DIV style="position:absolute; width:290px; text-align:center; left:50px; top:30px;">
    	    	<span style="color:#218CE1; font-size:16px;">发布方平台存款转化成网店收款</span>
                <div style="width:288px; text-align:left; margin-top:15px; border:solid 1px #ccc;">
                	<SPAn style="background:url(<%=request.getContextPath() %>/resources/images/icon1.gif) no-repeat 0 30px; display:block; padding-left:30px; margin-left:30px; padding-top:30px;">发布网店任务</SPAn>
                	<SPAn style="background:url(<%=request.getContextPath() %>/resources/images/icon2.gif) no-repeat 0 30px; display:block; padding-left:30px; margin-left:30px; padding-top:30px;">获得好生意</SPAn>
                	<SPAn style="background:url(<%=request.getContextPath() %>/resources/images/icon3.gif) no-repeat 0 30px; display:block; padding-left:30px; padding-bottom:30px; margin-left:30px; padding-top:30px;">悬赏佣金,获得好关系</SPAn>
                </div>
                <SPAn  id="a_withlink1" class="a_withlink1" style="background:#218CE1; display:block; height:30px; line-height:30px; color:#fff;">
                	<a  href="<%=request.getContextPath() %>/site/order/create" >发布网店任务</a>
                </SPAn>
                <SPAn style="background:#125489; display:block; height:5px;"></SPAn>
            </DIV>
	        <DIV style="position:absolute; width:290px; text-align:center; right:50px; top:30px;">
    	    	<span style="color:#218CE1; font-size:16px;">接手方网店付款转化为平台收款</span>
                <div style="width:288px; text-align:left; margin-top:15px; border:solid 1px #ccc;">
                	<SPAn style="background:url(<%=request.getContextPath() %>/resources/images/icon4.gif) no-repeat 0 30px; display:block; padding-left:30px; margin-left:30px; padding-top:30px;">参与任务</SPAn>
                	<SPAn style="background:url(<%=request.getContextPath() %>/resources/images/icon5.gif) no-repeat 0 30px; display:block; padding-left:30px; margin-left:30px; padding-top:30px;">按规定完成</SPAn>
                	<SPAn style="background:url(<%=request.getContextPath() %>/resources/images/icon6.gif) no-repeat 0 30px; display:block; padding-left:30px; padding-bottom:30px; margin-left:30px; padding-top:30px;">获得高佣金</SPAn>
                </div>
                <SPAn id="a_withlink2" class="a_withlink2" style="background:#00A89C; display:block; height:30px; line-height:30px; color:#fff;">
                	<a href="<%=request.getContextPath() %>/site/task/task_center" >接任务获得高额佣金</a></SPAn>
                <SPAn style="background:#003E39; display:block; height:5px;"></SPAn>
            </DIV>
            <div style="width:50%; height:70px; text-align:center; line-height:70px; bottom:0px; color:#fff; background:#218CE1; left:0px; position:absolute;">
            	<div style="background:url(<%=request.getContextPath() %>/resources/images/bg1.png) no-repeat 0 center; width:200px; margin:0 auto; text-align:left; padding-left:60px;" class="a_withlink2">
            		<a  href="<%=request.getContextPath() %>/site/order/create"  style="color:#fff;">发布精准营销任务</a></div>
            </div>
            <div style="width:50%; height:70px; text-align:center; line-height:70px; bottom:0px; color:#fff; background:#00A89C; right:0px; position:absolute;">
            	<div style="background:url(<%=request.getContextPath() %>/resources/images/bg0.gif) no-repeat 0 center; width:200px; margin:0 auto; text-align:left; padding-left:60px;">
            		<a href="<%=request.getContextPath() %>/site/task/task_center"   style="color:#fff;">做任务,勤奋赚佣金</a></div>
            </div>
        
        </div>

	</div>
	<jsp:include page="./common/footer.jsp"></jsp:include>

</body>
<script type="text/javascript">
$(function(){
	(function($){
		$('.tabNav li').click(function(){
			$(this).addClass('current').siblings().removeClass('current');
			var index = $(this).index();
			$('.tabContent>div').eq(index).show().siblings().hide();
		});
	})(jQuery);
	
	$("#kinMaxShow").kinMaxShow({
	    height: 371,
	    intervalTime:1,
	    hoverPause:true
	});
	if ($('.index_propaganda .wrap').css('width') == '1188px') {
	    
	    $('.PPD_header_nav').find('.w1000center').css('width', 1188);
	}
});
</script>
</html>