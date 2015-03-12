<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals,com.gxkj.taobaoservice.util.*"%>
 <%@ page import="static com.gxkj.taobaoservice.util.SystemDbData.subTaskInfoMap, com.gxkj.common.exceptions.*"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>任务确认</title>
<jsp:include page="../common/css.jsp"></jsp:include>
<jsp:include page="../common/bookstrap.jsp"></jsp:include> 
<style type="text/css">
.cbc:hover{background-color:#D3D3D3;}
table td{padding:5px; height:25px; font-size:14px;}
 
.zengzhiinputwidth {width: 250px;}
 
#kinMaxShow .KMSPrefix_kinMaxShow_button{left:50%; margin-left:-33px;}

.inputwidth {width:360px;height:50px;line-height: 50px;}
</style>
 
<body>
	<jsp:include page="../common/head.jsp" flush="true">
		<jsp:param name="showlogin" value="true"></jsp:param>
		<jsp:param name="showreg" value="false"></jsp:param>
	</jsp:include> 
	<jsp:include page="../common/subheader.jsp" flush="true">
		<jsp:param name="showshouye" value="true"></jsp:param>
	</jsp:include>
	
	<div style="width:100%; background-color:#ededed; padding-bottom:135px;">

		<div class="center" style="width:1200px;">

			<jsp:include page="../common/leftmenu.jsp" flush="true">
				<jsp:param name="mainmenu" value="safe"></jsp:param>
				<jsp:param name="submenu" value="czm"></jsp:param>
			</jsp:include>

			 
			<div style="width:910px; padding:0 10px; background-color:#FFF;" class="fr">
				 	<form id="submitForm" method="post" action="<%=request.getContextPath() %>/site/order/sure">
					<table border="0" cellpadding="5" cellspacing="0" style="margin:20px 0;">
					<c:choose>
								<c:when test="${empty error }">
									<tr  >
										<td colspan="3"  align="center"><b>请确认您的订单</b></td>
									</tr>
								</c:when>
								<c:otherwise>
									
									 <tr  >
										<td colspan="3"  align="center"> <div class="form-group has-error">错误：
											<c:choose>
												<c:when test="${'账户点数不足' eq  error.message }">
												${error.message },<a href="<%=request.getContextPath() %>/site/products/pointcard">去购买点数</a>
												</c:when>
												<c:otherwise></c:otherwise>
											</c:choose>
										</div> </td>
									</tr>
								</c:otherwise>
								
							</c:choose>
							
							<tr style="display: none" >
									<td colspan="3"  align="center">
										<input type="hidden" name="orderId" value="${order.id }">
										<input type="hidden" name="goodCommentTimeLimit" value="${goodCommentTimeLimit}">
										<input type="hidden" name="goodCommentContent" value="${goodCommentContent }">
									</td>
							</tr>
							<tr>
									<td width="150" align="right">商家淘宝小号</td>
									<td width="260"><c:out escapeXml="true" value="${order.taobaoXiaohao}"></c:out></td>
									<td align="left"></td>
							</tr>
							<tr>
									<td align="right">商家QQ</td>
									<td><c:out escapeXml="true" value="${order.userQq}"></c:out></td>
									<td></td>
							</tr>
							<tr>
									<td align="right">商品标题</td>
									<td><c:out escapeXml="true" value="${order.productTitle}"></c:out></td>
									<td>&nbsp;</td>
							</tr>
							<tr>
									<td align="right">商品网址</td>
									<td><c:out escapeXml="true" value="${order.productLink}"></c:out></td>
									<td>&nbsp;</td>
							</tr>
							
							<tr>
									<td align="right">担保价格</td>
									<td>${order.guaranteePrice}</td>
									<td>担保价格 = 你淘宝的宝贝价格(或改价后价格) + 邮费的总价 <a href="<%=request.getContextPath() %>/site/money/chongzhi" target="_blank"  class="easyui-linkbutton"  >我要充值</a></td>
							</tr>
							<tr>
									<td align="right">基本佣金</td>
									<td>${order.basicReceiverGainMoney}</td>
									<td>佣金标准：100元以下的5元（包含100元）;100-200元的8元（包含200元）;200-500元的15元（包含500元）;500元以上20元</td>
							</tr>
							<tr>
									<td align="right">奖励接手人</td>
									<td>${order.encourage}</td>
									<td>&nbsp;</td>
							</tr>
							<tr>
									<td align="right">支付平台发布点</td>
									<td>0.5</td>
									<td>&nbsp;</td>
							</tr>
							<tr align="right">
									<td colspan="3" align="center" bgcolor="#09f" style="color:#fff;"><b>以下为基本任务区 </b></td>
							</tr>
							<tr>
									<td align="right">好评时限要求</td>
									<td>
									<c:choose>
											<c:when test="${'IMMEDIATELY' eq GOOD_COMMENT_TIME_LIMIT.inputValue  }">立刻好评</c:when>
											<c:when test="${'THIRTYMMinuteLater' eq GOOD_COMMENT_TIME_LIMIT.inputValue  }">30分钟后好评</c:when>
											<c:when test="${'ONE_DAY_LATER' eq GOOD_COMMENT_TIME_LIMIT.inputValue  }">1天后好评</c:when>
											<c:when test="${'TWO_DAY_LATER' eq GOOD_COMMENT_TIME_LIMIT.inputValue  }">2天后好评</c:when>
											<c:when test="${'THREE_DAY_LATER' eq GOOD_COMMENT_TIME_LIMIT.inputValue  }">3天后好评</c:when>
											<c:when test="${'FOURE_DAY_LATER' eq GOOD_COMMENT_TIME_LIMIT.inputValue  }">4天后好评</c:when>
											<c:when test="${'FIVE_DAY_LATER' eq GOOD_COMMENT_TIME_LIMIT.inputValue  }">5天后好评</c:when>
											<c:when test="${'SIX_DAY_LATER' eq GOOD_COMMENT_TIME_LIMIT.inputValue  }">6天后好评</c:when>
											<c:when test="${'SEVEN_DAY_LATER' eq GOOD_COMMENT_TIME_LIMIT.inputValue  }">7天后好评</c:when>
										</c:choose>
									</td>
									<td>&nbsp;</td>
							</tr>
							<tr>
									<td align="right">指定好评内容</td>
									<td><c:out escapeXml="true" value="${goodCommentContent}"></c:out></td>
									<td>&nbsp;</td>
							</tr>
							<tr>
									<td align="right">商铺动态评分</td>
									<td> <b>掌柜店铺动态评分，请全给对方打5分</b></td>
									<td>&nbsp;</td>
							</tr>
							<tr align="right">
									<td colspan="3" align="center" bgcolor="#09f" style="color:#fff;"><b>以下为增值服务区，选择勾选</b></td>
							</tr>
							<tr>
									<td align="right">需要旺旺聊天</td>
									<td><c:choose>
												<c:when test="${empty NEED_WANGWANG_TALK or '0' eq NEED_WANGWANG_TALK }">不需要</c:when>
												<c:otherwise>需要</c:otherwise> 
											</c:choose></td>
									<td>奖励接手方<%=subTaskInfoMap.get("NEED_WANGWANG_TALK").getAmount() %>个发布点</td>
							</tr>
							<tr>
									<td align="right">限制重复接任务</td>
									<td><c:choose>
												<c:when test="${empty NO_REPEAT_TASK  or '0' eq NO_REPEAT_TASK}">不需要</c:when>
												<c:otherwise>需要</c:otherwise>
											</c:choose></td>
									<td>奖励接手方<%=subTaskInfoMap.get("NO_REPEAT_TASK").getAmount() %>个发布点</td>
							</tr>
							<tr>
									<td align="right">指定接手人</td>
									<td><c:choose>
												<c:when test="${ empty ZHI_DING_JIE_SHOU_REN   or '0' eq ZHI_DING_JIE_SHOU_REN}">不需要</c:when>
												<c:otherwise>需要 接手人ID：${ZHI_DING_JIE_SHOU_REN_ID }</c:otherwise>
											</c:choose> 
										
									</td>
									<td>支付平台<%=subTaskInfoMap.get("ZHI_DING_JIE_SHOU_REN").getAmount() %>个发布点</td>
							</tr>
							
							<tr>
									<td align="right">指定收货地址</td>
									<td>
									<c:choose>
												<c:when test="${ empty ZHI_DING_SHOU_HUO_DI_ZHI   or '0' eq ZHI_DING_SHOU_HUO_DI_ZHI   }">不需要</c:when>
												<c:otherwise>需要 &nbsp;<c:out escapeXml="true" value="${ZHI_DING_SHOU_HUO_DI_ZHI_ADDRESS}"></c:out></c:otherwise>
											</c:choose>
									</td>
									<td>奖励接手方<%=subTaskInfoMap.get("ZHI_DING_SHOU_HUO_DI_ZHI").getAmount() %>个发布点</td>
							</tr>
							
							 <tr>
									<td align="right">批量发布</td>
									<td><c:choose>
												<c:when test="${ empty PI_LIANG_FA_BU   or '0' eq PI_LIANG_FA_BU   }">不需要</c:when>
												<c:otherwise>需要 ${PI_LIANG_FA_BU_input }条 </c:otherwise>
											</c:choose>
									</td>
									<td>批量发布，上限50条,需要支付平台<%=subTaskInfoMap.get("PI_LIANG_FA_BU").getAmount() %>个发布点</td>
							</tr>
							<tr>
									<td align="right">统计</td>
									<td align="center">
										需要金额：${order.countPayMoney}，需要发布点：${order.countPayPoints}
									</td>
									 
							</tr>
							<c:choose>
								<c:when test="${empty error }">
									<tr>
										<td colspan="3" align="center">
											<button class="btn btn-lg btn-success"  onclick="sure(this)">确认订单</button>
											<button class="btn btn-default" type="button" onclick="back(this)">修改</button>
										</td>
									</tr>
								</c:when>
								<c:when test="${error.siteFlag eq 'points' }">
									<tr>
										<td colspan="3" align="center">
											<button class="btn btn-default" type="button" onclick="goBuyPoint(this)">去买点</button>
										</td>
									</tr>
								</c:when>
								<c:when test="${error.siteFlag eq 'lockAmount' }">
									<tr>
										<td colspan="3" align="center">
											<button class="btn btn-default" type="button" onclick="goChongzhi(this)">去充值</button>
										</td>
						 			</tr>
								</c:when>
								<c:when test="${error.siteFlag eq 'status' }">
									<tr>
										<td colspan="3" align="center">
											订单状态：${ order.status.name}
										</td>
						 			</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="3" align="center">
											<button class="btn btn-default" type="button" onclick="back(this)">修改</button>
										</td>
									</tr>
								</c:otherwise>
								
							</c:choose>
							
					</table>
					 </form>
			</div>

		</div>
		<div style="clear:both;"></div>
	</div>
	<div style="clear:both;"></div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(function(){ 
	})
	function sure(btn){
		$(btn).attr("disabled",true); 
		var path = "<%=request.getContextPath() %>/site/order/sure";
		 $('#submitForm').attr("action", path).submit();
	}
	function back(btn){
		$(btn).attr("disabled",true); 
		var path = "<%=request.getContextPath() %>/site/order/back";
		 $('#submitForm').attr("action", path).submit();
	}
	function goBuyPoint(btn){
		$(btn).attr("disabled",true); 
		 $("#submitForm").attr("method","GET");
		var path = "<%=request.getContextPath() %>/site/products/pointcard";
		 $('#submitForm').attr("action", path).submit();
	}
	function goChongzhi(btn){
		$(btn).attr("disabled",true); 
		$("#submitForm").attr("method","GET");
		var path = "<%=request.getContextPath() %>/site/money/chongzhi";
		 $('#submitForm').attr("action", path).submit();;
	}
</script>

</html> 