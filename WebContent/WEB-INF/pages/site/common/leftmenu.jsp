<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.*,com.gxkj.taobaoservice.enums.*,com.gxkj.taobaoservice.entitys.*,com.gxkj.common.util.SessionUtil,java.util.*"%>

<%
String mainmenu = request.getParameter("mainmenu"); 
String submenu = request.getParameter("submenu");
%>
			<div style="width:190px; margin-right:20px; background-color:#e1e1e1;" class="fl" >
				<div class="tac" style="color:#fff; font-size:20px; letter-spacing:10px; font-weight:bold; background-color:#025CA8; height:50px; line-height:50px;">我的账户</div>
				<ul>
					<li class="fs16">
						<a class="cbc" style=" <% if("task_center".equals(mainmenu)) {%> background-color:#d3d3d3; <% }%> display:inline-block; width:100%; padding:15px 0;">
							<span style="margin-left:15px;">&nbsp;>&nbsp;任务中心</span>
						</a>
						<ul>
							<li><a href="<%=request.getContextPath() %>/site/task/task_center" class="cbc" style="<%if("task_center".equals(submenu)) {%> background-color:#d3d3d3; <% }%>display:inline-block; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;任务大厅</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/order/create" class="cbc" style="<%if("order_create".equals(submenu)) {%> background-color:#d3d3d3; <% }%>display:inline-block; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;发布新任务</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/order/myorderpage" class="cbc" style="<%if("my_order_page".equals(submenu)) {%> background-color:#d3d3d3; <% }%>display:inline-block;  width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;我的订单列表</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/task/mypublishlist" class="cbc" style="<%if("task_mypublish".equals(submenu)) {%> background-color:#d3d3d3; <% }%>display:inline-block; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;我发布的任务</span></a></li>
							
							<li><a href="<%=request.getContextPath() %>/site/task/myuncompletetasklist" class="cbc" style="<%if("task_myuncompletetasklist".equals(submenu)) {%> background-color:#d3d3d3; <% }%>display:inline-block; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;我未完成的任务</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/task/mycompletelist" class="cbc" style="<%if("task_mycompletetasklist".equals(submenu)) {%> background-color:#d3d3d3; <% }%>display:inline-block; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;我已完成的任务</span></a></li>
						</ul>
					</li>
					<li class="fs16">
						<a  class="cbc" style="<%if("caiwu".equals(mainmenu)) {%> background-color:#d3d3d3; <% }%> display:inline-block; width:100%; padding:15px 0;">
							<span style="margin-left:15px;">&nbsp;>&nbsp;财务中心</span>
						</a>
						<ul>
							<li><a href="<%=request.getContextPath() %>/site/money/chongzhi" class="cbc" style="<%if("chongzhi".equals(submenu)) {%> background-color:#d3d3d3; <% }%>display:inline-block; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;充值</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/money/chongzhi/recordpage" class="cbc" style="<%if("chongzhijilu".equals(submenu)) {%> background-color:#d3d3d3; <% }%>display:inline-block; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;充值记录</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/money/tixian" class="cbc" style="<%if("tixian".equals(submenu)) {%> background-color:#d3d3d3; <% }%>display:inline-block;  width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;提现</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/money/tixian/recordpage" class="cbc" style="<%if("tixian_recordpage".equals(submenu)) {%> background-color:#d3d3d3; <% }%>display:inline-block; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;提现记录</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/useraccount/log" class="cbc" style="<%if("useraccount_log".equals(submenu)) {%>background-color:#d3d3d3; <% }%>display:inline-block; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;资金记录</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/products/pointcard" class="cbc" style="<%if("point_card".equals(submenu)) {%>background-color:#d3d3d3; <% }%>display:inline-block; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;购买点卡</span></a></li>
						</ul>
					</li>
					<li class="fs16">
						<a  class="cbc" style="display:inline-block; <%if("safe".equals(mainmenu)) {%> background-color:#d3d3d3; <% }%> width:100%; padding:15px 0;">
							<span style="margin-left:15px;">&nbsp;>&nbsp;安全中心</span>
						</a>
						<ul>
							<li><a href="<%=request.getContextPath() %>/site/bind/password" class="cbc" style="display:inline-block;  <%if("bind_password".equals(submenu)) {%> background-color:#d3d3d3; <% }%> width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;修改密码</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/bind/caozuoma" class="cbc" style="display:inline-block; <%if("bind_caozuoma".equals(submenu)) {%> background-color:#d3d3d3; <% }%> width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;设置操作码</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/bind/email" class="cbc" style="display:inline-block; <%if("bind_email".equals(submenu)) {%> background-color:#d3d3d3; <% }%> width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;绑定邮箱</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/bind/qq" class="cbc" style="display:inline-block; <%if("bind_qq".equals(submenu)) {%> background-color:#d3d3d3; <% }%>  width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;绑定QQ</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/bind/alipay" class="cbc" style="display:inline-block; <%if("bind_alipay".equals(submenu)) {%> background-color:#d3d3d3; <% }%>  width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;绑定淘宝小号</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/bind/tel" class="cbc" style="display:inline-block;  <%if("bind_tel".equals(submenu)) {%> background-color:#d3d3d3; <% }%> width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;绑定手机号</span></a></li>
						</ul>
					</li>
				</ul>
				
				<div style="clear:both;"></div>
			</div>