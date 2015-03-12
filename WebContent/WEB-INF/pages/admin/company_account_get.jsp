<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals"%>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公司账务信息</title>
<%@include file="../common/easyui-html5.jsp" %>
</head>
<body class="easyui-layout">
	 <div data-options="region:'center'">
		<table id="dg"></table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<table style="width:100%">
			<tr style="width:100%">
				<td width="100%"  colspan="2">
					 
					创建日期从: <input class="easyui-datebox" style="width:160px" id="beginTime"/>
					到: <input class="easyui-datebox" style="width:160px" id="endTime"/>
					<a href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFn()">查看</a>
				</td>
					
			</tr>
			 
		</table>
	</div>
	  
</body>
<script type="text/javascript">
var dopage = "${_adminUser_.btnMap.admin_company_account_dopage}"== "true"?true:false; 

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
			  	url:'<%=request.getContextPath() %>/admin/company_account/dopage?d='+new Date().getTime(),
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
					
					{field:'createTime',title:'创建时间',width:170,formatter:dateFormat},
					{field:'sellPoint',title:'已售点数' ,width:80},
					{field:'sellPointsMoney',title:'售点收入金额',width:150 },
					{field:'getPoints',title:'服务获取点数',width:100 }, 
					{field:'depositMoney',title:'充值金额',width:100 },
					{field:'drawMoney',title:'客户取出金额',width:100 },
					{field:'reasonType',title:'变化原因',width:100 ,formatter:reasonFormat},
					{field:'refId',title:'关联表ID',width:100 }  
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
 function dateFormat(value,row,index){
	if(typeof value == 'undefined'){
		return value;
	}else if(typeof value == 'number'){
		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
	}
}
 function searchFn(){
	 
		var beginTime = $("#beginTime").datebox('getValue');
		var endTime = $("#endTime").datebox('getValue');
		
		 
		
		 
		$('#dg').datagrid('load',{
			 
			beginTime:begintimeFormat(beginTime),
			endTime:endtimeFormat(endTime),
			  
			d:new Date().getTime()
		});
	}
 function reasonFormat(v){
	 if("DRAW" == v){
		 return "取款";
	 }
	 if("DEPOSIT" == v){
		 return "充值";
	 }
	 if("ORDERSURE" == v){
		 return "订单确认";
	 }
	 if("SellPoint" == v){
		 return "卖点卡";
	 }
	 return v;
 }
 function endtimeFormat(v){
	 if($.trim(v).length == 0)return "";
	 return v+" 23:59:59";
 }
 function begintimeFormat(v){
	 if($.trim(v).length == 0)return "";
	 return v+" 00:00:00";
 }
  

	</script>
</html>