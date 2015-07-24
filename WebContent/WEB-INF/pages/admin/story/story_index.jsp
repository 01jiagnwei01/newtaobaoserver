<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals"%>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>故事管理 </title>
<%@include file="../../common/easyui-html5.jsp" %>
<script  src="<%=request.getContextPath() %>/resources/ckeditor/ckeditor.js">
</script> 
</head>
<body class="easyui-layout">
	 <div data-options="region:'center'">
		<table id="dg"></table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<table style="width: 100%">
			<tr >
				<td width="50%">
					标题: <input class="easyui-textbox" style="width:160px;height:30px;padding:12px" id="s_title"/>
					<a href="#"  class="easyui-linkbutton" iconCls="icon-search" onclick="searchFn()">查看</a>
				</td>
				<td align="right" width="50%">
					<a href="#"  class="easyui-linkbutton" id="addBtn"  data-options="iconCls:'add_btn',plain:true" onclick="addFn()">创建</a>
				</td>
			</tr>
		</table>
	</div>
	<div id="story_w" class="easyui-window" title="创建文章" data-options="modal:true,closed:true,iconCls:'icon-save',
		collapsible:false,minimizable:false,maximizable:false,resizable:false" 
			style="width:600px;height:420px;padding:10px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',border:false" id="content_layout" >
				<form id="ff" method="post">
					 <div class="easyui-tabs" style="height:420px;width: 100%" data-options="fit:true,plain:true">
					 	        <div title="基本信息" style="padding:5px">
						            <table>
								 		<tr style="display: none;">
							    			<td>id:</td>
							    			<td><input class="easyui-textbox" type="text" id="articleId" name="articleId" data-options="required:false"></input></td>
							    		</tr>
							    		<tr>
							    			<td>标题:</td>
							    			<td>
							    				<input style="width:600px;height:40px;padding:12px"  class="easyui-textbox" type="text" id="articleTitle" name="articleTitle" data-options="width:200"></input>
							    			</td>
							    		</tr>
							    		<tr>
							    			<td>发生时间:</td>
							    			<td>
							    				<input style="width:600px;height:40px;padding:12px"  class="easyui-textbox"   id="storyTime" name="storyTime" 
							    				data-options="prompt:'例如：远古时期' "></input>
							    			</td>
							    		</tr>
							    		<tr>
							    			<td>出处:</td>
							    			<td>
							    				<input class="easyui-textbox" style="width:600px;height:40px;padding:12px" 
							    				id="fromBookName" name="fromBookName"
							    				data-options="prompt:'出处' ">
							    				 </td>
							    		</tr>
							    		<tr>
							    			<td>简介:</td>
							    			<td>
							    				<input style="width:600px;height:100px;padding:12px"  class="easyui-textbox" type="text" id="storySummary" name="storySummary" data-options="multiline:true"></input>
							    			</td>
							    		</tr>
							    	</table>
						        </div>
						        <div title="故事内容" style="padding:5px">
						            <textarea rows="1" cols="1" name="editor01"></textarea>
						        </div>
					 </div>
				</form>
			</div>
			<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
					<a class="easyui-linkbutton" id ="okBtn"  data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:submitFormFn()">保存</a>
					<a class="easyui-linkbutton" id ="cancelBtn" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:closeWinFn()">取消</a>
			</div>
		</div>
	</div>
	 
</body>
<script type="text/javascript">
var addStoryBtn = "${_adminUser_.btnMap.admin_MYTH_doadd}"== "true"?true:false;
var updateStroyBtn = "${_adminUser_.btnMap.admin_MYTH_doupdate}"== "true"?true:false;
var storydopage = "${_adminUser_.btnMap.admin_MYTH_dopage}"== "true"?true:false;
var setStatusBtn = "${_adminUser_.btnMap.admin_MYTH_setstatus}"== "true"?true:false;
var detailBtn = "${_adminUser_.btnMap.admin_MYTH_detail}"== "true"?true:false;
var deleteBtn = "${_adminUser_.btnMap.admin_MYTH_delete}"== "true"?true:false;
var p = getPageArea();
var pwidth = p.width;
var pheight = p.height;
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
				method:'get',
			  	url:'<%=request.getContextPath() %>/admin/MYTH/dopage?d='+new Date().getTime(),
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
					{field:'articleId',title:'Id'},
					{field:'articleTitle',title:'标题' ,width:100},
					{field:'fromBookName',title:'出处' ,width:100},
					{field:'hitTimes',title:'浏览数',width:100},
					{field:'praiseNumber',title:'点赞数',width:100 },
					{field:'tiresomeNumber',title:'脚踩数' ,width:100 },
					{field:'status',title:'状态' ,width:100 ,formatter:statusFormat},
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
	if(value == 'WAIT4REVIEW') {
		return "<font color='red'>待审核</font>";
	}else if(value == 'NORMAL') {
		return "审核通过，已上线";
	}else if(value == 'NOPASS') {
		return "审核未通过";
	}
	return "";	
}
function searchFn(){
		if(!storydopage){
			 $.messager.alert('系统提示','您没有权限访问!','info');
			return;
		}
		var s_title = $("#s_title").textbox('getValue');
		$('#dg').datagrid('load',{
				title:s_title,
				d:new Date().getTime()
		});
}
function optFormat (value,row,index){
	var btns = [];
	if(updateStroyBtn){
		btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="showDetail(\''+row['articleId']+'\',\'update\')" href="#" plain="true" iconCls="update_btn"><span class="l-btn-left"><span class="l-btn-text update_btn l-btn-icon-left">修改</span></span></a>');
	}
	if(detailBtn){
		btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="showDetail(\''+row['articleId']+'\',\'detail\')" href="###" plain="true" iconCls="detail_btn"><span class="l-btn-left"><span class="l-btn-text detail_btn l-btn-icon-left">详情</span></span></a>');
	}
	if(setStatusBtn){
		btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="setstatus(\''+row['articleId']+'\',\'pass\')" href="###" plain="true" iconCls="green_btn"><span class="l-btn-left"><span class="l-btn-text green_btn l-btn-icon-left">通过</span></span></a>');
		btns.push('<br/>');
		btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="setstatus(\''+row['articleId']+'\',\'nopass\')" href="###" plain="true" iconCls="red_btn"><span class="l-btn-left"><span class="l-btn-text red_btn l-btn-icon-left">不通过</span></span></a>');
		
	}
	if(deleteBtn)
	{
		btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="setstatus(\''+row['articleId']+'\',\'delete\')" href="###" plain="true" iconCls="del_btn"><span class="l-btn-left"><span class="l-btn-text del_btn l-btn-icon-left">删除</span></span></a>');
	}
	return btns.join("&nbsp;");
}
function addFn(){
	saveType = "add";
	$("#ff").form("reset");
	 $('#story_w').window('open').panel('setTitle',"创建文章").panel('resize',{width: pwidth,
			height: pheight});
	 $('#story_w').panel('move',{left:0,top:0});
	 initEditor();
	 setReadOnly(false)
	 CKEDITOR.instances.editor01.setData( null, {
		    callback: function() {
		     
		    }
		} );
}
function closeWinFn(){
	$('#story_w').window('close');
}
function initEditor()
{
	if(typeof CKEDITOR!='undefined' 
		   && typeof CKEDITOR.instances !='undefined'
			&&  typeof CKEDITOR.instances.editor01 =='undefined' )
		 CKEDITOR.replace( 'editor01', {
		    	toolbar : 'zixun',
		    	width:(pwidth-150),
			  	height :300
		    });
	
		
}
function submitFormFn(){
	var articleId = $("#articleId").val();
	var articleTitle = $("#articleTitle").val();
	var storyTime = $("#storyTime").val();
	var storySummary = $("#storySummary").val();
	var fromBookName = $('#fromBookName').val(); 
	var editor_data = CKEDITOR.instances.editor01.getData();
	var saveObj = {};
	saveObj.articleId=$.trim(articleId).length==0?0:articleId;
	saveObj.articleTitle= articleTitle;
	saveObj.storyTime=storyTime;
	saveObj.storySummary = storySummary;
	saveObj.fromBookName = fromBookName; 
	saveObj.articleContent = editor_data;
	if(saveType == 'add'){ insertIntoDb(saveObj); }else if(saveType == 'update'){ updateIntoDb(saveObj); }
}
function insertIntoDb(saveObj){
	var url =  "<%=request.getContextPath()%>/admin/MYTH/doadd";
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
	var url =  "<%=request.getContextPath()%>/admin/MYTH/doupdate";
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
			  	 // $('#dg').datagrid('selectRow',updateRowIndex);
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
function showDetail(id,type){
	$("#ff").form("reset");
	updateRowIndex = -1;
	var rows = $("#dg").datagrid("getRows");
	var row = null;
	for(var i=0;i<rows.length;i++){
		if(rows[i]['articleId'] == id){
			row = rows[i];
			break;
		}
	}
	var rowIndex = 	$('#dg').datagrid("getRowIndex",row);
	updateRowIndex = rowIndex;
	
	
	var url =  "<%=request.getContextPath()%>/admin/MYTH/detail";
 	 $.ajax({
		  url: url,
		  context: document.body,
		  data:{storyId:id},
		  success:function(json){
		   		//json = $.parseJSON(json);
		   		json = json.entity;
			  	afterAjaxShowWin(json,type);
			
		  },
		  error:function(xhr,textStatus,errorThrown){
			
		  		var responseText = xhr.responseText;
		  		json = $.parseJSON(responseText);
		  		 var  errortype = json.errortype;
		  		 
		  		$.messager.alert('系统提示','加载数据失败，请刷新后重试!','error');
		  		log.info(responseText);
		  		  
		  
		  } 
	});
}
function afterAjaxShowWin(dto,type){
	$("#ff").form("reset");
	 $("#articleId").textbox('setValue',dto.articleId);
	 $("#articleTitle").textbox('setValue',dto.articleTitle);
	 $("#storyTime").textbox('setValue',dto.storyTime);
	$("#storySummary").textbox('setValue',dto.storySummary);
	$('#fromBookName').textbox('setValue',dto.fromBookName);

	var content = dto.articleContent;
	initEditor();
	CKEDITOR.instances.editor01.setData( content, {
	    callback: function() {
	     
	    }
	} );
	var title = '';
	if(type == 'detail'){
		title = "文章详情";
	 }else if(type == 'update'){
		 saveType = "update";
		title = "修改文章";
	 }
	 $('#story_w').window('open').panel('setTitle',title).panel('resize',{width: pwidth,
			height: pheight});
	 $('#story_w').panel('move',{left:0,top:0});
	 //alert(type);
	 if(type == 'detail'){
		 setReadOnly(true);
	 }else{
		 setReadOnly(false);
	 }
	 
	 //
}
function setReadOnly(bool){
	if(bool){
		 $("#articleTitle").textbox('readonly');
		 $("#storyTime").textbox('readonly');
		 $("#storySummary").textbox('readonly');
		 $("#fromBookName").textbox('readonly');
		 
		 $("#okBtn").hide();
		
	}else{
		 $("#articleTitle").textbox('readonly',false);
		 $("#storyTime").textbox('readonly',false);
		 $("#storySummary").textbox('readonly',false);
		 $("#fromBookName").textbox('readonly',false);
		
		 $("#okBtn").show();
	}
}
function setstatus(id,type){
	var url = null;
	var param = null;
	var dRowIndex = -1;
	 
	var rows = $("#dg").datagrid("getRows");
	var row = null;
	for(var i=0;i<rows.length;i++){
		if(rows[i]['articleId'] == id){
			row = rows[i];
			break;
		}
	}
	var rowIndex = 	$('#dg').datagrid("getRowIndex",row);
	dRowIndex = rowIndex;
	
	if(type =='pass'){
		url = "<%=request.getContextPath()%>/admin/MYTH/setstatus";
		param = {storyId:id,status:'NORMAL'}
		row.status = 'NORMAL';
		deSetStatus(url,param,dRowIndex,row,type);
	}else if(type =='nopass'){
		url = "<%=request.getContextPath()%>/admin/MYTH/setstatus";
		param = {storyId:id,status:'NOPASS'}
		row.status = 'NOPASS';
		deSetStatus(url,param,dRowIndex,row,type);
	}else if(type =='delete'){
		url = "<%=request.getContextPath()%>/admin/MYTH/delete";
		param = {storyId:id,status:'DEL'}
		row.status = 'DEL';
		$.messager.confirm('系统提示', '您确定要删除该记录吗?', function(r){
			if (r){
				deSetStatus(url,param,dRowIndex,row,type)
			}
		});
			
		 
	} 
 	 
}
function deSetStatus(url,param,dRowIndex,row,type){
	$.ajax({
	  	  type:'post',
		  url: url,
		  context: document.body,
		  data:param,
		  success:function(json){
			  if(type =='delete'){
				  $('#dg').datagrid('deleteRow',dRowIndex);
				} else{
					 $('#dg').datagrid('updateRow',{
					  	 	index:dRowIndex,
					  	 	row:row
					  	 });
				}
			  	 // $('#dg').datagrid('selectRow',updateRowIndex);
			 	 $.messager.alert('系统提示','保存成功!','info');
			
		  },
		  error:function(xhr,textStatus,errorThrown){
			
		  		var responseText = xhr.responseText;
		  		json = $.parseJSON(responseText);
		  	
		  			$.messager.alert('系统提示','保存失败,'+responseText,'error');
		  } 
	});
}
 


	</script>
</html>