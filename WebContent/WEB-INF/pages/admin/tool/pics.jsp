<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals"%>
  <%@ page import="com.gxkj.taobaoservice.enums.MailTempleteTypes"%>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片管理 </title>
<%@include file="../../common/easyui-html5.jsp" %>
<script type="text/javascript">
var fenye = "${_adminUser_.btnMap.admin_tool_pics_dopage}"== "true"?true:false;
var add = "${_adminUser_.btnMap.admin_tool_pics_upload}"== "true"?true:false;
 
var del = "${_adminUser_.btnMap.admin_tool_pics_del}"== "true"?true:false; 

</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
		<table class="easyui-datagrid"   
					id ='dg' 
		           data-options="fit:true,fitColumns:true,border:false,rownumbers:true,checkOnSelect:true,singleSelect:true,pagination:true,
		           pageSize:20,url:'<%=request.getContextPath() %>/admin/tool/pics/dopage?d='+new Date().getTime(),method:'POST'
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
								<th data-options="field:'picPath',width:150,formatter:imgFormat"  >图片</th> 
								<th data-options="field:'picName',width:150,formatter:picFormat,styler:picStyler,nowrap:false"  >图片名称/路径</th> 
								<th data-options="field:'picDesc',width:150"  >图片描述</th>
								<th data-options="field:'createTime',width:150,formatter:dateFormat"  >修改时间</th>
								<th data-options="field:'createUser',width:150,formatter:userFormat"  >修改人</th>
								<th data-options="field:'opt',formatter:optFormat,width:150"  >操作</th> 
					</tr>
					
				</thead>
			</table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<table style="width:100%">
			<tr >
				<td width="50%">
					名称: <input class="easyui-textbox" style="width:160px" id="picName"/>
					<a href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFn()">搜索</a>
				</td>
				<td align="right" width="50%">
					<a href="#"    class="easyui-linkbutton" id="addBtn"  data-options="iconCls:'add_btn',plain:true" onclick="addFn()">创建</a>
				</td>
				 
			</tr>
		</table>
	</div>
	 <div id="templete_w" class="easyui-window" title="上传图片" data-options="modal:true,closed:true,iconCls:'icon-save',
		collapsible:false,minimizable:false,maximizable:false,resizable:false" 
			style="width:600px;height:420px;padding:10px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',border:false" id="content_layout" >
				<form action="<%=request.getContextPath() %>/admin/tool/pics/upload"
				 enctype='multipart/form-data' method="POST" target='hidden_frame' id="ff">
				 	图片名称：<input class="easyui-textbox" data-options="" name="picName" style="width:300px"><br/>
				 	图片描述：<input class="easyui-textbox" data-options="" name="picDesc" style="width:300px"><br/>
					  <input class="easyui-filebox" name="pic" id="pic" data-options="prompt:'请选择要上传的图片'
					  ,buttonText:'选择',buttonAlign:'right'
					  " style="width:100%"/>
			  	</form>
			 </div>
			 <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
					<a class="easyui-linkbutton" id="savebtn"  data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:submitFormFn()">保存</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:closeWinFn()">关闭</a>
			</div>
		</div>
	</div>
	<iframe name='hidden_frame' id="hidden_frame" style='display:none'></iframe>  
</body>
<script type="text/javascript">

$(function(){
 	if(!add){
 		$("#addBtn").hide();
 	}
});
function searchFn(){
	var picName = $("#picName").val(); 
	$('#dg').datagrid('load',{
		name:picName, 
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
function userFormat(value,row,index){
	if(value){
		return row['createUserName']+"["+row['createUser']+"]"
	}
	return value;
}
function optFormat(value,row,index){
		var btns = [];
		btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="copyFn(\''+row['id']+'\')" href="#" plain="true" iconCls="update_btn"><span class="l-btn-left"><span class="l-btn-text update_btn l-btn-icon-left">拷贝图片路径</span></span></a>');
		 if(del){
				btns.push('<a class="easyui-linkbutton l-btn l-btn-plain" onclick="delFn(\''+row['id']+'\')" href="#" plain="true" iconCls="del_btn"><span class="l-btn-left"><span class="l-btn-text del_btn l-btn-icon-left">删除</span></span></a>');
		 }
		return btns.join("&nbsp;");
}
function imgFormat(value,row,index){
	 
	return "<img style='width:100px;heigh:100px;' src='"+value+"'>";
}
function picFormat(value,row,index){
	 
	return value+"<br/>"+row['picPath'];
}
function picStyler(value,row,index){
	return 'white-space:normal;word-break:break-all;nowrap: true;word-wrap:break-word;';
}
function callback(json){
 var result  = json['result'];
 if(result){
	 $.messager.alert('系统提示','上传成功!','info',function(){
		 closeWinFn();
		 $('#dg').datagrid("reload",{
			 d:new Date().getTime()
		 });
	 });
	 return;
 }
 alert("失败");
}
var saveType = "add";
function closeWinFn(){
	$('#templete_w').window('close');
}
function addFn(){
	saveType = "add";
	 $("#ff").form("reset");
	 $('#templete_w').window('open').panel('setTitle',"上传图片");
	 $('#savebtn').show();
}
 

function submitFormFn(){
	var pic = $('#pic').textbox('getValue');
	if($.trim(pic).length  == 0){
		 $.messager.alert('系统提示','请选择要上传的图片!','info');
		 return;
	}
	$("#ff").submit();
	 
} 

function copyFn(id){
	var rows = $("#dg").datagrid("getRows");
	var row = null;
	for(var i=0;i<rows.length;i++){
		if(rows[i]['id'] == id){
			row = rows[i];
			break;
		}
	}
	if(row == null) return;
	var path = row['picPath'];
	copyToClipBoard(path);
}
//复制到剪切板js代码
function copyToClipBoard(s) {

    //alert(s);
    if (window.clipboardData) {

        window.clipboardData.setData("Text", s);

        alert("已经复制到剪切板！"+ "\n" + s);
        return true;

    } else if (navigator.userAgent.indexOf("Opera") != -1) {

        window.location = s;

    } else if (window.netscape) {

        try {

            netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");

        } catch (e) {

            alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");
            return false;

        }

        var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);

        if (!clip)

            return false;

        var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);

        if (!trans)

        	 return false;

        trans.addDataFlavor('text/unicode');

        var str = new Object();

        var len = new Object();

        var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);

        var copytext = s;

        str.data = copytext;

        trans.setTransferData("text/unicode", str, copytext.length * 2);

        var clipid = Components.interfaces.nsIClipboard;

        if (!clip)

            return false;

        clip.setData(trans, null, clipid.kGlobalClipboard);

       return true;

    }
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
				var url = "<%=request.getContextPath() %>/admin/tool/pics/dodel";
		  		 $.ajax({
		  	  	  type:'post',
				  url: url,
				  data:{
				  	id:id
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
	</script>
</html>