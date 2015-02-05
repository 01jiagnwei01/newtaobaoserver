<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.gxkj.common.util.SystemGlobals"%>
<%@ page import ="com.gxkj.taobaoservice.enums.MailSenderStatus" %>
<%@ page import ="com.gxkj.taobaoservice.enums.MailAddressComeFrom" %>
<%@ page import ="com.gxkj.taobaoservice.enums.MailAddressListStatus" %>
<%@ page import ="com.gxkj.taobaoservice.enums.UserGender" %>
<%@ page import ="com.gxkj.taobaoservice.enums.MailSenderRefAddressListStatus" %><!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邮件发送任务 </title>
<%@include file="../../common/easyui-html5.jsp" %>
<script  src="<%=request.getContextPath() %>/resources/ckeditor/ckeditor.js"></script> 
<script src="<%=request.getContextPath() %>/resources/ckeditor/adapters/jquery.js"></script>
<script src="<%=request.getContextPath() %>/resources/json3.min.js"></script> 
<script type="text/javascript">
var fenye = "${_adminUser_.btnMap.admin_mail_sendertask_dopage}"== "true"?true:false;
var add = "${_adminUser_.btnMap.admin_mail_sendertask_doadd}"== "true"?true:false;
var update = "${_adminUser_.btnMap.admin_mail_sendertask_doupdate}"== "true"?true:false;
var del = "${_adminUser_.btnMap.admin_mail_sendertask_dodel}"== "true"?true:false;
var sendMail = "${_adminUser_.btnMap.admin_mail_sendertask_dosend}"== "true"?true:false;
var taskDetail = "${_adminUser_.btnMap.admin_mail_sendertask_dotail}"== "true"?true:false;
var detail = "${_adminUser_.btnMap.admin_mail_sendertask_detailpage}"== "true"?true:false;

var typeMap = {}; 
var gendermap = {};
var addressTypemap = {};
var mailSendStatus = {};
<%
MailSenderStatus[] atitems = MailSenderStatus.values();
for(MailSenderStatus type: atitems){
%>
typeMap['<%=type.name()%>'] ='<%=type.getName()%>';
<%
}
%>
<%
MailAddressListStatus[] abtitems = MailAddressListStatus.values();
for(MailAddressListStatus type: abtitems){
%>
addressTypemap['<%=type.name()%>'] ='<%=type.getName()%>';
<%
}
%>
<%
UserGender[] genderitems = UserGender.values();
for(UserGender type: genderitems){
%>
gendermap['<%=type.name()%>'] ='<%=type.getName()%>';
<%
}
%>
<%
MailSenderRefAddressListStatus[] mailSendS = MailSenderRefAddressListStatus.values();
for(MailSenderRefAddressListStatus type: mailSendS){
%>
mailSendStatus['<%=type.name()%>'] ='<%=type.getName()%>';
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
		           pageSize:20,url:'<%=request.getContextPath() %>/admin/mail/sendertask/dopage?d='+new Date().getTime(),method:'POST'
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
								<th data-options="field:'title',width:150"  >标题</th> 
								<th data-options="field:'createUser',formatter:adminUserIdFormat,width:100"  >管理员</th>
								<th data-options="field:'createTime',formatter:dateFormat,width:100"  >修改时间</th>
								<th data-options="field:'status',width:100,formatter: function(value,row,index){if(value)return typeMap[value]}"  >状态</th> 
								<th data-options="field:'opt',formatter:optFormat,width:200"  >操作</th> 
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
		collapsible:false,minimizable:false,maximizable:false,resizable:false" 
			style="width:600px;height:420px;padding:10px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',border:false" id="content_layout" >
				<form id="ff" method="post" style="width:100%;height:100%;">
					 <div id="tt" class="easyui-tabs" data-options="border:false,fit:true,tabWidth:150" >
					     <div title="邮件内容" data-options="cache:false" style='padding-top: 20px;'>
					         <table>
					         <tr style="display: none;">
				    			<td>id:</td>
				    			<td><input class="easyui-validatebox" type="text" id="id" name="id" data-options="required:false"></input></td>
				    		</tr>
											<tr>
										    			<td>内容标题:</td>
										    			<td>
										    				 <input id="contentId" class="easyui-combogrid" style="width:250px" data-options="
										    				 	onSelect:comgridselect,
													            panelWidth: 500,
													            idField: 'id',
													            textField: 'title',
													            url: '<%=request.getContextPath() %>/admin/mail/content/dopage?d='+new Date().getTime(),
													            method: 'POST',
													            columns: [[
													                {field:'id',title:'Item ID',hidden:true},
													                {field:'title',title:'标题',width:120}
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
													        "/>
										    			</td>
										    </tr>
										    <tr>
										    	<td>模板(只读)</td>
										    	<td><input class="easyui-textbox" id="temp_id" readonly="readonly"  data-options="width:250" style="width:250px;height:32px"></td>
										    </tr>
										    <tr>
										    	<td>邮件内容(只读)</td>
										    	<td><textarea id="content" name="content"  readonly="readonly" ></textarea></td>
										    </tr>
										 </table>
					    </div>
					     <div title="收件人" data-options="cache:false,height:1500" style='padding: 20px;'>
					        
							 <div id="cc" class="easyui-layout" style="width:100%;height:100%;">
							     <div data-options="region:'center',title:'可选收件人'" style="padding:5px;background:#eee;">
							     	<table class="easyui-datagrid"   
												id ='tt_dg' 
									           data-options="striped:false,fit:true,fitColumns:true,border:false,rownumbers:true,checkOnSelect:true,
									           singleSelect:false,pagination:true,
									           idField:'id',
									           pageSize:20,
									           url:'<%=request.getContextPath() %>/admin/mail/addresslist/dopage?d='+new Date().getTime(),method:'POST'
									           ,onBeforeLoad:function(param){
									           
											param['pageno'] =  param['page']-1;
											param['pagesize']  = param['rows'];
											
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
											},onCheck:onCheckShouJianRen,
											onUncheck:onUncheckShouJianRen,
											onSelectAll:onSelectAllShouJianRen,
											onUnselectAll:onUnselectAllShouJianRen,
											onLoadSuccess:onLoadSuccessSelShouJianRen
											">
									        <thead> 
									        	<tr>
															<th data-options="field:'id',checkbox:true" >id</th>
															<th data-options="field:'name',width:150,formatter: uNameFormat"  >姓名</th>
															<th data-options="field:'email',width:150"  >email</th>
															<th data-options="field:'status',width:100,formatter: function(value,row,index){if(value)return addressTypemap[value]}"  >状态</th>
															<th data-options="field:'gender',width:100,formatter: function(value,row,index){if(value)return gendermap[value]}"  >性别</th>
															
												</tr>
												
											</thead>
										</table>
							     
							     
							     </div>
							     <div data-options="region:'south',title:'已选收件人',split:true,collapsible:false" style="height:100px;" id='showSeleAddress'>
							     
							     </div>
							 </div>
					    </div>
					 </div>

				
					
					 	 
				</form>
			 </div>
			 <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
					<a class="easyui-linkbutton" id="savebtn"  data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:submitFormFn()">保存</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:closeWinFn()">关闭</a>
			</div>
		</div>
	</div>
	
	 <div id="detail_w" class="easyui-window" title="窗口" data-options="modal:true,closed:true,iconCls:'icon-save',
		collapsible:false,minimizable:false,maximizable:false,resizable:false" 
			style="width:600px;height:420px;padding:10px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',border:false" id="detail_content_layout" >
					<table class="easyui-datagrid"   
							id ='detail_dg' 
				            data-options="fit:true,fitColumns:true,border:false,rownumbers:true,checkOnSelect:true,singleSelect:true,pagination:true,
		           pageSize:20,url:'<%=request.getContextPath() %>/admin/mail/sendertask/detailpage?d='+new Date().getTime(),method:'POST'
		           ,onBeforeLoad:function(param){
						param['pageno'] =  param['page']-1;
						param['pagesize']  = param['rows'];
						if(!param['taskId']){
							return false;
						}
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
				} ">
			        <thead> 
			        	<tr>
									<th data-options="field:'id',width:30,hidden:true" >id</th>
									<th data-options="field:'emailUserName',width:150"  >姓名</th> 
									<th data-options="field:'email',width:150"  >邮箱</th>
									<th data-options="field:'status',width:150,formatter:sendStatusFormat"  >发送状态</th>
									 
						</tr>
						
					</thead>
				</table>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

$(function(){
	 
});

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

function adminUserIdFormat(value,row,index){
	if(typeof value == 'undefined'){
		return value;
	} 
	return row['createUserName']+"["+row['createUser']+"]"
}
function optFormat(value,row,index){
		var btns = [];
		if(detail){
			btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="detailWinFn(\''+row['id']+'\')" href="#" plain="true" iconCls="detail_btn"><span class="l-btn-left"><span class="l-btn-text detail_btn l-btn-icon-left">查看收件人</span></span></a>');
		}
		if(row['status'] == 'NOSEND'){
			if(update){
				btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="updateFn(\''+row['id']+'\')" href="#" plain="true" iconCls="update_btn"><span class="l-btn-left"><span class="l-btn-text update_btn l-btn-icon-left">修改</span></span></a>');
			 }
			 if(del){
					btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="delFn(\''+row['id']+'\')" href="#" plain="true" iconCls="del_btn"><span class="l-btn-left"><span class="l-btn-text del_btn l-btn-icon-left">删除</span></span></a>');
			 }
			 if(sendMail){
					btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="sendMailFn(\''+row['id']+'\')" href="#" plain="true" iconCls="send_btn"><span class="l-btn-left"><span class="l-btn-text send_btn l-btn-icon-left">发送</span></span></a>');
			 }
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
		  contentEditable:false,
		  readOnly:true,
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
		  width: w,
		  height: (h-10)
	  });
	  $('#mail_w').window('center');
	  $('#mail_w').window('open').panel('setTitle',"创建发送任务");
	  
	selAddressIds = [];
	$('#showSeleAddress').html("");
	
	 $('#savebtn').show();
}
function updateFn(id){
	if(!taskDetail){
		$.messager.alert('系统提示','您需要有查看详情的权限才可以继续修改!','info');
		return;
	}
	var url =  "<%=request.getContextPath() %>/admin/mail/sendertask/get?d="+new Date().getTime();
 	$.ajax({
	  url: url,
	  method:'POST',
	  context: document.body,
	  beforeSend:function(){
		   
		  jQuery.showMask(document.body,"正在努力加载数据中 ....");
		 },
	  data:{
		  taskId:id
	  },
	  success:function(json){
	 	// var json_str = JSON2.stringify(json);
	 	  jQuery.hideMask(document.body);
	 	if(!json){
	 		$.messager.alert('系统提示','获取数据失败，请刷新后重试!','error');
	 		return;
	 	}
	 	var result = json.result;
   	 	var entity = json.entity;
   	 	
   	 	//设置收件人
   	 	selAddressIds = entity.shouJianRen?entity.shouJianRen:[]; 
   		var emails = [];
   		for(var i=0;i<selAddressIds.length;i++){
   			emails.push(selAddressIds[i]['email']);
   		}
   
   		$('#showSeleAddress').html(emails.join(","));
   		
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
   		updateRowIndex = $('#dg').datagrid("getRowIndex",row);
   		  
   		$("#id").val(row['id']);
	   	 var pageSize = getPageSize();
		 var w = pageSize.pageWidth;
		 var h = pageSize.pageHeight;
		
		 initEditor(pageSize);
		 var editor = CKEDITOR.instances.content;
			editor.setData( entity?entity.content:"" );
		  
			
		 $('#contentId').combogrid('setValue', entity?entity.contentId:"");
		 $('#contentId').combogrid('setText', entity?entity.title:"");
		 
		 var temp = entity.mailTemplete; 
		 if(temp){
			 
			 $('#temp_id').textbox('setValue',temp['templeteName']+"["+temp['id']+"]");
		 }
		 
		 
	
		 $('#mail_w').window('resize', {
			  width: w,
			  height: (h-10)
		  });
		  $('#mail_w').window('center');
		  $('#mail_w').window('open').panel('setTitle',"修改发送任务");
		  $('#savebtn').show();
		  onLoadSuccessSelShouJianRen();
	  },
	  error:function(xhr,textStatus,errorThrown){
		  jQuery.hideMask(document.body);
	  	var responseText = xhr.responseText;
	  	$.messager.alert('系统提示','获取数据失败，请刷新后重试!','error');
	  
	  } 
	});
	
	
	 
}
function submitFormFn(){
	var u_id = $("#id").val();
	
	var contentId = $('#contentId').combogrid('getValue');
	var contentName = $('#contentId').combogrid('getText'); 
 	
	
	if(contentId == null ||  contentId.length == 0 || contentId == 0){
		 $.messager.alert('系统提示','请选择要发送的内容!','info');
		 return;
	} 
	if(selAddressIds.length ==0){
		$.messager.alert('系统提示','请选择收件人!','info');
		 return;
	}

	var saveObj = {};
	saveObj.taskId=$.trim(u_id).length==0?0:u_id;
	saveObj.contentId= contentId;
	saveObj.shoujianren = JSON.stringify(selAddressIds);
	 
	if(saveType == 'add'){ insertIntoDb(saveObj); }else if(saveType == 'update'){ updateIntoDb(saveObj); }
}
function insertIntoDb(saveObj){
	var url =  "<%=request.getContextPath()%>/admin/mail/sendertask/doadd";
  	 $.ajax({
  	  	  type:'post',
		  url: url,
		  context: document.body,
		  beforeSend:function(){
			  	//jQuery.showMask($("#w")[0],"正在保存中 ....");
			  jQuery.showMask($("#mail_w")[0],"正在处理中 ....");
			},
		  data:saveObj,
		  success:function(json){
			  jQuery.hideMask($("#mail_w")[0]);
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
			  jQuery.hideMask($("#mail_w")[0]);
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
	 
	var url =  "<%=request.getContextPath() %>/admin/mail/sendertask/doupdate";
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
	alert("undo");
	return  
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
				var url = "<%=request.getContextPath() %>/admin/mail/sendertask/dodel";
		  		 $.ajax({
		  	  	  type:'post',
				  url: url,
				  beforeSend:function(){
					  	//jQuery.showMask($("#w")[0],"正在保存中 ....");
					  jQuery.showMask(document.body,"正在处理中 ....");
				},
				  data:{
					taskId:id ,
				  	d:new Date().getTime()
				  },
				  context: document.body,
				  success:function(json){
					  jQuery.hideMask(document.body);
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
					  jQuery.hideMask(document.body);
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
function comgridselect(index,row){
	var title = row['title'];
	var temp = row["templeteName"]+"["+row['templeteId']+"]";
	var data = row['content'];
	
	 
	$('#temp_id').textbox("setText",temp);
	var editor = CKEDITOR.instances.content;
	editor.setData( data);
	
}
function uNameFormat(value,row,index){ 
		if(value){
			return row['name']+"  ["+row['id']+"]";
		}
		return null;
}
var selAddressIds = [];
function onCheckShouJianRen(index,row){
	var email = row['email'];
	var id = row['id'];
	var index = -1;
	for(var i=0,j = selAddressIds.length;i<j;i++){
		var innerId =  selAddressIds[i]['id'];
		if (innerId == id){
			index = i;
			break;
		}
	}
	if(index>=0){
		//已经存在
		return;
	}
	 
	selAddressIds.push({id:id,email:email,gender:row['gender'],name:row['name']});
	var emails = [];
	for(var i=0;i<selAddressIds.length;i++){
		emails.push(selAddressIds[i]['email']);
	}
	$('#showSeleAddress').html(emails.join(","));
	//$('#showSeleAddress').panel('open').panel('refresh');
}
function onUncheckShouJianRen(index,row){
	var email = row['email'];
	var id = row['id'];

	var index = -1;
	for(var i=0,j = selAddressIds.length;i<j;i++){
		var innerId =  selAddressIds[i]['id'];
		if (innerId == id){
			index = i;
			break;
		}
	}
	if(index<0){
		//不存在
		return;
	}
	selAddressIds.splice(index,1);
	var emails = [];
	for(var i=0;i<selAddressIds.length;i++){
		emails.push(selAddressIds[i]['email']);
	}
	$('#showSeleAddress').html(emails.join(","));
}
function onSelectAllShouJianRen(rows){
	if(!rows) return;
 
	for(var i=0;i<rows.length;i++){
	 
		var row = rows[i];
		onCheckShouJianRen(i,row);
	}
}
function  onUnselectAllShouJianRen(rows){
	if(!rows) return;
	var index = 0;
	for(var i=0;i<rows.length;i++){
		var row = rows[i];
		  onUncheckShouJianRen(i,row);
	}
}
function onLoadSuccessSelShouJianRen(){
	var rows  = $('#tt_dg').datagrid('getRows');
	var allIds = [];
	for(var i=0;i<selAddressIds.length;i++){
		allIds.push(selAddressIds[i]['id']);
	}
	 
	var ids = ","+allIds.join(",")+",";
	for(var i=0,j=rows.length;i<j;i++){
		if(ids.indexOf(","+rows[i]['id']+",")>=0){
			$('#tt_dg').datagrid('selectRecord',rows[i]['id']);
		}
	}
}
function sendMailFn(id){
	/***/
	var rows = $("#dg").datagrid("getRows");
	var row = null;
	for(var i=0;i<rows.length;i++){
		if(rows[i]['id'] == id){
			row = rows[i];
			break;
		}
	}
	var rowIndex = 	$('#dg').datagrid("getRowIndex",row);
	var url = "<%=request.getContextPath() %>/admin/mail/sendertask/dosend";
		 $.ajax({
	  	  type:'post',
		  url: url,
		  data:{
			  taskid:id ,
		  	d:new Date().getTime()
		  },
		  beforeSend:function(){
			  	//jQuery.showMask($("#w")[0],"正在保存中 ....");
			  jQuery.showMask(document.body,"正在处理中 ....");
			  },
	  	context: document.body,
	 	 success:function(json){
	 		jQuery.hideMask(document.body);
			 var result = json.result;
		   	 	var entity = json.entity;
		   	 	if(result){
		   	 	
			 		 $.messager.alert('系统提示','执行成功!','info');
			 		//$('#dg').datagrid('reload');
			 		row['status'] = entity.status;
			 		/***	*/
			 		$('#dg').datagrid("updateRow",{
			 			index: rowIndex,
						row:row
			 		});
			 	
			   	 }else{
			   		$.messager.alert('系统提示','执行失败，请刷新后重试!','error');
			   	 }
		   	 	
	  },
	  error:function(xhr,textStatus,errorThrown){
		  jQuery.hideMask(document.body);
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
	    			$.messager.alert('系统提示','执行失败\r\n'+msgArray.join("\r\n"),'error');
	    		}else{
	    			$.messager.alert('系统提示','执行失败\r\n'+msg,'error');
	    		}
	    	 
	    		
	    	}catch(error){
	    		alert(error);
	    		$.messager.alert('系统提示','执行失败，请刷新后重试!','error');
	    	}
	  
	  } 
});
	
}
function detailWinFn(taskId){
	
	 var pageSize = getPageSize();
	 var w = pageSize.pageWidth;
	 var h = pageSize.pageHeight;
		
	
	 $('#detail_w').window('resize', {
		  width:w,
		  height: (h-10)
	  });
 
	 $('#detail_dg').datagrid('load',{
		 taskId:taskId,
		 d:new Date().getTime()
	 });
	$('#detail_w').window('open').panel('setTitle',"详情页") ;
	$('#detail_w').window('center'); 
}
function sendStatusFormat(value,row,index){
	return mailSendStatus[value]
}
 
	</script>
</html>