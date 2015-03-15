<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.gxkj.common.util.SystemGlobals,com.gxkj.taobaoservice.util.*"%>
<%@ page import="static com.gxkj.taobaoservice.util.SystemDbData.subTaskInfoMap,com.gxkj.common.exceptions.*,com.gxkj.taobaoservice.entitys.*,com.gxkj.common.util.*, org.apache.commons.lang3.StringUtils"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh"> 
<jsp:include page="../common/mina.jsp"></jsp:include>
<jsp:include page="../common/title.jsp" flush="true">
		<jsp:param name="titletype" value="order_create"></jsp:param>
</jsp:include>
<jsp:include page="../common/css.jsp"></jsp:include>
<jsp:include page="../common/bookstrap.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/resources/appjs/appjs.js"></script>
<style type="text/css">
.cbc:hover{background-color:#D3D3D3;}
table td{padding:5px; height:25px; font-size:14px;}
.zengzhiinputwidth{width:250px;}
#kinMaxShow .KMSPrefix_kinMaxShow_button{left:50%; margin-left:-33px;}
.inputwidth{width:360px;height:50px;line-height:50px;}
.bb{border-bottom:1px solid #eee;}
</style>
<%
BusinessException error = (BusinessException)request.getAttribute("error");
UserBase userBase =  SessionUtil.getSiteUserInSession(request);
%>
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
				<a href="###">任务中心</a>&nbsp;>&nbsp;<a href="###">发布新任务</a>
			</div>

			<div style="overflow:hidden;">

				<div style="margin-bottom:-5000px; padding-bottom:5000px;">
					<jsp:include page="../common/leftmenu.jsp" flush="true">
						<jsp:param name="mainmenu" value="task_center"></jsp:param>
						<jsp:param name="submenu" value="order_create"></jsp:param>
					</jsp:include>
        		</div>
 
				<div style="width:990px; padding:0 40px; background-color:#FFF; padding-bottom:100px;margin-bottom:-5000px; padding-bottom:5000px;" class="fr">
                
			 	  <form method="post" action="<%=request.getContextPath() %>/site/order/create">
				 		<input type="hidden" name="orderid" value="${order.id}">
					  <table border="0" cellpadding="5" cellspacing="0" style="margin:20px 0;">
						  <tr id="errortr" <% if(error == null){out.print("style='display:none;'");} %>>
							  <td colspan="2" align="center">
								  <% if(error != null && error.getSiteFlag() != null && !error.getSiteFlag().equals("userBase")){
										out.print("<b>错误提示："+error.getMessage()+"</b>");
									}%>
							  </td>
						  </tr>
						  <tr>
							  <td width="150" align="right">商家淘宝小号：</td>
							  <td>
                               	  <input type="text" value="${taobaoXiaohao}" style="height:30px; line-height:30px;" id="taobaoXiaohao" name="taobaoXiaohao" class="inputwidth" placeholder="商家淘宝小号">
								  <button class="btn btn-default" type="button" onclick="">使用我的</button>
							  </td>
						  </tr>
						  <tr>
							  <td align="right">商家QQ：</td>
							  <td>
								  <input type="text" class="inputwidth" style="height:30px; line-height:30px;" value="${userQq}"  id="userQq" name="userQq" placeholder="商家QQ"/>
								  <button class="btn btn-default" type="button" onclick="">使用我的</button>
							  </td>
						  </tr>
						  <tr>
							  <td align="right">商品标题：</td>
							  <td>
                               	  <input type="text" class="inputwidth" style="height:30px; line-height:30px;" value="${productTitle}" id="productTitle" name="productTitle" placeholder="商品标题"/>
							  </td>
						  </tr>
						  <tr>
							  <td align="right">商品网址：</td>
							  <td>
                               	  <input type="text" class="inputwidth" value="${productLink}" id="productLink" name="productLink" placeholder="商品网址" style="height:30px; line-height:30px;" />
                              </td>
						  </tr>
						  <tr>
							  <td align="right">担保价格：</td>
							  <td>
                               	  <input type="number" class="inputwidth" style="height:30px; line-height:30px;" value="${guaranteePrice}" id="guaranteePrice" name="guaranteePrice" placeholder="担保价格" min="0" onkeyup="caculateFn()"/><br/>
                                  <span style="font-size:12px; color:#999;">担保价格 = 你淘宝的宝贝价格(或改价后价格) + 邮费的总价 <a href="<%=request.getContextPath() %>/site/money/chongzhi" target="_blank" class="easyui-linkbutton" style="color:#36F;">我要充值</a></span>
                              </td>
						  </tr>
						  <tr>
							  <td align="right">基本佣金：</td>
							  <td>
                               	  <input readonly type="text" style="height:30px; line-height:30px;" value="${basicReceiverGainMoney}" class="inputwidth" id="basicReceiverGainMoney" name="basicReceiverGainMoney" placeholder="基本佣金" min="0"/><br/>
                                  <span style="font-size:12px; color:#999;">佣金标准：100元以下的5元（包含100元）;100-200元的8元（包含200元）;200-500元的15元（包含500元）;500元以上20元</span>
                              </td>
						  </tr>
						  <tr>
							  <td align="right">奖励接手人：</td>
							  <td>
                               	  <input class="inputwidth" style="height:30px; line-height:30px;" name="encourage" value="${encourage}" type="number" min="0"/>
                              </td>
						  </tr>
						  <tr>
							  <td align="right">支付平台发布点：</td>
							  <td>
                               	  <input class="inputwidth" style="height:30px; line-height:30px;" id="basicPingtaiGainPoint" name="basicPingtaiGainPoint" value='${basicPingtaiGainPoint }' readonly />
                              </td>
						  </tr>
						  <tr align="right">
							  <td colspan="2" align="center" bgcolor="#09f" style="color:#fff;"><b>以下为基本任务区</b></td>
						  </tr>
						  <tr>
							  <td align="right">好评时限要求：</td>
							  <td>
                               	  <select style="height:30px; line-height:30px;" class="inputwidth" name="goodCommentTimeLimit" id="goodCommentTimeLimit"  >
									  <option value="IMMEDIATELY" <c:if test="${'IMMEDIATELY' == goodCommentTimeLimit }">selected="selected"</c:if>>立刻好评</option>
									  <option value="THIRTYMMinuteLater" <c:if test="${'THIRTYMMinuteLater' == goodCommentTimeLimit }">selected="selected"</c:if>>30分钟后好评</option>
									  <option value="ONE_DAY_LATER" <c:if test="${'ONE_DAY_LATER' == goodCommentTimeLimit }">selected="selected"</c:if>>1天后好评</option>
									  <option value="TWO_DAY_LATER" <c:if test="${'TWO_DAY_LATER' == goodCommentTimeLimit }">selected="selected"</c:if>>2天后好评</option>
									  <option value="THREE_DAY_LATER" <c:if test="${'FOURE_DAY_LATER' == goodCommentTimeLimit }">selected="selected"</c:if>>3天后好评</option>
									  <option value="FOURE_DAY_LATER" <c:if test="${'FOURE_DAY_LATER' == goodCommentTimeLimit }">selected="selected"</c:if>>4天后好评</option>
									  <option value="FIVE_DAY_LATER" <c:if test="${'FIVE_DAY_LATER' == goodCommentTimeLimit }">selected="selected"</c:if>>5天后好评</option>
									  <option value="SIX_DAY_LATER" <c:if test="${'SIX_DAY_LATER' == goodCommentTimeLimit }">selected="selected"</c:if>>6天后好评</option>
									  <option value="SEVEN_DAY_LATER" <c:if test="${'SEVEN_DAY_LATER' == goodCommentTimeLimit }">selected="selected"</c:if>>7天后好评</option>
								  </select>
							  </td>
						  </tr>
						  <tr>
							  <td align="right">指定好评内容：</td>
							  <td>
                               	  <textarea name="goodCommentContent" id="goodCommentContent" class="inputwidth" style="width:360px; resize:none; height:50px; line-height:normal;" rows="4">${goodCommentContent}</textarea>
                              </td>
						  </tr>
						  <tr>
							  <td align="right">商铺动态评分：</td>
							  <td><b>掌柜店铺动态评分，请全给对方打5分</b></td>
						  </tr>
						  <tr align="right">
							  <td colspan="2" align="center" bgcolor="#09f" style="color:#fff;">
                               	  <b>以下为增值服务区，选择勾选</b>
                              </td>
						  </tr>
						  <tr>
							  <td align="right">需要旺旺聊天：</td>
							  <td>
                               	  <select style="height:30px; line-height:30px; width:400px;" class="inputwidth" id="NEED_WANGWANG_TALK" name="NEED_WANGWANG_TALK">
									  <option value="0" <c:if test="${NEED_WANGWANG_TALK eq '0' }">selected</c:if>>不需要</option>
									  <option value="1" <c:if test="${NEED_WANGWANG_TALK eq '1' }">selected</c:if>>需要</option>
								  </select><br/>
                                  <span style="font-size:12px; color:#999;">奖励接手方<%=subTaskInfoMap.get("NEED_WANGWANG_TALK").getAmount() %>个发布点</span>
                              </td>
						  </tr>
						  <tr>
							  <td align="right">限制重复接任务：</td>
							  <td>
                               	  <select class="inputwidth" style="height:30px; width:400px; line-height:30px;" id="NO_REPEAT_TASK" name="NO_REPEAT_TASK" value="${NO_REPEAT_TASK }">
									  <option value="0" <c:if test="${NO_REPEAT_TASK eq '0' }">selected="selected"</c:if>>不需要</option>
									  <option value="1" <c:if test="${NO_REPEAT_TASK eq '1' }">selected="selected"</c:if> >需要</option> 
								  </select><br/>
                                  <span style="font-size:12px; color:#999;">支付平台<%=subTaskInfoMap.get("NO_REPEAT_TASK").getAmount() %>个发布点</span>
                              </td>
						  </tr>
						  <tr>
							  <td align="right">指定收货地址：</td>
							  <td>
                               	  <select class="inputwidth" style="width:100px; height:30px; line-height:30px;"id="ZHI_DING_SHOU_HUO_DI_ZHI" name="ZHI_DING_SHOU_HUO_DI_ZHI" value="${ZHI_DING_SHOU_HUO_DI_ZHI }">
									  <option value="0" <c:if test="${ZHI_DING_SHOU_HUO_DI_ZHI eq '0' }">selected="selected"</c:if>>不需要</option>
									  <option value="1" <c:if test="${ZHI_DING_SHOU_HUO_DI_ZHI eq '1' }">selected="selected"</c:if>>需要</option>
								  </select>
								  <input class="zengzhiinputwidth" type="text" id="ZHI_DING_SHOU_HUO_DI_ZHI_ADDRESS" name="ZHI_DING_SHOU_HUO_DI_ZHI_ADDRESS" value="${ZHI_DING_SHOU_HUO_DI_ZHI_ADDRESS }" style="width:300px;height:30px;line-height:30px;" placeholder="收货人地址"/><br/>
                                  <span style="font-size:12px; color:#999;">奖励接手方<%=subTaskInfoMap.get("ZHI_DING_SHOU_HUO_DI_ZHI").getAmount() %>个发布点</span>
							  </td>
					    </tr>
						  <tr>
							  <td align="right">指定接手人：</td>
							  <td>
								  <select class="inputwidth" style="width:100px; height:30px; line-height:30px;" id="ZHI_DING_JIE_SHOU_REN" name="ZHI_DING_JIE_SHOU_REN"  value="${ZHI_DING_JIE_SHOU_REN }">
									  <option value="0" <c:if test="${ZHI_DING_JIE_SHOU_REN eq '0' }">selected="selected"</c:if>>不需要</option>
									  <option value="1" <c:if test="${ZHI_DING_JIE_SHOU_REN eq '1' }">selected="selected"</c:if>>需要</option>
								  </select>
								  <input class="easyui-textbox zengzhiinputwidth" type="text" name="ZHI_DING_JIE_SHOU_REN_ID" id="ZHI_DING_JIE_SHOU_REN_ID" value="${ZHI_DING_JIE_SHOU_REN_ID }" style="width:100px;height:30px;line-height:30px;" placeholder="接手人ID"/><br/>
                                  <span style="font-size:12px; color:#999;">支付平台<%=subTaskInfoMap.get("ZHI_DING_JIE_SHOU_REN").getAmount() %>个发布点</span>
							  </td>
						  </tr>
						  <tr>
							  
						  </tr>
						  <tr>
							  <td align="right">批量发布：</td>
							  <td>
                               	  <select class="inputwidth" style="width:100px; height:30px; line-height:30px;" id="PI_LIANG_FA_BU" name="PI_LIANG_FA_BU">
									  <option value="0" <c:if test="${PI_LIANG_FA_BU eq '0' }">selected="selected"</c:if>>不需要</option> 
									  <option value="1" <c:if test="${PI_LIANG_FA_BU eq '1' }">selected="selected"</c:if>>需要</option>
								  </select>
								  <input class="zengzhiinputwidth" style="width:100px;height:30px;line-height:30px;" id="PI_LIANG_FA_BU_input"  name="PI_LIANG_FA_BU_input" value="${PI_LIANG_FA_BU_input}" type="number" min="2" placeholder="发布条数"/><br/>
                                  <span style="font-size:12px; color:#999;">批量发布，上限50条,需要支付平台<%=subTaskInfoMap.get("PI_LIANG_FA_BU").getAmount() %>个发布点</span>
							  </td>
						  </tr>
						  <tr>
							  <td colspan="2" align="center">
								  <button class="btn btn-lg btn-success" type="submit">确认提交</button>
							  </td>
						  </tr>
					  </table>
				    </form>

				</div>

			</div>

		</div>

		<div style="clear:both;"></div>

	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
var errorType = null;
<% if(error != null){
	out.print("errorType='"+error.getSiteFlag()+"'");
}%>

	$(function(){
		if(errorType != null  ){
			if( errorType == 'taobaoXiaohao'){
				$("#taobaoXiaohao").focus(function(){
					$("#errortr").hide();
				});
			}else if ( errorType == 'productTitle'){
				$("#productTitle").focus(function(){
					$("#errortr").hide();
				});
				
			}else if ( errorType == 'productLink'){
				$("#productLink").focus(function(){
					$("#errortr").hide();
				});
				
			}else if ( errorType == 'guaranteePrice'){
				$("#guaranteePrice").focus(function(){
					$("#errortr").hide();
				});
				
			}else if ( errorType == 'jieShouRenId'){
				
				
				$("#ZHI_DING_JIE_SHOU_REN").focus(function(){
					$("#errortr").hide();
				});
				$("#ZHI_DING_JIE_SHOU_REN_ID").focus(function(){
					$("#errortr").hide();
				});
				
			}else if ( errorType == 'shouHuoDiZhi'){
				$("#ZHI_DING_SHOU_HUO_DI_ZHI").focus(function(){
					$("#errortr").hide();
				});
				$("#ZHI_DING_SHOU_HUO_DI_ZHI_ADDRESS").focus(function(){
					$("#errortr").hide();
				});
				
			}else if ( errorType == 'piLiangFabuCount'){
				
				$("#PI_LIANG_FA_BU").focus(function(){
					$("#errortr").hide();
				});
				$("#PI_LIANG_FA_BU_input").focus(function(){
					$("#errortr").hide();
				});
			}
		}
	})
</script>

</html> 