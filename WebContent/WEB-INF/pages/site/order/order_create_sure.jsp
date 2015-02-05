<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals"%>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单确认页面 </title>
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
	 	<div align="center"><h1>订单确认</h1></div>
		 <form id="ff" method="post" action="<%= request.getContextPath()%>/order/create">
					<table cellpadding="5">
							<tr>
								<td>商家淘宝小号</td>
								<td>wsypy890（最长100字）</input></td>
								<td align="left"> </td>
							</tr>
							<tr>
								<td>商家QQ</td>
								<td>48924951（最长20字）</input></td>
								<td> </td>
							</tr>
							<tr>
								<td>商品地址</td>
								<td>http://item.taobao.com/item.htm?spm=a1z10.1.w5003-9327637950.2.kAfZoQ&id=41950071928&scene=taobao_shop（最长300字） </td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>商品标题</td>
								<td>冬装中老年女装羽绒服40-50岁送妈妈装高贵时尚保暖服加大码外套（最长300字）</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>任务担保价格</td>
								<td>138元</td>
								<td>担保价格 = 你淘宝的宝贝价格(或改价后价格) + 邮费的总价 
								</td>
							</tr>
							<tr>
								<td>基本佣金</td>
								<td>5元</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>奖励接手人：</td>
								<td>2元</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>基本发布点</td>
								<td>0.5</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>任务类型:</td>
								<td>虚拟  </td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td colspan="3"><b>以下为基本任务区 </b></td>
							</tr>
							<tr>
								<td>好评时限要求:</td>
								<td>立刻好评</select>
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>指定好评内容:</td>
								<td>
								质量不错，没见到上身效果，125斤听说很合适！[或不指定]（最长100字） 
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
								<td>需要
								</td>
								<td>奖励接手方0.5个发布点</td>
							</tr>
							
							<tr>
								<td>指定接手人</td>
								<td>需要<input class="easyui-textbox zengzhiinputwidth" type="text" name="ZHI_DING_JIE_SHOU_REN" data-options="required:true" value="abcdd"></input></td>
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
							<tr><td colspan="3" align="center">
								<table>
									<tr><td>您将支付如下费用</td></tr>
									<tr><td>每单订单佣金：5元</td></tr>
									<tr><td>每单支付奖励佣金：2元</td></tr>
									<tr><td>每单[需要旺旺聊天]支付[接手人]发布点：0.5个</td></tr>
									<tr><td>每单[指定接手人]支付[接手人]发布点：0.5个</td></tr>
									<tr><td>。。。。。（这里列举增值区的消费明细及金额）</td></tr>
								</table>
								<table>
									<tr><td>合计</td></tr>
									<tr><td>冻结资金：7元</td></tr>
									<tr><td>冻结平台币：2个</td></tr>
									<tr><td>支付平台币：1个</td></tr> 
								</table>
							
							</td></tr>
							
					</table>
			  </form>
				<div style="text-align:center;padding:5px">
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">确认</a><a href="">返回修改</a>
				</div>
	</div>
	 
</body>
<script type="text/javascript">
var addUserBtn = "${_adminUser_.btnMap.userdoadd}"== "true"?true:false;

$(function(){
	 
});
function submitForm(){
	alert("任务已经创建完毕，将跳转到我发布的任务里");
}
 

	</script>
</html>