<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.gxkj.common.util.SystemGlobals,com.gxkj.taobaoservice.util.*"%>
<%@ page import="static com.gxkj.taobaoservice.util.SystemDbData.subTaskInfoMap, com.gxkj.common.exceptions.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<jsp:include page="../common/mina.jsp"></jsp:include>
<c:choose>
	<c:when test="${'order_sure' eq  tag }"><title>订单确认</title></c:when>
	<c:when test="${'order_detail' eq  tag }"><title>订单详情页</title></c:when>
</c:choose>

<jsp:include page="../common/css.jsp"></jsp:include>
<jsp:include page="../common/bookstrap.jsp"></jsp:include> 
<style type="text/css">
.cbc:hover{background-color:#D3D3D3;}
table td{padding:10px 5px; height:25px; font-size:14px;}
.zengzhiinputwidth{width:250px;}
#kinMaxShow .KMSPrefix_kinMaxShow_button{left:50%; margin-left:-33px;}
.inputwidth{width:360px; height:50px; line-height:50px;}
.bb{border-bottom:1px solid #eee;}
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
			
			<div style="height:50px; line-height:50px;">
				<c:choose>
					<c:when test="${'order_sure' eq  tag }"><a href="###">任务中心</a>&nbsp;>&nbsp;<a href="###">发布新任务</a></c:when>
					<c:when test="${'order_detail' eq  tag }"><a href="###">任务中心</a>&nbsp;>&nbsp;<a href="###">任务详情</a></c:when>
				</c:choose>
			</div>

			<div style="overflow:hidden;">

				<div style="margin-bottom:-5000px; padding-bottom:5000px;">
					<jsp:include page="../common/leftmenu.jsp" flush="true">
						<jsp:param name="mainmenu" value="safe"></jsp:param>
						<jsp:param name="submenu" value="czm"></jsp:param>
					</jsp:include>
                </div>
			 
				<div style="width:990px; padding:0 40px; background-color:#FFF; padding-bottom:100px;margin-bottom:-5000px; padding-bottom:5000px;" class="fr">
				 	<form id="submitForm" method="post" action="<%=request.getContextPath() %>/site/order/sure">
				 		<c:choose>
										<c:when test="${order.status eq 'CANCEL'}"></c:when>
										<c:otherwise>
											
				 						</c:otherwise>
						</c:choose>
						<c:if test="${not empty  error}">
					 							<h2 style="font-size:14px; font-weight:bold;"><span style="color:#f00;">错误</span>：${error.message }。</h2>	
					 						</c:if>
						<table border="0" cellpadding="5" cellspacing="0" style="margin:20px 0;">	 
							<tr style="display: none" >
								<td colspan="2"  align="center">
									<input type="hidden" name="orderId" value="${order.id }">
									<input type="hidden" name="goodCommentTimeLimit" value="${goodCommentTimeLimit}">
									<input type="hidden" name="goodCommentContent" value="${goodCommentContent }">
								</td>
							</tr>
							<tr>
								<td width="150" align="right" class="bb">订单编号：</td>
								<td class="bb"><c:out escapeXml="true" value="${order.id }"></c:out></td>
							</tr>
							<tr>
								<td width="150" align="right" class="bb">商家淘宝小号：</td>
								<td class="bb"><c:out escapeXml="true" value="${order.taobaoXiaohao }"></c:out></td>
							</tr>
							<tr>
								<td align="right" class="bb">商家QQ：</td>
								<td class="bb"><c:out escapeXml="true" value="${order.userQq }"></c:out></td>
							</tr>
							<tr>
								<td class="bb" align="right">商品标题：</td>
								<td class="bb"><c:out escapeXml="true" value="${order.productTitle }"></c:out></td>
							</tr>
							<tr>
								<td class="bb" align="right">商品网址：</td>
								<td class="bb"><c:out escapeXml="true" value="${order.productLink }"></c:out></td>
							</tr>
							<tr>
								<td class="bb" align="right" valign="top">担保价格：</td>
								<td class="bb">${order.guaranteePrice}<br/>
                                <span style="font-size:12px; color:#999;">担保价格 = 你淘宝的宝贝价格(或改价后价格) + 邮费的总价 <a href="<%=request.getContextPath() %>/site/money/chongzhi" target="_blank" class="easyui-linkbutton" style="color:#36F;">我要充值</a></span></td>
							</tr>
							<tr>
								<td class="bb" align="right" valign="top">佣金：</td>
								<td class="bb">${order.commission}<br/>
                                <span style="font-size:12px; color:#999;">接任务的人每单获利。</span></td>
							</tr>
							
							<tr>
								<td align="right">支付平台发布点：</td>
								<td>0.5</td>
							</tr>
							<tr align="right">
								<td colspan="2" align="center" bgcolor="#09f" style="color:#fff;"><b>以下为基本任务区</b></td>
							</tr>
							<tr>
								<td class="bb" align="right">好评时限要求：</td>
								<td class="bb">
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
							</tr>
							<tr>
								<td class="bb" align="right">指定好评内容：</td>
								<td class="bb"><c:out escapeXml="true" value="${GOOD_COMMENT_CONTENT.inputValue}"></c:out></td>
							</tr>
							<tr>
								<td align="right">商铺动态评分：</td>
								<td><b>掌柜店铺动态评分，请全给对方打5分</b></td>
							</tr>
							<tr align="right">
								<td colspan="2" align="center" bgcolor="#09f" style="color:#fff;"><b>以下为增值服务区，选择勾选</b></td>
							</tr>
							<tr>
								<td align="right" valign="top" class="bb">需要旺旺聊天：</td>
								<td class="bb">
                                	<c:choose>
										<c:when test="${empty NEED_WANGWANG_TALK or '0' eq NEED_WANGWANG_TALK   }">不需要</c:when>
										<c:otherwise>需要</c:otherwise> 
									</c:choose><br/>
                                    <span style="font-size:12px; color:#999;">奖励接手方<%=subTaskInfoMap.get("NEED_WANGWANG_TALK").getAmount() %>个发布点</span>
								</td>
							</tr>
							<tr>
								<td align="right" valign="top" class="bb">限制重复接任务：</td>
								<td class="bb">
                                	<c:choose>
										<c:when test="${empty NO_REPEAT_TASK or '0' eq NO_REPEAT_TASK  }">不需要</c:when>
										<c:otherwise>需要</c:otherwise>
									</c:choose><br/>
                                    <span style="font-size:12px; color:#999;">支付平台<%=subTaskInfoMap.get("NO_REPEAT_TASK").getAmount() %>个发布点</span>
								</td>
							</tr>
							<tr>
								<td align="right" valign="top" class="bb">指定接手人：</td>
								<td class="bb">
                                	<c:choose>
										<c:when test="${ empty ZHI_DING_JIE_SHOU_REN   or '0' eq ZHI_DING_JIE_SHOU_REN}">不需要</c:when>
										<c:otherwise>需要 接手人ID：${ZHI_DING_JIE_SHOU_REN.inputValue }</c:otherwise>
									</c:choose><br/>
                                    <span style="font-size:12px; color:#999;">支付平台<%=subTaskInfoMap.get("ZHI_DING_JIE_SHOU_REN").getAmount() %>个发布点</span>
								</td>
							</tr>
							<tr>
								<td align="right" valign="top" class="bb">指定收货地址：</td>
								<td class="bb">
									<c:choose>
										<c:when test="${empty ZHI_DING_SHOU_HUO_DI_ZHI or '0' eq ZHI_DING_SHOU_HUO_DI_ZHI}">不需要</c:when>
										<c:otherwise>需要 &nbsp;
												<c:if test="${not empty  ZHI_DING_SHOU_HUO_DI_ZHI.inputValue}"> <c:out escapeXml="true" value="${ZHI_DING_SHOU_HUO_DI_ZHI.inputValue}"></c:out></c:if>
										</c:otherwise>		
									</c:choose><br/>
                                    <span style="font-size:12px; color:#999;">奖励接手方<%=subTaskInfoMap.get("ZHI_DING_SHOU_HUO_DI_ZHI").getAmount() %>个发布点</span>
								</td>
							</tr>
							<tr>
								<td align="right" valign="top" class="bb">批量发布：</td>
								<td class="bb">
                                	<c:choose>
										<c:when test="${ empty PI_LIANG_FA_BU or  '0' eq PI_LIANG_FA_BU    }">不需要</c:when>
										<c:otherwise>需要  <c:choose>
													<c:when test="${empty  PI_LIANG_FA_BU.inputValue}"></c:when>
													<c:otherwise> <c:out escapeXml="true" value="${PI_LIANG_FA_BU.inputValue}"></c:out>条</c:otherwise>
												</c:choose>
										 </c:otherwise>
									</c:choose><br/>
                                    <span style="font-size:12px; color:#999;">批量发布，上限50条,需要支付平台<%=subTaskInfoMap.get("PI_LIANG_FA_BU").getAmount() %>个发布点</span>
								</td>
							</tr>
							<tr>
								<td class="bb" align="right">统计：</td>
								<td class="bb">
									<strong>需要金额：${order.countPayMoney}，需要发布点：${order.countPayPoints}</strong>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<c:choose>
										<c:when test="${order.status eq 'WAIT_FOR_SURE'}">
											<button class="btn btn-lg btn-success" onclick="sure(this)" style="font-size:14px; padding:6px 12px; border-radius:4px;">确认订单</button>
											<button class="btn btn-default" type="button" onclick="update(this)">修改</button>
											<button class="btn btn-default" type="button" onclick="docancle(this)">取消订单</button>
										 </c:when>
										<c:when test="${order.status eq 'SURE'}"></c:when>
										<c:when test="${order.status eq 'CANCEL'}">该订单已取消</c:when>
										<c:otherwise>需要 ${PI_LIANG_FA_BU.inputValue} 条</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</table>
					</form>
				</div>

			</div>

			<div style="clear:both;"></div>

		</div>
        
		<div style="clear:both;"></div>
        
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(function(){ 
	})
	function sure(btn){
		//$(btn).attr("disabled",true); 
		var path = "<%=request.getContextPath() %>/site/order/sure";
		 $('#submitForm').attr("action", path).submit();;
	}
	function update(btn){
		//$(btn).attr("disabled",true); 
		var path = "<%=request.getContextPath() %>/site/order/update";
		 $('#submitForm').attr("action", path).submit();
	}
	function docancle(btn){
		//$(btn).attr("disabled",true); 
		var path = "<%=request.getContextPath() %>/site/order/docancel";
		 $('#submitForm').attr("action", path).submit();;
	}
</script>

</html> 