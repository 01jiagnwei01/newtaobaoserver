<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals"%>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邮箱内容管理 </title>
<%@include file="../../common/easyui-html5.jsp" %> 
<script  src="<%=request.getContextPath() %>/resources/ckeditor/ckeditor.js"></script> 
<script src="<%=request.getContextPath() %>/resources/ckeditor/adapters/jquery.js"></script>
  
<script type="text/javascript">
CKEDITOR.disableAutoInline = true;
var fenye = "${_adminUser_.btnMap.admin_mail_content_dopage}"== "true"?true:false;
var add = "${_adminUser_.btnMap.admin_mail_content_doadd}"== "true"?true:false;
var update = "${_adminUser_.btnMap.admin_mail_content_doupdate}"== "true"?true:false;
var del = "${_adminUser_.btnMap.admin_mail_content_dodel}"== "true"?true:false;
var detail = "${_adminUser_.btnMap.admin_mail_content_get}"== "true"?true:false;
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
		<table class="easyui-datagrid"   
					id ='dg' 
		           data-options="fit:true,fitColumns:true,border:false,rownumbers:true,checkOnSelect:true,singleSelect:true,pagination:true,
		           pageSize:20,url:'<%=request.getContextPath() %>/admin/mail/content/dopage?d='+new Date().getTime(),method:'POST'
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
								<th data-options="field:'templeteId',hidden:true"  >模板ID</th>
								<th data-options="field:'title',width:150"  >标题</th>
								<th data-options="field:'templeteName',formatter:temFormat,width:150"  >模板名称</th>
								<th data-options="field:'updateUserId',formatter:adminUserIdFormat,width:100"  >管理员</th>
								<th data-options="field:'updateTime',formatter:dateFormat,width:100"  >修改时间</th> 
								<th data-options="field:'opt',formatter:optFormat,width:150"  >操作</th> 
					</tr>
					
				</thead>
			</table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<table style="width:100%">
			<tr >
				<td width="50%">
					标题: <input class="easyui-textbox" style="width:160px" id="title"/>
					<a href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFn()">查看</a>
				</td>
				<td align="right" width="50%">
					<a href="#"  class="easyui-linkbutton" id="addBtn"  data-options="iconCls:'add_btn',plain:true" onclick="addFn()">创建</a>
				</td>
				 
			</tr>
		</table>
	</div>
	 <div id="mail_w" class="easyui-window" title="窗口" data-options="modal:true,closed:true,iconCls:'icon-save',
		collapsible:false,minimizable:false,maximizable:false,resizable:false,onOpen:winOpen" 
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
				    			<td>标题:</td>
				    			<td>
				    				<input style="width:300px;" class="easyui-validatebox" type="text" id="form_title" name="title" data-options="required:true"></input>
				    			</td>
				    		</tr>
				    		<tr >
				    			<td>模板:</td>
				    			<td>
				    				 <input id="templeteid" name="templeteId" class="easyui-combogrid" style="width:200px;heigh:80px" data-options="
										panelWidth: 380,
										textField: 'templeteName',
										idField:'id',
										editable:false,
										mode: 'remote',
										fit:true,
										pageSize:20,
										url:'<%=request.getContextPath() %>/admin/mail/templete/dopage?d='+new Date().getTime(),
										method: 'post',
										columns: [[
											{field:'id',title:'id',width:80,hidden:true},
											{field:'templeteName',title:'名字',width:120} 
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
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>邮件内容(只读):</td>
				    			<td>
				    				<textarea id="content" name="content" ></textarea>
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
function winOpen(){
 
	
}
function searchFn(){
	var title = $("#title").val(); 
	$('#dg').datagrid('load',{
		title:title, 
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
function temFormat(value,row,index){
	return row["templeteName"]+"["+row['templeteId']+"]"
	
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
			$('#mail_w').window('close');
}
//获取页面的高度、宽度

function getPageSize() {

    var xScroll, yScroll;

    if (window.innerHeight && window.scrollMaxY) {

        xScroll = window.innerWidth + window.scrollMaxX;

        yScroll = window.innerHeight + window.scrollMaxY;

    } else {

        if (document.body.scrollHeight > document.body.offsetHeight) { // all but Explorer Mac    

            xScroll = document.body.scrollWidth;

            yScroll = document.body.scrollHeight;

        } else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari    

            xScroll = document.body.offsetWidth;

            yScroll = document.body.offsetHeight;

        }

    }

    var windowWidth, windowHeight;

    if (self.innerHeight) { // all except Explorer    

        if (document.documentElement.clientWidth) {

            windowWidth = document.documentElement.clientWidth;

        } else {

            windowWidth = self.innerWidth;

        }

        windowHeight = self.innerHeight;

    } else {

        if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode    

            windowWidth = document.documentElement.clientWidth;

            windowHeight = document.documentElement.clientHeight;

        } else {

            if (document.body) { // other Explorers    

                windowWidth = document.body.clientWidth;

                windowHeight = document.body.clientHeight;

            }

        }

    }       

    // for small pages with total height less then height of the viewport    

    if (yScroll < windowHeight) {

        pageHeight = windowHeight;

    } else {

        pageHeight = yScroll;

    }    

    // for small pages with total width less then width of the viewport    

    if (xScroll < windowWidth) {

        pageWidth = xScroll;

    } else {

        pageWidth = windowWidth;

    }

    //arrayPageSize = new Array(pageWidth, pageHeight, windowWidth, windowHeight);

    return {
    	"pageWidth":pageWidth,
    	"pageHeight":pageHeight,
    	"windowWidth":windowWidth,
    	"windowHeight":windowHeight
    	};

}
function initEditor(pageSize){
	 var w = pageSize.pageWidth;
	 var h = pageSize.pageHeight;
	 $( '#content' ).ckeditor(function( textarea ) {
	  },{
		  	width:(w-150),
		  	height :(h-100)
		 
	});	  
	
}
function addFn(){
	saveType = "add";
	 $("#ff").form("reset");
	 
	 /** */
	 var pageSize = getPageSize();
	 var w = pageSize.pageWidth;
	 var h = pageSize.pageHeight;
	
	 initEditor(pageSize);
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
	$("#form_title").val(row['title']);
	//$("#content").val(row['content']);
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
	var editor = CKEDITOR.instances.content;
	editor.setData( row['content'] );
	
	 $('#mail_w').window('resize', {
		  width:w,
		  height: (h-10)
	  });
	$('#mail_w').window('open').panel('setTitle',"修改邮件") ;
	$('#mail_w').window('center');
	$('#savebtn').show();
}
function submitFormFn(){
	var u_id = $("#id").val();
	var title = $("#form_title").val();
	var content = $("#content").val();
	var templeteid = $('#templeteid').combogrid('getValue');
	var templeteName = $('#templeteid').combogrid('getText'); 
	if(title.length == 0){
		 $.messager.alert('系统提示','标题不能为空!','info');
		 return;
	}
	if(templeteid == null ||  templeteid.length == 0 || templeteid == 0){
		 $.messager.alert('系统提示','请选择模板!','info');
		 return;
	}
	if(content.length == 0  ){
		 $.messager.alert('系统提示','内容不能为空!','info');
		 return;
	}
	var editor = CKEDITOR.instances.content;
	content = editor.getData() ;
 
	
	var saveObj = {};
	saveObj.id=$.trim(u_id).length==0?0:u_id;
	saveObj.templeteId= templeteid;
	saveObj.content=content;
	saveObj.title = title;
	 
	if(saveType == 'add'){ insertIntoDb(saveObj); }else if(saveType == 'update'){ updateIntoDb(saveObj); }
}
function insertIntoDb(saveObj){
	var url =  "<%=request.getContextPath()%>/admin/mail/content/doadd";
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
	var url =  "<%=request.getContextPath() %>/admin/mail/content/doupdate";
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
				var url = "<%=request.getContextPath() %>/admin/mail/content/dodel";
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