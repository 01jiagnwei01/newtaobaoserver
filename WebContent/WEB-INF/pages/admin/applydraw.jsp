<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals"%>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>取款管理 </title>
<%@include file="../common/easyui-html5.jsp" %>
</head>
<body class="easyui-layout">
	 <div data-options="region:'center'">
		<table id="dg"></table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<table width="100%">
			<tr width="100%">
				<td width="100%">
					申请用户编号: <input class="easyui-numberspinner" style="width:160px" id="userId"/>
					申请起始日期: <input class="easyui-datebox" style="width:160px" id="createBeginTime"/>
					申请结束日期: <input class="easyui-datebox" style="width:160px" id="createEndTime"/>
					金额: <input class="easyui-numberspinner" style="width:160px" id="amount"/>
				</td>
			</tr>
			<tr width="100%">
				<td width="100%">
					支付宝流水号: <input class="easyui-textbox" style="width:160px" id="thirdOrderNo"/>
					状态: <select id="status" class="easyui-combobox"   style="width:160px;"  data-options='panelHeight:90'>
						<option value="">不限</option>
						<option value="WAIT_FOR_AUDIT">待审</option>
						<option value="REFUSE">拒绝</option>
						<option value="APPROVE">通过</option> 
					</select> 
					审核起始日期: <input class="easyui-datebox" style="width:160px" id="reviewBeginTime"/>
					审核结束日期: <input class="easyui-datebox" style="width:160px" id="reviewEndTime"/>
					 
				</td>
			</tr>
			<tr width="100%">
				<td width="100%">
					审核者编号: <input class="easyui-numberspinner" style="width:160px" id="auditorId"/>
					<a href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFn()">查看</a>
				</td>
			</tr>
		</table>
	</div>
	 
	 <div id="win" class="easyui-window" title="拒绝" style="width:400px;height:320px" 
	 	 data-options="modal:true,closed:true,iconCls:'icon-save',collapsible:false,minimizable:false,maximizable:false,resizable:false">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'center'">
					<table>
						<tr>
							<td>拒绝理由</td>
						</tr>
						<tr>
							<td>
								<input type="hidden" id="reasonId" value="">
								<textarea rows="12" cols="40" id='reason'></textarea>
							</td>
						</tr>
					</table>
				</div>
				 <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
					<a id="refBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:refuseSubmitFormFn(this)">保存</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:closeWinFn()">取消</a>
				</div>
			</div>
	</div>
	 <div id="agreewin" class="easyui-window" title="同意" style="width:400px;height:320px" 
	 	 data-options="modal:true,closed:true,iconCls:'icon-save',collapsible:false,minimizable:false,maximizable:false,resizable:false">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'center'" style="padding:30px 20px 50px 20px">
					<div style="margin-bottom:20px;">
							<div>流水号:</div>
							<input class="easyui-textbox" style="width:80%" id="agreeThirdNo">
							<input type="hidden" id="agreeId" value="">
					</div>
				</div>
				 <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
					<a  id = 'agreebtn' class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:submitAgreeFormFn(this)">保存</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:closeAgreeWinFn()">取消</a>
				</div>
			</div>
	</div>
</body>
<script type="text/javascript">
var admin_applydraw_dopage = "${_adminUser_.btnMap.admin_applydraw_dopage}" == "true" ?true:false;
var admin_applydraw_doarefuse = "${_adminUser_.btnMap.admin_applydraw_doarefuse}" == "true" ?true:false;
var admin_applydraw_doagree = "${_adminUser_.btnMap.admin_applydraw_doagree}" == "true"?true:false; 
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
	  	url:'<%=request.getContextPath() %>/admin/applydraw/dopage?d='+new Date().getTime(),
	  	queryParams:{ },
	  	onBeforeLoad:function(param){
			param['pageno'] =  param['page']-1;
			param['pagesize']  = param['rows'];
			 if(!admin_applydraw_dopage){
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
			{field:'userId',title:'申请用户ID' ,width:80},
			{field:'createTime',title:'申请日期',width:120,formatter:dateFormat},
			{field:'amount',title:'申请金额' ,width:100},
			{field:'thirdOrderNo',title:'支付宝流水号' ,width:100},
			{field:'status',title:'状态',width:100,formatter:statusFormat},
			{field:'auditorName',title:'审核人' ,width:100,formatter:auditorNameFormat},
			{field:'reviewTime',title:'审核日期',width:100,formatter:dateFormat},
			{field:'refuseReason',title:'拒绝理由',width:100},
			{field:'opt',title:'操作' ,width:100,formatter:optFormat} 
		]],
		toolbar: '#tb',
		loadFilter:function(data){
			
			var result = data.result;
			if(!result){
				if(data.msg ){alert(data.msg)}
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
})
function auditorNameFormat(value,row,index){
	if(!value)return "";
	 return value+"["+row['auditorId']+"]"
}
function statusFormat(value,row,index){
	if(value == 'WAIT_FOR_AUDIT') {
		return "等待审核";
	}else if(value == 'APPROVE') {
		return "通过";
	}else if(value == 'REFUSE') {
		return "拒绝";
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
	
	if(row['status'] == 'WAIT_FOR_AUDIT') {
		var btns = [];
		 if(admin_applydraw_doagree){
			btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="passFn(\''+row['id']+'\')" href="#" plain="true" iconCls="update_btn"><span class="l-btn-left"><span class="l-btn-text update_btn l-btn-icon-left">通过</span></span></a>');
		 }
		 if(admin_applydraw_doarefuse){
			btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="disPassFn(\''+row['id']+'\')" href="#" plain="true" iconCls="del_btn"><span class="l-btn-left"><span class="l-btn-text del_btn l-btn-icon-left">拒绝</span></span></a>');
		 }
			return btns.join("&nbsp;");
	}else if(value == 'APPROVE') {
		 
	}else if(value == 'REFUSE') {
		 
	}
	
}
function searchFn(){
 
	var userId = $("#userId").numberspinner('getValue');  
	var createBeginTime = $("#createBeginTime").datebox('getValue');
	var createEndTime = $("#createEndTime").datebox('getValue');
	
	var amount = $("#amount").numberspinner('getValue');
	var thirdOrderNo = $('#thirdOrderNo').val();//.textbox('getValue');
	var status = $('#status').combobox('getValue');
	
	var reviewBeginTime = $("#reviewBeginTime").datebox('getValue');
	var reviewEndTime = $("#reviewEndTime").datebox('getValue');
	var auditorId =  $("#auditorId").numberspinner('getValue');  
	
	$('#dg').datagrid('load',{
		userId:userId,
		createBeginTime:createBeginTime,
		createEndTime:createEndTime,
		amount:amount,
		thirdOrderNo:thirdOrderNo,
		status:status,
		reviewBeginTime:reviewBeginTime,
		reviewEndTime:reviewEndTime,
		auditorId:auditorId,
		d:new Date().getTime()
	});
}
function disPassFn(id){
	$('#reasonId').val(id);
	$('#reason').val('');
	$('#win').window('open');  
	$('#win').window('center');
}
function closeWinFn(){
	$('#win').window('close');
	$('#refBtn').linkbutton('enable');
	 // $('#refBtn').text("提交");
}
function closeAgreeWinFn(){
	$('#agreewin').window('close');
	 $('#agreebtn').linkbutton('enable');
	 //$('#agreebtn').text("提交");
}
function passFn(id){
	$('#agreeId').val(id); 
	$('#agreeThirdNo').textbox('reset');	
	$('#agreewin').window('open');  
	$('#agreewin').window('center'); 
}
function refuseSubmitFormFn(btn){
	var reasonId = $('#reasonId').val();
	var reason =  $('#reason').val();
	var rows = $("#dg").datagrid("getRows");
	var row = null;
	for(var i=0;i<rows.length;i++){
		if(rows[i]['id'] == reasonId){
			row = rows[i];
			break;
		}
	}
	var updateRowIndex = 	$('#dg').datagrid("getRowIndex",row);
	if(updateRowIndex <0){
		alert("错误");
		return;
	}
	$.ajax({ 
		url: "<%=request.getContextPath() %>/admin/applydraw/doarefuse?d="+new Date().getTime(), 
		method:'POST',
		data:{applyId:reasonId,reason:reason},
		 beforeSend:function(){
			  $(btn).linkbutton('disable');
			 // $(btn).text("正在提交中。。。");
			  jQuery.showMask($("#win")[0],"正在保存中 ....");
		 },
		context: document.body, 
		success: function(json){
	    	//$(this).addClass("done");
	    	 jQuery.hideMask($("#win")[0]);
	    	if(json.result){
	    		var  entity = json.entity;
			 	 $('#dg').datagrid('updateRow',{
			  	 	index:updateRowIndex,
			  	 	row:entity
			  	 });
			 	 $.messager.alert('系统提示','保存成功!','info',closeWinFn);
	    	}else{
	    		 $.messager.alert('系统提示','保存失败!'+json.msg,'error',closeWinFn);
	    	}
	    	 
		},
		error:function (XMLHttpRequest, textStatus, errorThrown) {
			var msg = XMLHttpRequest.responseText;
			
			var json =   $.parseJSON(msg);
			$.messager.alert('系统提示','保存失败，'+json.msg,'error');
			
		    // 通常 textStatus 和 errorThrown 之中
		    // 只有一个会包含信息
		    this; // 调用本次AJAX请求时传递的options参数
		    $(btn).attr("disabled",false); 
		    jQuery.hideMask($("#win")[0]);
		   
		}
	});
}
function submitAgreeFormFn(btn){
	//$('#agreeId').val(id);
	//$('#agreeThirdNo').val('');
	var agreeId = $('#agreeId').val();
	var agreeThirdNo =  $('#agreeThirdNo').val();
	var rows = $("#dg").datagrid("getRows");
	var row = null;
	for(var i=0;i<rows.length;i++){
		if(rows[i]['id'] == agreeId){
			row = rows[i];
			break;
		}
	}
	var updateRowIndex = 	$('#dg').datagrid("getRowIndex",row);
	if(updateRowIndex <0){
		alert("错误");
		return;
	}
	$.ajax({ 
		url: "<%=request.getContextPath() %>/admin/applydraw/doagree?d="+new Date().getTime(), 
		method:'POST',
		data:{thirdOrderNo:agreeThirdNo ,applyId:agreeId},
		beforeSend:function(){
			  $(btn).linkbutton('disable');
			  jQuery.showMask($("#agreewin")[0],"正在保存中 ....");
		 },
		context: document.body, 
		success: function(json){
			 jQuery.hideMask($("#agreewin")[0]);
	    	if(json.result){
	    		var  entity = json.entity;
			 	 $('#dg').datagrid('updateRow',{
			  	 	index:updateRowIndex,
			  	 	row:entity
			  	 });
			 	 $.messager.alert('系统提示','保存成功!','info',closeAgreeWinFn);
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
		    $(btn).attr("disabled",false); 
		    jQuery.hideMask($("#agreewin")[0]);
		   
		}
	});
}
</script>
</html>