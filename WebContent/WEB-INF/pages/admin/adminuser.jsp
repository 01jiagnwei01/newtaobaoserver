<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals"%>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员管理 </title>
<%@include file="../common/easyui-html5.jsp" %>
</head>
<body class="easyui-layout">
	 <div data-options="region:'center'">
		<table id="dg"></table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<table width="100%">
			<tr width="100%">
				<td width="50%">
					真实姓名: <input class="easyui-spinner" style="width:160px" id="s_name"/>
					<a href="#"  class="easyui-linkbutton" iconCls="icon-search" onclick="searchFn()">查看</a>
				</td>
				<td align="right" width="50%">
					<a href="#"  class="easyui-linkbutton" id="addBtn"  data-options="iconCls:'add_btn',plain:true" onclick="addFn()">增加</a>
				</td>
			</tr>
		</table>
	</div>
	<div id="adminuser_w" class="easyui-window" title="系统管理员" data-options="modal:true,closed:true,iconCls:'icon-save',
		collapsible:false,minimizable:false,maximizable:false,resizable:false" 
			style="width:600px;height:420px;padding:10px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',border:false" id="content_layout" >
				<form id="ff" method="post">
					 	<table>
					 		<tr style="display: none;">
				    			<td>id:</td>
				    			<td><input class="easyui-validatebox" type="text" id="id" name="id" data-options="required:false"></input></td>
				    		</tr>
				    		<tr>
				    			<td>登陆名称:</td>
				    			<td>
				    				<input style="width:200px;" class="easyui-validatebox" type="text" id="name" name="name" data-options="required:true"></input>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>真实姓名:</td>
				    			<td>
				    				<input style="width:200px;" class="easyui-validatebox" type="text" id="realName" name="realName" data-options="required:true"></input>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>是否锁定:</td>
				    			<td>
				    				<select class="easyui-combobox" id="status" name="status" style="width:200px;"   data-options="editable:false,panelHeight:'auto',required:true">
											<option value="1">启用</option>
											<option value="2">锁定</option> 
									</select>
				    			</td>
				    		</tr>
				    		<tr >
				    			<td>角色:</td>
				    			<td>
				    				 <input id="roleid" name="roleid" class="easyui-combogrid" style="width:200px;" data-options="
										panelWidth: 380,
										textField: 'name',
										idField:'id',
										editable:false,
										mode: 'remote',
										method:'post',
										fit:true,
										pageSize:20,
										url:'<%=request.getContextPath() %>/admin/role/dopage?status=1&d='+new Date().getTime(),
										method: 'post',
										columns: [[
											{field:'id',title:'id',width:80,hidden:true},
											{field:'name',title:'名字',width:120} 
										]],
										fitColumns: true,
										pagination:true,
										pageSize:20,
										onBeforeLoad:function(param){
											 param['pageno'] =  param['page']-1;
											param['limit']  = param['rows'];
									  		return true ;
									  	},
									  	loadFilter:function(data){
											if(data  == null)return null;
											data.total = data.totalRows;
											data.rows = data.pageData ?data.pageData:[];
											return data;
										}
									">
				    			</td>
				    		</tr>
				    	</table>
				 </form>
			 </div>
			 <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:submitFormFn()">保存</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:closeWinFn()">取消</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var addUserBtn = "${_adminUser_.btnMap.userdoadd}"== "true"?true:false;
var updateUserBtn = "${_adminUser_.btnMap.userdoupdate}"== "true"?true:false;
var userdodel = "${_adminUser_.btnMap.userdodel}"== "true"?true:false;
var userdopage = "${_adminUser_.btnMap.userdopage}"== "true"?true:false;
var usersetpassword = "${_adminUser_.btnMap.usersetpassword}"== "true"?true:false;


$(function(){
	if(!addUserBtn){
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
			  	url:'<%=request.getContextPath() %>/admin/user/dopage?d='+new Date().getTime(),
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
					{field:'name',title:'登录名' ,width:100},
					{field:'realName',title:'真实姓名',width:200},
					{field:'role',title:'角色',width:200,formatter:roleFormat},
					{field:'status',title:'状态' ,width:50,formatter:statusFormat},
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
		if(!userdopage){
			 $.messager.alert('系统提示','您没有权限访问!','info');
			return;
		}
		var s_name = $("#s_name").val();
		$('#dg').datagrid('load',{
				realname:s_name,
				d:new Date().getTime()
		});
}
var saveType = "add";
function closeWinFn(){
			$('#w').window('close');
}
function addFn(){
	saveType = "add";
	 $("#ff").form("reset");
	 $('#adminuser_w').window('open').panel('setTitle',"增加管理员");
}
function optFormat(v,row,index){
	var btns = [];
	if(updateUserBtn){
		btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="updateFn(\''+row['id']+'\')" href="#" plain="true" iconCls="update_btn"><span class="l-btn-left"><span class="l-btn-text update_btn l-btn-icon-left">修改</span></span></a>');
	}
	if(userdodel){
		btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="delFn(\''+row['id']+'\')" href="#" plain="true" iconCls="del_btn"><span class="l-btn-left"><span class="l-btn-text del_btn l-btn-icon-left">删除</span></span></a>');
	}
	if(usersetpassword){
		btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="passwordRest(\''+row['id']+'\')" href="#" plain="true" iconCls="lock"><span class="l-btn-left"><span class="l-btn-text lock l-btn-icon-left">密码初始化</span></span></a>');
	}
	return btns.join("&nbsp;");
}
function roleFormat(v,row,index){
	if(!v)return "";
	return v.name;
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
	$("#name").val(row['name']);
	$("#realName").val(row['realName']);
	
	
	$('#name').validatebox("validate");
	$('#realName').validatebox("validate");
	
	 $('#status').combobox('setValue', row['status']);
	 if(row['role']){
	 	var role = row['role'];
	 	var roleID =role.id;
	 	var roleName = role.name;
	 	 $('#roleid').combogrid('setValue', roleID);
		 $('#roleid').combogrid('setText', roleName);
	 }
	
	$('#adminuser_w').window('open').panel('setTitle',"修改管理员") ;
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
				var url = "<%=request.getContextPath() %>/admin/user/dodel";
		  		 $.ajax({
		  	  	  type:'post',
				  url: url,
				  data:{
				  	id:id,
				  	status:4
				  },
				  context: document.body,
				  success:function(json){
				 	$('#dg').datagrid("deleteRow",delRowIndex);
			  		$('#dg').datagrid('acceptChanges');
				 	 $.messager.alert('系统提示','删除成功!','info',closeWinFn);
					
				  },
				  error:function(xhr,textStatus,errorThrown){
				  		var responseText = xhr.responseText;
				    	$.messager.alert('系统提示','删除失败，请刷新后重试!','error');
				  
				  } 
			});
		}}
	);
}			 
function submitFormFn(){
	var u_id = $("#id").val();
	var u_name = $("#name").val();
	var realName = $("#realName").val();
	var status = $("#status").combobox("getValue");
	var roleid = $('#roleid').combogrid('getValue');
	var roleName = $('#roleid').combogrid('getText');
	var status = $('#status').combogrid('getValue');
	var saveObj = {};
	saveObj.id=$.trim(u_id).length==0?0:u_id;
	saveObj.name= u_name;
	saveObj.realName=realName;
	saveObj.roleid = roleid?roleid:0;
	saveObj.roleName = roleName; 
	saveObj.status=status;
	if(saveType == 'add'){ insertIntoDb(saveObj); }else if(saveType == 'update'){ updateIntoDb(saveObj); }
}
function insertIntoDb(saveObj){
			var url =  "<%=request.getContextPath()%>/admin/user/doadd";
		  	 $.ajax({
		  	  	  type:'post',
				  url: url,
				  context: document.body,
				  data:saveObj,
				  success:function(json){
				   	 //json = $.parseJSON(json);
				   	 json = json.entity;
				 	 $('#dg').datagrid('appendRow',json);
				 	 $.messager.alert('系统提示','保存成功!','info',closeWinFn);
				  },
				  error:function(xhr,textStatus,errorThrown){
				  		var responseText = xhr.responseText;
				  		 
				  		var obj = jQuery.parseJSON(responseText);
				  		
				  		 json = $.parseJSON(responseText);
				  		 var  errortype = json.errortype;
				  		if(errortype){
				  			$.messager.alert('系统提示','保存失败,'+json.msg,'error');
				  		 }else{
				  			$.messager.alert('系统提示','保存失败，请刷新后重试!','error');
				  		 }
				    	
				  
				  } 
			});
}
function updateIntoDb(saveObj){
			var url =  "<%=request.getContextPath() %>/admin/user/doupdate";
		  	 $.ajax({
		  	  	  type:'post',
				  url: url,
				  context: document.body,
				  data:saveObj,
				  success:function(json){
				   		//json = $.parseJSON(json);
				   		json = json.entity;
					  	 $('#dg').datagrid('updateRow',{
					  	 	index:updateRowIndex,
					  	 	row:json
					  	 });
					  	  $('#dg').datagrid('selectRow',updateRowIndex);
					 	 $.messager.alert('系统提示','保存成功!','info',closeWinFn);
					
				  },
				  error:function(xhr,textStatus,errorThrown){
					
				  		var responseText = xhr.responseText;
				  		json = $.parseJSON(responseText);
				  		 var  errortype = json.errortype;
				  		if(errortype){
				  			$.messager.alert('系统提示','保存失败,'+json.msg,'error');
				  		 }else{
				  			$.messager.alert('系统提示','保存失败，请刷新后重试!','error');
				  		 }
				  
				  } 
			});
}
var initPassword = "111111";
function passwordRest(id){
		var url = "<%=request.getContextPath()  %>/admin/user/setpassword";
  		 $.ajax({
  	  	  type:'get',
		  url: url,
		  context: document.body,
		  data:{
		  	d:new Date().getTime(),
		  	 id:id  ,
		  	 password:initPassword
		  },
		  success:function(json){
		 	 // json = eval("("+json+")");
		 	  
			 var success = json['result'];
			 if(success){
			 	 $.messager.alert('系统提示','密码重置成功!新密码为'+initPassword,'info',function(){ });
			 }else{
			 	$.messager.alert('系统提示','密码重置失败，请刷新后重试!','error');
			 }
		 	
			
		  },
		  error:function(xhr,textStatus,errorThrown){
		  		var responseText = xhr.responseText;
		    	$.messager.alert('系统提示','密码重置失败，请刷新后重试!','error');
		  
		  } 
	});
	}
/***/

	</script>
</html>