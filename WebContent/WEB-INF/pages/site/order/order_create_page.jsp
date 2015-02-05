<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals"%>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建任务 </title>
<%@include file="../../common/easyui-html5.jsp" %>
<style type="text/css">
.inputwidth{
	width: 280px;
}
.zengzhiinputwidth{
	width: 250px;
}
</style>
</head>
<body class="easyui-layout">
	 <div data-options="region:'center'">
		 <form id="ff" method="post" action="<%= request.getContextPath()%>/order/create">
					<table cellpadding="5">
							<tr>
								<td>商家淘宝小号</td>
								<td><input class="easyui-textbox inputwidth" type="text" name="taobaoXiaohao" data-options="required:true"></input></td>
								<td align="left"><a href="#" class="easyui-linkbutton">使用我的</a></td>
							</tr>
							<tr>
								<td>商家淘宝QQ</td>
								<td><input class="easyui-textbox inputwidth" type="text" name="userQq" data-options="required:true"></input></td>
								<td><a href="#" class="easyui-linkbutton">使用我的</a></td>
							</tr>
							<tr>
								<td>商品地址</td>
								<td><input class="easyui-textbox inputwidth" type="text" name="productLink" data-options="required:true"></input></td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>商品标题</td>
								<td><input class="easyui-textbox inputwidth" type="text" name="productTitle" data-options="required:true"></input></td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>任务担保价格</td>
								<td><input class="easyui-numberbox inputwidth"  name="guaranteePrice"  data-options="min:1,max:90000,precision:2,required:true"></input></td>
								<td>担保价格 = 你淘宝的宝贝价格(或改价后价格) + 邮费的总价 
								 <a href="#" class="easyui-linkbutton" data-options="" 
								 	onclick="javascript:alert('easyui')">我要充值</a>
								</td>
							</tr>
							<tr>
								<td>基本佣金</td>
								<td><input class="easyui-numberbox inputwidth"  name="basicPublishDot" value='5' readonly="readonly" data-options="min:0.5,max:90000,precision:2,required:true"></input></td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>奖励接手人：</td>
								<td><input class="easyui-numberbox inputwidth"  name="basicPublishDot" value='5' readonly="readonly" data-options="min:0.5,max:90000,precision:2,required:true"></input></td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>基本发布点</td>
								<td><input class="easyui-numberbox inputwidth"  name="basicPublishDot" value='0.5' readonly="readonly" data-options="min:0.5,max:90000,precision:2,required:true"></input></td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>任务类型:</td>
								<td><select class="easyui-combobox inputwidth" data-options="panelHeight:40,width:100" name="taskType">
										<option value="VIRTUAL">虚拟                                </option>
										<option value="PRACTICALITY" selected="selected">实物</option>
									</select>
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td colspan="3"><b>以下为基本任务区 </b></td>
							</tr>
							<tr>
								<td>好评时限要求:</td>
								<td><select class="easyui-combobox inputwidth" data-options="panelHeight:170,width:170" name="goodCommentTimeLimit">
										<option value="IMMEDIATELY">立刻好评</option>
										<option value="THIRTYMMinuteLater" selected="selected">30分钟后好评</option>
										<option value="ONE_DAY_LATER">1天后好评</option>
										<option value="TWO_DAY_LATER">2天后好评</option>
										<option value="THREE_DAY_LATER">3天后好评</option>
										<option value="FOURE_DAY_LATER">4天后好评</option>
										<option value="FIVE_DAY_LATER">5天后好评</option>
										<option value="SIX_DAY_LATER">6天后好评</option>
										<option value="SEVEN_DAY_LATER">7天后好评</option>
									</select>
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>指定好评内容:</td>
								<td>
								<textarea name="goodComment" class="inputwidth"
								  style="width: 280px; height: 50px;" rows="4"></textarea>
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>商铺动态评分：</td>
								<td>
								掌柜店铺动态评分，请全给对方打5分
								<td>&nbsp;</td>
							</tr>	
							<tr>
								<td colspan="3"><b>以下为增值服务区，选择勾选</b></td>
							</tr>
							<tr>
								<td>需要旺旺聊天：</td>
								<td>
								<select class="easyui-combobox inputwidth" data-options="panelHeight:50,width:70" name="NEED_WANGWANG_TALK">
										<option value="1">需要</option>
										<option value="0" selected="selected">不需要</option>　
									</select>
								</td>
								<td>奖励接手方0.5个发布点</td>
							</tr>
							
							<tr>
								<td>指定接手人</td>
								<td><select class="easyui-combobox" data-options="panelHeight:50,width:70" name="ZHI_DING_JIE_SHOU_REN">
										<option value="1">需要</option>
										<option value="0" selected="selected">不需要</option>　
									</select><input class="easyui-textbox zengzhiinputwidth" type="text" name="ZHI_DING_JIE_SHOU_REN" data-options="required:true"></input></td>
								<td>支付平台0.5个发布点</td>
							</tr>
							<tr>
								<td>指定收货地址</td>
								<td><select class="easyui-combobox"  data-options="panelHeight:50,width:70" name="ZHI_DING_SHOU_HUO_DI_ZHI">
										<option value="1">需要</option>
										<option value="0" selected="selected">不需要</option>　
									</select><input class="easyui-textbox zengzhiinputwidth" type="text" name="" data-options="required:true"></input></td>
								<td>奖励接手方0.5个发布点</td>
							</tr>
							
							<tr>
								<td>限制用户不重复接手：</td>
								<td>
								<select class="easyui-combobox inputwidth" data-options="panelHeight:50,width:70" name="XIAN_ZHI_YONG_HU_BU_CHONG_FU_JIE_SHOU">
										<option value="1">需要</option>
										<option value="0" selected="selected">不需要</option>　
									</select>
								</td>
								<td>同一天内接手人不允许重复接手相同掌柜的任务，需额外支付平台0.2个发布点</td>
							</tr>
							
							　<tr>
								<td>批量发布</td>
								<td>
								<select class="easyui-combobox"  data-options="panelHeight:50,width:70" name="PI_LIANG_FA_BU">
										<option value="1">需要</option>
										<option value="0" selected="selected">不需要</option>　
									</select>
								<input class="easyui-numberbox zengzhiinputwidth"    data-options="min:1,max:50,precision:0,required:false"></input></td>
								<td>
								 	批量发布，上限50条,需要支付平台1个发布点
								</td>
							</tr>
					</table>
			  </form>
				<div style="text-align:center;padding:5px">
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">确认提交</a>
				</div>
	</div>
	 
</body>
<script type="text/javascript">
var addUserBtn = "${_adminUser_.btnMap.userdoadd}"== "true"?true:false;

$(function(){
	 
});
function submitForm(){
	alert(99);
 document.getElementById("ff").submit();	
}
 

	</script>
</html>