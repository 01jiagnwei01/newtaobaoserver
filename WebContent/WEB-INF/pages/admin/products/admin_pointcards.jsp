<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals"%>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平台操作点卡管理 </title>
<%@include file="../../common/easyui-html5.jsp" %>
</head>
<body class="easyui-layout">
	 <div data-options="region:'center'">
		<table id="dg"></table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<table width="100%">
			<tr width="100%">
				<td width="50%">
					 
				</td>
				<td align="right" width="50%">
					<a href="#"  class="easyui-linkbutton" id="addBtn"  data-options="iconCls:'add_btn',plain:true" onclick="addFn()">增加</a>
				</td>
			</tr>
		</table>
	</div>
	<div id="adminuser_w" class="easyui-window" title="系统管理员" data-options="modal:true,closed:true,iconCls:'icon-save',
		collapsible:false,minimizable:false,maximizable:false,resizable:false" 
			style="width:600px;height:300px;padding:10px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',border:false" id="content_layout" >
				<form id="ff" method="post">
					 	<table>
					 		<tr style="display: none;">
				    			<td>id:</td>
				    			<td><input class="easyui-validatebox" type="text" id="id" name="id" data-options="required:false"></input></td>
				    		</tr>
				    		<tr>
				    			<td>标题:</td>
				    			<td>
				    				<input style="width:300px;" class="easyui-textbox" type="text" id="title" name="title" data-options="required:true"></input>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>金额:</td>
				    			<td>
				    				<input style="width:300px;" id="money"  type="text" class="easyui-numberbox" value="100" data-options="min:0,precision:0">
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>点数:</td>
				    			<td>
				    				<input style="width:300px;" id="points"  type="text" class="easyui-numberbox" value="100" data-options="min:0,precision:0">
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>顺序:</td>
				    			<td>
				    				<input style="width:300px;" id="orders"  type="text" class="easyui-numberbox" value="100" data-options="min:0,precision:0">
				    			</td>
				    		</tr>
				    		 
				    		 
				    	</table>
				 </form>
			 </div>
			 <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
					<a class="easyui-linkbutton" id="submitBtn" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:submitFormFn()">保存</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:closeWinFn()">取消</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var addBtn = "${_adminUser_.btnMap.adminProductsPoint_cardDoadd}"== "true"?true:false;
var updateBtn = "${_adminUser_.btnMap.adminProductsPoint_cardDoupdate}"== "true"?true:false;
var dodelBtn = "${_adminUser_.btnMap.adminProductsPoint_cardDodel}"== "true"?true:false;
var dopageBtn = "${_adminUser_.btnMap.adminProductsPoint_cardDopage}"== "true"?true:false; 


$(function(){
	if(!addBtn){
		$("#addBtn").hide();
	}
			$('#dg').datagrid({
			 	border:false,
				rownumbers:true,
				checkOnSelect:true,
				fitColumns:true,
				pagination:true,
				fit:true,
				pageSize:20,
				singleSelect:true,
				method:'get',
			  	url:'<%=request.getContextPath() %>/admin/products/point_card/dopage?d='+new Date().getTime(),
			  	queryParams:{ },
			  	onBeforeLoad:function(param){
					param['pageno'] =  param['page']-1;
					param['pagesize']  = param['rows'];
					if(!dopageBtn){
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
					{field:'title',title:'标题' ,width:200},
					{field:'money',title:'金额',width:100},
					{field:'points',title:'点数',width:100,},
					{field:'orders',title:'顺序',width:100},
					{field:'opt',title:'操作' ,width:100,formatter:optFormat} 
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
function statusFormat(value,row,index){
	if(value == 1) {
		return "启用";
	}else if(value == 2) {
		return "锁定";
	}
	return "";	
}
function searchFn(){
		 
		 
}
var saveType = "add";
function closeWinFn(){
			$('#w').window('close');
}
function addFn(){
	saveType = "add";
	 $("#ff").form("reset");
	 $('#adminuser_w').window('open').panel('setTitle',"增加销售点卡");
}
function optFormat(v,row,index){
	var btns = [];
	if(updateBtn){
		btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="updateFn(\''+row['id']+'\')" href="#" plain="true" iconCls="update_btn"><span class="l-btn-left"><span class="l-btn-text update_btn l-btn-icon-left">修改</span></span></a>');
	}
	if(dodelBtn){
		btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="delFn(\''+row['id']+'\')" href="#" plain="true" iconCls="del_btn"><span class="l-btn-left"><span class="l-btn-text del_btn l-btn-icon-left">删除</span></span></a>');
	}
	 
	return btns.join("&nbsp;");
}

function updateFn(id){
	$("#ff").form("reset");
	updateRowIndex = -1;
	saveType = "update";
	var rows = $("#dg").datagrid("getRows");
	var row = null;
	for(var i=0;i<rows.length;i++){
		if(rows[i]['id'] == id){
			row = rows[i];
			break;
		}
	}
	var rowIndex = 	$('#dg').datagrid("getRowIndex",row);
	updateRowIndex = rowIndex;
	
	
	
	$("#id").val(row['id']);
	 $('#title').textbox('setValue',row['title']);
	$('#money').numberbox('setValue', row['money']);
	$('#points').numberbox('setValue', row['points']);
	$('#orders').numberbox('setValue', row['orders']);
	 
	
	$('#adminuser_w').window('open').panel('setTitle',"修改销售点卡") ;
	$('#adminuser_w').window('center');
	
	
	
}
function closeWinFn(){
	 $('#adminuser_w').window('close');
}
function delFn(id){
	var rows = $("#dg").datagrid("getRows");
	var row = null;
	for(var i=0;i<rows.length;i++){
		if(rows[i]['id'] == id){
			row = rows[i];
			break;
		}
	}
	var delRowIndex = 	$('#dg').datagrid("getRowIndex",row);
	 $.messager.confirm('系统提示', '您确定要删除这条记录吗?', function(r){
		if (r){
				var url = "<%=request.getContextPath() %>/admin/products/point_card/dodel";
		  		 $.ajax({
		  	  	  type:'post',
				  url: url,
				  data:{
					  cardId:id
				  },
				  context: document.body,
				  beforeSend:function(){
					   
						  jQuery.showMask($(document.body),"正在提交中 ....");
					  
				 },
				  success:function(json){
					  jQuery.hideMask($(document.body));
				 	$('#dg').datagrid("deleteRow",delRowIndex);
			  		$('#dg').datagrid('acceptChanges');
				 	 $.messager.alert('系统提示','删除成功!','info',closeWinFn);
					
				  },
				  error:function(xhr,textStatus,errorThrown){
					  jQuery.hideMask($(document.body));
				  		var responseText = xhr.responseText;
				    	$.messager.alert('系统提示','删除失败，请刷新后重试!','error');
				  
				  } 
			});
		}}
	);
}			 
function submitFormFn(){
	var u_id = $("#id").val();
	var title = $('#title').textbox('getValue');
	var money = $('#money').numberbox('getValue');
	var orders = $('#orders').numberbox('getValue');
	var points = $("#points").numberbox('getValue');
	var saveObj = {};
	saveObj.id=$.trim(u_id).length==0?0:u_id;
	saveObj.title= title;
	saveObj.points=points;
	saveObj.money = money; 
	saveObj.orders = orders; 
	if(saveType == 'add'){ insertIntoDb(saveObj); }else if(saveType == 'update'){ updateIntoDb(saveObj); }
}
function insertIntoDb(saveObj){
			var url =  "<%=request.getContextPath()%>/admin/products/point_card/doadd";
		  	 $.ajax({
		  	  	  type:'post',
				  url: url,
				  context: document.body,
				  data:saveObj,
				  beforeSend:function(){
					  	$("#submitBtn").linkbutton('disable');
						  jQuery.showMask($("#adminuser_w")[0],"正在保存中 ....");
					  
				 }, 
				  success:function(json){
				   	 //json = $.parseJSON(json);
				   	 json = json.entity;
				   	$("#submitBtn").linkbutton('enable');
					  jQuery.hideMask($("#adminuser_w")[0]);
					  
				 	 $('#dg').datagrid('appendRow',json);
				 	 $.messager.alert('系统提示','保存成功!','info',closeWinFn);
				  },
				  error:function(xhr,textStatus,errorThrown){
					  $("#submitBtn").linkbutton('enable');
					  jQuery.hideMask($("#adminuser_w")[0]);
					  
				  		var responseText = xhr.responseText;
				    	$.messager.alert('系统提示','保存失败，请刷新后重试!','error');
				    	
				    	
				  
				  } 
			});
}
function updateIntoDb(saveObj){
			var url =  "<%=request.getContextPath() %>/admin/products/point_card/doupdate";
		  	 $.ajax({
		  	  	  type:'post',
				  url: url,
				  context: document.body,
				  data:saveObj,
				  beforeSend:function(){
					  $("#submitBtn").linkbutton('disable');
					  jQuery.showMask($("#adminuser_w")[0],"正在保存中 ....");
				 },
				  success:function(json){
				   		//json = $.parseJSON(json);
				   		json = json.entity;
				   		$("#submitBtn").linkbutton('enable');
						  jQuery.hideMask($("#adminuser_w")[0]);
					  	 $('#dg').datagrid('updateRow',{
					  	 	index:updateRowIndex,
					  	 	row:json
					  	 });
					  	  $('#dg').datagrid('selectRow',updateRowIndex);
					 	 $.messager.alert('系统提示','保存成功!','info',closeWinFn);
					
				  },
				  error:function(xhr,textStatus,errorThrown){
				  		var responseText = xhr.responseText;
				  		$("#submitBtn").linkbutton('enable');
						  jQuery.hideMask($("#adminuser_w")[0]);
				    	$.messager.alert('系统提示','保存失败，请刷新后重试!','error');
				  
				  } 
			});
}
 
/***/

	</script>
</html>