<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<div style="width:100%; background-color:#999; position:fixed; left:0; bottom:0; z-index:9999;">
	<div class="center tac" style="width:1200px; height:140px; color:#FFF;">
    	<div style="padding:20px 0;">
		<span style="background:url(<%=request.getContextPath() %>/resources/images/icon_qq.png) no-repeat; padding-left:25px; display:inline-block; height:25px; line-height:25px;">
			  客服QQ：346745719
        </span>
		<span style="display:inline-block; height:25px; padding:0 30px; line-height:25px;">
                	<a target="_blank" href="http://shang.qq.com/wpa/qunwpa?idkey=fa27f65e73a48bd593a36921de2bc5e09c7ad68e7d6cc99b036b38d157031e2d">
            <img src="<%=request.getContextPath() %>/resources/images/group.png" height="25" />
        	</a>

        </span>
        <span style="background:url(<%=request.getContextPath() %>/resources/images/icon_time.png) no-repeat; padding-left:30px; display:inline-block; height:25px; line-height:25px; padding-right:30px;">
			客服时间：9:00 — 18:00
        </span>
        </div>
		<div class="tac">Copyright Reserved 2015 © 谷谷道场（www.gugudaochang.com）</div>
		<div style="margin:20px 0;">
		<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1254570389'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/stat.php%3Fid%3D1254570389%26show%3Dpic2' type='text/javascript'%3E%3C/script%3E"));</script>
		</div>
	</div>
</div>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/resources/js/jquery.Sonline.js"></script>
<script>
$(function(){
	$("body").Sonline({
		Position:"right",//left或right
		Top:200,//顶部距离，默认200px
		Effect:true, //滚动或者固定两种方式，布尔值：true或false
		DefaultsOpen:true, //默认展开：true,默认收缩：false
		Qqlist:"346745719|客服01,408931602|客服02" //多个QQ用','隔开，QQ和客服名用'|'隔开
	});
})
</script>