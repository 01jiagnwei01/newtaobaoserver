package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.TaskBasicDao;
import com.gxkj.taobaoservice.entitys.TaskBasic;
import com.gxkj.taobaoservice.entitys.UserBase;
@Repository
public class TaskBasicDaoImpl extends BaseDAOImpl implements TaskBasicDao {

	 
	private String getTaskBasicCountReceivedByReceiverIdHQL = "from TaskBasic where userId = :userId and receiverId=:receiverId and receiverTime is not null and receiverTime between :beginTime and :endTime order by id desc";
	
	public ListPager doPageForSite(UserBase userBase, Integer orderId,
			int pageno, int pagesize, Date startTime, Date endTime)
			throws SQLException {
		StringBuffer hql = new StringBuffer("from TaskBasic  where  userId = ? ");
		 List<Object> params = new ArrayList<Object>();
		 boolean haveParam = false;
		 if(userBase != null){
			 if(!haveParam){
				 haveParam = true;
				 hql.append( "  where " );
			 }else {
				 hql.append( "  and " );
			 }
			 hql.append( "  userId = ?" );
			 params.add(userBase.getId());
		 }
		
		 if(startTime != null ){
			 if(!haveParam){
				 haveParam = true;
				 hql.append( "  where " );
			 }else {
				 hql.append( "  and " );
			 }
				hql.append( "  createTime >= ?" );
				params.add(startTime);
				 
		}
		if(endTime != null ){
			 if(!haveParam){
				 haveParam = true;
				 hql.append( "  where " );
			 }else {
				 hql.append( "  and " );
			 }
			hql.append( "  createTime <= ?" );
			params.add(endTime);
		 
		}
		if(orderId != null && orderId!=1){
			 if(!haveParam){
				 haveParam = true;
				 hql.append( "  where " );
			 }else {
				 hql.append( "  and " );
			 }
			hql.append( "  taskOrderId = ?" );
			params.add(orderId);
		}
		
		hql.append(" order by id desc ");
		String hqlString = hql.toString();
		ListPager pager = new ListPager();
		pager.setPageNo(pageno);
		pager.setRowsPerPage(pagesize);
		
		this.selectPageByHql(hqlString, params.toArray(), pager);
		/**
		 * 做翻转
		 */
		if(CollectionUtils.isNotEmpty(pager.getPageData())){
			Collections.reverse(pager.getPageData()); 
		}
		return pager;
	}

	/**
	 * 
	 */
	public TaskBasic getTaskBasicCountReceivedByReceiverId(Date now,
			Integer receiverId, Integer createrId) throws SQLException {
		 String beginTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(now);
		 String endTime = new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(now);
		 Map<String,Object> parameters = new HashMap<String,Object> ();
		 parameters.put("userId", createrId);
		 parameters.put("receiverId", receiverId);
		 parameters.put("beginTime", beginTime);
		 parameters.put("endTime", endTime);
		
		return (TaskBasic) this.selectOneByHQL(getTaskBasicCountReceivedByReceiverIdHQL, parameters);
	}

}
