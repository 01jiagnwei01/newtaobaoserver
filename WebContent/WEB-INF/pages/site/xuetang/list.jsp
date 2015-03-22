<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.*,com.gxkj.taobaoservice.enums.*,com.gxkj.taobaoservice.entitys.*,com.gxkj.common.util.SessionUtil,java.util.*"%>

<!DOCTYPE html>
<html>
<jsp:include page="../common/mina.jsp"></jsp:include>
<jsp:include page="../common/title.jsp" flush="true">
		<jsp:param name="titletype" value="bind_alipay"></jsp:param>
</jsp:include>
<jsp:include page="../common/css.jsp"></jsp:include>
<jsp:include page="../common/js.jsp"></jsp:include>
<style>
ul{padding:0px; margin:0px;}
li{padding:0px; margin:0px; list-style:none; float:left; margin-bottom:30px; width:300px; margin-right:100px; height:150px;}
h3{font-size:14px; color:#218CE1;}
h3 a{color:#218CE1;}
ul li a img{ opacity:0.5;}
ul li a img:hover{ opacity:1.0;}
</style>
<body>
<jsp:include page="../common/head.jsp" flush="true">
		<jsp:param name="showlogin" value="true"></jsp:param>
		<jsp:param name="showreg" value="false"></jsp:param>
	</jsp:include> 
	<jsp:include page="../common/subheader.jsp" flush="true">
		<jsp:param name="showshouye" value="true"></jsp:param>
	</jsp:include>  
	
	<div class="content">
	<div class="center" style="width:1200px; margin-bottom:50px;">
			<div style="height:50px; line-height:50px;">
				<a href="###">课堂</a>&nbsp;>&nbsp;<a href="###">课堂列条</a>
			</div>
            <ul>
            	<li>
                	<div style="width:298px; height:98px; border:solid #ccc 1px; background:url(<%=request.getContextPath() %>/resources/images/01.jpg) no-repeat center;">
                    	<a href="###" style="display:block; text-align:center; height:100px; width:300px; line-height:100px; position:relative;">
                        	<img src="<%=request.getContextPath() %>/resources/images/btn_play.png" style="position:absolute; left:50%; top:50%; margin-left:-25px; margin-top:-25px;" />
                        </a>
                    </div>
                    <div style="">
                    	<h3><a href="###" style="color:#218CE1;">标题标题标题标题标题标题标题标题</a></h3>
                    	<!-- 
                        <p>标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题</p>
                   		 -->
                   </div>
                </li>
            	<li>
                	<div style="width:298px; height:98px; border:solid #ccc 1px; background:url(<%=request.getContextPath() %>/resources/images/02.jpg) no-repeat center;">
                    	<a href="###" style="display:block; text-align:center; height:100px; width:300px; line-height:100px; position:relative;">
                        	<img src="<%=request.getContextPath() %>/resources/images/btn_play.png" style="position:absolute; left:50%; top:50%; margin-left:-25px; margin-top:-25px;" />
                        </a>
                    </div>
                    <div style="">
                    	<h3><a href="###" style="color:#218CE1;">标题标题标题标题标题标题标题标题</a></h3>
                        	<!-- 
                        <p>标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题</p>
                   		 -->
                   </div>
                </li>
            	<li>
                	<div style="width:298px; height:98px; border:solid #ccc 1px; background:url(<%=request.getContextPath() %>/resources/images/02.jpg) no-repeat center;">
                    	<a href="###" style="display:block; text-align:center; height:100px; width:300px; line-height:100px; position:relative;">
                        	<img src="<%=request.getContextPath() %>/resources/images/btn_play.png" style="position:absolute; left:50%; top:50%; margin-left:-25px; margin-top:-25px;" />
                        </a>
                    </div>
                    <div style="">
                    	<h3><a href="###" style="color:#218CE1;">标题标题标题标题标题标题标题标题</a></h3>
                        	<!-- 
                        <p>标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题</p>
                   		 -->
                   </div>
                </li>
            	<li>
                	<div style="width:298px; height:98px; border:solid #ccc 1px; background:url(<%=request.getContextPath() %>/resources/images/02.jpg) no-repeat center;">
                    	<a href="###" style="display:block; text-align:center; height:100px; width:300px; line-height:100px; position:relative;">
                        	<img src="<%=request.getContextPath() %>/resources/images/btn_play.png" style="position:absolute; left:50%; top:50%; margin-left:-25px; margin-top:-25px;" />
                        </a>
                    </div>
                    <div style="">
                    	<h3><a href="###" style="color:#218CE1;">标题标题标题标题标题标题标题标题</a></h3>
                        	<!-- 
                        <p>标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题</p>
                   		 -->
                   </div>
                </li>
            	<li>
                	<div style="width:298px; height:98px; border:solid #ccc 1px; background:url(<%=request.getContextPath() %>/resources/images/02.jpg) no-repeat center;">
                    	<a href="###" style="display:block; text-align:center; height:100px; width:300px; line-height:100px; position:relative;">
                        	<img src="<%=request.getContextPath() %>/resources/images/btn_play.png" style="position:absolute; left:50%; top:50%; margin-left:-25px; margin-top:-25px;" />
                        </a>
                    </div>
                    <div style="">
                    	<h3><a href="###" style="color:#218CE1;">标题标题标题标题标题标题标题标题</a></h3>
                        	<!-- 
                        <p>标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题</p>
                   		 -->
                   </div>
                </li>
            	<li>
                	<div style="width:298px; height:98px; border:solid #ccc 1px; background:url(<%=request.getContextPath() %>/resources/images/03.jpg) no-repeat center;">
                    	<a href="###" style="display:block; text-align:center; height:100px; width:300px; line-height:100px; position:relative;">
                        	<img src="<%=request.getContextPath() %>/resources/images/btn_play.png" style="position:absolute; left:50%; top:50%; margin-left:-25px; margin-top:-25px;" />
                        </a>
                    </div>
                    <div style="">
                    	<h3><a href="###" style="color:#218CE1;">标题标题标题标题标题标题标题标题</a></h3>
                        	<!-- 
                        	<p>标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题</p>
                  	 -->
                   </div>
                </li>
            	<li>
                	<div style="width:298px; height:98px; border:solid #ccc 1px; background:url(<%=request.getContextPath() %>/resources/images/02.jpg) no-repeat center;">
                    	<a href="###" style="display:block; text-align:center; height:100px; width:300px; line-height:100px; position:relative;">
                        	<img src="<%=request.getContextPath() %>/resources/images/btn_play.png" style="position:absolute; left:50%; top:50%; margin-left:-25px; margin-top:-25px;" />
                        </a>
                    </div>
                    <div style="">
                    	<h3><a href="###" style="color:#218CE1;">标题标题标题标题标题标题标题标题</a></h3>
                    	<!-- 
                        <p>标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题</p>
                  	 -->
                   </div>
                </li>
            	<li>
                	<div style="width:298px; height:98px; border:solid #ccc 1px; background:url(<%=request.getContextPath() %>/resources/images/03.jpg) no-repeat center;">
                    	<a href="###" style="display:block; text-align:center; height:100px; width:300px; line-height:100px; position:relative;">
                        	<img src="<%=request.getContextPath() %>/resources/images/btn_play.png" style="position:absolute; left:50%; top:50%; margin-left:-25px; margin-top:-25px;" />
                        </a>
                    </div>
                    <div style="">
                    	<h3><a href="###" style="color:#218CE1;">标题标题标题标题标题标题标题标题</a></h3>
                       <!--  <p>标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题</p>
                  		-->
                   </div>
                </li>
            	<li>
                	<div style="width:298px; height:98px; border:solid #ccc 1px; background:url(<%=request.getContextPath() %>/resources/images/01.jpg) no-repeat center;">
                    	<a href="###" style="display:block; text-align:center; height:100px; width:300px; line-height:100px; position:relative;">
                        	<img src="<%=request.getContextPath() %>/resources/images/btn_play.png" style="position:absolute; left:50%; top:50%; margin-left:-25px; margin-top:-25px;" />
                        </a>
                    </div>
                    <div style="">
                    	<h3><a href="###" style="color:#218CE1;">标题标题标题标题标题标题标题标题</a></h3>
                       <!--  <p>标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题</p>
                   	-->
                   </div>
                </li>
            </ul>
            <div style="width:100%; padding:20px 0; text-align:center;">分页</div>
		</div>
	</div>
 
 	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>