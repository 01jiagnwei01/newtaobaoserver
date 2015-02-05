<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals"%>
  <%@ page import="com.gxkj.taobaoservice.enums.MailTempleteTypes"%>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邮箱模板管理 </title>
<%@include file="../../common/easyui-html5.jsp" %>
<script type="text/javascript">
var fenye = "${_adminUser_.btnMap.admin_mail_templete_dopage}"== "true"?true:false;
var add = "${_adminUser_.btnMap.admin_mail_templete_doadd}"== "true"?true:false;
var update = "${_adminUser_.btnMap.admin_mail_templete_doupdate}"== "true"?true:false;
var del = "${_adminUser_.btnMap.admin_mail_templete_dodel}"== "true"?true:false;
var detail = "${_adminUser_.btnMap.admin_mail_templete_get}"== "true"?true:false;
var typeMap = {};
<%
MailTempleteTypes[] types = MailTempleteTypes.values();
for(MailTempleteTypes type: types){
%>

typeMap['<%=type.name()%>'] ='<%=type.getString()%>'
<%
}
%>
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
		<table class="easyui-datagrid"   
					id ='dg' 
		           data-options="fit:true,fitColumns:true,border:false,rownumbers:true,checkOnSelect:true,singleSelect:true,pagination:true,
		           pageSize:20,url:'<%=request.getContextPath() %>/admin/mail/templete/dopage?d='+new Date().getTime(),method:'POST'
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
								<th data-options="field:'id',width:30" >id</th>
								<th data-options="field:'templeteName',width:150"  >名称</th>
								<th data-options="field:'templetePath',width:150"  >保存路径</th>
								<th data-options="field:'type',width:150,formatter: function(value,row,index){if(value)return typeMap[value]}
								"  >模板类型</th>
								<th data-options="field:'templeteDesc',width:150"  >模板描述</th>
								<th data-options="field:'opt',formatter:optFormat,width:150"  >操作</th> 
					</tr>
					
				</thead>
			</table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<table style="width:100%">
			<tr >
				<td width="50%">
					名称: <input class="easyui-spinner" style="width:160px" id="templeteName"/>
					<a href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFn()">查看</a>
				</td>
				<td align="right" width="50%">
					<a href="#"  class="easyui-linkbutton" id="addBtn"  data-options="iconCls:'add_btn',plain:true" onclick="addFn()">创建</a>
				</td>
				 
			</tr>
		</table>
	</div>
	 <div id="templete_w" class="easyui-window" title="创建模板" data-options="modal:true,closed:true,iconCls:'icon-save',
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
				    			<td>名称:</td>
				    			<td>
				    				<input style="width:300px;" class="easyui-validatebox" type="text" id="form_templeteName" name="templeteName" data-options="required:true"></input>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>类型:</td>
				    			<td>
				    				<select  style="width:300px;" id="type" class="easyui-combobox" name="type" style="width:200px;" data-options="panelHeight:40">
				    				<%
				    			 
				    				for(MailTempleteTypes type: types){
				    				%>
				    				<option value="<%=type.name()%>"><%=type.getString()%></option>
				    				<%
				    				}
				    				%>
				    				</select>
				    			
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>保存路径:</td>
				    			<td>
				    				<input style="width:300px;" class="easyui-validatebox" type="text" id="templetePath" name="templetePath" data-options="required:true"></input>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>描述:</td>
				    			<td>
				    				<input class="easyui-textbox" data-options="multiline:true" style="width:300px" id='templeteDesc'/>
				    			</td>
				    		</tr>
				    		 
				    		
				    	</table>
				 </form>
			 </div>
			 <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
					<a class="easyui-linkbutton" id="savebtn"  data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:submitFormFn()">保存</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:closeWinFn()">关闭</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

$(function(){
 
});
function searchFn(){
	var templeteName = $("#templeteName").val(); 
	$('#dg').datagrid('load',{
		templeteName:templeteName, 
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
	return row['updateUserName']+"["+row['updateUserId']+"]"
}
function optFormat(value,row,index){
	
	 
		var btns = [];
		if(detail){
			btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="getFn(\''+row['id']+'\')" href="#" plain="true" iconCls="green_btn"><span class="l-btn-left"><span class="l-btn-text green_btn l-btn-icon-left">查看</span></span></a>');
		}
		 if(update){
			btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="updateFn(\''+row['id']+'\')" href="#" plain="true" iconCls="update_btn"><span class="l-btn-left"><span class="l-btn-text update_btn l-btn-icon-left">修改</span></span></a>');
		 }
		 if(del){
				btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="delFn(\''+row['id']+'\')" href="#" plain="true" iconCls="del_btn"><span class="l-btn-left"><span class="l-btn-text del_btn l-btn-icon-left">删除</span></span></a>');
		 }
		return btns.join("&nbsp;");
	 
	
}
var saveType = "add";
function closeWinFn(){
			$('#templete_w').window('close');
}
function addFn(){
	saveType = "add";
	 $("#ff").form("reset");
	 $('#templete_w').window('open').panel('setTitle',"创建模板");
	 $('#savebtn').show();
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
	$("#form_templeteName").val(row['templeteName']);
	$("#templetePath").val(row['templetePath']);
	$("#type").val(row['type']);
	$("#templeteDesc").val(row['templeteDesc']);
	  
	$('#templete_w').window('open').panel('setTitle',"修改模板") ;
	$('#templete_w').window('center');
	$('#savebtn').show();
}
function submitFormFn(){
	var u_id = $("#id").val();
	var form_templeteName = $("#form_templeteName").val();
	var templetePath = $("#templetePath").val();
	var templeteDesc = $("#templeteDesc").val();
	var type = $("#type").val();
	 
	if(form_templeteName.length == 0){
		 $.messager.alert('系统提示','名称不能为空!','info');
		 return;
	}
	if(templetePath == null  ){
		 $.messager.alert('系统提示','路径不能为空!','info');
		 return;
	}
	 
	var saveObj = {};
	saveObj.id=$.trim(u_id).length==0?0:u_id;
	saveObj.templeteName= form_templeteName;
	saveObj.templetePath=templetePath; 
	saveObj.type = type;
	saveObj.templeteDesc = templeteDesc;
	 
	if(saveType == 'add'){ insertIntoDb(saveObj); }else if(saveType == 'update'){ updateIntoDb(saveObj); }
}
function insertIntoDb(saveObj){
	var url =  "<%=request.getContextPath()%>/admin/mail/templete/doadd";
  	 $.ajax({
  	  	  type:'post',
		  url: url,
		  context: document.body,
		  data:saveObj,
		  success:function(json){
		   
		   	 var result = json.result;
		   	 var entity = json.entity;
		   	 if(result){
		   		 $('#dg').datagrid('appendRow',entity);
			 	 $.messager.alert('系统提示','保存成功!','info',closeWinFn);
		   	 }else{
		   		$.messager.alert('系统提示','保存失败，请刷新后重试!','error');
		   	 }
		 	
		  },
		  error:function(xhr,textStatus,errorThrown){
		  		var responseText = xhr.responseText;
		    	try{
		    		var json = $.parseJSON(responseText);
		    		var msg = json.msg;
		    		if(typeof  msg == 'string' && msg.indexOf("errorMsg")>0 ){
		    			var msgArray = [];
		    			msg = eval("("+msg+")");
		    			for (var i=0;i<msg.length;i++){
		    				var d = msg[i] ;
		    				msgArray.push(d['errorMsg']);
		    			}
		    			$.messager.alert('系统提示','保存失败\r\n'+msgArray.join("\r\n"),'error');
		    		}else{
		    			$.messager.alert('系统提示','保存失败\r\n'+msg,'error');
		    		}
		    	 
		    		
		    	}catch(error){
		    		alert(error);
		    		$.messager.alert('系统提示','保存失败，请刷新后重试!','error');
		    	}
		  
		  } 
	});
}
function updateIntoDb(saveObj){
	var url =  "<%=request.getContextPath() %>/admin/mail/templete/doupdate";
  	 $.ajax({
  	  	  type:'post',
		  url: url,
		  context: document.body,
		  data:saveObj,
		  success:function(json){
			  //json = $.parseJSON(json);
			   	 var result = json.result;
		   	 	var entity = json.entity;
		   	 	if(result){
			   		$('#dg').datagrid('updateRow',{
				  	 	index:updateRowIndex,
				  	 	row:entity
				  	 });
				  	  $('#dg').datagrid('selectRow',updateRowIndex);
				 	 $.messager.alert('系统提示','保存成功!','info',closeWinFn);
			   	 }else{
			   		$.messager.alert('系统提示','保存失败，请刷新后重试!','error');
			   	 }
			   	
			  	 
			
		  },
		  error:function(xhr,textStatus,errorThrown){
			  var responseText = xhr.responseText;
		    	try{
		    		var json = $.parseJSON(responseText);
		    		var msg = json.msg;
		    		if(typeof  msg == 'string' && msg.indexOf("errorMsg")>0 ){
		    			var msgArray = [];
		    			msg = eval("("+msg+")");
		    			for (var i=0;i<msg.length;i++){
		    				var d = msg[i] ;
		    				msgArray.push(d['errorMsg']);
		    			}
		    			$.messager.alert('系统提示','保存失败\r\n'+msgArray.join("\r\n"),'error');
		    		}else{
		    			$.messager.alert('系统提示','保存失败\r\n'+msg,'error');
		    		}
		    	 
		    		
		    	}catch(error){
		    		alert(error);
		    		$.messager.alert('系统提示','保存失败，请刷新后重试!','error');
		    	}
		  
		  } 
	});
}
function getFn(id){
	$("#ff").form("reset");
	updateRowIndex = -1;
	saveType = "";
	var rows = $("#dg").datagrid("getRows");
	var row = null;
	for(var i=0;i<rows.length;i++){
		if(rows[i]['id'] == id){
			row = rows[i];
			break;
		}
	}
	 
	 
	$("#id").val(row['id']);
	$("#form_templeteName").val(row['templeteName']);
	$("#templetePath").val(row['templetePath']);
	$("#type").val(row['type']);
	$("#templeteDesc").val(row['templeteDesc']);
	$('#templete_w').window('open').panel('setTitle',"查看模板") ;
	$('#templete_w').window('center');
	$('#savebtn').hide();
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
				var url = "<%=request.getContextPath() %>/admin/mail/templete/dodel";
		  		 $.ajax({
		  	  	  type:'post',
				  url: url,
				  data:{
				  	id:id ,
				  	d:new Date().getTime()
				  },
				  context: document.body,
				  success:function(json){
					  
						 var result = json.result;
					   	 	var entity = json.entity;
					   	 	if(result){
					   	 		$('#dg').datagrid("deleteRow",delRowIndex);
					  			$('#dg').datagrid('acceptChanges');
						 		 $.messager.alert('系统提示','删除成功!','info',closeWinFn);
						   	 }else{
						   		$.messager.alert('系统提示','删除失败，请刷新后重试!','error');
						   	 }
					   	 	
				  },
				  error:function(xhr,textStatus,errorThrown){
					  var responseText = xhr.responseText;
				    	try{
				    		var json = $.parseJSON(responseText);
				    		var msg = json.msg;
				    		if(typeof  msg == 'string' && msg.indexOf("errorMsg")>0 ){
				    			var msgArray = [];
				    			msg = eval("("+msg+")");
				    			for (var i=0;i<msg.length;i++){
				    				var d = msg[i] ;
				    				msgArray.push(d['errorMsg']);
				    			}
				    			$.messager.alert('系统提示','删除失败\r\n'+msgArray.join("\r\n"),'error');
				    		}else{
				    			$.messager.alert('系统提示','删除失败\r\n'+msg,'error');
				    		}
				    	 
				    		
				    	}catch(error){
				    		alert(error);
				    		$.messager.alert('系统提示','删除失败，请刷新后重试!','error');
				    	}
				  
				  } 
			});
		}}
	);
}
	</script>
</html>