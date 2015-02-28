<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals,com.gxkj.taobaoservice.util.*"%>
 <%@ page import="static com.gxkj.taobaoservice.util.SystemDbData.subTaskInfoMap"%>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建任务 </title>
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
				 	<form method="post" action="/site/order/create">
					<table border="0" cellpadding="5" cellspacing="0" style="margin:20px 0;">
							<tr>
									<td width="150" align="right">商家淘宝小号</td>
									<td width="260"><input type="text" id="taobaoXiaohao" name="taobaoXiaohao" class="inputwidth" placeholder="商家淘宝小号"></td>
									<td align="left"></td>
							</tr>
							<tr>
									<td align="right">商家QQ</td>
									<td>
									<input type="text" class="inputwidth" id="userQq" name="userQq"  placeholder="商家QQ"/>
									</td>
									<td></td>
							</tr>
							<tr>
									<td align="right">商品标题</td>
									<td> <input type="text" class="inputwidth" id="productTitle" name="productTitle" placeholder="商品标题"/></td>
									<td>&nbsp;</td>
							</tr>
							<tr>
									<td align="right">商品网址</td>
									<td><input type="text" class="inputwidth" id="productLink"  name="productLink" placeholder="商品网址"/></td>
									<td>&nbsp;</td>
							</tr>
							
							<tr>
									<td align="right">担保价格</td>
									<td><input type="number" class="inputwidth" id="guaranteePrice" name="guaranteePrice" placeholder="担保价格" min="0"/></td>
									<td>担保价格 = 你淘宝的宝贝价格(或改价后价格) + 邮费的总价 <a href="<%=request.getContextPath() %>/site/money/chongzhi" target="_blank"  class="easyui-linkbutton"  >我要充值</a></td>
							</tr>
							<tr>
									<td align="right">基本佣金</td>
									<td><input readonly="readonly" type="text" class="inputwidth" id="basicReceiverGainMoney" name="basicReceiverGainMoney" placeholder="基本佣金" min="0"/></td>
									<td>佣金标准：100元以下的5元（包含100元）;100-200元的8元（包含200元）;200-500元的15元（包含500元）;500元以上20元</td>
							</tr>
							<tr>
									<td align="right">奖励接手人</td>
									<td><input class="inputwidth"  name="encourage"  name="encourage"  type="number" min="0"/></td>
									<td>&nbsp;</td>
							</tr>
							<tr>
									<td align="right">支付平台发布点</td>
									<td><input class="inputwidth"  id="basicPingtaiGainPoint"  name="basicPingtaiGainPoint" value='0.5' readonly /></td>
									<td>&nbsp;</td>
							</tr>
							<tr align="right">
									<td colspan="3" align="center" bgcolor="#09f" style="color:#fff;"><b>以下为基本任务区 </b></td>
							</tr>
							<tr>
									<td align="right">好评时限要求</td>
									<td><select class="inputwidth"   name="goodCommentTimeLimit" id="goodCommentTimeLimit">
											<option value="IMMEDIATELY">立刻好评</option>
											<option value="THIRTYMMinuteLater" selected="selected">30分钟后好评</option>
											<option value="ONE_DAY_LATER">1天后好评</option>
											<option value="TWO_DAY_LATER">2天后好评</option>
											<option value="THREE_DAY_LATER">3天后好评</option>
											<option value="FOURE_DAY_LATER">4天后好评</option>
											<option value="FIVE_DAY_LATER">5天后好评</option>
											<option value="SIX_DAY_LATER">6天后好评</option>
											<option value="SEVEN_DAY_LATER">7天后好评</option>
									</select></td>
									<td>&nbsp;</td>
							</tr>
							<tr>
									<td align="right">指定好评内容</td>
									<td><textarea name="goodCommentContent" id="goodCommentContent" class="inputwidth"
								  style="width: 320px; height: 50px;" rows="4"></textarea></td>
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
									<td><select class="inputwidth"   id="NEED_WANGWANG_TALK" name="NEED_WANGWANG_TALK">
											<option value="1">需要</option>
											<option value="0" selected="selected">不需要</option> 
									</select></td>
									<td>奖励接手方<%=subTaskInfoMap.get("NEED_WANGWANG_TALK").getAmount() %>个发布点</td>
							</tr>
							<tr>
									<td align="right">限制重复接任务</td>
									<td><select class="inputwidth"   id="NO_REPEAT_TASK" name="NO_REPEAT_TASK">
											<option value="1">需要</option>
											<option value="0" selected="selected">不需要</option> 
									</select></td>
									<td>奖励接手方<%=subTaskInfoMap.get("NO_REPEAT_TASK").getAmount() %>个发布点</td>
							</tr>
							<tr>
									<td align="right">指定接手人</td>
									<td>
										<select class="inputwidth" style="width:100px;"  id="ZHI_DING_JIE_SHOU_REN" name="ZHI_DING_JIE_SHOU_REN">
											<option value="1">需要</option>
											<option value="0" selected="selected">不需要</option>
										</select>
										<input class="easyui-textbox zengzhiinputwidth" type="text" id="ZHI_DING_JIE_SHOU_REN_ID" id="ZHI_DING_JIE_SHOU_REN_ID" style="width:190px;height:50px;line-height: 50px;"  placeholder="接手人ID"/> 
									</td>
									<td>支付平台<%=subTaskInfoMap.get("ZHI_DING_JIE_SHOU_REN").getAmount() %>个发布点</td>
							</tr>
							<tr>
									<td align="right">指定收货地址</td>
									<td><select class="inputwidth"   style="width:100px;"id="ZHI_DING_SHOU_HUO_DI_ZHI" name="ZHI_DING_SHOU_HUO_DI_ZHI">
											<option value="1">需要</option>
											<option value="0" selected="selected">不需要</option>
									
									</select>
											<input class="zengzhiinputwidth" type="text" id="ZHI_DING_SHOU_HUO_DI_ZHI_ADDRESS" style="width:190px;height:50px;line-height: 50px;" placeholder="收货人地址"/> 
											</td>
									<td>奖励接手方<%=subTaskInfoMap.get("ZHI_DING_SHOU_HUO_DI_ZHI").getAmount() %>个发布点</td>
							</tr>
						<tr>
								<td align="right">批量发布</td>
								<td><select class="inputwidth"   style="width:100px;" id="PI_LIANG_FA_BU" name="PI_LIANG_FA_BU">
										<option value="1">需要</option>
										<option value="0" selected="selected">不需要</option> 
								</select>
										<input class="zengzhiinputwidth" style="width:100px;height:50px;line-height: 50px;" id="PI_LIANG_FA_BU_input" type="number" min="1" placeholder="发布条数"/>
										</td>
								<td> 批量发布，上限50条,需要支付平台<%=subTaskInfoMap.get("PI_LIANG_FA_BU").getAmount() %>个发布点 </td>
						</tr>
						<tr>
							<td colspan="3" align="center">
								<button class="btn btn-lg btn-success" type="submit">确认提交</button>
							</td>
						</tr>
					</table>
					 </form>
			</div>

		</div>
		<div style="clear:both;"></div>

	</div>

	<div style="clear:both;"></div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html> 