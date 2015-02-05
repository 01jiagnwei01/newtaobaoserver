<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals"%>
 <%@ page import ="com.gxkj.taobaoservice.enums.MailAddressComeFrom" %>
  <%@ page import ="com.gxkj.taobaoservice.enums.MailAddressListStatus" %>
 <%@ page import ="com.gxkj.taobaoservice.enums.UserGender" %>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通讯录管理 </title>

<%@include file="../../common/easyui-html5.jsp" %> 
 
  
<script type="text/javascript">
var fenye = "${_adminUser_.btnMap.admin_mail_addresslist_dopage}"== "true"?true:false;
var add = "${_adminUser_.btnMap.admin_mail_addresslist_doadd}"== "true"?true:false;
var update = "${_adminUser_.btnMap.admin_mail_addresslist_doupdate}"== "true"?true:false;
var del = "${_adminUser_.btnMap.admin_mail_addresslist_dodel}"== "true"?true:false;
var detail = "${_adminUser_.btnMap.xx}"== "true"?true:false;
var typeMap = {};
var gendermap = {};
<%
MailAddressListStatus[] atitems = MailAddressListStatus.values();
for(MailAddressListStatus type: atitems){
%>
typeMap['<%=type.name()%>'] ='<%=type.getName()%>'
<%
}
%>
<%
UserGender[] genderitems = UserGender.values();
for(UserGender type: genderitems){
%>
gendermap['<%=type.name()%>'] ='<%=type.getName()%>'
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
		           pageSize:20,url:'<%=request.getContextPath() %>/admin/mail/addresslist/dopage?d='+new Date().getTime(),method:'POST'
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
								<th data-options="field:'name',width:150"  >姓名</th>
								<th data-options="field:'email',width:150"  >email</th>
								<th data-options="field:'status',width:100,formatter: function(value,row,index){if(value)return typeMap[value]}"  >状态</th>
								<th data-options="field:'gender',width:100,formatter: function(value,row,index){if(value)return gendermap[value]}"  >性别</th>
								
								<th data-options="field:'createUserId' ,width:100,formatter: userIdFormat"  >管理员</th>
								<th data-options="field:'creteTime',formatter:dateFormat,width:100"  >修改时间</th> 
								<th data-options="field:'opt',formatter:optFormat,width:150"  >操作</th> 
					</tr>
					
				</thead>
			</table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<table style="width:100%">
			<tr >
				<td width="80%">
					姓名: <input class="easyui-textbox" style="width:160px" id="s_name"/> 
					邮箱: <input class="easyui-textbox" style="width:160px" id="s_email"/>
					状态: <select id="s_status" class="easyui-combobox" name="s_status" data-options="editable:false,panelHeight:80,width:100" >
										 <option value="">不限</option> 
									     <%
									     MailAddressListStatus[] aitems = MailAddressListStatus.values();
													for(MailAddressListStatus item: aitems){
													%>
													 <option value="<%=item.name()%>"><%=item.getName()%></option>  
													<%
													}
													%>
									 </select>
					<a href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFn()">查看</a>
				</td>
				<td align="right" width="20%">
					<a href="#"  class="easyui-linkbutton" id="addBtn"  data-options="iconCls:'add_btn',plain:true" onclick="addFn()">创建</a>
				</td>
				 
			</tr>
		</table>
	</div>
	 <div id="mail_w" class="easyui-window" title="窗口" data-options="modal:true,closed:true,iconCls:'icon-save',
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
				    			<td>姓名:</td>
				    			<td>
				    				<input style="width:300px;" class="easyui-validatebox" type="text" id="form_name" name="name" data-options="required:true"></input>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>邮箱:</td>
				    			<td>
				    				<input style="width:300px;" class="easyui-validatebox" type="text" id="form_email" name="email" data-options="required:true"></input>
				    			</td>
				    		</tr>
				    		<tr >
				    			<td>性别:</td>
				    			<td>
									 <select id="form_gender" class="easyui-combobox" name="gender" style="width:200px;" data-options="editable:false,panelHeight:60">
									     <%
									     
													for(UserGender item: genderitems){
													%>
													 <option value="<%=item.name()%>"><%=item.getName()%></option>  
													<%
													}
													%>
									 </select> 
				    			</td>
				    		</tr>
				    		<tr >
				    			<td>来源:</td>
				    			<td>
									 <select id="form_comefrom" class="easyui-combobox" name="comefrom" style="width:200px;" data-options="editable:false,panelHeight:60">
									     <%
									     		MailAddressComeFrom[] items = MailAddressComeFrom.values();
													for(MailAddressComeFrom item: items){
													%>
													 <option value="<%=item.name()%>"><%=item.getName()%></option>  
													<%
													}
													%>
									 </select> 
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
	var email = $("#s_email").val(); 
	var name = $("#s_name").val(); 
	var status = $('#s_status').combo('getValue');	
	$('#dg').datagrid('load',{
		email:email, 
		status:status,
		name:name, 
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
	 
	return row['createUserName']+"["+row['createUserId']+"]"
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
			$('#mail_w').window('close');
}

function addFn(){
	saveType = "add";
	 $("#ff").form("reset");
	 
	 /** */
	
	 var w = 500;
	 var h = 360;
	
	 $('#mail_w').window('resize', {
		  width:w,
		  height: (h-10)
	  });
	  $('#mail_w').window('center');
	  $('#mail_w').window('open').panel('setTitle',"创建邮件");
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
	$("#form_name").val(row['name']);
	$("#form_email").val(row['email']);
	$('#form_comefrom').combobox("setValue",row['comefrom']);
	$('#form_gender').combobox("setValue",row['gender']);
	
	$('#form_name').validatebox("validate");
	$('#form_email').validatebox("validate");
	 
	 
	 var w = 500;
	 var h = 360;
 
	
	 $('#mail_w').window('resize', {
		  width:w,
		  height: h
	  });
	$('#mail_w').window('open').panel('setTitle',"修改联系人") ;
	$('#mail_w').window('center');
	$('#savebtn').show();
}
function submitFormFn(){
	var u_id = $("#id").val();
	var name = $("#form_name").val();
	var email = $("#form_email").val();
	var gender =  $('#form_gender').combobox("getValue");
	 
	var  comefrom = $('#form_comefrom').combobox("getValue");
	if(name.length == 0){
		 $.messager.alert('系统提示','联系人姓名不能为空!','info');
		 return;
	}
	if(email.length == 0){
		 $.messager.alert('系统提示','联系人邮箱不能为空!','info');
		 return;
	}
	if(comefrom == null ||  comefrom.length == 0 ){
		 $.messager.alert('系统提示','请选择联系人来源!','info');
		 return;
	}
	
 
	
	var saveObj = {};
	saveObj.id=$.trim(u_id).length==0?0:u_id;
	saveObj.name= name;
	saveObj.email=email;
	saveObj.comefrom = comefrom;
	saveObj.gender = gender;
	 
	if(saveType == 'add'){ insertIntoDb(saveObj); }else if(saveType == 'update'){ updateIntoDb(saveObj); }
}
function insertIntoDb(saveObj){
	var url =  "<%=request.getContextPath()%>/admin/mail/addresslist/doadd";
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
		    			$.messager.alert('系统提示','保存失败,\r\n'+msgArray.join("\r\n"),'error');
		    		}else{
		    			$.messager.alert('系统提示','保存失败,\r\n'+msg,'error');
		    		}
		    	 
		    		
		    	}catch(error){
		    		alert(error);
		    		$.messager.alert('系统提示','保存失败，请刷新后重试!','error');
		    	}
		  
		  } 
	});
}
function updateIntoDb(saveObj){
	var url =  "<%=request.getContextPath() %>/admin/mail/addresslist/doupdate";
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
	var rowIndex = 	$('#dg').datagrid("getRowIndex",row);
	 
	 
	$("#id").val(row['id']);
	$("#form_title").val(row['title']);
	$("#content").val(row['content']);
	 if(row['templeteId']){
	 	var templeteId = row['templeteId'];
	 	var templeteName = row['templeteName'] == null ?"": row['templeteName'] ;
	 	 
	 	//var roleName = role.name;
	 	 $('#templeteid').combogrid('setValue', templeteId);
		 $('#templeteid').combogrid('setText', templeteName);
		  
	 }
	 
	 var pageSize = getPageSize();
	 var w = pageSize.pageWidth;
	 var h = pageSize.pageHeight;
	
	 initEditor(pageSize);
	 $('#mail_w').window('resize', {
		  width:w,
		  height: (h-10)
	  });
	
	 
	$('#mail_w').window('open').panel('setTitle',"查看邮件") ;
	$('#mail_w').window('center');
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
				var url = "<%=request.getContextPath() %>/admin/mail/addresslist/dodel";
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