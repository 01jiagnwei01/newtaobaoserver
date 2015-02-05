<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals"%>
 <!DOCTYPE html>
<html lang="zh">
<head><%--  --%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单管理信息</title>
<%@include file="../common/easyui-html5.jsp" %>
</head>
<script type="text/javascript">
var menulist = "${_adminUser_.btnMap.menulist}"?true:false;
var menudoupdate = "${_adminUser_.btnMap.menudoupdate}"?true:false;
var menudoadd = "${_adminUser_.btnMap.menudoadd}"?true:false;
var menudodel = "${_adminUser_.btnMap.menudodel}"?true:false;
var hostpath = "<%=request.getContextPath() %>";
function makeTreeData(menus,usecheck){

	if(menus.length==0)return menus;
 	 var mapdata = {};
	  //第一次组织树节点内容
	  for(var i=0;i<menus.length;i++)
	  {
	      var node = menus[i];
	      var menuname = node['name'];
	      var pid = node['pid'];
	      var aid = node['id'] ;
	      
	      var path = node['path'];
	      var isbutton = node['isbutton'];
	      var btnflag = node["btnflag"];
			var obj = {
	    		  id:aid,
	    		  text:menuname,
	    		 // "iconCls":isbutton!=1?"tree-file":"icon-add",
	    		  
	    		  attributes:{
	    		  	path:path,
	    		  	isbutton:isbutton,
	    		  	btnflag:btnflag,
	    		  	pid:pid
	    		  }
	    		  
	    		};
	    		if(isbutton==1){
	    			obj['iconCls'] = 'icon-add';
	    		}
	      mapdata["id_"+aid]= obj;
	     if(usecheck)
		 {
		 	mapdata["id_"+aid]['checked']=false;
		 }

	  }
  var ret = [];
  for(var key in mapdata)
  {
      var node = mapdata[key];
      var pid = mapdata[key]['attributes']['pid'];
      if(mapdata["id_"+pid])
      {
          if(typeof mapdata["id_"+pid]['children'] == 'undefined')
          {
              mapdata["id_"+pid]['children'] = [];
          }
          mapdata["id_"+pid]['children'].push(mapdata[key]);
      }else
      {
          ret.push(mapdata[key]);
      }
  }
  return ret;
}
 
function disableBtn(){
	$("#cancleBtn").attr('disabled','disabled');
	$("#saveBtn").attr('disabled','disabled');

}
function enableableBtn(){
	$("#cancleBtn").removeAttr("disabled");
	$("#saveBtn").removeAttr("disabled");
	
}
</script>
<body class="easyui-layout"> 
	<div data-options="region:'center'">
		<div style="border:1px solid #ddd;text-align:right;position: fixed;width: 100%;margin-left: -30px;">
			<a href="#" id="addBtn" onclick="addFn()" class="easyui-linkbutton" data-options="plain:true,iconCls:'add_btn'">增加</a>
			<a href="#"  id="delBtn" onclick="deleteFn()"  id= "delBtn" class="easyui-linkbutton" data-options="plain:true,iconCls:'del_btn'">删除</a>
			<a href="#"  id="updateBtn" onclick="updateFn()" class="easyui-linkbutton" data-options="plain:true,iconCls:'update_btn'">修改</a>
		</div>
		<ul id="tt" style="width:100%;margin-top: 30px;"></ul>
	</div>
	<!--  弹出窗口 -->
	 <div id="w" class="easyui-window" title="Modal Window" data-options="modal:true,closed:true,iconCls:'icon-save',collapsible:false,minimizable:false,maximizable:false,resizable:false" style="width:500px;height:400px;padding:10px;">
		<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'center',border:false" style="padding:10px;">
						<form id="ff" method="post">
					    	<table>
					    		<tr   style="display: none;">
					    			<td>菜单id:</td>
					    			<td><input class="easyui-validatebox"  style="width:250px;" type="text"  id="id" name="id" data-options="required:false"></input></td>
					    		</tr>
					    		<tr>
					    			<td>上级菜单:</td>
					    			<td>
					    				<input class="easyui-combotree" 
					    					data-options="editable:false,required:true"
					    					id="parentMenu"
					    					style="width:250px;">
					    			</td>
					    		</tr>
					    		<tr>
					    			<td>名称:</td>
					    			<td><input class="easyui-validatebox" style="width:250px;"  type="text" id="name" name="name" data-options="required:true"></input></td>
					    		</tr>
					    		<tr >
					    			<td>类型:</td>
					    			<td><select class="easyui-combobox" style="width:250px;" id="isbutton"  name="isbutton"  data-options="editable:false,panelHeight: 'auto',onChange:onChangeFn">
											<option value="0" selected="selected">菜单</option>
											<option value="1">按钮</option>
										</select>
								</tr>
					    		<tr id="menu_path_tr" >
					    			<td>路径:</td>
					    			<td><input class="easyui-validatebox" style="width:250px;"  type="text"  id="path" name="path" data-options="required:false"></input></td>
					    		</tr>
					    		<tr id="btn_code_tr" style="display: none;">
					    			<td>按钮编码:</td>
					    			<td><input class="easyui-validatebox"  style="width:250px;" type="text"  id="btnflag" name="btnflag" data-options="required:false"></input></td>
					    		</tr>
					    		
					    	</table>
					    </form>
				</div>
				<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" id="saveBtn" onclick="javascript:submitForm()">保存</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" id="cancleBtn" onclick="javascript:closeWinFn()">取消</a>
				</div>
		</div>
		
	</div>
</body>
<script type="text/javascript">
	
	/***/
	var type = 'menu';
	window.treeData = [];
	$(function(){
			if(!menudoadd){
				$("#addBtn").attr("disabled",true);
			}
			if(!menudoupdate){
				$("#updateBtn").hide();
			}
			if(!menudodel){
				$("#delBtn").hide();
			}
			
			$('#tt').tree({
			    url: hostpath+'/admin/menu/list',
			    method: 'get',
				animate: true,
				onBeforeLoad: function(node, param){
					param['date'] = new Date().getTime();
				},
			    loadFilter: function(data){
			    	window.treeData = data;
			         return makeTreeData(data,false);
			    }
			});
	});
	
	
var saveType="add";
function addFn(){
	initFn();
	saveType = "add";
	var data = window.treeData;
	 $('#w').window('open').panel('setTitle',"增加菜单");
	 var menus = []; for(var i=0;i<data.length;i++){ if(data[i]['isbutton']!= 1){ menus.push(data[i]); } }
	 var tree2Data = makeTreeData(menus,false);
	var  newTree2Data = [{
		"id":0,
		"text":"根目录",
		"children":tree2Data
	}];
	 $('#parentMenu').combotree('loadData',newTree2Data);
	 $('#parentMenu').combotree('setValue',0);
}
function closeWinFn()
{
	$('#w').window('close');
}
function onChangeFn(newValue, oldValue){
	if(newValue == 0){
		type = 'menu';
		//$("#menu_path_tr").show();
		$("#btn_code_tr").hide();
		
	}else if(newValue == 1){
		type = 'button';
		//$("#menu_path_tr").hide();
		$("#btn_code_tr").show();
	}

}
function initFn(){
	 
		type = 'menu';
		$("#menu_path_tr").show();
		$("#btn_code_tr").hide();
		$('#ff').form('reset');
		
}
function submitForm(){
 
	var id = $("#id").val();
	var pid =  $('#parentMenu').combotree('getValue');
	var name =  $('#name').val();
	var isbutton = $('#isbutton').combo('getValue');
	var path = $("#path").val();
	var btnflag = $("#btnflag").val();
	
	 
	if($.trim(name).length==0){
		$.messager.alert('系统提示','名称不能为空!','warning');
		return;
	}
	if(isbutton ==1 ){/**按钮*/
	
		if(saveType == 'update'){
			var node = $('#tt').tree("find",id);
			 var isLeaf = $('#tt').tree('isLeaf',node.target);
			 if(!isLeaf){
			 	$.messager.alert('系统提示','此菜单下有内容不能修改为按钮!','warning');
			 	return;
			 }
		 }
		if($.trim(btnflag).length==0){
			$.messager.alert('系统提示','按钮编码不能为空!','warning');
			return;
		}
	}else {
		btnflag = "";
	}
	 
	if(saveType == 'add'){
		
		var url = hostpath+"/admin/menu/doadd";
		 $.ajax({
		  	  	  type:'post',
				  url: url,
				  context: document.body,
				  beforeSend:function(){
				  	disableBtn();
				  	jQuery.showMask($("#w")[0],"正在保存中 ....");
				  	return true;
				  },
				  data:{
				  	name:name,
				  	isbutton:isbutton,
				  	btnflag:btnflag,
				  	path:path,
				  	pid:pid
				  },
				  success:function(json){
				  	 enableableBtn();
				  	 jQuery.hideMask($("#w")[0]);
				  	 if(!json){
				  		 $.messager.alert('系统提示','保存失败,该按钮编码已经存在,请修改!','info',function(){
				 	   		 
				 	   	});
				 	   	return;
				  	 }
				 	// var json_str = JSON2.stringify(json);
				 	  $('#tt').tree("reload");
				 	   $.messager.alert('系统提示','保存成功!','info',function(){
				 	   		closeWinFn();
				 	   });
				 	    
			 	   	   
				 	  
				  },
				  error:function(xhr,textStatus,errorThrown){
				  	 enableableBtn();
				  	jQuery.hideMask($("#w")[0]);
				  	var responseText = xhr.responseText;
				  	$.messager.alert('系统提示','保存失败，请刷新后重试!','error');
				  
				  } 
		});
	}else if(saveType == 'update') {
		var tree = $('#parentMenu').combotree('tree');
		var selNode = tree.tree("find",pid);
		if(pid == id){
			$.messager.alert('系统提示','禁止向自身节点移动!','warning');
			return;
		}
		
		try{
			var isError = false;
			var xxxnode = selNode;
			while( true ){
				var pNode = tree.tree("getParent",xxxnode.target);
				
				if(pNode.id == id){
					isError = true;
					break;
				}else if(pNode.id == 0){
					break;
				}
				xxxnode = pNode;
			}
			if(isError){
				$.messager.alert('系统提示','禁止向子节点移动!','warning');
				return;
			}
		}catch(e){
		}
		//数据入库
		var url = hostpath+"/admin/menu/doupdate";
	  	 $.ajax({
	  	  	  type:'post',
			  url: url,
			  context: document.body,
			  beforeSend:function(){
				  	disableBtn();
				  	jQuery.showMask($("#w")[0],"正在保存中 ....");
				  },
			  data:{
			  	id:id,
			  	name:name,
				  	isbutton:isbutton,
				  	btnflag:btnflag,
				  	path:path,
				  	pid:pid
			  	
			  },
			  success:function(json){
			  		
			  		jQuery.hideMask($("#w")[0]);
			 	// var json_str = JSON2.stringify(json);
			  		enableableBtn();
			 	//	 var json_str = JSON2.stringify(json);
			 		if(!json){
				  		 $.messager.alert('系统提示','保存失败,该按钮编码已经存在,请修改!','info',function(){
				 	   		 
				 	   	});
				 	   	return;
				  	 }
			 	 	 $('#tt').tree("reload");
			 	 	 $.messager.alert('系统提示','保存成功!','info');
		 	   	  	 $("div[class='messager-button']").bind('click',function(){
						closeWinFn();
				  	});
			  	 
			  
			  },
			  error:function(xhr,textStatus,errorThrown){
			  		 enableableBtn();
			  		jQuery.hideMask($("#w")[0]);
				  	var responseText = xhr.responseText;
				  	$.messager.alert('系统提示','保存失败，请刷新后重试!','error');
			  
			  } 
			}); 
	} 
}
function updateFn(){
	 var node = $('#tt').tree('getSelected');
	 if(node == null){
	 	$.messager.alert('系统提示','请选择要修改的菜单!','warning');
	 	return;
	 }
	//alert(JSON2.stringify(node.attributes));
	saveType = "update";
	var data = window.treeData;
	 $('#w').window('open').panel('setTitle',"修改菜单");
	 
	 var menus = []; for(var i=0;i<data.length;i++){ if(data[i]['isbutton']!= 1){ menus.push(data[i]); } }
	 var tree2Data = makeTreeData(menus,false);
	 var  newTree2Data = [{
		"id":0,
		"text":"根目录",
		"children":tree2Data
	 }];
	 $('#parentMenu').combotree('loadData',newTree2Data);
	 $('#parentMenu').combotree('setValue',node.attributes.pid);
	 $('#name').val(node.text);
	 $('#name').validatebox("validate");
	 
	 var isbutton = node.attributes.isbutton
	 $('#isbutton').combobox('setValue',isbutton);	
	 
	 if(isbutton == 0){
		
		$("#btn_code_tr").hide();
		
	}else if(isbutton == 1){
		 
		
		$("#btn_code_tr").show();
	}
	$("#path").val(node.attributes.path);
	$("#btnflag").val(node.attributes.btnflag);
	$("#id").val(node.id);
	 

}
function deleteFn()
{
 	var node = $('#tt').tree('getSelected');
	 if(node == null){
	 	$.messager.alert('系统提示','请选择要删除的菜单!','warning');
	 	return;
	 }
	 var isLeaf = $('#tt').tree('isLeaf',node.target);
	 if(!isLeaf){
	 	$.messager.alert('系统提示','只能操作叶子节点!','warning');
	 	return;
	 }
	 $.messager.confirm('系统提示', '您确定要删除选择的节点吗?', function(r){
				if (r){
					delIndb(node.id);
				}
	});
	 
}
function delIndb(nodeId){
	var url = hostpath+"/admin/menu/dodel?d="+new Date().getTime();
		$.ajax({
			  url: url,
			  context: document.body,
			  beforeSend:function(){
			  		$("#delBtn").attr('disabled','disabled');
				  
				 },
			  data:{
			   id:nodeId
			  },
			  success:function(json){
				 	 $('#tt').tree("reload");
				 	 $.messager.alert('系统提示','删除成功!','info',function(){
				 	 	$("#delBtn").removeAttr("disabled");
				 	 });
			 	   	 
			  },
		  error:function(xhr,textStatus,errorThrown){
		  	var responseText = xhr.responseText;
		  	$.messager.alert('系统提示','删除失败，请刷新后重试!','error');
		  	$("#delBtn").removeAttr("disabled");
		  
		  } 
		});

}
</script>
</html>