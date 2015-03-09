<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals"%>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>前台用户管理 </title>
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
					用户名: <input class="easyui-spinner" style="width:160px" id="userName"/>
					注册始日期: <input class="easyui-datebox" style="width:160px" id="regBeignTime"/>
					注册结束日期: <input class="easyui-datebox" style="width:160px" id="regEndTime"/>
					状态: <select id="status" class="easyui-combobox"   style="width:160px;"  data-options='panelHeight:90'>
						<option value="">不限</option>
						<option value="NORMAL">正常</option> 
						<option value="WAIT_FOR_ACTIVE">待审</option>
						<option value="LOCKED">锁定</option>
					</select> 
					<a href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFn()">查看</a>
				 </td>
					
			</tr>
			 
		</table>
	</div>
	 <div id="set_supply_win" class="easyui-window" title="设置赞助点数" style="width:400px;height:320px" 
	 	 data-options="modal:true,closed:true,iconCls:'icon-save',collapsible:false,minimizable:false,maximizable:false,resizable:false">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'center'" style="padding:30px 20px 50px 20px">
					<div style="margin-bottom:20px;">
							<div>赞助点数:</div>
							<input class="easyui-numberbox" style="width:80%" id="apply_amount">
							<input type="hidden" id="apply_user_id" value="">
					</div>
				</div>
				 <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:submitApplyFormFn()">保存</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:closeApplyWinFn()">取消</a>
				</div>
			</div>
	</div>
</body>
<script type="text/javascript">
var admin_siteuser_dopage = "${_adminUser_.btnMap.admin_siteuser_dopage}"== "true"?true:false;
 
var admin_siteuser_supplypoint = "${_adminUser_.btnMap.admin_siteuser_supplypoint}"== "true"?true:false;
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
			  	url:'<%=request.getContextPath() %>/admin/siteuser/dopage?d='+new Date().getTime(),
			  	queryParams:{ },
			  	onBeforeLoad:function(param){
					param['pageno'] =  param['page']-1;
					param['pagesize']  = param['rows'];
					if(!admin_siteuser_dopage){
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
					{field:'id',title:'id'},
					{field:'userName',title:'用户名' ,width:100},
					{field:'regTime',title:'注册时间',width:200,formatter:dateFormat},
					{field:'status',title:'状态' ,width:50,formatter:statusFormat},
					{field:'uerAccount.currentBalance',title:'当前金额' ,width:100,formatter:currentBalanceFormat},
					{field:'uerAccount.lockedBalance',title:'当前锁定金额' ,width:100,formatter:lockedBalanceFormat},
					{field:'uerAccount.currentRestPoints',title:'当前点数' ,width:100,formatter:currentRestPointsFormat},
					{field:'uerAccount.lockedPoints',title:'当前锁定点数' ,width:100,formatter:lockedPointsFormat},
					 
					{field:'opt',title:'操作' ,width:200,formatter:optFormat} 
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
	if(value == 'WAIT_FOR_ACTIVE') {
		return "待激活";
	}else if(value == 'NORMAL')  {
		return "正常";
	}else if(value == 'LOCKED')  {
		return "锁定";
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
	 if(admin_siteuser_supplypoint){
		//btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="setSupplyFn(\''+row['id']+'\')" href="#" plain="true" iconCls="update_btn"><span class="l-btn-left"><span class="l-btn-text update_btn l-btn-icon-left">赞助点数</span></span></a>');
 	}
	return btns.join("&nbsp;");
}
 
function currentBalanceFormat(value,row,index){
	var v = row['uerAccount']['currentBalance'];
	return v=='0'?"":v;
}
function lockedBalanceFormat(value,row,index){
	var v =row['uerAccount']['lockedBalance'];	
	return v=='0'?"":v;
}
function currentRestPointsFormat(value,row,index){
	var v = row['uerAccount']['currentRestPoints'];	
	return v=='0'?"":v;
}
function lockedPointsFormat(value,row,index){
	var v = row['uerAccount']['lockedPoints'];	
	return v=='0'?"":v;
}
function searchFn(){
	var userName = $("#userName").val();  
	var regBeignTime = $("#regBeignTime").datebox('getValue');
	var regEndTime = $("#regEndTime").datebox('getValue');
	var status = $('#status').combobox('getValue');
	
	 
	$('#dg').datagrid('load',{
		name:userName,
		regBeignTime:regBeignTime,
		regEndTime:regEndTime, 
		status:status, 
		d:new Date().getTime()
	});
}
function setSupplyFn(id){
	
	$('#apply_user_id').val(id);
	$('#apply_amount').numberbox('setValue', 0);

	$('#set_supply_win').window('open');  
	$('#set_supply_win').window('center');
	
}

function closeApplyWinFn(){
	$('#set_supply_win').window('close');
}
function submitApplyFormFn(){
	var id = $('#apply_user_id').val();
	var amount = $("#apply_amount").numberspinner('getValue');
	if(amount<0){
		$.messager.alert('系统提示','点数目不能小于0!','info');
		return;
	}else if(amount>50){
		$.messager.alert('系统提示','点数目不能大于50!','info');
		return;
	}
	var rows = $("#dg").datagrid("getRows");
	var row = null;
	for(var i=0;i<rows.length;i++){
		if(rows[i]['id'] == id){
			row = rows[i];
			break;
		}
	}
	var updateRowIndex = 	$('#dg').datagrid("getRowIndex",row);
	$.ajax({ 
		url: "<%=request.getContextPath() %>/admin/siteuser/supplypoint?d="+new Date().getTime(), 
		method:'POST',
		data:{userId:id , supplyPoint:amount},
		context: document.body, 
		success: function(json){
	    	//$(this).addClass("done");
	    	if(json.result){
	    		var  entity = json.entity;
			 	 $('#dg').datagrid('updateRow',{
			  	 	index:updateRowIndex,
			  	 	row:entity
			  	 });
			 	 $.messager.alert('系统提示','保存成功!','info',function(){$('#dg').datagrid('reload');closeApplyWinFn();});
	    	}else{
	    		 $.messager.alert('系统提示','保存失败!  '+json.msg,'error');
	    	}
		},
		error:function (XMLHttpRequest, textStatus, errorThrown) {
			var msg = XMLHttpRequest.responseText;
			
				var json =   $.parseJSON(msg);
				 $.messager.alert('系统提示','保存失败，'+json.msg,'error');
			
		    // 通常 textStatus 和 errorThrown 之中
		    // 只有一个会包含信息
		    this; // 调用本次AJAX请求时传递的options参数
		   
		}
	});
}
 



	</script>
</html>