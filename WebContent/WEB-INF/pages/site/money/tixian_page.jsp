<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page import="java.text.*,com.gxkj.common.util.ListPager,org.apache.commons.lang3.*,com.gxkj.taobaoservice.enums.*,com.gxkj.taobaoservice.entitys.*,com.gxkj.common.util.SessionUtil,java.util.*"%>

<!DOCTYPE html>  
<html>
<meta charset="utf-8">

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
	
	<div style="width:100%; background-color:#ededed; padding-bottom:35px;">

		<div class="center" style="width:1200px;">

			 <jsp:include page="../common/leftmenu.jsp" flush="true">
				<jsp:param name="mainmenu" value="safe"></jsp:param>
				<jsp:param name="submenu" value="czm"></jsp:param>
			</jsp:include>

			<div style="width:910px; padding:0 40px; background-color:#FFF;" class="fr">
			
				<div class="fs16" style="border-bottom:1px #ccc solid; padding:20px 0;">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:20px;">
							<tr>
									<td align="center" bgcolor="#4CA4EE" style="color:#fff;">操作时间</td>
									<td align="center" bgcolor="#4CA4EE" style="color:#fff;">支付宝账号</td>
									<td align="center" bgcolor="#4CA4EE" style="color:#fff;">订单号</td>
									<td align="center" bgcolor="#4CA4EE" style="color:#fff;">金额</td>
									<td align="center" bgcolor="#4CA4EE" style="color:#fff;">状态</td>
									<td align="center" bgcolor="#4CA4EE" style="color:#fff;">备注</td>
							</tr>
							<%
								if(pager == null || pager.getTotalRows() == 0){
									%>
									<tr>
										<td colspan="6" align="center">没有查到数据</td> 
									</tr>
									<%
								}else{
									List<ApplyDrawLog> datas = (List<ApplyDrawLog>)pager.getPageData();
									for(int i=0,l=datas.size();i<l;i++){
										ApplyDrawLog item = datas.get(i);
										%>
										<tr>
									<td align="center"><%=formatter.format(item.getCreateTime()) %></td>
									<td align="center"><%=item.getAccountNo()%></td>
									<td align="center"><%=item.getThirdOrderNo()%></td>
									<td align="center"><%=item.getAmount()%></td>
									<td align="center"><%=item.getStatus().getName()%></td>
									<td align="center"><%if(StringUtils.isNotBlank( item.getRefuseReason() )){
										%><%=item.getRefuseReason()%><%
									}else{
										%>&nbsp;<%
										
									}%></td>
							</tr>
										
										<%
									}
									
								}
							%>
							
							 
					</table>
					<div id="page_container"></div>
					<div style="clear:both;"></div>
				</div>
			</div>
			
		</div>
		<div style="clear:both;"></div>

	</div>

	<div style="clear:both;"></div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">

$(function(){
	<%
	List<DepositAppLog> datas2 = (List<DepositAppLog>)pager.getPageData();
	int currentPage = pager.getPageNo();
	long totalRows = pager.getTotalRows();
		%>
	var pageCount=0;//总页数 
	var currentPage = <%=currentPage%>+1;
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
					  return "<%=request.getContextPath() %>/site/money/tixian/topage?pageno="+(page-1)+"&limit="+pageSize
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
