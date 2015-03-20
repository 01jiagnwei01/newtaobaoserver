<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page import="java.math.*,java.text.*,com.gxkj.common.util.ListPager,org.apache.commons.lang3.*,com.gxkj.taobaoservice.enums.*,com.gxkj.taobaoservice.entitys.*,com.gxkj.common.util.SessionUtil,java.util.*"%>
<!DOCTYPE html>  
<html>
<jsp:include page="../common/mina.jsp"></jsp:include>
<jsp:include page="../common/title.jsp" flush="true">
		<jsp:param name="titletype" value="useraccount_log"></jsp:param>
</jsp:include>
<jsp:include page="../common/css.jsp"></jsp:include>
<jsp:include page="../common/bookstrap.jsp"></jsp:include> 
<style type="text/css">
.cbc:hover{background-color:#D3D3D3;}
table td{padding:5px; font-size:14px; height:25px;}
#kinMaxShow .KMSPrefix_kinMaxShow_button{left:50%; margin-left:-33px;}
</style>
<%
ListPager pager = (ListPager)request.getAttribute("paper");
DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
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
				<a href="###">财务中心</a>&nbsp;>&nbsp;<a href="###">资金记录</a>
			</div>
			<div style="overflow:hidden;">
				<div style="margin-bottom:-5000px; padding-bottom:5000px;">
					<jsp:include page="../common/leftmenu.jsp" flush="true">
						<jsp:param name="mainmenu" value="caiwu"></jsp:param>
						<jsp:param name="submenu" value="useraccount_log"></jsp:param>
					</jsp:include>
				</div>
				<div style="width:990px; padding:0 40px; background-color:#FFF; padding-bottom:5000px; margin-bottom:-5000px;" class="fr">
				 	<h2 style="background:#f8f8f8; font-weight:normal; margin-top:30px;">
	                	<div style="padding:10px;"><span style="font-size:16px;">可用余额：</span><span style="color:rgb(57, 161, 234);">${userAccount.currentBalance }</span></div>
	                </h2>
				 	<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:20px;">
							<tr>
									<td align="center" bgcolor="#4CA4EE" style="color:#fff;" rowspan="2">操作时间</td>
									<td align="center" bgcolor="#4CA4EE" style="color:#fff;" rowspan="2">操作类型</td>
									<td align="center" bgcolor="#4CA4EE" style="color:#fff;" rowspan="2">操作资金</td>
									<td align="center" bgcolor="#4CA4EE" style="color:#fff;" rowspan="2">锁定资金</td>
									<td align="center" bgcolor="#4CA4EE" style="color:#fff;" rowspan="2">操作点数</td>
									<td align="center" bgcolor="#4CA4EE" style="color:#fff;" rowspan="2">锁定点数</td>
									<td align="center" bgcolor="#4CA4EE" style="color:#fff;" colspan="4" >操作后</td>
							</tr>
							<tr>
									<td align="center" bgcolor="#4CA4EE" style="color:#fff;">可用资金</td>
									<td align="center" bgcolor="#4CA4EE" style="color:#fff;">可用点数</td>
									<td align="center" bgcolor="#4CA4EE" style="color:#fff;">锁定资金</td>
									<td align="center" bgcolor="#4CA4EE" style="color:#fff;">锁定点数</td> 
							</tr>
							<%
								if(pager == null || pager.getTotalRows() == 0){
									%>
									<tr>
										<td colspan="10" align="center">没有查到数据</td> 
									</tr>
									<%
								}else{
									List<UserAccountLog> datas = (List<UserAccountLog>)pager.getPageData();
									//Collections.reverse(datas);
									for(int i=0,l=datas.size();i<l;i++){
										UserAccountLog item = datas.get(i);
										%>
										<tr>
									<td align="center"><%=formatter.format(item.getCreateTime()) %></td>
									<td align="center"><%=item.getType().getName()%></td>
									<td align="center"><%if(item.getPayAmount()==null||  BigDecimal.ZERO.compareTo( item.getPayAmount()) == 0) {out.print("&nbsp;");}else {
										out.print( item.getPayAmount());
										}%></td>
									<td align="center"><%if(item.getLockAmount()==null||  BigDecimal.ZERO.compareTo( item.getLockAmount()) == 0) {out.print("&nbsp;");}else {
										out.print( item.getLockAmount());
										}%></td>
									<td align="center"><%if(item.getPayPoints()==null || BigDecimal.ZERO.compareTo( item.getPayPoints()) == 0) {out.print("&nbsp;");}else {
										out.print( item.getPayPoints());}%></td>
									<td align="center"><%if(item.getLockPoint()==null ||  BigDecimal.ZERO.compareTo( item.getLockPoint()) == 0) {out.print("&nbsp;");}else {
										out.print( item.getLockPoint());}%></td>
									<td align="center">
									<%=item.getAfterRestAmount()%>
									</td>
									<td align="center"><%=item.getAfterRestPoints()%> </td>
									<td align="center"><%=item.getAfterLockedAmount() %></td>
									<td align="center"><%=item.getAfterLockedPoints() %></td>
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
	List<UserAccountLog> datas2 = (List<UserAccountLog>)pager.getPageData();
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
					  return "<%=request.getContextPath() %>/site/useraccount/log?pageno="+(page)+"&limit="+pageSize
            //        return "http://example.com/list/page/"+page;

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

</script>
</html>
