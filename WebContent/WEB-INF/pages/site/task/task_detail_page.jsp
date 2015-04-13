<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals,com.gxkj.taobaoservice.util.*,com.gxkj.taobaoservice.entitys.*"%>
 <%@ page import="static com.gxkj.taobaoservice.util.SystemDbData.subTaskInfoMap, com.gxkj.common.exceptions.*"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <% TaskBasic taskBasic = (TaskBasic)request.getAttribute("taskBasic"); %>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<jsp:include page="../common/mina.jsp"></jsp:include>
<jsp:include page="../common/title.jsp" flush="true">
		<jsp:param name="titletype" value="task_detail"></jsp:param>
</jsp:include>
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
				<a href="###">任务中心</a>&nbsp;>&nbsp;<a href="###">任务详情</a>
			</div>

			<div style="overflow:hidden;">

				<div style="margin-bottom:-5000px; padding-bottom:5000px;">
					<jsp:include page="../common/leftmenu.jsp" flush="true">
						<jsp:param name="mainmenu" value="task"></jsp:param>
						<jsp:param name="submenu" value="detail"></jsp:param>
					</jsp:include>
				</div>

			  <div style="width:990px; padding:0 40px; background-color:#FFF; padding-bottom:100px; margin-bottom:-5000px; padding-bottom:5000px;" class="fr">
                	
                <h2 style="font-size:16px; font-weight:bold; color:#39F;">状态：${taskBasic.status.name}</h2>
					<table border="0" cellpadding="5" cellspacing="0" style="margin:20px 0;">
						<tr style="display: none" >
							<td colspan="2" align="center">
								<input type="hidden" name="taskid" value="${taskBasic.id }"> 
							</td>
						</tr>
						<tr>
							<td width="150" align="right" class="bb">商家淘宝小号：</td>
							<td class="bb"><c:out escapeXml="true" value="${taskBasic.taobaoXiaohao}"></c:out></td>
						</tr>
						<tr>
							<td align="right" class="bb">商家QQ：</td>
							<td class="bb"><c:out escapeXml="true" value="${taskBasic.userQq}"></c:out></td>
						</tr>
						<tr>
							<td align="right" class="bb">商品标题：</td>
							<td class="bb"><c:out escapeXml="true" value="${taskBasic.productTitle}"></c:out></td>
						</tr>
						<tr>
							<td class="bb" align="right">商品网址：</td>
							<td class="bb"><c:out escapeXml="true" value="${taskBasic.productLink}"></c:out></td>
						</tr>	
						<tr>
							<td class="bb" align="right" valign="top">担保价格：</td>
							<td class="bb">${taskBasic.guaranteePrice}<br/><span style="font-size:12px; color:#999;">担保价格 = 你淘宝的宝贝价格(或改价后价格) + 邮费的总价 <a href="<%=request.getContextPath() %>/site/money/chongzhi" target="_blank" class="easyui-linkbutton" style="color:#36F;">我要充值</a></span></td>
						</tr>
						<tr>
							<td class="bb" align="right" valign="top">佣金：</td>
						  <td class="bb">${taskBasic.commission}<br/><span style="font-size:12px; color:#999;">接任务人获利</span></td>
					  </tr>
						
						<tr>
							<td align="right">支付平台发布点：</td>
							<td>0.5</td>
						</tr>
						<tr>
							<td colspan="2" align="center" bgcolor="#09f" style="color:#fff;"><b>以下为基本任务区 </b></td>
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
							<td class="bb">
                            	<c:choose>
									<c:when test="${empty GOOD_COMMENT_CONTENT.inputValue   }">无</c:when>
									<c:otherwise><c:out escapeXml="true" value="${GOOD_COMMENT_CONTENT.inputValue  }"></c:out></c:otherwise>
								</c:choose>
                            </td>
						</tr>
						<tr>
							<td align="right">商铺动态评分：</td>
							<td><b>掌柜店铺动态评分，请全给对方打5分</b></td>
						</tr>
						<tr>
							<td colspan="2" align="center" bgcolor="#09f" style="color:#fff;"><b>以下为增值服务区，选择勾选</b></td>
						</tr>
						<tr>
							<td class="bb" align="right" valign="top">需要旺旺聊天：</td>
							<td class="bb">
                            	<c:choose>
									<c:when test="${empty NEED_WANGWANG_TALK }">不需要</c:when>
									<c:otherwise>需要</c:otherwise> 
							  </c:choose><br/>
                                <span style="font-size:12px; color:#999;">奖励接手方<%=subTaskInfoMap.get("NEED_WANGWANG_TALK").getAmount() %>个发布点</span>
						  </td>
					  </tr>
						<tr>
							<td class="bb" align="right" valign="top">限制重复接任务：</td>
							<td class="bb">
                            	<c:choose>
									<c:when test="${empty NO_REPEAT_TASK  }">不需要</c:when>
									<c:otherwise>需要</c:otherwise>
							  </c:choose><br/>
                                <span style="font-size:12px; color:#999;">支付平台<%=subTaskInfoMap.get("NO_REPEAT_TASK").getAmount() %>个发布点</span>
                          </td>
					  </tr>
						<tr>
							<td class="bb" align="right" valign="top">指定接手人：</td>
							<td class="bb">
                            	<c:choose>
									<c:when test="${ empty ZHI_DING_JIE_SHOU_REN   }">不需要</c:when>
									<c:otherwise>需要 接手人ID：${ZHI_DING_JIE_SHOU_REN.inputValue }</c:otherwise>
							  </c:choose><br/>
                                <span style="font-size:12px; color:#999;">支付平台<%=subTaskInfoMap.get("ZHI_DING_JIE_SHOU_REN").getAmount() %>个发布点</span>
						  </td>
					  </tr>
						<tr>
							<td align="right" valign="top">指定收货地址：</td>
							<td>
								<c:choose>
									<c:when test="${ empty ZHI_DING_SHOU_HUO_DI_ZHI     }">不需要</c:when>
									<c:otherwise>需要 &nbsp;<c:out escapeXml="true" value="${ZHI_DING_SHOU_HUO_DI_ZHI.inputValue}"></c:out></c:otherwise>
							  </c:choose><br/>
                                <span style="font-size:12px; color:#999;">奖励接手方<%=subTaskInfoMap.get("ZHI_DING_SHOU_HUO_DI_ZHI").getAmount() %>个发布点</span>
						  </td>
					  </tr>
						 
						<c:if test="${not empty taskBasic.receiverId }">
						<tr>
							<td colspan="2" align="center" bgcolor="#09f" style="color:#fff;"><b>任务进度</b></td>
						</tr>
							<tr>
								<td class="bb" align="right">接单人QQ：</td>
								<td class="bb">
									 <c:out escapeXml="true" value="${taskBasic.receiverQq}"></c:out>
								</td>
							</tr>
							<tr>
								<td class="bb" align="right">接单淘宝小号：</td>
								<td class="bb">
									 <c:out escapeXml="true" value="${taskBasic.receiverAlipay}"></c:out>
								</td>
							</tr>
							<tr>
								<td class="bb" align="right">接单时间：</td>
								<td class="bb">
									<fmt:formatDate value="${taskBasic.receiverTime}" type="both" pattern="yyyy-MM-dd HH:mm"/>
								</td>
							</tr>
							<tr>
							  <td class="bb" align="right">完成时间：</td>
							  <td class="bb"><fmt:formatDate value="${taskBasic.taskCompleteTime}" type="both" pattern="yyyy-MM-dd HH:mm"/></td>
						  </tr>
							<tr>
							  <td class="bb" align="right">结束时间：</td>
							  <td class="bb"><fmt:formatDate value="${taskBasic.taskEndTime}" type="both" pattern="yyyy-MM-dd HH:mm"/></td>
						  </tr>
							
						</c:if>
						<tr>
							<td colspan="2" align="center">
								<c:choose>
									<c:when test="${taskBasic.status eq 'Wait_For_Receive'}">
										<c:if test="${userBase.id != taskBasic.userId}">
                                        	<button class="btn btn-lg btn-success" onclick="doThisTask(this,'${taskBasic.id }')">接单</button>
                                        </c:if>
									</c:when>
									<c:when test="${taskBasic.status eq 'Have_Bean_Received'}"> 
										<c:if test="${taskBasic.receiverId eq userBase.id}">
											<button class="btn btn-default" id="completebtn${taskBasic.id}" onclick="dowork(this,${taskBasic.id})">完成</button>
											<button class="btn btn-default" id="cancelbtn${taskBasic.id}" onclick="docancle(this,${taskBasic.id})">放弃任务</button>
										</c:if>
									</c:when>
									<c:when test="${taskBasic.status eq 'Receive_Complete'}"> 
										<c:if test="${taskBasic.userId eq userBase.id}">
											<button class="btn btn-default" id="suerbtn${taskBasic.id}" onclick="doSure(this,${taskBasic.id})">完成</button>
										</c:if>
									</c:when>
									<c:otherwise>   
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</table>

				</div>
					 
			</div>

		</div>

		<div style="clear:both;"></div>

	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(function(){ 
	})
	 
	function doThisTask(zthis,id){
	 
		var yanzhengmaurl = "<%=request.getContextPath()%>/site/task/recievetask";
	  	$.ajax({
			  type:'post',
			  url: yanzhengmaurl,
			  context: document.body,
			  beforeSend:function(){
				  $(zthis).attr("disabled",true); 
				  $(zthis).html("提交中。。。");
			 },
			  data:{
				  taskid:id,
				  d:new Date().getTime()
				  
			  },
			  success:function(json){
				  alert("已接任务,新任务放到我未完成的任务里");
				  window.location.reload();
				  return; 
				 	  
			  },
		      error:function(xhr,textStatus,errorThrown){
		    	  $(zthis).attr("disabled",false); 
				  $(zthis).html("接任务");
				  
		  		var responseText = xhr.responseText;
		  		var obj = jQuery.parseJSON(responseText);
				var errortype = obj.errortype
		  		var msg = obj.msg;
				alert(msg)
				
		  		 
		  } 
		})
		
	}
	/**
	 * 任务完成
	 */
	function dowork(zthis,id){
		var yanzhengmaurl = "<%=request.getContextPath()%>/site/task/reciercomplete";
	  	$.ajax({
			  type:'post',
			  url: yanzhengmaurl,
			  context: document.body,
			  beforeSend:function(){
				  $(zthis).attr("disabled",true); 
				  $(zthis).html("提交中。。。");
			 },
			  data:{
				  d:new Date().getTime(),
				  taskid:id
			  },
			  success:function(json){
				  alert("任务已完成，任务进入我完成的任务中");
				  window.location.reload();
				  return; 
				 	  
			  },
		      error:function(xhr,textStatus,errorThrown){
		    	  $(zthis).attr("disabled",false); 
				  $(zthis).html("完成");
				  
		  		var responseText = xhr.responseText;
		  		var obj = jQuery.parseJSON(responseText);
				var errortype = obj.errortype
		  		var msg = obj.msg;
				alert(msg)
		  } 
		})
	}
	function docancle(zthis,id){
		var yanzhengmaurl = "<%=request.getContextPath()%>/site/task/reciergiveup";
	  	$.ajax({
			  type:'post',
			  url: yanzhengmaurl,
			  context: document.body,
			  beforeSend:function(){
				  $(zthis).attr("disabled",true); 
				  $(zthis).html("提交中。。。");
			 },
			  data:{
				  d:new Date().getTime(),
				  taskid:id
			  },
			  success:function(json){
				  alert("任务已放弃，任务进入任务大厅中");
				  window.location.reload();
				  return; 
				 	  
			  },
		      error:function(xhr,textStatus,errorThrown){
		    	  $(zthis).attr("disabled",false); 
				  $(zthis).html("取消");
				  
		  		var responseText = xhr.responseText;
		  		var obj = jQuery.parseJSON(responseText);
				var errortype = obj.errortype
		  		var msg = obj.msg;
				alert(msg)
		  } 
		})
	}
	function doSure(zthis,id){
		var  sure=window.confirm("确定完成任务，将进行支付？");
		if(!sure)return;
		var yanzhengmaurl = "<%=request.getContextPath()%>/site/task/surecomplete";
	  	$.ajax({
			  type:'post',
			  url: yanzhengmaurl,
			  context: document.body,
			  beforeSend:function(){
				  $("#suerbtn"+id).attr("disabled",true); 
				  $("#suerbtn"+id).html("提交中。。。");
			 },
			  data:{
				  d:new Date().getTime(),
				  taskid:id
			  },
			  success:function(json){
				   
				  alert("任务已结束");
				  $("#suerbtn"+id).attr("disabled",false);
				  window.location.reload();
				  return; 
				 	  
			  },
		      error:function(xhr,textStatus,errorThrown){
		    	  $("#suerbtn"+id).attr("disabled",false); 
				  $("#suerbtn"+id).html("完成");
				  
		  		var responseText = xhr.responseText;
		  		var obj = jQuery.parseJSON(responseText);
				var errortype = obj.errortype
		  		var msg = obj.msg;
				alert(msg)
		  } 
		})
	}
</script>

</html> 