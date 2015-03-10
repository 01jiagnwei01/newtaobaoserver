<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals"%>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单管理 </title>
<%@include file="../../common/easyui-html5.jsp" %>
</head>
<body class="easyui-layout">
	 <div data-options="region:'center'">
		<table id="dg"></table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<table style="width:100%">
			<tr style="width:100%">
				<td width="100%"  colspan="2">
					创建者ID: <input class="easyui-spinner" style="width:160px" id="userId"/>
					创建日期从: <input class="easyui-datebox" style="width:160px" id="beginTime"/>
					到: <input class="easyui-datebox" style="width:160px" id="endTime"/>
					订单淘宝号: <input class="easyui-textbox" style="width:160px" id="taobao"/>
				</td>
					
			</tr>
			<tr style="width:100%">
				<td width="100%"  colspan="2">
					订单QQ号: <input class="easyui-textbox" style="width:160px" id="qq"/>
					
					状态: <select id="status" class="easyui-combobox"   style="width:160px;"  data-options='panelHeight:90'>
						<option value="">不限</option>
						<option value="WAIT_FOR_SURE">等待确认</option> 
						<option value="SURE">已确认</option>
						<option value="CANCEL">已取消</option> 
					</select> 
					<a href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFn()">查看</a>
				 </td>
			</tr>
		</table>
	</div>
	 <div id="w" class="easyui-window" title="任务详情" data-options="minimizable:false,modal:true,closed:true,iconCls:'icon-save'" style=" padding:10px;width: 100%;height: 100%">
		<div class="easyui-layout" data-options="fit:true">
			 
			<div data-options="region:'center'" style="padding:10px;">
				<form id="ff" method="post">
			    	<table cellpadding="5">
			    		<tr>
			    			<td>淘宝号:</td>
			    			<td id="taobaoXiaohao"></td>
			    		</tr>
			    		<tr>
			    			<td>订单QQ号:</td>
			    			<td id="userQq"></td>
			    		</tr>
			    		<tr>
			    			<td>订单时间:</td>
			    			<td id="createTime"></td>
			    		</tr>
			    		<tr>
			    			<td>商品标题:</td>
			    			<td id="productTitle"></td>
			    		</tr>
			    		<tr>
			    			<td>商品链接:</td>
			    			<td id="productLink"></td>
			    		</tr>
			    		<tr>
			    			<td>担保价格:</td>
			    			<td id="guaranteePrice"></td>
			    		</tr>
			    		<tr>
			    			<td>基本佣金:</td>
			    			<td id="basicReceiverGainMoney"></td>
			    		</tr>
			    		<tr>
			    			<td>奖励接手人:</td>
			    			<td id="encourage"></td>
			    		</tr>
			    		<tr>
			    			<td>支付平台发布点:</td>
			    			<td id="basicPingtaiGainPoint"></td>
			    		</tr>
			    		<tr>
			    			<td>好评时限要求:</td>
							<td id="GOOD_COMMENT_TIME_LIMIT"></td>
			    		</tr>
			    		<tr>
			    			<td>指定好评内容:</td>
			    			<td id="GOOD_COMMENT_CONTENT"></td>
			    		</tr>
			    		<tr>
			    			<td>需要旺旺聊天:</td>
			    			<td id="NEED_WANGWANG_TALK"></td>
			    		</tr>
			    		<tr>
			    			<td>限制重复接任务:</td>
			    			<td id="NO_REPEAT_TASK"></td>
			    		</tr>
			    		<tr>
			    			<td>指定接手人:</td>
			    			<td id="ZHI_DING_JIE_SHOU_REN"></td>
			    		</tr>
			    		<tr>
			    			<td>指定收货地址:</td>
			    			<td id="ZHI_DING_SHOU_HUO_DI_ZHI"></td>
			    		</tr>
			    		<tr>
			    			<td>重复次数:</td>
			    			<td id="repeateTimes"></td>
			    		</tr>
			    		<tr>
			    			<td>状态:</td>
			    			<td id="fstatus"></td>
			    		</tr>
			    	</table>
			    </form>
			</div>
			<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
				<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#w').window('close')" style="width:80px">关闭</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var dopage = "${_adminUser_.btnMap.admin_oeder_dopage}"== "true"?true:false;
var detail = "${_adminUser_.btnMap.admin_order_detail}"== "true"?true:false;

$(function(){
			 
			$('#dg').datagrid({
			 	border:false,
				rownumbers:true,
				checkOnSelect:true,
				fitColumns:true,
				pagination:true,
				fit:true,
				pageSize:20,
				singleSelect:true,
				method:'POST',
			  	url:'<%=request.getContextPath() %>/admin/order/dopage?d='+new Date().getTime(),
			  	queryParams:{ },
			  	onBeforeLoad:function(param){
					param['pageno'] =  param['page']-1;
					param['pagesize']  = param['rows'];
					if(!dopage){
						alert("您没有查看权限");
						return false;
					 }
			  		return true ;
			  	},
			  	onDblClickRow:function(rowIndex, rowData){
			  			//updateRowIndex = rowIndex;
						//getRoleInfoById(rowData['roleId']);	
			  	},
				columns:[[ 
					{field:'id',title:'id',width:100},
					{field:'userId',title:'创建者ID' ,width:80},
					{field:'createTime',title:'创建时间',width:170,formatter:dateFormat},
					{field:'taobaoXiaohao',title:'订单<br>淘宝号',width:150 },
					{field:'userQq',title:'订单QQ号',width:100 },
					//{field:'productTitle',title:'商品标题',width:100 },
					{field:'guaranteePrice',title:'担保金',width:50 },
					{field:'basicReceiverGainMoney',title:'佣金',width:50 },
					{field:'encourage',title:'奖励',width:50 },
					{field:'repeateTimes',title:'重复',width:50 },
					{field:'status',title:'状态' ,width:100,formatter:statusFormat}
					//,{field:'opt',title:'操作' ,width:100,formatter:optFormat} 
				]],
				toolbar: '#tb',
				loadFilter:function(data){
					var result = data.result;
					if(!result){
						return {total:0,rows:[]};
					}else {
						var obj = {
							total:data.entity.totalRows,
							rows:data.entity.pageData==null?[]:data.entity.pageData
						};
						return obj;
					} 
				}
			});
			var pager = $('#dg').datagrid("getPager");
			pager.pagination({
				'onBeforeRefresh':function(){
					return true;
				}
			}); 
});
function statusFormat(value,row,index){
	if(value == 'WAIT_FOR_SURE') {
		return "等待确认";
	}else if(value == 'SURE')  {
		return "已确认";
	}else if(value == 'CANCEL')  {
		return "已取消";
	} 
	return "";	
}
function dateFormat(value,row,index){
	if(typeof value == 'undefined'){
		return value;
	}else if(typeof value == 'number'){
		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
	}
}
function optFormat(value,row,index){
	var btns = [];
	 if(detail){
		btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="detailFn(\''+row['id']+'\')" href="#" plain="true" iconCls="update_btn"><span class="l-btn-left"><span class="l-btn-text update_btn l-btn-icon-left">任务详情</span></span></a>');
 	}
	return btns.join("&nbsp;");
}
 
 
function searchFn(){
	var userId = $("#userId").val();  
	var beginTime = $("#beginTime").datebox('getValue');
	var endTime = $("#endTime").datebox('getValue');
	
	var taobao = $("#taobao").val();
	var qq = $("#qq").val(); 
	
	var status = $('#status').combobox('getValue');
	
	 
	$('#dg').datagrid('load',{
		userId:userId,
		beginTime:begintimeFormat(beginTime),
		endTime:endtimeFormat(endTime),
		taobao:taobao,
		qq:qq,
		
		status:status, 
		d:new Date().getTime()
	});
}
 
 function endtimeFormat(v){
	 if($.trim(v).length == 0)return "";
	 return v+" 23:59:59";
 }
 function begintimeFormat(v){
	 if($.trim(v).length == 0)return "";
	 return v+" 00:00:00";
 }
 function detailFn(id){
	 $.ajax({ 
			url: "<%=request.getContextPath() %>/admin/order/detail?d="+new Date().getTime(), 
			data:{id:id},
			context: document.body,
			 beforeSend:function(){
				  jQuery.showMask(document.body,"正在努力加载数据中 ....");
				 },
			success: function(json){
		    	//$(this).addClass("done");
		    	 jQuery.hideMask(document.body);
		    	if(json.result){
		    		var  entity = json.entity;
		    		
		    		$("#taobaoXiaohao").html(entity.taobaoXiaohao);
		    		$("#userQq").html( entity.userQq);
		    		$("#createTime").html(dateFormat(entity.createTime));
		    		
		    		$("#productTitle").html( entity.productTitle);
		    		$("#productLink").html( entity.productLink);
		    		$("#guaranteePrice").html( entity.guaranteePrice);
		    		
		    		$("#basicReceiverGainMoney").html( entity.basicReceiverGainMoney);
		    		$("#encourage").html( entity.encourage);
		    		$("#basicPingtaiGainPoint").html( entity.basicPingtaiGainPoint);
		    		 
		    		$("#fstatus").html(statusFormat(entity.status));
		    		$("#repeateTimes").html(statusFormat(entity.repeateTimes))
		    		
		    		
		    		var sub = {};
		    		var dbSubs = entity.taskOrderSubTaskInfos;
		    		if(dbSubs != null ){
		    			for(var i=0,l = dbSubs.length;i<l;i++){
		    				var item = dbSubs[i];
		    				sub[item.taskKey] = item;
		    			}
		    		}
		    		if(sub['GOOD_COMMENT_TIME_LIMIT']){
		    			$("#GOOD_COMMENT_TIME_LIMIT").html(goodLimitFormat(sub['GOOD_COMMENT_TIME_LIMIT']['inputValue']));
		    		}
		    		if(sub['GOOD_COMMENT_CONTENT']){
		    			$("#GOOD_COMMENT_CONTENT").html(goodLimitFormat(sub['GOOD_COMMENT_CONTENT']['inputValue']));
		    		}
		    		if(sub['NEED_WANGWANG_TALK']){
		    			$("#NEED_WANGWANG_TALK").html("需要");
		    		}else{
		    			$("#NEED_WANGWANG_TALK").html("不需要");
		    		}
		    		if(sub['NO_REPEAT_TASK']){
		    			$("#NO_REPEAT_TASK").html("需要");
		    		}else{
		    			$("#NO_REPEAT_TASK").html("不需要");
		    		}
		    		if(sub['ZHI_DING_JIE_SHOU_REN']){
		    			$("#ZHI_DING_JIE_SHOU_REN").html("需要;接手人ID："+sub['ZHI_DING_JIE_SHOU_REN']['inputValue']);
		    		}else{
		    			$("#ZHI_DING_JIE_SHOU_REN").html("不需要");
		    		}
		    		if(sub['ZHI_DING_SHOU_HUO_DI_ZHI']){
		    			$("#ZHI_DING_SHOU_HUO_DI_ZHI").html("需要;收货地址："+sub['ZHI_DING_SHOU_HUO_DI_ZHI']['inputValue']);
		    		}else{
		    			$("#ZHI_DING_SHOU_HUO_DI_ZHI").html("不需要");
		    		}
		    		 
		    		
		    		$('#w').window('open');
				 	 
		    	}else{
		    		 $.messager.alert('系统提示','加载数据失败!  ' ,'error');
		    	}
			},
			error:function (XMLHttpRequest, textStatus, errorThrown) {
				 jQuery.hideMask(document.body);
				var msg = XMLHttpRequest.responseText;
				
					var json =   $.parseJSON(msg);
					 $.messager.alert('系统提示','保存失败，'+json.msg,'error');
				
			    // 通常 textStatus 和 errorThrown 之中
			    // 只有一个会包含信息
			    this; // 调用本次AJAX请求时传递的options参数
			   
			}
		});
 }
 function goodLimitFormat(v){
	 if(!v)return "";
	 if(v == "IMMEDIATELY"){
		 return "立刻好评";
	 }
	 if(v == "THIRTYMMinuteLater"){
		 return "30分钟后好评";
	 }
	 if(v == "ONE_DAY_LATER"){
		 return "1天后好评";
	 }
	 if(v == "TWO_DAY_LATER"){
		 return "2天后好评";
	 }
	 if(v == "THREE_DAY_LATER"){
		 return "3天后好评";
	 }
	 if(v == "FOURE_DAY_LATER"){
		 return "4天后好评";
	 }
	 if(v == "FIVE_DAY_LATER"){
		 return "5天后好评";
	 }
	 if(v == "SIX_DAY_LATER"){
		 return "6天后好评";
	 }
	 if(v == "SEVEN_DAY_LATER"){
		 return "7天后好评";
	 }
	 
 }
 function userTypeFormat(v){
	 if(v == "RECEIVER"){
		 return "接单人";
	 }
	 if(v == "CREATER"){
		 return "创建者";
	 }
	 if(v == "ADMINER"){
		 return "管理员";
	 }
 }
  

	</script>
</html>