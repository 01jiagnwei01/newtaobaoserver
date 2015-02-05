<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals"%>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员管理 </title>
<%@include file="../../common/easyui-html5.jsp" %>
</head>
<body class="easyui-layout">
	 <div data-options="region:'center'">
		<table id="dg"></table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<table  >
			<tr  >
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
var addUserBtn = "${_adminUser_.btnMap.userdoadd}"== "true"?true:false;
var updateUserBtn = "${_adminUser_.btnMap.userdoupdate}"== "true"?true:false;
var userdodel = "${_adminUser_.btnMap.userdodel}"== "true"?true:false;
var userdopage = "${_adminUser_.btnMap.userdopage}"== "true"?true:false;
var usersetpassword = "${_adminUser_.btnMap.usersetpassword}"== "true"?true:false;


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
			  	url:'<%=request.getContextPath() %>/admin/log/dopage/COMPANY_SUPPLY?d='+new Date().getTime(),
			  	queryParams:{ },
			  	onBeforeLoad:function(param){
					param['pageno'] =  param['page']-1;
					param['pagesize']  = param['rows'];
					
			  		return true ;
			  	},
			  	onDblClickRow:function(rowIndex, rowData){
			  			//updateRowIndex = rowIndex;
						//getRoleInfoById(rowData['roleId']);	
			  	},
				columns:[[ 
					{field:'id',title:'id'},
					{field:'points',title:'点数' ,width:100} 
				]],
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
			});
			
			var pager = $('#dg').datagrid("getPager");
			pager.pagination({
				'onBeforeRefresh':function(){
					return true;
				}
			}); 
});
function searchFn(){
	var userId = $("#user_id").numberspinner('getValue'); 
	var admin_id = $("#admin_id").numberspinner('getValue');  
	var beginTime = $("#beginTime").datebox('getValue');
	var beginTime = $("#beginTime").datebox('getValue');
 
	$('#dg').datagrid('load',{
		userID:userId,
		adminId:admin_id,
		beginTime:beginTime,
		endTime:endTime,
			d:new Date().getTime()
	});
}

	</script>
</html>