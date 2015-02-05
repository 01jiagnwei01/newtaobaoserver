<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals"%>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>赞助点数日志 </title>
<%@include file="../../common/easyui-html5.jsp" %>
<script type="text/javascript">
var fenye = "${_adminUser_.btnMap.admin_log_dopage_COMPANY_SUPPLY}"== "true"?true:false;
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
		<table class="easyui-datagrid"   
					id ='dg' 
		           data-options="fit:true,fitColumns:true,border:false,rownumbers:true,checkOnSelect:true,singleSelect:true,pagination:true,
		           pageSize:20,url:'<%=request.getContextPath() %>/admin/log/dopage/COMPANY_SUPPLY?d='+new Date().getTime(),method:'POST'
		           ,onBeforeLoad:function(param){
		           
				param['pageno'] =  param['page']-1;
				param['pagesize']  = param['rows'];
				if(!fenye){
					alert('您没有查看权限');
					return false;
				 }
		  		return true ;
		  	},
		  	toolbar: '#tb',
				loadFilter:function(data){
					 
					var result = data.result;
					
					if(!result){
						return {total:0,rows:[]};
					}else {
						var obj = {
							total:data.entity.totalRows,
							rows:data.entity.pageData ?data.entity.pageData:[]
						};
						return obj;
					} 
				}
		           ">
		        <thead> 
		        	<tr>
								<th data-options="field:'id',width:30" rowspan="2">id</th>
								<th data-options="field:'createTime',formatter:dateFormat,width:150" rowspan="2">操作时间</th>
								<th data-options="field:'userName',formatter:userIdFormat,width:120" rowspan="2">用户</th>
								<th data-options="field:'adminUserId',formatter:adminUserIdFormat,width:150" rowspan="2">管理员</th>
								<th data-options="field:'points'" rowspan="2">点数</th>
								<th colspan="4">操作前</th>
								<th colspan="4">操作后</th>
					</tr>
					<tr>
							<th data-options="field:'beforeRestAmount',width:80,align:'center',formatter:dataFormat">可用金额</th>
							<th data-options="field:'beforeRestPoints',width:80,align:'center',formatter:dataFormat">可用点数</th>
							<th data-options="field:'beforeLockedAmount',width:80,formatter:dataFormat">锁定金额</th>
							<th data-options="field:'beforeLockedPoints',width:60,align:'center',formatter:dataFormat">锁定点数</th>
							
							<th data-options="field:'afterRestAmount',width:80,align:'center',formatter:dataFormat">可用金额</th>
							<th data-options="field:'afterRestPoints',width:80,align:'center',formatter:dataFormat">可用点数</th>
							<th data-options="field:'afterLockedAmount',width:80,formatter:dataFormat">锁定金额</th>
							<th data-options="field:'afterLockedPoints',width:60,align:'center',formatter:dataFormat">锁定点数</th>
					 </tr>
				</thead>
			</table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<table width="100%">
			<tr width="100%">
				<td width="50%">
					用户ID: <input class="easyui-numberspinner" style="width:160px" id="user_id"/>
					管理员ID: <input class="easyui-numberspinner" style="width:160px" id="admin_id"/>
					操作时间日期:从 <input class="easyui-datebox" style="width:160px" id="beginTime"/>
					到<input class="easyui-datebox" style="width:160px" id="endTime"/>
					<a href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFn()">查看</a>
				</td>
				 
			</tr>
		</table>
	</div>
	 
</body>
<script type="text/javascript">

$(function(){
 
});
function searchFn(){
	  
	 
	 var userId = $("#user_id").numberspinner('getValue'); 
	 var admin_id = $("#admin_id").numberspinner('getValue');  
	 var beginTime = $("#beginTime").datebox('getValue');
	 var endTime = $("#endTime").datebox('getValue');
 
	$('#dg').datagrid('load',{
		 userID:userId,
		 adminId:admin_id,
	 	 beginTime:beginTime,
	 	 endTime:endTime,
			d:new Date().getTime()
	});
}
function dateFormat(value,row,index){
	if(typeof value == 'undefined'){
		return value;
	}else if(typeof value == 'number'){
		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
	}
}
function userIdFormat(value,row,index){
	if(typeof value == 'undefined'){
		return value;
	}
	 
	return row['userName']+"["+row['userId']+"]"
}
function adminUserIdFormat(value,row,index){
	if(typeof value == 'undefined'){
		return value;
	} 
	return row['adminName']+"["+row['adminUserId']+"]"
}
function dataFormat(value,row,index){
	if(!value )return "";
	return value;
}

	</script>
</html>