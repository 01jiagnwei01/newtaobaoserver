<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
			<div style="height:50px; line-height:50px;">
				<a href="<%=request.getContextPath() %>">首页</a>&nbsp;>&nbsp;<a href="#">我的账户</a>&nbsp;>&nbsp;<a href="#">安全中心</a>
			</div>
			<div style="width:190px; margin-right:20px; background-color:#e1e1e1;" class="fl" >
				<div class="tac" style="color:#fff; font-size:20px; letter-spacing:10px; font-weight:bold; background-color:#025CA8; height:50px; line-height:50px;">我的账户</div>
				<ul>
					<li class="fs16">
						<a class="cbc" style="display:inline-block; width:100%; padding:15px 0;">
							<span style="margin-left:15px;">&nbsp;>&nbsp;任务中心</span>
						</a>
						<ul>
							<li><a href="<%=request.getContextPath() %>/site/bind/password" class="cbc" style="display:inline-block; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;任务大厅</span></a></li>
							<li><a href="<%=request.getContextPath() %>/useraccount" class="cbc" style="display:inline-block; background-color:#d3d3d3; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;发布新任务</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/bind/email" class="cbc" style="display:inline-block; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;我发布的任务</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/bind/email" class="cbc" style="display:inline-block; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;我未完成的任务</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/bind/email" class="cbc" style="display:inline-block; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;我已完成的任务</span></a></li>
						</ul>
					</li>
					<li class="fs16">
						<a  class="cbc" style="display:inline-block; width:100%; padding:15px 0;">
							<span style="margin-left:15px;">&nbsp;>&nbsp;财务中心</span>
						</a>
						<ul>
							<li><a href="<%=request.getContextPath() %>/site/money/chongzhi" class="cbc" style="display:inline-block; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;充值</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/money/chongzhi/topage" class="cbc" style="display:inline-block; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;充值记录</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/money/tixian" class="cbc" style="display:inline-block; background-color:#d3d3d3; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;提现</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/money/tixian/topage" class="cbc" style="display:inline-block; background-color:#d3d3d3; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;提现记录</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/bind/email" class="cbc" style="display:inline-block; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;购买发布点</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/bind/email" class="cbc" style="display:inline-block; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;资金记录</span></a></li>
						</ul>
					</li>
					<li class="fs16">
						<a  class="cbc" style="display:inline-block; background-color:#d3d3d3; width:100%; padding:15px 0;">
							<span style="margin-left:15px;">&nbsp;>&nbsp;安全中心</span>
						</a>
						<ul>
							<li><a href="<%=request.getContextPath() %>/site/bind/password" class="cbc" style="display:inline-block; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;修改密码</span></a></li>
							<li><a href="<%=request.getContextPath() %>/useraccount" class="cbc" style="display:inline-block; background-color:#d3d3d3; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;设置操作码</span></a></li>
							<li><a href="<%=request.getContextPath() %>/site/bind/email" class="cbc" style="display:inline-block; width:100%; padding:10px 0;"><span style="margin-left:30px;">&nbsp;>&nbsp;绑定邮箱</span></a></li>
						</ul>
					</li>
				</ul>
				
				<div style="clear:both;"></div>
				<div style="height: 100px;"></div>
			</div>