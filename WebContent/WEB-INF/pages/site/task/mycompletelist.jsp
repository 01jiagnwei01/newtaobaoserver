<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.gxkj.common.util.SystemGlobals,com.gxkj.taobaoservice.util.*"%>
 <%@ page import="static com.gxkj.taobaoservice.util.SystemDbData.subTaskInfoMap, com.gxkj.common.exceptions.*"%>
 <%@ page import="java.text.*,com.gxkj.common.util.ListPager,org.apache.commons.lang3.*,com.gxkj.taobaoservice.enums.*,com.gxkj.taobaoservice.entitys.*,com.gxkj.common.util.SessionUtil,java.util.*"%>
 
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%
ListPager pager = (ListPager)request.getAttribute("paper");
DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
 <!DOCTYPE html>
<html>
<jsp:include page="../common/mina.jsp"></jsp:include>
<jsp:include page="../common/title.jsp" flush="true">
		<jsp:param name="titletype" value="task_mycompletetasklist"></jsp:param>
</jsp:include>
<jsp:include page="../common/css.jsp"></jsp:include>
<jsp:include page="../common/bookstrap.jsp"></jsp:include>
<style type="text/css">
.cbc:hover {
	background-color: #D3D3D3;
}
table td {
	padding: 5px;
	height: 25px;
	font-size: 14px;
	border-bottom: 1px solid #eee;
}
 
</style>

<body>
	 <jsp:include page="../common/head.jsp" flush="true">
		<jsp:param name="showlogin" value="true"></jsp:param>
		<jsp:param name="showreg" value="false"></jsp:param>
	</jsp:include> 
	<jsp:include page="../common/subheader.jsp" flush="true">
		<jsp:param name="showshouye" value="true"></jsp:param>
	</jsp:include>
	 
	<div style="width:100%; background-color:#ededed; padding-bottom:135px;">
		<div class="center" style="width:1200px;">
			<div style="height:50px; line-height:50px;">
				<a href="###">任务中心</a>&nbsp;>&nbsp;<a href="###">我已完成的任务</a>
			</div>
 
			<div style="overflow:hidden;">
				<div style="margin-bottom:-5000px; padding-bottom:5000px;">
					<jsp:include page="../common/leftmenu.jsp" flush="true">
						<jsp:param name="mainmenu" value="task_center"></jsp:param>
						<jsp:param name="submenu" value="task_mycompletetasklist"></jsp:param>
					</jsp:include>
				</div>
				<div style="width:990px; padding:0 40px; background-color:#FFF; padding-bottom:5000px; margin-bottom:-5000px;" class="fr">
					  <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:20px;">
								<tr>
										<td align="center" bgcolor="#4CA4EE" style="color:#fff;">任务编号</td>
										<td align="center" bgcolor="#4CA4EE" style="color:#fff;">发布时间</td>
										<td align="center" bgcolor="#4CA4EE" style="color:#fff;">担保金额</td>
										<td align="center" bgcolor="#4CA4EE" style="color:#fff;">获利金额</td>
										<td align="center" bgcolor="#4CA4EE" style="color:#fff;">获利点数</td> 
										<td align="center" bgcolor="#4CA4EE" style="color:#fff;">接手状态</td>
										<td align="center" bgcolor="#4CA4EE" style="color:#fff;">操作</td>
								</tr>
								<%
								if(pager == null || pager.getTotalRows() == 0){
									%>
									<tr>
										<td colspan="7" align="center">您还没有完成的任务呢</td> 
									</tr>
									<%
								}else{
									List<TaskBasic> datas = (List<TaskBasic>)pager.getPageData();
									for(int i=0,l=datas.size();i<l;i++){
										TaskBasic item = datas.get(i);
									%>
									
								<tr>
										<td align="center"><%=item.getId() %></td>
										<td align="center"><%=formatter.format(item.getCreateTime()) %></td>
										<td align="center"><%=item.getGuaranteePrice()%> </td>
										<td align="center"><%= item.getPayReceiverMoney()%></td>
										<td align="center"><%= item.getPayReceiverPoints()%> </td> 
										<td align="center"><%=item.getStatus().getName()%></td>
										<td align="center">
											<button class="btn btn-xs btn-default" id="suerbtn<%=item.getId() %>" onclick="detail(this,<%=item.getId() %>)">详情</button>
										</td>
								</tr>
							<% 
									}
								}
							%>
								 
						</table>
					<div id="page_container"></div>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">

$(function(){
	<%
	List<TaskBasic> datas2 = (List<TaskBasic>)pager.getPageData();
	int currentPage = pager.getPageNo();
	long totalRows = pager.getTotalRows();
		%>
	var pageCount=0;//总页数 
	var currentPage = <%=currentPage%>;
	var recordCount = <%=totalRows%>;
	pageSize = <%=pager.getRowsPerPage()%>;
	pageCount=recordCount%pageSize===0?recordCount/pageSize:parseInt(recordCount/pageSize)+1;//
	
	
	var options = {

            tooltipTitles: function (type, page, current) {
                    switch (type) {
                    case "first":
                        return "首页";
                    case "prev":
                        return "前一页";
                    case "next": 
                        return "下一页";
                    case "last":
                        return "尾页";
                    case "page":
                        return "第" + page +"页";
                    }
                },
                onPageChanged: function(e,oldPage,newPage){
                   // alert("Current page changed, old: "+oldPage+" new: "+newPage);
                    //window.open(,"_self");
                },
                pageUrl: function(type, page, current){
					   if(page == current) return "###";
					  return "<%=request.getContextPath() %>/site/task/mycompletelist?pageno="+(page)+"&limit="+pageSize


                },


			size:'normal',
			alignment:'center',
	        currentPage: currentPage,
	        totalPages: pageCount
	    }
	if(recordCount >0){
	    $('#page_container').bootstrapPaginator(options);
		
	}
})

 
function detail(zthis,id){
	var url = "<%=request.getContextPath()%>/site/task/detail?taskId="+id+"&d="+new Date().getTime();
	window.open(url);
}
 
</script>
</html>